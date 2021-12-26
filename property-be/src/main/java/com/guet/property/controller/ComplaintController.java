package com.guet.property.controller;


import com.alibaba.fastjson.JSONObject;
import com.guet.property.service.ComplaintService;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 投诉表 前端控制器
 *
 * @author dhxstart
 * @date 2021/12/11 18:28
 */
@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    /**
     * 查询投诉列表
     */
    @GetMapping("/listComplaint")
    public JSONObject listComplaint(HttpServletRequest request) {
        return complaintService.listComplaint(CommonUtils.request2Json(request));
    }

    /**
     * 根据投诉内容和投诉地点进行模糊搜索
     */
    @GetMapping("/likeComplaint")
    public JSONObject likeComplaintTitleStatus(HttpServletRequest request) {
        return complaintService.likeComplaintTitleAndStatus(CommonUtils.request2Json(request));
    }

    /**
     * 添加投诉列表
     */
    @PostMapping("/addComplaint")
    public JSONObject addComplaint(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "");
        return complaintService.addComplaint(jsonObject);
    }

    /**
     * 更新投诉列表
     */
    @PostMapping("/updateComplaint")
    public JSONObject updateComplaint(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "");
        return complaintService.updateComplaint(jsonObject);
    }

    /**
     * 删除投诉
     */
    @PostMapping("/deleteComplaint")
    public JSONObject deleteComplaint(HttpServletRequest request) {
        return complaintService.deleteComplaint(CommonUtils.request2Json(request));
    }
}
