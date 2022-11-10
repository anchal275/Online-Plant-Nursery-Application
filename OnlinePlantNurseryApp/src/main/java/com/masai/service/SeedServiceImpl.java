package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.exceptions.SeedException;
import com.masai.model.Seed;
import com.masai.repository.SeedRepo;


@Service
public class SeedServiceImpl implements SeedService{

	@Autowired
	private SeedRepo sRepo;
	
	@Override
	public Seed addSeed(Seed seed) throws SeedException {
		Seed saveSeed = sRepo.save(seed);
		if(saveSeed==null) {
			throw new SeedException("Seed Not Saved");
		}else {
			return saveSeed;
		}
	}

	@Override
	public Seed updateSeed(Seed seed) throws SeedException {
        Integer seedId = seed.getSeedId();
		
		Optional<Seed> find = sRepo.findById(seedId);
		
		if(find.isPresent()) {
			return sRepo.save(seed);
		}
		else 
			throw new SeedException("No Such Seed Exist, can't Update");	
	}

	@Override
	public Seed deleteSeed(Integer seedId) throws SeedException {
		 Optional<Seed> find = sRepo.findById(seedId);		
			
			if(find.isPresent()) {
				Seed seed = find.get();
				sRepo.delete(seed);
				return seed;
			}
			else
				throw  new SeedException("No Such Seed Exist with this SeedID, can't Delete");
	}

	@Override
	public Seed viewSeed(int seedId) throws SeedException {
		Optional<Seed> find = sRepo.findById(seedId);		
			
	    return find.orElseThrow( ()-> new SeedException(
	    "No Such Seed Exist with this SeedID : "+seedId));
	}

	@Override
	public List<Seed> viewSeed(String commonName) throws SeedException {
		 List<Seed> seedListByCommonName = sRepo.findByCommonName(commonName);
			
	     if(seedListByCommonName.size()==0) {
	     	throw  new SeedException("No Such Seed Exist with this Common Name"+commonName);
	     }else {
	     	return seedListByCommonName;
	     }
	}

	@Override
	public List<Seed> viewAllSeeds() throws SeedException {
		List<Seed> ListOfAllSeeds = sRepo.findAll();
	    if(ListOfAllSeeds.size()==0) {
	       throw  new SeedException("Sorry Currently No Seeds Exists");
	    }else {
	       return ListOfAllSeeds;
	    }
	}

	@Override
	public List<Seed> viewAllSeeds(String typeOfSeed) throws SeedException {
	   List<Seed> allSeedBySeedType = sRepo.findByTypeOfSeeds(typeOfSeed);
	   if(allSeedBySeedType.size()==0) {
	     throw  new SeedException("No Seeds Exists with this Seed Type :"+typeOfSeed);
	   }else {
	     return allSeedBySeedType;
	   }
	}
	
	

	

}
