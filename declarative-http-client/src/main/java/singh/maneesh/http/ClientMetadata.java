package singh.maneesh.http;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ClientMetadata {

	private final String baseUrl;

	Map<Method, ClientMethodMetadata> methodMetadataMap;

	public ClientMetadata(String baseUrl) {
		this.baseUrl = baseUrl;
		this.methodMetadataMap = new HashMap<>();
	}

	public void setMethodMetadataList(ClientMethodMetadata[] methodMetadataList) {
		Arrays.stream(methodMetadataList)
				.forEach(methodMetadata -> methodMetadataMap.put(methodMetadata.method(), methodMetadata));
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public Map<Method, ClientMethodMetadata> getMethodMetadataMap() {
		return methodMetadataMap;
	}
}
