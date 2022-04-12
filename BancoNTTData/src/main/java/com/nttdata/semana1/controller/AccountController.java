package com.nttdata.semana1.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import com.nttdata.semana1.model.Account;
import com.nttdata.semana1.dto.TransactionsAccount;
import com.nttdata.semana1.service.AccountsService;
import com.nttdata.semana1.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AccountController {
	
	final AccountsService service;
	final CustomerService serviceCustomer;
	
	@GetMapping("/accounts")
	public Flux<Account> getAll()
	{
		return service.getAll();
	}
	
	@GetMapping("/accounts/{number}")
	public Mono<Account> getNumber(@PathVariable String number)
	{
		return service.findBynumber(number);
	}	
	
	@PostMapping("/accounts")
	public Mono<Account> createAccount(@RequestBody Account _account)
	{
		return service.createAccount(_account);		
	}
	
	@PutMapping("/accounts/{id}")
	public Mono<Account> updateAccount(@PathVariable String id, @RequestBody Account _account)
	{
		return service.updateAccount(_account);
	}
	
	@DeleteMapping("/accounts/{id}")
	public Mono<Void> deleteAccount(@PathVariable String id)
	{
		return service.deleteAccount(id);
	}
	
	@PostMapping("/accounts/transactions")
	public Mono<Account> PaymentAccount(@RequestBody TransactionsAccount _transacctions)
	{
		return service.PaymentAccount(_transacctions);
	}
}
