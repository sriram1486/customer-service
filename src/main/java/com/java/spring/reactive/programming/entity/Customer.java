package com.java.spring.reactive.programming.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor


@Document(collection = "customers")
public class Customer {

    @Id
    private long customerId;
    private String customerName;
    private String email;
    private String gender;
    private String dateOfBirth;
    private String address;
}
