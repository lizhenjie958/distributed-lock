package com.jie.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.jie.mapper.mapper",
        sqlSessionTemplateRef = "basicSqlSessionTemplate")
@Lazy
public class BasicDataSource {
    @Resource(name = "dataSource")
    DataSource dataSource;

    /**
     * 使用声明的数据源，创建sqlSession工厂
     */
    @Bean
    public SqlSessionFactory basicSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));

        return bean.getObject();
    }

    /**
     * 声明数据源有自己的事务管理
     */
    @Bean
    public DataSourceTransactionManager basicTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 声明SqlSessionTemplate由指定的SqlSession工厂创建
     */
    @Bean
    public SqlSessionTemplate basicSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(basicSqlSessionFactory());
    }

    @Bean("basicTransactionTemplate")
    public TransactionTemplate dataTransactionTemplate(DataSourceTransactionManager basicTransactionManager) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(basicTransactionManager);
        return transactionTemplate;
    }

}
