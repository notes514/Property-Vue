package com.guet.property.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guet.property.entity.Supply;

import java.util.List;

/**
 * <p>
 * 供求表 Mapper 接口
 * </p>
 *
 * @author dhxstart
 * @since 2021-12-27
 */
public interface SupplyMapper extends BaseMapper<Supply> {

    /**
     * 供求列表
     *
     * @param jsonObject json参数
     * @return List<JSONObject>
     */
    List<JSONObject> listSupply(JSONObject jsonObject);

    /**
     * 根据供求标题和供求状态进行模糊搜索
     *
     * @param jsonObject json参数
     * @return List<JSONObject>
     */
    List<JSONObject> likeSupplyTitleAndStatus(JSONObject jsonObject);
}
