package com.example.jobWeb.mapper;

import com.example.jobWeb.domain.Article;
import com.example.jobWeb.domain.Enroll;
import com.example.jobWeb.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 报名
 *
 * @author ccziwe
 * @date 2020-09-02 16:02
 */

public interface EnrollMapper {
    /**
     * 管理员根据任务ID查询未审核或已通过的人员名单
     * articleId 任务ID
     * status 0未审核 | 1已通过
     * @param enroll
     * @return
     */
    @Select("select u.*\n" +
            "from user u left join enroll e  on u.user_id = e.userId\n" +
            "left join article a  on e.articleId = a.articleId\n" +
            "where a.articleId is not null and a.articleId = #{articleId} and e.status = #{status}")
    List<User> queryAllEnroll(Enroll enroll);

    /**
     * 用户根据ID和状态查询自己已报名的所有任务
     * status 0未审核 | 1已通过
     * @param enroll
     * @return
     */
    @Select("select a.*\n" +
            "from user u left join enroll e  on u.user_id = e.userId\n" +
            "left join article a  on e.articleId = a.articleId\n" +
            "where a.articleId is not null and e.userId = #{userId} and e.status = #{status}")
    List<Article> userQueryEnroll(Enroll enroll);

    /**
     * 用户报名任务
     * @param enroll
     * @return
     */
    @Insert("INSERT INTO enroll (userId, articleId) VALUES (#{userId}, #{articleId})")
    int userEnroll(Enroll enroll);

    /**
     * 校验如果 >=1 就已经报名或已经通过审核
     *
     * @return
     */
    @Select("SELECT * FROM enroll where userId = #{userId} and articleId = #{articleId}")
    List<Enroll> checkEnroll(Enroll enroll);

    /**
     * 管理员通过审核
     * @param enroll
     * @return
     */
    @Update("UPDATE enroll SET status = 1 where userId = #{userId} and articleId = #{articleId}")
    int passEnroll(Enroll enroll);
}
