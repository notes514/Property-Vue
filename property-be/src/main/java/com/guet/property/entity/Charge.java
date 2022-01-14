package com.guet.property.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 收费项目表实体
 *
 * @author dhxstart
 * @date 2022/1/14 12:41
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_charge")
public class Charge implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收费项目ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 项目名称
     */
    private String chargeName;

    /**
     * 收费金额
     */
    private BigDecimal chargeStandard;

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
