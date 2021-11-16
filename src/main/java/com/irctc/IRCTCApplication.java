package com.irctc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class IRCTCApplication {

	private static final Logger logger = LoggerFactory.getLogger(IRCTCApplication.class);

	public static void main(String[] args) {
		logger.info("Starting IRCTCApplication................");
		SpringApplication.run(IRCTCApplication.class, args);

	}

}
