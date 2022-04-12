package com.nttdata.semana1.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.nttdata.semana1.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveCrudRepository<Customer,String>{
		
	public Mono<Customer> findBydocumentNumber(String number);
	public Mono<Customer> findById(String id);
}
