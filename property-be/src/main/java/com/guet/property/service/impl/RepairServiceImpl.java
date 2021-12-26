package com.guet.property.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.property.entity.Repair;
import com.guet.property.mapper.RepairMapper;
import com.guet.property.service.RepairService;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 此服务类用于实现针对于tb_repair表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
@Service
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements RepairService {

    @Autowired
    private RepairMapper repairMapper;
    
    @Override
    public JSONObject listRepair(JSONObject jsonObject) {
        CommonUtils.fillPageParam(jsonObject);
        int count = repairMapper.selectCount(new QueryWrapper<>());
        List<JSONObject> list = repairMapper.listRepair(jsonObject);
        return CommonUtils.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject likeRepairTitleAndStatus(JSONObject jsonObject) {
        QueryWrapper<Repair> wrapper = new QueryWrapper<>();
        wrapper.eq("repair_type", jsonObject.getString("repairType"));
        wrapper.eq("status", jsonObject.getString("status"));
        int count = repairMapper.selectCount(wrapper);

        CommonUtils.fillPageParam(jsonObject);
        List<JSONObject> list = repairMapper.likeRepairTitleAndStatus(jsonObject);
        return CommonUtils.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject addRepair(JSONObject jsonObject) {
        Repair repair = JSON.parseObject(jsonObject.toJSONString(), Repair.class);
        int insert = repairMapper.insert(repair);
        if (insert <= 0) {
            return CommonUtils.filedJson("添加失败！");
        }
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject updateRepair(JSONObject jsonObject) {
        Repair repair = JSON.parseObject(jsonObject.toJSONString(), Repair.class);
        Repair repairObj = repairMapper.selectById(repair.getId());
        repairObj.setStatus(repair.getStatus());

        int update = repairMapper.updateById(repairObj);
        if (update <= 0) {
            return CommonUtils.filedJson("更新失败！");
        }
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject deleteRepair(JSONObject jsonObject) {
        int id = jsonObject.getIntValue("id");
        int exist = repairMapper.selectCount(new QueryWrapper<Repair>().eq("id", id));
        if (exist <= 0) {
            return CommonUtils.filedJson("该活动已存在！");
        }

        int delete = repairMapper.deleteById(id);
        if (delete <= 0) {
            return CommonUtils.filedJson("删除失败！");
        }
        return CommonUtils.successJson();
    }
}
