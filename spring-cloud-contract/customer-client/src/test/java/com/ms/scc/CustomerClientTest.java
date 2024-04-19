package com.ms.scc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CustomerClientApplication.class)
//@AutoConfigureWireMock(port = 8080)
//@AutoConfigureJson
@AutoConfigureStubRunner(ids = "com.ms.scc:customer-service-stub:+:8080", stubsMode= StubRunnerProperties.StubsMode.LOCAL)
class CustomerClientTest {

	@Autowired
	private CustomerClient customerClient;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testGetCustomers() throws JsonProcessingException {
		/*WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/customers"))
				.willReturn(
						WireMock.aResponse()
								.withStatus(200)
								.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
								.withBody(jsonForCustomer(new Customer(1L, "Ram"), new Customer(2L, "Mohan")))
				));*/
		Collection<Customer> allCustomers = this.customerClient.getAllCustomers();
		BDDAssertions.then(allCustomers).size().isEqualTo(2);
		BDDAssertions.then(allCustomers.iterator().next().getId()).isEqualTo(1L);
		BDDAssertions.then(allCustomers.iterator().next().getName()).isEqualTo("Ram");
	}

	/*private String jsonForCustomer(Customer ... customers) throws JsonProcessingException {
		List<Customer> customersList = Arrays.asList(customers);
		return objectMapper.writeValueAsString(customersList);
	}*/
}