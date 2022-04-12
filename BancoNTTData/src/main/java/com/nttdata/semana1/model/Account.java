package com.nttdata.semana1.model;

import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(value = "accounts")
public class Account {

	@Id
	public String id;
	public List<CustomerBank> customers;
	public String number;
	public accountType type;
	public String nameType;
	public Double commission;
	public Integer countTransactions;	
	public String dayAllowed;
	public Double amount;
	public Date createAt;
	public List<Transactions> transactions;
	
}
