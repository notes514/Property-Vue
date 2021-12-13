package com.guet.property.service;

import com.guet.property.dto.session.SessionUserInfo;

/**
 * 此服务接口用于实现针对于Token的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
public interface TokenService {

    /**
     * 用户登录验证通过后(sso/帐密),生成token,记录用户已登录的状态
     *
     * @param username 用户名
     * @return String
     */
    String generateToken(String username);

    /**
     * 获取用户信息
     *
     * @return SessionUserInfo
     */
    SessionUserInfo getUserInfo();

    /**
     * 退出登录时,将token置为无效
     */
    void invalidateToken();
}
