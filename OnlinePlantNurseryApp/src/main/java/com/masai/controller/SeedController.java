package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.LoginException;
import com.masai.exceptions.SeedException;
import com.masai.model.Seed;
import com.masai.service.SeedService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class SeedController {
	
	@Autowired
	private SeedService sService;
	
	@PostMapping("/seeds")
	public ResponseEntity<Seed> addSeedHandler(@Valid @RequestBody Seed seed,
			@RequestParam("adminKey") String adminKey) throws SeedException, LoginException{
	Seed saveSeed = sService.addSeed(seed, adminKey);
	return new ResponseEntity<Seed>(saveSeed, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/seeds")
	public ResponseEntity<Seed> updateSeedHandler(@Valid @RequestBody Seed seed,
			@RequestParam("adminKey") String adminKey) throws SeedException, LoginException{
		Seed updateSeed = sService.updateSeed(seed, adminKey);
		return new ResponseEntity<Seed>(updateSeed, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/seeds/{seedId}")
	public ResponseEntity<Seed> deleteSeedHandler(@PathVariable("seedId") Integer seedId,
			@RequestParam("adminKey") String adminKey) throws SeedException, LoginException{
		Seed deleteSeed = sService.deleteSeed(seedId, adminKey);
		return new ResponseEntity<Seed>(deleteSeed, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/seeds/{seedId}")
	public ResponseEntity<Seed> viewSeedHandler(@PathVariable("seedId") Integer seedId,
			@RequestParam("key") String key) throws SeedException, LoginException{
		Seed viewSeed = sService.viewSeed(seedId, key);
		return new ResponseEntity<Seed>(viewSeed, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/seedsByCommonName/{commonName}")
	public ResponseEntity<List<Seed>> viewSeedByCommonNameHandler(@PathVariable("commonName") String commonName,
			@RequestParam("key") String key) throws SeedException, LoginException{
		List<Seed> listSeedByName = sService.viewSeed(commonName, key);
		return new ResponseEntity<List<Seed>>(listSeedByName, HttpStatus.CREATED);
	}
	
	@GetMapping("seeds")
	public ResponseEntity<List<Seed>> viewAllSeedsHandler(@RequestParam("key") String key) throws SeedException, LoginException{
		List<Seed> listSeeds = sService.viewAllSeeds(key);
		return new ResponseEntity<List<Seed>>(listSeeds, HttpStatus.CREATED);
	}
	
	@GetMapping("/seedsByType/{typeOfSeeds}")
	public ResponseEntity<List<Seed>> viewSeedByType(@PathVariable("typeOfSeeds") String typeOfSeeds,
			@RequestParam("key") String key) throws SeedException, LoginException{
		List<Seed> listSeedByType = sService.viewAllSeeds(typeOfSeeds, key);
		return new ResponseEntity<List<Seed>>(listSeedByType, HttpStatus.CREATED);
	}
 	
	
}
