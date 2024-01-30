package com.niupi.niuojcodesandbox;

import com.niupi.niuojcodesandbox.model.ExecuteCodeRequest;
import com.niupi.niuojcodesandbox.model.ExecuteCodeResponse;
import org.springframework.stereotype.Component;

/**
 * Java 原生代码沙箱实现（直接继承模板方法，这样也方便我们对方法进行自定义代码修改）
 */
@Component
public class JavaNativeCodeSandbox extends JavaCodeSandboxTemplate {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        return super.executeCode(executeCodeRequest);
    }
}
