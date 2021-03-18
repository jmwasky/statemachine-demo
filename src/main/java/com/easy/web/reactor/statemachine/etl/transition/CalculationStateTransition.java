package com.easy.web.reactor.statemachine.etl.transition;

import com.easy.web.reactor.statemachine.common.BaseStateMachine;
import com.easy.web.reactor.statemachine.annotation.StatesOnTransition;
import com.easy.web.reactor.statemachine.etl.event.Events;
import com.easy.web.reactor.statemachine.etl.state.States;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @author think
 */
@WithStateMachine
@Data
@Slf4j
public class CalculationStateTransition extends BaseStateMachine {

    @StatesOnTransition(target = States.CALCULATION)
    public void calculation(Message<Events> message) {
        log.info("--------------计算节点. target status:{}", States.CALCULATION.name());
        setStatus(States.CALCULATION.name());
    }
}