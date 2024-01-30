package com.niupi.niuojbackendjudgeservice.judge.codesandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import com.niupi.niuojbackendcommon.common.ErrorCode;
import com.niupi.niuojbackendcommon.exception.BusinessException;
import com.niupi.niuojbackendjudgeservice.judge.codesandbox.CodeSandBox;
import com.niupi.niuojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.niupi.niuojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * 远程代码沙箱(实际调用接口的沙箱)
 */
public class RemoteCodeSandBox implements CodeSandBox {
    public static final String AUTH_REQUEST_HEADER = "auth";
    public static final String AUTH_REQUEST_SECRET = "secretKey";

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        String json = JSONUtil.toJsonStr(executeCodeRequest);
//        String url = "http://localhost:8123/executeCode";
        String url = "http://81.70.42.129:8123/executeCode";
        String responseStr = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER,AUTH_REQUEST_SECRET)
                .body(json)
                .execute()
                .body();
        if (StringUtils.isBlank(responseStr)) {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "executeCode remoteSandbox error,message = {}" + responseStr);
        }
        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
    }
}
