package com.nttdata.semana1.repository;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.nttdata.semana1.model.credit;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface creditRepository extends ReactiveCrudRepository<credit, Integer>{

	public Flux<credit> findBycustomerid(String customerid);
	
	public Mono<credit> findBynumber(String number);
	
	public Mono<credit> findById(Integer id);
}

