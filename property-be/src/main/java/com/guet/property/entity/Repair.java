package com.guet.property.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 维修表
 * </p>
 *
 * @author dhxstart
 * @since 2021-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_repair")
public class Repair implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 报修ID
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
     * 报修人员（业主）ID
     */
    private Integer ownerId;

    /**
     * 报修人员（业主）名称
     */
    private String ownerName;

    /**
     * 报修类型
     */
    private String repairType;

    /**
     * 报修内容
     */
    private String repairContent;

    /**
     * 处理状态：0-待受理，1-受理中，2-受理完毕
     */
    private String status;

    /**
     * 逻辑删除:0-未删除（默认），1-已删除
     */
    @TableLogic
    private String deleted;

    /**
     * 处理人
     */
    private String handler;

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
