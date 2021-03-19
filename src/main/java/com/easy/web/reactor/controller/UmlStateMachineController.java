package com.easy.web.reactor.controller;

import com.easy.web.reactor.statemachine.etl.listener.StateMachineLogListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author think
 * @date 2021/2/7
 */
@RestController
@RequestMapping("/api/state-etl")
public class UmlStateMachineController extends BaseController{

    private final static String[] EVENTS = new String[] { "DEPLOY", "UNDEPLOY" };

    @Resource
    private StateMachine<String, String> stateMachine;

    @Autowired
    private StateMachineLogListener listener;

    @RequestMapping("/state")
    public Mono<String> feedAndGetState( @RequestParam(value = "event", required = false) String event) throws Exception {
        if (StringUtils.hasText(event)) {
            Map<String , Object> headers = new HashMap<>();
            headers.put("fail", true);
            listener.resetMessages();
            stateMachine.sendEvent(MessageBuilder.createMessage(event, new MessageHeaders(headers)));
        }
        return printState(stateMachine);
    }

}
