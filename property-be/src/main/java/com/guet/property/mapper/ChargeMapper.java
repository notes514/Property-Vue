package com.guet.property.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guet.property.entity.Charge;

import java.util.List;

/**
 * 收费项目表 Mapper 接口
 *
 * @author dhxstart
 * @date 2022/1/14 12:40
 */
public interface ChargeMapper extends BaseMapper<Charge> {

    /**
     * 从方法用于获取收费项目列表（带有分页）
     *
     * @param jsonObject json参数
     * @return List<JSONObject>
     */
    List<JSONObject> listCharge(JSONObject jsonObject);

    /**
     * 此方法用于对收费项目名称进行模糊查询（带有分页）
     *
     * @param jsonObject json参数
     * @return List<JSONObject>
     */
    List<JSONObject> likeChargeName(JSONObject jsonObject);
}
