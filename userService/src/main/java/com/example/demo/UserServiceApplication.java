package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "User Service Application Using PostgeSQL",
				description = "User Service Application Documentation Using Swagger",
				version = "1.0",
				contact = @Contact(
						name = "Srinivas",
						email = "2583862@tcs.com",
						url = "https://www.srinivas.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.srinivas.com/license"
				)
		),
		externalDocs = @ExternalDocumentation(
					description = "Spring Boot Application External Documentation",
					url = "https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/"
				)
)
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
