/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.registration.entity;

import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.machine.entity.MachineAccount;
import com.sayee.sxsy.modules.sys.entity.Area;
import com.sayee.sxsy.modules.sys.entity.Dict;
import com.sayee.sxsy.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;

import com.sayee.sxsy.common.persistence.DataEntity;

import java.util.List;

/**
 * 报案登记Entity
 * @author lyt
 * @version 2019-06-05
 */
public class ReportRegistration extends DataEntity<ReportRegistration> {

	private static final long serialVersionUID = 1L;
	private String reportRegistrationId;		// 主键
	private ComplaintMain complaintMain;		// 关联主表主键
	private String complaintMainId;
	private User user;  //当前登录人员
	private Area area;
	private User djEmployee;		// 登记人员（填表人签名）
	private User linkEmployee;		// 下一环节人员
	private String reportEmp;		// 报案人姓名
	private String patientMobile;		// 患方联系方式
	private String patientRelation;		// 与患者关系（审核日期）
	private String reportTime;		// 报案日期（出险日期  出险时间在前，纠纷发生时间在后）
	private String registrationEmp;		// 登记人员（填表人签名）
	private String registrationTime;		// 登记日期（填表日期）
	private String disputeTime;		// 纠纷发生时间
	private String isMajor;		// 是否重大
	private String summaryOfDisputes;		// 投诉纠纷概要
	private String focus;		// 投诉纠纷焦点
	private String patientAsk;		// 患方要求
	private String nextLink;		// 下一处理环节（审核人）
	private String nextLinkMan;		// 下一环节处理人
	private String doctorRisk;		// 是否医责险报案
    private Dict dict;  //
	private String doctorMobile;//医方联系方式
	private String policyNumber;//保单号
	private List<String> list;//工作站人员list
	private MachineAccount machineAccount;    //台账信息

	private String createUser;  //创建人员id

	public String getCreateUser() {
		return createUser;
	}

	public String getDoctorRisk() {
		return doctorRisk;
	}

	public void setDoctorRisk(String doctorRisk) {
		this.doctorRisk = doctorRisk;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public MachineAccount getMachineAccount() {
		return machineAccount;
	}

	public void setMachineAccount(MachineAccount machineAccount) {
		this.machineAccount = machineAccount;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getDoctorMobile() {
		return doctorMobile;
	}

	public void setDoctorMobile(String doctorMobile) {
		this.doctorMobile = doctorMobile;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    public Dict getDict() {
        return dict;
    }

    public void setDict(Dict dict) {
        this.dict = dict;
    }

    public ReportRegistration() {
		super();
	}

	public ReportRegistration(String id){
		super(id);
	}

	public User getDjEmployee() {
		return djEmployee;
	}

	public void setDjEmployee(User djEmployee) {
		this.djEmployee = djEmployee;
	}

	public User getLinkEmployee() {
		return linkEmployee;
	}

	public void setLinkEmployee(User linkEmployee) {
		this.linkEmployee = linkEmployee;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public ComplaintMain getComplaintMain() {
		return complaintMain;
	}

	public void setComplaintMain(ComplaintMain complaintMain) {
		this.complaintMain = complaintMain;
	}

	//@Length(min=1, max=32, message="主键长度必须介于 1 和 32 之间")
	public String getReportRegistrationId() {
		return reportRegistrationId;
	}

	public void setReportRegistrationId(String reportRegistrationId) {
		this.reportRegistrationId = reportRegistrationId;
	}

	@JsonBackReference
	//@NotNull(message="关联主表主键不能为空")
	public String getComplaintMainId() {
		return complaintMainId;
	}

	public void setComplaintMainId(String complaintMainId) {
		this.complaintMainId = complaintMainId;
	}

	@Length(min=0, max=32, message="报案人姓名长度必须介于 0 和 32 之间")
	public String getReportEmp() {
		return reportEmp;
	}

	public void setReportEmp(String reportEmp) {
		this.reportEmp = reportEmp;
	}

	@Length(min=0, max=15, message="患方联系方式长度必须介于 0 和 15 之间")
	public String getPatientMobile() {
		return patientMobile;
	}

	public void setPatientMobile(String patientMobile) {
		this.patientMobile = patientMobile;
	}

	@Length(min=0, max=20, message="与患者关系长度必须介于 0 和 20 之间")
	public String getPatientRelation() {
		return patientRelation;
	}

	public void setPatientRelation(String patientRelation) {
		this.patientRelation = patientRelation;
	}

	@Length(min=1, max=20, message="报案日期长度必须介于 0 和 20 之间")
	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	@Length(min=1, max=32, message="登记人员不能为空")
	public String getRegistrationEmp() {
		return registrationEmp;
	}

	public void setRegistrationEmp(String registrationEmp) {
		this.registrationEmp = registrationEmp;
	}

	@Length(min=0, max=20, message="登记日期长度必须介于 0 和 20 之间")
	public String getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(String registrationTime) {
		this.registrationTime = registrationTime;
	}

	@Length(min=1, max=20, message="纠纷发生时间长度必须介于 0 和 10 之间")
	public String getDisputeTime() {
		return disputeTime;
	}

	public void setDisputeTime(String disputeTime) {
		this.disputeTime = disputeTime;
	}

	@Length(min=0, max=1, message="是否重大长度必须介于 0 和 1 之间")
	public String getIsMajor() {
		return isMajor;
	}

	public void setIsMajor(String isMajor) {
		this.isMajor = isMajor;
	}

	public String getSummaryOfDisputes() {
		return summaryOfDisputes;
	}

	public void setSummaryOfDisputes(String summaryOfDisputes) {
		this.summaryOfDisputes = summaryOfDisputes;
	}

	public String getFocus() {
		return focus;
	}

	public void setFocus(String focus) {
		this.focus = focus;
	}

	public String getPatientAsk() {
		return patientAsk;
	}

	public void setPatientAsk(String patientAsk) {
		this.patientAsk = patientAsk;
	}

	@Length(min=0, max=32, message="下一处理环节长度必须介于 0 和 32 之间")
	public String getNextLink() {
		return nextLink;
	}

	public void setNextLink(String nextLink) {
		this.nextLink = nextLink;
	}

	//@Length(min=1, max=32, message="下一环节处理人不能为空")
	public String getNextLinkMan() {
		return nextLinkMan;
	}

	public void setNextLinkMan(String nextLinkMan) {
		this.nextLinkMan = nextLinkMan;
	}


}