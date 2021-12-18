package com.guet.property.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.guet.property.common.exception.CommonJsonException;
import com.guet.property.dto.session.SessionUserInfo;
import com.guet.property.mapper.LoginMapper;
import com.guet.property.service.TokenService;
import com.guet.property.util.StringUtils;
import com.guet.property.util.constants.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


/**
 * 此服务类用于实现针对于Token的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
@Service
@Slf4j
public class TokenServiceImpl implements TokenService {
    @Autowired
    private Cache<String, SessionUserInfo> cacheMap;
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public String generateToken(String username) {
        MDC.put("username", username);
        String token = UUID.randomUUID().toString().replace("-", "").substring(0, 20);
        //设置用户信息缓存
        setCache(token, username);
        return token;
    }

    @Override
    public SessionUserInfo getUserInfo() {
        String token = MDC.get("token");
        return getUserInfoFromCache(token);
    }

    /**
     * 根据token查询用户信息,如果token无效,则抛出未登录异常信息
     *
     * @param token token
     * @return SessionUserInfo
     */
    private SessionUserInfo getUserInfoFromCache(String token) {
        if (StringUtils.isNullOrEmpty(token)) {
            throw new CommonJsonException(ErrorEnum.E_20011);
        }
        log.debug("根据token从缓存中查询用户信息,{}", token);
        SessionUserInfo info = cacheMap.getIfPresent(token);
        if (info == null) {
            log.info("没拿到缓存 token={}", token);
            throw new CommonJsonException(ErrorEnum.E_20011);
        }
        return info;
    }

    /**
     * 设置缓存
     *
     * @param token token
     * @param username 用户名
     */
    private void setCache(String token, String username) {
        SessionUserInfo info = getUserInfoByUsername(username);
        log.info("设置用户信息缓存:token={} , username={}, info={}", token, username, info);
        cacheMap.put(token, info);
    }

    @Override
    public void invalidateToken() {
        String token = MDC.get("token");
        if (!StringUtils.isNullOrEmpty(token)) {
            cacheMap.invalidate(token);
        }
        log.debug("退出登录,清除缓存:token={}", token);
    }

    /**
     * 通过用户名获取用户信息
     *
     * @param username 用户名
     * @return SessionUserInfo
     */
    private SessionUserInfo getUserInfoByUsername(String username) {
        SessionUserInfo userInfo = loginMapper.getUserInfo(username);
        if (userInfo.getRoleIds().contains(1)) {
            //管理员,查出全部按钮和权限码
            userInfo.setMenuList(loginMapper.getAllMenu());
            userInfo.setPermissionList(loginMapper.getAllPermissionCode());
        }
        return userInfo;
    }
}
