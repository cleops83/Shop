package com.shop.application;

import com.shop.repositories.CategoryRepository;
import com.shop.repositories.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.shop"} )
@EntityScan( basePackages = {"com.shop"} )
@EnableJpaRepositories(basePackageClasses = {CategoryRepository.class, ProductRepository.class})
public class ReactApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactApplication.class, args);
    }
}
