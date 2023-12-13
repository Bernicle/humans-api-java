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
import com.pragmanila.hr.model.ResponseStructure;

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
	public HumanResponseStructure getHumanBasedOnId(@PathVariable Long id){
		HumanResponseStructure data = null;
		
		int index = findHuman(id);

		if (index != -1) {
			//data.put("data", listOfHuman.get(index));
			//data.put("Total", 1);
			data = new HumanResponseStructure(new Human[]{listOfHuman.get(index)}, 1);
		} else {
			data = new HumanResponseStructure(new Human[0], 0);
		}
		return data;
	}

	@PostMapping("/human")
	public ResponseStructure registerHuman(@RequestBody Human human){
		listOfHuman.add(human);
		
		return new ResponseStructure("success", "Registration Successful.");
	}
	
	@PutMapping("/human/{id}")
	public ResponseStructure updateHuman(@PathVariable Long id, @RequestBody Human human){
		System.out.println(human);
		
		int index = findHuman(id);

		ResponseStructure data = null;
		
		if (index != -1) {
			listOfHuman.set(index, human);
			// data.put("message", "Update Successful.");
			return new ResponseStructure("200", "Update Successful.");
		} else {
			// data.put("message", "Update Successful.");
			return new ResponseStructure("404", "Record not found.");
		}
	}

	@DeleteMapping("/human/{id}")
	public ResponseStructure deleteHuman(@PathVariable Long id){
		int index = findHuman(id);

		if (index < 0)
		{
			return new ResponseStructure("404", "Failed to delete. No ID Found");
		}

		boolean removed = listOfHuman.remove(listOfHuman.get(index));
        if (removed) {
			return new ResponseStructure("200", "Object deleted successfully.");
        } else {
 			return new ResponseStructure("404", "Object not found in the list.");
        }
	}
}