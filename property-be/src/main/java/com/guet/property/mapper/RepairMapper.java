package com.guet.property.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guet.property.entity.Repair;

import java.util.List;

/**
 * <p>
 * 维修表 Mapper 接口
 * </p>
 *
 * @author guidian
 * @since 2021-12-11
 */
public interface RepairMapper extends BaseMapper<Repair> {

    /**
     * 报修列表
     *
     * @param jsonObject json参数
     * @return List<JSONObject>
     */
    List<JSONObject> listRepair(JSONObject jsonObject);

    /**
     * 根据报修标题和报修状态进行模糊搜索
     *
     * @param jsonObject json参数
     * @return List<JSONObject>
     */
    List<JSONObject> likeRepairTitleAndStatus(JSONObject jsonObject);
}
