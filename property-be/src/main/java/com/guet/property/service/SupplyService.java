package com.guet.property.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.property.entity.Supply;

/**
 * 此服务接口用于实现针对于tb_supply_demand表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
public interface SupplyService extends IService<Supply> {

    /**
     * 获取报修列表
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject listSupply(JSONObject jsonObject);

    /**
     * 根据供求标题和供求状态进行模糊搜索
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject likeSupplyTitleAndStatus(JSONObject jsonObject);

    /**
     * 添加供求信息
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject addSupply(JSONObject jsonObject);

    /**
     * 更新供求信息
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject updateSupply(JSONObject jsonObject);

    /**
     * 删除供求信息
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject deleteSupply(JSONObject jsonObject);
}
