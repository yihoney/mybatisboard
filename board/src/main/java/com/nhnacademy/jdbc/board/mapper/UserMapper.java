package com.nhnacademy.jdbc.board.mapper;

import com.nhnacademy.jdbc.board.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    @Result(property = "usertype", column = "usertype_id", one= @One(select="getUsertypeById"))
    @Select("select * from MyBatisUser where id = #{id}")
    Optional<User> getUserById(Long id);

    @Result(property = "usertype", column = "usertype_id", one= @One(select="getUsertypeById"))
    @Select("select * from MyBatisUser where username = #{username}")
    Optional<User> getUserByUsername(String username);

    @Select("select value from MyBatisUsertype where id = #{usertype_id}")
    String getUsertypeById(Long usertype_id);

    @Select("select * from MyBatisUser")
    List<User> getUsers();

    @Transactional
    @Insert("insert into MyBatisUser(id, usertype_id, username, password, created_at) " +
            "values (#{id}, #{usertype_id}, #{username}, #{password}, now()")
    void insertUser(User user);

    // 비밀번호 업데이트
    @Transactional
    @Update("update MyBatisUser set password=#{password} where id = #{id}")
    void updatePasswordById(String password, Long id);

    @Delete("delete from MyBatisUser where id=#{id}")
    void deleteById(Long id);
}
