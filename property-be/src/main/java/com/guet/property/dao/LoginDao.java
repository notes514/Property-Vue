package com.guet.property.dao;

import com.alibaba.fastjson.JSONObject;
import com.guet.property.dto.session.SessionUserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * 登录相关Dao
 *
 * @author dhxstart
 * @date 2021/12/9 8:42
 */
public interface LoginDao {

    /**
     * 根据用户名和密码查询对应的用户
     *
     * @param username 用户名
     * @param password 密码
     * @return JSONObject
     */
    JSONObject checkUser(@Param("username") String username, @Param("password") String password);

    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @return SessionUserInfo
     */
    SessionUserInfo getUserInfo(String username);

    /**
     * 获取所有菜单项
     *
     * @return Set<String>
     */
    Set<String> getAllMenu();

    /**
     * 获取所有权限编码
     *
     * @return Set<String>
     */
    Set<String> getAllPermissionCode();
}
