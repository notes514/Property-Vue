package com.guet.property.dto.session;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * 保存在session中的用户信息
 *
 * @author dhxstart
 * @date 2021/12/9 19:38
 */
@Data
public class SessionUserInfo {
    /** 用户ID */
    private int userId;
    /** 用户名 */
    private String username;
    /** 用户昵称 */
    private String nickname;
    /** 角色ID列表 */
    private List<Integer> roleIds;
    /** 菜单列表 */
    private Set<String> menuList;
    /** 权限列表 */
    private Set<String> permissionList;
}
