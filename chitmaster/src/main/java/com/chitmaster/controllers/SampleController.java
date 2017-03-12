package com.chitmaster.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@GetMapping("/chitmaster/getname")
	public String getName(){
		
		return "Vishal-Shruthi.";
	}
	
}
