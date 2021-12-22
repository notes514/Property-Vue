package com.guet.property.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.property.entity.Owner;
import com.guet.property.mapper.OwnerMapper;
import com.guet.property.service.OwnerService;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 此服务类用于实现针对于tb_owner表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
@Service
public class OwnerServiceImpl extends ServiceImpl<OwnerMapper, Owner> implements OwnerService {

    @Autowired
    private OwnerMapper ownerMapper;

    @Override
    public JSONObject listOwner(JSONObject jsonObject) {
        CommonUtils.fillPageParam(jsonObject);
        int count = ownerMapper.selectCount(new QueryWrapper<>());
        List<JSONObject> listOwner = ownerMapper.listOwner(jsonObject);
        return CommonUtils.successPage(jsonObject, listOwner, count);
    }

    @Override
    public JSONObject likeOwnerNameAndType(JSONObject jsonObject) {
        CommonUtils.fillPageParam(jsonObject);

        QueryWrapper<Owner> wrapper = new QueryWrapper<>();
        wrapper.like("name", jsonObject.getString("name"))
                .eq("type", jsonObject.getString("type"));

        Integer count = ownerMapper.selectCount(wrapper);
        List<JSONObject> list = ownerMapper.likeOwnerNameAndType(jsonObject);
        return CommonUtils.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject addOwner(JSONObject jsonObject) {
        Owner owner = JSON.parseObject(jsonObject.toJSONString(), Owner.class);
        int exist = count(new QueryWrapper<Owner>().eq("id_card", owner.getIdCard()));
        if (exist > 0) {
            return CommonUtils.filedJson("该业主已存在！");
        }

        int insert = ownerMapper.insert(owner);
        if (insert <= 0) {
            return CommonUtils.filedJson("添加失败！");
        }
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject updateOwner(JSONObject jsonObject) {
        Owner owner = JSON.parseObject(jsonObject.toJSONString(), Owner.class);
        Owner ownerUpdate = ownerMapper.selectById(owner.getId());

        System.out.println(" owner.getBirthday() --- " + owner.getBirthday());

        ownerUpdate.setHouseId(owner.getHouseId());
        ownerUpdate.setName(owner.getName());
        ownerUpdate.setIdCard(owner.getIdCard());
        ownerUpdate.setBirthday(owner.getBirthday());
        ownerUpdate.setProfession(owner.getProfession());
        ownerUpdate.setSex(owner.getSex());
        ownerUpdate.setType(owner.getType());
        ownerUpdate.setPicture(owner.getPicture());
        ownerUpdate.setTelephone(owner.getTelephone());
        ownerUpdate.setRemark(owner.getRemark());

        int update = ownerMapper.updateById(ownerUpdate);
        if (update <= 0) {
            return CommonUtils.filedJson("更新业主失败！");
        }
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject deleteOwner(JSONObject jsonObject) {
        int id = jsonObject.getIntValue("id");
        boolean removeOwner = removeById(id);
        if (!removeOwner) {
            return CommonUtils.filedJson("删除业主失败！");
        }
        return CommonUtils.successJson();
    }
}
