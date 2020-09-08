package com.example.jobWeb.mapper;

import com.example.jobWeb.domain.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author ccziwe
 * @date 2020-09-02 10:05
 */

public interface ArticleMapper {
    /**
     * ID查询文章
     * @return
     */
    @Select("SELECT * FROM article where articleId = #{articleId}")
    Article queryArticleById(int articleId);

    /**
     * 查询所有文章列表
     * @return
     */
    @Select("SELECT * FROM article")
    List<Article> queryAllArticle();

    /**
     * 添加文章
     * @param article
     * @return
     */
    @Insert("insert into article(content,title,createDate,updateDate) values (#{content},#{title},#{createDate},#{updateDate})")
    int addArticle(Article article);

    /**
     * 修改文章
     * @param article
     * @return
     */
    @Update("UPDATE article SET content = #{content}, title = #{title} ,updateDate = #{updateDate} WHERE articleId = #{articleId}")
    int updateArticle(Article article);
}
