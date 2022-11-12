package com.masai.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
  
	private Integer cartId;
	
	private Integer quantity;

	private Double totalCost;
	

	@OneToMany(cascade = CascadeType.ALL)
	private List<Planter> planters;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Plant> plants;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Seed> seeds;
	
	
}
