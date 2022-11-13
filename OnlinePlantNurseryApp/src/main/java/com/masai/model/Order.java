package com.masai.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ProductOrder")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingOrderId;
	
	private Integer customerId;
	
	private LocalDateTime orderDate;
	
	@NotBlank(message = "Please provide valid transaction mode")
	@NotNull(message = "Transaction mode can not be null")
	private String TransactionMode;
	
	
	private Integer quantity;
	
	private Double totalCost;
	
	@Embedded
	@ElementCollection
	private List<ProductDTO> products = new ArrayList<>();
	

	public Order(LocalDateTime orderDate, String transactionMode, Integer quantity, Double totalCost) {
		super();
		this.orderDate = orderDate;
		TransactionMode = transactionMode;
		this.quantity = quantity;
		this.totalCost = totalCost;
	}
	
	
	
}
