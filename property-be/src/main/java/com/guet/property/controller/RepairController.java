package com.guet.property.controller;

import com.alibaba.fastjson.JSONObject;
import com.guet.property.service.RepairService;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 维修表 前端控制器
 *
 * @author dhxstart
 * @date 2021/12/11 18:28
 */
@RestController
@RequestMapping("/repair")
public class RepairController {

    @Autowired
    private RepairService repairService;
    
    /**
     * 查询报修列表
     */
    @GetMapping("/listRepair")
    public JSONObject listRepair(HttpServletRequest request) {
        return repairService.listRepair(CommonUtils.request2Json(request));
    }

    /**
     * 根据报修内容和报修地点进行模糊搜索
     */
    @GetMapping("/likeRepair")
    public JSONObject likeRepairTitleStatus(HttpServletRequest request) {
        return repairService.likeRepairTitleAndStatus(CommonUtils.request2Json(request));
    }

    /**
     * 添加报修列表
     */
    @PostMapping("/addRepair")
    public JSONObject addRepair(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "");
        return repairService.addRepair(jsonObject);
    }

    /**
     * 更新报修列表
     */
    @PostMapping("/updateRepair")
    public JSONObject updateRepair(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "");
        return repairService.updateRepair(jsonObject);
    }

    /**
     * 删除报修
     */
    @PostMapping("/deleteRepair")
    public JSONObject deleteRepair(HttpServletRequest request) {
        return repairService.deleteRepair(CommonUtils.request2Json(request));
    }
}
