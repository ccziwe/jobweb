package com.example.jobWeb.domain;

import java.io.Serializable;

/**
 * 报名实体类
 * @author ccziwe
 * @date 2020-09-02 09:50
 */

public class Enroll implements Serializable {
    private static final long serialVersionUID = -4034776081963122223L;
    private int userId,articleId,status;

    @Override
    public String toString() {
        return "Enroll{" +
                "userId=" + userId +
                ", articleId=" + articleId +
                ", status=" + status +
                '}';
    }

    public Enroll() {
    }

    public Enroll(int userId, int articleId, int status) {
        this.userId = userId;
        this.articleId = articleId;
        this.status = status;
    }

    public Enroll(int userId, int articleId) {
        this.userId = userId;
        this.articleId = articleId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }
}
