package singh.maneesh.http.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@HttpExchange(method = RequestMethod.GET)
public @interface GetCall {

	@AliasFor(annotation = HttpExchange.class, attribute = "value")
	String value() default "";

	@AliasFor(annotation = HttpExchange.class, attribute = "uri")
	String uri() default "";

	@AliasFor(annotation = HttpExchange.class, attribute = "headers")
	RequestHeader[] headers() default {};
}
