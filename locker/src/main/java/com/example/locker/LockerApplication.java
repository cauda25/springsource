package com.example.locker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LockerApplication.class, args);
	}

}
