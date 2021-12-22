package com.guet.property.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.property.entity.Activity;
import com.guet.property.mapper.ActivityMapper;
import com.guet.property.service.ActivityService;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 此服务类用于实现针对于tb_activity表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public JSONObject listActivity(JSONObject jsonObject) {
        CommonUtils.fillPageParam(jsonObject);
        int count = activityMapper.selectCount(new QueryWrapper<>());
        List<JSONObject> list = activityMapper.listActivity(jsonObject);
        return CommonUtils.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject likeActivityTitleAndStatus(JSONObject jsonObject) {
        QueryWrapper<Activity> wrapper = new QueryWrapper<>();
        wrapper.like("title", jsonObject.getString("title"));
        wrapper.eq("status", jsonObject.getString("status"));
        int count = activityMapper.selectCount(wrapper);

        CommonUtils.fillPageParam(jsonObject);
        List<JSONObject> list = activityMapper.likeActivityTitleAndStatus(jsonObject);
        return CommonUtils.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject addActivity(JSONObject jsonObject) {
        Activity activity = JSON.parseObject(jsonObject.toJSONString(), Activity.class);
        int exist = activityMapper.selectCount(new QueryWrapper<Activity>().eq("title", activity.getTitle()));
        if (exist > 0) {
            return CommonUtils.filedJson("该活动已存在！");
        }

        int insert = activityMapper.insert(activity);
        if (insert <= 0) {
            return CommonUtils.filedJson("添加失败！");
        }
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject updateActivity(JSONObject jsonObject) {
        Activity activity = JSON.parseObject(jsonObject.toJSONString(), Activity.class);
        Activity activityObj = activityMapper.selectById(activity.getId());
        activityObj.setTitle(activity.getTitle());
        activityObj.setAddress(activity.getAddress());
        activityObj.setOrganizer(activity.getOrganizer());
        activityObj.setStatus(activity.getStatus());
        activityObj.setStartTime(activity.getStartTime());
        activityObj.setEndTime(activity.getEndTime());

        int update = activityMapper.updateById(activityObj);
        if (update <= 0) {
            return CommonUtils.filedJson("更新失败！");
        }
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject deleteActivity(JSONObject jsonObject) {
        int id = jsonObject.getIntValue("id");
        int exist = activityMapper.selectCount(new QueryWrapper<Activity>().eq("id", id));
        if (exist <= 0) {
            return CommonUtils.filedJson("该活动已存在！");
        }

        int delete = activityMapper.deleteById(id);
        if (delete <= 0) {
            return CommonUtils.filedJson("删除失败！");
        }
        return CommonUtils.successJson();
    }
}
