package com.easy.web.reactor.controller;

import com.easy.web.reactor.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.time.Duration;
import java.util.Objects;

/**
 * @author think
 * @date 2021/2/7
 */
@RestController
@RequestMapping("/api/user")
public class DemoController {

    @GetMapping("/fromSupplier")
    public Mono<User> fromSupplier() {
        User user = new User();
        user.setAge("121");
        user.setUserName("userName");
        return Mono.fromSupplier(() -> user);
    }

    @GetMapping("/just")
    public Mono<String> just() {
        return Mono.just("Hello World!");
    }

    @GetMapping("/create")
    public Mono<String> create() {

        return Mono.create(stringMonoSink ->
                stringMonoSink.success("test"));
    }

    @GetMapping("/interval")
    public Flux<Long> interval() {

        return Flux.interval(Duration.ofSeconds(1), Duration.ofSeconds(1))
                .doOnNext(System.out::println);
    }



}
