package singh.maneesh.http;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import singh.maneesh.http.annotation.HttpExchange;
import singh.maneesh.http.annotation.HttpExchangeClient;
import singh.maneesh.http.params.QueryParam;
import singh.maneesh.http.proxy.RestClientProxy;

public class ClientBuilder {

	public <T> T buildClient(Class<T> clazz) {
		HttpExchangeClient annotation = clazz.getAnnotation(HttpExchangeClient.class);
		if(annotation != null){
			System.out.println("Declarative client found!!!");
			String baseUri = annotation.value();
			ClientMetadata clientMetadata = new ClientMetadata(baseUri);

			Method[] methods = clazz.getMethods();

			ClientMethodMetadata[] clientMethodMetadata = new ClientMethodMetadata[methods.length];
			for (int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				HttpExchange httpExchange = AnnotatedElementUtils.findMergedAnnotation(method,
						HttpExchange.class);
				var pathVars = new ArrayList<singh.maneesh.http.params.PathVariable>();
				var reqParams = new ArrayList<singh.maneesh.http.params.QueryParam>();
				singh.maneesh.http.params.RequestBody reqBody = null;
				for (Parameter param : method.getParameters()) {
					RequestBody requestBody = param.getAnnotation(RequestBody.class);
					PathVariable pathVariable = param.getAnnotation(PathVariable.class);
					RequestParam requestParam = param.getAnnotation(RequestParam.class);
					if (requestBody != null) {
						if(reqBody != null) {
							throw new RuntimeException("Can't have multiple request bodies!!");
						}
						reqBody = new singh.maneesh.http.params.RequestBody(param.getClass(), param);
					} else if (pathVariable != null) {
						pathVars.add(new singh.maneesh.http.params.PathVariable(
								pathVariable.name().isBlank() ? param.getName() : pathVariable.name(),
								param)
						);
					} else if (requestParam != null) {
						reqParams.add(new QueryParam(requestParam.name(), param));
					}
					throw new RuntimeException("Invalid argument " + param);
				}


				clientMethodMetadata[i] = new ClientMethodMetadata(methods[i], httpExchange, pathVars, reqParams, reqBody);
			}
			clientMetadata.setMethodMetadataList(clientMethodMetadata);
			System.out.println(clientMetadata);
			Object object = Proxy.newProxyInstance(
					this.getClass().getClassLoader(),
					new Class[]{clazz},
					new RestClientProxy(clientMetadata));
			return (T) object;
		}
		return null;
	}

//	private <T> Optional<Annotation> checkIfDeclarativeClient(Class<T> clazz) {
//		Annotation[] annotations = clazz.getAnnotations();
//		return Arrays.stream(annotations)
//				.filter(ann -> ann.annotationType().equals(HttpExchangeClient.class))
//				.findFirst();
//	}

}
