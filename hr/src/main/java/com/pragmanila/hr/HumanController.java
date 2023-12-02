package com.pragmanila.hr;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class HumanController {
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();


	@GetMapping("/humans")
	public Human getAllHumans(){
		return new Human(counter.incrementAndGet(),"First multiple testing", "Last multiple testing");
	}

	@GetMapping("/humans/{id}")
	public Human getHumanBasedOnId(@PathVariable Long id){
		return new Human(id,"First name testing", "Last name testing");
	}


}