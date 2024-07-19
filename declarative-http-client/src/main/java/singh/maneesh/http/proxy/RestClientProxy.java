package singh.maneesh.http.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.RequestBodyUriSpec;
import singh.maneesh.http.ClientMetadata;
import singh.maneesh.http.ClientMethodMetadata;

public class RestClientProxy implements InvocationHandler {

	Map<Method, RequestBodyUriSpec> methodRequestBodyUriSpecMap = new HashMap<>();

	public RestClientProxy(ClientMetadata clientMetadata) {
		String baseUrl = "http://localhost:8080";
		RestClient restClient = RestClient.builder()
				.baseUrl(baseUrl)
				.build();

		Map<Method, ClientMethodMetadata> methodMetadataMap = clientMetadata.getMethodMetadataMap();
		methodMetadataMap.forEach((method, methodMetadata) -> {
			RequestBodyUriSpec requestBodySpec = restClient.method(
					methodMetadata.httpExchange().method().asHttpMethod()
			);
			Arrays.stream(methodMetadata.httpExchange().headers())
					.forEach(requestHeader ->
							requestBodySpec.header(requestHeader.name(), requestHeader.value().split(","))
					);
			requestBodySpec.uri(clientMetadata.getBaseUrl() + methodMetadata.httpExchange().uri());
			methodRequestBodyUriSpecMap.put(method, requestBodySpec);
		});

	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return methodRequestBodyUriSpecMap.get(method).retrieve().body(method.getReturnType());
	}
}
