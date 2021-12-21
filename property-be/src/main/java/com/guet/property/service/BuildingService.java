package com.guet.property.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.property.entity.Building;
import org.springframework.transaction.annotation.Transactional;

/**
 * 此服务接口用于实现针对于tb_building表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
public interface BuildingService extends IService<Building> {

    /**
     * 获取文章列表
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject listBuilding(JSONObject jsonObject);

    /**
     * 模糊搜索
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject likeBuildingNameAndType(JSONObject jsonObject);

    /**
     * 新增文章
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    @Transactional(rollbackFor = Exception.class)
    JSONObject addBuilding(JSONObject jsonObject);

    /**
     * 更新文章
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    @Transactional(rollbackFor = Exception.class)
    JSONObject updateBuilding(JSONObject jsonObject);

    /**
     * 删除角色
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    @Transactional(rollbackFor = Exception.class)
    JSONObject deleteBuilding(JSONObject jsonObject);
}
