package com.guet.property.controller;

import com.alibaba.fastjson.JSONObject;
import com.guet.property.service.SupplyService;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 供求表 前端控制器
 *
 * @author dhxstart
 * @date 2021/12/11 18:28
 */
@RestController
@RequestMapping("/supply")
public class SupplyController {

    @Autowired
    private SupplyService supplyService;
    
    /**
     * 查询供求列表
     */
    @GetMapping("/listSupply")
    public JSONObject listSupply(HttpServletRequest request) {
        return supplyService.listSupply(CommonUtils.request2Json(request));
    }

    /**
     * 根据供求内容和供求地点进行模糊搜索
     */
    @GetMapping("/likeSupply")
    public JSONObject likeSupplyTitleStatus(HttpServletRequest request) {
        return supplyService.likeSupplyTitleAndStatus(CommonUtils.request2Json(request));
    }

    /**
     * 添加供求列表
     */
    @PostMapping("/addSupply")
    public JSONObject addSupply(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "");
        return supplyService.addSupply(jsonObject);
    }

    /**
     * 更新供求列表
     */
    @PostMapping("/updateSupply")
    public JSONObject updateSupply(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "");
        return supplyService.updateSupply(jsonObject);
    }

    /**
     * 删除供求
     */
    @PostMapping("/deleteSupply")
    public JSONObject deleteSupply(HttpServletRequest request) {
        return supplyService.deleteSupply(CommonUtils.request2Json(request));
    }
}
