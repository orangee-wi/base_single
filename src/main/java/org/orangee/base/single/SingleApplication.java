package org.orangee.base.single;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.orangee.base.single.mapper")
public class SingleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SingleApplication.class, args);
	}

}
