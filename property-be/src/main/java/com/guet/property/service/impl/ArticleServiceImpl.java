package com.guet.property.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.guet.property.mapper.ArticleMapper;
import com.guet.property.service.ArticleService;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 此服务类用于实现针对于tb_article表的常用操作
 *
 * @author dhxstart
 * @date 2021/12/9 19:53
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public JSONObject addArticle(JSONObject jsonObject) {
        articleMapper.addArticle(jsonObject);
        return CommonUtils.successJson();
    }

    @Override
    public JSONObject listArticle(JSONObject jsonObject) {
        CommonUtils.fillPageParam(jsonObject);
        int count = articleMapper.countArticle(jsonObject);
        List<JSONObject> list = articleMapper.listArticle(jsonObject);
        return CommonUtils.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject updateArticle(JSONObject jsonObject) {
        articleMapper.updateArticle(jsonObject);
        return CommonUtils.successJson();
    }
}
