package com.example.jobWeb.controller;

import com.auth0.jwt.JWT;
import com.example.jobWeb.config.UserLoginToken;
import com.example.jobWeb.domain.Article;
import com.example.jobWeb.domain.CommonResult;
import com.example.jobWeb.domain.User;
import com.example.jobWeb.service.ArticleService;
import com.example.jobWeb.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

/**
 * 文章
 *
 * @author ccziwe
 * @date 2020-09-02 10:35
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserServiceImpl userService;

    /**
     * ID查询文章
     * @param articleId
     * @return
     */
    @RequestMapping(value = "queryArticleById", method = RequestMethod.POST)
    public CommonResult queryArticleById(@RequestParam(name = "articleId") int articleId) {
        Article article = articleService.queryArticleById(articleId);
        return new CommonResult(200, article);
    }

    /**
     * 查询所有文章
     * @return
     */
    @RequestMapping(value = "queryAllArticle", method = RequestMethod.POST)
    public CommonResult queryAllArticle() {
            List<Article> articles = articleService.queryAllArticle();
            return new CommonResult(200, articles);
    }

    /**
     * 添加文章
     *
     * @param token
     * @param content
     * @param title
     * @return
     */
    @UserLoginToken
    @RequestMapping(value = "addArticle", method = RequestMethod.POST)
    public CommonResult addArticle(@RequestHeader("token") String token,
                                   @RequestParam(name = "content") String content,
                                   @RequestParam(name = "title") String title) {
        User user = userService.queryUserById(Integer.parseInt(JWT.decode(token).getAudience().get(0)));
        // 是否是管理员
        if (user.getUser_id() == 1) {
            Date date = new Date(System.currentTimeMillis());
            Article article = new Article(title, content, date, date);
            int i = articleService.addArticle(article);
            return new CommonResult(200, article, "添加成功");
        }
        return new CommonResult(400, "您不是管理员");
    }

    /**
     * 修改文章
     *
     * @param token
     * @param content
     * @param title
     * @param articleId
     * @return
     */
    @UserLoginToken
    @RequestMapping(value = "updateArticle", method = RequestMethod.POST)
    public CommonResult updateArticle(@RequestHeader("token") String token,
                                      @RequestParam(name = "content") String content,
                                      @RequestParam(name = "title") String title,
                                      @RequestParam(name = "articleId") int articleId) {
        User user = userService.queryUserById(Integer.parseInt(JWT.decode(token).getAudience().get(0)));
        // 是否是管理员
        if (user.getUser_id() == 1) {
            Article article = new Article(articleId, title, content, new Date(System.currentTimeMillis()));
            int i = articleService.updateArticle(article);
            return new CommonResult(200, article, "修改成功");
        }
        return new CommonResult(400, "您不是管理员");
    }
}
