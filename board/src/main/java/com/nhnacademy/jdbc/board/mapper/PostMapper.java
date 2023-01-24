package com.nhnacademy.jdbc.board.mapper;

import com.nhnacademy.jdbc.board.domain.Comments;
import com.nhnacademy.jdbc.board.domain.Post;
import com.nhnacademy.jdbc.board.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {
    @Select("select * from MyBatisPost where id = #{id}")
    @Results(id = "postMapperMap", value = {
            @Result(property = "writer", column = "writer_id", javaType = Optional.class, many= @Many(select="getUser")),
            @Result(property = "modifier", column = "modifier_id", javaType = Optional.class, many= @Many(select="getUser")),
    })
    Post getPost(Long id);

    @ResultMap(value = "postMapperMap")
    @Select("select * from MyBatisPost")
    List<Post> getPosts();

    @Transactional
    @Insert("insert into MyBatisPost" +
            " values (null, #{writer.id}, #{title}, now(), #{content}, null, null, 0, null)")
    void insertPost(Post post);

    @Transactional
    @Update("update MyBatisPost set title = #{title}, content = #{content}, modifier_id = #{modifier.id}, " +
            "modified_at = now() where id = #{id}")
    void updatePost(Post post);

//    @Delete("delete from MyBatisPost where id=#{id}")
//    void deleteById(Long id);

    @Transactional
    @Update("update MyBatisPost set is_deleted = 1 where id = #{id}")
    void updateDeleteFlagTrueById(Long Id);

    @Transactional
    @Update("update MyBatisPost set is_deleted = 0 where id = #{id}")
    void updateDeleteFlagFalseById(Long Id);

// 객체 매핑

    @Result(property = "usertype", column = "usertype_id", one= @One(select="getUsertypeById"))
    @Select("select * from MyBatisUser where id = #{id}")
    Optional<User> getUser(Long id);

    @Results(id = "commentsMapperMap", value = {
            @Result(property = "writer", column = "writer_id", javaType = Optional.class, many= @Many(select="getUser")),
            @Result(property = "modifier", column = "modifier_id", javaType = Optional.class, many= @Many(select="getUser")),
    })
    @Select("select * from MyBatisComments where post_id = #{c_post_id}")
    List<Comments> getAllCommentsByPostId(Long c_post_id);

    @Select("select value from MyBatisUsertype where id = #{usertype_id}")
    String getUsertypeById(Long usertype_id);

}
