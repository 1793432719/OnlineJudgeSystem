package com.niupi.niuojcodesandbox.security;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author jillion
 * @create com.niupi.niuojcodesandbox.security - the name of the target package where the new class or interface will
 * be created. niuoj-code-sandbox - the name of the current project. null.java - the name of the PHP file that will be created. TestSecurityManager - the name of the new file which you specify in the New File dialog box during thefile creation. Tom - the login name of the current user. 2024/1/8 - the current system date. 17:06 - the current system time. 2024 - the current year. 01 - the current month. 08 - the current day of the month. 17 - the current hour. 06 - the current minute. IntelliJ IDEA - the name of the IDE in which the file will be created. 1月 - the first 3 letters of the month name. Example: Jan, Feb, etc. 一月 - full name of a month. Example: January, February, etc.
 */
public class TestSecurityManager {
    public static void main(String[] args) {
        System.setSecurityManager(new MySecurityManager());
        List<String> strings = FileUtil.readLines("E:\\OJ\\niuoj-code-sandbox\\src\\main\\resources\\application.yml", StandardCharsets.UTF_8);
        System.out.println(strings);

    }
}
