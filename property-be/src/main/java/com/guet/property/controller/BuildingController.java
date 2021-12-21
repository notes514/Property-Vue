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
     * 查询楼栋列表
     */
    @GetMapping("/listBuilding")
    public JSONObject listBuilding(HttpServletRequest request) {
        return buildingService.listBuilding(CommonUtils.request2Json(request));
    }

    /**
     * 根据楼栋名称和类型进行模糊搜索
     */
    @GetMapping("/likeBuilding")
    public JSONObject likeBuildingNameAndType(HttpServletRequest request) {
        return buildingService.likeBuildingNameAndType(CommonUtils.request2Json(request));
    }

    /**
     * 添加楼栋列表
     */
    @PostMapping("/addBuilding")
    public JSONObject addBuilding(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "buildingName,totalHouseholds,type,description");
        return buildingService.addBuilding(jsonObject);
    }

    /**
     * 更新楼栋列表
     */
    @PostMapping("/updateBuilding")
    public JSONObject updateBuilding(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "id,buildingName,totalHouseholds,type,description");
        return buildingService.updateBuilding(jsonObject);
    }

    /**
     * 删除楼栋列表
     */
    @PostMapping("/deleteBuilding")
    public JSONObject deleteBuilding(HttpServletRequest request) {
        return buildingService.deleteBuilding(CommonUtils.request2Json(request));
    }
}
