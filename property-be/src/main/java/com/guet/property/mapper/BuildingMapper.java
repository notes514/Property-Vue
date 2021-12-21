package com.guet.property.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guet.property.entity.Building;

import java.util.List;

/**
 * <p>
 * 楼栋表 Mapper 接口
 * </p>
 *
 * @author guidian
 * @since 2021-12-11
 */
public interface BuildingMapper extends BaseMapper<Building> {

    /**
     * 楼栋列表
     *
     * @param jsonObject json参数
     * @return List<JSONObject>
     */
    List<JSONObject> listBuilding(JSONObject jsonObject);

    /**
     * 楼栋和类型搜索
     *
     * @param jsonObject json参数
     * @return List<JSONObject>
     */
    List<JSONObject> likeBuildingNameAndType(JSONObject jsonObject);
}
