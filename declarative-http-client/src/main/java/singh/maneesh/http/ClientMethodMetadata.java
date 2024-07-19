package singh.maneesh.http;

import java.lang.reflect.Method;
import singh.maneesh.http.annotation.HttpExchange;

public record ClientMethodMetadata(Method method, HttpExchange httpExchange) {
}
