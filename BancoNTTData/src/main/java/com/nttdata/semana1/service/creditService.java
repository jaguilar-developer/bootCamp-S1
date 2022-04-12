package com.nttdata.semana1.service;

import com.nttdata.semana1.dto.TransactionsDTO;
import com.nttdata.semana1.model.credit;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface creditService {

	public Flux<credit> getAll();
	public Mono<credit> createCredit(credit _credit);
	public Mono<credit> updateCredit(credit _credit);
	public Mono<credit> findCreditsbyCustomer(String customerid);
	public Mono<Void> deleteCredit(Integer id);
	public Mono<credit> PaymentCredit(TransactionsDTO transactionDTO);
	public Mono<credit> findById(Integer id);
	public Mono<credit> findBynumber(String number);
}