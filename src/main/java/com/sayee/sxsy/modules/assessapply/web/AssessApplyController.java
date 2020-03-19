/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessapply.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.modules.summaryinfo.service.SummaryInfoService;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.utils.FileBaseUtils;
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
import com.sayee.sxsy.modules.assessapply.entity.AssessApply;
import com.sayee.sxsy.modules.assessapply.service.AssessApplyService;

import java.util.List;
import java.util.Map;

/**
 * 评估申请Controller
 * @author zhangfan
 * @version 2019-06-11
 */
@Controller
@RequestMapping(value = "${adminPath}/assessapply/assessApply")
public class AssessApplyController extends BaseController {

	@Autowired
	private AssessApplyService assessApplyService;
	@Autowired
	SummaryInfoService summaryInfoService;
	/*@Autowired
	private PreOperativeConsentService preOperativeConsentService;*/
	@ModelAttribute
	public AssessApply get(@RequestParam(required=false) String id) {
		AssessApply entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = assessApplyService.get(id);
		}
		if (entity == null){
			entity = new AssessApply();
		}
		return entity;
	}
	
	@RequiresPermissions("assessapply:assessApply:view")
	@RequestMapping(value = {"list", ""})
	public String list(AssessApply assessApply, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AssessApply> page = assessApplyService.findPage(new Page<AssessApply>(request, response), assessApply); 
		model.addAttribute("page", page);
		return "modules/assessapply/assessApplyList";
	}

	@RequiresPermissions("assessapply:assessApply:view")
	@RequestMapping(value = "form")
	public String form(AssessApply assessApply, Model model,HttpServletRequest request) {
		String type = request.getParameter("type");		//接受从页面传回的数据
		List<Map<String, Object>> filePath = FileBaseUtils.getFilePath(assessApply.getAssessApplyId());
		for (Map<String, Object> map:filePath){
			if ("1".equals(MapUtils.getString(map, "fjtype"))) {
				model.addAttribute("files1", MapUtils.getString(map, "FILE_PATH", MapUtils.getString(map, "file_path", "")));
				model.addAttribute("acceId1",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}
			if ("2".equals(MapUtils.getString(map, "fjtype"))) {
				model.addAttribute("files2", MapUtils.getString(map, "FILE_PATH", MapUtils.getString(map, "file_path", "")));
				model.addAttribute("acceId2",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}
		}
		if("view".equals(type)){
			String show2=request.getParameter("show2");
			model.addAttribute("show2",show2);
			Map<String, Object> map = summaryInfoService.getViewDetail(assessApply.getComplaintMainId());
			model.addAttribute("map",map);
			model.addAttribute("assessApply", assessApply);
			return "modules/assessapply/assessApplyView";
		}else{
			model.addAttribute("assessApply", assessApply);
			return "modules/assessapply/assessApplyForm";
		}

	}

	@RequiresPermissions("assessapply:assessApply:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,AssessApply assessApply, Model model, RedirectAttributes redirectAttributes) {
		if (  "yes".equals(assessApply.getComplaintMain().getAct().getFlag()) && (!beanValidator(model, assessApply)||!beanValidator(model,assessApply.getComplaintMain())) ){
			return form(assessApply, model,request);
		}
		try {
			assessApplyService.save(assessApply,request);
			if ("yes".equals(assessApply.getComplaintMain().getAct().getFlag())){
				addMessage(redirectAttributes, "流程已启动，流程ID：" + assessApply.getComplaintMain().getProcInsId());
			}else {
				addMessage(redirectAttributes, "保存评估申请成功");
			}
		} catch (Exception e) {
			logger.error("启动纠纷调解流程失败：", e);
			addMessage(redirectAttributes, "系统内部错误！");
		}
//		if (!beanValidator(model, assessApply)){
//			return form(assessApply, model);
//		}
//		assessApplyService.save(assessApply);
		return "redirect:"+Global.getAdminPath()+"/assessapply/assessApply/?repage";
	}
	
	@RequiresPermissions("assessapply:assessApply:edit")
	@RequestMapping(value = "delete")
	public String delete(AssessApply assessApply, RedirectAttributes redirectAttributes) {
		assessApplyService.delete(assessApply);
		addMessage(redirectAttributes, "删除评估申请成功");
		return "redirect:"+Global.getAdminPath()+"/assessapply/assessApply/?repage";
	}

}