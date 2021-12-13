package com.guet.property.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 访问此接口需要的权限
 *
 * @author dhxstart
 * @date 2021/12/7 22:25
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermissions {
    String[] value();

    Logical logical() default Logical.AND;
}