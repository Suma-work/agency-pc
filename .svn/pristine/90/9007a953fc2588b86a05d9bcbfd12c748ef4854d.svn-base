package com.sumainfo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.sumainfo.modules.*.dao")
public class AgencyPcApplication extends SpringBootServletInitializer{
	/**
     * 如果要发布到自己的Tomcat中的时候，需要继承SpringBootServletInitializer类，并且增加如下的configure方法。
     * 如果不发布到自己的Tomcat中的时候，就无需上述的步骤
     */
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		// 注意这里要指向原先用main方法执行的Application启动类
		return application.sources(AgencyPcApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AgencyPcApplication.class, args);
	}

}
