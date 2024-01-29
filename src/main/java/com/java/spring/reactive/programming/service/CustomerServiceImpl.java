package com.java.spring.reactive.programming.service;

import com.java.spring.reactive.programming.repository.CustomerRepository;
import com.java.spring.reactive.programming.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements  CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Mono<Customer> saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Flux<Customer> findAllCustomers() {
        return customerRepository.findAll().switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<Customer> findByCustomerId(Long customerId) {
        return customerRepository.findById(customerId).switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Void> deleteCustomer(Long id) {
        return customerRepository.deleteById(id);
    }
}
