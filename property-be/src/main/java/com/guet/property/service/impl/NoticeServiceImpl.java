package com.guet.property.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guet.property.entity.Notice;
import com.guet.property.mapper.NoticeMapper;
import com.guet.property.service.NoticeService;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 此服务类用于实现针对于tb_notice表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public JSONObject listNotice(JSONObject jsonObject) {
        CommonUtils.fillPageParam(jsonObject);
        int count = noticeMapper.selectCount(new QueryWrapper<>());
        List<JSONObject> list = noticeMapper.listNotice(jsonObject);
        return CommonUtils.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject likeNoticeTitleAndStatus(JSONObject jsonObject) {
        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        wrapper.like("title", jsonObject.getString("title"))
                .eq("status", jsonObject.getString("status"));
        int count = noticeMapper.selectCount(wrapper);

        CommonUtils.fillPageParam(jsonObject);
        List<JSONObject> list = noticeMapper.likeNoticeTitleAndStatus(jsonObject);
        return CommonUtils.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject addNotice(JSONObject jsonObject) {
        Notice notice = JSON.parseObject(jsonObject.toJSONString(), Notice.class);
        int exist = noticeMapper.selectCount(new QueryWrapper<Notice>().eq("title", notice.getTitle()));
        if (exist > 0) {
            return CommonUtils.filedJson("该公告已存在！");
        }

        int insert = noticeMapper.insert(notice);
        if (insert <= 0) {
            return CommonUtils.filedJson("添加失败！");
        }
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject updateNotice(JSONObject jsonObject) {
        Notice notice = JSON.parseObject(jsonObject.toJSONString(), Notice.class);
        Notice noticeObj = noticeMapper.selectById(notice.getId());
        noticeObj.setTitle(notice.getTitle());
        noticeObj.setContent(notice.getContent());
        noticeObj.setStatus(notice.getStatus());
        noticeObj.setReleaseTime(notice.getReleaseTime());

        int update = noticeMapper.updateById(noticeObj);
        if (update <= 0) {
            return CommonUtils.filedJson("更新失败！");
        }
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject deleteNotice(JSONObject jsonObject) {
        int id = jsonObject.getIntValue("id");
        int exist = noticeMapper.selectCount(new QueryWrapper<Notice>().eq("id", id));
        if (exist <= 0) {
            return CommonUtils.filedJson("该活动已存在！");
        }

        int delete = noticeMapper.deleteById(id);
        if (delete <= 0) {
            return CommonUtils.filedJson("删除失败！");
        }
        return CommonUtils.successJson();
    }
}
