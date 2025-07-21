package com.deepakcode.hello;

import org.springframework.boot.SpringApplication;

public class TestHelloApplication {

	public static void main(String[] args) {
		SpringApplication.from(HelloApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
