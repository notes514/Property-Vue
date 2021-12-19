package com.guet.property.entity;

import com.baomidou.mybatisplus.annotation.*;
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
 * @since 2021-12-19
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
     * 所属楼栋
     */
    private Integer houseId;

    /**
     * 业主姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idcard;

    /**
     * 出生日期
     */
    private LocalDateTime birthday;

    /**
     * 职业
     */
    private String profession;

    /**
     * 性别:0-男（默认），1-女
     */
    private String sex;

    /**
     * 类型:0-房主（默认），1-租客
     */
    private String type;

    /**
     * 成员照片
     */
    private String picture;

    /**
     * 联系方式
     */
    private String telephone;

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
