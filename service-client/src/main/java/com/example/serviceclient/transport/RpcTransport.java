package com.example.serviceclient.transport;

import com.core.req.RpcRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Author caizhenya
 * @Date 2020/8/16
 * @Descrition
 **/
@Component
public class RpcTransport {

    @Value("${rpc.host}")
    private String host;
    @Value("${rpc.port}")
    private int port;

    public Object transport(RpcRequest rpcRequest) {

        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            Socket socket = new Socket(host, port);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rpcRequest);
            outputStream.flush();

            inputStream = new ObjectInputStream(socket.getInputStream());
            return inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
//            closeStream(inputStream, outputStream);
        }
        return null;



    }
}
