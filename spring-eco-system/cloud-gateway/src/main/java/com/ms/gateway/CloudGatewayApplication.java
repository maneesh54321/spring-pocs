package com.ms.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("todo_route", r -> r.path("/todos")
						.uri("http://localhost:8081"))
//				.route("host_route", r -> r.host("*.myhost.org")
//						.uri("http://httpbin.org"))
//				.route("rewrite_route", r -> r.host("*.rewrite.org")
//						.filters(f -> f.rewritePath("/foo/(?<segment>.*)", "/${segment}"))
//						.uri("http://httpbin.org"))
//				.route("hystrix_route", r -> r.host("*.hystrix.org")
//						.filters(f -> f.hystrix(c -> c.setName("slowcmd")))
//						.uri("http://httpbin.org"))
//				.route("hystrix_fallback_route", r -> r.host("*.hystrixfallback.org")
//						.filters(f -> f.hystrix(c -> c.setName("slowcmd").setFallbackUri("forward:/hystrixfallback")))
//						.uri("http://httpbin.org"))
//				.route("limit_route", r -> r
//						.host("*.limited.org").and().path("/anything/**")
//						.filters(f -> f.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter())))
//						.uri("http://httpbin.org"))
				.build();
	}

}
