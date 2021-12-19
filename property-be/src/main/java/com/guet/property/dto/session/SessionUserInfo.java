package com.guet.property.dto.session;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * 保存在session中的用户信息
 * //tb_activity,tb_article,tb_building,tb_charge_detail,tb_charge_item,tb_complaint,tb_device,tb_house,tb_letter,tb_notice,tb_owner,tb_repair,tb_supply_demand
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
