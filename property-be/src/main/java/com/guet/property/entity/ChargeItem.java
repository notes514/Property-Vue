package com.guet.property.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 收费项目表
 * </p>
 *
 * @author dhxstart
 * @since 2021-12-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_charge_item")
public class ChargeItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收费项目ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 收费编号
     */
    private String code;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 所属楼栋ID
     */
    private Integer buildingId;

    /**
     * 所属楼栋名称
     */
    private String buildingName;

    /**
     * 项目收费金额（年），单位分
     */
    private BigDecimal money;

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
