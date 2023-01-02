package com.user.userapi.conf;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef="h2dbEntityManager", transactionManagerRef="h2dbtr", basePackages="com.user.userapi.dao")
public class UserConf {
	
	@Autowired
	private Environment env;
	
@Bean("datasource")
//@ConfigurationProperties(prefix = "spring.datasource")
public DataSource h2ds() {
	 DriverManagerDataSource dataSource
     = new DriverManagerDataSource();
   dataSource.setDriverClassName(
     env.getProperty("spring.datasource.driver-class-name"));
   dataSource.setUrl(env.getProperty("spring.datasource.url"));
   dataSource.setUsername(env.getProperty("spring.datasource.username"));
   dataSource.setPassword(env.getProperty("spring.datasource.password"));

   return dataSource;
}

@Bean
public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
   return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
}
@SuppressWarnings("unchecked")
@Bean(name = "h2dbEntityManager")
public LocalContainerEntityManagerFactoryBean h2dbEntityManagerFactory(EntityManagerFactoryBuilder builder) {  
	return builder
			.dataSource(h2ds())
			.properties(hibernateProperties())
			.packages("com.user.userapi.model")
			.persistenceUnit("h2")
			.build();
}
@Bean(name = "h2dbtr")
public PlatformTransactionManager h2dbTransactionManager(@Qualifier("h2dbEntityManager") EntityManagerFactory entityManagerFactory) {
	return new JpaTransactionManager(entityManagerFactory);
}
@SuppressWarnings("rawtypes")
private Map hibernateProperties() {
	Properties properties = new Properties() {
		private static final long serialVersionUID = 1L;
		{
			setProperty("hibernate.hbm2ddl.auto", "create-drop");
			setProperty("hibernate.show_sql", "true");
			setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		}
	};
	return properties.entrySet().stream().collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue()));
}


}
