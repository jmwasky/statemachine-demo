package com.easy.web.reactor.entity;

import java.io.Serializable;

/**
 * @author think
 * @date 2021/2/7
 */
public class User implements Serializable {
    private static final long serialVersionUID = 2021L;

    private String userName;
    private String age;

    public String getUserName() {
        return userName;
    }

    public void setUserName( String userName ) {
        this.userName = userName;
    }
    public String getAge() {
        return age;
    }

    public void setAge( String age ) {
        this.age = age;
    }
}
