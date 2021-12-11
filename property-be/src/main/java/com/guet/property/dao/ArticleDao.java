package com.guet.property.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 文章Dao层
 *
 * @author dhxstart
 * @date 2021/12/9 8:40
 */
public interface ArticleDao {
    /**
     * 新增文章
     *
     * @param jsonObject jsonObject
     * @return int
     */
    int addArticle(JSONObject jsonObject);

    /**
     * 统计文章总数
     *
     * @param jsonObject jsonObject
     * @return int
     */
    int countArticle(JSONObject jsonObject);

    /**
     * 文章列表
     */
    List<JSONObject> listArticle(JSONObject jsonObject);

    /**
     * 更新文章
     */
    int updateArticle(JSONObject jsonObject);
}
