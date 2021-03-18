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
public class UploadStateTransition extends BaseStateMachine {

    @StatesOnTransition(target = States.UPLOAD)
    public void upload( Message<Events> message) {
        log.info("--------------上传文件. target status:{}", States.UPLOAD.name());
        setStatus(States.UPLOAD.name());
    }
}