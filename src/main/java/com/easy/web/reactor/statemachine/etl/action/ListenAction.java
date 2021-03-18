package com.easy.web.reactor.statemachine.etl.action;

import com.easy.web.reactor.statemachine.etl.event.Events;
import com.easy.web.reactor.statemachine.etl.state.States;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

/**
 * @author think
 * @date 2021/3/17
 */
@Component
public class ListenAction implements Action<States, Events> {
    @Override
    public void execute( StateContext<States, Events> context ) {
       /* context.getExtendedState().getVariables().put(Variables.ELAPSEDTIME, 0l);
        context.getExtendedState().getVariables().put(Variables.TRACK, 0);*/

    }
}
