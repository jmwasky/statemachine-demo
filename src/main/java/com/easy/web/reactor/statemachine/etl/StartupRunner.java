package com.easy.web.reactor.statemachine.etl;

import com.easy.web.reactor.statemachine.etl.event.Events;
import com.easy.web.reactor.statemachine.etl.state.States;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author think
 * @date 2021/3/17
 */
//@Component
public class StartupRunner implements CommandLineRunner {

    @Resource
    private StateMachine<States, Events> stateMachine;

    @Override
    public void run(String... args) throws Exception {
        stateMachine.start();
        stateMachine.sendEvent(Events.NOTICE_DOWNLOAD);
        Message<Events> message = MessageBuilder.withPayload(Events.INSERT_START)
                .setHeader("download", "test").build();
        stateMachine.sendEvent(message);
       // stateMachine.sendEvent(Events.DOWNLOAD_DONE);
        stateMachine.sendEvent(Events.UPDATE_START);
        stateMachine.sendEvent(Events.CALCULATION_START);
        stateMachine.sendEvent(Events.UPDATE_START);
       // stateMachine.sendEvent(Events.UPLOAD_DONE);
        stateMachine.sendEvent(Events.ROLLBACK);
    }
}