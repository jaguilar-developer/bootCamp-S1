package com.nttdata.semana1.model;


import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(value = "credit")
public class credit {

	@Id
	public Integer id;
	public String customerid;
	public accountType type;
	public String number;
	public Double csLimit;
	public Double calance;
	public Date createAt;
	public List<Transactions> transactions;
}

