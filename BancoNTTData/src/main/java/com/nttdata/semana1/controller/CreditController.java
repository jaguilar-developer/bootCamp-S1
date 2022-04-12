package com.nttdata.semana1.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.semana1.dto.TransactionsDTO;
import com.nttdata.semana1.model.credit;
import com.nttdata.semana1.service.creditService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CreditController {

	final creditService servicio;
	@GetMapping("/credits")
	public Flux<credit> getAll()
	{
		return servicio.getAll();
	}
	
	@GetMapping("/credits/{id}")
	public Mono<credit> getById(@PathVariable Integer id)
	{
		return servicio.findById(id);
	}
	
	@PostMapping("/credits")
	public Mono<credit> createCredit(@RequestBody credit _credit)
	{
		return servicio.createCredit(_credit);
	}
	
	@DeleteMapping("/credits/{id}")
	public Mono<Void> deleteCredit(@PathVariable Integer id)
	{
		return servicio.deleteCredit(id);
	}
	
	@PutMapping("/credits/{id}")
	public Mono<credit> updateCredit(@PathVariable Integer id,@RequestBody credit _credit)
	{
		return servicio.updateCredit(_credit);
	}
	
	
	@PostMapping("/credit/transactions")
	public Mono<credit> PaymentCredit(@RequestBody TransactionsDTO transactiondto)
	{
		return servicio.PaymentCredit(transactiondto);
	}
	
	@GetMapping("/credits/number/{number}")
	public Mono<credit> getById(@PathVariable String number)
	{
		return servicio.findBynumber(number);
	}
}
