package com.bupt.scsdata.po;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class User {
    //静态变量
    public static String TEACHER = "teacher";
    public static String ADMIN = "admin";

    private String id;  //主键标识
    private String account;
    private String wechat;
    private String name;
    private String pwd;
    private String role;
}
