package com.guet.property.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.property.entity.House;

/**
 * 此服务类用于实现针对于tb_house表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
public interface HouseService extends IService<House> {

    /**
     * 获取房屋列表信息
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject listHouse(JSONObject jsonObject);

    /**
     * 根据房屋名称和房屋状态进行模糊搜索
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject likeHouseNameAndType(JSONObject jsonObject);

    /**
     * 添加房屋
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject addHouse(JSONObject jsonObject);

    /**
     * 更新房屋
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject updateHouse(JSONObject jsonObject);

    /**
     * 删除房屋
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject deleteHouse(JSONObject jsonObject);

    /**
     * 房屋统计
     *
     * @return JSONObject
     */
    JSONObject getHouseCount();
}
