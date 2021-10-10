package com.core.core.transport;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @Author caizhenya
 * @Date 2020/8/16
 * @Descrition 启动server监听
 **/
@Component
public class SocketServer implements ApplicationListener<ContextRefreshedEvent> {

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * server end
     * @param contextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8888);
            while (true) {
                Socket accept = serverSocket.accept();
                System.out.println("Accept : " + accept.getInetAddress() + ":"+ accept.getLocalPort());
                executorService.execute(new ProcessorHandler(accept));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
