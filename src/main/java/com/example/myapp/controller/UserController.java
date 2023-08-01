package com.example.myapp.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.example.myapp.common.http.CommonResponse;
import com.example.myapp.common.utils.CaptchaUtil;
import com.example.myapp.entity.User;
import com.example.myapp.entity.dto.Captcha;
import com.example.myapp.entity.dto.LoginBody;
import com.example.myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CaptchaUtil captchaUtil;

    @GetMapping("/captcha")
    public Object code() {
        Captcha captcha = captchaUtil.createCaptcha();
        return CommonResponse.success(captcha);
    }

    @PostMapping("/register")
    public CommonResponse register(@RequestBody LoginBody loginBody) {
        // 检查验证码
        captchaUtil.checkCaptcha(loginBody.getId(), loginBody.getCode());
        User user = loginBody.getUser();
        // 加密
        String pwdSecret = DigestUtil.md5Hex(user.getPassword());
        user.setPassword(pwdSecret);
        // 注册
        userService.register(user);
        return CommonResponse.success("注册成功");
    }

    @PostMapping("/login")
    public CommonResponse login(@RequestBody LoginBody loginBody) {
        // 检查验证码
        captchaUtil.checkCaptcha(loginBody.getId(), loginBody.getCode());
        User user = loginBody.getUser();
        // 加密
        String pwdSecret = DigestUtil.md5Hex(user.getPassword());
        user.setPassword(pwdSecret);
        // 登录
        userService.login(user);
        return CommonResponse.success("登录成功");
    }

    @DeleteMapping("/logout")
    public CommonResponse logout() {
        StpUtil.logout(StpUtil.getLoginIdAsString());
        return CommonResponse.success("已退出登录");
    }
}
