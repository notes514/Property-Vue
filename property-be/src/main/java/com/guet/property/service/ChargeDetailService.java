package com.guet.property.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.property.entity.ChargeDetail;

/**
 * 此服务接口用于实现针对于tb_charge_detail表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
public interface ChargeDetailService extends IService<ChargeDetail> {

    /**
     * 获取收费项目明细列表
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject listChargeDetail(JSONObject jsonObject);

    /**
     * 此方法用于针对收费项目明细名称和缴费状态进行模糊查询
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject likeChargeDetailNameAndType(JSONObject jsonObject);

    /**
     * 添加收费项目明细
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject addChargeDetail(JSONObject jsonObject);

    /**
     * 更新收费项目明细
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject updateChargeDetail(JSONObject jsonObject);

    /**
     * 删除收费项目明细
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject deleteChargeDetail(JSONObject jsonObject);
}
