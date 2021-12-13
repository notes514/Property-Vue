package com.guet.property.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author dhxstart
 * @date 2021/12/11 17:16
 */
public interface UserMapper {
    /**
     * 查询用户数量
     *
     * @param jsonObject jsonObject
     * @return int 数量
     */
    int countUser(JSONObject jsonObject);

    /**
     * 查询用户列表
     *
     * @param jsonObject jsonObject
     * @return List<JSONObject>
     */
    List<JSONObject> listUser(JSONObject jsonObject);

    /**
     * 查询所有角色，在添加/添加用户时使用此方法
     *
     * @return List<cJSONObject>
     */
    List<JSONObject> getAllRoles();

    /**
     * 查询用户名是否已存在
     *
     * @param jsonObject jsonObject
     * @return 返回0表示失败，反之亦然
     */
    int queryExistUsername(JSONObject jsonObject);

    /**
     * 新增用户
     *
     * @param jsonObject jsonObject
     */
    void addUser(JSONObject jsonObject);

    /**
     * 修改用户
     *
     * @param jsonObject jsonObject
     */
    void updateUser(JSONObject jsonObject);

    /**
     * 批量添加用户角色
     *
     * @param jsonObject jsonObject
     */
    void batchAddUserRole(JSONObject jsonObject);

    /**
     * 删除用户所有角色
     *
     * @param userId jsonObject
     */
    void removeUserAllRole(int userId);

    /**
     * 获取角色列表
     *
     * @return List<JSONObject>
     */
    List<JSONObject> listRole();

    /**
     * 查询所有权限, 给角色分配权限时调用
     */
    List<JSONObject> listAllPermission();

    /**
     * 新增角色
     *
     * @param jsonObject jsonObject
     */
    void insertRole(JSONObject jsonObject);

    /**
     * 批量插入角色的权限
     *
     * @param roleId      角色ID
     * @param permissions 权限
     */
    void insertRolePermission(@Param("roleId") String roleId, @Param("permissions") List<Integer> permissions);

    /**
     * 将角色曾经拥有而修改为不再拥有的权限 delete_status改为'2'
     *
     * @param roleId 角色ID
     * @param permissions 权限
     */
    void removeOldPermission(@Param("roleId") String roleId, @Param("permissions") List<Integer> permissions);

    /**
     * 修改角色名称
     *
     * @param jsonObject jsonObject
     */
    void updateRoleName(JSONObject jsonObject);

    /**
     * 查询某角色的全部数据，在删除和修改角色时调用
     *
     * @param jsonObject jsonObject
     */
    JSONObject getRoleAllInfo(JSONObject jsonObject);

    /**
     * 删除角色
     *
     * @param jsonObject jsonObject
     */
    void removeRole(JSONObject jsonObject);

    /**
     * 删除本角色全部权限
     *
     * @param jsonObject jsonObject
     */
    void removeRoleAllPermission(JSONObject jsonObject);
}
