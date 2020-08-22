package com.example.serviceclient.proxy;

import com.core.anno.RpcReference;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @Author caizhenya
 * @Date 2020/8/16
 * @Descrition
 **/
@Component
public class ReferenceHandler implements BeanPostProcessor {


    @Autowired
    private RemoteHandler remoteHandler;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {


        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(RpcReference.class)) {
                field.setAccessible(true);
                Object proxy = remoteHandler.getProxy(field);
                try {
                    // 相当于针对加了RpcReference的注解，设置了一个代理，这个代理的实现是invocationHandler
                    field.set(bean, proxy);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }



}
