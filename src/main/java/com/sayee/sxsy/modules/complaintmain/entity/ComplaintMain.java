/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaintmain.entity;

import com.sayee.sxsy.common.persistence.ActEntity;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.test.entity.TestTree;
import org.hibernate.validator.constraints.Length;
import com.sayee.sxsy.modules.sys.entity.User;

import com.sayee.sxsy.common.persistence.DataEntity;

import java.util.List;

/**
 * 纠纷调解Entity
 * @author lyt
 * @version 2019-06-04
 */
public class ComplaintMain extends ActEntity<ComplaintMain> {

	private static final long serialVersionUID = 1L;
	private String complaintMainId;		// 主键
	private String complaintId;		// 医院投诉主键
	private User employee;  //涉及人员
	private User user;  //当前登录人员
	private Office hospital; //涉及医院
	private Office department; //涉及科室
	private String caseNumber;		// 案件编号
	private String patientName;		// 患者姓名
	private String patientSex;		// 患者性别 字典
	private String patientAge;		// 患者年龄
	private String patientCard;		// 患者身份证
	private String patientMobile;		// 患方联系电话
	private String involveHospital;		// 涉及医院
	private String hospitalLevel;		// 医院级别
	private String hospitalGrade;		// 医院等级
	private String involveDepartment;		// 涉及科室
	private String involveEmployee;		// 涉及人员
	private String procInsId;		// 流程实例id
    private String createById;
    private String updateById;
    private String taskDefKey; //放 流程节点编码
    private String key; //放 流程节点编码
    private String url; //不同模块 的不同路径
    private String nodeName; //节点名称
    private String assignee; //节点操作人
	private String source;		//案子的来源  1医调委录入 2 医院录入 默认是1
	private String testTree;//涉及科室
	private String isMajor;//是否重大 主表为主
	private List<String> list;//工作站人员list

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
	}

	public String getIsMajor() {
		return isMajor;
	}

	public void setIsMajor(String isMajor) {
		this.isMajor = isMajor;
	}

	public String getTestTree() {
		return testTree;
	}

	public void setTestTree(String testTree) {
		this.testTree = testTree;
	}

	public ComplaintMain() {
		super();
	}

	public ComplaintMain(String id){
		super(id);
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTaskDefKey() {
		return taskDefKey;
	}

	public void setTaskDefKey(String taskDefKey) {
		this.taskDefKey = taskDefKey;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCreateById() {
        return createById;
    }

    public void setCreateById(String createById) {
        this.createById = createById;
    }

    public String getUpdateById() {
        return updateById;
    }

    public void setUpdateById(String updateById) {
        this.updateById = updateById;
    }

    public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public Office getHospital() {
		return hospital;
	}

	public void setHospital(Office hospital) {
		this.hospital = hospital;
	}

	public Office getDepartment() {
		return department;
	}

	public void setDepartment(Office department) {
		this.department = department;
	}

	@Length(min=0, max=32, message="主键长度必须介于 1 和 32 之间")
	public String getComplaintMainId() {
		return complaintMainId;
	}

	public void setComplaintMainId(String complaintMainId) {
		this.complaintMainId = complaintMainId;
	}

	@Length(min=1, max=20, message="案件编号长度必须介于 1 和 20 之间")
	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	@Length(min=1, max=20, message="患者姓名长度必须介于 1 和 20 之间")
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	@Length(min=1, max=1, message="患者性别 字典长度必须介于 1 和 2 之间")
	public String getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}

	@Length(min=1, max=4, message="患者年龄长度必须介于 1 和 4 之间")
	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}

	@Length(min=1, max=20, message="患者身份证长度必须介于 1 和 20 之间")
	public String getPatientCard() {
		return patientCard;
	}

	public void setPatientCard(String patientCard) {
		this.patientCard = patientCard;
	}

	@Length(min=1, max=12, message="患方联系电话长度必须介于 1 和 12 之间")
	public String getPatientMobile() {
		return patientMobile;
	}

	public void setPatientMobile(String patientMobile) {
		this.patientMobile = patientMobile;
	}

	@Length(min=1, max=32, message="涉及医院长度必须介于 1 和 32 之间")
	public String getInvolveHospital() {
		return involveHospital;
	}

	public void setInvolveHospital(String involveHospital) {
		this.involveHospital = involveHospital;
	}

	@Length(min=1, max=10, message="医院级别长度必须介于 1 和 10 之间")
	public String getHospitalLevel() {
		return hospitalLevel;
	}

	public void setHospitalLevel(String hospitalLevel) {
		this.hospitalLevel = hospitalLevel;
	}

	@Length(min=1, max=10, message="医院等级长度必须介于 1 和 10 之间")
	public String getHospitalGrade() {
		return hospitalGrade;
	}

	public void setHospitalGrade(String hospitalGrade) {
		this.hospitalGrade = hospitalGrade;
	}

	@Length(min=1, max=1000, message="涉及科室长度必须介于 1 和 1000 之间")
	public String getInvolveDepartment() {
		return involveDepartment;
	}

	public void setInvolveDepartment(String involveDepartment) {
		this.involveDepartment = involveDepartment;
	}

	@Length(min=0, max=32, message="涉及人员长度必须介于 0 和 32 之间")
	public String getInvolveEmployee() {
		return involveEmployee;
	}

	public void setInvolveEmployee(String involveEmployee) {
		this.involveEmployee = involveEmployee;
	}

	@Length(min=0, max=32, message="流程实例id长度必须介于 0 和 32 之间")
	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}


}