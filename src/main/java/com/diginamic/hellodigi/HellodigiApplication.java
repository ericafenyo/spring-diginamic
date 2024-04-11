package com.diginamic.hellodigi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class HellodigiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HellodigiApplication.class, args);
	}

}
