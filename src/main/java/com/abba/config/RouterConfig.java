package com.abba.config;

import com.abba.handler.UserHandler;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

/**
 * @author dengbojing
 */
@Configuration
@EnableWebFlux
public class RouterConfig {

    //TODO
    /**
     * 未完成
     * @param userHandler
     * @return
     */
    @Bean
    @Resource
    public RouterFunction<ServerResponse> routerFunction(final UserHandler userHandler) {
        return RouterFunctions.route(GET("/userhandler"), request ->
                request.queryParam("operator").map(operator ->
                        Mono.justOrEmpty(ReflectionUtils.findMethod(UserHandler.class, operator, ServerRequest.class))
                                .flatMap(method -> (Mono<ServerResponse>) ReflectionUtils.invokeMethod(method, userHandler, request))
                                .switchIfEmpty(ServerResponse.badRequest().build())
                                .onErrorResume(ex -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build()))
                        .orElse(ServerResponse.badRequest().build()));
    }

}
