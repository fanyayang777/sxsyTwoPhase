/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaint.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.common.beanvalidator.BeanValidators;
import com.sayee.sxsy.common.utils.AjaxHelper;
import com.sayee.sxsy.common.utils.BaseUtils;
import com.sayee.sxsy.common.utils.DateUtils;
import com.sayee.sxsy.common.utils.excel.ExportExcel;
import com.sayee.sxsy.modules.act.entity.Act;
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import com.sayee.sxsy.modules.registration.service.ReportRegistrationService;
import com.sayee.sxsy.modules.stopmediate.entity.StopMediate;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.DictUtils;
import com.sayee.sxsy.modules.sys.utils.FileBaseUtils;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private ReportRegistrationService reportRegistrationService;

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
		String node=request.getParameter("node");
		if ("sjy".equals(node) || "fpy".equals(node)){
			complaintInfo.setAssignee(UserUtils.getUser().getLoginName());
		}
		Page<ComplaintInfo> page = complaintInfoService.findPage(new Page<ComplaintInfo>(request, response), complaintInfo);
		model.addAttribute("page", page);
		model.addAttribute("node", node);
		return "modules/complaint/complaintInfoList";
	}

	@RequiresPermissions("complaint:complaintInfo:view")
	@RequestMapping(value = "form")
	public String form(HttpServletRequest request, ComplaintInfo complaintInfo, Model model) {
		List<Map<String, Object>> filePath = FileBaseUtils.getFilePath(complaintInfo.getComplaintId());
		for (Map<String, Object> map:filePath){
			if ("2".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files1",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId1",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("1".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files2",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId2",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}
		}
		if (null==complaintInfo.getCaseNumber()){
			ComplaintMain complaintMain=new ComplaintMain();
			complaintMain.setCaseNumber(BaseUtils.getCode("year","4","COMPLAINT_MAIN","case_number"));
			complaintInfo.setCaseNumber(complaintMain.getCaseNumber());
		}
		String type=request.getParameter("type");
		String node=request.getParameter("node");
		model.addAttribute("node",node);
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
	public String save(ComplaintInfo complaintInfo, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request,HttpServletResponse response) {
        String export=request.getParameter("export");
        if (StringUtils.isNotBlank(export) && !export.equals("no")){
            ReportRegistration reportRegistration=reportRegistrationService.get(complaintInfo.getReportRegistration().getReportRegistrationId());
            String path=reportRegistrationService.exportWord(reportRegistration,export,"false",request,response);
            return path;
        }else{
            ReportRegistration reportRegistration=complaintInfo.getReportRegistration();
            if ("yes".equals(request.getParameter("flag")) && !beanValidator(model, reportRegistration ) || !beanValidator(model, complaintInfo )  ){
                //点击下一步的时候 进行后台字段验证
                return form(request,complaintInfo, model);
            }
            Act act= complaintInfoService.save(complaintInfo,request);
            if("false".equals(act.getFlag())){
                addMessage(model, "案件编号 "+complaintInfo.getCaseNumber()+" 重复");
                return form(request,complaintInfo, model);
            }else {
                addMessage(redirectAttributes, StringUtils.isNotBlank(act.getProcInsId())?"流程启动成功":"保存成功！");
                return "redirect:"+Global.getAdminPath()+"/complaint/complaintInfo/?repage";
            }
        }
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
		model.addAttribute("visitorDateEnd", request.getParameter("visitorDateEnd"));
		model.addAttribute("visitorMonthDate", request.getParameter("visitorMonthDate"));
		model.addAttribute("visitorMonthDateEnd", request.getParameter("visitorMonthDateEnd"));
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

	@RequestMapping(value = "audit")
	@Transactional(readOnly = false)
	public String audit(ComplaintInfo complaintInfo, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		String node=request.getParameter("node");
		String status=request.getParameter("status");
		model.addAttribute("node",node);
		try {
			boolean flag=complaintInfoService.audit(complaintInfo,request,model);
			if (flag){
				if (StringUtils.isNotBlank(status)){
					addMessage(redirectAttributes, "流程"+("0".equals(status)? "通过" :"驳回")+"成功！");
					return "redirect:"+Global.getAdminPath()+"/complaint/complaintInfo/?repage&node="+node;
				}else {
					model.addAttribute("message","保存失败！");
					return form(request,complaintInfo, model);
				}
			}else {
				addMessage(redirectAttributes, "所在区域案件分配角色下面没有人员，请联系管理员增加！");
				return "redirect:"+Global.getAdminPath()+"/complaint/complaintInfo/?repage&node="+node;
			}

		} catch (Exception e) {
			logger.error("audit===========：", e);
			addMessage(redirectAttributes, "系统内部错误,请联系工程师修正！");
			return "redirect:"+Global.getAdminPath()+"/complaint/complaintInfo/?repage";
		}
	}
	//选择医院 传  城市
	@RequestMapping(value = "officeInfo")
	public void officeInfo(HttpServletRequest request,HttpServletResponse response) {
		String code="";//1.成功 0失败
		Map<String,Object> map=new HashMap<String,Object>();
		String hospital=request.getParameter("hospital");//前台传过来的状态
//		String hospital=request.getParameter("involveHospital");//前台传过来的状态
		if (StringUtils.isBlank(hospital)){
			map.put("status","0");
			AjaxHelper.responseWrite(request,response,"1","success",map);
			return;
		}
		Office o=UserUtils.getOfficeId(hospital);

		if(StringUtils.isNotBlank(o.getHospitalGrade())){
			String label=DictUtils.getDictLabel(o.getHospitalGrade(),"hospital_grade","其他");
			map.put("hospitalGrade",label); //医院等级
		}

		map.put("policyNumber",o.getPolicyNumber()); //保单号
		map.put("gradeValue",o.getHospitalGrade()); //医院等级 value

		map.put("areaName",o.getArea().getName());	//医院地址
		map.put("areaId",o.getArea().getId());	//医院地址
		AjaxHelper.responseWrite(request,response,"1","success",map);
	}



}