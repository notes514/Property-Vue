package com.guet.property.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.transaction.annotation.Transactional;

/**
 * 此服务接口用于实现针对于tb_article表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/9 19:53
 */
public interface ArticleService {

    /**
     * 新增文章
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    @Transactional(rollbackFor = Exception.class)
    JSONObject addArticle(JSONObject jsonObject);

    /**
     * 获取文章列表
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    JSONObject listArticle(JSONObject jsonObject);

    /**
     * 更新文章
     *
     * @param jsonObject json参数
     * @return JSONObject
     */
    @Transactional(rollbackFor = Exception.class)
    JSONObject updateArticle(JSONObject jsonObject);
}
