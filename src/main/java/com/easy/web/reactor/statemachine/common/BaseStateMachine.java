package com.easy.web.reactor.statemachine.common;

import com.easy.web.reactor.statemachine.etl.state.States;
import lombok.Data;

/**
 * @author think
 * @date 2021/3/17
 */
@Data
public class BaseStateMachine {
    /**
     * @see States
     */
    private String status = States.LISTEN.name();

}
