package com.nttdata.semana1.model;

import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(value = "bankAccounts")
@Data
public class BankAccounts {
	
	@Id
	private String id;
	private List<CustomerBank> customers;
	private String type;
	private Double commission;
	private Integer countTransactions;
	private List<Transactions> transactions;
	private String dayAllowed;
	private Double amount;
	private Date createAt;
	private Date updatedAt;

}
