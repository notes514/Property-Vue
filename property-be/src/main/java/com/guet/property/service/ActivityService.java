package com.guet.property.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.property.entity.Activity;

/**
 * 此服务接口用于实现针对于tb_activity表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
public interface ActivityService extends IService<Activity> {

    /**
     * 获取活动列表
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject listActivity(JSONObject jsonObject);

    /**
     * 根据活动内容和活动状态进行模糊搜索
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject likeActivityTitleAndStatus(JSONObject jsonObject);

    /**
     * 添加活动
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject addActivity(JSONObject jsonObject);

    /**
     * 更新活动
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject updateActivity(JSONObject jsonObject);

    /**
     * 删除活动
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject deleteActivity(JSONObject jsonObject);
}
