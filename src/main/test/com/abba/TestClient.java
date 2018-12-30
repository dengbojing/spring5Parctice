package com.abba;

import com.abba.model.User;
import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class TestClient {

    @Test
    public void test(){
        WebClient client = WebClient.create("http://localhost:8088");
        Mono<User> employeeMono = client.get()
                .uri("/user/{id}", "1")
                .retrieve()
                .bodyToMono(User.class);

        employeeMono.subscribe(System.out::println);
    }

}
