package singh.maneesh.http.params;

import java.lang.reflect.Parameter;

public record RequestBody(Class<?> aClass, Parameter parameter) {
}
