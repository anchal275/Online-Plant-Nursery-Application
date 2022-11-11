package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.LoginException;
import com.masai.exceptions.PlantException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Plant;
import com.masai.repository.CurrentUserSessionRepo;
import com.masai.repository.PlantRepo;


@Service
public class PlantServiceImpl implements PlantService {
    
	@Autowired
	private PlantRepo pRepo;
	
	@Autowired
	private CurrentUserSessionRepo cRepo;
	
	@Override
	public Plant addPlant(Plant plant,String Key) throws PlantException,LoginException {
		if(Key.equals("admin")) {
			Plant savePlant = pRepo.save(plant);
			if(savePlant==null) {
				throw new PlantException("Plant Not Saved");
			}else {
				return savePlant;
			}	
		}else {
			throw new LoginException("Please Enter the Correct Admin Key");
		}
		
		
		
		
	}

	@Override
	public Plant updatePlant(Plant plant, String Key) throws PlantException,LoginException {
        if(Key.equals("admin")) {
        	Integer plantId = plant.getPlantId();
    		
    		Optional<Plant> find = pRepo.findById(plantId);	
    		
    		if(find.isPresent()) {
    			return pRepo.save(plant);
    		}
    		else 
    			throw new PlantException("No Such Plant Exist, can't Update");	
        }else {
        	throw new LoginException("Please Enter the Correct Admin Key");
        }
		
		
	}

	@Override
	public Plant deletePlant(Integer plantId, String Key) throws PlantException,LoginException {
        if(Key.equals("admin")) {
        	Optional<Plant> find = pRepo.findById(plantId);		
    		
    		if(find.isPresent()) {
    			Plant plant = find.get();
    			pRepo.delete(plant);
    			return plant;
    		}
    		else
    			throw  new PlantException("No Such Plant Exist with this PlantID, can't Delete");	
        }else {
        	throw new LoginException("Please Enter the Correct Admin Key");
        }
		
		
		
	}

	@Override
	public Plant viewPlant(Integer plantId,String Key) throws PlantException,LoginException {
		CurrentUserSession loggedInUser = cRepo.findByKey(Key);
		
		if(loggedInUser!=null || Key.equals("admin")) {
			Optional<Plant> find = pRepo.findById(plantId);		
			
			return find.orElseThrow( ()-> new PlantException(
			"No Such Plant Exist with this PlantID : "+plantId));	
		}else {
			throw new LoginException("please provide a valid Key to view Plant");
		}
		

	}

	@Override
	public List<Plant> viewPlant(String commonName,String Key) throws PlantException,LoginException {
		CurrentUserSession loggedInUser = cRepo.findByKey(Key); 
		
		if(loggedInUser!=null || Key.equals("admin")) {
			List<Plant> plantListByCommonName = pRepo.findByCommonName(commonName);
			
	        if(plantListByCommonName.size()==0) {
	        	throw  new PlantException("No Such Plant Exist with this Common Name ");
	        }else {
	        	return plantListByCommonName;
	        }	
		}else {
			throw new LoginException("please provide a valid Key to view Plant by common name");
		}
		
	
	}

	@Override
	public List<Plant> viewAllPlants(String Key) throws PlantException,LoginException {
	   CurrentUserSession loggedInUser = cRepo.findByKey(Key);
	
	   if(loggedInUser!=null || Key.equals("admin")) {
		   List<Plant> ListOfAllPlants = pRepo.findAll();
	       if(ListOfAllPlants.size()==0) {
	       	throw  new PlantException("Sorry Currently No Plants Exists");
	       }else {
	       	return ListOfAllPlants;
	       }   
	   }else {
		   throw new LoginException("please provide a valid Key to view Plants");
	   }
		
	
		
	}

	@Override
	public List<Plant> viewAllPlants(String typeOfPlant,String Key) throws PlantException,LoginException {
		CurrentUserSession loggedInUser = cRepo.findByKey(Key);

		if(loggedInUser!=null || Key.equals("admin")) {
			List<Plant> allPlantsByPlantType = pRepo.findByTypeOfPlant(typeOfPlant);
	        if(allPlantsByPlantType.size()==0) {
	          throw  new PlantException("No Plants Exists with this Plant Type :"+typeOfPlant);
	        }else {
	          return allPlantsByPlantType;
	        }	
		}else {
			throw new LoginException("please provide a valid Key to view Plant Type");
		}
	}
	
	

}
