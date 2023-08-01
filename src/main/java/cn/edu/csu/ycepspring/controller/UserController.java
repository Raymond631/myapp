package cn.edu.csu.ycepspring.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.edu.csu.ycepspring.common.captcha.CaptchaService;
import cn.edu.csu.ycepspring.common.response.CommonResponse;
import cn.edu.csu.ycepspring.common.utils.EncryptionUtils;
import cn.edu.csu.ycepspring.entity.dto.LoginBody;
import cn.edu.csu.ycepspring.entity.dto.LoginResp;
import cn.edu.csu.ycepspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CaptchaService captchaService;

    /**
     * 获取验证码
     */
    @GetMapping("/code")
    public Object getCaptcha() {
        return captchaService.createCaptcha();
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public CommonResponse login(@RequestBody LoginBody loginBody) {
        captchaService.checkCaptcha(loginBody.getCode(), loginBody.getCodeID());
        LoginResp userInfo = userService.checkAccount(loginBody.getUsername(), loginBody.getPassword());
        // 登录
        StpUtil.login(userInfo.getAccount());
        userInfo.setToken(StpUtil.getTokenInfo().getTokenValue());
        return CommonResponse.success("登陆成功", userInfo);
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public CommonResponse register(@RequestBody LoginBody loginBody) {
        captchaService.checkCaptcha(loginBody.getCode(), loginBody.getCodeID());
        // 密码加密
        String pwdSecret = EncryptionUtils.encode(loginBody.getPassword());
        LoginResp userInfo = userService.register(loginBody.getUsername(), pwdSecret);
        // 登录
        StpUtil.login(userInfo.getAccount());
        userInfo.setToken(StpUtil.getTokenInfo().getTokenValue());
        return CommonResponse.success("注册成功", userInfo);
    }

    /**
     * 退出登录
     */
    @DeleteMapping("/logout")
    public CommonResponse logout() {
        StpUtil.logout();
        return CommonResponse.success("注销成功");
    }
}
