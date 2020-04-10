package com.sayee.sxsy.newModules.training.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Train {
    private String trainId;

    private String title;

    private String vidioType;

    private String department;

    private String path;

    private String send;

    private String introduce;

    private String score;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String delFlag;

    private String picturePath;

    private String payment;

    private String courseChapter;

    private String buyNumber;

    private BigDecimal videoPrice;

    private String intendedFor;

    private String preknowledge;

    private String lecturerName;

    private String lecturerCompany;

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId == null ? null : trainId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getVidioType() {
        return vidioType;
    }

    public void setVidioType(String vidioType) {
        this.vidioType = vidioType == null ? null : vidioType.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send == null ? null : send.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath == null ? null : picturePath.trim();
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment == null ? null : payment.trim();
    }

    public String getCourseChapter() {
        return courseChapter;
    }

    public void setCourseChapter(String courseChapter) {
        this.courseChapter = courseChapter == null ? null : courseChapter.trim();
    }

    public String getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(String buyNumber) {
        this.buyNumber = buyNumber == null ? null : buyNumber.trim();
    }

    public BigDecimal getVideoPrice() {
        return videoPrice;
    }

    public void setVideoPrice(BigDecimal videoPrice) {
        this.videoPrice = videoPrice;
    }

    public String getIntendedFor() {
        return intendedFor;
    }

    public void setIntendedFor(String intendedFor) {
        this.intendedFor = intendedFor == null ? null : intendedFor.trim();
    }

    public String getPreknowledge() {
        return preknowledge;
    }

    public void setPreknowledge(String preknowledge) {
        this.preknowledge = preknowledge == null ? null : preknowledge.trim();
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName == null ? null : lecturerName.trim();
    }

    public String getLecturerCompany() {
        return lecturerCompany;
    }

    public void setLecturerCompany(String lecturerCompany) {
        this.lecturerCompany = lecturerCompany == null ? null : lecturerCompany.trim();
    }
}