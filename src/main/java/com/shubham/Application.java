package com.shubham;

import com.shubham.beandefinition.Dog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class Application {

    private final Dog dog;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            log.info("Dog: {}", dog);
        };
    }
}
