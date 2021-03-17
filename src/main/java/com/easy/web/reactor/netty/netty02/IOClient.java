package com.easy.web.reactor.netty.netty02;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author 闪电侠
 * https://juejin.cn/book/6844733738119593991/section/6844733738270588942
 */
public class IOClient {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                }
            } catch (IOException e) {
            }
        }).start();
    }
}