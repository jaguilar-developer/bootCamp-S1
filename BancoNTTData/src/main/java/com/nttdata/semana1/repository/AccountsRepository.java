package com.nttdata.semana1.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.nttdata.semana1.model.Account;

import reactor.core.publisher.Mono;

public interface AccountsRepository extends ReactiveCrudRepository<Account,String>{	

	public Mono<Account> findById(String id);
	public Mono<Account> findBynumber(String number);
}
