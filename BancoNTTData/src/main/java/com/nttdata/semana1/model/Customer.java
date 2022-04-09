package com.nttdata.semana1.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(value = "customers")
@Data
public class Customer {

	@Id
	private String id;
	private String firstName;
	private String listName;
	private String documentNumber;
	private String documentType;
	private String phone;
	private Address address;
	private String email;
	private String customerType;	

}
