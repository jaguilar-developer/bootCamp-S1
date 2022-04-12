package com.nttdata.semana1.impl;

import org.springframework.stereotype.Service;
import com.nttdata.semana1.repository.CustomerRepository;
import com.nttdata.semana1.service.CustomerService;
import com.nttdata.semana1.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.AllArgsConstructor;

@Service

@AllArgsConstructor
public class CustomerImpl implements CustomerService{
	
	final CustomerRepository repository;	
	
	@Override
	public Flux<Customer> getAll(){
		return repository.findAll();
	}
	
	@Override
	public Mono<Customer> createCustomer(Customer _customer)
	{		
		return repository.findBydocumentNumber(_customer.getDocumentNumber())
				.flatMap(p -> Mono.error(new RuntimeException("Cliente ya existe")))
				.switchIfEmpty(repository.save(_customer))
				.then(Mono.just(_customer));
		
	}
	
	@Override
	public Mono<Customer> updateCustomer(Customer _customer)
	{
		return repository.findById(_customer.getId())
				.map(p ->{
					repository.save(_customer).subscribe();
					return _customer;
				});
	}
	
	@Override
	public Mono<Void> deleteCustomer(String id)
	{
		return repository.findById(id)
				.flatMap(p -> repository.deleteById(p.getId()));
	}
	
	@Override
	public Mono<Customer> findBydocumentNumber(String number)
	{
		return repository.findBydocumentNumber(number);
	}
	
	@Override
	public Mono<Customer> findById(String id)
	{
		return repository.findById(id);
	}

}
