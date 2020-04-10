/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.stopmediate.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sayee.sxsy.common.config.Global;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.common.utils.WordExportUtil;
import com.sayee.sxsy.modules.act.entity.Act;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.complaintmain.dao.ComplaintMainDao;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.stopmediate.entity.StopMediate;
import com.sayee.sxsy.modules.stopmediate.dao.StopMediateDao;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 终止调解Service
 * @author lyt
 * @version 2019-06-20
 */
@Service
@Transactional(readOnly = true)
public class StopMediateService extends CrudService<StopMediateDao, StopMediate> {
	@Autowired
	private ComplaintMainDao complaintMainDao;
	@Autowired
	private ActTaskService actTaskService;
    @Autowired
    private StopMediateDao stopMediateDao;
	public StopMediate get(String id) {
		return super.get(id);
	}
	
	public List<StopMediate> findList(StopMediate stopMediate) {
		return super.findList(stopMediate);
	}
	
	public Page<StopMediate> findPage(Page<StopMediate> page, StopMediate stopMediate) {
		return super.findPage(page, stopMediate);
	}
	
	@Transactional(readOnly = false)
	public void save(StopMediate stopMediate) {
		if(StringUtils.isBlank(stopMediate.getCreateBy().getId()) || "".equals(stopMediate.getCreateBy().getId())){
			stopMediate.preInsert();
			stopMediate.setStopMediateId(stopMediate.getId());
			stopMediateDao.insert(stopMediate);
		}else{
			stopMediate.preUpdate();
            stopMediateDao.update(stopMediate);
		}
//		super.save(stopMediate);
		if ("yes".equals(stopMediate.getComplaintMain().getAct().getFlag())){
//			List<Act> list = actTaskService.todoList(stopMediate.getComplaintMain().getAct());

			Map<String,Object> var=new HashMap<String, Object>();
			var.put("pass","1");
			User assigness= UserUtils.get(stopMediate.getNextLinkMan());
			var.put("summary_user",assigness.getLoginName());
			// 执行流程
			actTaskService.complete(stopMediate.getComplaintMain().getAct().getTaskId(), stopMediate.getComplaintMain().getProcInsId(), stopMediate.getComplaintMain().getAct().getComment(), stopMediate.getComplaintMain().getCaseNumber(), var);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(StopMediate stopMediate) {
		super.delete(stopMediate);
	}

	public StopMediate handle(StopMediate stopMediate, String module,HttpServletRequest request) {
		ComplaintMain complaintMain=complaintMainDao.get(stopMediate.getComplaintMainId());
		if (StringUtils.isBlank(stopMediate.getStopMediateId())){
           StopMediate ss=stopMediateDao.passCom(stopMediate.getComplaintMainId());
           if (ss!=null){
               stopMediate=ss;
           }
        }
		stopMediate.setComplaintMain(complaintMain);
		stopMediate.setPatientName(stopMediate.getPatientName()==null ? complaintMain.getPatientName() : stopMediate.getPatientName());
		stopMediate.setInvolveHospital(stopMediate.getInvolveHospital() == null ? complaintMain.getInvolveHospital() : stopMediate.getInvolveHospital());
		//stopMediate.getHospital().setName();
		if ("badj".equals(module)){
			if(StringUtils.isNotBlank(request.getParameter("url1"))){
				stopMediate.setPower("/registration/reportRegistration/");
				stopMediate.setInfo("报案信息列表");
			}else if(StringUtils.isNotBlank(request.getParameter("url2"))){
				stopMediate.setPower("/auditacceptance/auditAcceptance/");
				stopMediate.setInfo("审核受理列表");
			}else if(StringUtils.isNotBlank(request.getParameter("url3"))){
				stopMediate.setPower("/nestigateeividence/investigateEvidence/");
				stopMediate.setInfo("调查取证列表");
			}else if(StringUtils.isNotBlank(request.getParameter("url7"))){
				stopMediate.setPower("/mediate/mediateEvidence/");
				stopMediate.setInfo("质证调解列表");
			}else if(StringUtils.isNotBlank(request.getParameter("url5"))){
				stopMediate.setPower("/assessapply/assessApply/");
				stopMediate.setInfo("评估鉴定申请列表");
			}else if(StringUtils.isNotBlank(request.getParameter("url6"))){
				stopMediate.setPower("/assessaudit/assessAudit/");
				stopMediate.setInfo("评估鉴定审批列表");
			}else if(StringUtils.isNotBlank(request.getParameter("url4"))){
				stopMediate.setPower("/assessappraisal/assessAppraisal/");
				stopMediate.setInfo("评估鉴定列表");
			}else if(StringUtils.isNotBlank(request.getParameter("url8"))){
				stopMediate.setPower("/reachmediate/reachMediate/");
				stopMediate.setInfo("达成调解列表");
			}else if(StringUtils.isNotBlank(request.getParameter("url9"))){
				stopMediate.setPower("/sign/signAgreement/");
				stopMediate.setInfo("签署协议列表");
			}else if(StringUtils.isNotBlank(request.getParameter("url10"))){
				stopMediate.setPower("/perform/performAgreement/");
				stopMediate.setInfo("履行协议列表");
			}

		}
		return stopMediate;
	}

	public String exportWord(StopMediate stopMediate, String export,String print, HttpServletRequest request, HttpServletResponse response) {
		WordExportUtil wordExportUtil=new WordExportUtil();
        ComplaintMain complaintMain=complaintMainDao.get(stopMediate.getComplaintMainId());
		stopMediate=this.get(stopMediate.getStopMediateId());
		String path=request.getServletContext().getRealPath("/");
		String newFileName="无标题文件.docx";
		String caseNumbr=stopMediate.getComplaintMain()==null ? "" : StringUtils.isBlank(stopMediate.getComplaintMain().getCaseNumber()) ? "" : stopMediate.getComplaintMain().getCaseNumber()+"/";
		String returnPath="";
		String savaPath=path;
		String pdfPath=path;
		Map<String, Object> params = new HashMap<String, Object>();
		String area="";
		User user=UserUtils.getUser();
		if ("广东省".equals(user.getAreaName())){
			params.put("area",user.getCompany().getName().substring(0,3));
		}else {
			params.put("area",user.getAreaName());
		}
		if ("yes".equals(export)){
			params.put("patient", stopMediate ==null ? complaintMain.getPatientName() :stopMediate.getPatientName());
			params.put("hospital", stopMediate ==null ? complaintMain.getHospital().getName() : stopMediate.getHospital().getName());
			path += "/doc/stopMediate.docx";  //模板文件位置
			newFileName="终止调解函.docx";
			savaPath +="/userfiles/stopMediate/"+caseNumbr+"stopMediate.docx";
			pdfPath +="/userfiles/stopMediate/"+caseNumbr+"stopMediate.pdf";
			returnPath="/userfiles/stopMediate/"+caseNumbr+"stopMediate.pdf";
		}
		try{
			File file =new File(request.getServletContext().getRealPath("/")+"/userfiles/stopMediate/"+caseNumbr);
			if (!file.exists()){
				file.mkdirs();
			}
			List<String[]> testList = new ArrayList<String[]>();
			String fileName= new String(newFileName.getBytes("UTF-8"),"iso-8859-1");    //生成word文件的文件名
			wordExportUtil.getWord(path,path,savaPath,print,params,testList,fileName,response);
			wordExportUtil.doc2pdf(savaPath,new FileOutputStream(pdfPath));
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnPath;
	}
}