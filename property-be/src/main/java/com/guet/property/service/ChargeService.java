package com.guet.property.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.property.entity.Charge;

/**
 * 此服务接口用于实现针对于tb_charge表的常用操作
 *
 * @author dhxstart
 * @date 2022/1/14 12:40
 */
public interface ChargeService extends IService<Charge> {

    /**
     * 获取收费项目列表
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject listCharge(JSONObject jsonObject);

    /**
     * 此方法用于针对收费项目名称进行模糊查询
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject likeChargeName(JSONObject jsonObject);

    /**
     * 添加收费项目
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject addCharge(JSONObject jsonObject);

    /**
     * 更新收费项目
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject updateCharge(JSONObject jsonObject);

    /**
     * 删除收费项目
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject deleteCharge(JSONObject jsonObject);
}
