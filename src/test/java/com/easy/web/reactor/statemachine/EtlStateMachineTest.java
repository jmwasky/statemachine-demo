package com.easy.web.reactor.statemachine;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineConfig;
import org.springframework.statemachine.test.StateMachineTestPlan;
import org.springframework.statemachine.test.StateMachineTestPlanBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

/**
 * @author think
 * @date 2021/3/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { StateMachineConfig.class})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class EtlStateMachineTest {

    @Autowired
    private StateMachine<String, String> stateMachine;

    @Test
    public void testDeployInstalledInstallOkInstallError() throws Exception {
        Message<String> deploy = MessageBuilder.withPayload("DEPLOY")
                .setHeader("isInstalled", true)
                .setHeader("hasError", true).build();
        StateMachineTestPlan<String, String> plan =
                StateMachineTestPlanBuilder.<String, String>builder()
                        .stateMachine(stateMachine)
                        .step().expectState("READY").and()
                        .step().sendEvent(deploy)
                        .expectStateChanged(5)
                        .expectStateEntered("DEPLOY", "PREPAREDEPLOY", "INSTALL", "ERROR", "READY")
                        .expectStates("READY").and()
                        .build();
        plan.test();
    }

}