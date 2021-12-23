package com.guet.property.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.property.entity.Notice;

/**
 * 此服务接口用于实现针对于tb_notice表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
public interface NoticeService extends IService<Notice> {

    /**
     * 获取公告列表
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject listNotice(JSONObject jsonObject);

    /**
     * 根据公告标题和公告状态进行模糊搜索
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject likeNoticeTitleAndStatus(JSONObject jsonObject);

    /**
     * 添加公告
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject addNotice(JSONObject jsonObject);

    /**
     * 更新公告
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject updateNotice(JSONObject jsonObject);

    /**
     * 删除公告
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject deleteNotice(JSONObject jsonObject);
}
