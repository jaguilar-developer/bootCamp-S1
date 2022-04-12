package com.nttdata.semana1.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(value = "customers")
@Data
public class Customer {

	@Id
	public String id;
	public String firstName;
	public String listName;
	public String documentNumber;
	public String documentType;
	public String phone;
	public Address address;
	public String email;
	public String customerType;	

}
