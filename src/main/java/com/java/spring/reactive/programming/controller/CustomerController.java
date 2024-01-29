package com.java.spring.reactive.programming.controller;

import com.java.spring.reactive.programming.entity.Customer;
import com.java.spring.reactive.programming.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/findAll")
    public Flux<Customer> getAllCustomers() {
        return customerService.findAllCustomers();
    }

    @PostMapping("/save")
    public Mono<ResponseEntity<Customer>> createCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer)
                .map(savedArticle -> new ResponseEntity<>(savedArticle, HttpStatus.CREATED));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Customer>> getCustomerById(@PathVariable Long customerId) {
        return customerService.findByCustomerId(customerId)
                .map(article -> ResponseEntity.ok(article))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<Customer>> updateCustomer(@PathVariable Long customerId,
                                                       @RequestBody Customer customer) {
        return customerService.findByCustomerId(customerId)
                .flatMap(existingCustomer -> {
                    existingCustomer.setCustomerName(customer.getCustomerName());
                    existingCustomer.setEmail(customer.getEmail());
                    existingCustomer.setGender(customer.getGender());
                    existingCustomer.setDateOfBirth(customer.getDateOfBirth());
                    existingCustomer.setAddress(customer.getAddress());
                    return customerService.saveCustomer(existingCustomer);
                })
                .map(updatedArticle -> new ResponseEntity<>(updatedArticle, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deleteCustomer(@PathVariable Long customerId) {
        return customerService.deleteCustomer(customerId)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .onErrorResume(error -> Mono.just(new ResponseEntity<Void>(HttpStatus.NOT_FOUND)));
    }

}
