package com.guet.property.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guet.property.entity.Owner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 此服务接口用于实现针对于tb_owner表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/11 18:12
 */
public interface OwnerService extends IService<Owner> {

    /**
     * 获取业主列表
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject listOwner(JSONObject jsonObject);

    /**
     * 新增业主
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    @Transactional(rollbackFor = Exception.class)
    JSONObject addOwner(JSONObject jsonObject);

    /**
     * 更新业主
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    @Transactional(rollbackFor = Exception.class)
    JSONObject updateOwner(JSONObject jsonObject);

    /**
     * 删除业主
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    @Transactional(rollbackFor = Exception.class)
    JSONObject deleteOwner(JSONObject jsonObject);
}
