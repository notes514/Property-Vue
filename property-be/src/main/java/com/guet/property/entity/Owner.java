package com.guet.property.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 业主表
 *
 * @author dhxstart
 * @date 2021/12/11 18:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_owner")
public class Owner {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
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
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
