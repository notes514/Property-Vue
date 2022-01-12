package com.guet.property.controller;

import com.alibaba.fastjson.JSONObject;
import com.guet.property.service.HouseService;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 房屋表 前端控制器
 *
 * @author dhxstart
 * @date 2021/12/11 18:28
 */
@RestController
@RequestMapping("/house")
public class HouseController {
    
    @Autowired
    private HouseService houseService;
    
    /**
     * 查询楼栋列表
     */
    @GetMapping("/listHouse")
    public JSONObject listHouse(HttpServletRequest request) {
        return houseService.listHouse(CommonUtils.request2Json(request));
    }

    /**
     * 根据楼栋名称和类型进行模糊搜索
     */
    @GetMapping("/likeHouse")
    public JSONObject likeHouseNameAndType(HttpServletRequest request) {
        return houseService.likeHouseNameAndType(CommonUtils.request2Json(request));
    }

    /**
     * 添加楼栋列表
     */
    @PostMapping("/addHouse")
    public JSONObject addHouse(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "");
        return houseService.addHouse(jsonObject);
    }

    /**
     * 更新楼栋列表
     */
    @PostMapping("/updateHouse")
    public JSONObject updateHouse(@RequestBody JSONObject jsonObject) {
        CommonUtils.hasAllRequired(jsonObject, "");
        return houseService.updateHouse(jsonObject);
    }

    /**
     * 删除楼栋
     */
    @PostMapping("/deleteHouse")
    public JSONObject deleteHouse(HttpServletRequest request) {
        return houseService.deleteHouse(CommonUtils.request2Json(request));
    }
}
