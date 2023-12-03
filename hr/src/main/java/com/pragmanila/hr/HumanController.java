package com.pragmanila.hr;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;


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
	public Human[] getAllHumans(){
		return listOfHuman.toArray(new Human[0]);
	}

	@GetMapping("/human/{id}")
	public Human getHumanBasedOnId(@PathVariable Long id){
		int index = findHuman(id);

		if (index != -1) {
			System.out.println("Human .");
			return listOfHuman.get(index);
		} else {
			System.out.println("Object not found in the list.");
			return null;
		}
	}

	@PostMapping("/human")
	public void registerHuman(@RequestBody Human human){
		listOfHuman.add(human);
		System.out.println("Registered.");
	}

	@PutMapping("/human/{id}")
	public void updateHuman(@PathVariable Long id, @RequestBody Human human){
		System.out.println(human);
		int index = findHuman(id);

		if (index != -1) {
			listOfHuman.set(index, human);
			System.out.println("Object updated successfully.");
		} else {
			System.out.println("Object not found in the list.");
		}
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