package com.guet.property.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.property.entity.Supply;
import com.guet.property.mapper.SupplyMapper;
import com.guet.property.service.SupplyService;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 供求表 服务实现类
 * </p>
 *
 * @author dhxstart
 * @since 2021-12-27
 */
@Service
public class SupplyServiceImpl extends ServiceImpl<SupplyMapper, Supply> implements SupplyService {

    @Autowired
    private SupplyMapper supplyMapper;
    
    @Override
    public JSONObject listSupply(JSONObject jsonObject) {
        CommonUtils.fillPageParam(jsonObject);
        int count = supplyMapper.selectCount(new QueryWrapper<>());
        List<JSONObject> list = supplyMapper.listSupply(jsonObject);
        return CommonUtils.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject likeSupplyTitleAndStatus(JSONObject jsonObject) {
        QueryWrapper<Supply> wrapper = new QueryWrapper<>();
        wrapper.eq("title", jsonObject.getString("title"));
        wrapper.eq("status", jsonObject.getString("status"));
        int count = supplyMapper.selectCount(wrapper);

        CommonUtils.fillPageParam(jsonObject);
        List<JSONObject> list = supplyMapper.likeSupplyTitleAndStatus(jsonObject);
        return CommonUtils.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject addSupply(JSONObject jsonObject) {
        Supply supplyDemand = JSON.parseObject(jsonObject.toJSONString(), Supply.class);
        int insert = supplyMapper.insert(supplyDemand);
        if (insert <= 0) {
            return CommonUtils.filedJson("添加失败！");
        }
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject updateSupply(JSONObject jsonObject) {
        Supply supplyDemand = JSON.parseObject(jsonObject.toJSONString(), Supply.class);
        Supply supplyDemandObj = supplyMapper.selectById(supplyDemand.getId());
        supplyDemandObj.setTitle(supplyDemand.getTitle());
        supplyDemandObj.setAddress(supplyDemand.getAddress());
        supplyDemandObj.setStatus(supplyDemand.getStatus());
        supplyDemandObj.setStartTime(supplyDemand.getStartTime());
        supplyDemandObj.setEndTime(supplyDemand.getEndTime());

        int update = supplyMapper.updateById(supplyDemandObj);
        if (update <= 0) {
            return CommonUtils.filedJson("更新失败！");
        }
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject deleteSupply(JSONObject jsonObject) {
        int delete = supplyMapper.deleteById(jsonObject.getIntValue("id"));
        if (delete <= 0) {
            return CommonUtils.filedJson("删除失败！");
        }
        return CommonUtils.successJson();
    }
}
