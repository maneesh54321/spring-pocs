package contracts.CustomerServiceTest

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

Contract.make {
    description("Should return all customers.")
    request {
        url("/customers")
        method GET()
    }
    response {
        status 200
        headers {
            header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        }
        body([[id: 1L, name: "Ram"], [id: 2L, name: "Mohan"]])
    }
}