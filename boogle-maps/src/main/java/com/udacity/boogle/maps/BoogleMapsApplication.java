package com.udacity.boogle.maps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Launches a Spring Boot application for the boogle maps API,
 * launches web clients to be consumed for Vehicles API.
 */
@SpringBootApplication
public class BoogleMapsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoogleMapsApplication.class, args);
	}

}
