package com.zfy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@EnableEurekaClient
@SpringBootApplication
public class GatewayApplication{
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }
    @Bean
    public HostAddrKeyResolver hostAddrKeyResolver(){
        return new HostAddrKeyResolver();
    }
}

class HostAddrKeyResolver implements KeyResolver{
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        String hostAddress = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        return Mono.just(hostAddress);
    }
}