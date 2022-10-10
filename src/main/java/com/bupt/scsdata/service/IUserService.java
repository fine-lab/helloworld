package com.bupt.scsdata.service;

import com.bupt.scsdata.controller.util.R;

public interface IUserService {
    /**
     * PC端登录
     *
     * @param account
     * @param pwd
     */
    public R pcLogin(String account, String pwd);

    /**
     * 微信小程序登录（微信号登录）
     *
     * @param wechat
     */
    public R wechatLoginByWechat(String wechat);

}
