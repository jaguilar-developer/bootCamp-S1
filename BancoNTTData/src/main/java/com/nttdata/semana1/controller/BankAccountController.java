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
import javax.servlet.http.HttpServletResponse;
import com.nttdata.semana1.model.BankAccounts;
import com.nttdata.semana1.service.BankAccountsService;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class BankAccountController {
	
	private final BankAccountsService accountService;
	
	@PostMapping("/bankAccounts")
	public void save(@RequestBody BankAccounts account, HttpServletResponse response) {
		String res;
		
		res = accountService.saveAccounts(account);
		
		if (res.equals("0")) {
			response.setStatus(HttpServletResponse.SC_CREATED);
			response.addHeader("response", "Creado correctamente");
		}else if (res.equals("-1")){
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			response.addHeader("response", "Debe ingresar un dia permitido");
		}else if (res.equals("-2")){
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			response.addHeader("response", "Tipo de cuenta ingresada no es valida");
		}else if (res.equals("-3")){
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			response.addHeader("response","Se encontro el cliente, es de tipo persona");
		}
		else if (res.equals("-4")){
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			response.addHeader("response","No se encontro clientes");
		}
		else if (res.equals("-5")){
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			response.addHeader("response","Cliente encontrado en cuenta");
		}
	}
	
	@GetMapping("/bankAccounts")
	public List<BankAccounts> findAll(){
		List<BankAccounts> account = accountService.findAll();		
		return account;
	}
	
	@GetMapping("/bankAccount/{id}")
	public BankAccounts findById(@PathVariable String id){		
		return accountService.findById(id).get();
	}
	
	@DeleteMapping("/bankAccount/{id}")
	public void deleteById(@PathVariable String id) {
		accountService.deleteById(id);
	}
	
	@PutMapping("/bankAccount")
	public void update(@RequestBody BankAccounts account) {
		accountService.saveAccounts(account);
	}

}
