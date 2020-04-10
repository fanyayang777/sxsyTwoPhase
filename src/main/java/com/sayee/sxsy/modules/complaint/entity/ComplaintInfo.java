/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaint.entity;

import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import com.sayee.sxsy.modules.sys.entity.Dict;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

import javax.servlet.Registration;
import java.util.List;

/**
 * 投诉接待Entity
 * @author zhangfan
 * @version 2019-05-27
 */
public class ComplaintInfo extends DataEntity<ComplaintInfo> {

	private static final long serialVersionUID = 1L;
	private Office office;  //部门
	private Dict  dict;  //字典
	private String caseNumber;		// 案件编号
	private String complaintMainId;	//主表Id
	private ComplaintMain complaintMain;	//主表信息
	private String visitorName;		// 访客姓名
	private String visitorMobile;		// 访客电话
	private String patientRelation;		// 与患者关系  字典维护
	private String patientName;		// 患者姓名
	private String patientSex;		// 患者性别
	private String patientAge;		// 患者年龄
	private String visitorNumber;		// 来访人数
	private String involveHospital;		// 涉及医院
	private String involveDepartment;		// 涉及科室
	private String complaintId;		// 主键
	private String involveEmployee;		// 涉及人员
	private String summaryOfDisputes;		// 投诉纠纷概要
	private String visitorDate;		// 来访日期
	private String complaintMode;		// 投诉方式
	private String isMajor;		// 是否重大
	private String appeal;		// 诉求
	private String receptionEmployee;		// 接待人员
	private String receptionDate;		// 接待时间
	private String nextLink;		// 下一处理环节
	private String nextLinkMan;		// 下一环节处理人
	private String relationName;	//与患者关系
	private String hospitalName;	//医院名称
	private String departmentName;	//科室名称
	private String employeeName;	//人员名称
	private String sexName;		//性别
    private User employee;      //接待人员
    private User link;          //下一环节处理人
    private User user;          //当前登陆人
    private String isMediate;       //是否进入医调委调解
    private String handleResult;       //处理结果
    private String handlePass;          //处理经过
    private String complaintType;       //投诉类别
    private String typeName;            //投诉类型名称
	private String receptionEndDate;	//接待结束时间
	private String handleWay;			//处理方式
	private String shiftHandle;			//转办科室
	private String shiftHandleName;		//转办科室名称
	private String testTree;//涉及科室（树）
	private String status;//状态
	private String expectedClosure;//结案预期
	private String closingMethod;//结案方式
	private String amountInvolved;//涉及金额
	private List<String> list;//驻卫健委工作站人员list
	private String node;//数据员审核 还是 分配员分配
	private String assignee;//数据员审核 还是 分配员分配 时的操作员
	private ReportRegistration reportRegistration;//报案登记实体类
	private String nameCount;//重复名字数量

	public String getNameCount() {
		return nameCount;
	}

	public void setNameCount(String nameCount) {
		this.nameCount = nameCount;
	}
	public ComplaintInfo() {
		super();
	}

	public ComplaintInfo(String id){
		super(id);
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public ReportRegistration getReportRegistration() {
		return reportRegistration;
	}

	public void setReportRegistration(ReportRegistration reportRegistration) {
		this.reportRegistration = reportRegistration;
	}

	public String getTestTree() {
		return testTree;
	}

	public void setTestTree(String testTree) {
		this.testTree = testTree;
	}

	public String getShiftHandleName() {
		return shiftHandleName;
	}

	public void setShiftHandleName(String shiftHandleName) {
		this.shiftHandleName = shiftHandleName;
	}

	public String getHandleWay() {
		return handleWay;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExpectedClosure() {
		return expectedClosure;
	}

	public void setExpectedClosure(String expectedClosure) {
		this.expectedClosure = expectedClosure;
	}

	public String getClosingMethod() {
		return closingMethod;
	}

	public void setClosingMethod(String closingMethod) {
		this.closingMethod = closingMethod;
	}

	public String getAmountInvolved() {
		return amountInvolved;
	}

	public void setAmountInvolved(String amountInvolved) {
		this.amountInvolved = amountInvolved;
	}

	public void setHandleWay(String handleWay) {
		this.handleWay = handleWay;
	}

	public String getShiftHandle() {
		return shiftHandle;
	}

	public void setShiftHandle(String shiftHandle) {
		this.shiftHandle = shiftHandle;
	}

	public String getReceptionEndDate() {
		return receptionEndDate;
	}

	public void setReceptionEndDate(String receptionEndDate) {
		this.receptionEndDate = receptionEndDate;
	}

	public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public void setComplaintMainId(String complaintMainId) {
		this.complaintMainId = complaintMainId;
	}

	public String getComplaintMainId() {
		return complaintMainId;
	}

	public ComplaintMain getComplaintMain() {
		return complaintMain;
	}

	public void setComplaintMain(ComplaintMain complaintMain) {
		this.complaintMain = complaintMain;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Length(min=0, message="处理经过不能为空。")
    public String getHandlePass() {
        return handlePass;
    }

    public void setHandlePass(String handlePass) {
        this.handlePass = handlePass;
    }


    @Length(min=0, message="处理结果不能为空。")
    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }

    public void setIsMediate(String isMediate) {
        this.isMediate = isMediate;
    }

    @Length(min=1, max=20, message="是否进入医调委调解不能为空,且长度必须介于 1 和 20 之间")
    public String getIsMediate() {
        return isMediate;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public User getLink() {
        return link;
    }

    public void setLink(User link) {
        this.link = link;
    }

    public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public Office getOffice() {
		return office;
	}

	public Dict getDict() {
		return dict;
	}

	public void setDict(Dict dict) {
		this.dict = dict;
	}

	public void setOffice(Office office) {
		this.office = office;
	}


	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	@Length(min=1, max=20, message="访客姓名不能为空,且长度必须介于 1 和 20 之间")
	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	@Length(min=0, max=15, message="访客电话不能为空,且长度必须介于 0 和 15 之间")
	public String getVisitorMobile() {
		return visitorMobile;
	}

	public void setVisitorMobile(String visitorMobile) {
		this.visitorMobile = visitorMobile;
	}

	@Length(min=1, message="与患者关系不能为空")
	public String getPatientRelation() {
		return patientRelation;
	}

	public void setPatientRelation(String patientRelation) {
		this.patientRelation = patientRelation;
	}

	@Length(min=1, max=20, message="患者姓名不能为空")
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	@Length(min=0, max=1, message="患者性别长度必须介于 0 和 1 之间")
	public String getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}

	@Length(min=1, max=4, message="患者年龄不能为空")
	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}

	@Length(min=0, max=10, message="来访人数长度必须介于 0 和 10 之间")
	public String getVisitorNumber() {
		return visitorNumber;
	}

	public void setVisitorNumber(String visitorNumber) {
		this.visitorNumber = visitorNumber;
	}

	@Length(min=1, max=32, message="涉及医院不能为空")
	public String getInvolveHospital() {
		return involveHospital;
	}

	public void setInvolveHospital(String involveHospital) {
		this.involveHospital = involveHospital;
	}

	@Length(min=1, max=1000, message="涉及科室不能为空")
	public String getInvolveDepartment() {
		return involveDepartment;
	}

	public void setInvolveDepartment(String involveDepartment) {
		this.involveDepartment = involveDepartment;
	}

	@Length(min=0, max=32, message="主键长度必须介于 1 和 32 之间")
	public String getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
	}

	@Length(min=0, max=32, message="涉及人员不能为空,且长度必须介于 1 和 32 之间")
	public String getInvolveEmployee() {
		return involveEmployee;
	}

	public void setInvolveEmployee(String involveEmployee) {
		this.involveEmployee = involveEmployee;
	}

	@Length(min=1,message = "纠纷概要不能为空")
	public String getSummaryOfDisputes() {
		return summaryOfDisputes;
	}

	public void setSummaryOfDisputes(String summaryOfDisputes) {
		this.summaryOfDisputes = summaryOfDisputes;
	}

	@Length(min=0, max=20, message="来访日期长度必须介于 0 和 10 之间")
	public String getVisitorDate() {
		return visitorDate;
	}

	public void setVisitorDate(String visitorDate) {
		this.visitorDate = visitorDate;
	}

	@Length(min=1, message="投诉方式不能为空")
	public String getComplaintMode() {
		return complaintMode;
	}

	public void setComplaintMode(String complaintMode) {
		this.complaintMode = complaintMode;
	}

	@Length(min=1, message="是否重大不能为空")
	public String getIsMajor() {
		return isMajor;
	}

	public void setIsMajor(String isMajor) {
		this.isMajor = isMajor;
	}

	@Length(min=1,message = "诉求不能为空")
	public String getAppeal() {
		return appeal;
	}

	public void setAppeal(String appeal) {
		this.appeal = appeal;
	}

	public String getReceptionEmployee() {
		return receptionEmployee;
	}

	public void setReceptionEmployee(String receptionEmployee) {
		this.receptionEmployee = receptionEmployee;
	}

	@Length(min=0, max=20, message="接待时间长度必须介于 0 和 20 之间")
	public String getReceptionDate() {
		return receptionDate;
	}

	public void setReceptionDate(String receptionDate) {
		this.receptionDate = receptionDate;
	}

	@Length(min=0, max=32, message="下一处理环节长度必须介于 0 和 32 之间")
	public String getNextLink() {
		return nextLink;
	}

	public void setNextLink(String nextLink) {
		this.nextLink = nextLink;
	}

	public String getNextLinkMan() {
		return nextLinkMan;
	}

	public void setNextLinkMan(String nextLinkMan) {
		this.nextLinkMan = nextLinkMan;
	}

}