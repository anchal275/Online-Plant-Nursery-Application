package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.exceptions.CartException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.OrderException;
import com.masai.model.Cart;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.Order;
import com.masai.model.Plant;
import com.masai.model.Planter;
import com.masai.model.ProductDTO;
import com.masai.model.Seed;
import com.masai.repository.CartRepo;
import com.masai.repository.CurrentUserSessionRepo;
import com.masai.repository.CustomerRepo;
import com.masai.repository.OrderRepo;
import com.masai.repository.PlantRepo;
import com.masai.repository.PlanterDao;
import com.masai.repository.SeedRepo;

public class OrderServiceImp implements OrderService{
	
	
	@Autowired
	CustomerRepo rCustomers;
	
	
	@Autowired
	CurrentUserSessionRepo currentUserSession;
	
	
	@Autowired
	CartRepo cartRepo;
	
	
	@Autowired
	OrderRepo orderRepo;
	
	
	@Autowired
	PlanterDao planterRepo;
	
	
	@Autowired
	PlantRepo planRepo;
	
	
	@Autowired
	SeedRepo seedRepo;

	@Override
	public String placeOrder(String transactionMode, String key) throws LoginException, CartException {
		String message ="";
		
		CurrentUserSession loginCustomer = currentUserSession.findByKey(key);
		
		if(loginCustomer==null) {
			throw new LoginException("customer not loggedIn: ");
		}
		 
		Optional<Customer> customerOpt = rCustomers.findById(loginCustomer.getUserId());
		
		Customer customer = customerOpt.get();
		Cart cart =  customer.getCart();
		
		if(cart==null) throw new CartException("cart is Empty");
		
		List<Planter> planterList = cart.getPlanters();
		List<Plant>  plantList = cart.getPlants();
		List<Seed> seedList = cart.getSeeds();
		
		
		
		
		
		
		Order order = new Order();
		
		planterList.stream().forEach(p->{
			ProductDTO pDto = new ProductDTO(p.getPlanterId(),"Planter");
			order.getProducts().add(pDto);
		});
		
		plantList.stream().forEach(p->{
			ProductDTO pDto = new ProductDTO(p.getPlantId(),"Plant");
			order.getProducts().add(pDto);
		});
		
		seedList.stream().forEach(p->{
			ProductDTO pDto = new ProductDTO(p.getSeedId(),"Seed");
			order.getProducts().add(pDto);
		});
		
		
		
		order.setCustomerId(loginCustomer.getUserId());
		order.setOrderDate(LocalDateTime.now());
		order.setTransactionMode(transactionMode);
		order.setQuantity(cart.getQuantity());
		order.setTotalCost(cart.getTotalCost());
		orderRepo.save(order);
		
		
		return message;
	}

	@Override
	public String cancelOrder(String key, Integer orderId) throws LoginException, OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order viewOrder(String key, Integer customerId) throws LoginException, OrderException, CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> viewAllOrders(String key) throws LoginException, OrderException {
		// TODO Auto-generated method stub
		return null;
	}

}
