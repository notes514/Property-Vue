package com.guet.property.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guet.property.entity.House;

import java.util.List;

/**
 * <p>
 * 房屋表 Mapper 接口
 * </p>
 *
 * @author guidian
 * @since 2021-12-11
 */
public interface HouseMapper extends BaseMapper<House> {
    
    /**
     * 房屋列表
     *
     * @param jsonObject json参数
     * @return List<JSONObject>
     */
    List<JSONObject> listHouse(JSONObject jsonObject);

    /**
     * 根据房屋标题和房屋状态进行模糊搜索
     *
     * @param jsonObject json参数
     * @return List<JSONObject>
     */
    List<JSONObject> likeHouseNameAndStatus(JSONObject jsonObject);
}
