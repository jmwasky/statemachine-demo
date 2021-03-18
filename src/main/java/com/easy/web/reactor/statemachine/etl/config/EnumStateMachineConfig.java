package com.easy.web.reactor.statemachine.etl.config;

import com.easy.web.reactor.statemachine.etl.action.ListenAction;
import com.easy.web.reactor.statemachine.etl.event.Events;
import com.easy.web.reactor.statemachine.etl.state.States;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import javax.annotation.Resource;
import java.util.EnumSet;

/**
 * @author think
 * @date 2021/3/17
 */
@Configuration
@EnableStateMachine
public class EnumStateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Resource
    private ListenAction listenAction;

    @Override
    public void configure( StateMachineStateConfigurer<States, Events> states) throws Exception {
        states.withStates().initial(States.LISTEN).states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure( StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions
                .withExternal()
                    .source(States.LISTEN).target(States.DOWNLOAD)
                    .event(Events.NOTICE_DOWNLOAD)
                    .and()
                .withExternal()
                    .source(States.DOWNLOAD).target(States.INSERT)
                    .event(Events.INSERT_START)
                    .and()
                .withExternal()
                    .source(States.INSERT).target(States.UPDATE)
                    .event(Events.UPDATE_START)
                    .and()
                .withExternal()
                    .source(States.UPDATE).target(States.CALCULATION)
                    .event(Events.CALCULATION_START)
                    .and()
                .withExternal()
                    .source(States.CALCULATION).target(States.UPLOAD)
                    .event(Events.UPDATE_START)
                    .and()
                .withExternal()
                        .source(States.UPLOAD).target(States.LISTEN)
                        .event(Events.ROLLBACK);
    }
}