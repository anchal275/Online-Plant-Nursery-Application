package com.masai.service;

import java.util.List;

import com.masai.exceptions.LoginException;
import com.masai.exceptions.SeedException;
import com.masai.model.Seed;

public interface SeedService {
	
	 public Seed addSeed(Seed seed,String Key) throws SeedException,LoginException;
		
     public Seed updateSeed(Seed seed,String Key) throws SeedException,LoginException;
		
     public Seed deleteSeed(Integer seedId,String Key) throws SeedException,LoginException;
		
     public Seed viewSeed(int seedId,String Key) throws SeedException,LoginException;
		
     public List<Seed> viewSeed(String commonName,String Key) throws SeedException,LoginException;
		
     public List<Seed> viewAllSeeds(String Key) throws SeedException,LoginException;
     
     public List<Seed> viewAllSeeds(String typeOfSeed,String Key) throws SeedException,LoginException;
}
