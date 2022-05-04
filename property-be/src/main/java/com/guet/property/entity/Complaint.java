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
 * @since 2021-12-26
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
     * 投诉人员（业主）ID
     */
    private Integer ownerId;

    /**
     * 投诉人员（业主）ID
     */
    private String ownerName;

    /**
     * 投诉类型
     */
    private String complaintType;

    /**
     * 投诉内容
     */
    private String complaintContent;

    /**
     * 处理状态：0-待受理，1-受理中，2-受理完毕
     */
    private String status;

    /**
     * 处理人
     */
    private String handler;

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
