package com.core.core.transport;

import com.core.core.RegisterBase;
import com.core.req.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Author caizhenya
 * @Date 2020/8/16
 * @Descrition
 **/
public class ProcessorHandler implements Runnable {

    private Socket socket;

    public ProcessorHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            // 反序列化
            RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();

            // 中间代理执行目标方法
            RegisterBase registerBase = RegisterBase.getInstance();
            Object response = registerBase.processor(rpcRequest);
            System.out.println("服务端的执行结果：" + response);

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(response);
            outputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            closeStream(inputStream, outputStream);
        }
    }

}
