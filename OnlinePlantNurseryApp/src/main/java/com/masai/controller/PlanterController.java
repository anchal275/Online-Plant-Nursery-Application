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
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.PlanterException;
import com.masai.model.Planter;
import com.masai.service.PlanterService;

@RestController
@RequestMapping("/planter")
public class PlanterController {
	
	@Autowired
	private PlanterService planterService;
	
	@PostMapping("/save/{aid}")
	public ResponseEntity<Planter> savePlanterHandler(@Valid @PathVariable("aid") String adminId, @RequestBody Planter planter) throws PlanterException, AdminException{
		
		Planter savedPlanter = planterService.addPlanter(planter, adminId);
		
		return new ResponseEntity<Planter>(savedPlanter, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update/{aid}")
	public ResponseEntity<Planter> updatePlanterHandler(@Valid @PathVariable("aid") String adminId, @RequestBody Planter planter) throws PlanterException, AdminException{
		
		Planter updatedPlanter = planterService.updatePlanter(planter, adminId);
		
		return new ResponseEntity<Planter>(updatedPlanter, HttpStatus.ACCEPTED);
		
	}
	
	
	@DeleteMapping("/delete/{aid}/{pid}")
	public ResponseEntity<Planter> deletePlanterHandler(@Valid @PathVariable("aid") String adminId, @PathVariable("pid") Integer pid) throws PlanterException, AdminException{
		
		Planter deletedPlanter = planterService.deletePlanter(pid, adminId);
		
		return new ResponseEntity<Planter>(deletedPlanter, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/byId/{pid}")
	public ResponseEntity<Planter> getPlanterByIdHandler(@Valid @PathVariable("pid") Integer pid) throws PlanterException{
		
		Planter planter = planterService.viewPlanter(pid);
		
		return new ResponseEntity<Planter>(planter, HttpStatus.OK);
		
	}
	
	@GetMapping("/byShape/{shape}")
	public ResponseEntity<List<Planter>> getPlanterByShapeHandler(@Valid @PathVariable("shape") String shape) throws PlanterException{
		
		List<Planter> planters = planterService.viewPlanter(shape);
		
		return new ResponseEntity<List<Planter>>(planters, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/allplanter")
	public ResponseEntity<List<Planter>> getAllPlanterHandler() throws PlanterException{
		
		List<Planter> planters = planterService.viewAllPlanters();
		
		return new ResponseEntity<List<Planter>>(planters, HttpStatus.OK);
		
	}
	
	@GetMapping("/betweenCost/{min}/{max}")
    public ResponseEntity<List<Planter>> getAllPlanterByCostHandler(@Valid @PathVariable("min") double min, @PathVariable("max") double max) throws PlanterException{
		
		List<Planter> planters = planterService.viewAllPlanters(min, max);
		
		return new ResponseEntity<List<Planter>>(planters, HttpStatus.OK);
		
	}
	
	
	
	
}
