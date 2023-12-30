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
	public ResponseEntity<HumanResponseStructure> getAllHumans(){
		try{
			HumanResponseStructure data;
			List<Human> listOfHuman = new ArrayList<Human>();
			humanRepository.findAll().forEach(listOfHuman::add);

			data = new HumanResponseStructure(listOfHuman.toArray(new Human[0]), listOfHuman.toArray(new Human[0]).length);
			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/human/{id}")
	public ResponseEntity<HumanResponseStructure> getHumanBasedOnId(@PathVariable Long id){
		HumanResponseStructure data = null;
		
		Human human = humanRepository.findById(id).orElse(null);

		if (human != null) {
			data = new HumanResponseStructure(new Human[]{human}, 1);
		} else {
			data = new HumanResponseStructure(new Human[0], 0);
		}
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@PostMapping("/human")
	public ResponseEntity<Human> registerHuman(@RequestBody Human human){
		try {
			Human _human = humanRepository.save(new Human(human.getFirst_name(), human.getLast_name()));
			return new ResponseEntity<>(_human, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/human/{id}")
	public ResponseEntity<ResponseStructure> updateHuman(@PathVariable Long id, @RequestBody Human human){
		
		Human currentHuman = humanRepository.findById(id).orElse(null);

		if (currentHuman != null) {
			humanRepository.save(human);
			// data.put("message", "Update Successful.");
			return new ResponseEntity<>(new ResponseStructure("200", "Update Successful."), HttpStatus.OK);
		} else {
			// data.put("message", "Update Successful.");
			return new ResponseEntity<>(new ResponseStructure("400", "No Record exist found."), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/human/{id}")
	public ResponseEntity<ResponseStructure> deleteHuman(@PathVariable Long id){
		Human currentHuman = humanRepository.findById(id).orElse(null);

		if (currentHuman != null) {
			humanRepository.deleteById(id);
			return new ResponseEntity<>(new ResponseStructure("200", "Deleted Successful."), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseStructure("400", "No Record exist found."), HttpStatus.BAD_REQUEST);
		}
	}
}