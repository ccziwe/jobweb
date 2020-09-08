package com.example.jobWeb.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;

/**
 * 文章实体类
 *
 * @author ccziwe
 * @date 2020-09-02 09:53
 */

public class Article implements Serializable {
    private static final long serialVersionUID = -5341148390186832019L;
    private int articleId;
    private String title, content;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date createDate, updateDate;

    public Article() {
    }

    public Article(String title, String content, Date createDate, Date updateDate) {
        this.content = content;
        this.title = title;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Article(int articleId, String title, String content, Date updateDate) {
        this.articleId = articleId;
        this.content = content;
        this.title = title;
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

}
