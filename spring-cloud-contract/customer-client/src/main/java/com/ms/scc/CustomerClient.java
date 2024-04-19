package com.ms.scc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Objects;

@Component
public class CustomerClient {

	@Autowired
	private RestTemplate restTemplate;

	public Collection<Customer> getAllCustomers() {
		ResponseEntity<Collection<Customer>> exchange = restTemplate.exchange("http://localhost:8080/customers",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Collection<Customer>>() {
				});
		return exchange.getBody();
	}
}

class Customer {
	private Long id;
	private String name;

	public Customer(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Customer customer = (Customer) o;
		return Objects.equals(getId(), customer.getId()) && Objects.equals(getName(), customer.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName());
	}
}
