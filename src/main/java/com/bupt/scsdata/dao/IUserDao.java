package com.bupt.scsdata.dao;

import com.bupt.scsdata.po.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserDao extends MongoRepository<User, String>{
    public User findUserByAccount(String account);
    public User findUserByWechat(String wechat);

}
