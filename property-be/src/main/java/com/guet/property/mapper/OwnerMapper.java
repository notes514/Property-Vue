package com.guet.property.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guet.property.entity.Owner;

import java.util.List;

/**
 * <p>
 * 业主表 Mapper 接口
 * </p>
 *
 * @author guidian
 * @since 2021-12-11
 */
public interface OwnerMapper extends BaseMapper<Owner> {

    /**
     * 业主列表
     *
     * @param jsonObject json参数
     * @return List<JSONObject>
     */
    List<JSONObject> listOwner(JSONObject jsonObject);

    /**
     * 楼栋和类型搜索
     *
     * @param jsonObject json参数
     * @return List<JSONObject>
     */
    List<JSONObject> likeOwnerNameAndType(JSONObject jsonObject);
}
