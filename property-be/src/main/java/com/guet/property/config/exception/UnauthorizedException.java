package com.guet.property.config.exception;

/**
 * 此类用于访问权限不足的接口、数据所报出的异常
 *
 * @author dhxstart
 * @date 2021/12/7 23:26
 */
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("用户无此接口权限！");
    }
}
