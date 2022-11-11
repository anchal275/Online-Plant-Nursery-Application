package com.masai.model;

<<<<<<< Updated upstream
public class Plant {
	
	
=======
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	
	@NotNull
	@Min(value = 1 , message = "Plant Height Should Be Greater Than Zero")
	private Integer plantHeight;
	
	@NotNull
	private String plantSpread;
	
	@NotNull
	private String commonName;
	
	@NotNull
	private String bloomTime;
	
	@NotNull
    private String medicinalOrCulinaryUse;
    
	@NotNull
	private String difficultyLevel;
	
	@NotBlank
	private String temperature;
	
	@NotNull
	private String typeOfPlant;
	
	@NotNull
	private String plantDescription;
	
	@NotNull
	@Min(value = 0 , message = "Plant Stock Cannot Be Negative")
	private Integer plantsStock;
	
	@NotNull
	@Min(value = 1 , message = "Plant Cost Cannot Be Zero")
	private double plantCost;
>>>>>>> Stashed changes

}
