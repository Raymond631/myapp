package com.example.myapp.common.utils;

import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.core.math.Calculator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.example.myapp.common.exception.ServiceException;
import com.example.myapp.entity.dto.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CaptchaUtil {
    @Value("${captcha.prefix}")
    private String prefix;
    @Value("${captcha.timeout}")
    private Long timeout;

    @Autowired
    private RedisUtil redisUtil;

    public Captcha createCaptcha() {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = cn.hutool.captcha.CaptchaUtil.createShearCaptcha(200, 45, 4, 4);
        // 自定义验证码内容为四则运算方式
        captcha.setGenerator(new MathGenerator());
        // 重新生成code
        captcha.createCode();

        // 计算表达式结果
        int code = (int) Calculator.conversion(captcha.getCode());
        String id = IdUtil.simpleUUID();
        redisUtil.setCacheObject(prefix + id, code, timeout, TimeUnit.SECONDS);
        return new Captcha(id, captcha.getImageBase64());
    }

    public void checkCaptcha(String id, int code) {
        String key = prefix + id;
        if (StrUtil.isBlank(id)) {
            throw new ServiceException("验证码id不能为空");
        } else if (!redisUtil.hasKey(key)) {
            throw new ServiceException("验证码已失效");
        }
        int captcha = redisUtil.getCacheObject(key);
        redisUtil.deleteObject(key);
        if (code != captcha) {
            throw new ServiceException("验证码错误");
        }
    }
}
