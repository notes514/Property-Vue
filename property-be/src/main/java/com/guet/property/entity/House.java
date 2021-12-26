package com.guet.property.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 房屋表
 * </p>
 *
 * @author dhxstart
 * @since 2021-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_house")
public class House implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 房屋ID
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
     * 所属单元ID
     */
    private Integer unitId;

    /**
     * 所属单元名称
     */
    private String unitName;

    /**
     * 房产编码
     */
    private String code;

    /**
     * 房产名称
     */
    private String name;

    /**
     * 所属（业主）ID
     */
    private Integer ownerId;

    /**
     * 所属户主（业主）名称
     */
    private String ownerName;

    /**
     * 面积
     */
    private BigDecimal area;

    /**
     * 房间数
     */
    private Integer roomNum;

    /**
     * 楼层
     */
    private Integer floor;

    /**
     * 联系方式
     */
    private Integer telephone;

    /**
     * 房屋状态：0-未销售（默认），1-已入住
     */
    private String status;

    /**
     * 房屋状态：0-未销售（默认），1-已入住
     */
    private String refinedDecoration;

    /**
     * 房屋描述
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
     * 入住时间
     */
    private LocalDateTime liveTime;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    private LocalDateTime gmtModified;
}
