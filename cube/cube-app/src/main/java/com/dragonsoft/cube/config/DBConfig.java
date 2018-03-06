package com.dragonsoft.cube.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.ValidationMode;
import javax.sql.DataSource;

/**
 * Created: 2016/9/2.
 * Author: Qiannan Lu
 */
@Configuration
@EnableJpaRepositories(value = "com.dragonsoft.cube.**.repository", includeFilters = {@ComponentScan.Filter(Repository.class)})
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaAuditing
@PropertySource("classpath:druid.properties")
public class DBConfig {

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();

		dataSource.setUrl(env.getProperty("druid.url").trim());
		dataSource.setDriverClassName(env.getProperty("druid.driverClassName").trim());
		dataSource.setUsername(env.getProperty("druid.user").trim());
		dataSource.setPassword(env.getProperty("druid.password").trim());

		//配置初始化大小、最小、最大、配置获取连接等待超时的时间
		dataSource.setInitialSize(Integer.parseInt(env.getProperty("druid.initialSize").trim()));
		dataSource.setMinIdle(Integer.parseInt(env.getProperty("druid.minIdle").trim()));
		dataSource.setMaxActive(Integer.parseInt(env.getProperty("druid.maxActive").trim()));
		dataSource.setMaxWait(Long.parseLong(env.getProperty("druid.maxWait").trim()));
		//超时配置 set by Su Sunbin on 2015/05/04
		dataSource.setRemoveAbandoned(Boolean.valueOf(env.getProperty("druid.removeAbandoned").trim()));//超过时间是否回收
		dataSource.setRemoveAbandonedTimeout(Integer.parseInt(env.getProperty("druid.removeAbandonedTimeout").trim()));//超过时间限制多长
		//配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
		dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getProperty("druid.timeBetweenEvictionRunsMillis").trim()));
		//配置一个连接在池中最小生存的时间，单位是毫秒
		dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(env.getProperty("druid.minEvictableIdleTimeMillis").trim()));
		dataSource.setValidationQuery(env.getProperty("druid.validationQuery").trim());
		dataSource.setTestWhileIdle(Boolean.valueOf(env.getProperty("druid.testWhileIdle").trim()));
		dataSource.setTestOnBorrow(Boolean.valueOf(env.getProperty("druid.testOnBorrow").trim()));
		dataSource.setTestOnReturn(Boolean.valueOf(env.getProperty("druid.testOnReturn").trim()));
		//是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
		dataSource.setPoolPreparedStatements(Boolean.valueOf(env.getProperty("druid.poolPreparedStatements").trim()));
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(env.getProperty("druid.maxPoolPreparedStatementPerConnectionSize").trim()));

		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.dragonsoft.cube");
		factory.setDataSource(dataSource());
		factory.getJpaPropertyMap().put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
//		factory.getJpaPropertyMap().put("hibernate.ejb.interceptor", INTERCEPTOR);
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
