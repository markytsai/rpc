package com.core.core;

import com.core.anno.RpcRemoteService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author caizhenya
 * @Date 2020/8/16
 * @Descrition
 **/
@Component
public class InitialMerdiator implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(RpcRemoteService.class)) {
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                String routingKey = bean.getClass().getInterfaces()[0].getName() + "." + method.getName();
                BeanMethod beanMethod = new BeanMethod();
                beanMethod.setBean(bean);
                beanMethod.setMethod(method);
                Mediator.ROUTING.put(routingKey, beanMethod);
            }
        }
        return bean;
    }
}
