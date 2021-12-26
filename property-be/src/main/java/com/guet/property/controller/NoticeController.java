package com.guet.property.controller;

import com.alibaba.fastjson.JSONObject;
import com.guet.property.service.NoticeService;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 公告表 前端控制器
 *
 * @author dhxstart
 * @date 2021/12/11 18:28
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;
    
    /**
     * 查询公告列表
     */
    @GetMapping("/listNotice")
    public JSONObject listNotice(HttpServletRequest request) {
        return noticeService.listNotice(CommonUtils.request2Json(request));
    }

    /**
     * 根据公告内容和公告地点进行模糊搜索
     */
    @GetMapping("/likeNotice")
    public JSONObject likeNoticeTitleStatus(HttpServletRequest request) {
        return noticeService.likeNoticeTitleAndStatus(CommonUtils.request2Json(request));
    }

    /**
     * 添加公告列表
     */
    @PostMapping("/addNotice")
    public JSONObject addNotice(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "");
        return noticeService.addNotice(jsonObject);
    }

    /**
     * 更新公告列表
     */
    @PostMapping("/updateNotice")
    public JSONObject updateNotice(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "");
        return noticeService.updateNotice(jsonObject);
    }

    /**
     * 删除公告
     */
    @PostMapping("/deleteNotice")
    public JSONObject deleteNotice(HttpServletRequest request) {
        return noticeService.deleteNotice(CommonUtils.request2Json(request));
    }
}
