package com.bupt.scsdata.controller;

import com.bupt.scsdata.service.IUserService;
import com.bupt.scsdata.controller.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {
    @Autowired
    IUserService userService;

    /**
     * 重定向api，如果未认证或者未授权访问，直接重定向到登录界面
     * */
    @GetMapping("/redirect")
    public R redirect() {
        return R.error().message("尚未登陆，请先登录后访问");
    }

    /*PC端登录api*/
    @PostMapping("/pclogin")
    public R pcLogin(String account, String pwd){
        return userService.pcLogin(account, pwd);
    }

    /*小程序微信账号登录*/
    @PostMapping("/wechatloginbywechat")
    public R wechatLoginByWechat(String wechat){
        return userService.wechatLoginByWechat(wechat);
    }
}
