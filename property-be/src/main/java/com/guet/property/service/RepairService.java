package com.guet.property.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.property.entity.Repair;

/**
 * 此服务接口用于实现针对于tb_repair表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
public interface RepairService extends IService<Repair> {

    /**
     * 获取报修列表
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject listRepair(JSONObject jsonObject);

    /**
     * 根据报修标题和报修状态进行模糊搜索
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject likeRepairTitleAndStatus(JSONObject jsonObject);

    /**
     * 添加报修信息
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject addRepair(JSONObject jsonObject);

    /**
     * 更新报修信息
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject updateRepair(JSONObject jsonObject);

    /**
     * 删除报修信息
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject deleteRepair(JSONObject jsonObject);
}
