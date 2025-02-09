package com.venu;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class SpringBootApp {

    @Autowired
    FunctionalInt fit;

    @RequestMapping("/")
    String home() {


        System.out.println(fit.intMethod("test"));
        return "Hello World!";
    }

    @Bean
    public FunctionalInt functionAsService() {
            return String::toUpperCase;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootApp.class, args);
    }

}