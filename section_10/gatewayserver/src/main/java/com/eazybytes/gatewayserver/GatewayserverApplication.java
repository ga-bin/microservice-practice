package com.eazybytes.gatewayserver;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	// custom dynamic routing configuration
	@Bean
	public RouteLocator eaxyBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
						.route(p -> p
							.path("/eazybank/accounts/**")
							.filters(f -> f.rewritePath("/eazybank/accounts/(?<segment>.*)","/${segment}")
										.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
										.circuitBreaker(config -> config.setName("accountsCircuitBreaker")
											.setFallbackUri("forward:/contactSupport")))
							.uri("lb://ACCOUNTS"))
						.route(p -> p
							.path("/eazybank/loans/**")
							.filters(f -> f.rewritePath("/eazybank/loans/(?<segment>.*)","/${segment}")
									.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
									// 어플리케이션 요청 실패 시 3번 시도해봄
									// insert, update, delete들은 여러 side effect가 발생할 가능성이 있으므로 get에 대해서만 해봄
									// backoff에서 최초 재시도시 대기시간 100ms, 최대 대기시간 1000ms(1초)로 설정
									// 재시도 시도시 마다 2배씩 지수로 증가
									.retry(retryConfig -> retryConfig.setRetries(3)
										.setMethods(HttpMethod.GET)
										.setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true))) 
							.uri("lb://LOANS"))	
						.route(p -> p
							.path("/eazybank/cards/**")
							.filters(f -> f.rewritePath("/eazybank/cards/(?<segment>.*)","/${segment}")
									.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
									.requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter()).setKeyResolver(userKeyResolver()))
							)	
							.uri("lb://CARDS"))
						.build();
	}

	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
				.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build()).build());
	}

	// defaultReplenishRate 
	@Bean
	public RedisRateLimiter redisRateLimiter() {
		// (한 번에 처리할 수 있는 최대 요청 수, 초당 몇 개 요청, 요청당 필요한 토큰의 수)
		return new RedisRateLimiter(1, 1, 1);
	}

	@Bean
	KeyResolver userKeyResolver() {
		return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
							.defaultIfEmpty("anonymous");
	}
}

	

