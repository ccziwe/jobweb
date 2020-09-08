package com.example.jobWeb.service;

import com.example.jobWeb.domain.Article;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ccziwe
 * @date 2020-09-02 10:12
 */
@Service
public interface ArticleService {
    List<Article> queryAllArticle();
    int addArticle(Article article);
    int updateArticle(Article article);
    Article queryArticleById(int articleId);
}
