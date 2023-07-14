package com.example.msavaliacaocredito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsavaliacaoCreditoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsavaliacaoCreditoApplication.class, args);
	}

}
