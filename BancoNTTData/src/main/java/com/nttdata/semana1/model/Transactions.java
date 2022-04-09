package com.nttdata.semana1.model;

import lombok.Data;

@Data
public class Transactions {
	
	private String idTransactions;
	private String type;
	private Double amountTransactions;
	private String createAt;

}
