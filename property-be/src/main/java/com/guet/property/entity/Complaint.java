package com.guet.property.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 投诉表
 * </p>
 *
 * @author dhxstart
 * @since 2021-12-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_complaint")
public class Complaint implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 投诉ID
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
     * 投诉人员（业主）ID
     */
    private Integer ownerId;

    /**
     * 投诉人员（业主）名称
     */
    private String ownerName;

    /**
     * 投诉具体描述
     */
    private String description;

    /**
     * 投诉事由
     */
    private String reason;

    /**
     * 状态：0-未受理（默认），1-已受理，2-已处理完毕
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
