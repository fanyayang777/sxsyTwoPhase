/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.train.entity;

import org.hibernate.validator.constraints.Length;

import com.sayee.sxsy.common.persistence.DataEntity;

/**
 * 培训视频Entity
 * @author wjm
 * @version 2020-03-31
 */
public class Train extends DataEntity<Train> {
	
	private static final long serialVersionUID = 1L;
	private String trainId;		// train_id
	private String title;		// 标题
	private String vidioType;		// 视频类型
	private String department;		// 科室
	private String path;		// path
	private String send;		// 发往：小程序 网站 后台
	private String introduce;		// 介绍
	private String score;		// 评分
	private String picturePath;		// 图片路径
	private String payment;		// 是否付费
	private String courseChapter;		// 课程章节
	private String buyNumber;		// 购买人数
	private String videoPrice;		// 视频价格
	private String intendedFor;		// 适用人群
	private String preknowledge;		// 预备知识
	private String lecturerName;		// 讲师名字
	private String lecturerCompany;		// 讲师所属单位
	
	public Train() {
		super();
	}

	public Train(String id){
		super(id);
	}

	@Length(min=1, max=32, message="train_id长度必须介于 1 和 32 之间")
	public String getTrainId() {
		return trainId;
	}

	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}
	
	@Length(min=0, max=500, message="标题长度必须介于 0 和 500 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=10, message="视频类型长度必须介于 0 和 10 之间")
	public String getVidioType() {
		return vidioType;
	}

	public void setVidioType(String vidioType) {
		this.vidioType = vidioType;
	}
	
	@Length(min=0, max=50, message="科室长度必须介于 0 和 50 之间")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Length(min=0, max=1000, message="path长度必须介于 0 和 1000 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Length(min=0, max=50, message="发往：小程序 网站 后台长度必须介于 0 和 50 之间")
	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}
	
	@Length(min=0, max=255, message="介绍长度必须介于 0 和 255 之间")
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	@Length(min=0, max=255, message="评分长度必须介于 0 和 255 之间")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	@Length(min=0, max=1000, message="图片路径长度必须介于 0 和 1000 之间")
	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	
	@Length(min=0, max=1, message="是否付费长度必须介于 0 和 1 之间")
	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}
	
	@Length(min=0, max=10, message="课程章节长度必须介于 0 和 10 之间")
	public String getCourseChapter() {
		return courseChapter;
	}

	public void setCourseChapter(String courseChapter) {
		this.courseChapter = courseChapter;
	}
	
	@Length(min=0, max=6, message="购买人数长度必须介于 0 和 6 之间")
	public String getBuyNumber() {
		return buyNumber;
	}

	public void setBuyNumber(String buyNumber) {
		this.buyNumber = buyNumber;
	}
	
	public String getVideoPrice() {
		return videoPrice;
	}

	public void setVideoPrice(String videoPrice) {
		this.videoPrice = videoPrice;
	}
	
	@Length(min=0, max=30, message="适用人群长度必须介于 0 和 30 之间")
	public String getIntendedFor() {
		return intendedFor;
	}

	public void setIntendedFor(String intendedFor) {
		this.intendedFor = intendedFor;
	}
	
	@Length(min=0, max=255, message="预备知识长度必须介于 0 和 255 之间")
	public String getPreknowledge() {
		return preknowledge;
	}

	public void setPreknowledge(String preknowledge) {
		this.preknowledge = preknowledge;
	}
	
	@Length(min=0, max=100, message="讲师名字长度必须介于 0 和 100 之间")
	public String getLecturerName() {
		return lecturerName;
	}

	public void setLecturerName(String lecturerName) {
		this.lecturerName = lecturerName;
	}
	
	@Length(min=0, max=100, message="讲师所属单位长度必须介于 0 和 100 之间")
	public String getLecturerCompany() {
		return lecturerCompany;
	}

	public void setLecturerCompany(String lecturerCompany) {
		this.lecturerCompany = lecturerCompany;
	}
	
}