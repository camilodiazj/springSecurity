package com.sophos.serviciooauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicioOauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioOauthApplication.class, args);
	}

}
