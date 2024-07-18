package singh.maneesh.http.test;

import org.springframework.web.bind.annotation.PathVariable;
import singh.maneesh.http.annotation.GetCall;
import singh.maneesh.http.annotation.HttpExchangeClient;

@HttpExchangeClient("/base")
public interface HelloClient {

	@GetCall(uri = "/hello")
	String hello(@PathVariable String name);
}
