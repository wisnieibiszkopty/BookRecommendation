package org.example.bookrecomendationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// TODO
/*
    1. Check validation
    2. Optimize queries
    3. Handle exception in better way
    4. Test everything
    5. Made frontend

    6. Add categories (tags?)
    7. Friends requests
    8. Search for popular books
    9. Filtering books
    10. Getting personalized recommendations
    11. Chat bot?

 */

@EnableTransactionManagement
@SpringBootApplication
public class BookRecomendationAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookRecomendationAppApplication.class, args);
    }

}
