package com.guet.property.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 业主表
 * </p>
 *
 * @author dhxstart
 * @since 2021-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_owner")
public class Owner implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 业主ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 所属楼栋ID
     */
    private Integer buildingId;

    /**
     * 所属楼栋名称
     */
    private String buildingName;

    /**
     * 业主姓名
     */
    private String name;

    /**
     * 业主登录名
     */
    private String loginName;

    /**
     * 业主登录密码
     */
    private String password;

    /**
     * 性别:0-男（默认），1-女
     */
    private String sex;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime birthday;

    /**
     * 联系方式
     */
    private String telephone;

    /**
     * 职业
     */
    private String profession;

    /**
     * 工作单位
     */
    private String workUnits;

    /**
     * 成员照片
     */
    private String picture;

    /**
     * 类型:0-房主（默认），1-租客
     */
    private String type;

    /**
     * 备注（默认无）
     */
    private String remark;

    /**
     * 逻辑删除:0-未删除（默认），1-已删除
     */
    @TableLogic
    private String deleted;

    /**
     * 乐观锁
     */
    @Version
    private Integer version;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    private LocalDateTime gmtModified;
}
