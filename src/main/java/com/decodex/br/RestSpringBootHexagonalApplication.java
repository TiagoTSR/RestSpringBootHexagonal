package com.decodex.br;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class }
)
public class RestSpringBootHexagonalApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestSpringBootHexagonalApplication.class, args);
	}

}
