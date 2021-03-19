package com.easy.web.reactor.statemachine.etl.transition;

import com.easy.web.reactor.statemachine.common.BaseStateMachine;
import com.easy.web.reactor.statemachine.annotation.StatesOnTransition;
import com.easy.web.reactor.statemachine.etl.event.Events;
import com.easy.web.reactor.statemachine.etl.state.States;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.WithStateMachine;

import javax.annotation.Resource;

/**
 * @author think
 */
@WithStateMachine
@Data
@Slf4j
public class DownloadStateTransition extends BaseStateMachine {

    @Resource
    private StateMachine<States, Events> stateMachine;

    @StatesOnTransition(target = States.DOWNLOAD)
    public void download(Message<Events> message, Exception exception) {
        log.info("------- status.id:{}", stateMachine.getState().getId());
        log.info("------- status.getVariables:{}",stateMachine.getExtendedState().getVariables());
        log.info("------- status.uuid:{}",stateMachine.getUuid());
        log.info("------- message.getHeaders:{}",message.getHeaders());
        log.info("--------------准备下载. target status:{}", States.DOWNLOAD.name());

        stateMachine.getExtendedState().getVariables().put("download", "downloadtest");
        setStatus(States.DOWNLOAD.name());
        /*int a = 1 /0;
        throw new IllegalArgumentException("hello");*/
        try {
            int a = 1 /0;
        } catch (Exception ex) {
            // 错误主动发到状态机
            stateMachine.setStateMachineError(ex);
            ex.printStackTrace();
        }

    }
}