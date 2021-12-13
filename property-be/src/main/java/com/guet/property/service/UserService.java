package com.guet.property.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.transaction.annotation.Transactional;

/**
 * 此服务接口用于实现针对于用户/角色/权限表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
public interface UserService {

    /**
     * 获取用户列表
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject listUser(JSONObject jsonObject);

    /**
     * 添加用户
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject addUser(JSONObject jsonObject);

    /**
     * 查询所有的角色，在添加 /修改用户的时候要使用此方法
     *
     * @return JSONObject
     */
    JSONObject getAllRoles();

    /**
     * 修改用户
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject updateUser(JSONObject jsonObject);

    /**
     * 获取角色列表
     *
     * @return JSONObject
     */
    JSONObject listRole();

    /**
     * 查询所有权限，给角色分配权限时调用
     *
     * @return JSONObject
     */
    JSONObject listAllPermission();

    /**
     * 添加角色
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    @Transactional(rollbackFor = Exception.class)
    JSONObject addRole(JSONObject jsonObject);

    /**
     * 修改角色
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    @Transactional(rollbackFor = Exception.class)
    JSONObject updateRole(JSONObject jsonObject);

    /**
     * 删除角色
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    @Transactional(rollbackFor = Exception.class)
    JSONObject deleteRole(JSONObject jsonObject);
}
