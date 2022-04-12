package com.nttdata.semana1.service;

import com.nttdata.semana1.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface CustomerService{
	
	public Flux<Customer> getAll();
	public Mono<Customer> createCustomer(Customer _customer);
	public Mono<Customer> updateCustomer(Customer _customer);
	public Mono<Void> deleteCustomer(String id);
	public Mono<Customer> findById(String id);
	public Mono<Customer> findBydocumentNumber(String number);	
}
