package com.meowricio.kafkaproject;

import com.meowricio.kafkaproject.producer.KafkaProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(KafkaProducer kafkaProducer) {
		return args -> {
			kafkaProducer.send("meow", "hello kafka");
			kafkaProducer.send("meow", "hello meow");
			kafkaProducer.send("meow", "meow");
		};
	}
}
