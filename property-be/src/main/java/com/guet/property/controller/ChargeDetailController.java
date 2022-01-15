package com.guet.property.controller;

import com.alibaba.fastjson.JSONObject;
import com.guet.property.service.ChargeDetailService;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 收费名细表 前端控制器
 *
 * @author dhxstart
 * @date 2021/12/11 18:28
 */
@RestController
@RequestMapping("/charge/detail")
public class ChargeDetailController {

    @Autowired
    private ChargeDetailService chargeDetailService;

    /**
     * 查询收费项目明细列表
     */
    @GetMapping("/listChargeDetail")
    public JSONObject listChargeDetail(HttpServletRequest request) {
        return chargeDetailService.listChargeDetail(CommonUtils.request2Json(request));
    }

    /**
     * 根据收费项目明细名称模糊搜索
     */
    @GetMapping("/likeChargeDetailNameAndStatus")
    public JSONObject likeChargeDetailNameAndStatus(HttpServletRequest request) {
        return chargeDetailService.likeChargeDetailNameAndStatus(CommonUtils.request2Json(request));
    }

    /**
     * 添加收费项目明细
     */
    @PostMapping("/addChargeDetail")
    public JSONObject addCharge(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "");
        return chargeDetailService.addChargeDetail(jsonObject);
    }

    /**
     * 更新收费项目明细
     */
    @PostMapping("/updateChargeDetail")
    public JSONObject updateChargeDetail(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "");
        return chargeDetailService.updateChargeDetail(jsonObject);
    }

    /**
     * 删除收费项目明细
     */
    @PostMapping("/deleteChargeDetail")
    public JSONObject deleteChargeDetail(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "");
        return chargeDetailService.deleteChargeDetail(jsonObject);
    }
}
