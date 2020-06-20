package dev.amrw.filmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * The Spring configuration class and the application's entry point.
 */
@SpringBootApplication
@PropertySource("classpath:omdb-api.properties")
public class Application {

    /**
     * The application's entry point.
     * @param args command-line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
