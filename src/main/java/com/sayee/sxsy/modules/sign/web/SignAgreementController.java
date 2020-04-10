/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.sign.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sayee.sxsy.common.utils.*;
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.complaintmain.service.ComplaintMainService;
import com.sayee.sxsy.modules.medicalofficeemp.entity.MedicalOfficeEmp;
import com.sayee.sxsy.modules.patientlinkemp.entity.PatientLinkEmp;
import com.sayee.sxsy.modules.recordinfo.entity.RecordInfo;
import com.sayee.sxsy.modules.signtype.entity.SignTypeInfo;
import com.sayee.sxsy.modules.summaryinfo.service.SummaryInfoService;
import com.sayee.sxsy.modules.machine.service.MachineAccountService;
import com.sayee.sxsy.modules.sys.utils.FileBaseUtils;
import com.sayee.sxsy.modules.typeinfo.entity.TypeInfo;
import org.activiti.engine.impl.json.JsonListConverter;
import org.activiti.explorer.util.StringUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sayee.sxsy.common.config.Global;
import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.web.BaseController;
import com.sayee.sxsy.modules.sign.entity.SignAgreement;
import com.sayee.sxsy.modules.sign.service.SignAgreementService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 签署协议Controller
 *
 * @author zhangfan
 * @version 2019-06-14
 */
@Controller
@RequestMapping(value = "${adminPath}/sign/signAgreement")
public class SignAgreementController extends BaseController {

    @Autowired
    private SignAgreementService signAgreementService;
    @Autowired
    SummaryInfoService summaryInfoService;
    @Autowired
    private MachineAccountService machineAccountService;
    @Autowired
    ComplaintMainService complaintMainService;

    @ModelAttribute
    public SignAgreement get(@RequestParam(required = false) String id) {
        SignAgreement entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = signAgreementService.get(id);
        }
        if (entity == null) {
            entity = new SignAgreement();
        }
        return entity;
    }

    @RequiresPermissions("sign:signAgreement:view")
    @RequestMapping(value = {"list", ""})
    public String list(SignAgreement signAgreement, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<SignAgreement> page = signAgreementService.findPage(new Page<SignAgreement>(request, response), signAgreement);
        model.addAttribute("page", page);
        return "modules/sign/signAgreementList";
    }

    @RequiresPermissions("sign:signAgreement:view")
    @RequestMapping(value = "form")
    public String form(SignAgreement signAgreement, Model model, HttpServletRequest request) {

        ComplaintMain complaintMain = complaintMainService.get(signAgreement.getComplaintMainId());
        if (signAgreement.getPatientLinkEmpList().size() == 0 && complaintMain != null) {
            List<PatientLinkEmp> patientLinkEmpList = signAgreement.getPatientLinkEmpList();
            PatientLinkEmp patientLinkEmp = new PatientLinkEmp();
            patientLinkEmp.setPatientLinkName(complaintMain.getPatientName());
            patientLinkEmp.setPatientLinkSex(complaintMain.getPatientSex());
            patientLinkEmp.setPatientRelation("1");
            patientLinkEmp.setIdNumber(complaintMain.getPatientCard());
            patientLinkEmpList.add(patientLinkEmp);
        }
        if (signAgreement.getMedicalOfficeEmpList().size() == 0 && complaintMain != null) {
            MedicalOfficeEmp medicalOfficeEmp = new MedicalOfficeEmp();
            List<MedicalOfficeEmp> medicalOfficeEmpList = signAgreement.getMedicalOfficeEmpList();
            medicalOfficeEmp.setMedicalOfficeName(complaintMain.getHospital().getName());
            medicalOfficeEmp.setMedicalOfficeAgent(signAgreement.getAuditAcceptance().getMediateApplyInfo().getAgent());
            medicalOfficeEmp.setMedicalOfficeMobile(signAgreement.getAuditAcceptance().getMediateApplyInfo().getHospitalMobile());
            medicalOfficeEmpList.add(medicalOfficeEmp);
        }
        List<TypeInfo> info = new ArrayList<>();
        //在修改时 拿到 用逗号分割的数据  进行处理
        info = BaseUtils.getType("3");
        signAgreementService.label(info, signAgreement.getMediation());
        List<SignTypeInfo> tjqk = BaseUtils.getType("3", signAgreement.getSignAgreementId());
        signAgreementService.signLabel(tjqk, signAgreement.getMediation());
        model.addAttribute("tjqk", tjqk.size() > 0 ? tjqk : info);

        info = BaseUtils.getType("4");
        signAgreementService.label(info, signAgreement.getMediation());
        List<SignTypeInfo> xyydsx = BaseUtils.getType("4", signAgreement.getSignAgreementId());
        signAgreementService.signLabel(xyydsx, signAgreement.getAgreedMatter());
        model.addAttribute("xyydsx", xyydsx.size() > 0 ? xyydsx : info);

        info = BaseUtils.getType("5");
        signAgreementService.label(info, signAgreement.getMediation());
        List<SignTypeInfo> lxxyfs = BaseUtils.getType("5", signAgreement.getSignAgreementId());
        signAgreementService.signLabel(lxxyfs, signAgreement.getPerformAgreementMode());
        model.addAttribute("lxxyfs", lxxyfs.size() > 0 ? lxxyfs : info);

        info = BaseUtils.getType("6");
        signAgreementService.label(info, signAgreement.getMediation());
        List<SignTypeInfo> xysm = BaseUtils.getType("6", signAgreement.getSignAgreementId());
        signAgreementService.signLabel(xysm, signAgreement.getAgreementExplain());
        model.addAttribute("xysm", xysm.size() > 0 ? xysm : info);
        //附件展示
        List<Map<String, Object>> filePath = FileBaseUtils.getFilePath(signAgreement.getSignAgreementId());
        for (Map<String, Object> map : filePath) {
            if ("7".equals(MapUtils.getString(map, "fjtype"))) {
                model.addAttribute("files1", MapUtils.getString(map, "FILE_PATH", MapUtils.getString(map, "file_path", "")));
                model.addAttribute("acceId1", MapUtils.getString(map, "ACCE_ID", MapUtils.getString(map, "acce_id", "")));
            } else if ("8".equals(MapUtils.getString(map, "fjtype"))) {
                model.addAttribute("files2", MapUtils.getString(map, "FILE_PATH", MapUtils.getString(map, "file_path", "")));
                model.addAttribute("acceId2", MapUtils.getString(map, "ACCE_ID", MapUtils.getString(map, "acce_id", "")));
            } else if ("9".equals(MapUtils.getString(map, "fjtype"))) {
                model.addAttribute("files3", MapUtils.getString(map, "FILE_PATH", MapUtils.getString(map, "file_path", "")));
                model.addAttribute("acceId3", MapUtils.getString(map, "ACCE_ID", MapUtils.getString(map, "acce_id", "")));
            } else if ("10".equals(MapUtils.getString(map, "fjtype"))) {
                model.addAttribute("files4", MapUtils.getString(map, "FILE_PATH", MapUtils.getString(map, "file_path", "")));
                model.addAttribute("acceId4", MapUtils.getString(map, "ACCE_ID", MapUtils.getString(map, "acce_id", "")));
            }
        }
        String type = request.getParameter("type");
        if ("view".equals(type)) {
            String show2 = request.getParameter("show2");
            model.addAttribute("show2", show2);
            Map<String, Object> map = summaryInfoService.getViewDetail(signAgreement.getComplaintMainId());
            model.addAttribute("map", map);
            model.addAttribute("signAgreement", signAgreement);
            return "modules/sign/signAgreementView";
        } else {
            if (signAgreement != null) {
                if (signAgreement.getRecordInfo() == null) {
                    RecordInfo recordInfo = new RecordInfo();
                    recordInfo.setRecordContent("调解员:请全体起立，宣读人民调解协议书。\n确认协议书中约定的履行事项双方是否听清?\n患方:\n医方:\n调解员:双方对协议有无异议?是否自愿签署协议?\n患方:\n医方:\n调解员:请双方签名、按手印确认。\n患方:\n医方:");
                    signAgreement.setRecordInfo(recordInfo);
                }
            }
            model.addAttribute("com",signAgreement.getComplaintMainId());
            model.addAttribute("signAgreement", signAgreement);
            return "modules/sign/signAgreementForm";
        }
    }
    @RequestMapping(value = "agreement")
    @ResponseBody
    public String agreement(SignAgreement signAgreement){
        //协议号
        if (null == signAgreement.getAgreementNumber()) {
            List<SignAgreement> numx = signAgreementService.selectAgreementNumber(signAgreement);
            if (numx.size() == 0) {
                String code = BaseUtils.getCode("time", "3", "PROPOSAL", "proposal_code");
                String c1 = code.substring(0, 4);
                signAgreement.setAgreementNumber("晋医人调字[" + c1 + "]001号");
            } else {
                int max = 0;
                for (int i = 0; i < numx.size(); i++) {
                    String agreementNumber = numx.get(i).getAgreementNumber();
                    String d = agreementNumber.substring(11, 14);
                    int d1 = Integer.valueOf(d);
                    if (d1 > max) {
                        max = d1;
                    }
                }
                int d2 = max + 1;
                String a = BaseUtils.getCode("time", "3", "SIGN_AGREEMENT", "agreement_number");
                String c = a.substring(0, 4);
                String format = String.format("%0" + 3 + "d", d2);
                String e1 = "晋医人调字[" + c + "]" + format + "号";
                signAgreement.setAgreementNumber(e1);
            }
        }
        return signAgreement.getAgreementNumber();
    }
    @RequiresPermissions("sign:signAgreement:edit")
    @RequestMapping(value = "save")
    public String save(HttpServletRequest request, SignAgreement signAgreement, Model model, RedirectAttributes redirectAttributes, HttpServletResponse response) {
        String export = request.getParameter("export");
        if (StringUtils.isNotBlank(export) && !export.equals("no")) {
//		    SignAgreement signAgreement1 = signAgreementService.get(signAgreement.getSignAgreementId());
            String path = signAgreementService.exportWord(signAgreement, export, "false", request, response);
            return path;
        } else {
            if (!beanValidator(model, signAgreement) && "yes".equals(signAgreement.getComplaintMain().getAct().getFlag())) {
                return form(signAgreement, model, request);
            }
            try {
                signAgreementService.save(request, signAgreement);
                machineAccountService.savetz(signAgreement.getMachineAccount(), "si", signAgreement);
                if ("yes".equals(signAgreement.getComplaintMain().getAct().getFlag())) {
                    addMessage(redirectAttributes, "流程已启动，流程ID：" + signAgreement.getComplaintMain().getProcInsId());
                    return "redirect:" + Global.getAdminPath() + "/sign/signAgreement/?repage";
                } else {
                    model.addAttribute("message", "保存签署协议成功");
                    return form(signAgreementService.get(signAgreement.getSignAgreementId()), model, request);
                }
            } catch (Exception e) {
                logger.error("启动纠纷调解流程失败：", e);
                addMessage(redirectAttributes, "系统内部错误！");
                return "redirect:" + Global.getAdminPath() + "/sign/signAgreement/?repage";
            }

        }
    }

    @RequiresPermissions("sign:signAgreement:edit")
    @RequestMapping(value = "delete")
    public String delete(SignAgreement signAgreement, RedirectAttributes redirectAttributes) {
        signAgreementService.delete(signAgreement);
        addMessage(redirectAttributes, "删除签署协议成功");
        return "redirect:" + Global.getAdminPath() + "/sign/signAgreement/?repage";
    }

    @RequestMapping(value = "pass")
    public void pass(HttpServletRequest request, HttpServletResponse response) {
        String code = "";//1.成功 0失败
        String signAgreementId = request.getParameter("signAgreementId");//前台传过来的状态
        String data = request.getParameter("data");
        String agreedMatter = request.getParameter("agreedMatter");
        String mediation = request.getParameter("mediation");
        String performAgreementMode = request.getParameter("performAgreementMode");
        String agreementExplain = request.getParameter("agreementExplain");
        Map pa = JSONObject.parseObject(data, Map.class);


        String idNumber = (String) pa.get("patientLinkEmpList[0].idNumber");
        String patientLinkName = (String) pa.get("patientLinkEmpList[0].patientLinkName");
        String patientRelation = (String) pa.get("patientLinkEmpList[0].patientRelation");
        String patientLinkAddress = (String) pa.get("patientLinkEmpList[0].patientLinkAddress");
        String patientLinkName1 = (String) pa.get("patientLinkDList[0].patientLinkName");
        String patientRelation1 = (String) pa.get("patientLinkDList[0].patientRelation");
        String idNumber1 = (String) pa.get("patientLinkDList[0].idNumber");
        String patientLinkAddress1 = (String) pa.get("patientLinkDList[0].patientLinkAddress");
        String medicalOfficeName = (String) pa.get("medicalOfficeEmpList[0].medicalOfficeName");
        String medicalOfficeAddress = (String) pa.get("medicalOfficeEmpList[0].medicalOfficeAddress");
        String legalRepresentative = (String) pa.get("medicalOfficeEmpList[0].legalRepresentative");
        String medicalOfficePost = (String) pa.get("medicalOfficeEmpList[0].medicalOfficePost");
        String medicalOfficeAgent = (String) pa.get("medicalOfficeEmpList[0].medicalOfficeAgent");
        String medicalOfficeSex = (String) pa.get("medicalOfficeEmpList[0].medicalOfficeSex");
        String medicalOfficeIdcard = (String) pa.get("medicalOfficeEmpList[0].medicalOfficeIdcard");
        String medicalOfficeCompany = (String) pa.get("medicalOfficeEmpList[0].medicalOfficeCompany");
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String s1=(String)pa.get("createDate");
//		String aa="";
//		try {
//			Date createDate = new Date((String)pa.get("createDate"));
//			 aa=simpleDateFormat.format(createDate);
//			 pa.put("createDate",aa);
//
//		}catch (Exception e){
//
//		}
//

//		Date createDate = (Date) pa.get("createDate");
        PatientLinkEmp patientLinkEmp1 = new PatientLinkEmp();
        patientLinkEmp1.setPatientLinkName(patientLinkName);
        patientLinkEmp1.setIdNumber(idNumber);
        patientLinkEmp1.setPatientRelation(patientRelation);
        patientLinkEmp1.setPatientLinkAddress(patientLinkAddress);
        PatientLinkEmp patientLinkEmp2 = new PatientLinkEmp();
        patientLinkEmp2.setPatientLinkName(patientLinkName1);
        patientLinkEmp2.setPatientLinkAddress(patientLinkAddress1);
        patientLinkEmp2.setPatientRelation(patientRelation1);
        patientLinkEmp2.setIdNumber(idNumber1);
        MedicalOfficeEmp medicalOfficeEmp = new MedicalOfficeEmp();
        medicalOfficeEmp.setMedicalOfficeName(medicalOfficeName);
        medicalOfficeEmp.setLegalRepresentative(legalRepresentative);
        medicalOfficeEmp.setMedicalOfficePost(medicalOfficePost);
        medicalOfficeEmp.setMedicalOfficeAgent(medicalOfficeAgent);
        medicalOfficeEmp.setMedicalOfficeAddress(medicalOfficeAddress);
        medicalOfficeEmp.setMedicalOfficeSex(medicalOfficeSex);
        medicalOfficeEmp.setMedicalOfficeIdcard(medicalOfficeIdcard);
        medicalOfficeEmp.setMedicalOfficeCompany(medicalOfficeCompany);
        SignAgreement signAgreement1 = JSON.parseObject(data, SignAgreement.class);
        signAgreement1.getMedicalOfficeEmpList().add(medicalOfficeEmp);
        signAgreement1.getPatientLinkEmpList().add(patientLinkEmp1);
        signAgreement1.getPatientLinkDList().add(patientLinkEmp2);
        signAgreement1.setAgreedMatter(agreedMatter);
        signAgreement1.setMediation(mediation);
        signAgreement1.setPerformAgreementMode(performAgreementMode);
        signAgreement1.setAgreementExplain(agreementExplain);
        String export = request.getParameter("export");//前台传过来的状态
        String print = request.getParameter("print");//前台传过来的状态
//		SignAgreement signAgreement = signAgreementService.get(signAgreementId);
        code = signAgreementService.exportWord(signAgreement1, export, print, request, response);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url", code);
        AjaxHelper.responseWrite(request, response, "1", "success", map);
    }


}