package com.example.serviceprovider.impl;

import com.api.ApplyOrder;
import com.api.ApplyOrderSerivce;
import com.core.anno.RpcRemoteService;

/**
 * @Author caizhenya
 * @Date 2020/8/16
 * @Descrition
 **/
@RpcRemoteService
public class OrderServiceImpl implements ApplyOrderSerivce {
    @Override
    public ApplyOrder apply(String applicant) {
        ApplyOrder applyOrder = new ApplyOrder();
        applyOrder.setApplicant(applicant);
        applyOrder.setApplyOrderId("1111"+applicant);
        return applyOrder;
    }

    @Override
    public String getName() {
        return "I am caizhenya";
    }

}
