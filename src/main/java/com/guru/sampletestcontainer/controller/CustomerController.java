package com.guru.sampletestcontainer.controller;

import com.guru.sampletestcontainer.entities.Customer;
import com.guru.sampletestcontainer.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class CustomerController {

    private CustomerRepo customerRepo;

    @Autowired
    public CustomerController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @GetMapping("/customers")
    public List<Customer> allCustomers(){
        return customerRepo.findAll();
    }
}
