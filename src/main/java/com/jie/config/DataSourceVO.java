package com.jie.config;

import lombok.Data;

@Data
public class DataSourceVO {
    private String url;
    private String userName;
    private String password;
    private int initialSize;
    private int minIdle;
    private long maxWait;
    private String driverClassName;
    private int maxActive;
    private int idleTimeout;
    private int connectionTimeout;
}
