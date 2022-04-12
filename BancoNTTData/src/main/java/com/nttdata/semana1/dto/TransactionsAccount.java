package com.nttdata.semana1.dto;

import lombok.Data;

@Data
public class TransactionsAccount {

	public String idTransactions;
	public String type;
	public Double amountTransactions;	
	public String number;	
	public String createAt;
}
