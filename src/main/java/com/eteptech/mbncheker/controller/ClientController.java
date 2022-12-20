package com.eteptech.mbncheker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class ClientController {
	
	@GetMapping("/home")
	public String homePage() {
		return "index";
	}

}
