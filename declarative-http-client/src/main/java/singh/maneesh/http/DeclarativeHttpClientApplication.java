package singh.maneesh.http;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.PatternMatchUtils;
import singh.maneesh.http.test.HelloClient;

//@SpringBootApplication
public class DeclarativeHttpClientApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DeclarativeHttpClientApplication.class, args);
//		HelloClient helloClient = new ClientBuilder().buildClient(HelloClient.class);
//		String output = helloClient.hello("Maneesh");
//		System.out.println(output);

//		boolean b = PatternMatchUtils.simpleMatch("[a-zA-Z0-9]*", "abc");
//		System.out.println("Matching? " + b);
//		Pattern pattern = Pattern.compile(".*\\{[a-zA-Z0-9]+}");
//		System.out.println(pattern.matcher("/hello/{abc}").appendReplacement());

		Map<String, String> pathVars= new HashMap<>();
		pathVars.put("var1", "value1");
		pathVars.put("var2", "value2");
		var url = "/hello/{var1}/{var2}";
		var pathVarPattern = Pattern.compile("\\{[a-zA-Z0-9]+}");
		var finalUrl = new StringBuilder();
		var urlMatcher = pathVarPattern.matcher(url);
		while (urlMatcher.find()) {
			String matchedVar = urlMatcher.toMatchResult().group();
			urlMatcher.appendReplacement(finalUrl, pathVars.get(matchedVar.substring(1, matchedVar.length()-1)));
		}
		System.out.println("Final Url: " + finalUrl);

//		Pattern p = Pattern. compile("cat");
//		Matcher m = p. matcher("one cat two cats in the yard");
//		StringBuffer sb = new StringBuffer();
//		while (m.find()) {
//			m.appendReplacement(sb, "dog");
//		}
//		m.appendTail(sb);
//		System. out. println(sb.toString());
	}

}
