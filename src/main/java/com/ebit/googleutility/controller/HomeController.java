package com.ebit.googleutility.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ebit.googleutility.service.GoogleService;

@RestController
public class HomeController {

	@Autowired
	GoogleService googleService;
	
	@GetMapping("/health")
	String getHealth() {
		return "OK";
	}
	
	@GetMapping("/readFiles")
	String readGoogleDriveFiles(@RequestParam String filename) throws Exception {
		return googleService.readGoogleDrive(filename);
	}
}
