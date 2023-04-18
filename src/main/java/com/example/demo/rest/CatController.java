package com.example.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatController {

	@GetMapping("/")
	public String greeting() {
		return "Hello World!";
	}

}
