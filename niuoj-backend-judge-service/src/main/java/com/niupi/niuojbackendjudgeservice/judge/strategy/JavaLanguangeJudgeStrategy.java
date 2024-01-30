package com.niupi.niuojbackendjudgeservice.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.niupi.niuojbackendmodel.model.codesandbox.JudgeInfo;
import com.niupi.niuojbackendmodel.model.dto.question.JudgeCase;
import com.niupi.niuojbackendmodel.model.dto.question.JudgeConfig;
import com.niupi.niuojbackendmodel.model.entity.Question;
import com.niupi.niuojbackendmodel.model.enums.JudgeInfoMessageEnum;

import java.util.List;
import java.util.Optional;

/**
 * Java程序的判题策略
 */
public class JavaLanguangeJudgeStrategy implements JudgeStrategy {

    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        long memoryLimit = Optional.ofNullable(judgeInfo.getMemoryLimit()).orElse(0L);
        long time = Optional.ofNullable(judgeInfo.getTime()).orElse(0L);
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        Question question = judgeContext.getQuestion();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.ACCEPTED;
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setMemoryLimit(memoryLimit);
        judgeInfoResponse.setTime(time);
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());

// 先判断沙箱执行的结果输出数量是否和预期输出数量相等
        if (outputList.size() != inputList.size()) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;

        }
        // 依次判断每一项输出和预期输出是否相等
        for (int i = 0; i < judgeCaseList.size(); i++) {
            JudgeCase judgeCase = judgeCaseList.get(i);
            if (!judgeCase.getOutput().equals(outputList.get(i))) {
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
                return judgeInfoResponse;

            }
        }
        // 判断题目限制
        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        long needMemoryLimit = judgeConfig.getMemoryLimit();
        long needTimeLimit = judgeConfig.getTimeLimit();
        if (memoryLimit > needMemoryLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        }
//        假如Java程序运行需要额外的10秒钟，而不算做题目要求内
        long JAVA_PROGRAM_TIME_COST = 10000L;
        if ((needTimeLimit - JAVA_PROGRAM_TIME_COST) > time) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        }
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        return judgeInfoResponse;
    }
}
