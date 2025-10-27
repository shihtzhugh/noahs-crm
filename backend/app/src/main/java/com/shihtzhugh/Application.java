package com.shihtzhugh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.out.println("DB URL: " + System.getenv("DATABASE_URL"));
        System.out.println("DB USER: " + System.getenv("DATABASE_USERNAME"));
        SpringApplication.run(Application.class, args);
    }
}
