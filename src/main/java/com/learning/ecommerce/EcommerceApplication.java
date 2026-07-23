package com.learning.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the application.
 *
 * This class is intentionally kept THIN — its only job is to bootstrap
 * the Spring container. Business logic never lives here.
 *
 * Placement matters: this class sits at the ROOT of our package tree
 * (com.learning.ecommerce). @SpringBootApplication's component scan
 * defaults to "this package and everything below it" — so if we buried
 * this class inside, say, com.learning.ecommerce.controller, Spring
 * would never discover our repository/, service/, entity/ packages.
 *
 * @SpringBootApplication — what it actually is
 * It's not one annotation, it's a meta-annotation — a convenience wrapper composed of three:
 *
 * Annotation	Role
 * @SpringBootConfiguration A specialization of @Configuration. Marks this class as a source of bean definitions.
 * @EnableAutoConfiguration The magic one — tells Spring Boot to scan the classpath and auto-configure beans based on what's present (e.g., sees spring-boot-starter-data-jpa + a DataSource class → auto-configures EntityManagerFactory, TransactionManager, etc.)
 * @ComponentScan Tells Spring to scan this package (and sub-packages) for @Component, @Service, @Repository, @Controller, etc., and register them as beans.
 */
@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {

		// This single line does an enormous amount of work internally —
		// see "What SpringApplication.run() actually does" below.
		/**
		 * What SpringApplication.run() actually does, step by step
		 * 1. Determines application type — web (Servlet), reactive, or none — based on classpath (it sees spring-boot-starter-web → picks Servlet-based AnnotationConfigServletWebServerApplicationContext).
		 * 2. Prepares the Environment — loads application.yml/.properties, active profiles, env vars, command-line args, in that precedence order.
		 * 3. Creates the ApplicationContext (the IoC container) — this is the object that owns and manages every bean's lifecycle.
		 * 4. Runs auto-configuration — reads META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports (Spring Boot 3.x location; it was spring.factories pre-2.7) and conditionally registers beans using @ConditionalOnClass, @ConditionalOnMissingBean, @ConditionalOnProperty, etc. This is why adding spring-boot-starter-data-jpa to pom.xml alone starts configuring JPA infrastructure — the auto-configuration classes detect the JPA/Hibernate classes on the classpath.
		 * 5. Refreshes the context — instantiates all singleton beans, injects dependencies, runs @PostConstruct callbacks.
		 * 6. Starts the embedded server (Tomcat, since we're on spring-boot-starter-web) and binds it to a port.
		 * 7. Publishes ApplicationReadyEvent — app is now live.
		 * **/
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
