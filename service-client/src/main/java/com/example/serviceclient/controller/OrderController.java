package com.example.serviceclient.controller;

import com.api.ApplyOrder;
import com.api.ApplyOrderSerivce;
import com.core.anno.RpcReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author caizhenya
 * @Date 2020/8/16
 * @Descrition
 **/
@RestController
@RequestMapping("/")
public class OrderController {

    @RpcReference
    private ApplyOrderSerivce applyOrderSerivce;

    @GetMapping("/apply")
    public ApplyOrder apply() {
        return applyOrderSerivce.apply("caizy");
    }

    @GetMapping("/name")
    public String name() {
        return applyOrderSerivce.getName();
    }
}
