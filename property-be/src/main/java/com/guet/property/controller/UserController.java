package com.guet.property.controller;

import com.alibaba.fastjson.JSONObject;
import com.guet.property.common.annotation.Logical;
import com.guet.property.common.annotation.RequiresPermissions;
import com.guet.property.service.UserService;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户/角色/权限相关 前端控制器
 *
 * @author dhxstart
 * @date 2021/12/11 18:28
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查询用户列表
     */
    @RequiresPermissions("user:list")
    @GetMapping("/list")
    public JSONObject listUser(HttpServletRequest request) {
        return userService.listUser(CommonUtils.request2Json(request));
    }

    @RequiresPermissions("user:add")
    @PostMapping("/addUser")
    public JSONObject addUser(@RequestBody JSONObject requestJson) {
        CommonUtils.hasAllRequired(requestJson, "username, password, nickname, roleIds");
        return userService.addUser(requestJson);
    }

    @RequiresPermissions("user:update")
    @PostMapping("/updateUser")
    public JSONObject updateUser(@RequestBody JSONObject requestJson) {
        CommonUtils.hasAllRequired(requestJson, " nickname, roleIds, deleteStatus, userId");
        return userService.updateUser(requestJson);
    }

    @RequiresPermissions(value = {"user:add", "user:update"}, logical = Logical.OR)
    @GetMapping("/getAllRoles")
    public JSONObject getAllRoles() {
        return userService.getAllRoles();
    }

    /**
     * 角色列表
     */
    @RequiresPermissions("role:list")
    @GetMapping("/listRole")
    public JSONObject listRole() {
        return userService.listRole();
    }

    /**
     * 查询所有权限, 给角色分配权限时调用
     */
    @RequiresPermissions("role:list")
    @GetMapping("/listAllPermission")
    public JSONObject listAllPermission() {
        return userService.listAllPermission();
    }

    /**
     * 新增角色
     */
    @RequiresPermissions("role:add")
    @PostMapping("/addRole")
    public JSONObject addRole(@RequestBody JSONObject requestJson) {
        CommonUtils.hasAllRequired(requestJson, "roleName,permissions");
        return userService.addRole(requestJson);
    }

    /**
     * 修改角色
     */
    @RequiresPermissions("role:update")
    @PostMapping("/updateRole")
    public JSONObject updateRole(@RequestBody JSONObject requestJson) {
        CommonUtils.hasAllRequired(requestJson, "roleId,roleName,permissions");
        return userService.updateRole(requestJson);
    }

    /**
     * 删除角色
     */
    @RequiresPermissions("role:delete")
    @PostMapping("/deleteRole")
    public JSONObject deleteRole(@RequestBody JSONObject requestJson) {
        CommonUtils.hasAllRequired(requestJson, "roleId");
        return userService.deleteRole(requestJson);
    }
}
