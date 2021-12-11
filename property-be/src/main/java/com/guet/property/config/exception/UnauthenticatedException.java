package com.guet.property.config.exception;

/**
 * 自定义未登录异常类
 *
 * @author dhxstart
 * @date 2021/12/7 23:18
 */
public class UnauthenticatedException extends RuntimeException {

    public UnauthenticatedException() {
        super("未登录异常!");
    }
}
