package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.PlantException;
import com.masai.model.Plant;
import com.masai.repository.PlantRepo;


@Service
public class PlantServiceImpl implements PlantService {
    
	@Autowired
	private PlantRepo pRepo;
	
	@Override
	public Plant addPlant(Plant plant) throws PlantException {
		Plant savePlant = pRepo.save(plant);
		if(savePlant==null) {
			throw new PlantException("Plant Not Saved");
		}else {
			return savePlant;
		}
		
	}

	@Override
	public Plant updatePlant(Plant plant) throws PlantException {
        Integer plantId = plant.getPlantId();
		
		Optional<Plant> find = pRepo.findById(plantId);	
		
		if(find.isPresent()) {
			return pRepo.save(plant);
		}
		else 
			throw new PlantException("No Such Plant Exist, can't Update");	
	}

	@Override
	public Plant deletePlant(Integer plantId) throws PlantException {
        Optional<Plant> find = pRepo.findById(plantId);		
		
		if(find.isPresent()) {
			Plant plant = find.get();
			pRepo.delete(plant);
			return plant;
		}
		else
			throw  new PlantException("No Such Plant Exist with this PlantID, can't Delete");
		
	}

	@Override
	public Plant viewPlant(Integer plantId) throws PlantException {
        Optional<Plant> find = pRepo.findById(plantId);		
		
		return find.orElseThrow( ()-> new PlantException(
		"No Such Plant Exist with this PlantID : "+plantId));
	}

	@Override
	public List<Plant> viewPlant(String commonName) throws PlantException {
        List<Plant> plantListByCommonName = pRepo.findByCommonName(commonName);
		
        if(plantListByCommonName.size()==0) {
        	throw  new PlantException("No Such Plant Exist with this Common Name ");
        }else {
        	return plantListByCommonName;
        }
	}

	@Override
	public List<Plant> viewAllPlants() throws PlantException {
       List<Plant> ListOfAllPlants = pRepo.findAll();
       if(ListOfAllPlants.size()==0) {
       	throw  new PlantException("Sorry Currently No Plants Exists");
       }else {
       	return ListOfAllPlants;
       }
		
	}

	@Override
	public List<Plant> viewAllPlants(String typeOfPlant) throws PlantException {
        List<Plant> allPlantsByPlantType = pRepo.findByTypeOfPlant(typeOfPlant);
        if(allPlantsByPlantType.size()==0) {
          throw  new PlantException("No Plants Exists with this Plant Type :"+typeOfPlant);
        }else {
          return allPlantsByPlantType;
        }
	}
	
	

}
