package com.guet.property.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 楼栋表
 * </p>
 *
 * @author dhxstart
 * @since 2021-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_building")
public class Building implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 楼栋ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 楼栋名
     */
    private String name;

    /**
     * 层数
     */
    private Integer layer;

    /**
     * 面积
     */
    private BigDecimal area;

    /**
     * 类型：0-楼梯房，1-电梯房（默认）
     */
    private String type;

    /**
     * 描述
     */
    private String description;

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
