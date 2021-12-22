package com.guet.property.controller;

import com.alibaba.fastjson.JSONObject;
import com.guet.property.service.ActivityService;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 活动表 前端控制器
 *
 * @author dhxstart
 * @date 2021/12/11 18:28
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {
    
    @Autowired
    private ActivityService activityService;
    
    /**
     * 查询活动列表
     */
    @GetMapping("/listActivity")
    public JSONObject listActivity(HttpServletRequest request) {
        return activityService.listActivity(CommonUtils.request2Json(request));
    }

    /**
     * 根据活动内容和活动地点进行模糊搜索
     */
    @GetMapping("/likeActivity")
    public JSONObject likeActivityTitleStatus(HttpServletRequest request) {
        return activityService.likeActivityTitleAndStatus(CommonUtils.request2Json(request));
    }

    /**
     * 添加活动列表
     */
    @PostMapping("/addActivity")
    public JSONObject addActivity(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "");
        return activityService.addActivity(jsonObject);
    }

    /**
     * 更新活动列表
     */
    @PostMapping("/updateActivity")
    public JSONObject updateActivity(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "");
        return activityService.updateActivity(jsonObject);
    }

    /**
     * 删除活动
     */
    @PostMapping("/deleteActivity")
    public JSONObject deleteActivity(HttpServletRequest request) {
        return activityService.deleteActivity(CommonUtils.request2Json(request));
    }
}
