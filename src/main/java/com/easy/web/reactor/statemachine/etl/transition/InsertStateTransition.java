package com.easy.web.reactor.statemachine.etl.transition;

import com.easy.web.reactor.statemachine.common.BaseStateMachine;
import com.easy.web.reactor.statemachine.annotation.StatesOnTransition;
import com.easy.web.reactor.statemachine.etl.event.Events;
import com.easy.web.reactor.statemachine.etl.state.States;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.WithStateMachine;

import javax.annotation.Resource;

/**
 * @author think
 * @date 2021/3/17
 */
@WithStateMachine
@Data
@Slf4j
public class InsertStateTransition extends BaseStateMachine {

    @Resource
    private StateMachine<States, Events> stateMachine;

    @StatesOnTransition(target = States.INSERT)
    public void insert(Message<Events> message) {
        log.info("------- status.uuid:{}",stateMachine.getUuid());
        log.info("------- message.getHeaders:{}",message.getHeaders());
        log.info("--------------插入数据. target status:{}", States.INSERT.name());

        log.info("--------------getVariables:{}",stateMachine.getExtendedState().getVariables().get("download"));

        setStatus(States.INSERT.name());
    }
}
