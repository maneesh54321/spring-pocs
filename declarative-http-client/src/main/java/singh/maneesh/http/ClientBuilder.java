package singh.maneesh.http;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.springframework.core.annotation.AnnotatedElementUtils;
import singh.maneesh.http.annotation.HttpExchange;
import singh.maneesh.http.annotation.HttpExchangeClient;
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
				clientMethodMetadata[i] = new ClientMethodMetadata(methods[i],
						AnnotatedElementUtils.findMergedAnnotation(methods[i], HttpExchange.class) );
			}
			clientMetadata.setMethodMetadataList(clientMethodMetadata);
			System.out.println(clientMetadata);
			T proxyInstance = (T) Proxy.newProxyInstance(
					this.getClass().getClassLoader(),
					new Class[]{clazz},
					new RestClientProxy(clientMetadata));
			return proxyInstance;
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
