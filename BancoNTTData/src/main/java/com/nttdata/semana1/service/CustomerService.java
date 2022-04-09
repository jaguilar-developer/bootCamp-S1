package com.nttdata.semana1.service;

import com.nttdata.semana1.model.Customer;
import com.nttdata.semana1.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService{
	private final CustomerRepository customerRepository;
	
	public void saveCustomer(Customer customer) {
		customerRepository.save(customer);
	}
	
	public List<Customer> findAll(){
		return customerRepository.findAll();
	}
	
	public Optional<Customer> findById(String id) {
		return customerRepository.findById(id);
	}
	
	public void deleteById(String id) {		
		customerRepository.deleteById(id);
	}
}
