package com.masai.service;

import java.util.List;

import com.masai.exceptions.LoginException;
import com.masai.exceptions.PlantException;
import com.masai.model.Plant;
public interface PlantService {
	public Plant addPlant(Plant plant,String Key) throws PlantException,LoginException;

	public Plant updatePlant(Plant plant,String Key) throws PlantException,LoginException;

	public Plant deletePlant(Integer plantId,String Key) throws PlantException,LoginException;

	public Plant viewPlant(Integer plantId,String Key) throws PlantException,LoginException;

	public List<Plant> viewPlant(String commonName,String Key) throws PlantException,LoginException;

	public List<Plant> viewAllPlants(String Key) throws PlantException,LoginException;

	public List<Plant> viewAllPlants(String typeOfPlant,String Key) throws PlantException,LoginException;


}
