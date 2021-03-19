package com.easy.web.reactor.controller;

import org.springframework.statemachine.StateMachine;
import reactor.core.publisher.Mono;

/**
 * @author think
 * @date 2021/3/18
 */
public class BaseController {
    /**
     * print result
     * @param stateMachine
     * @return
     */
    public Mono<String> printState( StateMachine stateMachine) {
        return Mono.create(stringMonoSink ->
                stringMonoSink.success(stateMachine.getState()+""));
    }
}
