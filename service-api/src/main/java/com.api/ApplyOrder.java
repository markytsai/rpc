package com.api;

import java.io.Serializable;

/**
 * @Author caizhenya
 * @Date 2020/8/16
 * @Descrition
 **/
public class ApplyOrder implements Serializable {

    private String applyOrderId;

    private String applicant;

    public String getApplyOrderId() {
        return applyOrderId;
    }

    public void setApplyOrderId(String applyOrderId) {
        this.applyOrderId = applyOrderId;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }
}
