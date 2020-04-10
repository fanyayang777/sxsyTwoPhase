package com.sayee.sxsy.newModules.complaintinfo.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ComplaintInfo {
    private String complaintId;//主键

    private String complaintMainId;//主表id

    private String visitorName;//访客姓名

    private String visitorMobile;//访客电话

    private String patientRelation;//与患者关系  字典维护

    private String visitorNumber;//来访人数

    private String patientName;//患者姓名

    private String patientSex;//患者姓名

    private Byte patientAge;//患者年龄

    private String involveHospital;//涉及医院

    private String involveDepartment;//涉及科室

    private String involveEmployee;//涉及人员

    private String caseNumber;//案件编号

    private String visitorDate;//案件编号

    private String complaintMode;//投诉方式

    private String isMajor;//是否重大

    private String receptionEmployee;//接待人员

    private String receptionDate;//接待时间

    private String isMediate;//是否进入医调委调解

    private String complaintType;//投诉类别  取基础模块中的类别（投诉原因）

    private String handleWay;//处理方式 1当面处理 2转办处理 （转办处理时显示 转办科室）

    private String shiftHandle;//转办科室  （选择当前医院的科室）

    private String status;//0处理中 1协调中 2结案(当面 处理时的状态)

    private String expectedClosure;//结案预期

    private String closingMethod;//结案方式

    private BigDecimal amountInvolved;//涉及金额

    private String nextLink;//下一处理环节

    private String nextLinkMan;//下一环节处理人

    private String createBy;//创建者ID

    private Date createDate;//创建时间

    private String updateBy;//更新人ID

    private Date updateDate;//修改时间

    private String delFlag;//删除标记

    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId == null ? null : complaintId.trim();
    }

    public String getComplaintMainId() {
        return complaintMainId;
    }

    public void setComplaintMainId(String complaintMainId) {
        this.complaintMainId = complaintMainId == null ? null : complaintMainId.trim();
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName == null ? null : visitorName.trim();
    }

    public String getVisitorMobile() {
        return visitorMobile;
    }

    public void setVisitorMobile(String visitorMobile) {
        this.visitorMobile = visitorMobile == null ? null : visitorMobile.trim();
    }

    public String getPatientRelation() {
        return patientRelation;
    }

    public void setPatientRelation(String patientRelation) {
        this.patientRelation = patientRelation == null ? null : patientRelation.trim();
    }

    public String getVisitorNumber() {
        return visitorNumber;
    }

    public void setVisitorNumber(String visitorNumber) {
        this.visitorNumber = visitorNumber == null ? null : visitorNumber.trim();
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

    public String getInvolveHospital() {
        return involveHospital;
    }

    public void setInvolveHospital(String involveHospital) {
        this.involveHospital = involveHospital == null ? null : involveHospital.trim();
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

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber == null ? null : caseNumber.trim();
    }

    public String getVisitorDate() {
        return visitorDate;
    }

    public void setVisitorDate(String visitorDate) {
        this.visitorDate = visitorDate == null ? null : visitorDate.trim();
    }

    public String getComplaintMode() {
        return complaintMode;
    }

    public void setComplaintMode(String complaintMode) {
        this.complaintMode = complaintMode == null ? null : complaintMode.trim();
    }

    public String getIsMajor() {
        return isMajor;
    }

    public void setIsMajor(String isMajor) {
        this.isMajor = isMajor == null ? null : isMajor.trim();
    }

    public String getReceptionEmployee() {
        return receptionEmployee;
    }

    public void setReceptionEmployee(String receptionEmployee) {
        this.receptionEmployee = receptionEmployee == null ? null : receptionEmployee.trim();
    }

    public String getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(String receptionDate) {
        this.receptionDate = receptionDate == null ? null : receptionDate.trim();
    }

    public String getIsMediate() {
        return isMediate;
    }

    public void setIsMediate(String isMediate) {
        this.isMediate = isMediate == null ? null : isMediate.trim();
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType == null ? null : complaintType.trim();
    }

    public String getHandleWay() {
        return handleWay;
    }

    public void setHandleWay(String handleWay) {
        this.handleWay = handleWay == null ? null : handleWay.trim();
    }

    public String getShiftHandle() {
        return shiftHandle;
    }

    public void setShiftHandle(String shiftHandle) {
        this.shiftHandle = shiftHandle == null ? null : shiftHandle.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getExpectedClosure() {
        return expectedClosure;
    }

    public void setExpectedClosure(String expectedClosure) {
        this.expectedClosure = expectedClosure == null ? null : expectedClosure.trim();
    }

    public String getClosingMethod() {
        return closingMethod;
    }

    public void setClosingMethod(String closingMethod) {
        this.closingMethod = closingMethod == null ? null : closingMethod.trim();
    }

    public BigDecimal getAmountInvolved() {
        return amountInvolved;
    }

    public void setAmountInvolved(BigDecimal amountInvolved) {
        this.amountInvolved = amountInvolved;
    }

    public String getNextLink() {
        return nextLink;
    }

    public void setNextLink(String nextLink) {
        this.nextLink = nextLink == null ? null : nextLink.trim();
    }

    public String getNextLinkMan() {
        return nextLinkMan;
    }

    public void setNextLinkMan(String nextLinkMan) {
        this.nextLinkMan = nextLinkMan == null ? null : nextLinkMan.trim();
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
        return "ComplaintInfo{" +
                "complaintId='" + complaintId + '\'' +
                ", complaintMainId='" + complaintMainId + '\'' +
                ", visitorName='" + visitorName + '\'' +
                ", visitorMobile='" + visitorMobile + '\'' +
                ", patientRelation='" + patientRelation + '\'' +
                ", visitorNumber='" + visitorNumber + '\'' +
                ", patientName='" + patientName + '\'' +
                ", patientSex='" + patientSex + '\'' +
                ", patientAge=" + patientAge +
                ", involveHospital='" + involveHospital + '\'' +
                ", involveDepartment='" + involveDepartment + '\'' +
                ", involveEmployee='" + involveEmployee + '\'' +
                ", caseNumber='" + caseNumber + '\'' +
                ", visitorDate='" + visitorDate + '\'' +
                ", complaintMode='" + complaintMode + '\'' +
                ", isMajor='" + isMajor + '\'' +
                ", receptionEmployee='" + receptionEmployee + '\'' +
                ", receptionDate='" + receptionDate + '\'' +
                ", isMediate='" + isMediate + '\'' +
                ", complaintType='" + complaintType + '\'' +
                ", handleWay='" + handleWay + '\'' +
                ", shiftHandle='" + shiftHandle + '\'' +
                ", status='" + status + '\'' +
                ", expectedClosure='" + expectedClosure + '\'' +
                ", closingMethod='" + closingMethod + '\'' +
                ", amountInvolved=" + amountInvolved +
                ", nextLink='" + nextLink + '\'' +
                ", nextLinkMan='" + nextLinkMan + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}