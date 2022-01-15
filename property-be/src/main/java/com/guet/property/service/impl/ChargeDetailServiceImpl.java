package com.guet.property.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.property.entity.ChargeDetail;
import com.guet.property.mapper.ChargeDetailMapper;
import com.guet.property.service.ChargeDetailService;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 此服务类用于实现针对于tb_charge_detail表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
@Service
public class ChargeDetailServiceImpl extends ServiceImpl<ChargeDetailMapper, ChargeDetail>
        implements ChargeDetailService {

    @Autowired
    private ChargeDetailMapper chargeDetailMapper;

    @Override
    public JSONObject listChargeDetail(JSONObject jsonObject) {
        CommonUtils.fillPageParam(jsonObject);
        int count = chargeDetailMapper.selectCount(new QueryWrapper<>());
        List<JSONObject> list = chargeDetailMapper.listChargeDetail(jsonObject);
        return CommonUtils.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject likeChargeDetailNameAndStatus(JSONObject jsonObject) {
        CommonUtils.fillPageParam(jsonObject);
        List<JSONObject> list = chargeDetailMapper.likeChargeDetailNameAndType(jsonObject);
        return CommonUtils.successPage(jsonObject, list, list.size());
    }

    @Override
    public JSONObject addChargeDetail(JSONObject jsonObject) {
        ChargeDetail charge = JSON.parseObject(jsonObject.toJSONString(), ChargeDetail.class);
        int exist = chargeDetailMapper.selectCount(
                new QueryWrapper<ChargeDetail>().eq("charge_name", charge.getChargeName()));
        if (exist > 0) {
            return CommonUtils.filedJson("该费用类型已存在，不能重复添加!");
        }
        final int insert = chargeDetailMapper.insert(charge);
        if (insert <= 0) {
            return CommonUtils.filedJson("添加失败!");
        }
        return CommonUtils.successJson("添加成功!");
    }

    @Override
    public JSONObject updateChargeDetail(JSONObject jsonObject) {
        ChargeDetail chargeDetail = JSON.parseObject(jsonObject.toJSONString(), ChargeDetail.class);
        ChargeDetail chargeDetailObj = chargeDetailMapper.selectById(chargeDetail.getId());
        chargeDetailObj.setOwnerId(chargeDetail.getOwnerId());
        chargeDetailObj.setOwnerName(chargeDetail.getOwnerName());
        chargeDetailObj.setChargeId(chargeDetail.getChargeId());
        chargeDetailObj.setChargeName(chargeDetail.getChargeName());
        chargeDetailObj.setChargeStandard(chargeDetail.getChargeStandard());
        chargeDetailObj.setPayReal(chargeDetail.getPayReal());
        chargeDetailObj.setPayBalance(chargeDetail.getPayBalance());
        chargeDetailObj.setPayStatus(chargeDetail.getPayStatus());
        chargeDetailObj.setPayTime(chargeDetail.getPayTime());

        final int update = chargeDetailMapper.updateById(chargeDetailObj);
        if (update <= 0) {
            return CommonUtils.filedJson("更新失败!");
        }
        return CommonUtils.successJson("更新成功!");
    }

    @Override
    public JSONObject deleteChargeDetail(JSONObject jsonObject) {
        int delete = chargeDetailMapper.deleteById(jsonObject.getIntValue("id"));
        if (delete <= 0) {
            return CommonUtils.filedJson("删除失败!");
        }
        return CommonUtils.successJson("删除成功!");
    }
}
