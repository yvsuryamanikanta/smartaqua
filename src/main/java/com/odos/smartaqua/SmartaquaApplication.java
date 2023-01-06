package com.odos.smartaqua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.odos.smartaqua.utils.FileStorageProperties;


@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
public class SmartaquaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartaquaApplication.class, args);
	}
}
