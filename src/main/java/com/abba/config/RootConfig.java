package com.abba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author dengbojing
 */
@Configuration
@ComponentScan({"com.abba.service","com.abba.filter","com.abba.handler"})
public class RootConfig {
    /*@Bean
    RouterFunction<ServerResponse> getEmployeeByIdRoute() {
        return route(GET("/employees/{id}"),
                req -> ok().body();
    }*/
}
