package com.wasanco.productmanagement.productmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductmanagementApplication.class, args);
	}

}
