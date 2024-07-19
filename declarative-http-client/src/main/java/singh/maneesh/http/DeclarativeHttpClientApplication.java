package singh.maneesh.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import singh.maneesh.http.test.HelloClient;

//@SpringBootApplication
public class DeclarativeHttpClientApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DeclarativeHttpClientApplication.class, args);
		HelloClient helloClient = new ClientBuilder().buildClient(HelloClient.class);
		String output = helloClient.hello("Maneesh");
		System.out.println(output);
	}

}
