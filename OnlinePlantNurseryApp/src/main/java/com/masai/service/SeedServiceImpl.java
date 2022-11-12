package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.masai.exceptions.LoginException;
import com.masai.exceptions.SeedException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Seed;
import com.masai.repository.CurrentUserSessionRepo;
import com.masai.exceptions.SeedException;
import com.masai.model.Seed;
import com.masai.repository.SeedRepo;


@Service
public class SeedServiceImpl implements SeedService{

	@Autowired
	private SeedRepo sRepo;
	

	@Autowired
	private CurrentUserSessionRepo cRepo;
	
	@Override
	public Seed addSeed(Seed seed,String Key) throws SeedException,LoginException {
		if(Key.equals("admin")) {
			Seed saveSeed = sRepo.save(seed);
			if(saveSeed==null) {
				throw new SeedException("Seed Not Saved");
			}else {
				return saveSeed;
			}	
		}else {
			throw new LoginException("Please Enter the Correct Admin Key");
		}
		
		
	}

	@Override
	public Seed updateSeed(Seed seed,String Key) throws SeedException,LoginException {
        if(Key.equals("admin")) {

    		Integer seedId = seed.getSeedId();
    		
    		Optional<Seed> find = sRepo.findById(seedId);
    		
    		if(find.isPresent()) {
    			return sRepo.save(seed);
    		}
    		else 
    			throw new SeedException("No Such Seed Exist, can't Update");	
        }else {
        	throw new LoginException("Please Enter the Correct Admin Key");
        }
			
	}

	@Override
	public Seed deleteSeed(Integer seedId,String Key) throws SeedException,LoginException {
		if(Key.equals("admin")) {

			Optional<Seed> find = sRepo.findById(seedId);		
				
			if(find.isPresent()) {
			Seed seed = find.get();
			sRepo.delete(seed);
				return seed;
			}
			else
				throw  new SeedException("No Such Seed Exist with this SeedID, can't Delete");	
		}else {
			throw new LoginException("Please Enter the Correct Admin Key");
		}
	}

	@Override
	public Seed viewSeed(int seedId,String Key) throws SeedException,LoginException {
		CurrentUserSession loggedInUser = cRepo.findByKey(Key);
		
		if(loggedInUser!=null || Key.equals("admin")) {
			Optional<Seed> find = sRepo.findById(seedId);		
			
		    return find.orElseThrow( ()-> new SeedException(
		    "No Such Seed Exist with this SeedID : "+seedId));
		}else {
			throw new LoginException("please provide a valid Key to view seed");
		}
		
		
	}

	@Override
	public List<Seed> viewSeed(String commonName,String Key) throws SeedException,LoginException {
		CurrentUserSession loggedInUser = cRepo.findByKey(Key);
		
		
		if(loggedInUser!=null || Key.equals("admin")) {
			List<Seed> seedListByCommonName = sRepo.findByCommonName(commonName);
			
		     if(seedListByCommonName.size()==0) {
		     	throw  new SeedException("No Such Seed Exist with this Common Name"+commonName);
		     }else {
		     	return seedListByCommonName;
		     }	
		}else {
			throw new LoginException("please provide a valid Key to view seed with common name");
		}
		
	}

	@Override
	public List<Seed> viewAllSeeds(String Key) throws SeedException,LoginException {
		CurrentUserSession loggedInUser = cRepo.findByKey(Key);
		
		if(loggedInUser!=null || Key.equals("admin")) {
			List<Seed> ListOfAllSeeds = sRepo.findAll();
		    if(ListOfAllSeeds.size()==0) {
		       throw  new SeedException("Sorry Currently No Seeds Exists");
		    }else {
		       return ListOfAllSeeds;
		    }	
		}else {
			throw new LoginException("please provide a valid Key to view all seed");
		}
	}

	@Override
	public List<Seed> viewAllSeeds(String typeOfSeed,String Key) throws SeedException,LoginException {
	   CurrentUserSession loggedInUser = cRepo.findByKey(Key);
		
	   if(loggedInUser!=null || Key.equals("admin")) {
		   List<Seed> allSeedBySeedType = sRepo.findByTypeOfSeeds(typeOfSeed);
		   if(allSeedBySeedType.size()==0) {
		     throw  new SeedException("No Seeds Exists with this Seed Type :"+typeOfSeed);
		   }else {
		     return allSeedBySeedType;
		   }   
	   }else {
		  throw new LoginException("please provide a valid Key to view all seed Type");
	   }  
	}
}
