package com.jie.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class BasicDataSourceBean {
    @Resource
    private BasicDataSourceConfig basicDataSourceConfig;

    @Bean(value = "dataSource", destroyMethod ="close")
    public DataSource dataSource() {
        DataSourceVO dataSourceVO = basicDataSourceConfig.getData();
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(dataSourceVO.getDriverClassName());
        dataSource.setJdbcUrl(dataSourceVO.getUrl());
        dataSource.setUsername(dataSourceVO.getUserName());
        dataSource.setPassword(dataSourceVO.getPassword());
        dataSource.setMinimumIdle(dataSourceVO.getMinIdle());
        dataSource.setMaximumPoolSize(dataSourceVO.getMaxActive());
        dataSource.setIdleTimeout(dataSourceVO.getIdleTimeout());
        dataSource.setMaxLifetime(dataSourceVO.getMaxWait());
        dataSource.setConnectionTimeout(dataSourceVO.getConnectionTimeout());
        return dataSource;
    }
}
