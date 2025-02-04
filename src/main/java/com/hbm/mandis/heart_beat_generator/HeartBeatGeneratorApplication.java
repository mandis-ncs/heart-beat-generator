package com.hbm.mandis.heart_beat_generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HeartBeatGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeartBeatGeneratorApplication.class, args);
	}

}
