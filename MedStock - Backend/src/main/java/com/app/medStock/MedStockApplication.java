package com.app.medStock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class MedStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedStockApplication.class, args);
	}

        //swagger-ui (http://localhost:8080/swagger-ui/index.html#/)
}
