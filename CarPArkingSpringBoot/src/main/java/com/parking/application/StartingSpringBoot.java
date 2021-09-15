package com.parking.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartingSpringBoot
{
	
	
	@GetMapping("/firstStep")
	public String firstStep()
	{
		return "Started First Springboot Test";
	}
	
	@RequestMapping("secondStep")
	public String secondStep()
	{
		return "My seocnd page";
	}
	
	

}
