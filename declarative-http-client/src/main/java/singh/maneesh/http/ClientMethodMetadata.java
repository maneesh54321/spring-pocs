package singh.maneesh.http;

import java.lang.reflect.Method;
import singh.maneesh.http.annotation.HttpExchange;

public class ClientMethodMetadata {
	Method method;

	HttpExchange httpExchange;

	public ClientMethodMetadata(Method method, HttpExchange httpExchange) {
		this.method = method;
		this.httpExchange = httpExchange;
	}

	@Override
	public String toString() {
		return "ClientMethodMetadata{" +
				"method=" + method +
				", httpExchange=" + httpExchange +
				'}';
	}
}
