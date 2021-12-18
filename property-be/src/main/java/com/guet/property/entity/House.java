package com.guet.property.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * 房屋表
 *
 * @author dhxstart
 * @date 2021/12/11 18:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_house")
public class House {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 房屋编码
     */
    private String code;

    /**
     * 房屋名称
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
     * 户主（业主）ID
     */
    private Integer ownerId;

    /**
     * 户主（业主）名称
     */
    private String ownerName;

    /**
     * 面积
     */
    private Integer area;

    /**
     * 房间数
     */
    private Integer roomNum;

    /**
     * 单元
     */
    private Integer unit;

    /**
     * 楼层
     */
    private Integer floor;

    /**
     * 联系方式
     */
    private Integer telephone;

    /**
     * 描述
     */
    private String description;

    /**
     * 入住时间
     */
    private LocalDateTime liveTime;
}
