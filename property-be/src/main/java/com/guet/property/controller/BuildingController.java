package com.guet.property.controller;

import com.alibaba.fastjson.JSONObject;
import com.guet.property.service.BuildingService;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 楼栋表 前端控制器
 *
 * @author dhxstart
 * @date 2021/12/11 18:28
 */
@RestController
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    /**
     * 查询文章列表
     */
    @GetMapping("/listBuilding")
    public JSONObject listBuilding(HttpServletRequest request) {
        return buildingService.listBuilding(CommonUtils.request2Json(request));
    }

    @PostMapping("/addBuilding")
    public JSONObject addBuilding(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "buildingName,totalHouseholds,type,description");
        return buildingService.addBuilding(jsonObject);
    }

    @PostMapping("/updateBuilding")
    public JSONObject updateBuilding(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "id,buildingName,totalHouseholds,type,description");
        return buildingService.updateBuilding(jsonObject);
    }

    @PostMapping("/deleteBuilding")
    public JSONObject deleteBuilding(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "id");
        return buildingService.deleteBuilding(jsonObject);
    }
}
