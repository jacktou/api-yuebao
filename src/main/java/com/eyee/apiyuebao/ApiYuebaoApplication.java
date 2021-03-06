package com.eyee.apiyuebao;

import com.eyee.apiyuebao.filter.InterfaceFilter;
import com.eyee.apiyuebao.model.Ip;
import com.eyee.apiyuebao.model.ResponseBase;
import com.eyee.apiyuebao.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@RestController
@Slf4j
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
        log.info("uuuuuuuuu");
		return ResponseBase.succeeded().setData(User.builder().id(id).bulid());
	}

	@RequestMapping("/api/fulluser/{id}")
	public ResponseBase<User> getUserInfo(@PathVariable("id") String id, @RequestParam("name") String name){

		return ResponseBase.succeeded().setData(User.builder().id(id).name(name).bulid());
	}

	@GetMapping("/api/ip")
	public ResponseBase<Ip> getIp(HttpServletRequest httpServletRequest ){

		String ipAddress = httpServletRequest.getHeader("x-forwarded-for");
		if(StringUtils.isBlank(ipAddress)) {

			ipAddress = httpServletRequest.getRemoteAddr();
		}

		return ResponseBase.succeeded().setData(new Ip("network",ipAddress));
	}


}
