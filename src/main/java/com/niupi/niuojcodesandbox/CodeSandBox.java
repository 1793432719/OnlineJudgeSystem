package com.niupi.niuojcodesandbox;

import com.niupi.niuojcodesandbox.model.ExecuteCodeRequest;
import com.niupi.niuojcodesandbox.model.ExecuteCodeResponse;

/**
 * @author jillion
 * @create com.niupi.niuojcodesandbox - the name of the target package where the new class or interface will
 * be created. niuoj-code-sandbox - the name of the current project. null.java - the name of the PHP file that will be created. CodeSandBox - the name of the new file which you specify in the New File dialog box during thefile creation. Tom - the login name of the current user. 2024/1/6 - the current system date. 11:09 - the current system time. 2024 - the current year. 01 - the current month. 06 - the current day of the month. 11 - the current hour. 09 - the current minute. IntelliJ IDEA - the name of the IDE in which the file will be created. 1月 - the first 3 letters of the month name. Example: Jan, Feb, etc. 一月 - full name of a month. Example: January, February, etc.
 */
public interface CodeSandBox {
    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
