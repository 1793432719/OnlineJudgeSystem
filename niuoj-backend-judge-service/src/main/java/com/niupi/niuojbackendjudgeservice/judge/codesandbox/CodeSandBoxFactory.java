package com.niupi.niuojbackendjudgeservice.judge.codesandbox;


import com.niupi.niuojbackendjudgeservice.judge.codesandbox.impl.ExampleCodeSandBox;
import com.niupi.niuojbackendjudgeservice.judge.codesandbox.impl.RemoteCodeSandBox;
import com.niupi.niuojbackendjudgeservice.judge.codesandbox.impl.ThirdPartyCodeSandBox;

/**
 * 代码沙箱工厂（静态工厂模式）（根据字符串参数创建指定的代码沙箱示例）
 */
public class CodeSandBoxFactory {
    public static CodeSandBox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandBox();
            case "remote":
                return new RemoteCodeSandBox();
            case "thirdParty":
                return new ThirdPartyCodeSandBox();
            default:
                return new ExampleCodeSandBox();
        }
    }
}
