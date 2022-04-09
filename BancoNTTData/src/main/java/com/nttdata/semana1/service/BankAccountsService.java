package com.nttdata.semana1.service;

import com.nttdata.semana1.exception.ExceptionFuntional;
import com.nttdata.semana1.model.BankAccounts;
import com.nttdata.semana1.repository.BankAccountsRepository;
import com.nttdata.semana1.repository.CustomerRepository;
import com.nttdata.semana1.model.Customer;
import com.nttdata.semana1.model.CustomerBank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BankAccountsService {
	private final BankAccountsRepository bankaccountsRepository;
	private final CustomerRepository customerRepository;
	
	
	public String saveAccounts(BankAccounts account) {
		
		try {
			List<Customer> listCustomer = account.getCustomers().stream()
					.map(iCustomerBank -> customerRepository.findByDocumentNumber(iCustomerBank.getDocumentNumber()))
					.collect(Collectors.toList());
			
			listCustomer.stream().forEach(x -> validarListas(x.getDocumentNumber(),account.getCustomers()));
			listCustomer.stream().forEach(x -> validarCuentas(x.getDocumentNumber()));

			
			
			return "0";
			
		}catch (Exception e) {
		  e.getStackTrace();
		  e.printStackTrace();
		  return e.getMessage();		  
		}
		
		
		/*
		if (account.getType().toUpperCase().equals("AHORRO")) {
			
			account.setAmount(0.0);
			
		}else if (account.getType().toUpperCase().equals("CUENTA CORRIENTE")) {
			
			account.setCountTransactions(null);
			
		}else if (account.getType().toUpperCase().equals("PLAZO FIJO")) {
			
			account.setAmount(0.0);
			account.setCountTransactions(1);
			
			if(account.getDayAllowed().isEmpty()) {
				return "-1";
			}
		}
		else {
			return "-2";
		}
		bankaccountsRepository.save(account);
		return "0";*/
	}
	
	private void validarCuentas(String documentNumber) {
		bankaccountsRepository.findAll().stream()
			.filter(x -> x.getCustomers()!= null)
			.forEach(iAccounts -> {
			iAccounts.getCustomers().stream().forEach(iCustomers -> {
					validarSubCuenta(iCustomers, documentNumber);				
			});
		});
	}

	private void validarSubCuenta(CustomerBank iCustomers, String documentNumber){		
		if (documentNumber.equals(iCustomers.getDocumentNumber())){
			throw new RuntimeException("-5");
		}
	}

	private void validarListas(String documentNumber, List<CustomerBank> customers) {
		
		Optional<CustomerBank> findDocument = customers.stream().filter(x -> x.getDocumentNumber().equals(documentNumber)).findFirst();
		if (!findDocument.isPresent()) {
			throw new RuntimeException("-4");
		}		
	}

	public List<BankAccounts> findAll(){
		return bankaccountsRepository.findAll();
	}
	
	public Optional<BankAccounts> findById(String id) {
		return bankaccountsRepository.findById(id);
	}
	
	public void deleteById(String id) {		
		bankaccountsRepository.deleteById(id);
	}
}
