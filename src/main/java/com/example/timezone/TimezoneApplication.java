package com.example.timezone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TimezoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimezoneApplication.class, args);
	}

}
