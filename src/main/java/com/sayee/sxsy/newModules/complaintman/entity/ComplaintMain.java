package com.sayee.sxsy.newModules.complaintman.entity;

import java.util.Date;

public class ComplaintMain {
    private String complaintMainId;//主键

    private String caseNumber;//案件编号

    private String patientName;//患者姓名

    private String patientSex;//患者性别 字典

    private Byte patientAge;//患者年龄

    private String patientCard;//患者身份证

    private String patientMobile;//患方联系电话

    private String involveHospital;//涉及医院

    private String hospitalLevel;//医院级别

    private String hospitalGrade;//医院等级

    private String involveDepartment;//涉及科室,多个逗号隔开

    private String involveEmployee;//涉及人员

    private String isMajor;//是否重大.重大表的主键 之前的弃用，现在主表为主

    private String procInsId;//流程实例id

    private String source;//案子的来源  1医调委录入 2 医院录入 3.小程序  默认是1

    private String createBy;//

    private Date createDate;//

    private String updateBy;//

    private Date updateDate;//

    private String delFlag;//

    public String getComplaintMainId() {
        return complaintMainId;
    }

    public void setComplaintMainId(String complaintMainId) {
        this.complaintMainId = complaintMainId == null ? null : complaintMainId.trim();
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber == null ? null : caseNumber.trim();
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex == null ? null : patientSex.trim();
    }

    public Byte getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Byte patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientCard() {
        return patientCard;
    }

    public void setPatientCard(String patientCard) {
        this.patientCard = patientCard == null ? null : patientCard.trim();
    }

    public String getPatientMobile() {
        return patientMobile;
    }

    public void setPatientMobile(String patientMobile) {
        this.patientMobile = patientMobile == null ? null : patientMobile.trim();
    }

    public String getInvolveHospital() {
        return involveHospital;
    }

    public void setInvolveHospital(String involveHospital) {
        this.involveHospital = involveHospital == null ? null : involveHospital.trim();
    }

    public String getHospitalLevel() {
        return hospitalLevel;
    }

    public void setHospitalLevel(String hospitalLevel) {
        this.hospitalLevel = hospitalLevel == null ? null : hospitalLevel.trim();
    }

    public String getHospitalGrade() {
        return hospitalGrade;
    }

    public void setHospitalGrade(String hospitalGrade) {
        this.hospitalGrade = hospitalGrade == null ? null : hospitalGrade.trim();
    }

    public String getInvolveDepartment() {
        return involveDepartment;
    }

    public void setInvolveDepartment(String involveDepartment) {
        this.involveDepartment = involveDepartment == null ? null : involveDepartment.trim();
    }

    public String getInvolveEmployee() {
        return involveEmployee;
    }

    public void setInvolveEmployee(String involveEmployee) {
        this.involveEmployee = involveEmployee == null ? null : involveEmployee.trim();
    }

    public String getIsMajor() {
        return isMajor;
    }

    public void setIsMajor(String isMajor) {
        this.isMajor = isMajor == null ? null : isMajor.trim();
    }

    public String getProcInsId() {
        return procInsId;
    }

    public void setProcInsId(String procInsId) {
        this.procInsId = procInsId == null ? null : procInsId.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
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

    @Override
    public String toString() {
        return "ComplaintMain{" +
                "complaintMainId='" + complaintMainId + '\'' +
                ", caseNumber='" + caseNumber + '\'' +
                ", patientName='" + patientName + '\'' +
                ", patientSex='" + patientSex + '\'' +
                ", patientAge=" + patientAge +
                ", patientCard='" + patientCard + '\'' +
                ", patientMobile='" + patientMobile + '\'' +
                ", involveHospital='" + involveHospital + '\'' +
                ", hospitalLevel='" + hospitalLevel + '\'' +
                ", hospitalGrade='" + hospitalGrade + '\'' +
                ", involveDepartment='" + involveDepartment + '\'' +
                ", involveEmployee='" + involveEmployee + '\'' +
                ", isMajor='" + isMajor + '\'' +
                ", procInsId='" + procInsId + '\'' +
                ", source='" + source + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}