package com.masai.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingOrderId;
	
	private LocalDateTime orderDate;
	
	@NotBlank(message = "Please provide valid transaction mode")
	@NotNull(message = "Transaction mode can not be null")
	private String TransactionMode;
	
	private Integer quantity;
	
	private Double totalCost;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Planter> planters;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Plant> plants;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Seed> seeds;

	public Order(LocalDateTime orderDate, String transactionMode, Integer quantity, Double totalCost) {
		super();
		this.orderDate = orderDate;
		TransactionMode = transactionMode;
		this.quantity = quantity;
		this.totalCost = totalCost;
	}
	
	
	
}
