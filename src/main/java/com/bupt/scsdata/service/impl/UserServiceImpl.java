package com.bupt.scsdata.service.impl;

import com.bupt.scsdata.controller.util.R;
import com.bupt.scsdata.po.User;
import com.bupt.scsdata.service.IUserService;
import com.bupt.scsdata.dao.IUserDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public R pcLogin(String account, String pwd) {
        //获取当前用户对应的Subject
        Subject subject = SecurityUtils.getSubject();
        //执行登录方法
        try {
            subject.login(new UsernamePasswordToken(account, pwd));
        } catch (Exception e) {
            log.info("用户【" + account + "】尝试登录，但是登陆失败，shiro异常");
            return R.error().message("登录失败");
        }
        //获取用户实体
        User user = userDao.findUserByAccount(account);
        //认证成功
        if (subject.isAuthenticated()) {
            // 设置登录有效期
            subject.getSession().setTimeout(1000 * 60 * 60 * 24 * 7);
            // 密码置空
            user.setPwd("");
            // 将用户信息插入到session中
            Session session = subject.getSession();
            session.setAttribute("user", user);
            // 记录日志并返回数据
            log.info("账户【" + account + "】登录成功");
            return R.ok().data("user",user).data("AUTH_TOKEN", session.getId());
        }
        // 登陆失败，查找失败原因
        if (user == null) {// 账户不存在
            log.info("账户【" + account + "】尝试登陆，但是账户不存在");
            return R.error().message("账户不存在");
        } else if (!user.getPwd().equals(pwd)) {// 密码错误
            log.info("账户【" + account + "】尝试登陆，但是密码错误");
            return R.error().message("密码错误");
        }
        return R.error().message("登录失败");
    }

    @Override
    public R wechatLoginByWechat(String wechat) {
        //获取用户实体
        User user = userDao.findUserByWechat(wechat);
        //用户不存在，登录失败
        if (user == null){
            log.info("微信账户【" + wechat + "】尝试登陆，但是账户不存在");
            return R.error().message("微信账户不存在");
        }
        user.setPwd(""); //密码置空
        log.info("微信账户【" + wechat + "】登录成功");
        return R.ok().data("user", user);
    }
}
