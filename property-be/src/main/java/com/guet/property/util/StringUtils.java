package com.guet.property.util;

/**
 * 字符串工具类
 *
 * @author dhxstart
 * @date 2021/12/7 22:54
 */
public class StringUtils {

    public static boolean isNullOrEmpty(String str) {
        return str == null || "".equals(str) || "null".equals(str);
    }

    public static boolean isNullOrEmpty(Object obj) {
        return null == obj || "".equals(obj);
    }
}
