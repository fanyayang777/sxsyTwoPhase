/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.surgicalconsentbook.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sayee.sxsy.common.utils.*;
import com.sayee.sxsy.modules.sys.entity.Role;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.activiti.engine.impl.util.CollectionUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.surgicalconsentbook.entity.PreOperativeConsent;
import com.sayee.sxsy.modules.surgicalconsentbook.dao.PreOperativeConsentDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 术前同意书见证管理Service
 * @author gbq
 * @version 2019-05-31
 */
@Service
@Transactional(readOnly = true)
public class PreOperativeConsentService extends CrudService<PreOperativeConsentDao, PreOperativeConsent> {

	public PreOperativeConsent get(String id) {
		return super.get(id);
	}
	
	public List<PreOperativeConsent> findList(PreOperativeConsent preOperativeConsent) {
		return super.findList(preOperativeConsent);
	}
	
	public Page<PreOperativeConsent> findPage(Page<PreOperativeConsent> page, PreOperativeConsent preOperativeConsent) {
//		List<Role> roles=UserUtils.selectRoles(UserUtils.getUser().getId());
//		Object[] objs = roles.toArray();
//		List<String> roleType=BaseUtils.convert(objs,"roleType",false);
//		if (!roleType.contains("assignment")){
//			preOperativeConsent.setUser(UserUtils.getUser());
//		}
		//如果当前人员角色 是 医调委主任 则看全部数据
		List<String> aa= ObjectUtils.convert(UserUtils.getRoleList().toArray(),"enname",true);
		if (aa.contains("yitiaoweizhuren")){//韩主任 医调委主任 看全部数据

		}else if(aa.contains("jinzhuxingzhengbumenzhuren")){
			//曹华磊 与韩主任 有全部数据的权限，为了看 看卫健委工作站人员 信息
			List<String> list=new ArrayList<String>();
			List<User> listUser=UserUtils.getUser("jinzhuxingzhengbumentiaojieyuan");
			for (User user:listUser) {
				list.add(user.getId());
			}
			if (list.size()>0){
				preOperativeConsent.setList(list);
			}else {
				list.add(UserUtils.getUser().getId());
				preOperativeConsent.setList(list);
			}
		}else if(aa.contains("fengxianguankongbuzhuren")){
			//风险管控部 部门副主任 看自己部门所有数据
			List<String> list=new ArrayList<String>();

			List<User> listUser=UserUtils.getUserByOffice(UserUtils.getUser().getOffice().getId());
			for (User user:listUser) {
				list.add(user.getId());
			}
			if (list.size()>0){
				preOperativeConsent.setList(list);
			}else {
				list.add(UserUtils.getUser().getId());
				preOperativeConsent.setList(list);
			}
		}else{
			preOperativeConsent.setUser(UserUtils.getUser());
		}
		Page<PreOperativeConsent> list=super.findPage(page, preOperativeConsent);
		for (PreOperativeConsent pp : list.getList()) {
			preOperativeConsent.setOfficeName(StringUtils.isNotBlank(preOperativeConsent.getHospital()) ? pp.getOfficeName() : "");
			preOperativeConsent.setWitnessName(StringUtils.isNotBlank(preOperativeConsent.getWitness()) ? pp.getWitnessName() :"");
			preOperativeConsent.setRecordManName(StringUtils.isNotBlank(preOperativeConsent.getRecordMan()) ? pp.getRecordManName() : "");
		}
		return list;
	}
	
	@Transactional(readOnly = false)
	public void save(PreOperativeConsent preOperativeConsent,HttpServletRequest request) {
		super.save(preOperativeConsent);
		this.savefj(request,preOperativeConsent);
	}
	
	@Transactional(readOnly = false)
	public void delete(PreOperativeConsent preOperativeConsent) {
		super.delete(preOperativeConsent);
	}
	@Transactional(readOnly = false)
	public void save1(String acceId1,String itemId1,String files1,String fjtype){
		super.dao.insertzf(acceId1,itemId1,files1,fjtype);
	}
	@Transactional(readOnly = false)
	public void updatefj(String files1,String itemId1,String fjtype){
		super.dao.updateFile(files1,itemId1,fjtype);
	}
	@Transactional(readOnly = false)
	public void delefj(String itemId1,String fjtype){
		super.dao.deletfj(itemId1,fjtype);
	}
	//保存附件
	@Transactional(readOnly = false)
	public void savefj(HttpServletRequest request,PreOperativeConsent preOperativeConsent){
		String files1 = request.getParameter("files1");
		String files2 = request.getParameter("files2");
		String acceId = null;
		String itemId = preOperativeConsent.getId();
		String fjtype1 = request.getParameter("fjtype1");
		String fjtype2 = request.getParameter("fjtype2");
		if(StringUtils.isNotBlank(files1)){
			String acceId1=request.getParameter("acceId1");
			if(StringUtils.isNotBlank(acceId1)){
				this.updatefj(files1,itemId,fjtype1);
			}else{
				acceId = IdGen.uuid();
				this.save1(acceId,itemId,files1,fjtype1);
			}
		}else{
			this.delefj(itemId,fjtype1);
		}
		if(StringUtils.isNotBlank(files2)){
			String acceId2=request.getParameter("acceId2");
			if(StringUtils.isNotBlank(acceId2)){
				this.updatefj(files2,itemId,fjtype2);
			}else{
				acceId = IdGen.uuid();
				this.save1(acceId,itemId,files2,fjtype2);
			}
		}else{
			this.delefj(itemId,fjtype2);
		}
	}

    public void exportWord(String type,HttpServletRequest request, HttpServletResponse response) {
		WordExportUtil wordExportUtil=new WordExportUtil();
		String path=request.getServletContext().getRealPath("/");
		String savaPath=path;
		String fileName = "见证签到表.docx";
		if ("bl".equals(type)){
			fileName="手术—见证会笔录.docx";
			path += "/doc/hospitalRecord.docx";  //模板文件位置
			savaPath +="/userfiles/preOperativeConsent/hospitalRecord.docx";
		}else if("dj".equals(type)){
			fileName="医院投诉处理登记表.docx";
			path += "/doc/complaintRegistration.docx";  //模板文件位置
			savaPath +="/userfiles/preOperativeConsent/complaintRegistration.docx";
		}else {
			path += "/doc/signIn.docx";  //模板文件位置
			savaPath +="/userfiles/preOperativeConsent/signIn.docx";
		}
		try{
			File file =new File(request.getServletContext().getRealPath("/")+"/userfiles/preOperativeConsent/");
			if (!file.exists()){
				file.mkdirs();
			}
			List<String[]> testList = new ArrayList<String[]>();
			String fileNameNew= new String(fileName.getBytes("UTF-8"),"iso-8859-1");    //生成word文件的文件名
			wordExportUtil.getWord(path,path,savaPath,"false",null,null,fileNameNew,response);
		}catch(Exception e){
			e.printStackTrace();
		}

    }
}