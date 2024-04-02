package com.nav.cloudGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
public class NavCloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(NavCloudGatewayApplication.class, args);
	}

	/* This bean is to configure the Redis Rate limiter with this configuration
	 * You also need to provide configuration either in Application.yml of gateway route or
	 * in Java code of  RoutLocater Bean*/ 
	@Bean
	KeyResolver userKeySolver() {
		return exchange -> Mono.just("userKey");
	}
	
	/* This is the factory to enable resilience circuit breaker with this configuration
	 * You also need to provide configuration either in Application.yml of gateway route or
	 * in Java code of  RoutLocater Bean
	 * 
	 * */
	
	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory.configureDefault(
				id -> new Resilience4JConfigBuilder(id)
						.circuitBreakerConfig(
								CircuitBreakerConfig.ofDefaults()

						).build()
		);
	}
	
	
}
