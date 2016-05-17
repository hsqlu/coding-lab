package com.hsqlu.coding.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.hsqlu.coding.common.Constants;
import com.hsqlu.coding.common.WebContext;
import com.hsqlu.coding.common.handler.TableMapperInterceptor;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.ValidationMode;
import javax.sql.DataSource;

/**
 * Created: 2016/5/6.
 * Author: Qiannan Lu
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaAuditing
@ComponentScan
@PropertySource(value = "classpath:druid.properties")
public class RDMSConfig {
    public static final Dialect DIALECT = new MySQL5InnoDBDialect();
    private static final String INTERCEPTOR = TableMapperInterceptor.class.getCanonicalName();

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(env.getProperty("druid.url").trim());
        dataSource.setDriverClassName(env.getProperty("druid.driverClassName").trim());
        dataSource.setUsername(env.getProperty("druid.user").trim());
        dataSource.setPassword(env.getProperty("druid.password").trim());
        dataSource.setInitialSize(Integer.parseInt(env.getProperty("druid.initialSize").trim()));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("druid.minIdle").trim()));
        dataSource.setMaxActive(Integer.parseInt(env.getProperty("druid.maxActive").trim()));
        dataSource.setMaxWait(Long.parseLong(env.getProperty("druid.maxWait").trim()));
        //超时配置 set by Su Sunbin on 2015/05/04
        dataSource.setRemoveAbandoned(Boolean.valueOf(env.getProperty("druid.removeAbandoned").trim()));//超过时间是否回收
        dataSource.setRemoveAbandonedTimeout(Integer.parseInt(env.getProperty("druid.removeAbandonedTimeout").trim()));//超过时间限制多长

        dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getProperty("druid.timeBetweenEvictionRunsMillis").trim()));
        dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(env.getProperty("druid.minEvictableIdleTimeMillis").trim()));
        dataSource.setValidationQuery(env.getProperty("druid.validationQuery").trim());
        dataSource.setTestWhileIdle(Boolean.valueOf(env.getProperty("druid.testWhileIdle").trim()));
        dataSource.setTestOnBorrow(Boolean.valueOf(env.getProperty("druid.testOnBorrow").trim()));
        dataSource.setTestOnReturn(Boolean.valueOf(env.getProperty("druid.testOnReturn").trim()));
        //是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。又说MySql5.5以下的没有。。 注释 by Su Sunbin on 2015/05/04
        dataSource.setPoolPreparedStatements(Boolean.valueOf(env.getProperty("druid.poolPreparedStatements").trim()));
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(env.getProperty("druid.maxPoolPreparedStatementPerConnectionSize").trim()));

       /* Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DbConn.DEFAULT, dataSource);

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(dataSource);
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.afterPropertiesSet();*/
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        if (Constants.DEVELOPMENT_ENV.equals(env.getProperty(WebContext.WEB_ENVIRONMENT))) {
            vendorAdapter.setShowSql(true);
        }

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.nd.donate");
        factory.setDataSource(dataSource);
        factory.getJpaPropertyMap().put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        factory.getJpaPropertyMap().put("hibernate.ejb.interceptor", INTERCEPTOR);
        factory.setValidationMode(ValidationMode.NONE);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
