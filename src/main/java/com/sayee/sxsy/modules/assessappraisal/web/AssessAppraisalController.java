/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.assessappraisal.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sayee.sxsy.common.utils.BaseUtils;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.machine.service.MachineAccountService;
import com.sayee.sxsy.modules.proposal.entity.Proposal;
import com.sayee.sxsy.modules.proposal.service.ProposalService;
import com.sayee.sxsy.modules.sign.service.SignAgreementService;
import com.sayee.sxsy.modules.summaryinfo.service.SummaryInfoService;
import com.sayee.sxsy.modules.sys.utils.FileBaseUtils;
import com.sayee.sxsy.modules.typeinfo.entity.TypeInfo;
import com.sayee.sxsy.modules.typeinfo.service.TypeInfoService;
import org.apache.commons.collections.ListUtils;
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
import com.sayee.sxsy.modules.assessappraisal.entity.AssessAppraisal;
import com.sayee.sxsy.modules.assessappraisal.service.AssessAppraisalService;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * 评估鉴定Controller
 * @author gbq
 * @version 2019-06-13
 */
@Controller
@RequestMapping(value = "${adminPath}/assessappraisal/assessAppraisal")
public class AssessAppraisalController extends BaseController {

	@Autowired
	private AssessAppraisalService assessAppraisalService;
	@Autowired
	private SignAgreementService signAgreementService;
	@Autowired
	private TypeInfoService typeInfoService;
	@Autowired
	SummaryInfoService summaryInfoService;
	@Autowired
    private MachineAccountService machineAccountService;
	@Autowired
	ProposalService proposalService;
	@ModelAttribute
	public AssessAppraisal get(@RequestParam(required=false) String id) {
		AssessAppraisal entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = assessAppraisalService.get(id);
		}
		if (entity == null){
			entity = new AssessAppraisal();
		}
		return entity;
	}

	@RequiresPermissions("assessappraisal:assessAppraisal:view")
	@RequestMapping(value = {"list", ""})
	public String list(AssessAppraisal assessAppraisal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AssessAppraisal> page = assessAppraisalService.findPage(new Page<AssessAppraisal>(request, response), assessAppraisal);
		model.addAttribute("page", page);
		return "modules/assessappraisal/assessAppraisalList";
	}

	@RequiresPermissions("assessappraisal:assessAppraisal:view")
	@RequestMapping(value = "form")
	public String form(AssessAppraisal assessAppraisal, Model model,HttpServletRequest request) {
		if(null==assessAppraisal.getProposal()){
			Proposal proposal =new Proposal();
			List<Proposal> list = proposalService.findList(proposal);
			if(list.size()==0){
				String code = BaseUtils.getCode("time", "3", "PROPOSAL", "proposal_code");
				String c1= code.substring(0, 4);
				proposal.setProposalCode("晋医人调鉴(评)["+c1+"]001号");
				assessAppraisal.setProposal(proposal);
			}else{
			    int max=0;
			    for(int i=0;i<list.size();i++){
                    String proposalCode = list.get(i).getProposalCode();
                    String d = proposalCode.substring(14, 17);
                    int d1=Integer.valueOf(d);
                    if(d1>max){
                        max=d1;
                    }
                }
                int d2=max+1;
				String a=BaseUtils.getCode("time","3","PROPOSAL","proposal_code");
				String c=a.substring(0,4);
				String format = String.format("%0" + 3 + "d", d2);
				String e1="晋医人调鉴(评)["+c+"]"+format;
				proposal.setProposalCode(e1);
				assessAppraisal.setProposal(proposal);

			}
		}

		List<TypeInfo> fxyj = BaseUtils.getType("1");
		List<TypeInfo> jl = BaseUtils.getType("2");
		if (assessAppraisal.getProposal()!=null ){
			signAgreementService.label(fxyj,assessAppraisal.getProposal().getAnalysisOpinion());
			signAgreementService.label(jl,assessAppraisal.getProposal().getConclusion());
		}
		model.addAttribute("fxyj",fxyj);
		model.addAttribute("jl",jl);
		//获取附件
		List<Map<String, Object>> filePath = FileBaseUtils.getFilePath(assessAppraisal.getAssessAppraisalId());
		for (Map<String, Object> map:filePath) {
			if("16".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files1",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId1",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("17".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files2",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId2",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("18".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files3",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId3",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("19".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files4",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId4",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("20".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files5",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId5",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("21".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files6",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId6",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}else if("22".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files7",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
				model.addAttribute("acceId7",MapUtils.getString(map,"ACCE_ID",MapUtils.getString(map,"acce_id","")));
			}
		}
        String type = request.getParameter("type");
		if("view".equals(type)){
			String show2=request.getParameter("show2");
			model.addAttribute("show2",show2);
			Map<String, Object> map = summaryInfoService.getViewDetail(assessAppraisal.getComplaintMainId());
			model.addAttribute("map",map);
			model.addAttribute("assessAppraisal", assessAppraisal);
			return "modules/assessappraisal/assessAppraisalView";
		}else{
			model.addAttribute("assessAppraisal", assessAppraisal);
			return "modules/assessappraisal/assessAppraisalForm";
		}

	}

	@RequiresPermissions("assessappraisal:assessAppraisal:edit")
	@RequestMapping(value = "save")
	public String save(AssessAppraisal assessAppraisal, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request,HttpServletResponse response) {
//		if (!beanValidator(model, assessAppraisal)&&"yes".equals(assessAppraisal.getComplaintMain().getAct().getFlag())||!beanValidator(model,assessAppraisal.getComplaintMain())&&"yes".equals(assessAppraisal.getComplaintMain().getAct().getFlag())||!beanValidator(model,assessAppraisal.getRecordInfo1())&&"yes".equals(assessAppraisal.getComplaintMain().getAct().getFlag())||!beanValidator(model,assessAppraisal.getRecordInfo1().getYrecordInfo())&&"yes".equals(assessAppraisal.getComplaintMain().getAct().getFlag())||!beanValidator(model,assessAppraisal.getProposal())&&"yes".equals(assessAppraisal.getComplaintMain().getAct().getFlag())){
//			return form(assessAppraisal, model,request);
//		}
		String export=request.getParameter("export");
		if (StringUtils.isNotBlank(export) && !export.equals("no")){
			AssessAppraisal assessAppraisal1 = assessAppraisalService.get(assessAppraisal.getAssessAppraisalId());
			assessAppraisalService.exportWord(assessAppraisal1,export,request,response);
			return "";
		}else {
			try {
				assessAppraisalService.save(assessAppraisal, request);
				machineAccountService.savetz(assessAppraisal.getMachineAccount(), "d", assessAppraisal.getAssessAppraisalId());
				if ("yes".equals(assessAppraisal.getComplaintMain().getAct().getFlag())) {
					addMessage(redirectAttributes, "流程已启动，流程ID：" + assessAppraisal.getComplaintMain().getProcInsId());
				} else {
					addMessage(redirectAttributes, "保存评估鉴定成功");
				}

			} catch (Exception e) {
				logger.error("启动鉴定评估流程失败：", e);
				addMessage(redirectAttributes, "系统内部错误");

			}
			return "redirect:" + Global.getAdminPath() + "/assessappraisal/assessAppraisal/?repage";
		}
	}

	@RequiresPermissions("assessappraisal:assessAppraisal:edit")
	@RequestMapping(value = "delete")
	public String delete(AssessAppraisal assessAppraisal, RedirectAttributes redirectAttributes) {
		assessAppraisalService.delete(assessAppraisal);
		addMessage(redirectAttributes, "删除评估鉴定成功");
		return "redirect:"+Global.getAdminPath()+"/assessappraisal/assessAppraisal/?repage";
	}

}