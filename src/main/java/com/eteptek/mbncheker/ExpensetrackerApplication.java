package com.eteptek.mbncheker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import com.eteptek.mbncheker.rest.repo.RestClient;

@SpringBootApplication
public class ExpensetrackerApplication {
	
	public ExpensetrackerApplication(RestTemplateBuilder restBuilder) {
		restBuilder.build();
		new RestClient(restBuilder);
	}
	public static void main(String[] args) {
		SpringApplication.run(ExpensetrackerApplication.class, args);
		
	}

}
