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
import java.util.List;

import com.nttdata.semana1.model.Customer;
import com.nttdata.semana1.service.CustomerService;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class CustomerController {
	
	
	private final CustomerService customerService;
	
	@PostMapping("/customers")
	public void save(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
	}
	
	@GetMapping("/customers")
	public List<Customer> showCustomers(){
		List<Customer> oCustomer = customerService.showCustomer();
		//return customerService.showCustomer();
		return oCustomer;
	}
	
	@GetMapping("/customer/{id}")
	public Customer showCustomerById(@PathVariable String id){		
		return customerService.showCustomerById(id).get();
	}
	
	@DeleteMapping("/customer/{id}")
	public void delete(@PathVariable String id) {
		customerService.deleteCustomer(id);
	}
	
	@PutMapping("/customers")
	public void update(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
	}
	
}
