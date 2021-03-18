package com.easy.web.reactor.statemachine.etl.state;

/**
 * @author think
 * @date 2021/3/17
 */
public enum States {
    LISTEN,
    DOWNLOAD,
    INSERT,
    UPDATE,
    CALCULATION,
    UPLOAD
}