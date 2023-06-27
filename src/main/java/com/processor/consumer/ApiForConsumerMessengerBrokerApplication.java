package com.processor.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiForConsumerMessengerBrokerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiForConsumerMessengerBrokerApplication.class, args);
	}

}
