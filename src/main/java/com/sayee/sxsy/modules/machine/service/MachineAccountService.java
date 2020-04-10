/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.machine.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sayee.sxsy.common.utils.*;
import com.sayee.sxsy.modules.assessappraisal.entity.AssessAppraisal;
import com.sayee.sxsy.modules.assessappraisal.service.AssessAppraisalService;
import com.sayee.sxsy.modules.assessinfo.entity.AssessInfo;
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.auditacceptance.service.AuditAcceptanceService;
import com.sayee.sxsy.modules.mediate.entity.MediateEvidence;
import com.sayee.sxsy.modules.mediate.service.MediateEvidenceService;
import com.sayee.sxsy.modules.nestigateeividence.entity.InvestigateEvidence;
import com.sayee.sxsy.modules.nestigateeividence.service.InvestigateEvidenceService;
import com.sayee.sxsy.modules.perform.entity.PerformAgreement;
import com.sayee.sxsy.modules.perform.service.PerformAgreementService;
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import com.sayee.sxsy.modules.registration.service.ReportRegistrationService;
import com.sayee.sxsy.modules.sign.entity.SignAgreement;
import com.sayee.sxsy.modules.sign.service.SignAgreementService;
import com.sayee.sxsy.modules.summaryinfo.entity.SummaryInfo;
import com.sayee.sxsy.modules.summaryinfo.service.SummaryInfoService;
import com.sayee.sxsy.modules.sys.entity.*;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.machine.entity.MachineAccount;
import com.sayee.sxsy.modules.machine.dao.MachineAccountDao;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 台账信息展示Service
 *
 * @author zhangfan
 * @version 2019-05-17
 */
@Service
@Lazy(value = true)
@Transactional(readOnly = true)
public class MachineAccountService extends CrudService<MachineAccountDao, MachineAccount> {
//    @Autowired
//    private ReportRegistrationService reportRegistrationService;
//    @Autowired
//    private AuditAcceptanceService auditAcceptanceService;
//    @Autowired
//    private InvestigateEvidenceService investigateEvidenceService;
//    @Autowired
//    private MediateEvidenceService mediateEvidenceService;
//    @Autowired
//    private AssessAppraisalService assessAppraisalService;
//    @Autowired
//    private SignAgreementService signAgreementService;
//    @Autowired
//    private SummaryInfoService summaryInfoService;
    @Autowired
    private MachineAccountDao machineAccountDao;
//    @Autowired
//    private PerformAgreementService performAgreementService;
    public MachineAccount get(String id) {
        String machineAccountId=id;
        return machineAccountDao.getDetail(machineAccountId);
    }
    public MachineAccount getM(String id){
     MachineAccount machineAccount = super.get(id);
        return machineAccount;
    }
    public List<MachineAccount> findList(MachineAccount machineAccount) {
        return super.findList(machineAccount);
    }

    public Page<MachineAccount> findPage(Page<MachineAccount> page, MachineAccount machineAccount) {
        //卫计委
        Area area=new Area();
        area.setName(machineAccount.getArea()!=null && StringUtils.isNotBlank(machineAccount.getArea().getName()) ? machineAccount.getArea().getName() : "");
        //非 卫计委
        User user = UserUtils.getUser();
        Area ar=user.getCompany().getArea();
        machineAccount.setArea(ar);
        if (StringUtils.isBlank(machineAccount.getEndReportingTime()) && StringUtils.isBlank(machineAccount.getReportingTime())){
            machineAccount.setReportingTime(DateUtils.getYear());
            int year=Integer.valueOf(DateUtils.getYear())+1;
            machineAccount.setEndReportingTime(String.valueOf(year));
        }
        if ("3".equals(user.getCompany().getOfficeType())){
            //卫计委人员
            area.setId(StringUtils.isBlank(user.getPost()) ? "123456" : user.getPost());
            machineAccount.setArea(area);
        }
        page.setCount(machineAccountDao.findPageCount(machineAccount));

        return super.findPage(page, machineAccount);
    }

    @Transactional(readOnly = false)
    public void save(MachineAccount machineAccount) {
//        machineAccount.preInsert();
        machineAccount.setMachineAccountId(IdGen.uuid());
        //转换调解员
        User user = UserUtils.getId(machineAccount.getMediatorId());
        machineAccount.setMediatorId(user != null ? user.getId() : machineAccount.getMediatorId());
        //转换部门OFFICE
//		Office office=UserUtils.officeId(machineAccount.getDeptId());
//		machineAccount.setDeptId(office!=null ?  office.getId() :machineAccount.getDeptId());
        machineAccount.setDeptId(user != null ? user.getOffice().getId() : machineAccount.getDeptId());
        //专为医院 id
        Office hospital=UserUtils.officeId(machineAccount.getHospitalId());
        machineAccount.setHospitalId(hospital !=null ? hospital.getId() : machineAccount.getHospitalId());
        //投诉类型



        //是否重大
        if ("是".equals(machineAccount.getIsMajor()) || "1".equals(machineAccount.getIsMajor())) {
            machineAccount.setIsMajor("1");
        } else {
            machineAccount.setIsMajor("0");
        }
        //对金额 字段 为空的时候进行处理
        //协议金额=保险金额+医院金额
        if (StringUtils.isBlank(machineAccount.getAgreementAmount()) || machineAccount.getAgreementAmount() == null) {
            machineAccount.setAgreementAmount("0");
        }
        if (StringUtils.isBlank(machineAccount.getInsuranceAmount()) || machineAccount.getInsuranceAmount() == null) {
            machineAccount.setInsuranceAmount("0");
        }
        if (StringUtils.isBlank(machineAccount.getHospitalAmount()) || machineAccount.getHospitalAmount() == null) {
            machineAccount.setHospitalAmount(String.valueOf(Float.valueOf(machineAccount.getAgreementAmount())-Float.valueOf(machineAccount.getInsuranceAmount())));
        }

        //流转天数”设公式=交理赔时间-赔付时间，按天计算、时间格式统一  流转天数为空，且交理赔时间和赔付时间不为空
        if (StringUtils.isNotBlank(machineAccount.getClaimSettlementTime()) && StringUtils.isNotBlank(machineAccount.getCompensateTime())  && (StringUtils.isBlank(machineAccount.getFlowDays()) || machineAccount.getFlowDays() == null)){
            //将字符串专为日期格式
            if(DateUtils.parseDate(machineAccount.getCompensateTime())!=null && DateUtils.parseDate(machineAccount.getClaimSettlementTime())!=null){
                machineAccount.setFlowDays(String.valueOf(DateUtils.getDistanceOfTwoDate(DateUtils.parseDate(machineAccount.getCompensateTime()),DateUtils.parseDate(machineAccount.getClaimSettlementTime()))));
            }
        }
        //调解次数
        machineAccount.setMeetingFrequency(StringUtils.isBlank(machineAccount.getMeetingFrequency()) ? "" : machineAccount.getMeetingFrequency());
        //计算金额
        machineAccount.setCountAmount(StringUtils.isBlank(machineAccount.getCountAmount()) ? "0" : machineAccount.getCountAmount());
        /*//提交理赔天数
        machineAccount.setClaimSettlementDay("");
        //理赔流转天数（公式
        machineAccount.setSettlementFlowDays("");
        //卷宗评分
        machineAccount.setAssessGrade("");*/
        // machineAccount.setIsNewRecord(true);
        machineAccount.setDelFlag("0");
        super.save(machineAccount);
    }

    @Transactional(readOnly = false)
    public void delete(MachineAccount machineAccount) {
        super.delete(machineAccount);
    }

    /*
     *卷宗编号验重
     *
     * */
    @Transactional(readOnly = false)
    public boolean checkFileNumber(String fileNumber) {
        if (StringUtils.isNotBlank(fileNumber)) {
            MachineAccount machineAccount = dao.checkFileNumber(fileNumber);
            if (machineAccount != null) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Transactional(readOnly = false)
    public void savetz(MachineAccount machineAccount, String node, Object id) {
        //2019年10月11日 14:26:26  审核受理 生成台账 现在改为  报案登记 的时候 就生成
        if ("a".equals(node)) {
            ReportRegistration reportRegistration = (ReportRegistration)id;
            machineAccount = this.getM(reportRegistration.getComplaintMainId());
            MachineAccount machineAccount1=new MachineAccount();
                if (machineAccount==null) {
                    //案件编号
                    machineAccount1.setMachineAccountId(IdGen.uuid());
                    machineAccount1.setComplaintMainId(reportRegistration.getComplaintMainId());
                    machineAccount1.setPatientName(reportRegistration.getComplaintMain().getPatientName()==null?"":reportRegistration.getComplaintMain().getPatientName());
                    machineAccount1.setCaseNumber(reportRegistration.getComplaintMain().getCaseNumber()==null?"":reportRegistration.getComplaintMain().getCaseNumber());
                    //保单号
                    machineAccount1.setPolicyNumber(reportRegistration.getPolicyNumber()==null?"":reportRegistration.getPolicyNumber());
                    //报案时间
                    machineAccount1.setReportingTime(reportRegistration.getRegistrationTime()==null?"":reportRegistration.getRegistrationTime());
                    machineAccount1.setIsMajor(reportRegistration.getIsMajor()==null?"": reportRegistration.getIsMajor());
                    machineAccount1.setReportingTime(reportRegistration.getReportTime()==null?"":reportRegistration.getReportTime());
                    machineAccount1.setDisputesTime(reportRegistration.getDisputeTime()==null?"": reportRegistration.getDisputeTime());
                    machineAccount1.setSummaryOfDisputes(reportRegistration.getSummaryOfDisputes()==null?"": reportRegistration.getSummaryOfDisputes());
                    //machineAccount1.setTreatmentMode(reportRegistration.getDiagnosisMode()==null?"":auditAcceptance.getDiagnosisMode());
                    //machineAccount1.setTreatmentResult(reportRegistration.getTreatmentOutcome()==null?"":auditAcceptance.getTreatmentOutcome());
                    machineAccount1.setHospitalId(reportRegistration.getComplaintMain().getInvolveHospital()==null?"":reportRegistration.getComplaintMain().getInvolveHospital());
                    machineAccount1.setHospitalGrade(reportRegistration.getComplaintMain().getHospitalGrade()==null?"":reportRegistration.getComplaintMain().getHospitalGrade());
                    Office office=UserUtils.getOfficeId(reportRegistration.getComplaintMain().getInvolveHospital());
                    machineAccount1.setAreaId(office.getArea().getId()==null?"":office.getArea().getId());
                    machineAccount1.setRelatedMajor(reportRegistration.getComplaintMain().getInvolveDepartment()==null?"":reportRegistration.getComplaintMain().getInvolveDepartment());
                    machineAccount1.setRiskPeople(reportRegistration.getComplaintMain().getInvolveEmployee()==null?"":reportRegistration.getComplaintMain().getInvolveEmployee());
                    machineAccount1.setRiskTime(reportRegistration.getReportTime()==null ? "":reportRegistration.getReportTime());
                    machineAccount1.setDelFlag("0");
                    machineAccount1.preInsert();
                    dao.insert(machineAccount1);
                }
            else{
                machineAccount.preUpdate();
                machineAccount.setMachineAccountId(machineAccount.getMachineAccountId());
                machineAccount.setComplaintMainId(reportRegistration.getComplaintMainId());
                machineAccount.setPatientName(reportRegistration.getComplaintMain().getPatientName()==null?"":reportRegistration.getComplaintMain().getPatientName());
                machineAccount.setCaseNumber(reportRegistration.getComplaintMain().getCaseNumber()==null?"":reportRegistration.getComplaintMain().getCaseNumber());
                //保单号
                machineAccount.setPolicyNumber(reportRegistration.getPolicyNumber()==null?"":reportRegistration.getPolicyNumber());
                //报案时间
                machineAccount.setReportingTime(reportRegistration.getRegistrationTime()==null?"":reportRegistration.getRegistrationTime());
                machineAccount.setIsMajor(reportRegistration.getIsMajor()==null?"": reportRegistration.getIsMajor());
                machineAccount.setReportingTime(reportRegistration.getReportTime()==null?"":reportRegistration.getReportTime());
                machineAccount.setDisputesTime(reportRegistration.getDisputeTime()==null?"": reportRegistration.getDisputeTime());
                machineAccount.setSummaryOfDisputes(reportRegistration.getSummaryOfDisputes()==null?"": reportRegistration.getSummaryOfDisputes());
                machineAccount.setHospitalId(reportRegistration.getComplaintMain().getInvolveHospital()==null?"":reportRegistration.getComplaintMain().getInvolveHospital());
                machineAccount.setRelatedMajor(reportRegistration.getComplaintMain().getInvolveDepartment()==null?"":reportRegistration.getComplaintMain().getInvolveDepartment());
                Office office=UserUtils.getOfficeId(reportRegistration.getComplaintMain().getInvolveHospital());
                machineAccount.setAreaId(office.getArea().getId()==null?"":office.getArea().getId());
                machineAccount.setRelatedMajor(reportRegistration.getComplaintMain().getInvolveDepartment()==null?"":reportRegistration.getComplaintMain().getInvolveDepartment());
                machineAccount.setRiskPeople(reportRegistration.getComplaintMain().getInvolveEmployee()==null?"":reportRegistration.getComplaintMain().getInvolveEmployee());
                machineAccount.setRiskTime(reportRegistration.getReportTime()==null ? "":reportRegistration.getReportTime());
                dao.update(machineAccount);
            }
        } else if ("audit".equals(node)) {
            AuditAcceptance auditAcceptance = (AuditAcceptance)id;
            if (StringUtils.isNotBlank(auditAcceptance.getComplaintMainId())) {
                MachineAccount machineAccount1 = this.getM(auditAcceptance.getComplaintMainId());
                if(machineAccount1!=null){
                    machineAccount1.preUpdate();
                    //受理时间
                    machineAccount1.setAcceptanceTime(auditAcceptance.getGuaranteeTime());
                    //保险公司
                    machineAccount1.setInsuranceCompany(auditAcceptance.getInsuranceCompany()==null?"" : auditAcceptance.getInsuranceCompany());
                    //保单号  报案登记时  就有 这在重新保存下  有变更 则进行修改
                    machineAccount1.setPolicyNumber(auditAcceptance.getPolicyNumber()==null?"" : auditAcceptance.getPolicyNumber());
                    //起保 日期
                    machineAccount1.setStartInsuranceTime(auditAcceptance.getGuaranteeTime()==null?"" : auditAcceptance.getGuaranteeTime());
                    //诊疗方式
                    machineAccount1.setTreatmentMode(auditAcceptance.getDiagnosisMode()==null?"" : auditAcceptance.getDiagnosisMode());
                    //治疗结果
                    machineAccount1.setTreatmentResult(auditAcceptance.getTreatmentOutcome()==null?"" : auditAcceptance.getTreatmentOutcome());
                    //纠纷概要
                    machineAccount1.setSummaryOfDisputes(auditAcceptance.getSummaryOfDisputes()==null?"" : auditAcceptance.getSummaryOfDisputes());
                    //
                    machineAccount1.setMediatorId(UserUtils.getUser().getId());
                    //
                    machineAccount1.setDeptId(UserUtils.getUser().getOffice().getId());
                    dao.update(machineAccount1);
                }
            }
        }else if ("b".equals(node)) {
            InvestigateEvidence investigateEvidence = (InvestigateEvidence)id;
            if (StringUtils.isNotBlank(investigateEvidence.getComplaintMainId())) {
                MachineAccount machineAccount1 = this.getM(investigateEvidence.getComplaintMainId());
                if(machineAccount1!=null){
                    machineAccount1.preUpdate();
                    machineAccount1.setPatientsReflectFocus(investigateEvidence.getFocus()==null?"":investigateEvidence.getFocus());
                    dao.update(machineAccount1);
                }
            }
        } else if ("c".equals(node)) {
            MediateEvidence mediateEvidence = (MediateEvidence)id;
            if (StringUtils.isNotBlank(mediateEvidence.getComplaintMainId())) {
                MachineAccount machineAccount1 = this.getM(mediateEvidence.getComplaintMainId());
                if(machineAccount1!=null){
                    machineAccount1.preUpdate();
//                    machineAccount1.setMediatorId(mediateEvidence.getRecordInfo().getHost()==null?"":mediateEvidence.getRecordInfo().getHost());
//                    machineAccount1.setDeptId(UserUtils.get(mediateEvidence.getRecordInfo().getHost())==null?"":UserUtils.get(mediateEvidence.getRecordInfo().getHost()).getOffice().getId());
                    dao.update(machineAccount1);
                }
            }
        } else if ("d".equals(node)) {
            AssessAppraisal assessAppraisal = (AssessAppraisal)id;
            if (StringUtils.isNotBlank(assessAppraisal.getComplaintMainId())) {
                MachineAccount machineAccount1 = this.getM(assessAppraisal.getComplaintMainId());
                if(machineAccount1!=null){
                    machineAccount1.preUpdate();
                    machineAccount1.setDutyRatio(assessAppraisal.getResponsibilityRatio()==null?"":assessAppraisal.getResponsibilityRatio());
                    machineAccount1.setAssessTime(assessAppraisal.getRecordInfo1().getStartTime()==null?"":assessAppraisal.getRecordInfo1().getStartTime());
                    machineAccount1.setAssessNumber(assessAppraisal.getProposal().getProposalCode()==null?"":assessAppraisal.getProposal().getProposalCode());
                    machineAccount1.setEighteenItems(assessAppraisal.getEighteenItems()==null?"":assessAppraisal.getEighteenItems());//十八项
                    machineAccount1.setHost(assessAppraisal.getHost()==null?"":assessAppraisal.getHost());
                    machineAccount1.setClerk(assessAppraisal.getClerk()==null?"":assessAppraisal.getClerk());
                    machineAccount1.setMedicalExpert(assessAppraisal.getMedicalExpert()==null?"":assessAppraisal.getMedicalExpert());
                    machineAccount1.setLegalExpert(assessAppraisal.getLegalExpert()==null?"":assessAppraisal.getLegalExpert());
                    machineAccount1.setCountAmount(assessAppraisal.getCalculatedAmount()==null?"":assessAppraisal.getCalculatedAmount());
                    dao.update(machineAccount1);
                }
            }
        } else if ("si".equals(node)) {
            SignAgreement signAgreement = (SignAgreement)id;
            if (StringUtils.isNotBlank(signAgreement.getComplaintMainId())) {
                MachineAccount machineAccount1 = this.getM(signAgreement.getComplaintMainId());
                if(machineAccount1!=null){
                    machineAccount1.preUpdate();
                    machineAccount1.setAgreementNumber(signAgreement.getAgreementNumber()==null?"":signAgreement.getAgreementNumber());
                    machineAccount1.setRatifyAccord(signAgreement.getRatifyAccord()==null?"":signAgreement.getRatifyAccord());
                    dao.update(machineAccount1);
                }
            }
        }else if("per".equals(node)){
            PerformAgreement performAgreement = (PerformAgreement)id;
            if(StringUtils.isNotBlank(performAgreement.getComplaintMainId())){
                MachineAccount m = this.getM(performAgreement.getComplaintMainId());
                if(m!=null){
                    m.preUpdate();
                    m.setClaimSettlementTime(performAgreement.getClaimSettlementTime()==null?"":performAgreement.getClaimSettlementTime());
                    m.setCompensateTime(performAgreement.getInsurancePayTime()==null?"":performAgreement.getInsurancePayTime());
                    //int assessTime=m.getAssessTime()==null ? 0 : Integer.valueOf(m.getAssessTime()) ;
                    //m.setFlowDays(this.TianShu(m.getRatifyAccord(),m.getAcceptanceTime(),StringUtils.toInteger(m.getAssessTime())));//流转天数  公式=签署协议时间-受理时间 剔除评估天数
                    if (StringUtils.isNotBlank(performAgreement.getAgreementPayAmount()) || "0".equals(performAgreement.getAgreementPayAmount()) || "".equals(performAgreement.getAgreementPayAmount())) {
                        m.setAgreementAmount(performAgreement.getAgreementPayAmount());
                    } else {
                        m.setAgreementAmount("0");
                    }
                    if (StringUtils.isNotBlank(performAgreement.getInsurancePayAmount()) || "0".equals(performAgreement.getInsurancePayAmount()) || "".equals(performAgreement.getInsurancePayAmount())) {
                        m.setInsuranceAmount(performAgreement.getInsurancePayAmount());
                    } else {
                        m.setInsuranceAmount("0");
                    }
                    m.setHospitalAmount(performAgreement.getHospitalPayAmount()==null?"0":performAgreement.getHospitalPayAmount());
                    //保险赔付时间
                    m.setInsurancePayTime(performAgreement.getInsurancePayTime()==null ? "" :performAgreement.getInsurancePayTime());
                    //医院赔付时间
                    m.setHospitalPayTime(performAgreement.getHospitalPayTime()==null ? "" :performAgreement.getHospitalPayTime());
                    //理赔流转天数（公式=保险赔付时间-提交理赔时间）
                    m.setSettlementFlowDays(this.TianShu(m.getInsurancePayTime(),m.getClaimSettlementTime(),0));
                    //提交理赔天数(公式=提交理赔时间-协议生效时间）
                    m.setClaimSettlementDay(this.TianShu(m.getClaimSettlementTime(),performAgreement.getTakeEffectTime(),0));
                    dao.update(m);
                }
            }
        }else if ("f".equals(node)) {//案件总结
            SummaryInfo summaryInfo = (SummaryInfo)id;
            if (StringUtils.isNotBlank(summaryInfo.getComplaintMainId())) {
                MachineAccount machineAccount1 = this.getM(summaryInfo.getComplaintMainId());
                if(machineAccount1!=null){
                    machineAccount1.preUpdate();
                    //machineAccount1.setAcceptanceTime(summaryInfo.getAcceptanceTime());
                    machineAccount1.setRatifyAccord(summaryInfo.getRatifyAccord());
                    machineAccount1.setFlowDays(summaryInfo.getFlowDays());
                    machineAccount1.setMeetingFrequency(summaryInfo.getMeetingFrequency());
                    machineAccount1.setMediateResult(summaryInfo.getMediateResult());
                    machineAccount1.setArchiveTime(summaryInfo.getFilingTime()==null?"":summaryInfo.getFilingTime());
                    machineAccount1.setFileNumber(summaryInfo.getFileNumber()==null?"":summaryInfo.getFileNumber());
                    dao.update(machineAccount1);
                }
            }
        }else if("g".equals(node)){//案件评价
            AssessInfo assessInfo = (AssessInfo)id;
            if (StringUtils.isNotBlank(assessInfo.getComplaintMainId())) {
                MachineAccount machineAccount1 = this.getM(assessInfo.getComplaintMainId());
                if(machineAccount1!=null){
                    machineAccount1.preUpdate();
                    machineAccount1.setAssessGrade(assessInfo.getAssessGrade()==null?"":assessInfo.getAssessGrade());
                    machineAccount1.setAppraiser(assessInfo.getAppraiser()==null?"":assessInfo.getAppraiser());
                    dao.update(machineAccount1);
                }

            }
        }
    }

    public String TianShu(String strat,String end,int difference){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d1 = null;
        Date d2 = null;
        Integer day=0;
        try {
            d1 = format.parse(strat);
            d2 = format.parse(end);

            long diff = d1.getTime() - d2.getTime();

            Integer total = (int)(diff/1000);
            day = total/(3600*24) - difference;
            //machineAccount.setFlowDays(String.valueOf(day));
        }catch (Exception e){
            e.printStackTrace();
        }

        return String.valueOf(day);
    }

    public Page<MachineAccount> getMachine(Page<MachineAccount> page,MachineAccount machineAccount){
//        machineAccount.getSqlMap().get("",);
        machineAccount.setPage(page);
        page.setList(machineAccountDao.findMachine(machineAccount));
        return page;
    }
}