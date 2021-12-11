package com.guet.property.util.model;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Set;

/**
 * 基于mybatis一对多的json返回对象
 * <p>
 *     处理嵌套查询结果时，mybatis会根据bean定义的属性类型来初始化嵌套的成员变量，
 *     主要看其是不是Collection，如果这里不定义，那么嵌套返回结果里就只能返回一对一的结果，而不是一对多的
 * </p>
 * @author dhxstart
 * @date 2021/12/7 22:35
 */
public class One2Many extends JSONObject {
    /** 角色不重复集合 */
    private Set<String> roleList;
    /** 菜单不重复集合 */
    private Set<String> menuList;
    /** 权限不重复集合 */
    private Set<String> permissionList;
    /** 权限ID不重复集合 */
    private Set<Integer> permissionIds;
    /** 图片集合 */
    private List<JSONObject> picList;
    /** 菜单集合 */
    private List<JSONObject> menus;
    /** 角色列表 */
    private List<JSONObject> users;
    /** 权限集合 */
    private List<JSONObject> permissions;
    /** 角色集合 */
    private List<JSONObject> roles;
}
