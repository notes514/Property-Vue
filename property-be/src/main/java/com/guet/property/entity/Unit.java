package com.guet.property.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 单元表
 * </p>
 *
 * @author dhxstart
 * @since 2021-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_unit")
public class Unit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 单元ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 所属楼栋ID
     */
    private Integer ownerId;

    /**
     * 所属楼栋名称
     */
    private String buildingName;

    /**
     * 单元名
     */
    private String name;

    /**
     * 总层数
     */
    private Integer totalLayer;

    /**
     * 面积
     */
    private BigDecimal area;

    /**
     * 状态（是否是电梯房）：0-否，1-是（默认）
     */
    private String status;

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
