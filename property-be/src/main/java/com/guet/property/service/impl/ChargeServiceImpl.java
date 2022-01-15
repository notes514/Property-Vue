package com.guet.property.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.property.entity.Charge;
import com.guet.property.mapper.ChargeMapper;
import com.guet.property.service.ChargeService;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 此服务类用于实现针对于tb_charge表的常用操作
 *
 * @author dhxstart
 * @date 2022/1/14 12:39
 */
@Service
public class ChargeServiceImpl extends ServiceImpl<ChargeMapper, Charge> implements ChargeService {

    @Autowired
    private ChargeMapper chargeMapper;

    @Override
    public JSONObject listCharge(JSONObject jsonObject) {
        CommonUtils.fillPageParam(jsonObject);
        int count = chargeMapper.selectCount(new QueryWrapper<>());
        List<JSONObject> list = chargeMapper.listCharge(jsonObject);
        return CommonUtils.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject listChargeName() {
        QueryWrapper<Charge> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("charge_name");
        List<Charge> list = chargeMapper.selectList(queryWrapper);
        List<String> chargeNameList = new ArrayList<>();
        for (Charge charge : list) {
            chargeNameList.add(charge.getChargeName());
        }
        return CommonUtils.successJson(chargeNameList);
    }

    @Override
    public JSONObject likeChargeName(JSONObject jsonObject) {
        CommonUtils.fillPageParam(jsonObject);
        List<JSONObject> list = chargeMapper.likeChargeName(jsonObject);
        return CommonUtils.successPage(jsonObject, list, list.size());
    }

    @Override
    public JSONObject addCharge(JSONObject jsonObject) {
        Charge charge = JSON.parseObject(jsonObject.toJSONString(), Charge.class);
        int exist = chargeMapper.selectCount(
                new QueryWrapper<Charge>().eq("charge_name", charge.getChargeName()));
        if (exist > 0) {
            return CommonUtils.filedJson("该费用类型已存在，不能重复添加!");
        }
        final int insert = chargeMapper.insert(charge);
        if (insert <= 0) {
            return CommonUtils.filedJson("添加失败!");
        }
        return CommonUtils.successJson("添加成功!");
    }

    @Override
    public JSONObject updateCharge(JSONObject jsonObject) {
        Charge charge = JSON.parseObject(jsonObject.toJSONString(), Charge.class);
        Charge chargeObj = chargeMapper.selectById(charge.getId());
        chargeObj.setChargeName(charge.getChargeName());
        chargeObj.setChargeStandard(charge.getChargeStandard());

        final int update = chargeMapper.updateById(chargeObj);
        if (update <= 0) {
            return CommonUtils.filedJson("更新失败!");
        }
        return CommonUtils.successJson("更新成功!");
    }

    @Override
    public JSONObject deleteCharge(JSONObject jsonObject) {
        int delete = chargeMapper.deleteById(jsonObject.getIntValue("id"));
        if (delete <= 0) {
            return CommonUtils.filedJson("删除失败!");
        }
        return CommonUtils.successJson("删除成功!");
    }
}
