package de.ollie.disym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Application starter class (service only).
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("de.ollie.disym")
public class DiSyMApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiSyMApplication.class, args);
	}

}