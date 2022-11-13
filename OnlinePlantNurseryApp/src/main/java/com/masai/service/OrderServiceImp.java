package com.masai.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CartException;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.OrderException;
import com.masai.exceptions.OutOfStockException;
import com.masai.exceptions.ProductException;
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

@Service
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
	
	
	@Autowired
	CartService cartService;

	@Override
	public String placeOrder(String transactionMode, String key) throws LoginException, CartException, OutOfStockException, ProductException,CustomerException {
		String message ="";
		
		//customer login check;
		
		CurrentUserSession loginCustomer = currentUserSession.findByKey(key);
		
		if(loginCustomer==null) {
			throw new LoginException("customer not loggedIn: ");
		}
		 
		// getting customer object with login session key;
		
		
		Optional<Customer> customerOpt = rCustomers.findById(loginCustomer.getUserId());
		
		Customer customer = customerOpt.get();
		
		
		//getting cart from loggedIn customer;
		
		Cart cart =  customer.getCart();
		
		if(cart==null) throw new CartException("cart is Empty");
		
		
		//getting all product list from logged customer cart;
		
		List<Planter> planterList = cart.getPlanters();
		List<Plant>  plantList = cart.getPlants();
		List<Seed> seedList = cart.getSeeds();
		
		
		// mapping all product quantity from cart product list;
		
		Map<Integer, Integer> planterQuantityCheck = new HashMap<>();
		
		for(Planter p:planterList) {
			Integer id = p.getPlanterId();
			if(!planterQuantityCheck.containsKey(id)) {
				planterQuantityCheck.put(id, 1);
			}else {
				planterQuantityCheck.put(id, planterQuantityCheck.get(id)+1);
			}
		}
		
		
		Map<Integer, Integer> plantQuantityCheck = new HashMap<>();
		
		
		for(Plant p:plantList) {
			Integer id = p.getPlantId();
			if(!plantQuantityCheck.containsKey(id)) {
				plantQuantityCheck.put(id, 1);
			}else {
				plantQuantityCheck.put(id, plantQuantityCheck.get(id)+1);
			}
		}
		
		
		Map<Integer, Integer> seedQuantityCheck = new HashMap<>();
		
		for(Seed p:seedList) {
			Integer id = p.getSeedId();
			if(!seedQuantityCheck.containsKey(id)) {
				seedQuantityCheck.put(id, 1);
			}else {
				seedQuantityCheck.put(id, seedQuantityCheck.get(id)+1);
			}
		}
		
		
		
		
		//Stock checking;
		
//		Boolean flag = true;
		
		for(Map.Entry<Integer, Integer> e :planterQuantityCheck.entrySet()) {
			Integer id = e.getKey();
			Integer value = e.getValue();
			
			 Optional<Planter> planterOpt = planterRepo.findById(id);
			 Planter planter =planterOpt.get();
			 
			 planter.setPlanterStock(planter.getPlanterStock()-value);
			 
			 if(value>planter.getPlanterStock()) { 
				 throw new OutOfStockException("planter out of stock with id "+planter.getPlanterId()+" and Planter shape "+planter.getPlanterShape());
			 } 
		}
		
		
		
		for(Map.Entry<Integer, Integer> e :plantQuantityCheck.entrySet()) {
			Integer id = e.getKey();
			Integer value = e.getValue();
			
			 Optional<Plant> plantOpt = planRepo.findById(id);
			 Plant plant =plantOpt.get();
			 plant.setPlantsStock(plant.getPlantsStock()-value);
			 
			 if(value>plant.getPlantsStock()) { 
				 throw new OutOfStockException("plant out of stock with id "+plant.getPlantId()+" and Plant name "+plant.getCommonName());
			 } 
		}
		
		
		
		
		for(Map.Entry<Integer, Integer> e :seedQuantityCheck.entrySet()) {
			Integer id = e.getKey();
			Integer value = e.getValue();
			
			 Optional<Seed> seedOpt = seedRepo.findById(id);
			 Seed seed =seedOpt.get();
			 seed.setSeedsStock(seed.getSeedsStock()-value);
			 if(value>seed.getSeedsStock()) { 
				 throw new OutOfStockException("seed out of stock with id "+seed.getSeedId()+" and seed name "+seed.getCommonName());
			 } 
		}
	
		
		// stock maintaining;
				for(Map.Entry<Integer, Integer> e :planterQuantityCheck.entrySet()) {
			Integer id = e.getKey();
			Integer value = e.getValue();
			
			 Optional<Planter> planterOpt = planterRepo.findById(id);
			 Planter planter =planterOpt.get();
			 planter.setPlanterStock(planter.getPlanterStock()-value);
			 planterRepo.save(planter);
		}
		
		
		
		for(Map.Entry<Integer, Integer> e :plantQuantityCheck.entrySet()) {
			Integer id = e.getKey();
			Integer value = e.getValue();
			
			 Optional<Plant> plantOpt = planRepo.findById(id);
			 Plant plant =plantOpt.get();
			 plant.setPlantsStock(plant.getPlantsStock()-value);
			 planRepo.save(plant);
		}
		
		
		for(Map.Entry<Integer, Integer> e :seedQuantityCheck.entrySet()) {
			Integer id = e.getKey();
			Integer value = e.getValue();
			
			 Optional<Seed> seedOpt = seedRepo.findById(id);
			 Seed seed =seedOpt.get();
			 seed.setSeedsStock(seed.getSeedsStock()-value);
			 seedRepo.save(seed);
		}
		
		
		
		// order maintain;
		
		Order order = new Order();
		
		planterList.forEach(p->{
    
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
		
		cartService.emptyCart(key);
		
		return "order placed";
	}
	/////////////////////////////////////////order palace method end///////////////////////////////////////
	
	
	

	
/////////////////////////////////////////order cancel method start///////////////////////////////////////
	@Override
	public String cancelOrder(String key, Integer orderId) throws LoginException, OrderException {
	
		//customer login check;
		
				CurrentUserSession loginCustomer = currentUserSession.findByKey(key);
				
				if(loginCustomer==null) {
					throw new LoginException("customer not loggedIn: ");
				}
				 
				// getting customer object with login session key;
				
				
				Optional<Customer> customerOpt = rCustomers.findById(loginCustomer.getUserId());
				
				Customer customer = customerOpt.get();
				
				if(orderRepo.findById(orderId).isEmpty()) {
					throw new OrderException("Please Enter a valid OrderID :"+orderId);
				}else {
					orderRepo.deleteById(orderId);
						
				}
				
			
				
		return "Order cancelled successfully";
	}

	
	
	
	
	///////////////////////////////////////view order by key or customer Id\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
	
	@Override
	public List<Order> viewOrder(String key, Integer customerId) throws LoginException, OrderException, CustomerException {
		
		
		CurrentUserSession loginCustomer = currentUserSession.findByKey(key);
		
		if(loginCustomer==null && !key.equals("admin")) {
			throw new LoginException("Invalid key: ");
		}else if(loginCustomer!=null) {
	
			Optional<Customer> customerOpt = rCustomers.findById(loginCustomer.getUserId());
			
			Customer customer = customerOpt.get();
			
			if(customerId!=customer.getCustomerId()) {
				throw new LoginException("Please provide right cuscomer id");
			}
			
			List<Order> orderList = orderRepo.findByCustomerId(customerId);
			
			if(orderList.size()==0) { throw new OrderException("order not found with this user id: "+ customerId);}
			
			 return orderList;
			 
		}else if(key.equals("admin")) {
			Optional<Customer> customerOpt = rCustomers.findById(customerId);
			
			if(customerOpt.isEmpty()) throw new CustomerException("customer does not exist with this customerId "+customerId);

			List<Order> orderList = orderRepo.findByCustomerId(customerId);
			
			if(orderList.size()==0) throw new OrderException("order not found with this user id: "+ customerId);
			return orderList;
		}
		else{
			throw new LoginException("customer not loggedIn: ");
		}
	}
	
	
	/////////////////////////////////////////view all orders\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	@Override
	public List<Order> viewAllOrders(String key) throws LoginException, OrderException {
		
		if(!key.equals("admin")) throw new LoginException("please provide valid key");
		
		List<Order> orderList= orderRepo.findAll();
		
		if(orderList.size()==0) throw new OrderException("No any order found in orders");
		return orderList;
	}

}
