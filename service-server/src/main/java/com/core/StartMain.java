package com.core;

import jdk.nashorn.internal.runtime.linker.Bootstrap;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author caizhenya
 * @Date 2020/8/16
 * @Descrition
 **/
@Configuration
@ComponentScan("com.core")
public class StartMain {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(StartMain.class);
    }

}
