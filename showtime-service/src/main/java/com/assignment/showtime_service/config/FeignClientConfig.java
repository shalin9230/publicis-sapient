package com.assignment.showtime_service.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.RequestTemplate;

import java.util.Map;

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {

            @Override
            public void apply(RequestTemplate requestTemplate) {
                Map<String, String> headers = JwtValidationFilter.HEADER_HOLDER.get();

                if (headers != null) {
                    headers.forEach(requestTemplate::header);;
                }
            }
        };
    }
}
