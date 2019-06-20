package com.zhichenhaixin.certificatemanage;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.github.pagehelper.PageHelper;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@MapperScan("com.zhichenhaixin.certificatemanage.dao")
@ComponentScan
@EnableScheduling //定时器启动注解
@EnableAsync   //线程池注解
public class MyApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		//System.setProperty("java.io.tmpdir", "D:\\log\\");
		SpringApplication.run(MyApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MyApplication.class);
	}
	/**
	 * 配置mybatis的分页插件pageHelper
	 * @return
	 */
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        //配置数据库的方言  如：mysql
        //<!-- 设置数据库类型 Oracle,Mysql,MariaDB,SQLite,Hsqldb,PostgreSQL六种数据库-->
        properties.setProperty("dialect","postgresql");    
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
