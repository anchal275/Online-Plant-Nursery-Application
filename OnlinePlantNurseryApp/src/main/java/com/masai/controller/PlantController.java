package com.masai.controller;

import javax.validation.Valid;
import java.util.List;

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
import com.masai.exceptions.PlantException;
import com.masai.model.Plant;
import com.masai.service.PlantService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class PlantController {
	
	@Autowired
	private PlantService pService;
	
	
	@PostMapping("/plants")
	public ResponseEntity<Plant> addPlantHandler(@Valid @RequestBody Plant plant,
			@RequestParam("adminKey") String adminKey) throws PlantException, LoginException{
	Plant savePlant = pService.addPlant(plant, adminKey);
	return new ResponseEntity<Plant>(savePlant, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/plants")
	public ResponseEntity<Plant> updatePlantHandler( @Valid @RequestBody Plant plant,
			@RequestParam("adminKey") String adminKey) throws PlantException, LoginException{
		Plant updatePlant = pService.updatePlant(plant, adminKey);
		return new ResponseEntity<Plant>(updatePlant, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/plants/{plantId}")
	public ResponseEntity<Plant> deletePlantHandler(@PathVariable("plantId") Integer plantId,
			@RequestParam("adminKey") String adminKey) throws PlantException, LoginException{
		Plant deletePlant = pService.deletePlant(plantId, adminKey);
		return new ResponseEntity<Plant>(deletePlant, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/plants/{plantId}")
	public ResponseEntity<Plant> viewPlantHandler(@PathVariable("plantId") Integer plantId,
			@RequestParam("key") String key) throws PlantException, LoginException{
		Plant viewPlant = pService.viewPlant(plantId, key);
		return new ResponseEntity<Plant>(viewPlant, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/plantsByCommonName/{commonName}")
	public ResponseEntity<List<Plant>> viewPlantByCommonNameHandler(@PathVariable("commonName") String commonName,
			@RequestParam("key") String key) throws PlantException, LoginException{
		List<Plant> listPlantByName = pService.viewPlant(commonName, key);
		return new ResponseEntity<List<Plant>>(listPlantByName, HttpStatus.CREATED);
	}
	
	@GetMapping("plants")
	public ResponseEntity<List<Plant>> viewAllPlantsHandler(@RequestParam String key) throws PlantException, LoginException{
		List<Plant> listPlants = pService.viewAllPlants(key);
		return new ResponseEntity<List<Plant>>(listPlants, HttpStatus.CREATED);
	}
	
	@GetMapping("/plantsByType/{typeOfPlant}")
	public ResponseEntity<List<Plant>> viewPlantByType(@PathVariable("typeOfPlant") String typeOfPlant,
			@RequestParam("key") String key) throws PlantException, LoginException{
		List<Plant> listPlantByType = pService.viewAllPlants(typeOfPlant, key);
		return new ResponseEntity<List<Plant>>(listPlantByType, HttpStatus.CREATED);
	}
}
