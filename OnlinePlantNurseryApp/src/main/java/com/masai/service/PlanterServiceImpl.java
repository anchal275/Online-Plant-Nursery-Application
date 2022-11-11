package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.PlanterException;
import com.masai.model.Planter;
import com.masai.repository.PlanterDao;

public class PlanterServiceImpl implements PlanterService{
	
	@Autowired
	private PlanterDao planterRepo;
	
	
	@Override
	public Planter addPlanter(Planter planter, String key) throws PlanterException, AdminException {
		
		if(key.equals("admin")) {
			Planter savedPlanter = planterRepo.save(planter);
			
			if(savedPlanter != null)
				return savedPlanter;
			
			else
				throw new PlanterException("Could not aad planter give planter details properly");
		}
		else {
			throw new AdminException("Invalid admin id :"+key);
		}
			
	}

	@Override
	public Planter updatePlanter(Planter planter, String key) throws PlanterException, AdminException {
		
		if(key.equals("admin")) {
			Optional<Planter> opt = planterRepo.findById(planter.getPlanterId());
			
			if(opt.isPresent()) {
				Planter updatedPlanter = planterRepo.save(planter);
				return updatedPlanter;
			}
			throw new PlanterException("Invalid Planter Details!");
		}
		else {
			throw new AdminException("Invalid admin id :"+key);
		}
		
	}

	@Override
	public Planter deletePlanter(Integer planterId, String key) throws PlanterException, AdminException {
		if(key.equals("admin")){
			Optional<Planter> opt = planterRepo.findById(planterId);
			
			if(opt.isPresent()) {
				Planter deletedPlanter = opt.get();
				planterRepo.delete(deletedPlanter);
				return deletedPlanter;
			}
			else
				throw new PlanterException("Invalid Planter Id :"+planterId);
		}
		else {
			throw new AdminException("Invalid admin id :"+key);
		}
		
       
		
	}

	@Override
	public Planter viewPlanter(Integer planterId) throws PlanterException {
		Optional<Planter> opt = planterRepo.findById(planterId);
		
		if(opt.isPresent()) {
			Planter foundPlanter = opt.get();
			
			return foundPlanter;
		}
		throw new PlanterException("Invalid Planter Id :"+planterId);
	}

	@Override
	public List<Planter> viewPlanter(String planterShape) throws PlanterException {
		List<Planter> allPlanter = planterRepo.findByPlanterShape(planterShape);
		if(allPlanter.size() == 0)
			throw new PlanterException("Invalid shape!");
		else
			return allPlanter;
	}

	@Override
	public List<Planter> viewAllPlanters() throws PlanterException {
		
		List<Planter> allPlanters = planterRepo.findAll();
		
		if(allPlanters.size() == 0)
			throw new PlanterException("Not any planter found");
		else
			return allPlanters;
		
	}

	@Override
	public List<Planter> viewAllPlanters(double minCost, double maxCost) throws PlanterException {
		List<Planter> allPlanters = planterRepo.findByPlanterCostBetween(minCost, maxCost);
		
		if(allPlanters.size() == 0)
			throw new PlanterException("Not any planter found");
		else
			return allPlanters;
	}
}
