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
 * @since 2021-12-26
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
     * 业主ID
     */
    private Integer ownerId;

    /**
     * 缴费人员（业主）姓名
     */
    private String ownerName;

    /**
     * 收费项目ID
     */
    private Integer chargeId;

    /**
     * 收费项目名称
     */
    private String chargeName;

    /**
     * 应收金额(￥)，单位分
     */
    private BigDecimal chargeStandard;

    /**
     * 实收金额(￥)，单位分
     */
    private BigDecimal payReal;

    /**
     * 欠费金额(￥)，单位分
     */
    private BigDecimal payBalance;

    /**
     * 缴费时间
     */
    private LocalDateTime payTime;

    /**
     * 缴费状态：0-未缴费（默认），1-已缴费
     */
    private String payStatus;

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
