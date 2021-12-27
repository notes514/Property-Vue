package com.guet.property.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 供求表
 * </p>
 *
 * @author dhxstart
 * @since 2021-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_supply")
public class Supply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 供求ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 供求人员（业主）ID
     */
    private Integer ownerId;

    /**
     * 供求人员（业主）名称
     */
    private String ownerName;

    /**
     * 供求标题
     */
    private String title;

    /**
     * 供求地点
     */
    private String address;

    /**
     * 状态:0-供求未开始（默认），1-供求进行中，2-供求已结束
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
     * 供求开始时间
     */
    private LocalDateTime startTime;

    /**
     * 供求截止时间
     */
    private LocalDateTime endTime;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    private LocalDateTime gmtModified;
}
