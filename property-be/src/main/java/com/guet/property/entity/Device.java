package com.guet.property.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 设备表
 *
 * @author dhxstart
 * @date 2021/12/11 18:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_device")
public class Device {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
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
