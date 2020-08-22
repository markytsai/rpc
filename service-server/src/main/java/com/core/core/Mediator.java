package com.core.core;

import com.core.req.RpcRequest;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author caizhenya
 * @Date 2020/8/16
 * @Descrition
 **/
public class Mediator {

    /** 用来存储发布的服务的实例(服务调用的路由) */
    public static Map<String, BeanMethod> ROUTING = new ConcurrentHashMap<String, BeanMethod>();

    /** 单例模式创建该代理层实例 */
    private volatile static Mediator instance;

    private Mediator() {
    }

    public static Mediator getInstance() {
        if (instance == null) {
            synchronized (Mediator.class) {
                if (instance == null) {
                    instance = new Mediator();
                }
            }
        }
        return instance;
    }

    public Object processor(RpcRequest rpcRequest) {
        // 路由key
        String routingKey = rpcRequest.getClassName() + "." + rpcRequest.getMethodName();
        BeanMethod beanMethod = ROUTING.get(routingKey);
        if (beanMethod == null) {
            return null;
        }
        // 执行目标方法
        Object bean = beanMethod.getBean();
        Method method = beanMethod.getMethod();
        try {
            return method.invoke(bean, rpcRequest.getArgs());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}