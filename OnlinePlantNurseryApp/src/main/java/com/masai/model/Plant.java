package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer plantId;
	
	@Min(value = 1 , message = "Plant Height Should Be Greater Than Zero")
	private Integer plantHeight;
	
	@NotBlank
	private String plantSpread;
	
	@NotBlank
	private String commonName;
	
	@NotBlank
	private String bloomTime;
	
	@NotBlank
    private String medicinalOrCulinaryUse;
    
	@NotBlank
	private String difficultyLevel;
	
	@NotBlank
	private String temperature;
	
	@NotBlank
	private String typeOfPlant;
	
	@NotBlank
	private String plantDescription;
	
	@Min(value = 0 , message = "Plant Stock Cannot Be Negative")
	private Integer plantsStock;
	
	@Min(value = 1 , message = "Plant Cost Cannot Be Zero")
	private double plantCost;

}
