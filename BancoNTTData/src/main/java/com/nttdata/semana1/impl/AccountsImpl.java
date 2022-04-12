package com.nttdata.semana1.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import org.springframework.stereotype.Service;

import com.nttdata.semana1.model.Account;
import com.nttdata.semana1.model.Transactions;
import com.nttdata.semana1.model.accountType;
import com.nttdata.semana1.dto.TransactionsAccount;
import com.nttdata.semana1.repository.AccountsRepository;
import com.nttdata.semana1.repository.CustomerRepository;
import com.nttdata.semana1.service.AccountsService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service

@AllArgsConstructor
public class AccountsImpl implements AccountsService{

	final AccountsRepository repository;
	final CustomerRepository repositoryCustomer;
	
	@Override
	public Flux<Account> getAll(){
		return repository.findAll();
	}
	
	@Override
	public Mono<Account> createAccount(Account _account)
	{
		
		if (_account.getType().equals(accountType.CUENTA_PERSONAL)) {
			if(_account.getNameType().equals("AHORRO") || _account.getNameType().equals("PLAZO FIJO")) {
				_account.setCommission(0.0);
			}else if(_account.getNameType().equals("CUENTA CORRIENTE")) {
				_account.setCountTransactions(null);
			}else {
				return Mono.empty();
			}
		}else if(!(_account.getType().equals(accountType.CUENTA_EMPRESARIAL) && _account.getNameType().equals("CUENTA CORRIENTE"))){
			return Mono.empty();
		}
		
		
		return repository.save(_account);		
		
	}
	
	@Override
	public Mono<Account> updateAccount(Account _account)
	{
		return repository.findById(_account.getId())
				.map(p-> 
				{
					repository.save(_account).subscribe();
					return _account;
				});
	}
	
	@Override
	public Mono<Void> deleteAccount(String id)
	{
		return repository.findById(id)
				.flatMap(p -> repository.deleteById(p.getId()));
	}
	
	@Override
	public Mono<Account> findBynumber(String number)
	{
		return repository.findBynumber(number);
	}
	
	public Mono<Account> PaymentAccount(TransactionsAccount _transactions)
	{
		return repository.findBynumber(_transactions.getNumber())
				.map(a -> {
					a.setTransactions(addTransaction(a.transactions,_transactions));
					a.setAmount(updateAmount(a,_transactions));
					a.setCountTransactions(updateCountTransactions(a, _transactions));
					repository.save(a).subscribe();
					return a;
				});
	}
	
	public Double updateAmount(Account _account, TransactionsAccount transactionAccount)
	{
		Double amount = 0.0;
		
		if(transactionAccount.getType().equals("R")) { //RETIRO
			if(transactionAccount.getAmountTransactions() > _account.getAmount()) {
				throw new RuntimeException("Monto de retiro excede el saldo disponible");
			}
			amount = _account.getAmount() - transactionAccount.getAmountTransactions();
			
		}else if(transactionAccount.getType().equals("D")) { //DEPOSITO
			amount = _account.getAmount() + transactionAccount.getAmountTransactions();
		}else {
			throw new RuntimeException("Operacion invalida");
		}
		return amount;
	}
	
	public Integer updateCountTransactions(Account _account, TransactionsAccount transactionAccount)
	{
		Integer countTransactions = _account.getCountTransactions();
		Calendar c = Calendar.getInstance();
		String dia = Integer.toString(c.get(Calendar.DATE));
		
		if (_account.getNameType().equals("AHORRO")) {
			
			if (_account.getCountTransactions() > 0) {
				
				countTransactions = _account.getCountTransactions() - 1;
				
			}else {
				throw new RuntimeException("Excedio el limite de operaciones permitidas para esta cuenta");
			}
		}else if (_account.getNameType().equals("PLAZO FIJO")) {
			if(_account.getDayAllowed().equals(dia.toString())) {
				countTransactions = 0;
			}else {
				throw new RuntimeException("El dia de hoy no puede procesar esta transaccion");
			}
		}else {
			throw new RuntimeException("Tipo de cuenta invalida");
		}
		
		return countTransactions;
	}
	
	private List<Transactions> addTransaction(List<Transactions> transactions,TransactionsAccount transactionAccount)
	{
		Integer idNew = (transactions == null? 1: transactions.size()+1); 
		Transactions trx = new Transactions();
		trx.setType(transactionAccount.getType());
		trx.setAmountTransactions(transactionAccount.getAmountTransactions());
		trx.setCreateAt(LocalDateTime.now().toString());
		trx.setIdTransactions(idNew.toString());
		
		if(transactions == null)
			transactions = new ArrayList<Transactions>();
		
		transactions.add(trx);
		
		return transactions;
	}
	
}
