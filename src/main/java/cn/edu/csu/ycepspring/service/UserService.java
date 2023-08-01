package cn.edu.csu.ycepspring.service;

import cn.edu.csu.ycepspring.entity.dto.LoginResp;

public interface UserService {
    LoginResp checkAccount(String username, String password);

    LoginResp register(String username, String password);
}
