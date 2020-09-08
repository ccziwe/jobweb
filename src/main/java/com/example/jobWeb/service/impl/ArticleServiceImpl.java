package com.example.jobWeb.service.impl;

import com.example.jobWeb.domain.Article;
import com.example.jobWeb.mapper.ArticleMapper;
import com.example.jobWeb.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ccziwe
 * @date 2020-09-02 10:14
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired()
    private ArticleMapper articleMapper;

    @Override
    public List<Article> queryAllArticle() {
        return articleMapper.queryAllArticle();
    }

    @Override
    public int addArticle(Article article) {
        return articleMapper.addArticle(article);
    }

    @Override
    public int updateArticle(Article article) {
        return articleMapper.updateArticle(article);
    }

    @Override
    public Article queryArticleById(int articleId) {
        return articleMapper.queryArticleById(articleId);
    }
}
