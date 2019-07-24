/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaint.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.common.utils.BaseUtils;
import com.sayee.sxsy.common.utils.DateUtils;
import com.sayee.sxsy.common.utils.excel.ExportExcel;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sayee.sxsy.common.config.Global;
import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.web.BaseController;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.complaint.entity.ComplaintInfo;
import com.sayee.sxsy.modules.complaint.service.ComplaintInfoService;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 投诉接待Controller
 * @author zhangfan
 * @version 2019-05-27
 */
@Controller
@RequestMapping(value = "${adminPath}/complaint/complaintInfo")
public class ComplaintInfoController extends BaseController {

	@Autowired
	private ComplaintInfoService complaintInfoService;
	
	@ModelAttribute
	public ComplaintInfo get(@RequestParam(required=false) String id) {
		ComplaintInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = complaintInfoService.get(id);
		}
		if (entity == null){
			entity = new ComplaintInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("complaint:complaintInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(ComplaintInfo complaintInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ComplaintInfo> page = complaintInfoService.findPage(new Page<ComplaintInfo>(request, response), complaintInfo);

		model.addAttribute("page", page);
		return "modules/complaint/complaintInfoList";
	}

	@RequiresPermissions("complaint:complaintInfo:view")
	@RequestMapping(value = "form")
	public String form(HttpServletRequest request, ComplaintInfo complaintInfo, Model model) {
		if (null==complaintInfo.getCaseNumber()){
			ComplaintMain complaintMain=new ComplaintMain();
			complaintMain.setCaseNumber(BaseUtils.getCode("time","3","COMPLAINT_MAIN","case_number"));
			complaintInfo.setCaseNumber(complaintMain.getCaseNumber());
		}
		String type=request.getParameter("type");
		if ("view".equals(type)) {
			String show2=request.getParameter("show2");
			model.addAttribute("show2",show2);
			model.addAttribute("complaintInfo", complaintInfo);
			return "modules/complaint/complaintInfoView";
		}else {
			model.addAttribute("complaintInfo", complaintInfo);
			return "modules/complaint/complaintInfoForm";
		}

	}

	@RequiresPermissions("complaint:complaintInfo:edit")
	@RequestMapping(value = "save")
	public String save(ComplaintInfo complaintInfo, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, complaintInfo )){
			return form(request,complaintInfo, model);
		}
		boolean a= complaintInfoService.save(complaintInfo,request);
		if(false==a){
			addMessage(model, "案件编号 "+complaintInfo.getCaseNumber()+" 重复");
			return form(request,complaintInfo, model);
		}
		addMessage(redirectAttributes, "保存投诉接待成功");
		return "redirect:"+Global.getAdminPath()+"/complaint/complaintInfo/?repage";
	}
	
	@RequiresPermissions("complaint:complaintInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(ComplaintInfo complaintInfo, RedirectAttributes redirectAttributes) {
		complaintInfoService.delete(complaintInfo);
		addMessage(redirectAttributes, "删除投诉接待成功");
		return "redirect:"+Global.getAdminPath()+"/complaint/complaintInfo/?repage";
	}

	//工作量统计
	@RequestMapping(value = "statement")
	public String statement( HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<List> page = complaintInfoService.statementPage(new Page<List>(request, response),request, response);
		model.addAttribute("page", page);
		model.addAttribute("type", request.getParameter("type"));
		model.addAttribute("visitorDate", request.getParameter("visitorDate"));
		model.addAttribute("visitorMonthDate", request.getParameter("visitorMonthDate"));
		model.addAttribute("involveDepartment", request.getParameter("involveDepartment"));
		model.addAttribute("involveEmployee", request.getParameter("involveEmployee"));
		return "modules/complaint/numericalStatement";
	}

	@RequestMapping(value = "export",method = RequestMethod.POST)
	public String exportExcel(HttpServletResponse response,HttpServletRequest request,RedirectAttributes redirectAttributes){
		try{
			String date = request.getParameter("type");
			Page<List> page = complaintInfoService.statementPage(new Page<List>(request, response),request, response);

			String[] title = {"部门","人员","医院转办数量","当面处理数量","投诉接待数量(总)","转调解数量","同意书见证","接待日期"};
			if(page.getList().size()!=0){

				if("day".equals(date)){
					String fileName = "日工作量统计" + DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
					new ExportExcel("日工作量统计",title).setDataList(page.getList()).write(response,fileName).dispose();
				}else if("month".equals(date)){
					String fileName = "月工作量统计" + DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
					new ExportExcel("月工作量统计",title).setDataList(page.getList()).write(response,fileName).dispose();
				}
				return null;
			}else{
				addMessage(redirectAttributes, "导出工作量统计失败！失败信息：这段时间内没有工作量数据可导出");
				return "redirect:"+Global.getAdminPath()+"/complaint/complaintInfo/?repage";
			}
		}catch (Exception e){
			addMessage(redirectAttributes,"导出工作量统计数据失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/complaint/complaintInfo/statement";
	}

}