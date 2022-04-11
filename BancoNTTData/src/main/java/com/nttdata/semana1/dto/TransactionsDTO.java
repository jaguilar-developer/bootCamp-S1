package com.nttdata.semana1.dto;

import lombok.Data;

@Data
public class TransactionsDTO {

	public String idTransactions;
	public String type;
	public Double amountTransactions;
	public String createAt;
	public String number;
}