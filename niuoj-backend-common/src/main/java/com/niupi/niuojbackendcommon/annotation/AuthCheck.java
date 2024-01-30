package com.niupi.niuojbackendcommon.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限校验
 *
 * @author <a href="https://github.com/liniupi">程序员鱼皮</a>
 * @from <a href="https://niupi.icu">编程导航知识星球</a>
 */

/**
 * 权限校验注解，用于权限校验
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

    /**
     * 在mustRole中指定，必须是管理员还是必须是用户
     *
     * @return
     */
    String mustRole() default "";

}

