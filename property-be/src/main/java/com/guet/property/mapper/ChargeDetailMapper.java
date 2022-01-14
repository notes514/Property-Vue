package com.guet.property.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guet.property.entity.ChargeDetail;

import java.util.List;

/**
 * <p>
 * 收费名细表 Mapper 接口
 * </p>
 *
 * @author guidian
 * @since 2021-12-11
 */
public interface ChargeDetailMapper extends BaseMapper<ChargeDetail> {

    /**
     * 从方法用于获取收费项目明细列表（带有分页）
     *
     * @param jsonObject json参数
     * @return List<JSONObject>
     */
    List<JSONObject> listChargeDetail(JSONObject jsonObject);

    /**
     * 此方法用于对收费项目明细名称和缴费状态进行模糊查询（带有分页）
     *
     * @param jsonObject json参数
     * @return List<JSONObject>
     */
    List<JSONObject> likeChargeDetailNameAndType(JSONObject jsonObject);
}
