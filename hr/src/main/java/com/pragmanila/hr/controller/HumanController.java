package com.pragmanila.hr.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.pragmanila.hr.model.Human;
import com.pragmanila.hr.repository.HumanRepository;
import com.pragmanila.hr.model.HumanResponseStructure;
import com.pragmanila.hr.model.ResponseStructure;

@RestController
@RequestMapping("/api")
public class HumanController {
		
	@Autowired
	private HumanRepository humanRepository;

	@GetMapping("/humans")
	public HumanResponseStructure getAllHumans(){
		HumanResponseStructure data;
		
		List<Human> listOfHuman = new ArrayList<Human>();
        humanRepository.findAll().forEach(listOfHuman::add);

		data = new HumanResponseStructure(listOfHuman.toArray(new Human[0]), listOfHuman.toArray(new Human[0]).length);
		return data;
	}

	// @GetMapping("/human/{id}")
	// public HumanResponseStructure getHumanBasedOnId(@PathVariable Long id){
	// 	HumanResponseStructure data = null;
		
	// 	int index = findHuman(id);

	// 	if (index != -1) {
	// 		//data.put("data", listOfHuman.get(index));
	// 		//data.put("Total", 1);
	// 		data = new HumanResponseStructure(new Human[]{listOfHuman.get(index)}, 1);
	// 	} else {
	// 		data = new HumanResponseStructure(new Human[0], 0);
	// 	}
	// 	return data;
	// }

	@PostMapping("/human")
	public ResponseEntity<Human> registerHuman(@RequestBody Human human){
		try {
			Human _human = humanRepository.save(new Human(human.getFirst_name(), human.getLast_name()));
			return new ResponseEntity<>(_human, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// @PutMapping("/human/{id}")
	// public ResponseStructure updateHuman(@PathVariable Long id, @RequestBody Human human){
	// 	System.out.println(human);
		
	// 	int index = findHuman(id);

	// 	ResponseStructure data = null;
		
	// 	if (index != -1) {
	// 		listOfHuman.set(index, human);
	// 		// data.put("message", "Update Successful.");
	// 		return new ResponseStructure("200", "Update Successful.");
	// 	} else {
	// 		// data.put("message", "Update Successful.");
	// 		return new ResponseStructure("404", "Record not found.");
	// 	}
	// }

	// @DeleteMapping("/human/{id}")
	// public ResponseStructure deleteHuman(@PathVariable Long id){
	// 	int index = findHuman(id);

	// 	if (index < 0)
	// 	{
	// 		return new ResponseStructure("404", "Failed to delete. No ID Found");
	// 	}

	// 	boolean removed = listOfHuman.remove(listOfHuman.get(index));
    //     if (removed) {
	// 		return new ResponseStructure("200", "Object deleted successfully.");
    //     } else {
 	// 		return new ResponseStructure("404", "Object not found in the list.");
    //     }
	// }
}