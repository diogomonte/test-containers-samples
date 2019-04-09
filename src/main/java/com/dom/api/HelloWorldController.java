package com.dom.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class HelloWorldController {

	private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

	@Value("${service.url}")
	private String serviceUrl;
	@Value("${service.port}")
	private String servicePort;

	@GetMapping("/")
	public ResponseEntity works() {
		return ResponseEntity.ok("It works!");
	}

	@GetMapping("/hello")
	public ResponseEntity hello() {
		return ResponseEntity.ok("Hello world!");
	}

	@GetMapping("/health")
	public ResponseEntity health() throws IOException {
		logger.info("Service url: " + serviceUrl + " - port: " + servicePort);

		URL url = new URL("http://" + serviceUrl + ":" + servicePort + "/");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		connection.disconnect();

		return ResponseEntity.status(connection.getResponseCode()).body(content.toString());
	}

}
