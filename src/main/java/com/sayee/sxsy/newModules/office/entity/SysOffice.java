package com.sayee.sxsy.newModules.office.entity;

import java.util.Date;

public class SysOffice {
    private String id;

    private String parentId;

    private String parentIds;

    private String name;

    private Long sort;

    private String areaId;//归属区域

    private String officeType;//机构类别 1医调委 2医院  3卫生局',

    private String beds;

    private String code;

    private String type;

    private String grade;

    private String address;

    private String zipCode;

    private String master;//社会统一代码

    private String phone;

    private String fax;

    private String email;

    private String hospitalGrade;

    private String useable;

    private String legalRepresentative;//法定代表人

    private String representPhone;//法定人联系电话

    private String directorCharge;

    private String directorPhone;

    private String disputeHead;

    private String disputePhone;

    private String primaryPerson;

    private String deputyPerson;

    private String isInsured;

    private String insuranceCompany;

    private String insuredTime;//参保日期

    private String insuredEndTime;//参保结束日期

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    private String policyNumber;//保单号

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getOfficeType() {
        return officeType;
    }

    public void setOfficeType(String officeType) {
        this.officeType = officeType == null ? null : officeType.trim();
    }

    public String getBeds() {
        return beds;
    }

    public void setBeds(String beds) {
        this.beds = beds == null ? null : beds.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master == null ? null : master.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getHospitalGrade() {
        return hospitalGrade;
    }

    public void setHospitalGrade(String hospitalGrade) {
        this.hospitalGrade = hospitalGrade == null ? null : hospitalGrade.trim();
    }

    public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable == null ? null : useable.trim();
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative == null ? null : legalRepresentative.trim();
    }

    public String getRepresentPhone() {
        return representPhone;
    }

    public void setRepresentPhone(String representPhone) {
        this.representPhone = representPhone == null ? null : representPhone.trim();
    }

    public String getDirectorCharge() {
        return directorCharge;
    }

    public void setDirectorCharge(String directorCharge) {
        this.directorCharge = directorCharge == null ? null : directorCharge.trim();
    }

    public String getDirectorPhone() {
        return directorPhone;
    }

    public void setDirectorPhone(String directorPhone) {
        this.directorPhone = directorPhone == null ? null : directorPhone.trim();
    }

    public String getDisputeHead() {
        return disputeHead;
    }

    public void setDisputeHead(String disputeHead) {
        this.disputeHead = disputeHead == null ? null : disputeHead.trim();
    }

    public String getDisputePhone() {
        return disputePhone;
    }

    public void setDisputePhone(String disputePhone) {
        this.disputePhone = disputePhone == null ? null : disputePhone.trim();
    }

    public String getPrimaryPerson() {
        return primaryPerson;
    }

    public void setPrimaryPerson(String primaryPerson) {
        this.primaryPerson = primaryPerson == null ? null : primaryPerson.trim();
    }

    public String getDeputyPerson() {
        return deputyPerson;
    }

    public void setDeputyPerson(String deputyPerson) {
        this.deputyPerson = deputyPerson == null ? null : deputyPerson.trim();
    }

    public String getIsInsured() {
        return isInsured;
    }

    public void setIsInsured(String isInsured) {
        this.isInsured = isInsured == null ? null : isInsured.trim();
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany == null ? null : insuranceCompany.trim();
    }

    public String getInsuredTime() {
        return insuredTime;
    }

    public void setInsuredTime(String insuredTime) {
        this.insuredTime = insuredTime == null ? null : insuredTime.trim();
    }

    public String getInsuredEndTime() {
        return insuredEndTime;
    }

    public void setInsuredEndTime(String insuredEndTime) {
        this.insuredEndTime = insuredEndTime == null ? null : insuredEndTime.trim();
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber == null ? null : policyNumber.trim();
    }
}