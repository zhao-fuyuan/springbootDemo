package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients(basePackages = {"com.example.demo"})
public class DemoApplication {

	public static void main(String[] args) {
		System.setProperty("es.set.netty.runtime.available.processors", "false");
		System.setProperty("io.netty.allocator.type", "unpooled");
		SpringApplication.run(DemoApplication.class, args);
//		SpringApplication app = new SpringApplication(DemoApplication.class);
//		app.setRegisterShutdownHook(false);
//		app.run(args);
	}

}
