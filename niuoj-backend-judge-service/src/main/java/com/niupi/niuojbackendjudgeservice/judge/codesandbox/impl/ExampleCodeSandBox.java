package com.niupi.niuojbackendjudgeservice.judge.codesandbox.impl;



import com.niupi.niuojbackendjudgeservice.judge.codesandbox.CodeSandBox;
import com.niupi.niuojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.niupi.niuojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import com.niupi.niuojbackendmodel.model.codesandbox.JudgeInfo;
import com.niupi.niuojbackendmodel.model.enums.JudgeInfoMessageEnum;
import com.niupi.niuojbackendmodel.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * @author jillion
 * @create com.niupi.niuoj.judge.codesandbox - the name of the target package where the new class or interface will
 * be created. niuoj - the name of the current project. CodeSandBoxImpl.java.java - the name of the PHP file that will be created. CodeSandBoxImpl - the name of the new file which you specify in the New File dialog box during thefile creation. Tom - the login name of the current user. 2024/1/4 - the current system date. 17:37 - the current system time. 2024 - the current year. 01 - the current month. 04 - the current day of the month. 17 - the current hour. 37 - the current minute. IntelliJ IDEA - the name of the IDE in which the file will be created. 1月 - the first 3 letters of the month name. Example: Jan, Feb, etc. 一月 - full name of a month. Example: January, February, etc.
 */

/**
 * 实例代码沙箱(仅实现业务流程)
 */
public class ExampleCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemoryLimit(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
