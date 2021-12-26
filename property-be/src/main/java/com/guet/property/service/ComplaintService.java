package com.guet.property.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.property.entity.Complaint;

/**
 * 此服务接口用于实现针对于tb_complaint表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
public interface ComplaintService extends IService<Complaint> {

    /**
     * 获取投诉列表
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject listComplaint(JSONObject jsonObject);

    /**
     * 根据投诉标题和投诉状态进行模糊搜索
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject likeComplaintTitleAndStatus(JSONObject jsonObject);

    /**
     * 添加投诉信息
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject addComplaint(JSONObject jsonObject);

    /**
     * 更新投诉信息
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject updateComplaint(JSONObject jsonObject);

    /**
     * 删除投诉
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject deleteComplaint(JSONObject jsonObject);
}
