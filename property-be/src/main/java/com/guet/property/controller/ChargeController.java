package com.guet.property.controller;

import com.alibaba.fastjson.JSONObject;
import com.guet.property.service.ChargeService;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 收费项目表 前端控制器
 *
 * @author dhxstart
 * @date 2022/1/14 12:39
 */
@RestController
@RequestMapping("/charge")
public class ChargeController {

    @Autowired
    private ChargeService chargeService;

    /**
     * 查询收费项目列表
     */
    @GetMapping("/listCharge")
    public JSONObject listCharge(HttpServletRequest request) {
        return chargeService.listCharge(CommonUtils.request2Json(request));
    }

    /**
     * 查询收费项目名称列表
     */
    @GetMapping("/listChargeName")
    public JSONObject listChargeName() {
        return chargeService.listChargeName();
    }

    /**
     * 根据收费项目名称模糊搜索
     */
    @GetMapping("/likeChargeName")
    public JSONObject likeChargeName(HttpServletRequest request) {
        return chargeService.likeChargeName(CommonUtils.request2Json(request));
    }

    /**
     * 添加收费项目
     */
    @PostMapping("/addCharge")
    public JSONObject addCharge(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "");
        return chargeService.addCharge(jsonObject);
    }

    /**
     * 更新收费项目
     */
    @PostMapping("/updateCharge")
    public JSONObject updateCharge(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "");
        return chargeService.updateCharge(jsonObject);
    }

    /**
     * 删除收费项目
     */
    @PostMapping("/deleteCharge")
    public JSONObject deleteCharge(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "");
        return chargeService.deleteCharge(jsonObject);
    }
}
