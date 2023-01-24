package com.nhnacademy.jdbc.board.mapper;

import com.nhnacademy.jdbc.board.domain.Comments;
import com.nhnacademy.jdbc.board.domain.Post;
import com.nhnacademy.jdbc.board.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommentsMapper {
    @Results(id = "commentsMapperMap", value = {
            @Result(property = "writer", column = "writer_id", javaType = Optional.class, many= @Many(select="getUser")),
            @Result(property = "modifier", column = "modifier_id", javaType = Optional.class, many= @Many(select="getUser")),
    })
    @Select("select * from MyBatisComments where id = #{id}")
    Comments getComments(Long id);

    @ResultMap("commentsMapperMap")
    @Select("select * from MyBatisComments where post_id = #{post_id}")
    List<Comments> getAllCommentsByPostId(Long post_id);


    @Select("select * from MyBatisUser where id = #{writer_id}")
    Optional<User> getUser(Long writer_id);

    @Transactional
    @Insert("insert into MyBatisComments" +
            " values (null, #{post_id}, #{writer.id}, #{content}, now(), null, null, 0, null)")
    void insertComments(Comments comments);

//    @Delete("delete from MyBatisComments where id=#{id}")
//    void deleteById(Long id);

    @Transactional
    @Update("update MyBatisComments set is_deleted = 1 where id = #{id}")
    void updateDeleteFlagById(Long Id);

    @Transactional
    @Update("update MyBatisComments set content = #{content}, modifier_id = #{modifier.id}, " +
            "modified_at = now() where id = #{id}")
    void updateComments(Comments comments);

}
