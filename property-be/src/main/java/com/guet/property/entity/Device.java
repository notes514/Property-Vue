package com.guet.property.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 设备表
 * </p>
 *
 * @author dhxstart
 * @since 2021-12-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_device")
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 设备编号
     */
    private String code;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 设备品牌
     */
    private String brand;

    /**
     * 购买单价(￥)，单位分
     */
    private BigDecimal unitPrice;

    /**
     * 购买数量
     */
    private Integer num;

    /**
     * 预计使用年限(年)
     */
    private Integer expectedUsefulLife;

    /**
     * 购买日期
     */
    private LocalDateTime purchaseDate;

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
