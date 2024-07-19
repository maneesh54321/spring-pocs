package singh.maneesh.http;

import java.lang.reflect.Method;
import java.util.List;
import singh.maneesh.http.annotation.HttpExchange;
import singh.maneesh.http.params.PathVariable;
import singh.maneesh.http.params.QueryParam;
import singh.maneesh.http.params.RequestBody;

public record ClientMethodMetadata(Method method,
                                   HttpExchange httpExchange,
                                   List<PathVariable> pathVariables,
                                   List<QueryParam> queryParams,
                                   RequestBody requestBody) {

}
