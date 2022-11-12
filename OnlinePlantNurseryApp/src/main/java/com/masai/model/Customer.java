package com.masai.model;

<<<<<<< Updated upstream
public class Customer {
=======
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	
	@NotBlank(message = "Please provide a valid name")
	@NotNull(message = "Name can not be null")
	private String name;
	
	@Email(message = "Please provide a valid email address")
	private String email;
	
	@NotBlank(message = "username can not be blank")
	@Pattern(regexp = "^[a-zA-Z0-9_-]{3,30}$",message = "Username should not contain any special character except - or _ and should be minimum of 3 characters")
	@Column(unique = true)
	@NotNull(message = "Username can not be null")
	private String userName;

	@Size(min = 6 ,message= "Please provide a valid password of minimum 6 characters")
	@NotNull(message = "Password can not be null")
	private String password;
	
	@Valid
	@Embedded
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Cart cart = new Cart();

	public Customer(
			@NotBlank(message = "Please provide a valid name") @NotNull(message = "Name can not be null") String name,
			@Email(message = "Please provide a valid email address") String email,
			@NotBlank(message = "username can not be blank") @Pattern(regexp = "^[a-zA-Z0-9_-]{3,30}$", message = "Username should not contain any special character except - or _ and should be minimum of 3 characters") String userName,
			@Size(min = 6, message = "Please provide a valid password of minimum 6 characters") String password) {
		super();
		this.name = name;
		this.email = email;
		this.userName = userName;
		this.password = password;
	}
	
	
>>>>>>> Stashed changes

}
