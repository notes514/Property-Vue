package com.guet.property.mapper;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 文章表Mapper接口
 *
 * @author dhxstart
 * @date 2021/12/11 17:14
 */
public interface ArticleMapper {
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
