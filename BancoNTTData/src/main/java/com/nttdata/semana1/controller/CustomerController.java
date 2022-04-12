package com.nttdata.semana1.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.nttdata.semana1.model.Customer;
import com.nttdata.semana1.service.CustomerService;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class CustomerController {
	
	
	final CustomerService service;
	
	@GetMapping("/customers")
	public Flux<Customer> getAll()
	{
		return service.getAll();
	}
	
	@GetMapping("/customer/{id}")
	public Mono<Customer> getId(@PathVariable String id)
	{
		return service.findById(id);
	}	
	
	@GetMapping("/customer/search/{documentNumber}")
	public Mono<Customer> getNumber(@PathVariable String documentNumber)
	{
		return service.findBydocumentNumber(documentNumber);
	}
	
	@PostMapping("/customers")
	public Mono<Customer> createCustomer(@RequestBody Customer _customer)
	{
		return service.createCustomer(_customer);
	}
	
	@PutMapping("/customers/{id}")
	public Mono<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer _customer)
	{
		return service.updateCustomer(_customer);
	}
	
	@DeleteMapping("/customers/{id}")
	public Mono<Void> deleteCustomer(@PathVariable String id)
	{
		return service.deleteCustomer(id);
	}
	
}
