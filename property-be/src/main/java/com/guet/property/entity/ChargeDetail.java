package com.guet.property.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 收费名细表
 * </p>
 *
 * @author dhxstart
 * @since 2021-12-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_charge_detail")
public class ChargeDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收费明细ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 所属楼栋ID
     */
    private Integer buildingId;

    /**
     * 所属楼栋名称
     */
    private String buildingName;

    /**
     * 收费项目ID
     */
    private Integer chargeItemId;

    /**
     * 收费项目名称
     */
    private String chargeItemName;

    /**
     * 承办人名称
     */
    private String contractor;

    /**
     * 承办人联系电话
     */
    private String telephone;

    /**
     * 应收金额(￥)，单位分
     */
    private BigDecimal payMoney;

    /**
     * 实收金额(￥)，单位分
     */
    private BigDecimal actualMoney;

    /**
     * 缴费时间
     */
    private LocalDateTime payTime;

    /**
     * 状态：0-生效中（默认），1-已过期
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
