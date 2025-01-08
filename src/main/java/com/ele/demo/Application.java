package com.ele.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication is a convenience annotation that adds all of the following:
 * - @Configuration: Tags the class as a source of bean definitions for the application context.
 * - @EnableAutoConfiguration: Spring Boot attempts to automatically configure your Spring application based on the dependencies that you have added.
 * - @ComponentScan: Tells Spring to look for other components, configurations, and services. If specific packages are not defined, recursive scanning begins with the package of the class that declares the annotation.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
