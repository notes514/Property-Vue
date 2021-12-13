package com.guet.property.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 此服务接口用于实现针对于用户登录的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
public interface LoginService {

    /**
     * 登录提交表单
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject authLogin(JSONObject jsonObject);

    /**
     * 查询当前登录用户的权限等信息
     *
     * @return JSONObject
     */
    JSONObject getInfo();

    /**
     * 退出登录
     *
     * @return JSONObject
     */
    JSONObject logout();
}
