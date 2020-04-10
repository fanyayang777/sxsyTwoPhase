<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>评估鉴定审批管理</title>
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
            $("#pgjdsqDetail").attr("src","${ctx}/assessapply/assessApply/form?id=${map.pgjdsq}&type=view&show2=y");
            var pgjdsq= document.getElementById("pgjdsqDetail");
            pgjdsq.height=document.documentElement.clientHeight-130;
            pgjdsq.width=document.documentElement.clientWidth;
            }
        });
    </script>
</head>
<body>
<br/>
<form:form id="inputForm" modelAttribute="assessAudit" action="${ctx}/assessaudit/assessAudit/save" method="post"
           class="form-horizontal">
    <form:hidden path="assessAuditId"/>
    <form:hidden path="createDate"/>
    <form:hidden path="createBy"/>
    <form:hidden path="complaintMainId"/>
    <form:hidden path="complaintMain.complaintMainId"/>
    <form:hidden path="complaintMain.act.taskId"/>
    <form:hidden path="complaintMain.act.taskName"/>
    <form:hidden path="complaintMain.act.taskDefKey"/>
    <form:hidden path="complaintMain.act.procInsId"/>
    <form:hidden path="complaintMain.act.procDefId"/>
    <form:hidden path="complaintMain.procInsId"/>
    <form:hidden id="flag" path="complaintMain.act.flag"/>
    <sys:message content="${message}"/>
<fieldset>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#pgjdsp" data-toggle="tab">评估鉴定审批</a>
        </li>
        <c:if test="${empty show2}">
        <li>
            <a href="#details" data-toggle="tab">详情</a>
        </li>
        </c:if>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div id="pgjdsp" class="tab-pane fade in active">
    <legend>评估鉴定审批详情</legend>
    <ul id="myTab1" class="nav nav-tabs">
        <li class="active">
            <a href="#patient" data-toggle="tab">申请表（患方）</a>
        </li>
        <li>
            <a href="#hospital" data-toggle="tab">申请表（医方）</a>
        </li>
        <li>
            <a href="#annex" data-toggle="tab">附件</a>
        </li>
    </ul>

    <div id="myTabContent1" class="tab-content">
        <div class="tab-pane fade in active" id="patient">
            <table class="table-form">
                <tr>
                    <td class="tit" width="180px"><font color="red">*</font>申请人：</td>
                    <td width="429px">
                        ${assessAudit.patientApplyer}
                    </td>
                    <td class="tit" width="160px"><font color="red">*</font>与患者关系：</td>
                    <td width="476px">
                            ${fns:getDictLabel(assessAudit.patientRelation,'patient_relation', '无')}
                    </td>
                </tr>
                <tr>
                    <td class="tit" width="180px"><font color="red">*</font>电话：</td>
                    <td width="429px">
                        ${assessAudit.patientMobile}
                    </td>
                    <td class="tit" width="199px">患者姓名：</td>
                    <td width="522px">
                        ${assessAudit.patientName}
                    </td>
                </tr>
                <tr>
                    <td class="tit" width="180px">性别：</td>
                    <td >
                            <%--<form:input path="patientSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
                        <%--<form:select path="patientSex" class="input-medium">--%>
                            <%--<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
                        <%--</form:select>--%>
                                    ${fns:getDictLabel(assessAudit.patientSex,'sex', '无')}
                    </td>
                    <td class="tit">年龄：</td>
                    <td >
                        ${assessAudit.patientAge}
                    </td>
                </tr>
                <tr>
                    <td class="tit" width="199px"><font color="red">*</font>涉及医院：</td>
                    <td width="522px">
                        <%--<sys:treeselect id="involveHospital" name="complaintMain.involveHospital" value="${empty assessAudit.involveHospital ? assessAudit.complaintMain.involveHospital : assessAudit.involveHospital}" labelName="" labelValue="${empty assessAudit.involveHospital ? assessAudit.complaintMain.hospital.name : assessAudit.sjOffice.name}"--%>
                                        <%--title="机构" url="/sys/office/treeData?type=1&officeType=2" cssClass="" isAll="true" allowClear="true" notAllowSelectParent="false"/>--%>
                                ${empty assessAudit.involveHospital ? assessAudit.complaintMain.hospital.name : assessAudit.sjOffice.name}
                    </td>
                </tr>
                <tr>
                    <td class="tit" width="199px"><font color="red">*</font>申请事项：</td>
                    <td width="522px">
                        ${assessAudit.patientApplyMatter}
                    </td>

                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="hospital">
            <table class="table-form">
                <tr>
                    <td class="tit" width="180px"><font color="red">*</font>申请医院：</td>
                    <td width="429px">
                        <%--<sys:treeselect id="involveHospital" name="complaintMain.involveHospital" value="${empty assessAudit.hospitalApply ? assessAudit.complaintMain.involveHospital : assessAudit.hospitalApply}" labelName="" labelValue="${empty assessAudit.hospitalApply ? assessAudit.complaintMain.hospital.name : assessAudit.sqOffice.name}"--%>
                                        <%--title="机构" url="/sys/office/treeData?type=1&officeType=2" cssClass="" isAll="true" allowClear="true" notAllowSelectParent="false"/>--%>
                                ${empty assessAudit.hospitalApply ? assessAudit.complaintMain.hospital.name : assessAudit.sqOffice.name}
                    </td>
                    <td class="tit" width="199px"><font color="red">*</font>代理人：</td>
                    <td>
                        ${assessAudit.agent}
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>电话：</td>
                    <td class="controls">
                        ${assessAudit.hospitalMobile}
                    </td>
                    <td class="tit"><font color="red">*</font>医方姓名：</td>
                    <td class="controls">
                        ${assessAudit.hospitalName}
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>性别：</td>
                    <td class="controls">
                            <%--<form:input path="hospitalSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
                        <%--<form:select path="hospitalSex" class="input-medium">--%>
                            <%--<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
                        <%--</form:select>--%>
                                    ${fns:getDictLabel(assessAudit.hospitalSex,'sex', '无')}
                    </td>
                    <td class="tit"><font color="red">*</font>年龄：</td>
                    <td class="controls">
                        ${assessAudit.hospitalAge}
                    </td>
                </tr>
                <tr>
                    <td class="tit" width="199px"><font color="red">*</font>申请事项：</td>
                    <td width="522px">
                        ${assessAudit.hospitalApplyMatter}
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="annex">
            <table class="table-form">
                <tr style=" " >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">患方申请表：</td>
                    <input type="hidden"  name="fjtype1" value="12">
                    <td style="width: 450px; ">

                        <input type="hidden" id="files1" name="files1" htmlEscape="false" class="input-xlarge"  value="${files1}"/>
                        <input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files"  uploadPath="/assessaudit/assessAudit/Huan" selectMultiple="true" readonly="true"/></div>
                    </td>

                </tr>
                <tr style="" >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医方申请表：</td>
                    <input type="hidden" name="fjtype2" value="13">
                    <td style="width: 450px; ">
                        <input type="hidden" id="files2" name="files2" htmlEscape="false" class="input-xlarge" value="${files2}" />
                        <input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files2" type="files"  uploadPath="/assessaudit/assessAudit/Hospital" selectMultiple="true" readonly="true"/></div>
                    </td>

                </tr>
            </table>
        </div>
    </div>
    <table class="table-form">
        <tr>
            <td class="tit" width="225px;">申请类型：</td>
            <td>
                    ${fns:getDictLabel(assessAudit.applyType,'assessmentAppraisal', '无')}
            </td>
            <td class="tit">地点：</td>
            <td>
                ${assessAudit.auditAddress}
            </td>
        </tr>
        <tr>
            <td class="tit">医学专家：</td>
            <td>
                ${assessAudit.medicalExpert}
            </td>
            <td class="tit">日期：</td>
            <td>
                    ${assessAudit.date}
            </td>
        </tr>
        <tr>
            <td class="tit">法律专家：</td>
            <td>
                ${assessAudit.legalExpert}
            </td>
            <td class="tit">下一环节处理人：</td>
            <td >
                    ${assessAudit.linkEmployee.name}
            </td>
        </tr>

    </table>
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
                <li>
                    <a href="#pgjdsq" data-toggle="tab">评估鉴定申请</a>
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
            </div>
        </div>
    </div>
</fieldset>
    <c:if test="${empty show2}">
    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" style="margin-left: 550px;"/>
    </div>
    <act:histoicFlow procInsId="${assessAudit.complaintMain.procInsId}" />
    </c:if>
</form:form>
</body>
</html>