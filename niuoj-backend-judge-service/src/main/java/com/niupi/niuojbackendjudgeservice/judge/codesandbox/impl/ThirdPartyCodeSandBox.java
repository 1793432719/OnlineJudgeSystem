package com.niupi.niuojbackendjudgeservice.judge.codesandbox.impl;



/**
 * @author jillion
 * @create com.niupi.niuoj.judge.codesandbox - the name of the target package where the new class or interface will
 * be created. niuoj - the name of the current project. CodeSandBoxImpl.java.java - the name of the PHP file that will be created. CodeSandBoxImpl - the name of the new file which you specify in the New File dialog box during thefile creation. Tom - the login name of the current user. 2024/1/4 - the current system date. 17:37 - the current system time. 2024 - the current year. 01 - the current month. 04 - the current day of the month. 17 - the current hour. 37 - the current minute. IntelliJ IDEA - the name of the IDE in which the file will be created. 1月 - the first 3 letters of the month name. Example: Jan, Feb, etc. 一月 - full name of a month. Example: January, February, etc.
 */

import com.niupi.niuojbackendjudgeservice.judge.codesandbox.CodeSandBox;
import com.niupi.niuojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.niupi.niuojbackendmodel.model.codesandbox.ExecuteCodeResponse;

/**
 * 第三方代码沙箱(调用网上借口沙箱入go-judge)
 */
public class ThirdPartyCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
