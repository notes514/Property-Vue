package com.guet.property.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 信件表
 * </p>
 *
 * @author dhxstart
 * @since 2021-12-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_letter")
public class Letter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 信件ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 信件发送者（业主）ID
     */
    private Integer ownerId;

    /**
     * 信件发送者（业主）名称
     */
    private String ownerName;

    /**
     * 信件来源：0-信箱（默认），1-邮件，2-微信，3-公众号，4-app,5-pc
     */
    private String origin;

    /**
     * 信件标题
     */
    private String title;

    /**
     * 信箱内容
     */
    private String content;

    /**
     * 状态:0-未读（默认），1-已读
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
