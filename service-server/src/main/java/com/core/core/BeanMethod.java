package com.core.core;

import java.lang.reflect.Method;

/**
 * @Author caizhenya
 * @Date 2020/8/16
 * @Descrition
 **/
public class BeanMethod {

    private Object bean;

    private Method method;

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

}
