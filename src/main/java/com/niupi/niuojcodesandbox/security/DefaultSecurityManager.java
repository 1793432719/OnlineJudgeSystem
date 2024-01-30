package com.niupi.niuojcodesandbox.security;

import java.security.Permission;

/**
 * 安全管理器
 */
public class DefaultSecurityManager extends SecurityManager{
    //检查所有的权限的方法
    @Override
    public void checkPermission(Permission perm) {
//        super.checkPermission(perm);
    }

}
