package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.ProductException;
import com.masai.model.Cart;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.Plant;
import com.masai.model.Planter;
import com.masai.model.Seed;
import com.masai.repository.CartRepo;
import com.masai.repository.CurrentUserSessionRepo;
import com.masai.repository.CustomerRepo;
import com.masai.repository.PlantRepo;
import com.masai.repository.PlanterDao;
import com.masai.repository.SeedRepo;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CurrentUserSessionRepo cusr;

	@Autowired
	CartRepo cartRepo;

	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	PlantRepo plantRepo;

	@Autowired
	PlanterDao planterRepo;

	@Autowired
	SeedRepo seedRepo;

	@Override
	public String addToCart(Integer productId, String key) throws CustomerException, ProductException, LoginException {
		
		String message = "";
		
		CurrentUserSession user = cusr.findByKey(key);
		if (user == null)
			throw new LoginException("Please provide a valid key");
		
		Integer id = user.getUserId();
		Optional<Customer> customerOpt = customerRepo.findById(id);
		Customer customer = customerOpt.get();
		
		Cart cart = customer.getCart();
		
		Optional<Seed> seed = seedRepo.findById(productId);
		Optional<Plant> plant = plantRepo.findById(productId);
		Optional<Planter> planter = planterRepo.findById(productId);
		if (seed.isPresent()) {
			cart.getSeeds().add(seed.get());
			this.quantity(cart);
			this.cost(seed.get().getSeedsCost(), cart);
			message = seed.get().toString();
		}
		else if (plant.isPresent()) {
			cart.getPlants().add(plant.get());
			this.quantity(cart);
			this.cost(plant.get().getPlantCost(), cart);
			message = plant.get().toString();
		}
		else if (planter.isPresent()) {
			cart.getPlanters().add(planter.get());
			this.quantity(cart);
			this.cost(planter.get().getPlanterCost(), cart);
			message = planter.get().toString();
		}else {
			throw new ProductException("Product not found with product Id: " + productId);
		}
		cartRepo.save(cart);
		return "Product succesfully added to cart - " + message;
		
	}

	
	
	
	@Override
	public String deleteFromCart(Integer productId, String key)
			throws CustomerException, ProductException, LoginException {
		
		
		CurrentUserSession user = cusr.findByKey(key);
		if (user == null)
			throw new LoginException("Please provide a valid key");
		
		Integer id = user.getUserId();
		Optional<Customer> customerOpt = customerRepo.findById(id);
		Customer customer = customerOpt.get();
		
		Cart cart = customer.getCart();
		boolean flag = false;
		flag = cart.getPlanters().removeIf(p-> p.getPlanterId()== productId);
		flag = cart.getPlants().removeIf(p-> p.getPlantId()== productId);
		flag = cart.getSeeds().removeIf(p-> p.getSeedId()== productId);
		
		cart.setQuantity(cart.getPlanters().size()+cart.getPlants().size()+cart.getSeeds().size());
		Double cost = 0.0;
		for(Planter c:cart.getPlanters()) {
			cost += c.getPlanterCost();
		}
		for(Plant c:cart.getPlants()) {
			cost += c.getPlantCost();
		}
		for(Seed c:cart.getSeeds()) {
			cost += c.getSeedsCost();
		}
		cart.setTotalCost(cost);
		
		cartRepo.save(cart);
		if(flag) return "Product deleted from cart";
		return "Product does not exist in cart with product Id: "+ productId;
	}

	@Override
	public Cart viewCart(String key) throws CustomerException, ProductException, LoginException {
		CurrentUserSession user = cusr.findByKey(key);
		if (user == null)
			throw new LoginException("Please provide a valid key");
		
		Integer id = user.getUserId();
		Optional<Customer> customerOpt = customerRepo.findById(id);
		Customer customer = customerOpt.get();
		
		Cart cart = customer.getCart();
		if(cart.getQuantity()==0)
			throw new ProductException("Cart is empty");

		return cart;
	}

	@Override
	public String emptyCart(String key) throws CustomerException, ProductException, LoginException {
		CurrentUserSession user = cusr.findByKey(key);
		if (user == null)
			throw new LoginException("Please provide a valid key");
		
		Integer id = user.getUserId();
		Optional<Customer> customerOpt = customerRepo.findById(id);
		Customer customer = customerOpt.get();
		
		Cart cart = customer.getCart();
		cartRepo.delete(cart);
		
		return "All items successfully removed from cart";
	}
	
	private void quantity(Cart cart) {
		cart.setQuantity(cart.getQuantity()+1);
	}
	
	private void cost(Double cost,Cart cart) {
		cart.setTotalCost(cart.getTotalCost()+cost);
	}

}
