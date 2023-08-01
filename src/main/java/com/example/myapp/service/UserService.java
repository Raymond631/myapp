package com.example.myapp.service;

import cn.dev33.satoken.stp.StpUtil;
import com.example.myapp.common.exception.ServiceException;
import com.example.myapp.entity.User;
import com.example.myapp.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void login(User user) {
        int num = userMapper.checkUser(user);
        if (num == 1) {
            StpUtil.login(user.getUsername());
        } else {
            throw new ServiceException("用户名或密码错误!");
        }
    }

    public void register(User user) {
        if (user.getUsername().length() > 16) {
            throw new ServiceException("用户名不能超过16个字符！");
        } else {
            int num = userMapper.checkUsername(user.getUsername());
            if (num != 0) {
                throw new ServiceException("用户名已存在!");
            } else {
                userMapper.addUser(user);
                StpUtil.login(user.getUsername());
            }
        }
    }
}
