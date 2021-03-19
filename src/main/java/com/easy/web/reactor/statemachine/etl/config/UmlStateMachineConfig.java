package com.easy.web.reactor.statemachine.etl.config;

import com.easy.web.reactor.statemachine.etl.event.Events;
import com.easy.web.reactor.statemachine.etl.listener.StateMachineLogListener;
import com.easy.web.reactor.statemachine.etl.state.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineModelConfigurer;
import org.springframework.statemachine.config.model.StateMachineModelFactory;
import org.springframework.statemachine.event.StateMachineEvent;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.uml.UmlStateMachineModelFactory;

import javax.annotation.Resource;

/**
 * @author think
 * @date 2021/3/18
 */
@Configuration
@EnableStateMachine(contextEvents = false)
@Slf4j
public class UmlStateMachineConfig extends StateMachineConfigurerAdapter<String, String> {


    @Resource
    private StateMachineLogListener listener;

    @Override
    public void configure( StateMachineConfigurationConfigurer<String, String> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(listener);
    }

    @Override
    public void configure(StateMachineModelConfigurer<String, String> model)
            throws Exception {
        model.withModel().factory(modelFactory());
    }

    @Bean
    public StateMachineModelFactory<String, String> modelFactory() {
        return new UmlStateMachineModelFactory("classpath:etl-flow.uml");
    }


   /* @Bean
    public StateMachineLogListener stateMachineLogListener() {
        StateMachineLogListener listener = new StateMachineLogListener();
        return listener;
    }*/
}
