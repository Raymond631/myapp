package com.example.myapp.mapper;

import com.example.myapp.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int checkUser(User user);

    int checkUsername(@Param("username") String username);

    void addUser(User user);
}
