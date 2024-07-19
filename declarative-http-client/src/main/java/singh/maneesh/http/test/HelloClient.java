package singh.maneesh.http.test;

import org.springframework.web.bind.annotation.PathVariable;
import singh.maneesh.http.annotation.GetCall;
import singh.maneesh.http.annotation.HttpExchangeClient;

@HttpExchangeClient
public interface HelloClient {

	@GetCall(uri = "/hello", headers = {})
	String hello(@PathVariable String name);
}
