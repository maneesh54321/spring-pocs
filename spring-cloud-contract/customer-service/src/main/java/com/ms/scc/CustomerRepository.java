package com.ms.scc;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerRepository {

	public List<Customer> getAllCustomers(){
		return List.of(new Customer(1L, "Ram"), new Customer(2L, "Mohan"));
	}
}
