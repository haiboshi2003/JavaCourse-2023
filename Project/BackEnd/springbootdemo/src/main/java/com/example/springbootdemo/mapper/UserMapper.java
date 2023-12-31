package com.example.springbootdemo.mapper;

import com.example.springbootdemo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    //根据id查用户
    @Select("select * from user where id=#{id}")
    User findById(Integer id);

    //注册用户
    @Insert("insert into user(username,id,password,create_time,update_time)"+
            " values(#{username},#{id},#{password},now(),now())")
    void add(String username, Integer id, String password);
}
