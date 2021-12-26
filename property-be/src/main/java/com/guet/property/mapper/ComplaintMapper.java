package com.guet.property.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guet.property.entity.Complaint;

import java.util.List;

/**
 * <p>
 * 投诉表 Mapper 接口
 * </p>
 *
 * @author guidian
 * @since 2021-12-11
 */
public interface ComplaintMapper extends BaseMapper<Complaint> {

    /**
     * 投诉列表
     *
     * @param jsonObject json参数
     * @return List<JSONObject>
     */
    List<JSONObject> listComplaint(JSONObject jsonObject);

    /**
     * 根据投诉标题和投诉状态进行模糊搜索
     *
     * @param jsonObject json参数
     * @return List<JSONObject>
     */
    List<JSONObject> likeComplaintTitleAndStatus(JSONObject jsonObject);
}
