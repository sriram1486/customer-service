package com.java.spring.reactive.programming.service;

import com.java.spring.reactive.programming.entity.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    public Mono<Customer> saveCustomer(Customer customer);

    public Flux<Customer> findAllCustomers();

    public Mono<Customer> findByCustomerId(Long id);

    public Mono<Void> deleteCustomer(Long id);

}
