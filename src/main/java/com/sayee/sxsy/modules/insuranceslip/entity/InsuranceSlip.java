/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.insuranceslip.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 投保单Entity
 * @author yang
 * @version 2020-03-29
 */
public class InsuranceSlip extends DataEntity<InsuranceSlip> {
	
	private static final long serialVersionUID = 1L;
	private String insurancePolicyId;		// 投保单ID
	private String policyHolder;		// 投保人
	private String policyPhone;		// 投保人：联系电话
	private String sitePostcode;		// 投保人：通信地址和邮编
	private String emailAddress;		// 投保人：电子邮箱
	private String theInsured;		// 被保险人
	private String theInsuredPhone;		// 被保险人联系方式
	private String theInsuredSite;		// 被保险人地址
	private String postalCode;		// 被保人：邮政编码
	private String insureArea;		// 投保区域范围
	private String category;		//医院类别
	private Date creationTime;		// 被保险人：创建时间
	private String practiceNumber;		// 被保险人：执业许可号
	private String grade;		// 被保险人：医疗机构等级
	private String mold;		// 被保险人：类别
	private String nature;		// 被保险人：所有制性质
	private String department;		// 被保险人：主管部门
	private String sickbedNumber;		// 被保险人：病床数
	private String peopleNumber;		// 被保险人：年诊疗人数
	private String medicalStaffNumber;		// 被保险人：医务人员人数
	private String everyoneQuota;		// 医疗责任：每人赔偿限额
	private String spiritQuota;		// 医疗责任：精神损害赔偿限额
	private String everytimeQuota;		// 医疗责任：每次索赔免赔额
	private String accumulatedQuota;		// 医疗责任：累计赔偿额度
	private String lawEverytimeQuota;		// 法律费用：每人赔偿限额
	private String lawAccumulatedQuota;		// 法律费用：累计赔偿额度
	private String appendEverytimeQuota;		// 附加险：每人赔偿额度
	private String appendAccumulatedQuota;		// 附加险：累计赔偿额度
	private String bedPremium;		// 每床位保险费
	private String medicalPremium;		// 医疗机构保险费
	private String allWay;		// 投保方式：全部
	private String selWay;		// 投保方式：选择科室
	private String allMedicalNumber;		// 全部医务人员：医护人员人数
	private String allEveryonePremium;		// 全部医务人员：每人基本保险费
	private String allPremiumTotal;		// 全部医务人员：保险费合计
	private String selEveryonePremium;		// 选择科室：每人基本保险费
	private String selClinicOperationNumber;		// 选择科室：临床手术科室人数
	private String selClinicNotoperationNumber;		// 选择科室：临床非手术科室人数
	private String selMedicalLaboratoryNumber;		// 选择科室：医技科室人数
	private String selPremiumTotal;		// 选择科室：保险费合计
	private String basicPremiumTotal;		// 基本保险费总计
	private String addReduce;		// 增加/减少赔偿限额后的保险费调整系数
	private String enchanceDeduction;		// 提高免赔后的保险费调整系数
	private String riskFloat;		// 风险浮动系数
	private String asleftPremium;		// 调整后的保险费
	private String addittionRisk;		// 附加保险名称
	private String addittionPremium;		// 附加险保险费
	private String computationalFormula;		// 计算公式
	private String oddicialReceiptsPermium;		// 实收保险费
	private String insuranceDate;		// 保险期几个月
	private Date insuranceStartTime;		// 保险期开始时间
	private Date insuranceEndTime;		// 保险截止时间
	private String retroactiveDate;		// 追溯期 几个月
	private Date retroactiveStratDate;		// 追溯期开始时间
	private Date retroactiveEndDate;		// 追溯期结束时间
	private Date premiumDeliceryTime;		// 保险费交付日期
	private String dispute;		// 保险合同争议处理方式选择
	private String arbitrator;		// 仲裁员
	private String insureConditions;		// 以往投保情况
	private String accidentConditions;		// 以往事故情况
	private String specialAgreement;		// 特别约定
	private String signature;		// 投保人（签章）
	private Date insureDate;		// 投保时间
	private String assumeNature;		// 承保性质
	private String agencyCode;		// 业务员/代理人代码
	private String agencyName;		// 业务员/代理人姓名
	private String state; 			//状态
	private String reject;		//驳回信息

	public String getReject() {
		return reject;
	}

	public void setReject(String reject) {
		this.reject = reject;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public InsuranceSlip() {
		super();
	}

	public InsuranceSlip(String id){
		super(id);
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	//@Length(min=1, max=64, message="投保单ID长度必须介于 1 和 64 之间")
	public String getInsurancePolicyId() {
		return insurancePolicyId;
	}

	public void setInsurancePolicyId(String insurancePolicyId) {
		this.insurancePolicyId = insurancePolicyId;
	}
	
	@Length(min=0, max=64, message="投保人长度必须介于 0 和 64 之间")
	public String getPolicyHolder() {
		return policyHolder;
	}

	public void setPolicyHolder(String policyHolder) {
		this.policyHolder = policyHolder;
	}
	
	@Length(min=0, max=15, message="投保人：联系电话长度必须介于 0 和 15 之间")
	public String getPolicyPhone() {
		return policyPhone;
	}

	public void setPolicyPhone(String policyPhone) {
		this.policyPhone = policyPhone;
	}
	
	@Length(min=0, max=64, message="投保人：通信地址和邮编长度必须介于 0 和 64 之间")
	public String getSitePostcode() {
		return sitePostcode;
	}

	public void setSitePostcode(String sitePostcode) {
		this.sitePostcode = sitePostcode;
	}
	
	@Length(min=0, max=32, message="投保人：电子邮箱长度必须介于 0 和 32 之间")
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	@Length(min=0, max=64, message="被保险人长度必须介于 0 和 64 之间")
	public String getTheInsured() {
		return theInsured;
	}

	public void setTheInsured(String theInsured) {
		this.theInsured = theInsured;
	}
	
	@Length(min=0, max=15, message="被保险人联系方式长度必须介于 0 和 15 之间")
	public String getTheInsuredPhone() {
		return theInsuredPhone;
	}

	public void setTheInsuredPhone(String theInsuredPhone) {
		this.theInsuredPhone = theInsuredPhone;
	}
	
	@Length(min=0, max=64, message="被保险人地址长度必须介于 0 和 64 之间")
	public String getTheInsuredSite() {
		return theInsuredSite;
	}

	public void setTheInsuredSite(String theInsuredSite) {
		this.theInsuredSite = theInsuredSite;
	}
	
	@Length(min=0, max=20, message="被保人：邮政编码长度必须介于 0 和 20 之间")
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	@Length(min=0, max=64, message="投保区域范围长度必须介于 0 和 64 之间")
	public String getInsureArea() {
		return insureArea;
	}

	public void setInsureArea(String insureArea) {
		this.insureArea = insureArea;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	
	@Length(min=0, max=32, message="被保险人：执业许可号长度必须介于 0 和 32 之间")
	public String getPracticeNumber() {
		return practiceNumber;
	}

	public void setPracticeNumber(String practiceNumber) {
		this.practiceNumber = practiceNumber;
	}
	
//	@Length(min=0, max=1, message="被保险人：医疗机构等级长度必须介于 0 和 1 之间")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
//	@Length(min=0, max=1, message="被保险人：类别长度必须介于 0 和 1 之间")
	public String getMold() {
		return mold;
	}

	public void setMold(String mold) {
		this.mold = mold;
	}
	
//	@Length(min=0, max=32, message="被保险人：所有制性质长度必须介于 0 和 32 之间")
	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}
	
//	@Length(min=0, max=64, message="被保险人：主管部门长度必须介于 0 和 64 之间")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Length(min=0, max=5, message="被保险人：病床数长度必须介于 0 和 5 之间")
	public String getSickbedNumber() {
		return sickbedNumber;
	}

	public void setSickbedNumber(String sickbedNumber) {
		this.sickbedNumber = sickbedNumber;
	}
	
	@Length(min=0, max=5, message="被保险人：年诊疗人数长度必须介于 0 和 5 之间")
	public String getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(String peopleNumber) {
		this.peopleNumber = peopleNumber;
	}
	
	@Length(min=0, max=5, message="被保险人：医务人员人数长度必须介于 0 和 5 之间")
	public String getMedicalStaffNumber() {
		return medicalStaffNumber;
	}

	public void setMedicalStaffNumber(String medicalStaffNumber) {
		this.medicalStaffNumber = medicalStaffNumber;
	}
	
	public String getEveryoneQuota() {
		return everyoneQuota;
	}

	public void setEveryoneQuota(String everyoneQuota) {
		this.everyoneQuota = everyoneQuota;
	}
	
	public String getSpiritQuota() {
		return spiritQuota;
	}

	public void setSpiritQuota(String spiritQuota) {
		this.spiritQuota = spiritQuota;
	}
	
	public String getEverytimeQuota() {
		return everytimeQuota;
	}

	public void setEverytimeQuota(String everytimeQuota) {
		this.everytimeQuota = everytimeQuota;
	}
	
	public String getAccumulatedQuota() {
		return accumulatedQuota;
	}

	public void setAccumulatedQuota(String accumulatedQuota) {
		this.accumulatedQuota = accumulatedQuota;
	}
	
	public String getLawEverytimeQuota() {
		return lawEverytimeQuota;
	}

	public void setLawEverytimeQuota(String lawEverytimeQuota) {
		this.lawEverytimeQuota = lawEverytimeQuota;
	}
	
	public String getLawAccumulatedQuota() {
		return lawAccumulatedQuota;
	}

	public void setLawAccumulatedQuota(String lawAccumulatedQuota) {
		this.lawAccumulatedQuota = lawAccumulatedQuota;
	}
	
	public String getAppendEverytimeQuota() {
		return appendEverytimeQuota;
	}

	public void setAppendEverytimeQuota(String appendEverytimeQuota) {
		this.appendEverytimeQuota = appendEverytimeQuota;
	}
	
	public String getAppendAccumulatedQuota() {
		return appendAccumulatedQuota;
	}

	public void setAppendAccumulatedQuota(String appendAccumulatedQuota) {
		this.appendAccumulatedQuota = appendAccumulatedQuota;
	}
	
	public String getBedPremium() {
		return bedPremium;
	}

	public void setBedPremium(String bedPremium) {
		this.bedPremium = bedPremium;
	}
	
	public String getMedicalPremium() {
		return medicalPremium;
	}

	public void setMedicalPremium(String medicalPremium) {
		this.medicalPremium = medicalPremium;
	}
	
	@Length(min=0, max=1, message="投保方式：全部长度必须介于 0 和 1 之间")
	public String getAllWay() {
		return allWay;
	}

	public void setAllWay(String allWay) {
		this.allWay = allWay;
	}
	
	@Length(min=0, max=1, message="投保方式：选择科室长度必须介于 0 和 1 之间")
	public String getSelWay() {
		return selWay;
	}

	public void setSelWay(String selWay) {
		this.selWay = selWay;
	}
	
	@Length(min=0, max=4, message="全部医务人员：医护人员人数长度必须介于 0 和 4 之间")
	public String getAllMedicalNumber() {
		return allMedicalNumber;
	}

	public void setAllMedicalNumber(String allMedicalNumber) {
		this.allMedicalNumber = allMedicalNumber;
	}
	
	public String getAllEveryonePremium() {
		return allEveryonePremium;
	}

	public void setAllEveryonePremium(String allEveryonePremium) {
		this.allEveryonePremium = allEveryonePremium;
	}
	
	public String getAllPremiumTotal() {
		return allPremiumTotal;
	}

	public void setAllPremiumTotal(String allPremiumTotal) {
		this.allPremiumTotal = allPremiumTotal;
	}
	
	public String getSelEveryonePremium() {
		return selEveryonePremium;
	}

	public void setSelEveryonePremium(String selEveryonePremium) {
		this.selEveryonePremium = selEveryonePremium;
	}
	
	@Length(min=0, max=4, message="选择科室：临床手术科室人数长度必须介于 0 和 4 之间")
	public String getSelClinicOperationNumber() {
		return selClinicOperationNumber;
	}

	public void setSelClinicOperationNumber(String selClinicOperationNumber) {
		this.selClinicOperationNumber = selClinicOperationNumber;
	}
	
	@Length(min=0, max=4, message="选择科室：临床非手术科室人数长度必须介于 0 和 4 之间")
	public String getSelClinicNotoperationNumber() {
		return selClinicNotoperationNumber;
	}

	public void setSelClinicNotoperationNumber(String selClinicNotoperationNumber) {
		this.selClinicNotoperationNumber = selClinicNotoperationNumber;
	}
	
	@Length(min=0, max=4, message="选择科室：医技科室人数长度必须介于 0 和 4 之间")
	public String getSelMedicalLaboratoryNumber() {
		return selMedicalLaboratoryNumber;
	}

	public void setSelMedicalLaboratoryNumber(String selMedicalLaboratoryNumber) {
		this.selMedicalLaboratoryNumber = selMedicalLaboratoryNumber;
	}
	
	public String getSelPremiumTotal() {
		return selPremiumTotal;
	}

	public void setSelPremiumTotal(String selPremiumTotal) {
		this.selPremiumTotal = selPremiumTotal;
	}
	
	public String getBasicPremiumTotal() {
		return basicPremiumTotal;
	}

	public void setBasicPremiumTotal(String basicPremiumTotal) {
		this.basicPremiumTotal = basicPremiumTotal;
	}
	
	public String getAddReduce() {
		return addReduce;
	}

	public void setAddReduce(String addReduce) {
		this.addReduce = addReduce;
	}
	
	public String getEnchanceDeduction() {
		return enchanceDeduction;
	}

	public void setEnchanceDeduction(String enchanceDeduction) {
		this.enchanceDeduction = enchanceDeduction;
	}
	
	public String getRiskFloat() {
		return riskFloat;
	}

	public void setRiskFloat(String riskFloat) {
		this.riskFloat = riskFloat;
	}
	
	public String getAsleftPremium() {
		return asleftPremium;
	}

	public void setAsleftPremium(String asleftPremium) {
		this.asleftPremium = asleftPremium;
	}
	
	@Length(min=0, max=1000, message="附加保险名称长度必须介于 0 和 1000 之间")
	public String getAddittionRisk() {
		return addittionRisk;
	}

	public void setAddittionRisk(String addittionRisk) {
		this.addittionRisk = addittionRisk;
	}
	
	public String getAddittionPremium() {
		return addittionPremium;
	}

	public void setAddittionPremium(String addittionPremium) {
		this.addittionPremium = addittionPremium;
	}
	
	@Length(min=0, max=64, message="计算公式长度必须介于 0 和 64 之间")
	public String getComputationalFormula() {
		return computationalFormula;
	}

	public void setComputationalFormula(String computationalFormula) {
		this.computationalFormula = computationalFormula;
	}
	
	public String getOddicialReceiptsPermium() {
		return oddicialReceiptsPermium;
	}

	public void setOddicialReceiptsPermium(String oddicialReceiptsPermium) {
		this.oddicialReceiptsPermium = oddicialReceiptsPermium;
	}
	
	@Length(min=0, max=4, message="保险期几个月长度必须介于 0 和 4 之间")
	public String getInsuranceDate() {
		return insuranceDate;
	}

	public void setInsuranceDate(String insuranceDate) {
		this.insuranceDate = insuranceDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInsuranceStartTime() {
		return insuranceStartTime;
	}

	public void setInsuranceStartTime(Date insuranceStartTime) {
		this.insuranceStartTime = insuranceStartTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInsuranceEndTime() {
		return insuranceEndTime;
	}

	public void setInsuranceEndTime(Date insuranceEndTime) {
		this.insuranceEndTime = insuranceEndTime;
	}
	
	@Length(min=0, max=4, message="追溯期 几个月长度必须介于 0 和 4 之间")
	public String getRetroactiveDate() {
		return retroactiveDate;
	}

	public void setRetroactiveDate(String retroactiveDate) {
		this.retroactiveDate = retroactiveDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRetroactiveStratDate() {
		return retroactiveStratDate;
	}

	public void setRetroactiveStratDate(Date retroactiveStratDate) {
		this.retroactiveStratDate = retroactiveStratDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRetroactiveEndDate() {
		return retroactiveEndDate;
	}

	public void setRetroactiveEndDate(Date retroactiveEndDate) {
		this.retroactiveEndDate = retroactiveEndDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPremiumDeliceryTime() {
		return premiumDeliceryTime;
	}

	public void setPremiumDeliceryTime(Date premiumDeliceryTime) {
		this.premiumDeliceryTime = premiumDeliceryTime;
	}
	
	@Length(min=0, max=1, message="保险合同争议处理方式选择长度必须介于 0 和 1 之间")
	public String getDispute() {
		return dispute;
	}

	public void setDispute(String dispute) {
		this.dispute = dispute;
	}
	
	@Length(min=0, max=32, message="仲裁员长度必须介于 0 和 32 之间")
	public String getArbitrator() {
		return arbitrator;
	}

	public void setArbitrator(String arbitrator) {
		this.arbitrator = arbitrator;
	}
	
	public String getInsureConditions() {
		return insureConditions;
	}

	public void setInsureConditions(String insureConditions) {
		this.insureConditions = insureConditions;
	}
	
	public String getAccidentConditions() {
		return accidentConditions;
	}

	public void setAccidentConditions(String accidentConditions) {
		this.accidentConditions = accidentConditions;
	}
	
	public String getSpecialAgreement() {
		return specialAgreement;
	}

	public void setSpecialAgreement(String specialAgreement) {
		this.specialAgreement = specialAgreement;
	}
	
	@Length(min=0, max=32, message="投保人（签章）长度必须介于 0 和 32 之间")
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInsureDate() {
		return insureDate;
	}

	public void setInsureDate(Date insureDate) {
		this.insureDate = insureDate;
	}
	
	@Length(min=0, max=1, message="承保性质长度必须介于 0 和 1 之间")
	public String getAssumeNature() {
		return assumeNature;
	}

	public void setAssumeNature(String assumeNature) {
		this.assumeNature = assumeNature;
	}
	
	@Length(min=0, max=32, message="业务员/代理人代码长度必须介于 0 和 32 之间")
	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	
	@Length(min=0, max=32, message="业务员/代理人姓名长度必须介于 0 和 32 之间")
	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	
}