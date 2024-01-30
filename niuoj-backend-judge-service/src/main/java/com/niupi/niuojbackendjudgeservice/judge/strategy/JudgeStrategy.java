package com.niupi.niuojbackendjudgeservice.judge.strategy;


import com.niupi.niuojbackendmodel.model.codesandbox.JudgeInfo;

/**
 * @author jillion
 * @create com.niupi.niuoj.judge.strategy - the name of the target package where the new class or interface will
 * be created. niuoj - the name of the current project. null.java - the name of the PHP file that will be created. JudgeStrategy - the name of the new file which you specify in the New File dialog box during thefile creation. Tom - the login name of the current user. 2024/1/5 - the current system date. 16:28 - the current system time. 2024 - the current year. 01 - the current month. 05 - the current day of the month. 16 - the current hour. 28 - the current minute. IntelliJ IDEA - the name of the IDE in which the file will be created. 1月 - the first 3 letters of the month name. Example: Jan, Feb, etc. 一月 - full name of a month. Example: January, February, etc.
 */
public interface JudgeStrategy {
    JudgeInfo doJudge(JudgeContext judgeContext);
}
