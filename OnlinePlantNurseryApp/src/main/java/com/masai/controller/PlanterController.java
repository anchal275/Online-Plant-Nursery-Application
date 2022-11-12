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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.LoginException;
import com.masai.exceptions.PlanterException;
import com.masai.model.Planter;
import com.masai.service.PlanterService;

@RestController
@RequestMapping("/planter")
public class PlanterController {
	
	@Autowired
	private PlanterService planterService;
	
	@PostMapping("/save")
	public ResponseEntity<Planter> savePlanterHandler ( @Valid  @RequestBody Planter planter, @RequestParam String adminId) throws PlanterException, LoginException{
		
		Planter savedPlanter = planterService.addPlanter(planter, adminId);
		
		return new ResponseEntity<Planter>(savedPlanter, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Planter> updatePlanterHandler(@Valid @RequestParam String adminId, @RequestBody Planter planter) throws PlanterException, LoginException{
		
		Planter updatedPlanter = planterService.updatePlanter(planter, adminId);
		
		return new ResponseEntity<Planter>(updatedPlanter, HttpStatus.ACCEPTED);
		
	}
	
	
	@DeleteMapping("/delete")
	public ResponseEntity<Planter> deletePlanterHandler(@Valid @RequestParam("aid") String adminId, @RequestParam Integer pid) throws PlanterException, LoginException{
		
		Planter deletedPlanter = planterService.deletePlanter(pid, adminId);
		
		return new ResponseEntity<Planter>(deletedPlanter, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/byId/{pid}")
	public ResponseEntity<Planter> getPlanterByIdHandler(@PathVariable Integer pid, @RequestParam String key) throws PlanterException, LoginException{
		
		Planter planter = planterService.viewPlanter(pid, key);
		
		return new ResponseEntity<Planter>(planter, HttpStatus.OK);
		
	}
	
	@GetMapping("/byShape/{shape}")
	public ResponseEntity<List<Planter>> getPlanterByShapeHandler( @PathVariable String shape, @RequestParam String key) throws PlanterException, LoginException{
		
		List<Planter> planters = planterService.viewPlanter(shape, key);
		
		return new ResponseEntity<List<Planter>>(planters, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/allplanter")
	public ResponseEntity<List<Planter>> getAllPlanterHandler(@RequestParam String key) throws PlanterException, LoginException{
		
		List<Planter> planters = planterService.viewAllPlanters(key);
		
		return new ResponseEntity<List<Planter>>(planters, HttpStatus.OK);
		
	}
	
	@GetMapping("/betweenCost/{min}/{max}")
    public ResponseEntity<List<Planter>> getAllPlanterByCostHandler( @PathVariable double min, @PathVariable double max, @RequestParam String key) throws PlanterException, LoginException{
		
		List<Planter> planters = planterService.viewAllPlanters(min, max, key);
		
		return new ResponseEntity<List<Planter>>(planters, HttpStatus.OK);
		
	}
	
	
	
	
}
