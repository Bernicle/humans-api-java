package com.pragmanila.hr.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.pragmanila.hr.model.Human;
import com.pragmanila.hr.model.HumanResponseStructure;

@RestController
public class HumanController {
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	private static List<Human> listOfHuman = new ArrayList<>();
	private int findHuman(Long id){
		for (Human h : listOfHuman) {
			if (h.id() == id){
				return listOfHuman.indexOf(h);
			}
		}
		return -1;
	}

	@GetMapping("/humans")
	public HumanResponseStructure getAllHumans(){
		HumanResponseStructure data;
		
		Human[] arrayHuman = listOfHuman.toArray(new Human[0]);
		data = new HumanResponseStructure(arrayHuman, arrayHuman.length);
		return data;
	}

	@GetMapping("/human/{id}")
	public Map<String, Object> getHumanBasedOnId(@PathVariable Long id){
		Map<String, Object> data = new HashMap<>();
		
		Human arrayHuman;

		int index = findHuman(id);

		if (index != -1) {
			data.put("data", listOfHuman.get(index));
			data.put("Total", 1);
		} else {
			data.put("data", new Human[0]);
			data.put("Total", 0);
		}

		return data;
	}

	@PostMapping("/human")
	public Map<String, Object> registerHuman(@RequestBody Human human){
		listOfHuman.add(human);
		
		Map<String, Object> data = new HashMap<>();
		
		data.put("message", "Registration Successful.");
		data.put("TotalRegistrant", listOfHuman.size());

		return data;
	}

	@PutMapping("/human/{id}")
	public Map<String, Object> updateHuman(@PathVariable Long id, @RequestBody Human human){
		System.out.println(human);
		
		int index = findHuman(id);

		Map<String, Object> data = new HashMap<>();
		
		if (index != -1) {
			listOfHuman.set(index, human);
			data.put("message", "Update Successful.");
		} else {
			data.put("message", "Update Successful.");
		}

		data.put("TotalRegistrant", listOfHuman.size());

		return data;
	}

	@DeleteMapping("/human/{id}")
	public void deleteHuman(@PathVariable Long id){
		int index = findHuman(id);

		if (index < 0)
		{
			System.out.println("Failed to delete. No ID Found");
			return;
		}

		boolean removed = listOfHuman.remove(listOfHuman.get(index));
        if (removed) {
            System.out.println("Object deleted successfully.");
        } else {
            System.out.println("Object not found in the list.");
        }
	}
}