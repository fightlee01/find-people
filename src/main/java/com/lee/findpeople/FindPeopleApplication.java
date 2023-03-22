package com.lee.findpeople;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = "com.lee")
@EnableSwagger2
public class FindPeopleApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindPeopleApplication.class, args);
	}

}
