package com.jpa.basic.jpain10steps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JpaIn10StepsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaIn10StepsApplication.class, args);
	}

}

/**
 *
 * public static void main(String[] args) {
 * 		ApplicationContext context  = SpringApplication.run(JpaIn10StepsApplication.class, args);
 *
 * 		for (var bean: context.getBeanDefinitionNames()) {
 * 			System.out.println(bean);
 *                }* 	}
 */
