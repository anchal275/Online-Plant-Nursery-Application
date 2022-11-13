package com.masai.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "planter_planterId" )
	private List<Planter> planters;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "plant_plantId")
	private List<Plant> plants;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "seed_seedId")
	private List<Seed> seeds;
	
	
}
