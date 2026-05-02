package com.decodex.br;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class }
)
@EnableScheduling
public class RestSpringBootHexagonalApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestSpringBootHexagonalApplication.class, args);
	}

}
