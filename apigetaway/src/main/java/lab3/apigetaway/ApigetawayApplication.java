package lab3.apigetaway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class ApigetawayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApigetawayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("artGalleries", r -> r.host("localhost:8084")
                        .and()
                        .path("/api/artGalleries/{adress}", "/api/artGalleries")
                        .uri("http://localhost:8083"))
                .route("paintings", r -> r.host("localhost:8084")
                        .and()
                        .path("/api/paintings", "/api/paintings/**", "/api/artGalleries/{adress}/paintings", "/api/artGalleries/{adress}/paintings/**")
                        .uri("http://localhost:8082"))
                .build();
    }
    @Bean
    public CorsWebFilter corsWebFilter() {

        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        corsConfig.addAllowedHeader("*");

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }

}
