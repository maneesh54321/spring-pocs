package com.ms.scc;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest(classes = CustomerServiceApplication.class)
@ExtendWith(SpringExtension.class)
public class BaseClass {

	@Autowired
	private CustomerController controller;

	@MockBean
	private CustomerRepository customerRepository;

	@BeforeEach
	public void before(){
		RestAssuredMockMvc.standaloneSetup(controller);
		Mockito.when(customerRepository.getAllCustomers())
				.thenReturn(List.of(new Customer(1L, "Ram"), new Customer(2L, "Mohan")));
	}
}
