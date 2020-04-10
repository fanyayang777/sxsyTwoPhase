package com.sayee.sxsy.newModules.middleutils.entity;

public class ComuserComplaintinfoidKey {
    private String comuserId;

    private String complaintInfoId;

    public String getComuserId() {
        return comuserId;
    }

    public void setComuserId(String comuserId) {
        this.comuserId = comuserId == null ? null : comuserId.trim();
    }

    public String getComplaintInfoId() {
        return complaintInfoId;
    }

    public void setComplaintInfoId(String complaintInfoId) {
        this.complaintInfoId = complaintInfoId == null ? null : complaintInfoId.trim();
    }
}