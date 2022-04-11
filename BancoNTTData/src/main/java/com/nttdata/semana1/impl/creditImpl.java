package com.nttdata.semana1.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.nttdata.semana1.dto.TransactionsDTO;
import com.nttdata.semana1.model.Transactions;
import com.nttdata.semana1.model.accountType;
import com.nttdata.semana1.model.credit;
import com.nttdata.semana1.repository.creditRepository;
import com.nttdata.semana1.service.creditService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service

@AllArgsConstructor
public class creditImpl implements creditService{

	final creditRepository repository;
	
	@Override
	public Flux<credit> getAll(){
		return repository.findAll();
	}
	
	@Override
	public Mono<credit> createCredit(credit _credit)
	{
		return repository.findBycustomerid(_credit.getCustomerid())
				.flatMap(p -> validateCreateCreditPerson(_credit))
				.switchIfEmpty(repository.save(_credit))
				.then(Mono.just(_credit));
	}
	
	@Override
	public Mono<credit> updateCredit(credit _credit)
	{
		return repository.findById(_credit.getId())
				.map(p-> 
				{	repository.save(_credit).subscribe();
					return _credit;
				});
	}
	
	@Override
	public Mono<credit> findCreditsbyCustomer(String customerid)
	{
		return repository.findBycustomerid(customerid).next();
	}
		
	@Override
	public Mono<Void> deleteCredit(Integer id)
	{
		return repository.findById(id)
				.flatMap(p -> repository.deleteById(p.getId()));
	}
	
	@Override
	public Mono<credit> findById(Integer id)
	{
		return repository.findById(id);
	}

	
	private Mono<credit> validateCreateCreditPerson(credit _credit)
	{
		if(_credit.getType() == accountType.CREDITO_PERSONAL)
			return Mono.error(new RuntimeException("Solo se permite un credito para cliente Persona"));
		else
			return Mono.empty();
		
		
	}
	
	public Mono<credit> PaymentCredit(TransactionsDTO transactionDTO)
	{	
		return repository.findBynumber(transactionDTO.getNumber())
				.map(p -> {
					p.setTransactions(addTransaccion(p.transactions, transactionDTO));
					p.setCsLimit(setCreditLimit(p, transactionDTO));
					p.setCalance(setBalance(p, transactionDTO.getAmountTransactions()));
					repository.save(p).subscribe();	
					return p;
				});
	}
	
	private List<Transactions> addTransaccion(List<Transactions> transactions, TransactionsDTO transactiondto)
	{
		Integer idNew = (transactions == null? 1: transactions.size()+1);
		
		Transactions trx = new Transactions();
		trx.setType(transactiondto.getType());
		trx.setAmountTransactions(transactiondto.getAmountTransactions());
		trx.setCreateAt(LocalDateTime.now().toString());
		trx.setIdTransactions(idNew.toString());
		
		if (transactions == null) 
			transactions = new ArrayList<Transactions>();
		
		transactions.add(trx);
		
		return transactions;
	}
	
	private Double setCreditLimit(credit _credit, TransactionsDTO transactiondto)
	{
		Double creditLimit = _credit.getCsLimit(); 
		if (_credit.getType() == accountType.TC_PERSONAL || _credit.getType() == accountType.TC_EMPRESARIAL) 
		{
			if(transactiondto.getType().equals("CONSUMO"))
				if(_credit.getCsLimit() >= transactiondto.getAmountTransactions())
				{
					creditLimit =  _credit.getCsLimit() - transactiondto.getAmountTransactions();
				}
				else
				{
					throw new RuntimeException("Limite de credito insuficiente");
				}
			else if(transactiondto.getType().equals("PAGO"))
			{
				creditLimit =  _credit.getCsLimit() + transactiondto.getAmountTransactions();
			}
			else
				throw new RuntimeException("Tipo de transaccion no valida-" + transactiondto.getType());
		}
			

		return creditLimit;
	}
	
	private Double setBalance(credit _credit, Double amount)
	{
		Double balance = _credit.getCalance(); 
		if (_credit.getType() == accountType.CREDITO_PERSONAL || _credit.getType() == accountType.CREDITO_EMPRESARIAL)
			balance =  _credit.getCalance() - amount;

		return balance;
	}
	
}

