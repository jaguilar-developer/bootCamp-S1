package com.nttdata.semana1.service;

import com.nttdata.semana1.model.Account;
import com.nttdata.semana1.dto.TransactionsAccount;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountsService {
	
	public Flux<Account> getAll();
	public Mono<Account> createAccount(Account _acount);
	public Mono<Account> updateAccount(Account _acount);
	public Mono<Void> deleteAccount(String id);
	public Mono<Account> findBynumber(String number);
	public Mono<Account> PaymentAccount(TransactionsAccount transacctions);	
	
}
