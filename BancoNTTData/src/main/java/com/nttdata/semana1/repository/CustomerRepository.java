package com.nttdata.semana1.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.semana1.model.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer,String>{

}
