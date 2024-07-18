package singh.maneesh.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import singh.maneesh.http.test.HelloClient;

//@SpringBootApplication
public class DeclarativeHttpClientApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DeclarativeHttpClientApplication.class, args);
		new ClientBuilder().buildClient(HelloClient.class);
	}

}
