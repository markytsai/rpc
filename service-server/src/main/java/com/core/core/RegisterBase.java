package com.core.core;

import com.core.req.RpcRequest;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author caizhenya
 * @Date 2020/8/16
 * @Descrition 模拟远程调用，暂时没有注册中心
 **/
public class RegisterBase {

    /** 用来存储发布的服务的实例(服务调用的路由) */
    public static Map<String, BeanMethod> ROUTING = new ConcurrentHashMap<String, BeanMethod>();

    /** 单例模式创建该代理层实例 */
    private volatile static RegisterBase instance;

    private RegisterBase() {
    }

    public static RegisterBase getInstance() {
        if (instance == null) {
            synchronized (RegisterBase.class) {
                if (instance == null) {
                    instance = new RegisterBase();
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