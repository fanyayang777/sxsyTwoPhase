/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.machine.entity;

import com.sayee.sxsy.common.utils.excel.annotation.ExcelField;
import com.sayee.sxsy.modules.sys.entity.Area;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.User;
import org.aspectj.lang.annotation.DeclareMixin;
import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * 台账信息展示Entity
 * @author zhangfan
 * @version 2019-05-17
 */
public class MachineAccount extends DataEntity<MachineAccount> {

	private static final long serialVersionUID = 1L;
	private Office office;	// 归属部门
	private User user;	// 调解员
	private String machineAccountId;		// machine_account_id
	private String complaintMainId;			//主表主键
	private String reportingTime;		// 报案时间
	private String endReportingTime;		// 报案时间
	private String deptId;		// 部门主键
	private String mediatorId;		// 调解员ID，关联人员表主键
	private String patientName;		// 患者名称
	private String hospitalId;		// 医院名称。通过主键进行关联
	private String major;		// 重大
	private String insuranceCompany;		// 保险公司名称
	private String policyNumber;		// 保单号
	private String startInsuranceTime;// 起保日期
	private String startInsuranceTime1;		//导出开始日期
	private String endInsuranceTime1;		//导出结束时间
	private String endInsuranceTime;		// 起保日期
	private String disputesTime;		// 纠纷发生日期
	private String riskTime;		// 出险日期
	private String summaryOfDisputes;		// 纠纷概要
	private String complaintType;		// 投诉类型
	private String isMajor;		// 是否重大 1是 0否
	private String treatmentMode;		// 诊疗方式
	private String treatmentResult;		// 治疗结果
	private String patientsReflectFocus;		// 患方反映焦点
	private String relatedMajor;		// 涉及专业
	private String riskPeople;		// 出险医务人员
	private String assessTime;		// 评估时间
	private String assessNumber;		// 评估号
	private String dutyRatio;		// 责任比例
	private String feedbackTime;		// 反馈时间
	private String agreementNumber;		// 协议号
	private String ratifyAccord;		// 签署协议/判决时间
	private String agreementStampTime;		// 协议盖章时间
	private String agreementAmount;		// 协议金额 协议金额=保险金额+医院金额
	private String insuranceAmount;		// 保险赔付金额
	private String hospitalAmount;		// 医院赔付金额
	private String claimSettlementTime;		// 交理赔时间
	private String compensateTime;		// 赔付时间
	private String flowDays;		// 流转天数  流转天数设公式=交理赔时间-赔付时间
	private String handOver;		// 移交人
	private String archiveTime;		// 归档时间
	private String fileNumber;		// 卷宗编号
	private String remark;		// 备注
	private String delFlag;		//删除标识
	private String nodeName;		//案件节点
	private String relatedName;		//相关专业 名称
	private Office hospital;	//涉及医院
	private Area area;	//涉及医院所在区域
	private User hostUser;	//主持人
	private User clerkUser;	//书记员


	private String caseNumber;  //案件编号
	private String caseSituation;  //案件情况
	private String areaId;  //所属地区
	private String hospitalGrade;  //机构等级
	private String isMedia;  //是否媒体介入
	private String acceptanceTime;  //受理时间
	private String eighteenItems;  //涉及核心制度
	private String meetingFrequency;  //调解次数
	private String host;  //主持人
	private String clerk;  //书记员
	private String medicalExpert;  //医学专家
	private String legalExpert;  //法学律师
	private String countAmount;  //计算金额
	private String mediateResult;  //调解结果
	private String isJudicial;  //是否司法确认
	private String claimSettlementDay;	//提交理赔天数(公式=提交理赔时间-协议生效时间）
	private String insurancePayTime;	//保险赔付时间
	private String hospitalPayTime;	//医院赔付时间
	private String settlementFlowDays;	//理赔流转天数（公式=保险赔付时间-提交理赔时间）
	private String assessGrade;	//卷宗评分
	private String appraiser;	//评分人
	private String filePlace;	//卷宗位置

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getRelatedName() {
		return relatedName;
	}

	public void setRelatedName(String relatedName) {
		this.relatedName = relatedName;
	}

	public MachineAccount() {
		super();
	}
    @ExcelField(title="案件编号", align=2, sort=1)
	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
    @ExcelField(title="案件情况", align=2, sort=2)
	public String getCaseSituation() {
		return caseSituation;
	}

	public void setCaseSituation(String caseSituation) {
		this.caseSituation = caseSituation;
	}
    @ExcelField(title="所属地区", align=2, sort=3)
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
    @ExcelField(title="机构等级", align=2, sort=8)
	public String getHospitalGrade() {
		return hospitalGrade;
	}

	public void setHospitalGrade(String hospitalGrade) {
		this.hospitalGrade = hospitalGrade;
	}
    @ExcelField(title="是否媒体介入", align=2, sort=17)
	public String getIsMedia() {
		return isMedia;
	}

	public void setIsMedia(String isMedia) {
		this.isMedia = isMedia;
	}
    @ExcelField(title="受理时间", align=2, sort=21)
	public String getAcceptanceTime() {
		return acceptanceTime;
	}

	public void setAcceptanceTime(String acceptanceTime) {
		this.acceptanceTime = acceptanceTime;
	}
    @ExcelField(title="涉及核心制度", align=2, sort=22)
	public String getEighteenItems() {
		return eighteenItems;
	}

	public void setEighteenItems(String eighteenItems) {
		this.eighteenItems = eighteenItems;
	}
    @ExcelField(title="调解次数", align=2, sort=23)
	public String getMeetingFrequency() {
		return meetingFrequency;
	}

	public User getHostUser() {
		return hostUser;
	}

	public void setHostUser(User hostUser) {
		this.hostUser = hostUser;
	}

	public User getClerkUser() {
		return clerkUser;
	}

	public void setClerkUser(User clerkUser) {
		this.clerkUser = clerkUser;
	}

	public void setMeetingFrequency(String meetingFrequency) {
		this.meetingFrequency = meetingFrequency;
	}
    @ExcelField(title="主持人", align=2, sort=25)
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
    @ExcelField(title="书记员", align=2, sort=26)
	public String getClerk() {
		return clerk;
	}

	public void setClerk(String clerk) {
		this.clerk = clerk;
	}
    @ExcelField(title="医学专家", align=2, sort=27)
	public String getMedicalExpert() {
		return medicalExpert;
	}

	public void setMedicalExpert(String medicalExpert) {
		this.medicalExpert = medicalExpert;
	}
    @ExcelField(title="法学律师", align=2, sort=28)
	public String getLegalExpert() {
		return legalExpert;
	}

	public void setLegalExpert(String legalExpert) {
		this.legalExpert = legalExpert;
	}
    @ExcelField(title="计算金额", align=2, sort=30)
	public String getCountAmount() {
		return countAmount;
	}

	public void setCountAmount(String countAmount) {
		this.countAmount = countAmount;
	}
    @ExcelField(title="调解结果", align=2, sort=32)
	public String getMediateResult() {
		return mediateResult;
	}

	public void setMediateResult(String mediateResult) {
		this.mediateResult = mediateResult;
	}
    @ExcelField(title="是否司法确认", align=2, sort=33)
	public String getIsJudicial() {
		return isJudicial;
	}

	public void setIsJudicial(String isJudicial) {
		this.isJudicial = isJudicial;
	}
    @ExcelField(title="提交理赔天数", align=2, sort=42)
	public String getClaimSettlementDay() {
		return claimSettlementDay;
	}

	public void setClaimSettlementDay(String claimSettlementDay) {
		this.claimSettlementDay = claimSettlementDay;
	}
    @ExcelField(title="保险赔付时间", align=2, sort=43)
	public String getInsurancePayTime() {
		return insurancePayTime;
	}

	public void setInsurancePayTime(String insurancePayTime) {
		this.insurancePayTime = insurancePayTime;
	}
    @ExcelField(title="医院赔付时间", align=2, sort=44)
	public String getHospitalPayTime() {
		return hospitalPayTime;
	}

	public void setHospitalPayTime(String hospitalPayTime) {
		this.hospitalPayTime = hospitalPayTime;
	}
    @ExcelField(title="理赔流转天数", align=2, sort=45)
	public String getSettlementFlowDays() {
		return settlementFlowDays;
	}

	public void setSettlementFlowDays(String settlementFlowDays) {
		this.settlementFlowDays = settlementFlowDays;
	}
    @ExcelField(title="卷宗评分", align=2, sort=49)
	public String getAssessGrade() {
		return assessGrade;
	}

	public void setAssessGrade(String assessGrade) {
		this.assessGrade = assessGrade;
	}
    @ExcelField(title="评分人", align=2, sort=50)
	public String getAppraiser() {
		return appraiser;
	}

	public void setAppraiser(String appraiser) {
		this.appraiser = appraiser;
	}
    @ExcelField(title="卷宗位置", align=2, sort=51)
	public String getFilePlace() {
		return filePlace;
	}

	public void setFilePlace(String filePlace) {
		this.filePlace = filePlace;
	}

	public MachineAccount(String id){
		super(id);
	}

	public Office getHospital() {
		return hospital;
	}

	public String getStartInsuranceTime1() {
		return startInsuranceTime1;
	}

	public void setStartInsuranceTime1(String startInsuranceTime1) {
		this.startInsuranceTime1 = startInsuranceTime1;
	}

	public String getEndInsuranceTime1() {
		return endInsuranceTime1;
	}

	public void setEndInsuranceTime1(String endInsuranceTime1) {
		this.endInsuranceTime1 = endInsuranceTime1;
	}

	public void setHospital(Office hospital) {
		this.hospital = hospital;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	@Override
    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getComplaintMainId() {
        return complaintMainId;
    }

    public void setComplaintMainId(String complaintMainId) {
        this.complaintMainId = complaintMainId;
    }

	//@Length(min=1, max=32, message="machine_account_id长度必须介于 1 和 32 之间")
	public String getMachineAccountId() {
		return machineAccountId;
	}

	public void setMachineAccountId(String machineAccountId) {
		this.machineAccountId = machineAccountId;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Length(min=0, max=20, message="报案时间不能为空，且长度必须介于 0 和 20 之间")
	@ExcelField(title="报案时间", align=2, sort=20)
	public String getReportingTime() {
		return reportingTime;
	}

	public void setReportingTime(String reportingTime) {
		this.reportingTime = reportingTime;
	}

	public String getEndReportingTime() {
		return endReportingTime;
	}

	public void setEndReportingTime(String endReportingTime) {
		this.endReportingTime = endReportingTime;
	}

	public String getEndInsuranceTime() {
		return endInsuranceTime;
	}

	public void setEndInsuranceTime(String endInsuranceTime) {
		this.endInsuranceTime = endInsuranceTime;
	}

	@Length(min=0, max=32, message="部门不能为空")
	@ExcelField(title = "所属部门", align=2,sort = 4)
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Length(min=0, max=32, message="调解员不能为空")
	@ExcelField(title = "调解员",align = 2,sort = 5)
	public String getMediatorId() {
		return mediatorId;
	}

	public void setMediatorId(String mediatorId) {
		this.mediatorId = mediatorId;
	}

	@Length(min=0, max=10, message="患者名称不能为空，长度必须介于 0 和 10 之间")
	@ExcelField(title="患者名称", align=2, sort=6)
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	@Length(min=0, max=32, message="医院名称不能为空，且长度必须介于 0 和 32 之间")
	@ExcelField(title="涉及医院", align=2, sort=7)
	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	@Length(min=0, max=50, message="重大长度必须介于 0 和 50 之间")
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Length(min=0, max=100, message="保险公司名称长度必须介于 0 和 100 之间")
	@ExcelField(title="保险公司", align=2, sort=9)
	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	@Length(min=0, max=50, message="保单号长度必须介于 0 和 50 之间")
	//@ExcelField(title="保单号", align=2, sort=8)
	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	@Length(min=0, max=20, message="起保日期长度必须介于 0 和 20 之间")
	//@ExcelField(title="起保日期", align=2, sort=9)
	public String getStartInsuranceTime() {
		return startInsuranceTime;
	}

	public void setStartInsuranceTime(String startInsuranceTime) {
		this.startInsuranceTime = startInsuranceTime;
	}

	@Length(min=0, max=20, message="纠纷发生日期长度必须介于 0 和 20 之间")
	@ExcelField(title="纠纷发生时间", align=2, sort=19)
	public String getDisputesTime() {
		return disputesTime;
	}

	public void setDisputesTime(String disputesTime) {
		this.disputesTime = disputesTime;
	}

	@Length(min=0, max=20, message="出险日期长度必须介于 0 和 20 之间")
	@ExcelField(title="出险时间", align=2, sort=18)
	public String getRiskTime() {
		return riskTime;
	}

	public void setRiskTime(String riskTime) {
		this.riskTime = riskTime;
	}

	@Length(min=0, max=500, message="纠纷概要长度必须介于 0 和 500 之间")
	@ExcelField(title="纠纷概要", align=2, sort=10)
	public String getSummaryOfDisputes() {
		return summaryOfDisputes;
	}

	public void setSummaryOfDisputes(String summaryOfDisputes) {
		this.summaryOfDisputes = summaryOfDisputes;
	}

	@Length(min=0, max=50, message="投诉类型长度必须介于 0 和 50 之间")
	//@ExcelField(title="投诉类型", align=2, sort=13)
	public String getComplaintType() {
		return complaintType;
	}

	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}

	@Length(min=0, max=1, message="是否重大 1是 0否长度必须介于 0 和 1 之间")
	@ExcelField(title = "是否重大",align = 2,sort = 16)
	public String getIsMajor() {
		return isMajor;
	}

	public void setIsMajor(String isMajor) {
		this.isMajor = isMajor;
	}

	@Length(min=0, max=500, message="诊疗方式长度必须介于 0 和 500 之间")
	@ExcelField(title="诊疗方式", align=2, sort=12)
	public String getTreatmentMode() {
		return treatmentMode;
	}

	public void setTreatmentMode(String treatmentMode) {
		this.treatmentMode = treatmentMode;
	}

	@Length(min=0, max=500, message="治疗结果长度必须介于 0 和 500 之间")
	@ExcelField(title="治疗结果", align=2, sort=13)
	public String getTreatmentResult() {
		return treatmentResult;
	}

	public void setTreatmentResult(String treatmentResult) {
		this.treatmentResult = treatmentResult;
	}

	@Length(min=0, max=500, message="患方反映焦点长度必须介于 0 和 500 之间")
	@ExcelField(title="纠纷焦点", align=2, sort=15)
	public String getPatientsReflectFocus() {
		return patientsReflectFocus;
	}

	public void setPatientsReflectFocus(String patientsReflectFocus) {
		this.patientsReflectFocus = patientsReflectFocus;
	}

	@Length(min=0, max=255, message="涉及专业长度必须介于 0 和 255 之间")
	@ExcelField(title="涉及专业", align=2, sort=11)
	public String getRelatedMajor() {
		return relatedMajor;
	}

	public void setRelatedMajor(String relatedMajor) {
		this.relatedMajor = relatedMajor;
	}

	@Length(min=0, max=255, message="出险医务人员长度必须介于 0 和 255 之间")
	@ExcelField(title="出险医生", align=2, sort=14)
	public String getRiskPeople() {
		return riskPeople;
	}

	public void setRiskPeople(String riskPeople) {
		this.riskPeople = riskPeople;
	}

	@Length(min=0, max=20, message="评估时间长度必须介于 0 和 20 之间")
	@ExcelField(title="评估时间", align=2, sort=24)
	public String getAssessTime() {
		return assessTime;
	}

	public void setAssessTime(String assessTime) {
		this.assessTime = assessTime;
	}

	@Length(min=0, max=20, message="评估号长度必须介于 0 和 20 之间")
	@ExcelField(title="评估号", align=2, sort=31)
	public String getAssessNumber() {
		return assessNumber;
	}

	public void setAssessNumber(String assessNumber) {
		this.assessNumber = assessNumber;
	}

	@Length(min=0, max=50, message="责任比例长度必须介于 0 和 50 之间")
	@ExcelField(title="责任比例", align=2, sort=29)
	public String getDutyRatio() {
		return dutyRatio;
	}

	public void setDutyRatio(String dutyRatio) {
		this.dutyRatio = dutyRatio;
	}

	@Length(min=0, max=20, message="反馈时间长度必须介于 0 和 20 之间")
	//@ExcelField(title="反馈函时间", align=2, sort=23)
	public String getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(String feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	@Length(min=0, max=20, message="协议号长度必须介于 0 和 20 之间")
	@ExcelField(title="协议号", align=2, sort=34)
	public String getAgreementNumber() {
		return agreementNumber;
	}

	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
	}

	@Length(min=0, max=20, message="签署协议/判决时间长度必须介于 0 和 20 之间")
	@ExcelField(title="协议签署时间", align=2, sort=35)
	public String getRatifyAccord() {
		return ratifyAccord;
	}

	public void setRatifyAccord(String ratifyAccord) {
		this.ratifyAccord = ratifyAccord;
	}

	@Length(min=0, max=20, message="协议盖章时间长度必须介于 0 和 20 之间")
    @ExcelField(title="协议生效时间", align=2, sort=36)
	public String getAgreementStampTime() {
		return agreementStampTime;
	}

	public void setAgreementStampTime(String agreementStampTime) {
		this.agreementStampTime = agreementStampTime;
	}

	@Length(min=0,max=20,message = "协议金额不能为空，且长度必须介于 0 和 20 之间")
	//@DecimalMin(value = "0",message = "协议金额最低为0,且不能为空")
	@ExcelField(title = "协议金额",align = 2,sort = 38)
	public String getAgreementAmount() {
		return agreementAmount;
	}

	public void setAgreementAmount(String agreementAmount) {
		this.agreementAmount = agreementAmount;
	}

	@Length(min=0,max=20,message = "保险赔付金额不能为空，长度必须介于 0 和 20 之间")
	//@DecimalMin(value = "0",message = "保险赔付金额最低为0,且不能为空")
	@ExcelField(title = "保险赔付金额",align = 2,sort = 39)
	public String getInsuranceAmount() {
		return insuranceAmount;
	}

	public void setInsuranceAmount(String insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}

	@Length(min=0,max=20,message = "医院赔付金额不能为空，长度必须介于 0 和 20 之间")
	//@DecimalMin(value = "0",message = "医院赔付金额最低为0,且不能为空")
	@ExcelField(title = "医院赔付金额",align = 2,sort = 40)
	public String getHospitalAmount() {
		return hospitalAmount;
	}

	public void setHospitalAmount(String hospitalAmount) {
		this.hospitalAmount = hospitalAmount;
	}

	@Length(min=0, max=20, message="交理赔时间长度必须介于 0 和 20 之间")
	@ExcelField(title="交理赔时间", align=2, sort=41)
	public String getClaimSettlementTime() {
		return claimSettlementTime;
	}

	public void setClaimSettlementTime(String claimSettlementTime) {
		this.claimSettlementTime = claimSettlementTime;
	}

	@Length(min=0, max=20, message="赔付时间长度必须介于 0 和 20 之间")
	//@ExcelField(title="赔付时间", align=2, sort=29)
	public String getCompensateTime() {
		return compensateTime;
	}

	public void setCompensateTime(String compensateTime) {
		this.compensateTime = compensateTime;
	}

	@Length(min=0, max=20, message="流转天数长度必须介于 0 和 20 之间")
	@ExcelField(title="调解天数", align=2, sort=37)
	public String getFlowDays() {
		return flowDays;
	}

	public void setFlowDays(String flowDays) {
		this.flowDays = flowDays;
	}

	@Length(min=0, max=20, message="移交人长度必须介于 0 和 20 之间")
	@ExcelField(title="移交人", align=2, sort=47)
	public String getHandOver() {
		return handOver;
	}

	public void setHandOver(String handOver) {
		this.handOver = handOver;
	}

	@Length(min=0, max=20, message="归档时间长度必须介于 0 和 20 之间")
	@ExcelField(title="卷宗归档时间", align=2, sort=46)
	public String getArchiveTime() {
		return archiveTime;
	}

	public void setArchiveTime(String archiveTime) {
		this.archiveTime = archiveTime;
	}

	@Length(min=0, max=20, message="卷宗编号不能为空，且长度必须介于 0 和 20 之间")
	@ExcelField(title="卷宗编号", align=2, sort=48)
	public String getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}

	@Length(min=0, max=500, message="备注长度必须介于 0 和 500 之间")
	@ExcelField(title="备注", align=10, sort=52)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}