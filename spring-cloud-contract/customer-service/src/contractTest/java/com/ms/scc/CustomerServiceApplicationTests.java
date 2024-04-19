package com.ms.scc;

import com.ms.scc.Customer;
import com.ms.scc.CustomerController;
import com.ms.scc.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class CustomerServiceApplicationTests {

	@Autowired
	private CustomerController customerController;

	@MockBean
	private CustomerRepository customerRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() throws Exception {
		Mockito.when(customerRepository.getAllCustomers())
				.thenReturn(List.of(new Customer(1L, "Ram"), new Customer(2L, "Mohan")));
		this.mockMvc.perform(MockMvcRequestBuilders.get("/customers"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("@.[0].id").value(1L))
				.andExpect(MockMvcResultMatchers.jsonPath("@.[0].name").value("Ram"))
				.andExpect(MockMvcResultMatchers.jsonPath("@.[1].id").value(2L))
				.andExpect(MockMvcResultMatchers.jsonPath("@.[1].name").value("Mohan"));
	}

}
