package com.nttdata.semana1.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.semana1.model.BankAccounts;
import java.util.List;

@Repository
public interface BankAccountsRepository extends MongoRepository<BankAccounts,String>{	

}
