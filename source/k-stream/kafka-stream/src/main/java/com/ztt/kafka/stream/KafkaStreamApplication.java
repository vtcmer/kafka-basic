package com.ztt.kafka.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaStreamApplication {

	// https://www.vinsguru.com/kafka-streams-real-time-data-processing-using-spring-boot/

	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamApplication.class, args);
	}

}
