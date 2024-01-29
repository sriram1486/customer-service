package com.java.spring.reactive.programming.repository;

import com.java.spring.reactive.programming.entity.Customer;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CustomerRepository  extends ReactiveMongoRepository<Customer,Long> {

    @Query("{'customerName': ?0}")
    Flux<Customer> findByACustomerName(String author);

}
