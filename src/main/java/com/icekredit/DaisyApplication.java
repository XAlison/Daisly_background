package com.icekredit;

import com.icekredit.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan
@ComponentScan
public class DaisyApplication {
	private Logger logger = LoggerFactory.getLogger(DaisyApplication.class);


	public static void main(String[] args) {
		final ApplicationContext applicationContext = SpringApplication.run(DaisyApplication.class, args);
		SpringContextHolder.setApplicationContext(applicationContext);
	}
}
