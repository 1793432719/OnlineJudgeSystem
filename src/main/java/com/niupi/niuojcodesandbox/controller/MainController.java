package com.niupi.niuojcodesandbox.controller;

import com.niupi.niuojcodesandbox.JavaNativeCodeSandbox;
import com.niupi.niuojcodesandbox.model.ExecuteCodeRequest;
import com.niupi.niuojcodesandbox.model.ExecuteCodeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jillion
 * @create com.niupi.niuojcodesandbox.controller - the name of the target package where the new class or interface will
 * be created. niuoj-code-sandbox - the name of the current project. null.java - the name of the PHP file that will be created. MainController - the name of the new file which you specify in the New File dialog box during thefile creation. Tom - the login name of the current user. 2024/1/6 - the current system date. 10:44 - the current system time. 2024 - the current year. 01 - the current month. 06 - the current day of the month. 10 - the current hour. 44 - the current minute. IntelliJ IDEA - the name of the IDE in which the file will be created. 1月 - the first 3 letters of the month name. Example: Jan, Feb, etc. 一月 - full name of a month. Example: January, February, etc.
 */
@RestController("/")
public class MainController {
    //    定义鉴权请求头和秘钥来保证沙箱安全性
    public static final String AUTH_REQUEST_HEADER = "auth";
    public static final String AUTH_REQUEST_SECRET = "secretKey";

    @Resource
    private JavaNativeCodeSandbox javaNativeCodeSandbox;


    @GetMapping("/health")
    public String healthCheck() {
        return "ok";
    }

    /**
     * 执行代码API
     *
     * @param executeCodeRequest
     * @return
     */
    @PostMapping("/executeCode")
    ExecuteCodeResponse executeCode(@RequestBody ExecuteCodeRequest executeCodeRequest, HttpServletRequest request, HttpServletResponse response) {
//       进行一个基本的认证
        String authHeader = request.getHeader(AUTH_REQUEST_HEADER);
        if (!authHeader.equals(AUTH_REQUEST_SECRET)) {
            response.setStatus(403);
            return null;
        }
        if (executeCodeRequest == null) {
            throw new RuntimeException("请求参数为空");
        }

        return javaNativeCodeSandbox.executeCode(executeCodeRequest);

    }
}
