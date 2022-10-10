package com.bupt.scsdata.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "scsdata")
@Data
public class AppProperties {
    String fileDirectory;// 静态文件的根目录
    String serverIp; // 服务器IP地址
    @Value("${server.port}")
    String PORT; // 程序运行的端口
}