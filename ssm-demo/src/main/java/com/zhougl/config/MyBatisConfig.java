package com.zhougl.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author zhougl
 * 2018/8/26
 **/
@Configuration
@MapperScan(basePackages = {"com.zhougl.dao"},sqlSessionFactoryRef = "mySqlSessionFactoryBean")
public class MyBatisConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisConfig.class);

    @Value("${test.username}")
    private String username;

    @Value("${test.url}")
    private String url;

    @Value("${test.password}")
    private String password;

    @Value("${test.driverClassName}")
    private String driverClassName;

    @Bean(name = "druidDataSource")
    public DataSource getDataSource(){
        String validationQuery=  "select 1";
        try (DruidDataSource dataSource = new DruidDataSource()) {
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            dataSource.setDriverClassName(driverClassName);
            dataSource.setInitialSize(1);
            dataSource.setMaxActive(20);
            dataSource.setMinIdle(1);
            dataSource.setMaxWait(60000);
            dataSource.setValidationQuery(validationQuery);
            dataSource.setTestOnBorrow(false);
            dataSource.setTestOnReturn(false);
            dataSource.setTestWhileIdle(true);
            dataSource.setTimeBetweenEvictionRunsMillis(60000);
            dataSource.setMinEvictableIdleTimeMillis(300000);
            dataSource.setRemoveAbandoned(true);
            dataSource.setRemoveAbandonedTimeout(1800);
            dataSource.setLogAbandoned(true);
            return dataSource;
        }
    }

    @Bean(name = "mySqlSessionFactoryBean")
    public SqlSessionFactory getSqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getDataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage("com.zhougl.dao");

        //分页插件
        PageInterceptor interceptor = new com.github.pagehelper.PageInterceptor();
        Properties properties = new Properties();
        properties.put("helperDialect", "mysql");
        interceptor.setProperties(properties);

        sqlSessionFactoryBean.setPlugins(new Interceptor[]{interceptor});

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);//不开启驼峰支持
        // 设置sql查询字段为空时显示该字段（解决返回map类型数据空值字段不显示的配置）
        sqlSessionFactoryBean.getObject().getConfiguration().setCallSettersOnNulls(true);
        sqlSessionFactoryBean.getObject().getConfiguration().setUseGeneratedKeys(true);

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "myTransactionManager")
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(getDataSource());
    }

}
