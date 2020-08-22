package com.example.serviceclient.proxy;

import com.core.req.RpcRequest;
import com.example.serviceclient.transport.RpcTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author caizhenya
 * @Date 2020/8/16
 * @Descrition
 **/
@Component
public class RemoteHandler implements InvocationHandler {

    @Autowired
    private RpcTransport rpcTransport;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setTypes(method.getParameterTypes());
        rpcRequest.setArgs(args);
        return rpcTransport.transport(rpcRequest);
    }

    public Object getProxy(Field field) {
        return Proxy.newProxyInstance(field.getType().getClassLoader(),
                new Class<?>[]{field.getType()}, this);

    }
}
