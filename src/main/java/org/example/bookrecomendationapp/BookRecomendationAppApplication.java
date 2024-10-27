package org.example.bookrecomendationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class BookRecomendationAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookRecomendationAppApplication.class, args);
    }

}
