/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.insuranceslip.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.google.common.collect.Lists;
import com.sayee.sxsy.common.beanvalidator.BeanValidators;
import com.sayee.sxsy.common.utils.AjaxHelper;
import com.sayee.sxsy.common.utils.DateUtils;
import com.sayee.sxsy.common.utils.excel.ExportExcel;
import com.sayee.sxsy.common.utils.excel.ImportExcel;
import com.sayee.sxsy.modules.machine.entity.MachineAccount;
import com.sayee.sxsy.modules.roster.entity.Roster;
import com.sayee.sxsy.modules.roster.service.RosterService;
import com.sayee.sxsy.modules.sys.entity.Dict;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.service.SystemService;
import com.sayee.sxsy.modules.sys.utils.DictUtils;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sayee.sxsy.common.config.Global;
import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.web.BaseController;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.insuranceslip.entity.InsuranceSlip;
import com.sayee.sxsy.modules.insuranceslip.service.InsuranceSlipService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 投保单Controller
 * @author yang
 * @version 2020-03-29
 */
@Controller
@RequestMapping(value = "${adminPath}/insuranceslip/insuranceSlip")
public class InsuranceSlipController extends BaseController {

	@Autowired
	private InsuranceSlipService insuranceSlipService;

	@ModelAttribute
	public InsuranceSlip get(@RequestParam(required=false) String id) {
		InsuranceSlip entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = insuranceSlipService.get(id);
		}
		if (entity == null){
			entity = new InsuranceSlip();
		}
		return entity;
	}
	
	@RequiresPermissions("insuranceslip:insuranceSlip:view")
	@RequestMapping(value = {"list", ""})
	public String list(InsuranceSlip insuranceSlip, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<InsuranceSlip> page = insuranceSlipService.findPage(new Page<InsuranceSlip>(request, response), insuranceSlip); 
		model.addAttribute("page", page);
		return "modules/insuranceslip/insuranceSlipList";
	}

	@RequiresPermissions("insuranceslip:insuranceSlip:view")
	@RequestMapping(value = "form")
	public String form(InsuranceSlip insuranceSlip, Model model) {
		model.addAttribute("insuranceSlip", insuranceSlip);
		return "modules/insuranceslip/insuranceSlipForm";
	}

	@RequiresPermissions("insuranceslip:insuranceSlip:edit")
	@RequestMapping(value = "save")
	public String save(InsuranceSlip insuranceSlip, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, insuranceSlip)){
			return form(insuranceSlip, model);
		}
		String start = request.getParameter("start");
		insuranceSlip.setState(start);

		insuranceSlipService.save(insuranceSlip);
		addMessage(redirectAttributes, "保存投保单成功");
		return "redirect:"+Global.getAdminPath()+"/insuranceslip/insuranceSlip/?repage";
	}
	
	@RequiresPermissions("insuranceslip:insuranceSlip:edit")
	@RequestMapping(value = "delete")
	public String delete(InsuranceSlip insuranceSlip, RedirectAttributes redirectAttributes) {
		insuranceSlipService.delete(insuranceSlip);
		addMessage(redirectAttributes, "删除投保单成功");
		return "redirect:"+Global.getAdminPath()+"/insuranceslip/insuranceSlip/?repage";
	}

	//跳转条款页面
	@RequestMapping(value = "clause")
	public String alcuse(){
		return "modules/insuranceslip/clause";
	}
	//跳转产品介绍页面
	@RequestMapping(value = "introduce")
	public String introduce(){
		return "modules/insuranceslip/introduce";
	}

	@RequestMapping(value = "officeInfo")
	public void officeInfo(HttpServletRequest request,HttpServletResponse response) {
		String code="";//1.成功 0失败
		Map<String,Object> map=new HashMap<String,Object>();
		String hospital=request.getParameter("hospital");//前台传过来的状态
		System.out.println(hospital);
		if (StringUtils.isBlank(hospital)){
			map.put("status","0");
			AjaxHelper.responseWrite(request,response,"1","success",map);
			return;
		}
		Office o = UserUtils.getOfficeId(hospital);  //获取医院对象
		if(StringUtils.isNotBlank(o.getHospitalGrade())){
			String label=DictUtils.getDictLabel(o.getHospitalGrade(),"hospital_grade","其他");
			map.put("hospitalGrade",label); //医院等级
		}
		map.put("theInsured",o.getName1()); //医院名
		map.put("policyPhone",o.getPhone()); //投保人联系电话
		map.put("emailAddress",o.getEmail()); //投保人：电子邮箱
		map.put("sitePostcode",o.getAddress()); //投保人：通信地址
		map.put("postalCode",o.getZipCode()); //被保人：邮编
		map.put("gradeValue",o.getHospitalGrade()); //医院等级 value
		map.put("sickbedNumber",o.getBeds()); //床位数
		//医院类别
		String dictLabel = DictUtils.getDictLabel(o.getGrade(), "sys_office_grade", "");
		map.put("category",dictLabel);
		String office_coefficient = DictUtils.getDictValue(o.getGrade(), "office_coefficient", "");
		map.put("coefficient",office_coefficient); //医院类别系数

		//基本费用
		String bedsMoney = DictUtils.getDictValue("500", "beds", "");
		String bedsNumber = o.getBeds();
		int bedsM = Integer.parseInt(bedsMoney);	//床位单价
		int bedsN = Integer.parseInt(bedsNumber);	//床位数
		int medicalPremium = bedsM * bedsN; //医疗机构保险费
		map.put("bedPremium",bedsMoney);//每床位保险费
		map.put("medicalPremium",medicalPremium); //医疗机构保险费
		//每人基本保险费
		String allEveryonePremium = DictUtils.getDictValue("400", "one_premium", "");
		map.put("allEveryonePremium",allEveryonePremium);
		//累计赔偿限额
		String accumulatedQuota = DictUtils.getDictValue(o.getHospitalGrade(),"accumulated_quota","");
		map.put("accumulatedQuota",accumulatedQuota);
		String accumulatedQuotaBig = DictUtils.big(accumulatedQuota, "accumulated_quota", "");
		map.put("accumulatedQuotaBig",accumulatedQuotaBig);
		//精神赔偿限额
		String spiritQuota = DictUtils.getDictValue(o.getHospitalGrade(),"spirit_quota","");
		map.put("spiritQuota",spiritQuota);
		//每次赔偿限额
		String everytimeQuota = DictUtils.getDictValue(o.getHospitalGrade(),"ecerytime_quota","");
		map.put("everytimeQuota",everytimeQuota);
		String everytimeQuotaBig = DictUtils.big(everytimeQuota,"ecerytime_quota","");
		map.put("everytimeQuotaBig",everytimeQuotaBig);
		//每人累计赔偿限额
		String everyoneQuota = DictUtils.getDictValue(o.getHospitalGrade(),"eceryone_quota","");
		map.put("everyoneQuota",everyoneQuota);
		String everyoneQuotaBig = DictUtils.big(everyoneQuota,"eceryone_quota","");
		map.put("everyoneQuotaBig",everyoneQuotaBig);
		//法律费用 累计限额
		String lawAccumulatedQuota = DictUtils.getDictValue(o.getHospitalGrade(),"law_accumulated_quota","");
		map.put("lawAccumulatedQuota",lawAccumulatedQuota);
		String lawAccumulatedQuotaBig = DictUtils.big(lawAccumulatedQuota,"law_accumulated_quota","");
		map.put("lawAccumulatedQuotaBig",lawAccumulatedQuotaBig);
		//法律费用 每次限额
		String lawEverytimeQuota = DictUtils.getDictValue(o.getHospitalGrade(),"law_everytime_quota","");
		map.put("lawEverytimeQuota",lawEverytimeQuota);
		String lawEverytimeQuotaBig = DictUtils.big(lawEverytimeQuota,"law_everytime_quota","");
		map.put("lawEverytimeQuotaBig",lawEverytimeQuotaBig);
		//每床位保险费
		String beds1 = DictUtils.getDictValue("500", "beds", "");
		String beds = DictUtils.getDictValue(beds1,"beds","");
		map.put("beds",beds);

		//附加险
		String addittionRisk = request.getParameter("addittionRisk"); //附加险选中内容
		if (StringUtils.isNotBlank(addittionRisk)){
			String beds2 = o.getBeds();
			String beds02 = DictUtils.getDictLabel(o.getBeds(), "beds", "无法计算");
			if (addittionRisk.equals("changsuo")){
				//获取字典
				List<Dict> sitePremium = DictUtils.getDictList("site_premium");
				System.out.println(sitePremium);
				String label;
				for (Dict dict : sitePremium) {
//					System.out.println(dict);
					String s = dict.toString();
					String[] split1 = s.split("-");
					Integer minNum = Integer.valueOf(split1[0]);
					Integer maxNum = Integer.valueOf(split1[1]);
					Integer bed = Integer.valueOf(beds2);
					if (bed >= minNum && bed <= maxNum ){
						label = minNum + "-" + maxNum;
						String sitePremium1 = DictUtils.getDictValue(label, "site_premium", "");
						map.put("sitePremium1",sitePremium1); //附加：场所—保费
						String siteEachtime = DictUtils.getDictValue(label, "site_eachtime", "");
						map.put("siteEachtime",siteEachtime); //附加：每人
						String siteYearAddup = DictUtils.getDictValue(label, "site_year_addup", "");
						map.put("siteYearAddup",siteYearAddup); //附加：年累计
						String siteAddup = DictUtils.getDictValue(label, "site_addup", "");
						map.put("siteAddup",siteAddup); //附加：每次
						break;
					}
				}
			}else if (addittionRisk.equals("shanghai")){
				//保费
				String accidentPremium = DictUtils.getDictValue("baofei", "accident_premium", "");
				//保费金额 一般
				String accidentInsuranceOne = DictUtils.getDictValue("yiban", "accident_insurance01", "");
				//保费金额 纠纷
				String accidentInsuranceTwo = DictUtils.getDictValue("jiufen", "accident_insurance02", "");
				//意外医疗 一般
				String accidentMedicalOne = DictUtils.getDictValue("yiban", "accident_medical01", "");
				//意外医疗 纠纷
				String accidentMedicalTwo = DictUtils.getDictValue("jiufen", "accident_medical02", "");
				map.put("accidentPremium",accidentPremium);
				map.put("accidentInsuranceOne",accidentInsuranceOne);
				map.put("accidentInsuranceTwo",accidentInsuranceTwo);
				map.put("accidentMedicalOne",accidentMedicalOne);
				map.put("accidentMedicalTwo",accidentMedicalTwo);
			}else if (addittionRisk.equals("chuanran")){
				//保费
				String contagionPremium = DictUtils.getDictValue("baofei", "contagion_premium", "");
				//每人累计限额
				String contagionOneQuota = DictUtils.getDictValue("one", "contagion_one_quota", "");
				//累计赔偿限额
				String contagionYearQuota = DictUtils.getDictValue("leiji", "contagion_year_quota", "");
				map.put("contagionPremium",contagionPremium);
				map.put("contagionOneQuota",contagionOneQuota);
				map.put("contagionYearQuota",contagionYearQuota);
			}else if (addittionRisk.equals("waipin")){
				//保费系数
				String ououtsourcing = DictUtils.getDictValue("waipin", "ououtsourcing", "");
				map.put("ououtsourcing",ououtsourcing);
			}
		}
		System.out.println(map);
		AjaxHelper.responseWrite(request,response,"1","success",map);
	}
	@Autowired
	private RosterService RosterService;
    /**
     * 导入花名册数据
     * @param file
     * @param redirectAttributes
     * @return
     */
//    @RequiresPermissions("insuranceslip:insuranceSlip:edit")
    @RequestMapping(value = "import", method= RequestMethod.POST)
    public void importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
//		if(Global.isDemoMode()){
//			addMessage(redirectAttributes, "演示模式，不允许操作！");
//			return "redirect:" + adminPath + "/sys/user/list?repage";
//		}
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(file, 1, 0);
            List<Roster> list = ei.getDataList(Roster.class);
            for (Roster roster : list){
                try{
                    if(StringUtils.isBlank(roster.getPracticeNumber())){
                        // 执业资格证号不存在 不进行验重
						RosterService.save(roster);
                        successNum++;
                    }else {
                        if (true==RosterService.checkFileNumber(roster.getPracticeNumber())){
                            RosterService.save(roster);
                            successNum++;
                        }else{
                            failureMsg.append("<br/>执业资格证号 "+roster.getPracticeNumber()+" 已存在; ");
                            failureNum++;
                        }
                    }
                }catch(ConstraintViolationException ex){
                    failureMsg.append("<br/>执业资格证号 "+roster.getPracticeNumber()+" 导入失败：");
                    List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
                    for (String message : messageList){
                        failureMsg.append(message+"; ");
                        failureNum++;
                    }
                }catch (Exception ex) {
                    failureMsg.append("<br/>执业资格证号 "+roster.getPracticeNumber()+" 导入失败："+ex.getMessage());
                }
            }
            if (failureNum>0){
                failureMsg.insert(0, "，失败 "+failureNum+" 条人员，导入信息如下：");
            }
            addMessage(redirectAttributes, "已成功导入 "+successNum+" 条人员信息"+failureMsg);
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入花名册失败！失败信息："+e.getMessage());
        }
//        return "redirect:" + adminPath + "/insuranceslip/insuranceSlip/form?repage";
    }

    /**
     * 下载导入用户数据模板
     * @param response
     * @param redirectAttributes
     * @return
     */
//    @RequiresPermissions("insuranceslip:insuranceSlip:view")
    @RequestMapping(value = "import/template")
    public void importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "花名册导入模板.xlsx";
            List<Roster> list = Lists.newArrayList();
            new ExportExcel("花名册信息", Roster.class, 2).setDataList(list).write(response, fileName).dispose();
//            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
        }
//        return "redirect:" + adminPath + "/insuranceslip/insuranceSlip/list?repage";
    }

    /**
     * 导出台账
     */
    @RequiresPermissions("insuranceslip:insuranceSlip:view")
    @RequestMapping(value = "export",method = RequestMethod.POST)
    public void exportFile(Roster roster,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes){
        try {
			Roster roster1 = new Roster();
            if(StringUtils.isNotBlank(roster.getInsuranceSlipId())) {
            	roster1.setInsuranceSlipId(roster.getInsuranceSlipId());
            }
            String fileName = "花名册数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            Page<Roster> page = RosterService.getRoster(new Page<Roster>(request, response, -1), roster);
            if (page.getList().size() != 0) {
                new ExportExcel("花名册数据", MachineAccount.class).setDataList(page.getList()).write(response, fileName).dispose();
//                return null;
            } else {
                addMessage(redirectAttributes, "导出花名册失败！失败信息：没有这所医院的花名册可导出");
//                return "redirect:" + adminPath + "/insuranceslip/insuranceSlip/list?repage";
            }
        }catch (Exception e){
            addMessage(redirectAttributes,"导出花名册失败！失败信息："+e.getMessage());
        }
//        return "redirect:" + adminPath + "/insuranceslip/insuranceSlip/list?repage";
    }
}