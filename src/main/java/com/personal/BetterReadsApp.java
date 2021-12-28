package com.personal;

import java.nio.file.Path;

import com.personal.connection.DataStaxAstraProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Main application class with main method that runs the Spring Boot app
 */

@SpringBootApplication
@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class BetterReadsApp {

	public static void main(String[] args) {
		SpringApplication.run(BetterReadsApp.class, args);
	}

	// @RequestMapping("/user")
	// public String user(@AuthenticationPrincipal OAuth2User principal) {
	// 	System.out.println(principal);
	// 	return principal.getAttribute("name");
	// }

	 /**
     * This is necessary to have the Spring Boot app use the Astra secure bundle 
     * to connect to the database
     */

	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
		Path bundle = astraProperties.getSecureConnectBundle().toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle);
	}

}
