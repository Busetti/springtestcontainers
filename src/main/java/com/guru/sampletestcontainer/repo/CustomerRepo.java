package com.guru.sampletestcontainer.repo;

import com.guru.sampletestcontainer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
}
