package com.udacity.pricing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Creates a Spring Boot Application to run the Pricing Service.
 * TODO: Convert the application from a REST API to a microservice.
 */

// TODO: 18/01/2020 add documentation
@SpringBootApplication
@EnableEurekaClient
public class PricingServiceApplication {
    /**
     * Initializes the price api available to the Vehicle API consults.
     * it will be registered on EurekaServer see application.properties.
     */
    public static void main(String[] args) {
        SpringApplication.run(PricingServiceApplication.class, args);
    }

    /**
     * Creates the RestTemplate to provide create, read, update and delete resources
     * and gives suport for HTTP verbs GET, POST, PUT and DELETE
     * @param builder
     * @return the restTemplate
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {return builder.build(); }

}
