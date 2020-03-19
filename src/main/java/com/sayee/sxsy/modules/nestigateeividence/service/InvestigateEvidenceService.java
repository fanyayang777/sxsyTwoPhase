/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.nestigateeividence.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import com.google.common.collect.Lists;
import com.sayee.sxsy.common.utils.*;
import com.sayee.sxsy.modules.act.entity.Act;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.complaintmain.dao.ComplaintMainDao;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.respondentinfo.dao.RespondentInfoDao;
import com.sayee.sxsy.modules.respondentinfo.entity.RespondentInfo;
import com.sayee.sxsy.modules.respondentinfo.service.RespondentInfoService;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.Role;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.DictUtils;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.nestigateeividence.entity.InvestigateEvidence;
import com.sayee.sxsy.modules.nestigateeividence.dao.InvestigateEvidenceDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 调查取证Service
 * @author gbq
 * @version 2019-06-10
 */
@Service
@Transactional(readOnly = true)
public class InvestigateEvidenceService extends CrudService<InvestigateEvidenceDao, InvestigateEvidence> {
    @Autowired
    private ActTaskService actTaskService;
    @Autowired
    private ComplaintMainDao complaintMainDao;
    @Autowired
    private RespondentInfoService respondentInfoService;
    @Autowired
    private PreOperativeConsentService preOperativeConsentService;
    @Autowired
    private RespondentInfoDao respondentInfoDao;

    public InvestigateEvidence get(String id) {
        return super.get(id);
    }

    public List<InvestigateEvidence> findList(InvestigateEvidence investigateEvidence) {
        //获取当前登陆用户
        investigateEvidence.setUser(UserUtils.getUser());
        return super.findList(investigateEvidence);
    }

    public Page<InvestigateEvidence> findPage(Page<InvestigateEvidence> page, InvestigateEvidence investigateEvidence) {
        List<Role> roleList=UserUtils.getRoleList();//获取当前登陆人角色
        List<String> aa= ObjectUtils.convert(roleList.toArray(),"enname",true);
        User user=UserUtils.getUser();
        if (user.isAdmin() || aa.contains("quanshengtiaojiebuzhuren") || aa.contains("yitiaoweizhuren")
                || aa.contains("yitiaoweifuzhuren")|| aa.contains("shengzhitiaojiebuzhuren/fuzhuren")|| aa.contains("yitiaoweizhuren")
        ){    //!aa.contains("dept") &&
        }else if(aa.contains("szcz") || aa.contains("szjc") || aa.contains("szjz") || aa.contains("szgj") ||aa.contains("szyq") ||aa.contains("szsz") ||aa.contains("szxc") || aa.contains("szdt") || aa.contains("szll") ||aa.contains("szxy") || aa.contains("szyc") ||aa.contains("szlf") ||aa.contains("szybzg") ||aa.contains("szebzg")){
            List<Office> officeList = Lists.newArrayList();// 按明细设置数据范围s
            for (Role role:roleList) {
                for (Office office:role.getOfficeList()) {
                    officeList.add(UserUtils.getOfficeId(office.getId()));//将获得的 明细 添加到list;
                }
            }
            //工作站 主任 副主任 看自己 的员工
            Set<String> list=new HashSet<String>();
            for (Office office:officeList) {
                List<User> listUser=UserUtils.getUserByOffice(office.getId());
                for (User people:listUser) {
                    list.add(people.getLoginName());
                }
            }
            //添加 自己的loginName
            list.add(UserUtils.getUser().getLoginName());
            if (list.size()>0){
                investigateEvidence.setList(new ArrayList(list));
            }else {
                list.add(user.getLoginName());
                investigateEvidence.setList(new ArrayList(list));
            }
        }else if((  aa.contains("gongzuozhanzhuren/fuzhuren")) ){
            //工作站 主任 副主任 看自己 的员工
            List<String> list=new ArrayList<String>();
            List<User> listUser=UserUtils.getUserByOffice(user.getOffice().getId());
            for (User people:listUser) {
                list.add(people.getLoginName());
            }
            if (list.size()>0){
                investigateEvidence.setList(list);
            }else {
                list.add(user.getLoginName());
                investigateEvidence.setList(list);
            }
        }else {//不是管理员查看自己创建的
            investigateEvidence.setUser(UserUtils.getUser());
        }
        //Page<InvestigateEvidence> a = super.findPage(page, investigateEvidence);
        return super.findPage(page, investigateEvidence);
    }

    @Transactional(readOnly = false)
    public void save(InvestigateEvidence investigateEvidence, HttpServletRequest request) {
        //医方调查笔录实体类
        InvestigateEvidence yinvestigateEvidence = investigateEvidence.getInvestigateEvidence();
        //调查人实体类
        RespondentInfo d1 = investigateEvidence.getRespondentInfo();
        RespondentInfo d2 = investigateEvidence.getRespondentInfo2();
        RespondentInfo d3 = investigateEvidence.getRespondentInfo3();
        RespondentInfo d4 = investigateEvidence.getRespondentInfo4();
        if (StringUtils.isBlank(investigateEvidence.getCreateBy().getId())) {
            //判断主键ID是否为空
            investigateEvidence.preInsert();
            investigateEvidence.setInvestigateEvidenceId(investigateEvidence.getId());
            //将主键设为uuid  患方信息
            String investigateType1 = request.getParameter("investigateType");
            String investigateType2 = request.getParameter("investigateType2");
            investigateEvidence.setInvestigateType(investigateType1);
            //investigateEvidence.setComplaintMainId(investigateEvidence.getComplaintMain().getComplaintMainId());
            dao.insert(investigateEvidence);
            //保存调查人1
            this.respondent(d1, investigateEvidence.getInvestigateEvidenceId());
            //保存调查人2
            this.respondent(d2, investigateEvidence.getInvestigateEvidenceId());

            //保存医方信息
//			InvestigateEvidence yinvestigateEvidence=investigateEvidence.getInvestigateEvidence();
            yinvestigateEvidence.setInvestigateType(investigateType2);
            yinvestigateEvidence.setComplaintMainId(investigateEvidence.getComplaintMainId());
            yinvestigateEvidence.setInvestigateEvidenceId(IdGen.uuid());
            dao.insert(yinvestigateEvidence);
            //保存调查人1
//			RespondentInfo d3 = investigateEvidence.getRespondentInfo3();
            this.respondent(d3, investigateEvidence.getInvestigateEvidence().getInvestigateEvidenceId());
            //保存调查人2
//			RespondentInfo d4 = investigateEvidence.getRespondentInfo4();
            this.respondent(d4, investigateEvidence.getInvestigateEvidence().getInvestigateEvidenceId());

        } else {
            //如果不为空进行更新
            //修改调查取证表
            investigateEvidence.preUpdate();//更新者
            //更新患方笔录
            dao.update(investigateEvidence);

            //更新患方调查人1信息
            this.respondent(d1, investigateEvidence.getInvestigateEvidenceId());

            //更新患方调查人2信息
            this.respondent(d2, investigateEvidence.getInvestigateEvidenceId());
            //更新医方b笔录信息
            yinvestigateEvidence.preUpdate();
            dao.update(yinvestigateEvidence);
            //更新医方调查人1的信息
            d3.preUpdate();
            this.respondent(d3, yinvestigateEvidence.getInvestigateEvidenceId());
            //更新医方调查人2的信息
            d4.preUpdate();
            this.respondent(d4, yinvestigateEvidence.getInvestigateEvidenceId());

        }
        //保存附件
        this.savefj(request,investigateEvidence);

        //修改主表信息 因为处理的是  主表事由信息的  对主表信息进行修改即可

//		ComplaintMain complaintMain =investigateEvidence.getComplaintMain();
////		complaintMain.preUpdate();
////		complaintMain.setComplaintMainId(investigateEvidence.getComplaintMainId());
////		complaintMainDao.update(complaintMain);

        //super.save(investigateEvidence);
        if ("yes".equals(investigateEvidence.getComplaintMain().getAct().getFlag())) {
            //获取待办列表
            List<Act> list = actTaskService.todoList(investigateEvidence.getComplaintMain().getAct());
            Map<String, Object> var = new HashMap<String, Object>();
            var.put("pass", "0");
            User assigness = UserUtils.get(investigateEvidence.getNextLinkMan());
            var.put("mediation_user", assigness.getLoginName());
            //执行流程
            actTaskService.complete(investigateEvidence.getComplaintMain().getAct().getTaskId(), investigateEvidence.getComplaintMain().getAct().getProcInsId(), investigateEvidence.getComplaintMain().getAct().getComment(), investigateEvidence.getComplaintMain().getCaseNumber(), var);
        }


    }

    @Transactional(readOnly = false)
    public void delete(InvestigateEvidence investigateEvidence) {
        super.delete(investigateEvidence);
    }

    //对调查人信息进行处理
    @Transactional(readOnly = false)
    public void respondent(RespondentInfo respondentInfo, String relation) {
        //对调查人信息进程处理，如果有主键 说明是 进行修改方法； 如果没有主键 在看看有没有保存的年龄等信息，，如果有则insert，否则不处理
        if (StringUtils.isNotBlank(respondentInfo.getRespondentId())) {//主键不空  则进行修改
            respondentInfo.preUpdate();
            respondentInfo.setInvestigationEvidenceId(relation);
            respondentInfoDao.update(respondentInfo);

        } else if (StringUtils.isBlank(respondentInfo.getRespondentId())) {
            if (StringUtils.isNotBlank(respondentInfo.getRespondentAge())) {
                respondentInfo.setRespondentId(IdGen.uuid());
                respondentInfo.setInvestigationEvidenceId(relation);
                respondentInfo.preInsert();
                respondentInfoDao.insert(respondentInfo);
            }
        }

    }

    //查询调查人
    public void respondent(InvestigateEvidence investigateEvidence) {
        //遍历数据 拿到患方主键 查找调查人
        List<RespondentInfo> respondentInfo = respondentInfoDao.getL(investigateEvidence.getInvestigateEvidenceId());
        for (RespondentInfo r : respondentInfo) {
            if (investigateEvidence.getRespondentInfo() == null) {
                investigateEvidence.setRespondentInfo(r);
            } else {
                investigateEvidence.setRespondentInfo2(r);
            }
        }
        //查找医方调查人
        if(investigateEvidence.getInvestigateEvidence()!=null){
            List<RespondentInfo> YrespondentInfo = respondentInfoDao.getL(investigateEvidence.getInvestigateEvidence().getInvestigateEvidenceId());
            for (RespondentInfo yf : YrespondentInfo) {
                if (investigateEvidence.getRespondentInfo3() == null) {
                    investigateEvidence.setRespondentInfo3(yf);
                } else {
                    investigateEvidence.setRespondentInfo4(yf);
                }
            }
        }
        //return  investigateEvidence;
    }

    public String exportWord(InvestigateEvidence investigateEvidence, String export,String print, HttpServletRequest request, HttpServletResponse response) {
        WordExportUtil wordExportUtil = new WordExportUtil();
        investigateEvidence = this.get(investigateEvidence.getInvestigateEvidenceId());
        this.respondent(investigateEvidence);
        String path = request.getServletContext().getRealPath("/");
        String modelPath = path;
        String returnPath="";
        String newFileName = "无标题文件.docx";
        String savaPath=path;
        String pdfPath=path;
        Map<String, Object> params = new HashMap<String, Object>();
        //判断有无案件编号
        String num=null;
        if(investigateEvidence.getComplaintMain()!=null){
            num=investigateEvidence.getComplaintMain().getCaseNumber()==null?"":investigateEvidence.getComplaintMain().getCaseNumber()+"/";
        }else{
            num="";
        }
        if ("patientTake".equals(export)) {
            List<RespondentInfo> respondentInfoOne = respondentInfoDao.getL(investigateEvidence.getInvestigateEvidenceId());
            if(respondentInfoOne.size()==1){
                params.put("date", investigateEvidence.getStartTime()==null?"":investigateEvidence.getStartTime());//开始时间
                params.put("time", investigateEvidence.getEndTime()==null?"":investigateEvidence.getEndTime());//结束时间
                params.put("address", investigateEvidence.getAddress() ==null?"":DictUtils.getDictLabel(investigateEvidence.getAddress(),"meeting",""));//地点
                params.put("cause", investigateEvidence.getCause()==null?"":investigateEvidence.getCause());//事由
                params.put("investigators", investigateEvidence.getInvestigator()==null?"":investigateEvidence.getInvestigator());//调查人
                params.put("noteTaker", investigateEvidence.getNoteTaker()==null?"":investigateEvidence.getNoteTaker());//调查记录人
                //被调查人1
                if(investigateEvidence.getRespondentInfo()!=null){
                    // 身份
                    if("hz".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())){
                        params.put("respondentIdentity","患者本人");
                    }else if("hzxdjm".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())){
                        params.put("respondentIdentity","患者兄弟姐妹");
                    }else if("hzfq".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())){
                        params.put("respondentIdentity","患者夫妻");
                    }else if("hparent".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())){
                        params.put("respondentIdentity","患者父母");
                    }else if("hzn".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())){
                        params.put("respondentIdentity","患者子女");
                    }else if("hzqs".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())){
                        params.put("respondentIdentity","患者亲属");
                    }else if("other".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())){
                        params.put("respondentIdentity","其他人员");
                    }else if("hfdlr".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())){
                        params.put("respondentIdentity","患方代理人");
                    }else if("jzys".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())){
                        params.put("respondentIdentity","经治医师");
                    }else if("kszr".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())){
                        params.put("respondentIdentity","科室主任");
                    }else if("yfdl".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())){
                        params.put("respondentIdentity","医方代理");
                    }else {
                        params.put("respondentIdentity","");
                    }
                    if(StringUtils.isNotBlank(investigateEvidence.getRespondentInfo().getRespondentName())){
                        params.put("name",investigateEvidence.getRespondentInfo().getRespondentName());//姓名
                    }else{
                        params.put("name","");//姓名
                    }

                    //性别
                    if("1".equals(investigateEvidence.getRespondentInfo().getRespondentSex())){
                        params.put("sex","男");
                    }else if("2".equals(investigateEvidence.getRespondentInfo().getRespondentSex())){
                        params.put("sex","女");
                    }else{
                        params.put("sex","");
                    }
                    if(StringUtils.isNotBlank(investigateEvidence.getRespondentInfo().getRespondentAge())){
                        params.put("age",investigateEvidence.getRespondentInfo().getRespondentAge());//年龄
                    }else{
                        params.put("age","");//年龄
                    }
                    if(StringUtils.isNotBlank(investigateEvidence.getRespondentInfo().getRespondentMobile())){
                        params.put("phone",investigateEvidence.getRespondentInfo().getRespondentMobile());//联系方式
                    }else{
                        params.put("phone","");//联系方式
                    }
                    if(StringUtils.isNotBlank(investigateEvidence.getRespondentInfo().getRespondentWorkUnit())){
                        params.put("respondentWorkUnit",investigateEvidence.getRespondentInfo().getRespondentWorkUnit());//工作单位
                    }else{
                        params.put("respondentWorkUnit","");//工作单位
                    }
                    if(StringUtils.isNotBlank(investigateEvidence.getRespondentInfo().getRespondentPost())){
                        params.put("post",investigateEvidence.getRespondentInfo().getRespondentPost());//职务
                    }else{
                        params.put("post","");//职务
                    }

                }else{
                    params.put("respondentIdentity","");
                    params.put("name","");//姓名
                    params.put("sex","");
                    params.put("age","");//年龄
                    params.put("phone","");//联系方式
                    params.put("respondentWorkUnit","");//工作单位
                    params.put("post","");//职务
                }


                params.put("content",investigateEvidence.getContent());//笔录内容

                path += "/doc/recordOne.docx";  //模板文件位置
                modelPath += "/doc/recordOne.docx";
                savaPath +="/userfiles/investigateEvidence/"+num+"recordOne.docx";
                pdfPath +="/userfiles/investigateEvidence/"+num+"recordOne.pdf";
                returnPath="/userfiles/investigateEvidence/"+num+"recordOne.pdf";
                newFileName = "患方笔录.docx";
            }else {
                params.put("date", investigateEvidence.getStartTime() == null ? "" : investigateEvidence.getStartTime());//开始时间
                params.put("time", investigateEvidence.getEndTime() == null ? "" : investigateEvidence.getEndTime());//结束时间
                params.put("address", investigateEvidence.getAddress() == null ? "" : DictUtils.getDictLabel(investigateEvidence.getAddress(),"meeting",""));//地点
                params.put("cause", investigateEvidence.getCause() == null ? "" : investigateEvidence.getCause());//事由
                params.put("investigators", investigateEvidence.getInvestigator() == null ? "" : investigateEvidence.getInvestigator());//调查人
                params.put("noteTaker", investigateEvidence.getNoteTaker() == null ? "" : investigateEvidence.getNoteTaker());//调查记录人
                //被调查人1
                if (investigateEvidence.getRespondentInfo() != null) {
                    // 身份
                    if ("hz".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())) {
                        params.put("respondentIdentity", "患者本人");
                    } else if ("hzxdjm".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())) {
                        params.put("respondentIdentity", "患者兄弟姐妹");
                    } else if ("hzfq".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())) {
                        params.put("respondentIdentity", "患者夫妻");
                    } else if ("hparent".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())) {
                        params.put("respondentIdentity", "患者父母");
                    } else if ("hzn".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())) {
                        params.put("respondentIdentity", "患者子女");
                    } else if ("hzqs".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())) {
                        params.put("respondentIdentity", "患者亲属");
                    } else if ("other".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())) {
                        params.put("respondentIdentity", "其他人员");
                    } else if ("hfdlr".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())) {
                        params.put("respondentIdentity", "患方代理人");
                    } else if ("jzys".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())) {
                        params.put("respondentIdentity", "经治医师");
                    } else if ("kszr".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())) {
                        params.put("respondentIdentity", "科室主任");
                    } else if ("yfdl".equals(investigateEvidence.getRespondentInfo().getRespondentIdentity())) {
                        params.put("respondentIdentity", "医方代理");
                    } else {
                        params.put("respondentIdentity", "");
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo().getRespondentName())) {
                        params.put("name", investigateEvidence.getRespondentInfo().getRespondentName());//姓名
                    } else {
                        params.put("name", "");//姓名
                    }

                    //性别
                    if ("1".equals(investigateEvidence.getRespondentInfo().getRespondentSex())) {
                        params.put("sex", "男");
                    } else if ("2".equals(investigateEvidence.getRespondentInfo().getRespondentSex())) {
                        params.put("sex", "女");
                    } else {
                        params.put("sex", "");
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo().getRespondentAge())) {
                        params.put("age", investigateEvidence.getRespondentInfo().getRespondentAge());//年龄
                    } else {
                        params.put("age", "");//年龄
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo().getRespondentMobile())) {
                        params.put("phone", investigateEvidence.getRespondentInfo().getRespondentMobile());//联系方式
                    } else {
                        params.put("phone", "");//联系方式
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo().getRespondentWorkUnit())) {
                        params.put("respondentWorkUnit", investigateEvidence.getRespondentInfo().getRespondentWorkUnit());//工作单位
                    } else {
                        params.put("respondentWorkUnit", "");//工作单位
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo().getRespondentPost())) {
                        params.put("post", investigateEvidence.getRespondentInfo().getRespondentPost());//职务
                    } else {
                        params.put("post", "");//职务
                    }

                } else {
                    params.put("respondentIdentity", "");
                    params.put("name", "");//姓名
                    params.put("sex", "");
                    params.put("age", "");//年龄
                    params.put("phone", "");//联系方式
                    params.put("respondentWorkUnit", "");//工作单位
                    params.put("post", "");//职务
                }

                params.put("content", investigateEvidence.getContent());//笔录内容
                //被调查人2
                if (investigateEvidence.getRespondentInfo2() != null) {
                    // 身份
                    if ("hz".equals(investigateEvidence.getRespondentInfo2().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "患者本人");
                    } else if ("hzxdjm".equals(investigateEvidence.getRespondentInfo2().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "患者兄弟姐妹");
                    } else if ("hzfq".equals(investigateEvidence.getRespondentInfo2().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "患者夫妻");
                    } else if ("hparent".equals(investigateEvidence.getRespondentInfo2().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "患者父母");
                    } else if ("hzn".equals(investigateEvidence.getRespondentInfo2().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "患者子女");
                    } else if ("hzqs".equals(investigateEvidence.getRespondentInfo2().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "患者亲属");
                    } else if ("other".equals(investigateEvidence.getRespondentInfo2().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "其他人员");
                    } else if ("hfdlr".equals(investigateEvidence.getRespondentInfo2().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "患方代理人");
                    } else if ("jzys".equals(investigateEvidence.getRespondentInfo2().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "经治医师");
                    } else if ("kszr".equals(investigateEvidence.getRespondentInfo2().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "科室主任");
                    } else if ("yfdl".equals(investigateEvidence.getRespondentInfo2().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "医方代理");
                    } else {
                        params.put("respondentIdentity1", "");
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo2().getRespondentName())) {
                        params.put("name1", investigateEvidence.getRespondentInfo2().getRespondentName());//姓名
                    } else {
                        params.put("name1", "");//姓名
                    }

                    //性别
                    if ("1".equals(investigateEvidence.getRespondentInfo2().getRespondentSex())) {
                        params.put("sex1", "男");
                    } else if ("2".equals(investigateEvidence.getRespondentInfo2().getRespondentSex())) {
                        params.put("sex1", "女");
                    } else {
                        params.put("sex1", "");
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo2().getRespondentAge())) {
                        params.put("age1", investigateEvidence.getRespondentInfo2().getRespondentAge());//年龄
                    } else {
                        params.put("age1", "");//年龄
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo2().getRespondentMobile())) {
                        params.put("phone1", investigateEvidence.getRespondentInfo2().getRespondentMobile());//联系方式
                    } else {
                        params.put("phone1", "");//联系方式
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo2().getRespondentWorkUnit())) {
                        params.put("respondentWorkUnit1", investigateEvidence.getRespondentInfo2().getRespondentWorkUnit());//工作单位
                    } else {
                        params.put("respondentWorkUnit1", "");//工作单位
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo2().getRespondentPost())) {
                        params.put("post1", investigateEvidence.getRespondentInfo2().getRespondentPost());//职务
                    } else {
                        params.put("post1", "");//职务
                    }

                } else {
                    params.put("respondentIdentity1", "");
                    params.put("name1", "");//姓名
                    params.put("sex1", "");
                    params.put("age1", "");//年龄
                    params.put("phone1", "");//联系方式
                    params.put("respondentWorkUnit1", "");//工作单位
                    params.put("post1", "");//职务
                }

//            params.put("content1",investigateEvidence.getContent()==null?"":investigateEvidence.getContent());//笔录内容
                path += "/doc/record.docx";  //模板文件位置
                modelPath += "/doc/record.docx";
                savaPath += "/userfiles/investigateEvidence/" + num + "record.docx";
                pdfPath += "/userfiles/investigateEvidence/" + num + "record.pdf";
                returnPath = "/userfiles/investigateEvidence/" + num + "record.pdf";
                newFileName = "患方笔录.docx";
            }
        } else if ("hospitalTake".equals(export)) {
            List<RespondentInfo> respondentInfoOne = respondentInfoDao.getL(investigateEvidence.getInvestigateEvidence().getInvestigateEvidenceId());
            if (respondentInfoOne.size() == 1) {
                params.put("date", investigateEvidence.getInvestigateEvidence().getStartTime() == null ? "" : investigateEvidence.getInvestigateEvidence().getStartTime());//开始时间
                params.put("time", investigateEvidence.getInvestigateEvidence().getEndTime() == null ? "" : investigateEvidence.getInvestigateEvidence().getEndTime());//结束时间
                params.put("address", investigateEvidence.getInvestigateEvidence().getAddress() == null ? "" : DictUtils.getDictLabel(investigateEvidence.getInvestigateEvidence().getAddress(),"meeting",""));//地点
                params.put("cause", investigateEvidence.getInvestigateEvidence().getCause() == null ? "" : investigateEvidence.getInvestigateEvidence().getCause());//事由
                params.put("investigators", investigateEvidence.getInvestigateEvidence().getInvestigator() == null ? "" : investigateEvidence.getInvestigateEvidence().getInvestigator());//调查人
                params.put("noteTaker", investigateEvidence.getInvestigateEvidence().getNoteTaker() == null ? "" : investigateEvidence.getInvestigateEvidence().getNoteTaker());//调查记录人
                //医方调查人1
                if (investigateEvidence.getRespondentInfo3() != null) {
                    // 身份
                    if ("hz".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "患者本人");
                    } else if ("hzxdjm".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "患者兄弟姐妹");
                    } else if ("hzfq".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "患者夫妻");
                    } else if ("hparent".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "患者父母");
                    } else if ("hzn".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "患者子女");
                    } else if ("hzqs".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "患者亲属");
                    } else if ("other".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "其他人员");
                    } else if ("hfdlr".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "患方代理人");
                    } else if ("jzys".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "经治医师");
                    } else if ("kszr".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "科室主任");
                    } else if ("yfdl".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "医方代理");
                    } else {
                        params.put("respondentIdentity", "");
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo3().getRespondentName())) {
                        params.put("name", investigateEvidence.getRespondentInfo3().getRespondentName());//姓名
                    } else {
                        params.put("name", "");//姓名
                    }

                    //性别
                    if ("1".equals(investigateEvidence.getRespondentInfo3().getRespondentSex())) {
                        params.put("sex", "男");
                    } else if ("2".equals(investigateEvidence.getRespondentInfo3().getRespondentSex())) {
                        params.put("sex", "女");
                    } else {
                        params.put("sex", "");
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo3().getRespondentAge())) {
                        params.put("age", investigateEvidence.getRespondentInfo3().getRespondentAge());//年龄
                    } else {
                        params.put("age", "");//年龄
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo3().getRespondentMobile())) {
                        params.put("phone", investigateEvidence.getRespondentInfo3().getRespondentMobile());//联系方式
                    } else {
                        params.put("phone", "");//联系方式
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo3().getRespondentWorkUnit())) {
                        params.put("respondentWorkUnit", investigateEvidence.getRespondentInfo3().getRespondentWorkUnit());//工作单位
                    } else {
                        params.put("respondentWorkUnit", "");//工作单位
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo3().getRespondentPost())) {
                        params.put("post", investigateEvidence.getRespondentInfo3().getRespondentPost());//职务
                    } else {
                        params.put("post", "");//职务
                    }

                } else {
                    params.put("respondentIdentity", "");
                    params.put("name", "");//姓名
                    params.put("sex", "");
                    params.put("age", "");//年龄
                    params.put("phone", "");//联系方式
                    params.put("respondentWorkUnit", "");//工作单位
                    params.put("post", "");//职务
                }
                params.put("content", investigateEvidence.getInvestigateEvidence().getContent());//笔录内容
                path += "/doc/recordOne.docx";  //模板文件位置
                modelPath += "/doc/recordOne.docx";
                savaPath += "/userfiles/investigateEvidence/" + num + "recordOne.docx";
                pdfPath += "/userfiles/investigateEvidence/" + num + "recordOne.pdf";
                returnPath = "/userfiles/investigateEvidence/" + num + "recordOne.pdf";
                newFileName = "医方笔录.docx";
            } else {
                params.put("date", investigateEvidence.getInvestigateEvidence().getStartTime() == null ? "" : investigateEvidence.getInvestigateEvidence().getStartTime());//开始时间
                params.put("time", investigateEvidence.getInvestigateEvidence().getEndTime() == null ? "" : investigateEvidence.getInvestigateEvidence().getEndTime());//结束时间
                params.put("address", investigateEvidence.getInvestigateEvidence().getAddress() == null ? "" : DictUtils.getDictLabel(investigateEvidence.getInvestigateEvidence().getAddress(),"meeting",""));//地点
                params.put("cause", investigateEvidence.getInvestigateEvidence().getCause() == null ? "" : investigateEvidence.getInvestigateEvidence().getCause());//事由
                params.put("investigators", investigateEvidence.getInvestigateEvidence().getInvestigator() == null ? "" : investigateEvidence.getInvestigateEvidence().getInvestigator());//调查人
                params.put("noteTaker", investigateEvidence.getInvestigateEvidence().getNoteTaker() == null ? "" : investigateEvidence.getInvestigateEvidence().getNoteTaker());//调查记录人
                //医方调查人1
                if (investigateEvidence.getRespondentInfo3() != null) {
                    // 身份
                    if ("hz".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "患者本人");
                    } else if ("hzxdjm".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "患者兄弟姐妹");
                    } else if ("hzfq".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "患者夫妻");
                    } else if ("hparent".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "患者父母");
                    } else if ("hzn".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "患者子女");
                    } else if ("hzqs".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "患者亲属");
                    } else if ("other".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "其他人员");
                    } else if ("hfdlr".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "患方代理人");
                    } else if ("jzys".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "经治医师");
                    } else if ("kszr".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "科室主任");
                    } else if ("yfdl".equals(investigateEvidence.getRespondentInfo3().getRespondentIdentity())) {
                        params.put("respondentIdentity", "医方代理");
                    } else {
                        params.put("respondentIdentity", "");
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo3().getRespondentName())) {
                        params.put("name", investigateEvidence.getRespondentInfo3().getRespondentName());//姓名
                    } else {
                        params.put("name", "");//姓名
                    }

                    //性别
                    if ("1".equals(investigateEvidence.getRespondentInfo3().getRespondentSex())) {
                        params.put("sex", "男");
                    } else if ("2".equals(investigateEvidence.getRespondentInfo3().getRespondentSex())) {
                        params.put("sex", "女");
                    } else {
                        params.put("sex", "");
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo3().getRespondentAge())) {
                        params.put("age", investigateEvidence.getRespondentInfo3().getRespondentAge());//年龄
                    } else {
                        params.put("age", "");//年龄
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo3().getRespondentMobile())) {
                        params.put("phone", investigateEvidence.getRespondentInfo3().getRespondentMobile());//联系方式
                    } else {
                        params.put("phone", "");//联系方式
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo3().getRespondentWorkUnit())) {
                        params.put("respondentWorkUnit", investigateEvidence.getRespondentInfo3().getRespondentWorkUnit());//工作单位
                    } else {
                        params.put("respondentWorkUnit", "");//工作单位
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo3().getRespondentPost())) {
                        params.put("post", investigateEvidence.getRespondentInfo3().getRespondentPost());//职务
                    } else {
                        params.put("post", "");//职务
                    }

                } else {
                    params.put("respondentIdentity", "");
                    params.put("name", "");//姓名
                    params.put("sex", "");
                    params.put("age", "");//年龄
                    params.put("phone", "");//联系方式
                    params.put("respondentWorkUnit", "");//工作单位
                    params.put("post", "");//职务
                }
                params.put("content", investigateEvidence.getInvestigateEvidence().getContent());//笔录内容
                //医方被调查人2
                if (investigateEvidence.getRespondentInfo4() != null) {
                    // 身份
                    if ("hz".equals(investigateEvidence.getRespondentInfo4().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "患者本人");
                    } else if ("hzxdjm".equals(investigateEvidence.getRespondentInfo4().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "患者兄弟姐妹");
                    } else if ("hzfq".equals(investigateEvidence.getRespondentInfo4().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "患者夫妻");
                    } else if ("hparent".equals(investigateEvidence.getRespondentInfo4().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "患者父母");
                    } else if ("hzn".equals(investigateEvidence.getRespondentInfo4().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "患者子女");
                    } else if ("hzqs".equals(investigateEvidence.getRespondentInfo4().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "患者亲属");
                    } else if ("other".equals(investigateEvidence.getRespondentInfo4().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "其他人员");
                    } else if ("hfdlr".equals(investigateEvidence.getRespondentInfo4().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "患方代理人");
                    } else if ("jzys".equals(investigateEvidence.getRespondentInfo4().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "经治医师");
                    } else if ("kszr".equals(investigateEvidence.getRespondentInfo4().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "科室主任");
                    } else if ("yfdl".equals(investigateEvidence.getRespondentInfo4().getRespondentIdentity())) {
                        params.put("respondentIdentity1", "医方代理");
                    } else {
                        params.put("respondentIdentity1", "");
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo4().getRespondentName())) {
                        params.put("name1", investigateEvidence.getRespondentInfo4().getRespondentName());//姓名
                    } else {
                        params.put("name1", "");//姓名
                    }

                    //性别
                    if ("1".equals(investigateEvidence.getRespondentInfo4().getRespondentSex())) {
                        params.put("sex1", "男");
                    } else if ("2".equals(investigateEvidence.getRespondentInfo4().getRespondentSex())) {
                        params.put("sex1", "女");
                    } else {
                        params.put("sex1", "");
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo4().getRespondentAge())) {
                        params.put("age1", investigateEvidence.getRespondentInfo4().getRespondentAge());//年龄
                    } else {
                        params.put("age1", "");//年龄
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo4().getRespondentMobile())) {
                        params.put("phone1", investigateEvidence.getRespondentInfo4().getRespondentMobile());//联系方式
                    } else {
                        params.put("phone1", "");//联系方式
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo4().getRespondentWorkUnit())) {
                        params.put("respondentWorkUnit1", investigateEvidence.getRespondentInfo4().getRespondentWorkUnit());//工作单位
                    } else {
                        params.put("respondentWorkUnit1", "");//工作单位
                    }
                    if (StringUtils.isNotBlank(investigateEvidence.getRespondentInfo4().getRespondentPost())) {
                        params.put("post1", investigateEvidence.getRespondentInfo4().getRespondentPost());//职务
                    } else {
                        params.put("post1", "");//职务
                    }

                } else {
                    params.put("respondentIdentity1", "");
                    params.put("name1", "");//姓名
                    params.put("sex1", "");
                    params.put("age1", "");//年龄
                    params.put("phone1", "");//联系方式
                    params.put("respondentWorkUnit1", "");//工作单位
                    params.put("post1", "");//职务
                }
//            params.put("content1",investigateEvidence.getInvestigateEvidence().getContent()==null?"":investigateEvidence.getInvestigateEvidence().getContent());//笔录内容
                path += "/doc/record.docx";  //模板文件位置
                modelPath += "/doc/record.docx";
                savaPath += "/userfiles/investigateEvidence/" + num + "record.docx";
                pdfPath += "/userfiles/investigateEvidence/" + num + "record.pdf";
                returnPath = "/userfiles/investigateEvidence/" + num + "record.pdf";
                newFileName = "医方笔录.docx";
            }
        }
        try{
            File file =new File(request.getServletContext().getRealPath("/")+"/userfiles/investigateEvidence/"+num);
            if (!file.exists()){
                file.mkdirs();
            }
            List<String[]> testList = new ArrayList<String[]>();
            String fileName= new String(newFileName.getBytes("UTF-8"),"iso-8859-1");    //生成word文件的文件名
            wordExportUtil.getWord(path,modelPath,savaPath,print,params,testList,fileName,response);
            wordExportUtil.doc2pdf(savaPath,new FileOutputStream(pdfPath));
            System.out.println("转pdf成功");
//			if (StringUtils.isNotBlank(printName)){
            //wordExportUtil.wToPdfChange(savaPath,pdfPath);
            //wordExportUtil.PDFprint(new File(pdfPath),printName);
//			}
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnPath;
    }
    //保存附件
    public void savefj(HttpServletRequest request,InvestigateEvidence investigateEvidence){
        //从前台获取到的附件
        String files1 = request.getParameter("files1");
        String files2 = request.getParameter("files2");
        String files3 = request.getParameter("files3");
        String files4 = request.getParameter("files4");
        //附件的主键
        String acceId = null;
        //从前台获取附件类型
        String fjtype4 = request.getParameter("fjtype4");
        String fjtype3 = request.getParameter("fjtype3");
        String fjtype2 = request.getParameter("fjtype2");
        String fjtype1 = request.getParameter("fjtype1");
        //itemId1关联调查取证表的主键
        String itemId = investigateEvidence.getInvestigateEvidenceId();
        if(StringUtils.isNotBlank(files1)){
            String acceId1=request.getParameter("acceId1");
            if(StringUtils.isNotBlank(acceId1)){
                preOperativeConsentService.updatefj(files1,itemId,fjtype1);
            }else{
                acceId=IdGen.uuid();
                preOperativeConsentService.save1(acceId,itemId,files1,fjtype1);
            }
        }else{
            preOperativeConsentService.delefj(itemId,fjtype1);
        }
        if(StringUtils.isNotBlank(files2)){
            String acceId2=request.getParameter("acceId2");
            if(StringUtils.isNotBlank(acceId2)){
                preOperativeConsentService.updatefj(files2,itemId,fjtype2);
            }else{
                acceId = IdGen.uuid();
                preOperativeConsentService.save1(acceId,itemId,files2,fjtype2);
            }
        }else{
            preOperativeConsentService.delefj(itemId,fjtype2);
        }
        if(StringUtils.isNotBlank(files3)){
            String acceId3=request.getParameter("acceId3");
            if(StringUtils.isNotBlank(acceId3)){
                preOperativeConsentService.updatefj(files3,itemId,fjtype3);
            }else{
                acceId = IdGen.uuid();
                preOperativeConsentService.save1(acceId,itemId,files3,fjtype3);
            }
        }else{
            preOperativeConsentService.delefj(itemId,fjtype3);
        }
        if(StringUtils.isNotBlank(files4)){
            String acceId4=request.getParameter("acceId4");
            if(StringUtils.isNotBlank(acceId4)){
                preOperativeConsentService.updatefj(files4,itemId,fjtype4);
            }else{
                acceId = IdGen.uuid();
                preOperativeConsentService.save1(acceId,itemId,files4,fjtype4);
            }
        }else{
            preOperativeConsentService.delefj(itemId,fjtype4);
        }
    }
}