package com.easy.web.reactor.controller;

import com.easy.web.reactor.entity.User;
import com.easy.web.reactor.statemachine.etl.event.Events;
import com.easy.web.reactor.statemachine.etl.state.States;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * @author think
 * @date 2021/2/7
 */
//@RestController
//@RequestMapping("/api/state-etl")
public class StateMachineController extends BaseController {

    @Resource
    private StateMachine<States, Events> stateMachine;

    @GetMapping("/start")
    public Mono<String> start() {
        stateMachine.start();
        return printState(stateMachine);
    }
    @GetMapping("/download")
    public Mono<String> download() {
        Message<Events> message = MessageBuilder.withPayload(Events.NOTICE_DOWNLOAD)
                .setHeader("download", "downloadmessabe")
                .build();

        stateMachine.sendEvent(message);
        return printState(stateMachine);
    }
    @GetMapping("/insert")
    public Mono<String> insert() {
        Message<Events> message = MessageBuilder.withPayload(Events.INSERT_START)
                .setHeader("insert", "message insert").build();
        stateMachine.sendEvent(message);
        return printState(stateMachine);
    }
    @GetMapping("/update")
    public Mono<String> update() {
        stateMachine.sendEvent(Events.UPDATE_START);
        return printState(stateMachine);
    }
    @GetMapping("/calculation")
    public Mono<String> calculation() {
        stateMachine.sendEvent(Events.CALCULATION_START);
        return printState(stateMachine);
    }
    @GetMapping("/upload")
    public Mono<String> upload() {
        stateMachine.sendEvent(Events.UPDATE_START);
        return printState(stateMachine);
    }
    @GetMapping("/toListen")
    public Mono<String> toListen() {
        stateMachine.sendEvent(Events.ROLLBACK);
        return printState(stateMachine);
    }

}
