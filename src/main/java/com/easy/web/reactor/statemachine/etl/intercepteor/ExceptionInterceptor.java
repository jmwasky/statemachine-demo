package com.easy.web.reactor.statemachine.etl.intercepteor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.access.StateMachineFunction;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author think
 * @date 2021/3/18
 */
@Configuration
@Slf4j
public class ExceptionInterceptor {

    @Resource
    private StateMachine<String, String> stateMachine;

    @Bean
    public void addInterceptor() {
       /* stateMachine.getStateMachineAccessor()
                .doWithRegion(new StateMachineFunction<StateMachineAccess<String, String>>() {
                    @Override
                    public void apply(StateMachineAccess<String, String> function) {
                        function.addStateMachineInterceptor(
                                new StateMachineInterceptorAdapter<String, String>() {
                                    @Override
                                    public Exception stateMachineError(StateMachine<String, String> stateMachine,
                                                                       Exception exception) {
                                        log.info("--- handler  error!!1 ");
                                        return exception;
                                    }
                                });
                    }
                });*/
       stateMachine.getStateMachineAccessor()
               .doWithRegion((StateMachineAccess<String, String> function) -> {
                   function.addStateMachineInterceptor(new StateMachineInterceptorAdapter<String, String>() {
                           @Override
                           public Exception stateMachineError(StateMachine<String, String> stateMachine,
                                                              Exception exception) {
                                   log.info("--- handler  error!!1");
                                   return exception;
                               }
                           });
                   });
    }
}
