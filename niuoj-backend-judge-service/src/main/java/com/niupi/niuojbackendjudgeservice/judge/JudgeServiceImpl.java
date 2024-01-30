package com.niupi.niuojbackendjudgeservice.judge;

import cn.hutool.json.JSONUtil;
import com.niupi.niuojbackendcommon.common.ErrorCode;
import com.niupi.niuojbackendcommon.exception.BusinessException;
import com.niupi.niuojbackendjudgeservice.judge.codesandbox.CodeSandBox;
import com.niupi.niuojbackendjudgeservice.judge.codesandbox.CodeSandBoxFactory;
import com.niupi.niuojbackendjudgeservice.judge.codesandbox.CodeSandBoxProxy;
import com.niupi.niuojbackendjudgeservice.judge.strategy.JudgeContext;
import com.niupi.niuojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.niupi.niuojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import com.niupi.niuojbackendmodel.model.codesandbox.JudgeInfo;
import com.niupi.niuojbackendmodel.model.dto.question.JudgeCase;
import com.niupi.niuojbackendmodel.model.entity.Question;
import com.niupi.niuojbackendmodel.model.entity.QuestionSubmit;
import com.niupi.niuojbackendmodel.model.enums.QuestionSubmitStatusEnum;
import com.niupi.niuojbackendserviceclient.service.QuestionFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jillion
 * @create com.niupi.niuoj.judge - the name of the target package where the new class or interface will
 * be created. niuoj - the name of the current project. JudgeServiceImpl.java.java - the name of the PHP file that will be created. JudgeServiceImpl - the name of the new file which you specify in the New File dialog box during thefile creation. Tom - the login name of the current user. 2024/1/5 - the current system date. 15:33 - the current system time. 2024 - the current year. 01 - the current month. 05 - the current day of the month. 15 - the current hour. 33 - the current minute. IntelliJ IDEA - the name of the IDE in which the file will be created. 1月 - the first 3 letters of the month name. Example: Jan, Feb, etc. 一月 - full name of a month. Example: January, February, etc.
 */
@Service
public class JudgeServiceImpl implements JudgeService {
    @Resource
    private QuestionFeignClient questionFeignClient;
    @Resource
    private JudgeManager judgeManager;

    @Value("${codesandbox.type:example}")
    private String type;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
//       传入题目的提交 id，获取到对应的题目、提交信息（包含代码、编程语言等）
        QuestionSubmit questionSubmit = questionFeignClient.getQuestionSubmitById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionFeignClient.getQuestionById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }
        // 如果题目提交状态不为等待中，就不用重复执行了
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题中");
        }
        // 更改判题（题目提交）的状态为 “判题中”，防止重复执行
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
//        调用沙箱，获取执行结果
        CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance(type);
        codeSandBox = new CodeSandBoxProxy(codeSandBox);
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
//       获取输入用例
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputlist = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputlist)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        List<String> outputList = executeCodeResponse.getOutputList();
//        根据沙箱的执行结果，判断题目的判题状态和信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputlist);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
//        修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
        QuestionSubmit questionSubmitResult = questionFeignClient.getQuestionSubmitById(questionId);
        return questionSubmitResult;
    }
}
