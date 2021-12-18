package com.guet.property.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.property.entity.Building;
import com.guet.property.mapper.BuildingMapper;
import com.guet.property.service.BuildingService;
import com.guet.property.util.CommonUtils;
import com.guet.property.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 此服务类用于实现针对于tb_building表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements BuildingService {

    @Autowired
    private BuildingMapper buildingMapper;


    @Override
    public JSONObject listBuilding(JSONObject jsonObject) {
        CommonUtils.fillPageParam(jsonObject);
        int count = buildingMapper.selectCount(new QueryWrapper<>());
        List<JSONObject> list = buildingMapper.listBuilding(jsonObject);
        return CommonUtils.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject addBuilding(JSONObject jsonObject) {
        String buildingName = jsonObject.getString("buildingName");
        int exist = buildingMapper.selectCount(new QueryWrapper<Building>().eq("building_name", buildingName));
        System.out.println("exist：" + exist);
        if (exist > 0) {
            return CommonUtils.errorJson(ErrorEnum.E_10009);
        }
        Building building = new Building();
        building.setBuildingName(buildingName);
        building.setTotalHouseholds(jsonObject.getIntValue("totalHouseholds"));
        building.setType(jsonObject.getString("type"));
        building.setDescription(jsonObject.getString("description"));
        buildingMapper.insert(building);
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject updateBuilding(JSONObject jsonObject) {
        int id = jsonObject.getIntValue("id");
        Building building = new Building();
        building.setBuildingName(jsonObject.getString("buildingName"));
        building.setTotalHouseholds(jsonObject.getIntValue("totalHouseholds"));
        building.setType(jsonObject.getString("type"));
        building.setDescription(jsonObject.getString("description"));
        update(building, new QueryWrapper<Building>().eq("id", id));
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject deleteBuilding(JSONObject jsonObject) {
        int id = jsonObject.getIntValue("id");
        int exist = buildingMapper.selectCount(new QueryWrapper<Building>().eq("id", id));
        if (exist <= 0) {
            return CommonUtils.errorJson(ErrorEnum.E_10009);
        }
        int flag = buildingMapper.deleteById(id);
        if (flag <= 0) {
            return CommonUtils.errorJson(ErrorEnum.E_500);
        }
        return CommonUtils.successJson();
    }
}
