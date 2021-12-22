package com.guet.property.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guet.property.entity.Activity;

import java.util.List;

/**
 * <p>
 * 活动表 Mapper 接口
 * </p>
 *
 * @author guidian
 * @since 2021-12-11
 */
public interface ActivityMapper extends BaseMapper<Activity> {

    /**
     * 活动列表
     *
     * @param jsonObject json参数
     * @return List<JSONObject>
     */
    List<JSONObject> listActivity(JSONObject jsonObject);

    /**
     * 根据活动内容和活动状态进行模糊搜索
     *
     * @param jsonObject json参数
     * @return List<JSONObject>
     */
    List<JSONObject> likeActivityTitleAndStatus(JSONObject jsonObject);
}
