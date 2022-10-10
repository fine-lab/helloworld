package com.bupt.scsdata.config;

import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    final UserRealm userRealm;
    public ShiroConfig(UserRealm userRealm) {
        this.userRealm = userRealm;
    }
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        // 设置shiro内置过滤器
        Map<String,String> filterMap = new LinkedHashMap<>();
        /*
        map 中value 的意义
        * anon: 无需认证就可以访问资源；
        * authc：必须认证后才能访问资源；
        * user：必须拥有“记住我”功能才能访问资源；
        * perms：拥有对某个资源的权限才能访问资源；
        * role：拥有某个角色权限才能访问资源
        * **/
        filterMap.put("/users/pclogin","anon");
        filterMap.put("/users/wechatloginbywechat","anon");
        filterMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        // 没有认证，进行界面跳转
        shiroFilterFactoryBean.setLoginUrl("/users/redirect");
        return shiroFilterFactoryBean;
    }

    // DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        defaultWebSecurityManager.setRealm(userRealm);
        defaultWebSecurityManager.setSessionManager(sessionManager());
        return defaultWebSecurityManager;
    }

    @Bean
    public SessionManager sessionManager(){
        ShiroSession shiroSession = new ShiroSession();
        shiroSession.setSessionDAO(new EnterpriseCacheSessionDAO());
        return shiroSession;
    }
}