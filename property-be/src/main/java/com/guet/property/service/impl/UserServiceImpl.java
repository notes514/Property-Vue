package com.guet.property.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.guet.property.mapper.UserMapper;
import com.guet.property.service.UserService;
import com.guet.property.util.CommonUtils;
import com.guet.property.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 此服务类用于实现针对于用户/角色/权限表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public JSONObject listUser(JSONObject jsonObject) {
        CommonUtils.fillPageParam(jsonObject);
        int count = userMapper.countUser(jsonObject);
        List<JSONObject> list = userMapper.listUser(jsonObject);
        return CommonUtils.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject addUser(JSONObject jsonObject) {
        int exist = userMapper.queryExistUsername(jsonObject);
        if (exist > 0) {
            return CommonUtils.errorJson(ErrorEnum.E_10009);
        }
        userMapper.addUser(jsonObject);
        userMapper.batchAddUserRole(jsonObject);
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject getAllRoles() {
        List<JSONObject> roles = userMapper.getAllRoles();
        return CommonUtils.successPage(roles);
    }

    @Override
    public JSONObject updateUser(JSONObject jsonObject) {
        // 不允许修改管理员信息
        if (jsonObject.getIntValue("userId") == 10001) {
            return CommonUtils.successJson();
        }
        userMapper.updateUser(jsonObject);
        userMapper.removeUserAllRole(jsonObject.getIntValue("userId"));
        if (!jsonObject.getJSONArray("roleIds").isEmpty()) {
            userMapper.batchAddUserRole(jsonObject);
        }
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject listRole() {
        List<JSONObject> roles = userMapper.listRole();
        return CommonUtils.successPage(roles);
    }

    @Override
    public JSONObject listAllPermission() {
        List<JSONObject> permissions = userMapper.listAllPermission();
        return CommonUtils.successPage(permissions);
    }

    @Override
    public JSONObject addRole(JSONObject jsonObject) {
        userMapper.insertRole(jsonObject);
        userMapper.insertRolePermission(jsonObject.getString("roleId"), (List<Integer>) jsonObject.get("permissions"));
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject updateRole(JSONObject jsonObject) {
        String roleId = jsonObject.getString("roleId");
        List<Integer> newPerms = (List<Integer>) jsonObject.get("permissions");
        JSONObject roleInfo = userMapper.getRoleAllInfo(jsonObject);
        Set<Integer> oldPerms = (Set<Integer>) roleInfo.get("permissionIds");
        // 修改角色名称
        dealRoleName(jsonObject, roleInfo);
        // 添加新权限
        saveNewPermission(roleId, newPerms, oldPerms);
        // 移除旧的不再拥有的权限
        removeOldPermission(roleId, newPerms, oldPerms);
        return CommonUtils.successJson();
    }

    /**
     * 修改角色名称
     *
     * @param paramJson json参数
     * @param roleInfo  角色信息
     */
    private void dealRoleName(JSONObject paramJson, JSONObject roleInfo) {
        String roleName = paramJson.getString("roleName");
        if (!roleName.equals(roleInfo.getString("roleName"))) {
            userMapper.updateRoleName(paramJson);
        }
    }

    /**
     * 为角色添加新权限
     *
     * @param roleId   角色ID
     * @param newPerms 新的参数列表
     * @param oldPerms 旧的参数列表
     */
    private void saveNewPermission(String roleId, Collection<Integer> newPerms, Collection<Integer> oldPerms) {
        List<Integer> waitInsert = new ArrayList<>();
        for (Integer newPerm : newPerms) {
            if (!oldPerms.contains(newPerm)) {
                waitInsert.add(newPerm);
            }
        }
        if (!waitInsert.isEmpty()) {
            userMapper.insertRolePermission(roleId, waitInsert);
        }
    }

    /**
     * 删除角色 旧的 不再拥有的权限
     *
     * @param roleId   角色ID
     * @param newPerms 新的参数列表
     * @param oldPerms 旧的参数列表
     */
    private void removeOldPermission(String roleId, Collection<Integer> newPerms, Collection<Integer> oldPerms) {
        List<Integer> waitRemove = new ArrayList<>();
        for (Integer oldPerm : oldPerms) {
            if (!newPerms.contains(oldPerm)) {
                waitRemove.add(oldPerm);
            }
        }
        if (!waitRemove.isEmpty()) {
            userMapper.removeOldPermission(roleId, waitRemove);
        }
    }

    @Override
    public JSONObject deleteRole(JSONObject jsonObject) {
        JSONObject roleInfo = userMapper.getRoleAllInfo(jsonObject);
        List<JSONObject> users = (List<JSONObject>) roleInfo.get("users");
        if (users != null && !users.isEmpty()) {
            return CommonUtils.errorJson(ErrorEnum.E_10008);
        }
        userMapper.removeRole(jsonObject);
        userMapper.removeRoleAllPermission(jsonObject);
        return CommonUtils.successJson();
    }
}
