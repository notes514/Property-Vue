package com.guet.property.common.api;

/**
 * @author dhxstart
 * @date 2021/10/28 20:25
 */
public interface IErrorCode {

    /**
     * 返回状态码
     * @return long
     */
    long getCode();

    /**
     * 返回信息
     * @return String
     */
    String getMessage();
}
