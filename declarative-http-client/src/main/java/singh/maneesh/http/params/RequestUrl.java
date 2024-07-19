package singh.maneesh.http.params;

import java.util.regex.Pattern;
import org.springframework.util.PatternMatchUtils;

public class RequestUrl {

	private String url;

	public RequestUrl(String url) {
		this.url = url;
	}

	public void addPathVariables(PathVariable[] pathVariables) {

	}
}
