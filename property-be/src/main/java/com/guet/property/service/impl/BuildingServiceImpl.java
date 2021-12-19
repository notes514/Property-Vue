package com.guet.property.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.property.entity.Building;
import com.guet.property.mapper.BuildingMapper;
import com.guet.property.service.BuildingService;
import com.guet.property.util.CommonUtils;
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
        Building building = JSON.parseObject(jsonObject.toJSONString(), Building.class);
        int exist = buildingMapper.selectCount(new QueryWrapper<Building>().eq("building_name",
                building.getBuildingName()));
        if (exist > 0) {
            return CommonUtils.filedJson("该楼栋已存在！");
        }

        int insert = buildingMapper.insert(building);
        if (insert <= 0) {
            return CommonUtils.filedJson("添加失败！");
        }
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject updateBuilding(JSONObject jsonObject) {
        Building building = JSON.parseObject(jsonObject.toJSONString(), Building.class);
        Building buildingObj = buildingMapper.selectById(building.getId());
        buildingObj.setType(building.getType());
        buildingObj.setBuildingName(building.getBuildingName());
        buildingObj.setTotalHouseholds(building.getTotalHouseholds());
        buildingObj.setDescription(building.getDescription());

        int update = buildingMapper.updateById(buildingObj);
        if (update <= 0) {
            return CommonUtils.filedJson("更新失败！");
        }
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject deleteBuilding(JSONObject jsonObject) {
        int id = jsonObject.getIntValue("id");
        int exist = buildingMapper.selectCount(new QueryWrapper<Building>().eq("id", id));
        if (exist <= 0) {
            return CommonUtils.filedJson("该楼栋已存在！");
        }

        int delete = buildingMapper.deleteById(id);
        if (delete <= 0) {
            return CommonUtils.filedJson("删除失败！");
        }
        return CommonUtils.successJson();
    }
}
