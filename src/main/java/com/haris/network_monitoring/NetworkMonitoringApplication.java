package com.haris.network_monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NetworkMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetworkMonitoringApplication.class, args);
	}

}
