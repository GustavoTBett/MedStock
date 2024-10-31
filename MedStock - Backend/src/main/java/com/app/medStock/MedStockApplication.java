package com.app.medStock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableJpaRepositories(basePackages = {"com.app.medStock.repository"})
@EntityScan(basePackages = {"com.app.medStock.model"})
public class MedStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedStockApplication.class, args);
	}

        //swagger-ui (http://localhost:8080/swagger-ui/index.html#/)
}
