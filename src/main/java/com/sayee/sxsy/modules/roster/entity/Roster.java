/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.roster.entity;

import com.sayee.sxsy.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 花名册Entity
 * @author yang
 * @version 2020-03-30
 */
public class Roster extends DataEntity<Roster> {
	
	private static final long serialVersionUID = 1L;
	private String rosterId;		// 花名册id
	private String insuranceSlipId;		// 投保单id
	@ExcelField(title="医院", align=2, sort=10)
	private String hospital;		// 医院
	@ExcelField(title="姓名", align=2, sort=20)
	private String name;		// 姓名
	@ExcelField(title="身份证号", align=2, sort=30)
	private String identityCard;		// 身份证号
	@ExcelField(title="所属岗位", align=2, sort=40)
	private String post;		// 所属岗位
	@ExcelField(title="执业资格证号码", align=2, sort=50)
	private String practiceNumber;		// 执业资格证号码
	@ExcelField(title="手机号", align=2, sort=60)
	private String phone;		// 手机号
	
	public Roster() {
		super();
	}

	public Roster(String id){
		super(id);
	}

	@Length(min=1, max=32, message="花名册id长度必须介于 1 和 32 之间")
	public String getRosterId() {
		return rosterId;
	}

	public void setRosterId(String rosterId) {
		this.rosterId = rosterId;
	}
	
	@Length(min=0, max=32, message="投保单id长度必须介于 0 和 32 之间")
	public String getInsuranceSlipId() {
		return insuranceSlipId;
	}

	public void setInsuranceSlipId(String insuranceSlipId) {
		this.insuranceSlipId = insuranceSlipId;
	}
	
	@Length(min=0, max=64, message="医院长度必须介于 0 和 64 之间")
	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	
	@Length(min=0, max=32, message="姓名长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=20, message="身份证号长度必须介于 1 和 20 之间")
	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	
	@Length(min=0, max=64, message="所属岗位长度必须介于 0 和 64 之间")
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}
	
	@Length(min=0, max=64, message="执业资格证号码长度必须介于 0 和 64 之间")
	public String getPracticeNumber() {
		return practiceNumber;
	}

	public void setPracticeNumber(String practiceNumber) {
		this.practiceNumber = practiceNumber;
	}
	
	@Length(min=0, max=20, message="手机号长度必须介于 0 和 20 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}