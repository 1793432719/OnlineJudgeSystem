package com.niupi.niuojcodesandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jillion
 * @create com.niupi.niuoj.judge.codesandbox.model - the name of the target package where the new class or interface will
 * be created. niuoj - the name of the current project. null.java - the name of the PHP file that will be created. ExecuteCodeRequest - the name of the new file which you specify in the New File dialog box during thefile creation. Tom - the login name of the current user. 2024/1/4 - the current system date. 17:05 - the current system time. 2024 - the current year. 01 - the current month. 04 - the current day of the month. 17 - the current hour. 05 - the current minute. IntelliJ IDEA - the name of the IDE in which the file will be created. 1月 - the first 3 letters of the month name. Example: Jan, Feb, etc. 一月 - full name of a month. Example: January, February, etc.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeResponse {
    /**
     * 用例输出
     */
    private List<String> outputList;
    /**
     * 执行信息
     */
    private String message;
    /**
     * 执行状态
     */
    private Integer status;
    /**
     *判题信息
     */
    private JudgeInfo judgeInfo;
}
