package singh.maneesh.http;

import java.util.Arrays;

public class ClientMetadata {

	private String baseUrl;

	private ClientMethodMetadata[] methodMetadataList;

	public ClientMetadata(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	@Override
	public String toString() {
		return "ClientMetadata{" +
				"baseUrl='" + baseUrl + '\'' +
				", methodMetadataList=" + Arrays.toString(methodMetadataList) +
				'}';
	}

	public void setMethodMetadataList(ClientMethodMetadata[] methodMetadataList) {
		this.methodMetadataList = methodMetadataList;
	}
}
