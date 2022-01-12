package com.guet.property.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.property.entity.House;
import com.guet.property.mapper.HouseMapper;
import com.guet.property.service.HouseService;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 此服务类用于实现针对于tb_house表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
@Service
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements HouseService {

    @Autowired
    private HouseMapper houseMapper;

    @Override
    public JSONObject listHouse(JSONObject jsonObject) {
        CommonUtils.fillPageParam(jsonObject);
        int count = houseMapper.selectCount(new QueryWrapper<>());
        List<JSONObject> list = houseMapper.listHouse(jsonObject);
        return CommonUtils.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject likeHouseNameAndType(JSONObject jsonObject) {
        QueryWrapper<House> wrapper = new QueryWrapper<>();
        wrapper.eq("title", jsonObject.getString("title"));
        wrapper.eq("status", jsonObject.getString("status"));
        int count = houseMapper.selectCount(wrapper);

        CommonUtils.fillPageParam(jsonObject);
        List<JSONObject> list = houseMapper.likeHouseNameAndStatus(jsonObject);
        return CommonUtils.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject addHouse(JSONObject jsonObject) {
        House house = JSON.parseObject(jsonObject.toJSONString(), House.class);
        int insert = houseMapper.insert(house);

        if (insert <= 0) {
            return CommonUtils.filedJson("添加失败！");
        }
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject updateHouse(JSONObject jsonObject) {
        House house = JSON.parseObject(jsonObject.toJSONString(), House.class);
        House houseObj = houseMapper.selectById(house.getId());
        houseObj.setCode(house.getCode());
        houseObj.setName(house.getName());
        houseObj.setArea(house.getArea());
        houseObj.setRoomNum(house.getRoomNum());
        houseObj.setFloor(house.getFloor());
        houseObj.setTelephone(house.getTelephone());
        houseObj.setStatus(house.getStatus());
        houseObj.setRefinedDecoration(house.getRefinedDecoration());
        houseObj.setDescription(house.getDescription());
        house.setLiveTime(house.getLiveTime());

        int update = houseMapper.updateById(houseObj);
        if (update <= 0) {
            return CommonUtils.filedJson("更新失败！");
        }
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject deleteHouse(JSONObject jsonObject) {
        int delete = houseMapper.deleteById(jsonObject.getIntValue("id"));

        if (delete <= 0) {
            return CommonUtils.filedJson("删除失败！");
        }
        return CommonUtils.successJson();
    }
}
