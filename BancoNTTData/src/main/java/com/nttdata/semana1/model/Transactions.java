package com.nttdata.semana1.model;

import lombok.Data;

@Data
public class Transactions {
	
	public String idTransactions;
	public String type;
	public Double amountTransactions;
	public String createAt;

}
