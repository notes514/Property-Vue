package com.guet.property.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.property.entity.Complaint;
import com.guet.property.mapper.ComplaintMapper;
import com.guet.property.service.ComplaintService;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 此服务类用于实现针对于tb_complaint表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
@Service
public class ComplaintServiceImpl extends ServiceImpl<ComplaintMapper, Complaint> implements ComplaintService {

    @Autowired
    private ComplaintMapper complaintMapper;
    
    @Override
    public JSONObject listComplaint(JSONObject jsonObject) {
        CommonUtils.fillPageParam(jsonObject);
        int count = complaintMapper.selectCount(new QueryWrapper<>());
        List<JSONObject> list = complaintMapper.listComplaint(jsonObject);
        return CommonUtils.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject likeComplaintTitleAndStatus(JSONObject jsonObject) {
        QueryWrapper<Complaint> wrapper = new QueryWrapper<>();
        wrapper.like("complaint_type", jsonObject.getString("complaintType"))
                .eq("status", jsonObject.getString("status"));
        int count = complaintMapper.selectCount(wrapper);

        CommonUtils.fillPageParam(jsonObject);
        List<JSONObject> list = complaintMapper.likeComplaintTitleAndStatus(jsonObject);
        return CommonUtils.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject addComplaint(JSONObject jsonObject) {
        Complaint complaint = JSON.parseObject(jsonObject.toJSONString(), Complaint.class);
        int insert = complaintMapper.insert(complaint);
        if (insert <= 0) {
            return CommonUtils.filedJson("添加失败！");
        }
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject updateComplaint(JSONObject jsonObject) {
        Complaint complaint = JSON.parseObject(jsonObject.toJSONString(), Complaint.class);
        Complaint complaintObj = complaintMapper.selectById(complaint.getId());
        complaintObj.setStatus(complaint.getStatus());

        int update = complaintMapper.updateById(complaintObj);
        if (update <= 0) {
            return CommonUtils.filedJson("更新失败！");
        }
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject deleteComplaint(JSONObject jsonObject) {
        int id = jsonObject.getIntValue("id");
        int exist = complaintMapper.selectCount(new QueryWrapper<Complaint>().eq("id", id));
        if (exist <= 0) {
            return CommonUtils.filedJson("该活动已存在！");
        }

        int delete = complaintMapper.deleteById(id);
        if (delete <= 0) {
            return CommonUtils.filedJson("删除失败！");
        }
        return CommonUtils.successJson();
    }
}
