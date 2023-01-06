package com.user.userapi.conf;

import java.io.IOException;
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
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
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
	
	@Bean
    @Primary
	public LocalContainerEntityManagerFactoryBean h2dbEntityManager() {
		
		  LocalContainerEntityManagerFactoryBean em
          = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(bd1DataSource());
        em.setPackagesToScan(
          new String[] { "com.user.userapi.model", "com.user.userapi.dao" });
        
        HibernateJpaVendorAdapter vendorAdapter
        = new HibernateJpaVendorAdapter();
      em.setJpaVendorAdapter(vendorAdapter);
      HashMap<String, Object> properties = new HashMap<>();
      //properties.put("spring.jpa.hibernate.ddl-auto",
        //env.getProperty("spring.jpa.hibernate.ddl-auto", "update"));
      properties.put("hibernate.hbm2ddl.auto", "update");
      properties.put("spring.jpa.show-sql",
    	        env.getProperty("spring.jpa.show-sql", "true"));
      properties.put("spring.jpa.database-platform",
        env.getProperty("spring.jpa.database-platform"));
      em.setJpaPropertyMap(properties);

      return em;
		
		
	}
	
	@Primary
	@Bean(name="dataSource")
	public DataSource bd1DataSource() {
		 DriverManagerDataSource dataSource
         = new DriverManagerDataSource();
       dataSource.setDriverClassName(
         env.getProperty("spring.datasource.driver-class-name"));
       dataSource.setUrl(env.getProperty("spring.datasource.url"));
       dataSource.setUsername(env.getProperty("spring.datasource.username"));
       dataSource.setPassword(env.getProperty("spring.datasource.password"));

       return dataSource;
		
		
	}
	
	@Primary
	@Bean(name="h2dbtr")
	public PlatformTransactionManager h2dbtr() {
		
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(h2dbEntityManager().getObject());
		
		return transactionManager;
		
		
	}
	
	/*@EventListener
	private void onApplication(ContextRefreshedEvent event) throws IOException{
		ResourceDatabasePopulator dataPopulator = new ResourceDatabasePopulator(new ClassPathResource("insert.sql"));
		dataPopulator.setContinueOnError(true);
		dataPopulator.execute( bd1DataSource());
		
		
		
	}*/
	

}
