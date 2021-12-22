package com.guet.property.controller;

import com.alibaba.fastjson.JSONObject;
import com.guet.property.service.OwnerService;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 业主表 前端控制器
 *
 * @author dhxstart
 * @date 2021/12/11 18:28
 */
@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    /**
     * 查询业主列表
     */
    @GetMapping("/listOwner")
    public JSONObject listOwner(HttpServletRequest request) {
        return ownerService.listOwner(CommonUtils.request2Json(request));
    }

    /**
     * 查询业主列表
     */
    @GetMapping("/likeOwner")
    public JSONObject likeOwner(HttpServletRequest request) {
        return ownerService.likeOwnerNameAndType(CommonUtils.request2Json(request));
    }

    /**
     * 添加业主列表
     */
    @PostMapping("/addOwner")
    public JSONObject addOwner(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "");
        return ownerService.addOwner(jsonObject);
    }

    /**
     * 更新业主列表
     */
    @PostMapping("/updateOwner")
    public JSONObject updateOwner(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "");
        return ownerService.updateOwner(jsonObject);
    }

    /**
     * 删除业主列表
     */
    @PostMapping("/deleteOwner")
    public JSONObject deleteOwner(HttpServletRequest request) {
        return ownerService.deleteOwner(CommonUtils.request2Json(request));
    }
}
