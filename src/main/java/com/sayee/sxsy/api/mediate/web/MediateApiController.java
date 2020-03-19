package com.sayee.sxsy.api.mediate.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.complain.entity.WechatUserComplaint;
import com.sayee.sxsy.api.complain.service.WechatUserComplaintService;
import com.sayee.sxsy.api.mediate.entity.*;
import com.sayee.sxsy.api.mediate.service.*;
import com.sayee.sxsy.api.officeapi.entity.OrganizationApiEntity;
import com.sayee.sxsy.api.officeapi.service.OfficeApiService;
import com.sayee.sxsy.api.officeapi.service.OrganizationApiService;
import com.sayee.sxsy.api.user.entity.UserInfo;
import com.sayee.sxsy.api.user.service.UserApiService;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.assessappraisal.service.AssessAppraisalService;
import com.sayee.sxsy.modules.auditacceptance.service.AuditAcceptanceService;
import com.sayee.sxsy.modules.complaintdetail.service.ComplaintMainDetailService;
import com.sayee.sxsy.modules.machine.service.MachineAccountService;
import com.sayee.sxsy.modules.mediate.service.MediateEvidenceService;
import com.sayee.sxsy.modules.nestigateeividence.service.InvestigateEvidenceService;
import com.sayee.sxsy.modules.perform.service.PerformAgreementService;
import com.sayee.sxsy.modules.reachmediate.service.ReachMediateService;
import com.sayee.sxsy.modules.sign.service.SignAgreementService;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.attribute.standard.Media;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description 调解
 */
@Controller
@RequestMapping("${adminPath}/api")
public class MediateApiController {
    @Autowired
    private MediateApiService mediateApiService;
    @Autowired
    private MediateInfoApiService mediateInfoApiService;
    @Autowired
    private MediateDetailService mediateDetailService;
    @Autowired
    private OrganizationApiService organizationApiService;
    @Autowired
    private OfficeApiService officeApiService;
//    @Autowired
//    private AuditAcceptanceService auditAcceptanceService;
//    @Autowired
//    private ComplaintMainDetailService complaintMainDetailService;//投诉接待
//    @Autowired
//    private InvestigateEvidenceService investigateEvidenceService;//调查取证
//    @Autowired
//    private MediateEvidenceService mediateEvidenceService;//质证调解
//    @Autowired
//    private AssessAppraisalService assessAppraisalService;//评估鉴定
//    @Autowired
//    private ReachMediateService reachMediateService;//达成调解
//    @Autowired
//    private SignAgreementService signAgreementService;//签署协议
//    @Autowired
//    private PerformAgreementService performAgreementService;//履行协议
    @Autowired
    private ActTaskService actTaskService;//流程
    @Autowired
    private UserApiService userApiService;
    @Autowired
    private WechatUserComplaintService wechatUserComplaintService;

    /*投诉接待*/
    @RequestMapping("mediateadd")
    @ResponseBody
    public R mediateAdd(@RequestBody JSONObject jsonObject) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        MediateApiEntity mediateApiEntity = JSON.toJavaObject(jsonObject, MediateApiEntity.class);
        mediateApiEntity.preInsert();
        String caseNumber = getCaseMumber();
        String complaintMainId = mediateApiEntity.getId();
        String involveHospital = jsonObject.getString("involveHospital");//接收参数：涉及医院id
        OrganizationApiEntity organizationApiEntity = organizationApiService.getOrganizationById(involveHospital);
        String hospitalLevel = organizationApiEntity.getGrade();
        String hospitalGrade = organizationApiEntity.getHospitalGrade();
        String visitorDate = sdf.format(new Date());
        String complaintMode = "0";
        String isMajor = "0";//接收参数:案件分类
        String receptionDate = sdf.format(new Date());//添加日期
        String treatmentProcess=jsonObject.getString("treatmentProcess");//治疗经过
        String fault=jsonObject.getString("fault");//存在过错
        String sumaryOfDisputes = treatmentProcess+fault;//概要

        mediateApiEntity.setComplaintMainId(mediateApiEntity.getId());
        mediateApiEntity.setCaseNumber(caseNumber);
        mediateApiEntity.setHospitalLevel(hospitalLevel);
        mediateApiEntity.setHospitalGrade(hospitalGrade);
        mediateApiEntity.setSource("3");//默认案件来源
        mediateApiEntity.setSummaryOfDisputes(sumaryOfDisputes);

        mediateApiEntity.setCreateDate(new Date());
        mediateApiEntity.setUpdateDate(new Date());
        String wechatUserId = jsonObject.getString("wechatUserId");
        UserInfo userInfo=userApiService.getUserInfoByUserId(wechatUserId);
        String createUser ="5387a4ce17b14df2b7756c8714af2ed6";
        if(userInfo.getUserType()==2) {
            //String createUser = userApiService.getSysUserId(wechatUserId);
             createUser =userInfo.getSysUserId();
        }else{
             createUser ="5387a4ce17b14df2b7756c8714af2ed6";
        }
        MediateDetail mediateDetail = JSON.toJavaObject(jsonObject, MediateDetail.class);
        mediateDetail.preInsert();
        mediateDetail.setComplaintMainDetailId(mediateDetail.getId());
        mediateDetail.setSummaryOfDisputes(sumaryOfDisputes);
        mediateDetail.setNextLinkMan(createUser);
        mediateDetail.setComplaintMainId(complaintMainId);
        mediateDetail.setComplaintMode(complaintMode);
        mediateDetail.setVisitorDate(visitorDate);
        mediateDetail.setIsMajor(isMajor);
        mediateDetail.setReceptionDate(receptionDate);
        mediateDetail.setCaseNumber(caseNumber);
        String nextLinkMan = mediateDetail.getNextLinkMan();
        mediateDetail.setCreateUser(createUser);
        WechatUserComplaint wechatUserComplaint = new WechatUserComplaint();
        wechatUserComplaint.setWechatUserId(wechatUserId);
        wechatUserComplaint.setComplaintMainId(complaintMainId);
        wechatUserComplaintService.save(wechatUserComplaint);
        if (mediateApiService.saveMediate(mediateApiEntity) > 0) {
            if (mediateDetailService.save(mediateDetail) > 0) {
                Map<String, Object> var = new HashMap<String, Object>();
                var.put("pass", "1");
                User assigness = UserUtils.get(nextLinkMan);
                //logger.debug("用户名======="+assigness);
                var.put("enrollment_user", null == assigness ? "weChat" : assigness.getLoginName());
                var.put("id", "complaint_main_id");
                // 启动流程
                String actid = actTaskService.startProcess("complaint", "COMPLAINT_MAIN", complaintMainId, caseNumber, var);
                Map map = new HashMap();
                map.put("complaintMainId", complaintMainId);
                map.put("procInsId", actid);
                mediateApiService.setProInstId(map);
                //启动流程的时候 创建一个 隐藏的角色
                R r = new R();
                r.put("RtnCode", 0);
                r.put("RtnMsg", "success");
                r.put("RtnData", "data");
                return r;
            } else {
                R r = new R();
                r.put("RtnCode", 1);
                r.put("RtnMsg", "调解添加失败");
                r.put("RtnData", "");
                return r;
            }
        } else {
            R r = new R();
            r.put("RtnCode", 2);
            r.put("RtnMsg", "调解详情添加失败");
            r.put("RtnData", "");
            return r;
        }
    }

    @Autowired
    private MediateReportApiService mediateReportApiService;
//    @Autowired
//    private MachineAccountService machineAccountService;

    /*报案登记*/
    @RequestMapping("doreportregistration")
    @ResponseBody
    public R doReportRegistration(@RequestBody JSONObject jsonObject) {
        MediateReport mediateReport = JSON.toJavaObject(jsonObject, MediateReport.class);
        String complaintMainId = jsonObject.getString("complaintMainId");
        String nextLinkMan = jsonObject.getString("nextLinkMan");
        String caseNumber = jsonObject.getString("caseNumber");
        mediateReport.preInsert();
        mediateReport.setReportRegistrationId(mediateReport.getId());
        mediateReportApiService.saveMediateReport(mediateReport);
        process("0", nextLinkMan, complaintMainId, "", caseNumber, "check_user");
        //ReportRegistration reportRegistration=JSON.toJavaObject(jsonObject,ReportRegistration.class);
        //machineAccountService.savetz(null, "a", reportRegistration);
        R r = new R();
        r.put("RtnCode", 0);
        r.put("RtnMsg", "success");
        r.put("RtnData", "data");
        return r;
    }

    /*审核受理*/
    @Autowired
    private AcceptApiService acceptApiService;

    @RequestMapping("doaccept")
    @ResponseBody
    public R doAccept(@RequestBody JSONObject jsonObject) {
        Accept accept = JSON.toJavaObject(jsonObject, Accept.class);
        accept.preInsert();
        accept.setAuditAcceptanceId(accept.getId());
        String nextLinkMan = accept.getNextLinkMan();
        String complaintMainId = accept.getComplaintMainId();
        String caseNumber = jsonObject.getString("caseNumber");
        acceptApiService.save(accept);
        TaskEntity taskEntity = getTaskId(complaintMainId);
        String taskId = taskEntity.getTaskId();
        String procInsId = taskEntity.getProcInsId();
        Map<String, Object> var = new HashMap<String, Object>();
        var.put("pass", "0");
        User assigness = UserUtils.get(nextLinkMan);
        var.put("forensics_user", assigness.getLoginName());
        // 执行流程
        actTaskService.complete(taskId, procInsId, "", caseNumber, var);
        R r = new R();
        r.put("RtnCode", 0);
        r.put("RtnMsg", "success");
        r.put("RtnData", "data");
        return r;
    }

    @Autowired
    private InvestitageApiService investitageApiService;

    /*调查取证*/
    @RequestMapping("doinvestigate")
    @ResponseBody
    public R doInvestigate(@RequestBody JSONObject jsonObject) {
        //investigateEvidenceService.save(investigateEvidence);
        String complaintMainId = jsonObject.getString("complaintMainId");
        String caseNumber = jsonObject.getString("caseNumber");//接收参数:案件编号
        Investitage investitage = JSON.toJavaObject(jsonObject, Investitage.class);
        investitage.preInsert();
        investitage.setInvestigateEvidenceId(investitage.getId());
        investitage.setCreateDate(new Date());
        investitage.setUpdateDate(new Date());
        String nextLinkMan = investitage.getNextLinkMan();
        try {
            investitageApiService.save(investitage);
            Map<String, Object> var = new HashMap<String, Object>();
            TaskEntity taskEntity = getTaskId(complaintMainId);
            String taskId = taskEntity.getTaskId();
            String procInsId = taskEntity.getProcInsId();
            var.put("pass", "0");
            User assigness = UserUtils.get(nextLinkMan);
            var.put("mediation_user", assigness.getLoginName());
            //执行流程
            actTaskService.complete(taskId, procInsId, "", caseNumber, var);
            R r = new R();
            r.put("RtnCode", 0);
            r.put("RtnMsg", "添加成功");
            r.put("RtnData", "");
            return r;
        } catch (Exception e) {
            R r = new R();
            r.put("RtnCode", 1);
            r.put("RtnMsg", "添加失败");
            r.put("RtnData", e.getMessage());
            return r;
        }
    }

    @Autowired
    private EvidenceApiService evidenceApiService;

    /*质证调解*/
    @RequestMapping("domediateevidence")
    @ResponseBody
    public R doMediateEvidence(@RequestBody JSONObject jsonObject) {
        //mediateEvidenceService.save(mediateEvidence);
        String complaintMainId = jsonObject.getString("complaintMainId");
        String caseNumber = jsonObject.getString("caseNumber");
        Evidence evidence = JSON.toJavaObject(jsonObject, Evidence.class);
        String nextLinkMan = evidence.getNextLinkMan();
        try {
            evidenceApiService.save(evidence);
            Map<String, Object> var = new HashMap<String, Object>();

            //var.put("pass",StringUtils.isBlank(mediateEvidence.getNextLink()) ? "0" : mediateEvidence.getNextLink());
            var.put("pass", 0);
            TaskEntity taskEntity = getTaskId(complaintMainId);
            String taskId = taskEntity.getTaskId();
            String procInsId = taskEntity.getProcInsId();
            User assigness = UserUtils.get(nextLinkMan);
            //var.put("0".equals(MapUtils.getString(var,"pass","0")) ? "evaluation_user" : "sign_user",assigness.getLoginName());
            var.put("evaluation_user", assigness.getLoginName());
            // 执行流程
            actTaskService.complete(taskId, procInsId, "", caseNumber, var);
            R r = new R();
            r.put("RtnCode", 0);
            r.put("RtnMsg", "添加成功");
            r.put("RtnData", "");
            return r;
        } catch (Exception e) {
            R r = new R();
            r.put("RtnCode", 1);
            r.put("RtnMsg", "添加失败");
            r.put("RtnData", e.getMessage());
            return r;
        }
    }

    @Autowired
    private AppraisalApiService appraisalApiService;

    /*评估鉴定*/
    @RequestMapping("doassessappraisal")
    @ResponseBody
    public R doAssessappraisal(@RequestBody JSONObject jsonObject) {
        //assessAppraisalService.save(assessAppraisal);
        String complaintMainId = jsonObject.getString("complaintMainId");
        MediateCommon mediateCommon = mediateApiService.findMediateById(complaintMainId);
        String caseNumber = mediateCommon.getCaseNumber();
        String patientAge = mediateCommon.getPatientAge();
        String patientName = mediateCommon.getPatientName();
        String patientSex = mediateCommon.getPatientSex();
        Appraisal appraisal = JSON.toJavaObject(jsonObject, Appraisal.class);
        appraisal.preInsert();
        appraisal.setAssessAppraisalId(appraisal.getId());
        appraisal.setPatientAge(patientAge);
        appraisal.setPatientName(patientName);
        appraisal.setPatientSex(patientSex);
        appraisal.setCreateDate(new Date());
        appraisal.setUpdateDate(new Date());
        String nextLinkMan = appraisal.getNextLinkMan();
        try {
            appraisalApiService.save(appraisal);
            Map<String, Object> var = new HashMap<String, Object>();
            var.put("pass", "0");
            User assigness = UserUtils.get(nextLinkMan);
            var.put("reach_user", assigness.getLoginName());
            TaskEntity taskEntity = getTaskId(complaintMainId);
            String taskId = taskEntity.getTaskId();
            String procInsId = taskEntity.getProcInsId();
            // 执行流程
            actTaskService.complete(taskId, procInsId, "", caseNumber, var);
            R r = new R();
            r.put("RtnCode", 0);
            r.put("RtnMsg", "添加成功");
            r.put("RtnData", "");
            return r;
        } catch (Exception e) {
            R r = new R();
            r.put("RtnCode", 1);
            r.put("RtnMsg", "添加失败");
            r.put("RtnData", e.getMessage());
            return r;
        }
    }

    /*达成调解*/
    @Autowired
    private ReachApiService reachApiService;

    @RequestMapping("doreachmediate")
    @ResponseBody
    public R doReachMediate(@RequestBody JSONObject jsonObject) {
        //reachMediateService.save(reachMediate);
        Reach reach = JSON.toJavaObject(jsonObject, Reach.class);
        reach.preInsert();
        reach.setReachMediateId(reach.getId());
        String nextLinkMan = reach.getNextLinkMan();
        String complaintMainId = reach.getComplaintMainId();
        String caseNumber = jsonObject.getString("caseNumber");
        try {
            reachApiService.save(reach);
            Map<String, Object> var = new HashMap<String, Object>();
            var.put("pass", "0");
            User assigness = UserUtils.get(nextLinkMan);
            var.put("sign_user", assigness.getLoginName());
            //执行流程
            TaskEntity taskEntity = getTaskId(complaintMainId);
            String taskId = taskEntity.getTaskId();
            String procInsId = taskEntity.getProcInsId();
            actTaskService.complete(taskId, procInsId, "", caseNumber, var);
            R r = new R();
            r.put("RtnCode", 0);
            r.put("RtnMsg", "添加成功");
            r.put("RtnData", "");
            return r;
        } catch (Exception e) {
            e.getMessage();
            R r = new R();
            r.put("RtnCode", 0);
            r.put("RtnMsg", "添加失败");
            r.put("RtnData", e.getMessage());
            return r;
        }
    }

    /*签署协议*/
    @Autowired
    private SignApiService signApiService;
    @RequestMapping("dosignagreement")
    @ResponseBody
    public R doSignAgreement(@RequestBody JSONObject jsonObject) {
        //signAgreementService.save(signAgreement);
        Sign sign = JSON.toJavaObject(jsonObject, Sign.class);
        sign.preInsert();
        sign.setSignAgreementId(sign.getId());
        sign.setAgreementNumber(getAgreementNumber());
        String nextLinkMan = sign.getNextLinkMan();
        String complaintMainId = sign.getComplaintMainId();
        String caseNumber = jsonObject.getString("caseNumber");
        try {
            signApiService.save(sign);
            Map<String, Object> var = new HashMap<String, Object>();
            var.put("pass", "0");
            User assigness = UserUtils.get(nextLinkMan);
            var.put("fulfill_user", assigness.getLoginName());
            // 执行流程
            TaskEntity taskEntity = getTaskId(complaintMainId);
            String taskId = taskEntity.getTaskId();
            String procInsId = taskEntity.getProcInsId();
            actTaskService.complete(taskId, procInsId, "", caseNumber, var);
            R r = new R();
            r.put("RtnCode", 0);
            r.put("RtnMsg", "添加成功");
            r.put("RtnData", "");
            return r;
        } catch (Exception e) {
            e.getMessage();
            R r = new R();
            r.put("RtnCode", 0);
            r.put("RtnMsg", "添加失败");
            r.put("RtnData", e.getMessage());
            return r;
        }
    }

    /*履行协议*/
    @Autowired
    private PerformApiService performApiService;

    @RequestMapping("doperformagreement")
    @ResponseBody
    public R doPerformAgreement(@RequestBody JSONObject jsonObject) {
        //performAgreementService.save(performAgreement);
        Perform perform = JSON.toJavaObject(jsonObject, Perform.class);
        perform.preInsert();
        perform.setPerformAgreementId(perform.getId());
        String nextLinkMan = perform.getNextLinkMan();
        String complaintMainId = perform.getComplaintMainId();
        String caseNumber = jsonObject.getString("caseNumber");
        try {
            performApiService.save(perform);
            Map<String, Object> var = new HashMap<String, Object>();
            var.put("pass", "0");
            User assigness = UserUtils.get(nextLinkMan);
            TaskEntity taskEntity = getTaskId(complaintMainId);
            String taskId = taskEntity.getTaskId();
            String procInsId = taskEntity.getProcInsId();
            var.put("summary_user", assigness.getLoginName());
            // 执行流程
            actTaskService.complete(taskId, procInsId, "", caseNumber, var);
            R r = new R();
            r.put("RtnCode", 0);
            r.put("RtnMsg", "添加成功");
            r.put("RtnData", "");
            return r;
        } catch (Exception e) {
            e.getMessage();
            R r = new R();
            r.put("RtnCode", 0);
            r.put("RtnMsg", "添加失败");
            r.put("RtnData", e.getMessage());
            return r;
        }
    }

    /*案件总结*/
    @Autowired
    private SummaryApiService summaryApiService;

    @RequestMapping("dosummary")
    @ResponseBody
    public R doSummary(@RequestBody JSONObject jsonObject) {
        //performAgreementService.save(performAgreement);
        Summary summary = JSON.toJavaObject(jsonObject, Summary.class);
        summary.preInsert();
        summary.setSummaryId(summary.getId());
        String nextLinkMan = summary.getNextLinkMan();
        String complaintMainId = summary.getComplaintMainId();
        String caseNumber = jsonObject.getString("caseNumber");
        try {
            summaryApiService.save(summary);
            Map<String, Object> var = new HashMap<String, Object>();
            var.put("pass", "0");
            User assigness = UserUtils.get(nextLinkMan);
            var.put("assess_user", assigness.getLoginName());
            // 执行流程
            TaskEntity taskEntity = getTaskId(complaintMainId);
            String taskId = taskEntity.getTaskId();
            String procInsId = taskEntity.getProcInsId();
            actTaskService.complete(taskId, procInsId, "", caseNumber, var);
            R r = new R();
            r.put("RtnCode", 0);
            r.put("RtnMsg", "添加成功");
            r.put("RtnData", "");
            return r;
        } catch (Exception e) {
            e.getMessage();
            R r = new R();
            r.put("RtnCode", 0);
            r.put("RtnMsg", "添加失败");
            r.put("RtnData", e.getMessage());
            return r;
        }
    }

    /*案件评价*/
    @Autowired
    private AssessApiService assessApiService;

    @RequestMapping("doassess")
    @ResponseBody
    public R doAssess(@RequestBody JSONObject jsonObject) {
        //performAgreementService.save(performAgreement);
        Assess assess = JSON.toJavaObject(jsonObject, Assess.class);
        assess.preInsert();
        assess.setAssessId(assess.getId());
        String nextLinkMan = assess.getNextLinkMan();
        String complaintMainId = assess.getComplaintMainId();
        String caseNumber = jsonObject.getString("caseNumber");
        try {
            assessApiService.save(assess);
            Map<String, Object> var = new HashMap<String, Object>();
            var.put("pass", "0");
            TaskEntity taskEntity = getTaskId(complaintMainId);
            String taskId = taskEntity.getTaskId();
            String procInsId = taskEntity.getProcInsId();
            User assigness = UserUtils.get(nextLinkMan);
            var.put("feedback_user", assigness.getLoginName());
            // 执行流程
            actTaskService.complete(taskId, procInsId, "", caseNumber, var);
            R r = new R();
            r.put("RtnCode", 0);
            r.put("RtnMsg", "添加成功");
            r.put("RtnData", "");
            return r;
        } catch (Exception e) {
            e.getMessage();
            R r = new R();
            r.put("RtnCode", 0);
            r.put("RtnMsg", "添加失败");
            r.put("RtnData", e.getMessage());
            return r;
        }
    }

    /*流程操作*/
    private void process(String pass, String nextLinkMan, String complaintMainId, String title, String caseNumber, String varName) {
        Map<String, Object> var = new HashMap<String, Object>();
        var.put("pass", pass);
        TaskEntity taskEntity = getTaskId(complaintMainId);
        String taskId = taskEntity.getTaskId();
        String procInsId = taskEntity.getProcInsId();
        User assigness = UserUtils.get(nextLinkMan);
        //var.put("enrollment_user", null==assigness ? "weChat" : assigness.getLoginName());
        var.put("check_user", null == assigness ? "weChat" : assigness.getLoginName());
        // 执行流程
        actTaskService.complete(taskId, procInsId, title, caseNumber, var);
    }

    /*医调委获取调解列表*/
    @RequestMapping("mediatelist")
    @ResponseBody
    public R mediateList(@RequestBody JSONObject jsonObject) {
        String wechatUserId = jsonObject.getString("wechatUserId");
        List<Mediate> dataList = mediateApiService.mediateList(wechatUserId);
        for (Mediate mediate : dataList) {
            String tableName = "";
            switch (mediate.getActName()) {
                case "审核受理":
                    tableName="REPORT_REGISTRATION";
                    //tableName ="AUDIT_ACCEPTANCE";
                    break;
                case "调查取证":
                    tableName ="AUDIT_ACCEPTANCE";
                    break;
                case "质证调解":
                    tableName ="INVESTIGATE_EVIDENCE";
                    break;
                case "评估鉴定":
                    tableName ="MEDIATE_EVIDENCE";
                    break;
                case "达成调解":
                    tableName ="ASSESS_APPRAISAL";
                    break;
                case "签署协议":
                    tableName ="REACH_MEDIATE";
                    break;
                case "履行协议":
                    tableName ="SIGN_AGREEMENT";
                    break;
                case "案件总结":
                    tableName ="PERFORM_AGREEMENT";
                    break;
                case "案件评价":
                    tableName ="SUMMARY_INFO";
                    //tableName ="ASSESS_INFO";
                    break;
                default:
                    tableName ="COMPLAINT_MAIN_DETAIL";
                    break;
            }
            Map map = mediateApiService.getMediateUser(tableName,"'"+mediate.getComplaintMainId()+"'");
            if(null!=map) {
                mediate.setName(null == map.get("name") ? "" : map.get("name").toString());
                mediate.setPhoto(null == map.get("photo") ? "" : map.get("photo").toString());
            }
        }
        R r = new R();
        r.put("RtnCode", 0);
        r.put("RtnMsg", "success");
        r.put("RtnData", dataList);
        return r;
    }

    /*医院获取调解列表*/
    @RequestMapping("mediatelistforhos")
    @ResponseBody
    public R mediateListForHos(@RequestBody JSONObject jsonObject) {
        String wechatUserId = jsonObject.getString("wechatUserId");
        List<Mediate> dataList = mediateApiService.mediateListForHos(wechatUserId);
        for (Mediate mediate : dataList) {
            String tableName = "";
            switch (mediate.getActName()) {
                case "审核受理":
                    tableName="REPORT_REGISTRATION";
                    //tableName ="AUDIT_ACCEPTANCE";
                    break;
                case "调查取证":
                    tableName ="AUDIT_ACCEPTANCE";
                    break;
                case "质证调解":
                    tableName ="INVESTIGATE_EVIDENCE";
                    break;
                case "评估鉴定":
                    tableName ="MEDIATE_EVIDENCE";
                    break;
                case "达成调解":
                    tableName ="ASSESS_APPRAISAL";
                    break;
                case "签署协议":
                    tableName ="REACH_MEDIATE";
                    break;
                case "履行协议":
                    tableName ="SIGN_AGREEMENT";
                    break;
                case "案件总结":
                    tableName ="PERFORM_AGREEMENT";
                    break;
                case "案件评价":
                    tableName ="SUMMARY_INFO";
                    //tableName ="ASSESS_INFO";
                    break;
                default:
                    tableName ="COMPLAINT_MAIN_DETAIL";
                    break;
            }
            Map map = mediateApiService.getMediateUser(tableName,"'"+mediate.getComplaintMainId()+"'");
            if(null!=map) {
                mediate.setName(null == map.get("name") ? "" : map.get("name").toString());
                mediate.setPhoto(null == map.get("photo") ? "" : map.get("photo").toString());
            }
        }
        R r = new R();
        r.put("RtnCode", 0);
        r.put("RtnMsg", "success");
        r.put("RtnData", dataList);
        return r;
    }
    /*医调委获取调解列表*/
    @RequestMapping("getmediatelist")
    @ResponseBody
    public R getMediateList(@RequestBody JSONObject jsonObject) {
        String wechatUserId = jsonObject.getString("wechatUserId");
        List<Mediate> dataList1=mediateApiService.mediateCommenFindList(wechatUserId);
        List<Mediate> dataList=new ArrayList<>();
        for(int i=0;i<dataList1.size();i++){
            String actName=dataList1.get(i).getActName();
           if(actName.equals("报案登记")||actName.equals("审核受理")||actName.equals("调查取证")||actName.equals("质证调解")){
               dataList.add(dataList1.get(i));
           }
        }
        R r = new R();
        r.put("RtnCode", 0);
        r.put("RtnMsg", "success");
        r.put("RtnData", dataList);
        return r;
    }
    /*评估列表*/
    @RequestMapping("getassesslist")
    @ResponseBody
    public R getAssessList(@RequestBody JSONObject jsonObject){
        String wechatUserId = jsonObject.getString("wechatUserId");
        List<Mediate> dataList1=mediateApiService.mediateCommenFindList(wechatUserId);
        List<Mediate> dataList=new ArrayList<>();
        for(int i=0;i<dataList1.size();i++){
            String actName=dataList1.get(i).getActName();
            if(actName.equals("评估鉴定")||actName.equals("达成调解")||actName.equals("签署协议")||actName.equals("履行协议")){
                dataList.add(dataList1.get(i));
            }
        }
        R r = new R();
        r.put("RtnCode", 0);
        r.put("RtnMsg", "success");
        r.put("RtnData", dataList);
        return r;
    }
    /*结案列表*/
    @RequestMapping("getagreementlist")
    @ResponseBody
    public R getAgreementList(@RequestBody JSONObject jsonObject){
        String wechatUserId = jsonObject.getString("wechatUserId");
        List<Mediate> dataList1=mediateApiService.mediateCommenFindList(wechatUserId);
        List<Mediate> dataList=new ArrayList<>();
        for(int i=0;i<dataList1.size();i++){
            String actName=dataList1.get(i).getActName();
            if(actName.equals("案件总结")||actName.equals("案件评价")||actName.equals("案件反馈")){
                dataList.add(dataList1.get(i));
            }
        }
        R r = new R();
        r.put("RtnCode", 0);
        r.put("RtnMsg", "success");
        r.put("RtnData", dataList);
        return r;
    }

    /*医院获取调解列表*/
    @RequestMapping("getmediatelistforhos")
    @ResponseBody
    public R getMediateListForHos(@RequestBody JSONObject jsonObject) {
        String wechatUserId = jsonObject.getString("wechatUserId");
        List<Mediate> dataList = mediateApiService.mediateCommenFindListForHos(wechatUserId);
        /*for (int i = 0; i < dataList1.size() - 1; i++) {
            for (int n = dataList.size() - 1; n > i; n--) {
                if (dataList.get(n).getComplaintMainId().equals(dataList.get(i).getComplaintMainId())) {
                    dataList1.remove(n);
                }
            }
        }*/
        R r = new R();
        r.put("RtnCode", 0);
        r.put("RtnMsg", "success");
        r.put("RtnData", dataList);
        return r;
    }
    /*调解详情*/
    @RequestMapping("getmediateinfo")
    @ResponseBody
    public R getMediateInfo(@RequestBody JSONObject jsonObject) {
        String complaintMainId = jsonObject.getString("complaintMainId");
        //String status=mediateApiService.getStatus(complaintMainId);
        MediateCommon mediateCommon = mediateApiService.getMediateInfo(complaintMainId);
        List<ActInst> actInstList = mediateApiService.getActInfo(complaintMainId);
        Map map1 = new HashMap();
        List list = new ArrayList();
        map1.put("id", complaintMainId);
        map1.put("applicant", mediateCommon.getVisitorName());
        map1.put("hospital", mediateCommon.getInvolveHospital());
        Map map2 = new HashMap();
        map2.put("key", "案件编号");
        map2.put("value", mediateCommon.getCaseNumber());

        Map map3 = new HashMap();
        map3.put("key", "申请人姓名");
        map3.put("value", mediateCommon.getVisitorName());

        Map map4 = new HashMap();
        map4.put("key", "患者姓名");
        map4.put("value", mediateCommon.getPatientName());

        Map map5 = new HashMap();
        map5.put("key", "与患者关系");
        switch (mediateCommon.getPatientRelation()) {
            case "1":
                map5.put("value", "本人");
                break;
            case "2":
                map5.put("value", "夫妻");
                break;
            case "3":
                map5.put("value", "子女");
                break;
            case "4":
                map5.put("value", "父母");
                break;
            case "5":
                map5.put("value", "兄妹");
                break;
            case "6":
                map5.put("value", "亲属");
                break;
            case "7":
                map5.put("value", "其他");
                break;
        }
        Map map6 = new HashMap();
        map6.put("key", "联系电话");
        map6.put("value", mediateCommon.getVisitorMobile());

        Map map7 = new HashMap();
        map7.put("key", "纠纷经过");
        map7.put("value", mediateCommon.getSummaryOfDisputes());

        Map map8 = new HashMap();
        map8.put("key", "诉求");
        map8.put("value", mediateCommon.getAppeal());
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);
        list.add(map7);
        list.add(map8);
        //map.put("patientRelation",mediateCommon.getPatientRelation());//1本人 2夫妻 3子女 4父母 5兄妹 6亲属 7其他
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + "年" + "MM" + "月" + "dd" + "日");
        for (ActInst actInst : actInstList) {
            Map map9 = new HashMap();
            map9.put("key", actInst.getActName() + "时间");
            map9.put("value", sdf.format(actInst.getStartTime()));
            list.add(map9);
        }
        map1.put("detail", list);
        // mediateApiService.findMediateById(complaintMainId);
        R r = new R();
        r.put("RtnCode", 0);
        r.put("RtnMsg", "success");
        r.put("RtnData", map1);
        return r;
    }

    private String getDict(String involveHospitalName) {
        return officeApiService.getDict(involveHospitalName);
    }

    /*获取流程进度*/
    private TaskEntity getTaskId(String complaintMainId) {
        return mediateReportApiService.getTaskId(complaintMainId);
    }

    /*案件编号*/
    private String getCaseMumber() {
        String caseNumber = mediateApiService.getCaseNumber();
        return caseNumber;
    }

    /*签署协议编号*/
    private String getAgreementNumber() {
        return signApiService.getMax();
    }

    @RequestMapping("getorganization")
    @ResponseBody
    private OrganizationApiEntity getOrganization() {
        return organizationApiService.getOrganizationById("1f4cf5433c6f44e993590cacca362b11");
    }
}