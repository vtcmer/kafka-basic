package com.ztt.developer.example1;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableKafka
@EnableScheduling
public class Example1Application {

	public static void main(String[] args) {
		SpringApplication.run(Example1Application.class, args);
	}

	@Bean
	NewTopic livestreamsTopic() {
		return new NewTopic("livestreams", 3, (short) 3);
	}

}

