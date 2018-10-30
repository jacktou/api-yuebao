package com.eyee.apiyuebao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiYuebaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiYuebaoApplication.class, args);
	}

	@RequestMapping("/api/test")
	public String testDemo(){return "hello world";}
}
