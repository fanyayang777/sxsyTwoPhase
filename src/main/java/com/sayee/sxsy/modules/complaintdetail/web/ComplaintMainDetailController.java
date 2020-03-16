/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaintdetail.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import com.sayee.sxsy.common.utils.BaseUtils;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.complaintmain.service.ComplaintMainService;
import com.sayee.sxsy.modules.sys.utils.FileBaseUtils;
import com.sayee.sxsy.test.entity.TestTree;
import com.sayee.sxsy.test.service.TestTreeService;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sayee.sxsy.common.config.Global;
import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.web.BaseController;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.complaintdetail.entity.ComplaintMainDetail;
import com.sayee.sxsy.modules.complaintdetail.service.ComplaintMainDetailService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 医调委投诉接待Controller
 * @author zhangfan
 * @version 2019-06-05
 */
@Controller
@RequestMapping(value = "${adminPath}/complaintdetail/complaintMainDetail")
public class ComplaintMainDetailController extends BaseController {


	@Autowired
	private ComplaintMainDetailService complaintMainDetailService;
	@Autowired
	private ComplaintMainService complaintMainService;
	@ModelAttribute
	public ComplaintMainDetail get(@RequestParam(required=false) String id) {
		ComplaintMainDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = complaintMainDetailService.get(id);
		}
		if (entity == null){
			entity = new ComplaintMainDetail();
		}
        return entity;
	}
	
	@RequiresPermissions("complaintdetail:complaintMainDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(ComplaintMainDetail complaintMainDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ComplaintMainDetail> page = complaintMainDetailService.findPage(new Page<ComplaintMainDetail>(request, response), complaintMainDetail); 
		model.addAttribute("page", page);
		return "modules/complaintdetail/complaintMainDetailList";
	}

	@RequiresPermissions("complaintdetail:complaintMainDetail:view")
	@RequestMapping(value = "form")
	public String form(ComplaintMainDetail complaintMainDetail, Model model,HttpServletRequest request) {
		List<Map<String, Object>> filePath = FileBaseUtils.getFilePath(complaintMainDetail.getComplaintMainDetailId());
		if (filePath.size()==0 && complaintMainDetail.getComplaintMain()!=null){
			filePath = FileBaseUtils.getFilePath(complaintMainDetail.getComplaintMain().getComplaintId());
			for (Map<String, Object> map:filePath){
				map.remove("ACCE_ID");
				map.remove("acce_id");
			}
		}
		for (Map<String, Object> map:filePath){
			if ("2".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files1",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId1",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("1".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files2",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId2",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}
		}
		if (null==complaintMainDetail.getComplaintMain()){
			ComplaintMain complaintMain=new ComplaintMain();
			complaintMain.setCaseNumber(BaseUtils.getCode("year","4","COMPLAINT_MAIN","case_number"));
			complaintMainDetail.setComplaintMain(complaintMain);
		}
		String type=request.getParameter("type");
		if(StringUtils.isNotBlank(complaintMainDetail.getComplaintMainDetailId())){
			ComplaintMain complaintMain = complaintMainService.get(complaintMainDetail.getComplaintMainId());
			complaintMainDetail.getComplaintMain().setTestTree(complaintMain.getTestTree());
		}
			if ("view".equals(type)) {
				String show2=request.getParameter("show2");
				model.addAttribute("show2",show2);

				model.addAttribute("complaintMainDetail", complaintMainDetail);
				return "modules/complaintdetail/complaintMainDetailView";
			}else {

			model.addAttribute("complaintMainDetail", complaintMainDetail);
			return "modules/complaintdetail/complaintMainDetailForm";
		}
	}

	@RequiresPermissions("complaintdetail:complaintMainDetail:edit")
	@RequestMapping(value = "save")
	public String save(ComplaintMainDetail complaintMainDetail, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		try {
			if (!beanValidator(model, complaintMainDetail)&&"yes".equals(complaintMainDetail.getComplaintMain().getAct().getFlag()) || !beanValidator(model, complaintMainDetail.getComplaintMain())&&"yes".equals(complaintMainDetail.getComplaintMain().getAct().getFlag()) ){
				return form(complaintMainDetail, model,request);
			}
				complaintMainDetailService.save(complaintMainDetail,request);
				if ("yes".equals(complaintMainDetail.getComplaintMain().getAct().getFlag())){
					addMessage(redirectAttributes, "流程已启动");
					return "redirect:"+Global.getAdminPath()+"/complaintdetail/complaintMainDetail/?repage";
				}else {
					model.addAttribute("message","保存投诉接待成功");
					return form(this.get(complaintMainDetail.getComplaintMainDetailId()), model, request);
				}
		} catch (Exception e) {
			logger.error("启动纠纷调解流程失败：", e);
			addMessage(redirectAttributes, "系统内部错误！");
			return "redirect:"+Global.getAdminPath()+"/complaintdetail/complaintMainDetail/?repage";
		}

//		return "redirect:" + adminPath + "/oa/leave/form";
//
//		if (!beanValidator(model, complaintMainDetail)){
//			return form(complaintMainDetail, model);
//		}


	}
	
	@RequiresPermissions("complaintdetail:complaintMainDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(ComplaintMainDetail complaintMainDetail, RedirectAttributes redirectAttributes) {
		complaintMainDetailService.delete(complaintMainDetail);
		addMessage(redirectAttributes, "删除投诉接待成功");
		return "redirect:"+Global.getAdminPath()+"/complaintdetail/complaintMainDetail/?repage";
	}

	@RequestMapping(value = "shift")
	public String shift(ComplaintMainDetail complaintMainDetail, Model model,HttpServletRequest request) {
		model.addAttribute("complaintMainDetail", complaintMainDetail);
		return "modules/complaintdetail/shift";
	}

	@RequestMapping(value = "saveShift")
	public String saveShift(ComplaintMainDetail complaintMainDetail, RedirectAttributes redirectAttributes) {
		complaintMainDetailService.saveShift(complaintMainDetail);
		addMessage(redirectAttributes, "转交成功");
		return "redirect:"+Global.getAdminPath()+"/complaintdetail/complaintMainDetail/?repage";
	}

}