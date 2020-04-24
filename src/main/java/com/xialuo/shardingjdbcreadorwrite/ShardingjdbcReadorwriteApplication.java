package com.xialuo.shardingjdbcreadorwrite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.xialuo.shardingjdbcreadorwrite.dao")
public class ShardingjdbcReadorwriteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingjdbcReadorwriteApplication.class, args);
	}

}
