package com.dom.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("/")
	public ResponseEntity works() {
		return ResponseEntity.ok("It works!");
	}

	@GetMapping("/hello")
	public ResponseEntity hello() {
		return ResponseEntity.ok("Hello world!");
	}


}
