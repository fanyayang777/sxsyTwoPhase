<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>案件总结详情</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            //投诉接待详情
            $("#tsjdDetail").attr("src","${ctx}/complaintdetail/complaintMainDetail/form?id=${map.tsjd}&type=view");
            var tsjd= document.getElementById("tsjdDetail");
            tsjd.height=document.documentElement.clientHeight-130;
            tsjd.width=document.documentElement.clientWidth;
            var show='${show2}';
            if(show=='' || show== null){
            //报案登记
            $("#badjDetail").attr("src","${ctx}/registration/reportRegistration/form?id=${map.badj}&type=view&show2=y");
            var badj= document.getElementById("badjDetail");
            badj.height=document.documentElement.clientHeight-130;
            badj.width=document.documentElement.clientWidth;
            //审核受理
            $("#shslDetail").attr("src","${ctx}/auditacceptance/auditAcceptance/form?id=${map.shsl}&type=view&show2=y");
            var shsl= document.getElementById("shslDetail");
            shsl.height=document.documentElement.clientHeight-130;
            shsl.width=document.documentElement.clientWidth;
            //调查取证
            $("#dcqzDetail").attr("src","${ctx}/nestigateeividence/investigateEvidence/form?id=${map.dcqz}&type=view&show2=y");
            var dcqz= document.getElementById("dcqzDetail");
            dcqz.height=document.documentElement.clientHeight-130;
            dcqz.width=document.documentElement.clientWidth;
            //质证调解
            $("#zztjDetail").attr("src","${ctx}/mediate/mediateEvidence/form?id=${map.zztj}&type=view&show2=y");
            var zztj= document.getElementById("zztjDetail");
            zztj.height=document.documentElement.clientHeight-130;
            zztj.width=document.documentElement.clientWidth;
            //评估坚定申请
            <%--$("#pgjdsqDetail").attr("src","${ctx}/assessapply/assessApply/form?id=${map.pgjdsq}&type=view&show2=y");--%>
            <%--var pgjdsq= document.getElementById("pgjdsqDetail");--%>
            <%--pgjdsq.height=document.documentElement.clientHeight-130;--%>
            <%--pgjdsq.width=document.documentElement.clientWidth;--%>
            //评估鉴定审批
            <%--$("#pgjdspDetail").attr("src","${ctx}/assessaudit/assessAudit/form?id=${map.pgjdsq}&type=view&show2=y");--%>
            <%--var pgjdsp= document.getElementById("pgjdspDetail");--%>
            <%--pgjdsp.height=document.documentElement.clientHeight-130;--%>
            <%--pgjdsp.width=document.documentElement.clientWidth;--%>
            //评估鉴定
            $("#pgjdDetail").attr("src","${ctx}/assessappraisal/assessAppraisal/form?id=${map.pgjd}&type=view&show2=y");
            var pgjd= document.getElementById("pgjdDetail");
            pgjd.height=document.documentElement.clientHeight-130;
            pgjd.width=document.documentElement.clientWidth;
            //达成调解
            $("#dctjDetail").attr("src","${ctx}/reachmediate/reachMediate/form?id=${map.dctj}&type=view&show2=y");
            var dctj= document.getElementById("dctjDetail");
            dctj.height=document.documentElement.clientHeight-130;
            dctj.width=document.documentElement.clientWidth;
            //签署协议
            $("#qsxyDetail").attr("src","${ctx}/sign/signAgreement/form?id=${map.qsxy}&type=view&show2=y");
            var qsxy= document.getElementById("qsxyDetail");
            qsxy.height=document.documentElement.clientHeight-130;
            qsxy.width=document.documentElement.clientWidth;
            //履行协议
            $("#lxxyDetail").attr("src","${ctx}/perform/performAgreement/form?id=${map.lxxy}&type=view&show2=y");
            var lxxy= document.getElementById("lxxyDetail");
            lxxy.height=document.documentElement.clientHeight-130;
            lxxy.width=document.documentElement.clientWidth;
            }
        });
    </script>
</head>
<body>
<br/>
<form:form id="inputForm" modelAttribute="summaryInfo" class="form-horizontal">
    <sys:message content="${message}"/>
    <fieldset>
        <legend>案件总结详情</legend>
        <ul id="myTab" class="nav nav-tabs">
            <li class="active">
                <a href="#Summary" data-toggle="tab">案件总结</a>
            </li>
            <li>
                <a href="#attachment" data-toggle="tab">补录附件</a>
            </li>
            <c:if test="${empty show2}">
            <li>
                <a href="#details" data-toggle="tab">详情</a>
            </li>
            </c:if>
        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="Summary">
                <table class="table-form">
                    <tr>
                        <td class="tit" style="text-align: center;" width="13%">受理时间:</td>
                        <td width="20%" style="text-align: center;">
                                ${summaryInfo.acceptanceTime}
                        </td>
                        <td class="tit" style="text-align: center;" width="13%" >结案时间:</td>
                        <td width="20%" style="text-align: center;">
                                ${summaryInfo.ratifyAccord}
                        </td>
                        <td class="tit" style="text-align: center;" width="13%">调解天数:</td>
                        <td style="text-align: center;">
                                ${summaryInfo.flowDays}
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;">责任度:</td>
                        <td style="text-align: center;">
                                ${summaryInfo.responsibilityRatio}
                        </td>
                        <td style="text-align: center;" >调解次数:</td>
                        <td style="text-align: center;">
                                ${summaryInfo.meetingFrequency}
                        </td>
                        <td style="text-align: center;" >调解结果:</td>
                        <td style="text-align: center;">
                                ${summaryInfo.mediateResult=='1' ? '成功' : summaryInfo.mediateResult=='2' ? '终止' : summaryInfo.mediateResult=='3' ? '销案' :''}
                        </td>
                    </tr>
                    <tr>
                        <td class="tit" style="text-align: center;">卷宗编号:</td>
                        <td style="text-align: center;">
                                ${summaryInfo.fileNumber}
                        </td>
                        <td style="text-align: center;">归档时间:</td>
                        <td style="text-align: center;">
                            <fmt:formatDate value="${empty summaryInfo.updateDate ? now : summaryInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td class="tit" style="text-align: center;">总结人:</td>
                        <td style="text-align: center;">
                            ${fns:getUserById(summaryInfo.createBy).name}
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;" >调解经过:</td>
                        <td colspan="5">
                            <form:textarea path="mediatePass" htmlEscape="false" rows="4"  cssStyle="width: 95%;" disabled="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;" >经验总结:</td>
                        <td colspan="5">
                            <form:textarea path="summary" htmlEscape="false" rows="4" disabled="true" cssStyle="width: 95%;"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;" >其他:</td>
                        <td colspan="5">
                            <form:textarea path="other" htmlEscape="false" rows="4"  cssStyle="width: 95%;" disabled="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tit" style="text-align: center;" >总结时间:</td>
                        <td colspan="2" style="text-align: center;">
                            <c:set var="now" value="<%=new java.util.Date()%>" />
                            <c:choose>
                                <c:when test="${empty summaryInfo.summaryTime}">
                                    <fmt:formatDate value="${empty summaryInfo.createDate ? now : summaryInfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </c:when>
                                <c:otherwise>
                                    ${summaryInfo.summaryTime}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="text-align: center;" >下一步处理人:</td>
                        <td colspan="2" style="text-align: center;">
                                ${summaryInfo.linkEmployee.name}
                        </td>
                    </tr>
                </table>
            </div>
            <div class="tab-pane fade" id="attachment">
                <ul id="myTab1" class="nav nav-tabs">
                    <li class="active">
                        <a href="#huanfang" data-toggle="tab">患方相关附件</a>
                    </li>
                    <li>
                        <a href="#yifang" data-toggle="tab">医方相关附件</a>
                    </li>
                </ul>
                <div id="myTabContent1" class="tab-content">
                    <div class="tab-pane fade in active" id="huanfang">
                        <table class="table-form">
                            <tr>
                                <input type="hidden" name="fjtype1" value="25">
                                <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                    患方调解申请：
                                </td>
                                <td style="width: 450px; ">
                                    <input type="hidden" id="files1" name="files1" htmlEscape="false"
                                           class="input-xlarge"
                                           value="${files1}"/>
                                    <input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
                                        <%--<form:hidden id="nameImage" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>--%>
                                        <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                                    <div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files"
                                                                                  uploadPath="/summaryinfo/summaryInfo/huan/mediation"
                                                                                  selectMultiple="false"
                                                                                  maxWidth="100" maxHeight="100"
                                                                                  readonly="true"/></div>
                                </td>
                            </tr>
                            <tr style=" ">
                                <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                    医疗纠纷材料：
                                </td>
                                <input type="hidden" name="fjtype2" value="26">
                                <td style="width: 450px; ">
                                    <input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">
                                    <input type="hidden" id="files2" name="files2" htmlEscape="false"
                                           class="input-xlarge" value="${files2}"/>
                                    <div style="margin-top: -45px;"><sys:ckfinder input="files2" type="files"
                                                                                  uploadPath="/summaryinfo/summaryInfo/huan/medicalDisputes"
                                                                                  selectMultiple="true"
                                                                                  readonly="true"/></div>
                                </td>
                            </tr>
                            <tr style=" ">
                                <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                    当事人身份相关证件：
                                </td>
                                <input type="hidden" name="fjtype3" value="27">
                                <td style="width: 450px; ">
                                    <input type="hidden" id="acceId3" name="acceId3" value="${acceId3}">
                                    <input type="hidden" id="files3" name="files3" htmlEscape="false"
                                           class="input-xlarge" value="${files3}"/>
                                    <div style="margin-top: -45px;"><sys:ckfinder input="files3" type="files"
                                                                                  uploadPath="/summaryinfo/summaryInfo/huan/partyDocuments"
                                                                                  selectMultiple="true"
                                                                                  readonly="true"/></div>
                                </td>
                            </tr>
                            <tr style=" ">
                                <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                    授权委托书：
                                </td>
                                <input type="hidden" name="fjtype4" value="28">
                                <td style="width: 450px; ">
                                    <input type="hidden" id="acceId4" name="acceId4" value="${acceId4}">
                                    <input type="hidden" id="files4" name="files4" htmlEscape="false"
                                           class="input-xlarge" value="${files4}"/>
                                    <div style="margin-top: -45px;"><sys:ckfinder input="files4" type="files"
                                                                                  uploadPath="/summaryinfo/summaryInfo/huan/powerOfAttorney"
                                                                                  selectMultiple="true" readonly="true"/></div>
                                </td>
                            </tr>
                            <tr style=" ">
                                <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                    委托人、被委托人相关证件：
                                </td>
                                <input type="hidden" name="fjtype5" value="29">
                                <td style="width: 450px; ">
                                    <input type="hidden" id="acceId5" name="acceId5" value="${acceId5}">
                                    <input type="hidden" id="files5" name="files5" htmlEscape="false"
                                           class="input-xlarge" value="${files5}"/>
                                    <div style="margin-top: -45px;"><sys:ckfinder input="files5" type="files"
                                                                                  uploadPath="/summaryinfo/summaryInfo/huan/PrincipalRelatedDocuments"
                                                                                  selectMultiple="true" readonly="true"/></div>
                                </td>
                            </tr>
                            <tr style=" ">
                                <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                    病例及相关检查资料：
                                </td>
                                <input type="hidden" name="fjtype6" value="30">
                                <td style="width: 450px; ">
                                    <input type="hidden" id="acceId6" name="acceId6" value="${acceId6}">
                                    <input type="hidden" id="files6" name="files6" htmlEscape="false"
                                           class="input-xlarge" value="${files6}"/>
                                    <div style="margin-top: -45px;"><sys:ckfinder input="files6" type="files"
                                                                                  uploadPath="/summaryinfo/summaryInfo/huan/caseInformation"
                                                                                  selectMultiple="true"
                                                                                  readonly="true"/></div>
                                </td>
                            </tr>
                            <tr style=" ">
                                <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                    医疗费用及其他相关费用票据：
                                </td>
                                <input type="hidden" name="fjtype7" value="31">
                                <td style="width: 450px; ">
                                    <input type="hidden" id="acceId7" name="acceId7" value="${acceId7}">
                                    <input type="hidden" id="files7" name="files7" htmlEscape="false"
                                           class="input-xlarge" value="${files7}"/>
                                    <div style="margin-top: -45px;"><sys:ckfinder input="files7" type="files"
                                                                                  uploadPath="/summaryinfo/summaryInfo/huan/feeNotes"
                                                                                  selectMultiple="true"
                                                                                  readonly="true"/></div>
                                </td>
                            </tr>
                            <tr style=" ">
                                <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                    误工证明：
                                </td>
                                <input type="hidden" name="fjtype8" value="32">
                                <td style="width: 450px; ">
                                    <input type="hidden" id="acceId8" name="acceId8" value="${acceId8}">
                                    <input type="hidden" id="files8" name="files8" htmlEscape="false"
                                           class="input-xlarge" value="${files8}"/>
                                    <div style="margin-top: -45px;"><sys:ckfinder input="files8" type="files"
                                                                                  uploadPath="/summaryinfo/summaryInfo/huan/proofOfMissingWork"
                                                                                  selectMultiple="true"
                                                                                  readonly="true"/></div>
                                </td>
                            </tr>
                            <tr style=" ">
                                <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                    患者死亡相关证明：
                                </td>
                                <input type="hidden" name="fjtype9" value="33">
                                <td style="width: 450px; ">
                                    <input type="hidden" id="acceId9" name="acceId9" value="${acceId9}">
                                    <input type="hidden" id="files9" name="files9" htmlEscape="false"
                                           class="input-xlarge" value="${files9}"/>
                                    <div style="margin-top: -45px;"><sys:ckfinder input="files9" type="files"
                                                                                  uploadPath="/summaryinfo/summaryInfo/huan/patientDeathCertificate"
                                                                                  selectMultiple="true"
                                                                                  readonly="true"/></div>
                                </td>
                            </tr>
                            <tr style=" ">
                                <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                    新生儿出生证明：
                                </td>
                                <input type="hidden" name="fjtype10" value="34">
                                <td style="width: 450px; ">
                                    <input type="hidden" id="acceId10" name="acceId10" value="${acceId10}">
                                    <input type="hidden" id="files10" name="files10" htmlEscape="false"
                                           class="input-xlarge" value="${files10}"/>
                                    <div style="margin-top: -45px;"><sys:ckfinder input="files10" type="files"
                                                                                  uploadPath="/summaryinfo/summaryInfo/huan/neonatal"
                                                                                  selectMultiple="true"
                                                                                  readonly="true"/></div>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="tab-pane fade " id="yifang">
                        <table class="table-form">
                            <tr style=" ">
                                <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                    医方调解申请：
                                </td>
                                <input type="hidden" name="fjtype11" value="35">
                                <td style="width: 450px; ">
                                    <input type="hidden" id="acceId11" name="acceId11" value="${acceId11}">
                                    <input type="hidden" id="files11" name="files11" htmlEscape="false"
                                           class="input-xlarge" value="${files11}"/>
                                    <div style="margin-top: -45px;"><sys:ckfinder input="files11" type="files"
                                                                                  uploadPath="/summaryinfo/summaryInfo/yifang/yiMediation"
                                                                                  selectMultiple="true"
                                                                                  readonly="true"/></div>
                                </td>
                            </tr>
                            <tr style=" ">
                                <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                    法定代表人身份证明：
                                </td>
                                <input type="hidden" name="fjtype12" value="36">
                                <td style="width: 450px; ">
                                    <input type="hidden" id="acceId12" name="acceId12" value="${acceId12}">
                                    <input type="hidden" id="files12" name="files12" htmlEscape="false"
                                           class="input-xlarge" value="${files12}"/>
                                    <div style="margin-top: -45px;"><sys:ckfinder input="files12" type="files"
                                                                                  uploadPath="/summaryinfo/summaryInfo/yifang/yiLegalRepresentative"
                                                                                  selectMultiple="true"
                                                                                  readonly="true"/></div>
                                </td>
                            </tr>
                            <tr style=" ">
                                <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                    授权委托书：
                                </td>
                                <input type="hidden" name="fjtype13" value="37">
                                <td style="width: 450px; ">
                                    <input type="hidden" id="acceId13" name="acceId13" value="${acceId13}">
                                    <input type="hidden" id="files13" name="files13" htmlEscape="false"
                                           class="input-xlarge" value="${files13}"/>
                                    <div style="margin-top: -45px;"><sys:ckfinder input="files13" type="files"
                                                                                  uploadPath="/summaryinfo/summaryInfo/yifang/yiPowerOfAttorney"
                                                                                  selectMultiple="true"
                                                                                  readonly="true"/></div>
                                </td>
                            </tr>
                            <tr style=" ">
                                <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                    委托人、被委托人相关证件：
                                </td>
                                <input type="hidden" name="fjtype14" value="38">
                                <td style="width: 450px; ">
                                    <input type="hidden" id="acceId14" name="acceId14" value="${acceId14}">
                                    <input type="hidden" id="files14" name="files14" htmlEscape="false"
                                           class="input-xlarge" value="${files14}"/>
                                    <div style="margin-top: -45px;"><sys:ckfinder input="files14" type="files"
                                                                                  uploadPath="/summaryinfo/summaryInfo/yifang/yiPrincipalDocuments"
                                                                                  selectMultiple="true"
                                                                                  readonly="true"/></div>
                                </td>
                            </tr>
                            <tr style=" ">
                                <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                    执业许可证副本：
                                </td>
                                <input type="hidden" name="fjtype15" value="39">
                                <td style="width: 450px; ">
                                    <input type="hidden" id="acceId15" name="acceId15" value="${acceId15}">
                                    <input type="hidden" id="files15" name="files15" htmlEscape="false"
                                           class="input-xlarge" value="${files15}"/>
                                    <div style="margin-top: -45px;"><sys:ckfinder input="files15" type="files"
                                                                                  uploadPath="/summaryinfo/summaryInfo/yifang/yiLicenseToPractise"
                                                                                  selectMultiple="true"
                                                                                  readonly="true"/></div>
                                </td>
                            </tr>
                            <tr style=" ">
                                <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                    诊疗分析意见：
                                </td>
                                <input type="hidden" name="fjtype16" value="40">
                                <td style="width: 450px; ">
                                    <input type="hidden" id="acceId16" name="acceId16" value="${acceId16}">
                                    <input type="hidden" id="files16" name="files16" htmlEscape="false"
                                           class="input-xlarge" value="${files16}"/>
                                    <div style="margin-top: -45px;"><sys:ckfinder input="files16" type="files"
                                                                                  uploadPath="/summaryinfo/summaryInfo/yifang/yiZhenLiaoAnalysis"
                                                                                  selectMultiple="true"
                                                                                  readonly="true"/></div>
                                </td>
                            </tr>
                            <tr style=" ">
                                <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                    出险医务人员相关证件
                                    （身份证、执业证书）：
                                </td>
                                <input type="hidden" name="fjtype17" value="41">
                                <td style="width: 450px; ">
                                    <input type="hidden" id="acceId17" name="acceId17" value="${acceId17}">
                                    <input type="hidden" id="files17" name="files17" htmlEscape="false"
                                           class="input-xlarge" value="${files17}"/>
                                    <div style="margin-top: -45px;"><sys:ckfinder input="files17" type="files"
                                                                                  uploadPath="/summaryinfo/summaryInfo/yifang/yiChuXianRenDocuments"
                                                                                  selectMultiple="true"
                                                                                  readonly="true"/></div>
                                </td>
                            </tr>
                            <tr style=" ">
                                <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                    相关证据材料：
                                </td>
                                <input type="hidden" name="fjtype18" value="42">
                                <td style="width: 450px; ">
                                    <input type="hidden" id="acceId18" name="acceId18" value="${acceId18}">
                                    <input type="hidden" id="files18" name="files18" htmlEscape="false"
                                           class="input-xlarge" value="${files18}"/>
                                    <div style="margin-top: -45px;"><sys:ckfinder input="files18" type="files"
                                                                                  uploadPath="/summaryinfo/summaryInfo/yifang/yiRelevantEvidence"
                                                                                  selectMultiple="true"
                                                                                  readonly="true"/></div>
                                </td>
                            </tr>
                            <tr style=" ">
                                <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                    病历资料：
                                </td>
                                <input type="hidden" name="fjtype19" value="43">
                                <td style="width: 450px; ">
                                    <input type="hidden" id="acceId19" name="acceId19" value="${acceId19}">
                                    <input type="hidden" id="files19" name="files19" htmlEscape="false"
                                           class="input-xlarge" value="${files19}"/>
                                    <div style="margin-top: -45px;"><sys:ckfinder input="files19" type="files"
                                                                                  uploadPath="/summaryinfo/summaryInfo/yifang/yiMedicalRecords"
                                                                                  selectMultiple="true"
                                                                                  readonly="true"/></div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <div class="tab-pane fade" id="details">
                <ul id="iframe" class="nav nav-tabs">
                    <li class="active">
                        <a href="#tsjd" data-toggle="tab">投诉接待</a>
                    </li>
                    <li>
                        <a href="#badj" data-toggle="tab">报案登记</a>
                    </li>
                    <li>
                        <a href="#shsl" data-toggle="tab">审核受理</a>
                    </li>
                    <li>
                        <a href="#dcqz" data-toggle="tab">调查取证</a>
                    </li>
                    <li>
                        <a href="#zztj" data-toggle="tab">质证调解</a>
                    </li>
                    <%--<li>--%>
                        <%--<a href="#pgjdsq" data-toggle="tab">评估鉴定申请</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="#pgjdsp" data-toggle="tab">评估鉴定审批</a>--%>
                    <%--</li>--%>
                    <li>
                        <a href="#pgjd" data-toggle="tab">评估鉴定</a>
                    </li>
                    <li>
                        <a href="#dctj" data-toggle="tab">达成调解</a>
                    </li>
                    <li>
                        <a href="#qsxy" data-toggle="tab">签署协议</a>
                    </li>
                    <li>
                        <a href="#lxxy" data-toggle="tab">履行协议</a>
                    </li>
                </ul>
                <div id="iframeTabContent" class="tab-content">
                    <div class="tab-pane fade in active" id="tsjd">
                        <iframe id="tsjdDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                    </div>
                    <div class="tab-pane fade" id="badj">
                        <iframe id="badjDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                    </div>
                    <div class="tab-pane fade" id="shsl">
                        <iframe id="shslDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                    </div>
                    <div class="tab-pane fade" id="dcqz">
                        <iframe id="dcqzDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                    </div>
                    <div class="tab-pane fade" id="zztj">
                        <iframe id="zztjDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                    </div>
                    <div class="tab-pane fade" id="pgjdsq">
                        <iframe id="pgjdsqDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                    </div>
                    <div class="tab-pane fade" id="pgjdsp">
                        <iframe id="pgjdspDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                    </div>
                    <div class="tab-pane fade" id="pgjd">
                        <iframe id="pgjdDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                    </div>
                    <div class="tab-pane fade" id="dctj">
                        <iframe id="dctjDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                    </div>
                    <div class="tab-pane fade" id="qsxy">
                        <iframe id="qsxyDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                    </div>
                    <div class="tab-pane fade" id="lxxy">
                        <iframe id="lxxyDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </fieldset>
    <c:if test="${empty show2}">
    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
    <act:histoicFlow procInsId="${summaryInfo.complaintMain.procInsId}" />
    </c:if>
</form:form>
</body>
</html>
