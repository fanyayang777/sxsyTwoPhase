/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.insuranceslip.service;

import java.util.List;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.insuranceslip.entity.InsuranceSlip;
import com.sayee.sxsy.modules.insuranceslip.dao.InsuranceSlipDao;

/**
 * 投保单Service
 * @author yang
 * @version 2020-03-29
 */
@Service
@Transactional(readOnly = true)
public class InsuranceSlipService extends CrudService<InsuranceSlipDao, InsuranceSlip> {

	public InsuranceSlip get(String id) {
		return super.get(id);
	}
	
	public List<InsuranceSlip> findList(InsuranceSlip insuranceSlip) {
		return super.findList(insuranceSlip);
	}
	
	public Page<InsuranceSlip> findPage(Page<InsuranceSlip> page, InsuranceSlip insuranceSlip) {
		return super.findPage(page, insuranceSlip);
	}
	
	@Transactional(readOnly = false)
	public void save(InsuranceSlip insuranceSlip) {
		if (StringUtils.isBlank(insuranceSlip.getInsurancePolicyId())){
			insuranceSlip.preInsert();
			insuranceSlip.setInsurancePolicyId(insuranceSlip.getId());

			insuranceSlip.setReject(StringUtils.isBlank(insuranceSlip.getReject()) ? "":insuranceSlip.getReject()); ;
			insuranceSlip.setSickbedNumber(StringUtils.isBlank(insuranceSlip.getSickbedNumber()) ? "0":insuranceSlip.getSickbedNumber()); ;
			insuranceSlip.setPeopleNumber(StringUtils.isBlank(insuranceSlip.getPeopleNumber()) ? "0":insuranceSlip.getPeopleNumber()); ;
			insuranceSlip.setMedicalStaffNumber(StringUtils.isBlank(insuranceSlip.getMedicalStaffNumber()) ? "0":insuranceSlip.getMedicalStaffNumber()); ;
			insuranceSlip.setEveryoneQuota(StringUtils.isBlank(insuranceSlip.getEveryoneQuota()) ? "0":insuranceSlip.getEveryoneQuota()); ;
			insuranceSlip.setSpiritQuota(StringUtils.isBlank(insuranceSlip.getSpiritQuota()) ? "0":insuranceSlip.getSpiritQuota()); ;
			insuranceSlip.setEverytimeQuota(StringUtils.isBlank(insuranceSlip.getEverytimeQuota()) ? "0":insuranceSlip.getEverytimeQuota());

			insuranceSlip.setAccumulatedQuota(StringUtils.isBlank(insuranceSlip.getAccumulatedQuota()) ? "0":insuranceSlip.getAccumulatedQuota()); ;
			insuranceSlip.setLawEverytimeQuota(StringUtils.isBlank(insuranceSlip.getLawEverytimeQuota()) ? "0":insuranceSlip.getLawEverytimeQuota()); ;
			insuranceSlip.setLawAccumulatedQuota(StringUtils.isBlank(insuranceSlip.getAccumulatedQuota()) ? "0":insuranceSlip.getAccumulatedQuota()); ;
			insuranceSlip.setAppendEverytimeQuota(StringUtils.isBlank(insuranceSlip.getAppendEverytimeQuota()) ? "0":insuranceSlip.getAppendEverytimeQuota()); ;
			insuranceSlip.setAppendAccumulatedQuota(StringUtils.isBlank(insuranceSlip.getAppendAccumulatedQuota()) ? "0":insuranceSlip.getAppendAccumulatedQuota()); ;
			insuranceSlip.setBedPremium(StringUtils.isBlank(insuranceSlip.getBedPremium()) ? "0":insuranceSlip.getBedPremium()); ;
			insuranceSlip.setAllMedicalNumber(StringUtils.isBlank(insuranceSlip.getAllMedicalNumber()) ? "0":insuranceSlip.getAllMedicalNumber()); ;
			insuranceSlip.setAllEveryonePremium(StringUtils.isBlank(insuranceSlip.getAllEveryonePremium()) ? "0":insuranceSlip.getAllEveryonePremium()); ;
			insuranceSlip.setAllPremiumTotal(StringUtils.isBlank(insuranceSlip.getAllPremiumTotal()) ? "0":insuranceSlip.getAllPremiumTotal()); ;
			insuranceSlip.setSelEveryonePremium(StringUtils.isBlank(insuranceSlip.getSelEveryonePremium()) ? "0":insuranceSlip.getSelEveryonePremium()); ;
			insuranceSlip.setSelClinicOperationNumber(StringUtils.isBlank(insuranceSlip.getSelClinicOperationNumber()) ? "0":insuranceSlip.getSelClinicOperationNumber()); ;
			insuranceSlip.setSelClinicNotoperationNumber(StringUtils.isBlank(insuranceSlip.getSelClinicNotoperationNumber()) ? "0":insuranceSlip.getSelClinicNotoperationNumber()); ;
			insuranceSlip.setSelMedicalLaboratoryNumber(StringUtils.isBlank(insuranceSlip.getSelMedicalLaboratoryNumber()) ? "0":insuranceSlip.getSelMedicalLaboratoryNumber()); ;
			insuranceSlip.setSelPremiumTotal(StringUtils.isBlank(insuranceSlip.getSelPremiumTotal()) ? "0":insuranceSlip.getSelPremiumTotal()); ;
			insuranceSlip.setBasicPremiumTotal(StringUtils.isBlank(insuranceSlip.getBasicPremiumTotal()) ? "0":insuranceSlip.getBasicPremiumTotal()); ;
			insuranceSlip.setAddReduce(StringUtils.isBlank(insuranceSlip.getAddReduce()) ? "0":insuranceSlip.getAddReduce()); ;
			insuranceSlip.setEnchanceDeduction(StringUtils.isBlank(insuranceSlip.getEnchanceDeduction()) ? "0":insuranceSlip.getEnchanceDeduction()); ;
			insuranceSlip.setRiskFloat(StringUtils.isBlank(insuranceSlip.getRiskFloat()) ? "0":insuranceSlip.getRiskFloat()); ;
			insuranceSlip.setAsleftPremium(StringUtils.isBlank(insuranceSlip.getAsleftPremium()) ? "0":insuranceSlip.getAsleftPremium()); ;
			insuranceSlip.setAddittionPremium(StringUtils.isBlank(insuranceSlip.getAddittionPremium()) ? "0":insuranceSlip.getAddittionPremium()); ;
			insuranceSlip.setOddicialReceiptsPermium(StringUtils.isBlank(insuranceSlip.getOddicialReceiptsPermium())? "0":insuranceSlip.getOddicialReceiptsPermium()); ;
			insuranceSlip.setInsuranceDate(StringUtils.isBlank(insuranceSlip.getInsuranceDate()) ? "0":insuranceSlip.getInsuranceDate()); ;
			insuranceSlip.setRetroactiveDate(StringUtils.isBlank(insuranceSlip.getRetroactiveDate()) ? "0":insuranceSlip.getRetroactiveDate()); ;

			dao.insert(insuranceSlip);
		}else {
			insuranceSlip.preUpdate();

			insuranceSlip.setReject(StringUtils.isBlank(insuranceSlip.getReject()) ? "":insuranceSlip.getReject()); ;
			insuranceSlip.setSickbedNumber(StringUtils.isBlank(insuranceSlip.getSickbedNumber()) ? "0":insuranceSlip.getSickbedNumber()); ;
			insuranceSlip.setPeopleNumber(StringUtils.isBlank(insuranceSlip.getPeopleNumber()) ? "0":insuranceSlip.getPeopleNumber()); ;
			insuranceSlip.setMedicalStaffNumber(StringUtils.isBlank(insuranceSlip.getMedicalStaffNumber()) ? "0":insuranceSlip.getMedicalStaffNumber()); ;
			insuranceSlip.setEveryoneQuota(StringUtils.isBlank(insuranceSlip.getEveryoneQuota()) ? "0":insuranceSlip.getEveryoneQuota()); ;
			insuranceSlip.setSpiritQuota(StringUtils.isBlank(insuranceSlip.getSpiritQuota()) ? "0":insuranceSlip.getSpiritQuota()); ;
			insuranceSlip.setEverytimeQuota(StringUtils.isBlank(insuranceSlip.getEverytimeQuota()) ? "0":insuranceSlip.getEverytimeQuota());

			insuranceSlip.setAccumulatedQuota(StringUtils.isBlank(insuranceSlip.getAccumulatedQuota()) ? "0":insuranceSlip.getAccumulatedQuota()); ;
			insuranceSlip.setLawEverytimeQuota(StringUtils.isBlank(insuranceSlip.getLawEverytimeQuota()) ? "0":insuranceSlip.getLawEverytimeQuota()); ;
			insuranceSlip.setLawAccumulatedQuota(StringUtils.isBlank(insuranceSlip.getAccumulatedQuota()) ? "0":insuranceSlip.getAccumulatedQuota()); ;
			insuranceSlip.setAppendEverytimeQuota(StringUtils.isBlank(insuranceSlip.getAppendEverytimeQuota()) ? "0":insuranceSlip.getAppendEverytimeQuota()); ;
			insuranceSlip.setAppendAccumulatedQuota(StringUtils.isBlank(insuranceSlip.getAppendAccumulatedQuota()) ? "0":insuranceSlip.getAppendAccumulatedQuota()); ;
			insuranceSlip.setBedPremium(StringUtils.isBlank(insuranceSlip.getBedPremium()) ? "0":insuranceSlip.getBedPremium()); ;
			insuranceSlip.setAllMedicalNumber(StringUtils.isBlank(insuranceSlip.getAllMedicalNumber()) ? "0":insuranceSlip.getAllMedicalNumber()); ;
			insuranceSlip.setAllEveryonePremium(StringUtils.isBlank(insuranceSlip.getAllEveryonePremium()) ? "0":insuranceSlip.getAllEveryonePremium()); ;
			insuranceSlip.setAllPremiumTotal(StringUtils.isBlank(insuranceSlip.getAllPremiumTotal()) ? "0":insuranceSlip.getAllPremiumTotal()); ;
			insuranceSlip.setSelEveryonePremium(StringUtils.isBlank(insuranceSlip.getSelEveryonePremium()) ? "0":insuranceSlip.getSelEveryonePremium()); ;
			insuranceSlip.setSelClinicOperationNumber(StringUtils.isBlank(insuranceSlip.getSelClinicOperationNumber()) ? "0":insuranceSlip.getSelClinicOperationNumber()); ;
			insuranceSlip.setSelClinicNotoperationNumber(StringUtils.isBlank(insuranceSlip.getSelClinicNotoperationNumber()) ? "0":insuranceSlip.getSelClinicNotoperationNumber()); ;
			insuranceSlip.setSelMedicalLaboratoryNumber(StringUtils.isBlank(insuranceSlip.getSelMedicalLaboratoryNumber()) ? "0":insuranceSlip.getSelMedicalLaboratoryNumber()); ;
			insuranceSlip.setSelPremiumTotal(StringUtils.isBlank(insuranceSlip.getSelPremiumTotal()) ? "0":insuranceSlip.getSelPremiumTotal()); ;
			insuranceSlip.setBasicPremiumTotal(StringUtils.isBlank(insuranceSlip.getBasicPremiumTotal()) ? "0":insuranceSlip.getBasicPremiumTotal()); ;
			insuranceSlip.setAddReduce(StringUtils.isBlank(insuranceSlip.getAddReduce()) ? "0":insuranceSlip.getAddReduce()); ;
			insuranceSlip.setEnchanceDeduction(StringUtils.isBlank(insuranceSlip.getEnchanceDeduction()) ? "0":insuranceSlip.getEnchanceDeduction()); ;
			insuranceSlip.setRiskFloat(StringUtils.isBlank(insuranceSlip.getRiskFloat()) ? "0":insuranceSlip.getRiskFloat()); ;
			insuranceSlip.setAsleftPremium(StringUtils.isBlank(insuranceSlip.getAsleftPremium()) ? "0":insuranceSlip.getAsleftPremium()); ;
			insuranceSlip.setAddittionPremium(StringUtils.isBlank(insuranceSlip.getAddittionPremium()) ? "0":insuranceSlip.getAddittionPremium()); ;
			insuranceSlip.setOddicialReceiptsPermium(StringUtils.isBlank(insuranceSlip.getOddicialReceiptsPermium())? "0":insuranceSlip.getOddicialReceiptsPermium()); ;
			insuranceSlip.setInsuranceDate(StringUtils.isBlank(insuranceSlip.getInsuranceDate()) ? "0":insuranceSlip.getInsuranceDate()); ;
			insuranceSlip.setRetroactiveDate(StringUtils.isBlank(insuranceSlip.getRetroactiveDate()) ? "0":insuranceSlip.getRetroactiveDate()); ;
			dao.update(insuranceSlip);
		}

//		super.save(insuranceSlip);
	}
	
	@Transactional(readOnly = false)
	public void delete(InsuranceSlip insuranceSlip) {
		super.delete(insuranceSlip);
	}
	
}