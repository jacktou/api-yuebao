package com.eyee.apiyuebao;

import com.eyee.apiyuebao.model.ResponseBase;
import com.eyee.apiyuebao.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping("/api/say/{name}")
	public String testSay(@PathVariable("name") String name){

		return "hello:"+name;

	}

	@RequestMapping("/api/user/{id}")
	public ResponseBase<User> getUser(@PathVariable("id") String id){

		return ResponseBase.succeeded().setData(User.builder().id(id).bulid());
	}



}
