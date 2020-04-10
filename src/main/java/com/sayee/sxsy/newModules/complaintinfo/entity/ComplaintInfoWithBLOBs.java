package com.sayee.sxsy.newModules.complaintinfo.entity;

public class ComplaintInfoWithBLOBs extends ComplaintInfo {
    private String summaryOfDisputes;//投诉纠纷概要

    private String appeal;//诉求

    private String handlePass;//处理经过

    private String handleResult;//处理结果

    public String getSummaryOfDisputes() {
        return summaryOfDisputes;
    }

    public void setSummaryOfDisputes(String summaryOfDisputes) {
        this.summaryOfDisputes = summaryOfDisputes == null ? null : summaryOfDisputes.trim();
    }

    public String getAppeal() {
        return appeal;
    }

    public void setAppeal(String appeal) {
        this.appeal = appeal == null ? null : appeal.trim();
    }

    public String getHandlePass() {
        return handlePass;
    }

    public void setHandlePass(String handlePass) {
        this.handlePass = handlePass == null ? null : handlePass.trim();
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult == null ? null : handleResult.trim();
    }

    @Override
    public String toString() {
        return "ComplaintInfoWithBLOBs{" +
                "summaryOfDisputes='" + summaryOfDisputes + '\'' +
                ", appeal='" + appeal + '\'' +
                ", handlePass='" + handlePass + '\'' +
                ", handleResult='" + handleResult + '\'' +
                '}';
    }
}