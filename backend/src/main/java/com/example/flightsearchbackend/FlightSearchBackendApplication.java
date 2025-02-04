package com.example.flightsearchbackend;

import com.example.flightsearchbackend.config.AmadeusConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties(AmadeusConfig.class)
@ComponentScan(basePackages = "com.example.flightsearchbackend")
public class FlightSearchBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightSearchBackendApplication.class, args);
	}

}
