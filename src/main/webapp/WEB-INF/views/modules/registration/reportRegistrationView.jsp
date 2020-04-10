<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>报案信息管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    var aa=$("#export").val();
                    if(aa=='no') {
                        loading('正在提交，请稍等...');
                    }
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
    </script>
</head>
<body>
<c:if test="${reportRegistration.complaintMain.act.assigneeName}">

</c:if>
<legend>报案登记详情</legend>
<form:form id="inputForm" modelAttribute="reportRegistration" action="${ctx}/registration/reportRegistration/save"
           method="post" class="form-horizontal">
    <form:hidden path="reportRegistrationId"/>
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
    <form:hidden path="complaintMain.hospital.name"/>
    <form:hidden path="area.name"/>
    <form:hidden path="complaintMain.involveDepartment"/>
    <form:hidden path="complaintMain.involveEmployee"/>
    <form:hidden path="complaintMain.involveHospital"/>
    <form:hidden path="complaintMain.hospital.area.name"/>
    <form:hidden path="complaintMain.patientAge"/>
    <input type="hidden"  id="export" name="export"/>
    <sys:message content="${message}"/>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#visitor" data-toggle="tab">报案信息登记表</a>
        </li>
            <%--<li>--%>
            <%--<a href="#patient" data-toggle="tab">患者信息</a>--%>
            <%--</li>--%>
            <%--<li>--%>
            <%--<a href="#hospital" data-toggle="tab">涉及医院信息</a>--%>
            <%--</li>--%>
        <li>
            <a href="#annex" data-toggle="tab">附件</a>
        </li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="visitor">
            <table class="table-form">
                <tr>
                    <td class="tit" width="10%">医疗机构名称:</td>
                    <td colspan="3" style="font-size: 16px; text-align: center">${reportRegistration.complaintMain.hospital.name}</td>
                    <td class="tit" width="10%">保单号:</td>
                    <td colspan="5">
                        <form:input path="policyNumber" htmlEscape="false" maxlength="255" class="required" cssStyle="width: 97%;height: 30px; text-align: center;" readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">纠纷发生时间:</td>
                    <td>
                        <input name="disputeTime" type="text" readonly="readonly" maxlength="20"
                               class="input-medium Wdate required"
                               value="${reportRegistration.disputeTime}"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width:90%;height:30px;text-align: center;" id="jiufen" onchange="compareDate(this.value)" disabled="disabled"/>
                    </td>
                    <td class="tit" width="7%">机构等级:</td>
                    <td>
                        <form:select path="complaintMain.hospitalGrade" cssStyle="width: 80%; text-align: center;" disabled="true">
                            <form:options items="${fns:getDictList('hospital_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                        </form:select>
                    </td>
                    <td class="tit">所属城市:</td>
                    <td style="text-align: center;">
                            ${reportRegistration.complaintMain.hospital.area.name}
                    </td>
                    <td class="tit" width="7%">报案人姓名:</td>
                    <td>
                        <form:input path="reportEmp" htmlEscape="false" maxlength="32" class="input-xlarge required" cssStyle="width: 90%;height: 30px;text-align: center;font-size: 16px;" disabled="true"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">患者姓名:</td>
                    <td>
                        <form:input path="complaintMain.patientName" htmlEscape="false" maxlength="20" class="input-xlarge required" cssStyle="width: 90%;height: 30px;text-align: center;font-size: 16px;" disabled="true"/>
                    </td>
                    <td class="tit">性別:</td>
                    <td>
                        <form:select path="complaintMain.patientSex" class="input-medium" cssStyle="width: 80%;text-align: center;" disabled="true">
                            <form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                        </form:select>
                    </td>
                    <td class="tit">年齡:</td>
                    <td style="text-align: center;">
                            ${reportRegistration.complaintMain.patientAge}
                    </td>
                    <td class="tit">身份证号:</td>
                    <td>
                        <form:input path="complaintMain.patientCard" htmlEscape="false" maxlength="20" class="input-xlarge required card" cssStyle="width: 90%;height: 30px;text-align: center;" disabled="true"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">出险时间:</td>
                    <td>
                        <input name="reportTime" type="text" readonly="readonly" maxlength="20"
                               class="input-medium Wdate required"
                               value="${reportRegistration.reportTime}"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width:90%;height:30px;text-align: center;" id="chuxian" onchange="compareDate(this.value)" disabled="disabled"/>
                    </td>
                    <td class="tit">科室:</td>
                    <td colspan="2">
                            ${reportRegistration.complaintMain.testTree}
                    </td>
                    <td class="tit">出险医生:</td>
                    <td style="text-align: center;" colspan="2">
                            ${empty reportRegistration.complaintMain.employee.name?reportRegistration.complaintMain.involveEmployee:reportRegistration.complaintMain.employee.name}
                    </td>
                    <%--<td class="tit">是否重大纠纷:</td>
                    <td>
                        <form:select path="isMajor" style='width:110px;text-align: center;' disabled="true">
                            <form:option value="0">否</form:option>
                            <form:option value="1">是</form:option>
                        </form:select>
                    </td>--%>
                </tr>
                <tr>
                    <td class="tit">
                        联系电话:
                    </td>
                    <td colspan="3">
                        <p style="margin:0pt; orphans:0; widows:0">
                            <span style="font-family:宋体; font-size:12pt; font-weight:bold">患方：</span>
                            <span style="font-family:宋体; font-size:12pt; font-weight:bold">
                                <form:input path="patientMobile" htmlEscape="false" maxlength="15" class="input-xlarge required phone" cssStyle="width: 50%;height: 30px; font-size: 16px;" disabled="true"/>
                            </span>
                        </p>
                    </td>
                    <td colspan="4">
                        <p style="margin:0pt; orphans:0; widows:0"><span
                                style="font-family:宋体; font-size:12pt; font-weight:bold">医方：</span>
                            <span style="font-family:宋体; font-size:12pt; font-weight:bold">
                            <form:input path="doctorMobile" htmlEscape="false" maxlength="15"
                                        class="input-xlarge required phone" cssStyle="width: 42%;height: 30px;font-size: 16px;" disabled="true"/>
                        </span>
                        </p></td>
                </tr>
                <tr>
                    <td class="tit">
                        纠纷概要:
                    </td>
                    <td colspan="7">
                        <form:textarea path="summaryOfDisputes" htmlEscape="false" class="input-xlarge required"
                                       style="margin: 0px;width: 99%;font-size: 16px;" rows="15" disabled="true"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        纠纷焦点:
                    </td>
                    <td  colspan="7">
                        <form:textarea path="focus" htmlEscape="false" class="input-xlarge required" style="margin: 0px;width: 99%;font-size: 16px;" rows="5" disabled="true"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        患方要求:
                    </td>
                    <td  colspan="7">
                        <form:textarea path="patientAsk" htmlEscape="false" class="input-xlarge required" style="margin: 0px;width: 99%;font-size: 16px;" rows="2" disabled="true"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">填表人签名:</td>
                    <td colspan="3">
                        <sys:treeselect id="registrationEmp" name="registrationEmp"
                                        value="${reportRegistration.registrationEmp}" labelName=""
                                        labelValue="${reportRegistration.djEmployee.name}"
                                        title="用户" url="/sys/office/treeData?type=3&officeType=1" dataMsgRequired="必填信息"
                                        cssClass="required" allowClear="true" notAllowSelectParent="true" disabled="true"/>
                    </td>
                    <td class="tit">填表日期:</td>
                    <td colspan="3">
                        <input name="registrationTime" type="text" readonly="readonly" maxlength="20"
                               class="input-medium Wdate required"
                               value="${reportRegistration.registrationTime}"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width:40%;height:30px;text-align: center;" disabled="disabled"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">报案号:</td>
                    <td colspan="3">
                        <form:input path="complaintMain.caseNumber" htmlEscape="false" maxlength="20" class="input-xlarge required" cssStyle="width: 90%;height: 30px;border:hidden; text-align: center;" readonly="true"/>
                    </td>
                    <td class="tit">审核人:</td>
                    <td>
                        <form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge " cssStyle="width: 90%;height: 30px; text-align: center;" disabled="true"/>
                    </td>
                    <td class="tit">审核日期:</td>
                    <td>
                        <input name="patientRelation" type="text" readonly="readonly" maxlength="20"
                               class="input-medium Wdate "
                               value="${reportRegistration.patientRelation}"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width:90%;height:30px;text-align: center;" disabled="disabled"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">备注:</td>
                    <td colspan="7">
                        <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                style="font-family:'Times New Roman'; font-size:12pt">1.</span><span
                                style="font-family:宋体; font-size:12pt">表格中的案件编号由负责接案管理员填写；</span></p>
                        <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                style="font-family:'Times New Roman'; font-size:12pt">2.</span><span
                                style="font-family:宋体; font-size:12pt">报案号、审核日期由负责向经纪</span><span
                                style="font-family:'Times New Roman'; font-size:12pt">/</span><span
                                style="font-family:宋体; font-size:12pt">保险公司报案的工作人员填写；</span></p>
                        <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                style="font-family:'Times New Roman'; font-size:12pt">3.</span><span
                                style="font-family:宋体; font-size:12pt">纠纷发生时间是指患者首次向医疗机构提出索赔的时间；</span></p>
                        <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                style="font-family:'Times New Roman'; font-size:12pt">4.</span><span
                                style="font-family:宋体; font-size:12pt">出险时间系患者发生人身损害的时间；</span></p>
                        <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                style="font-family:'Times New Roman'; font-size:12pt">5.</span><span
                                style="font-family:宋体; font-size:12pt">报案人系填表人；</span></p>
                        <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                style="font-family:'Times New Roman'; font-size:12pt">6.</span><span
                                style="font-family:宋体; font-size:12pt">保内、保外案件均使用此表填写，未参保的在保单号内填写“未参保”；</span></p>
                        <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                style="font-family:'Times New Roman'; font-size:12pt">7.</span><span
                                style="font-family:宋体; font-size:12pt">审核人系负责向经纪</span><span
                                style="font-family:'Times New Roman'; font-size:12pt">/</span><span
                                style="font-family:宋体; font-size:12pt">保险公司报案的工作人员。</span></p>
                    </td>
                </tr>
            </table>
        </div>


            <%--<table class="table-form">--%>
            <%--<tr>--%>
            <%--<td class="tit" width="199px"><font color="red">*</font>医疗机构名称：</td>--%>
            <%--<td colspan="3">--%>
            <%--<sys:treeselect id="involveHospital" name="complaintMain.involveHospital"--%>
            <%--value="${reportRegistration.complaintMain.involveHospital}" labelName=""--%>
            <%--labelValue="${reportRegistration.complaintMain.hospital.name}"--%>
            <%--title="机构" url="/sys/office/treeData?type=1&officeType=2" isAll="true"--%>
            <%--dataMsgRequired="必填信息" cssClass="required" allowClear="true"--%>
            <%--notAllowSelectParent="false"/>--%>
            <%--</td>--%>
            <%--<td class="tit" width="199px"><font color="red">*</font>保单号：</td>--%>

            <%--<td colspan="3">--%>
            <%--<form:input path="policyNumber" htmlEscape="false" maxlength="255"--%>
            <%--class="input-xlarge required"/>--%>
            <%--</td>--%>

            <%--&lt;%&ndash;<td class="tit" width="180px"><font color="red">*</font>报案人姓名：</td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td width="429px">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<form:input path="reportEmp" htmlEscape="false" maxlength="32" class="input-xlarge required"/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td class="tit" width="180px"><font color="red">*</font>患方联系方式：</td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td width="429px">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<form:input path="patientMobile" htmlEscape="false" maxlength="15" class="input-xlarge required phone"/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--&lt;%&ndash;<td class="tit" width="160px"><font color="red">*</font>与患者关系：</td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td width="476px">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<form:select path="patientRelation" class="input-medium">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<form:options items="${fns:getDictList('patient_relation')}" itemLabel="label" itemValue="value" htmlEscape="false"/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</form:select>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td class="tit" width="160px">报案日期：</td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td width="476px">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<input name="reportTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"&ndash;%&gt;--%>
            <%--&lt;%&ndash;value="${reportRegistration.reportTime}"&ndash;%&gt;--%>
            <%--&lt;%&ndash;onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
            <%--<td class="tit" width="180px">纠纷发生时间：</td>--%>
            <%--<td>--%>
            <%--<input name="disputeTime" type="text" readonly="readonly" maxlength="20"--%>
            <%--class="input-medium Wdate required"--%>
            <%--value="${reportRegistration.disputeTime}"--%>
            <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>--%>
            <%--</td>--%>
            <%--<td class="tit" width="199px"><font color="red">*</font>医院等级：</td>--%>
            <%--<td>--%>
            <%--<form:select path="complaintMain.hospitalGrade" cssStyle="width: 180px">--%>
            <%--<form:option value="1">特等</form:option>--%>
            <%--<form:option value="2">甲等</form:option>--%>
            <%--<form:option value="3">乙等</form:option>--%>
            <%--<form:option value="4">丙等</form:option>--%>
            <%--</form:select>--%>
            <%--</td>--%>
            <%--<td class="tit" width="199px"><font color="red">*</font>所属地市：</td>--%>
            <%--<td style="text-align: center;">${reportRegistration.area.name}</td>--%>
            <%--<td class="tit" width="180px"><font color="red">*</font>报案人姓名：</td>--%>
            <%--<td width="429px">--%>
            <%--<form:input path="reportEmp" htmlEscape="false" maxlength="32" class="input-xlarge required"/>--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td class="tit" width="240px">患者姓名：</td>--%>
            <%--<td width="522px">--%>
            <%--<form:input path="complaintMain.patientName" htmlEscape="false" maxlength="20"--%>
            <%--class="input-xlarge required"/>--%>
            <%--</td>--%>
            <%--<td class="tit">患者性别：</td>--%>
            <%--<td>--%>
            <%--&lt;%&ndash;<form:input path="patientSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>&ndash;%&gt;--%>
            <%--<form:select path="complaintMain.patientSex" class="input-medium">--%>
            <%--<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value"--%>
            <%--htmlEscape="false"/>--%>
            <%--</form:select>--%>
            <%--</td>--%>
            <%--<td class="tit">患者年龄：</td>--%>
            <%--<td>--%>
            <%--<form:input path="complaintMain.patientAge" htmlEscape="false" maxlength="4"--%>
            <%--class="input-xlarge required"/>--%>
            <%--</td>--%>
            <%--<td class="tit">患者身份证号：</td>--%>
            <%--<td>--%>
            <%--<form:input path="complaintMain.patientCard" htmlEscape="false" maxlength="20"--%>
            <%--class="input-xlarge required card"/>--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td class="tit" width="160px">出险时间：</td>--%>
            <%--<td width="476px">--%>
            <%--<input name="reportTime" type="text" readonly="readonly" maxlength="20"--%>
            <%--class="input-medium Wdate required"--%>
            <%--value="${reportRegistration.reportTime}"--%>
            <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>--%>
            <%--</td>--%>
            <%--<td class="tit" width="180px"><font color="red">*</font>科室：</td>--%>
            <%--<td>--%>
            <%--<sys:treeselect id="involveDepartment" name="complaintMain.involveDepartment"--%>
            <%--value="${reportRegistration.complaintMain.involveDepartment}" labelName=""--%>
            <%--labelValue="${reportRegistration.complaintMain.department.name}"--%>
            <%--title="部门" url="/sys/office/treeData?type=2&officeType=2" isAll="true"--%>
            <%--dataMsgRequired="必填信息" cssClass="required" allowClear="true"--%>
            <%--notAllowSelectParent="true"/>--%>
            <%--</td>--%>
            <%--<td class="tit"><font color="red">*</font>出险医生：</td>--%>
            <%--<td class="controls">--%>
            <%--<sys:treeselect id="involveEmployee" name="complaintMain.involveEmployee"--%>
            <%--value="${reportRegistration.complaintMain.involveEmployee}" labelName=""--%>
            <%--labelValue="${reportRegistration.complaintMain.employee.name}"--%>
            <%--title="用户" url="/sys/office/treeData?type=3&officeType=2" isAll="true"--%>
            <%--dataMsgRequired="必填信息" cssClass="required" allowClear="true"--%>
            <%--notAllowSelectParent="true"/>--%>
            <%--</td>--%>
            <%--<td class="tit"><font color="red">*</font>是否重大纠纷：</td>--%>
            <%--<td>--%>
            <%--<form:select path="isMajor" style='width:110px;text-align: center;'>--%>
            <%--<form:option value="1">是</form:option>--%>
            <%--<form:option value="0">否</form:option>--%>
            <%--</form:select>--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td class="tit" width="180px"><font color="red">*</font>患方联系方式：</td>--%>
            <%--<td width="" colspan="3">--%>
            <%--<form:input path="patientMobile" htmlEscape="false" maxlength="15"--%>
            <%--class="input-xlarge required phone"/>--%>
            <%--</td>--%>
            <%--<td class="tit" width="180px"><font color="red">*</font>医方联系方式：</td>--%>
            <%--<td width="" colspan="3">--%>
            <%--<form:input path="doctorMobile" htmlEscape="false" maxlength="15"--%>
            <%--class="input-xlarge required phone"/>--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td class="tit"><font color="red">*</font>投诉纠纷概要：</td>--%>
            <%--<td colspan="7">--%>
            <%--<form:textarea path="summaryOfDisputes" htmlEscape="false" class="input-xlarge required"--%>
            <%--style="margin: 0px; width: 700px; height: 125px;"/>--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td class="tit"><font color="red">*</font>投诉纠纷焦点：</td>--%>
            <%--<td colspan="7">--%>
            <%--<form:textarea path="focus" htmlEscape="false" class="input-xlarge required"--%>
            <%--style="margin: 0px; width: 700px; height: 125px;"/>--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td class="tit"><font color="red">*</font>患方要求：</td>--%>
            <%--<td colspan="7">--%>
            <%--<form:textarea path="patientAsk" htmlEscape="false" class="input-xlarge required"--%>
            <%--style="margin: 0px; width: 700px; height: 125px;"/>--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td class="tit" width="160px">填表人签名：</td>--%>
            <%--<td width="" colspan="3">--%>
            <%--<sys:treeselect id="registrationEmp" name="registrationEmp"--%>
            <%--value="${reportRegistration.registrationEmp}" labelName=""--%>
            <%--labelValue="${reportRegistration.djEmployee.name}"--%>
            <%--title="用户" url="/sys/office/treeData?type=3&officeType=1" dataMsgRequired="必填信息"--%>
            <%--cssClass="required" allowClear="true" notAllowSelectParent="true"/>--%>

            <%--</td>--%>
            <%--<td class="tit" width="180px">填表日期：</td>--%>
            <%--<td colspan="3">--%>
            <%--<input name="registrationTime" type="text" readonly="readonly" maxlength="20"--%>
            <%--class="input-medium Wdate required"--%>
            <%--value="${reportRegistration.registrationTime}"--%>
            <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td class="tit" width="160px">报案号：</td>--%>
            <%--<td width="">--%>
            <%--<form:input path="complaintMain.caseNumber" htmlEscape="false" maxlength="20"--%>
            <%--class="input-xlarge required"/>--%>
            <%--</td>--%>
            <%--<td class="tit"><font color="red">*</font>审核人：</td>--%>
            <%--<td>--%>
            <%--<form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge required"/>--%>
            <%--</td>--%>
            <%--<td class="tit" width="160px">审核日期：</td>--%>
            <%--<td width="" colspan="3">--%>
            <%--<input name="patientRelation" type="text" readonly="readonly" maxlength="20"--%>
            <%--class="input-medium Wdate required"--%>
            <%--value="${reportRegistration.patientRelation}"--%>
            <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>--%>
            <%--</td>--%>

            <%--</tr>--%>
            <%--</table>--%>
            <%--</div>--%>

        <div class="tab-pane fade" id="annex">
            <input type="hidden" name="fjtype" value="0">
            <td style="width: 450px;">
                <input type="hidden" id="files" name="files" htmlEscape="false" class="input-xlarge" value="${files}"/>
                <input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
                <sys:ckfinder input="files" type="files" uploadPath="/reportReigsation/annex" selectMultiple="true"
                               readonly="true"/>
            </td>
        </div>
    </div>
    <table class="table-form">
        <tr>
            <td class="tit"><font color="red">*</font>下一环节处理人：</td>
            <td>
                    <%--<form:input path="nextLinkMan" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
                <sys:treeselect id="nextLinkMan" name="nextLinkMan"
                                value="${empty reportRegistration.nextLinkMan?fns:getUser().id:reportRegistration.nextLinkMan}"
                                labelName=""
                                labelValue="${empty reportRegistration.linkEmployee.name?fns:getUser().name:reportRegistration.linkEmployee.name}"
                                title="用户" url="/sys/office/treeData?type=3&officeType=1" dataMsgRequired="必填信息"
                                cssClass="required" allowClear="true" notAllowSelectParent="true" disabled="disabled"/>
            </td>
        </tr>
    </table>
    <div class="form-actions" >
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" style=""/>
    </div>
    <act:histoicFlow procInsId="${reportRegistration.complaintMain.procInsId}"/>
</form:form>
</body>
</html>


<%--<%@ page contentType="text/html;charset=UTF-8" %>--%>
<%--<%@ include file="/WEB-INF/views/include/taglib.jsp"%>--%>
<%--<html>--%>
<%--<head>--%>
<%--<title>报案信息管理</title>--%>
<%--<meta name="decorator" content="default"/>--%>
<%--<script type="text/javascript">--%>
<%--$(document).ready(function() {--%>
<%--//$("#name").focus();--%>
<%--$("#inputForm").validate({--%>
<%--submitHandler: function(form){--%>
<%--loading('正在提交，请稍等...');--%>
<%--form.submit();--%>
<%--},--%>
<%--errorContainer: "#messageBox",--%>
<%--errorPlacement: function(error, element) {--%>
<%--$("#messageBox").text("输入有误，请先更正。");--%>
<%--if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){--%>
<%--error.appendTo(element.parent().parent());--%>
<%--} else {--%>
<%--error.insertAfter(element);--%>
<%--}--%>
<%--}--%>
<%--});--%>
<%--});--%>
<%--</script>--%>
<%--</head>--%>
<%--<body>--%>
<%--<ul class="nav nav-tabs">--%>
<%--<li><a href="${ctx}/registration/reportRegistration/">报案信息列表</a></li>--%>
<%--<li class="active"><a href="${ctx}/registration/reportRegistration/form?id=${reportRegistration.reportRegistrationId}">报案信息<shiro:hasPermission name="registration:reportRegistration:edit">${not empty reportRegistration.reportRegistrationId?'修改':'修改'}</shiro:hasPermission><shiro:lacksPermission name="registration:reportRegistration:edit">查看</shiro:lacksPermission></a></li>--%>
<%--</ul><br/>--%>
<%--<form:form id="inputForm" modelAttribute="reportRegistration" action="${ctx}/registration/reportRegistration/save" method="post" class="form-horizontal">--%>
<%--<form:hidden path="reportRegistrationId"/>--%>
<%--<form:hidden path="createDate"/>--%>
<%--<form:hidden path="createBy"/>--%>
<%--<form:hidden path="complaintMainId"/>--%>
<%--<form:hidden path="complaintMain.complaintMainId"/>--%>
<%--<form:hidden path="complaintMain.act.taskId"/>--%>
<%--<form:hidden path="complaintMain.act.taskName"/>--%>
<%--<form:hidden path="complaintMain.act.taskDefKey"/>--%>
<%--<form:hidden path="complaintMain.act.procInsId"/>--%>
<%--<form:hidden path="complaintMain.act.procDefId"/>--%>
<%--<form:hidden path="complaintMain.procInsId"/>--%>
<%--<form:hidden id="flag" path="complaintMain.act.flag"/>--%>
<%--<sys:message content="${message}"/>--%>
<%--<ul id="myTab" class="nav nav-tabs">--%>
<%--<li class="active">--%>
<%--<a href="#visitor" data-toggle="tab">报案人信息</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="#patient" data-toggle="tab">患者信息</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="#hospital" data-toggle="tab">涉及医院信息</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="#annex" data-toggle="tab">附件</a>--%>
<%--</li>--%>
<%--</ul>--%>
<%--<div id="myTabContent" class="tab-content">--%>
<%--<div class="tab-pane fade in active" id="visitor">--%>
<%--<table class="table-form">--%>
<%--<tr>--%>
<%--<td class="tit" width="180px"><font color="red">*</font>报案人姓名：</td>--%>
<%--<td width="429px">--%>
<%--<form:input path="reportEmp" htmlEscape="false" maxlength="32" class="input-xlarge required"/>--%>
<%--</td>--%>
<%--<td class="tit" width="180px"><font color="red">*</font>患方联系方式：</td>--%>
<%--<td width="429px">--%>
<%--<form:input path="patientMobile" htmlEscape="false" maxlength="15" class="input-xlarge required phone"/>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td class="tit" width="160px"><font color="red">*</font>与患者关系：</td>--%>
<%--<td width="476px">--%>
<%--<form:select path="patientRelation" class="input-medium">--%>
<%--<form:options items="${fns:getDictList('patient_relation')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
<%--</form:select>--%>
<%--</td>--%>
<%--<td class="tit" width="160px">报案日期：</td>--%>
<%--<td width="476px">--%>
<%--<input name="reportTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"--%>
<%--value="${reportRegistration.reportTime}"--%>
<%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>--%>
<%--</td>--%>
<%--</tr>--%>
<%--</table>--%>
<%--</div>--%>
<%--<div class="tab-pane fade" id="patient">--%>
<%--<table class="table-form">--%>
<%--<tr>--%>
<%--<td class="tit" width="240px">患者姓名：</td>--%>
<%--<td width="522px">--%>
<%--<form:input path="complaintMain.patientName" htmlEscape="false" maxlength="20" class="input-xlarge required"/>--%>
<%--</td>--%>
<%--<td class="tit" >患者性别：</td>--%>
<%--<td >--%>
<%--&lt;%&ndash;<form:input path="patientSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>&ndash;%&gt;--%>
<%--<form:select path="complaintMain.patientSex" class="input-medium">--%>
<%--<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
<%--</form:select>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td class="tit">患者年龄：</td>--%>
<%--<td >--%>
<%--<form:input path="complaintMain.patientAge" htmlEscape="false" maxlength="4" class="input-xlarge required"/>--%>
<%--</td>--%>
<%--<td class="tit">患者身份证号：</td>--%>
<%--<td >--%>
<%--<form:input path="complaintMain.patientCard" htmlEscape="false" maxlength="20" class="input-xlarge required card"/>--%>
<%--</td>--%>
<%--</tr>--%>
<%--</table>--%>
<%--</div>--%>
<%--<div class="tab-pane fade" id="hospital">--%>
<%--<table class="table-form">--%>
<%--<tr >--%>
<%--<td class="tit" width="199px"><font color="red">*</font>涉及医院：</td>--%>
<%--<td width="522px">--%>
<%--<sys:treeselect id="involveHospital" name="complaintMain.involveHospital" value="${reportRegistration.complaintMain.involveHospital}" labelName="" labelValue="${reportRegistration.complaintMain.hospital.name}"--%>
<%--title="机构" url="/sys/office/treeData?type=1&officeType=2" isAll="true" dataMsgRequired="必填信息" cssClass="required"   allowClear="true" notAllowSelectParent="false"/>--%>
<%--</td>--%>
<%--<td class="tit" width="199px"><font color="red">*</font>医院等级：</td>--%>
<%--<td>--%>
<%--<form:select path="complaintMain.hospitalGrade" cssStyle="width: 180px">--%>
<%--<form:option value="1">特等</form:option>--%>
<%--<form:option value="2">甲等</form:option>--%>
<%--<form:option value="3">乙等</form:option>--%>
<%--<form:option value="4">丙等</form:option>--%>
<%--</form:select>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td class="tit" width="180px"><font color="red">*</font>涉及科室：</td>--%>
<%--<td >--%>
<%--<sys:treeselect id="involveDepartment" name="complaintMain.involveDepartment" value="${reportRegistration.complaintMain.involveDepartment}" labelName="" labelValue="${reportRegistration.complaintMain.department.name}"--%>
<%--title="部门" url="/sys/office/treeData?type=2&officeType=2" isAll="true" dataMsgRequired="必填信息" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>--%>
<%--</td>--%>
<%--<td class="tit" width="199px"><font color="red">*</font>医院级别：</td>--%>
<%--<td>--%>
<%--<form:select path="complaintMain.hospitalLevel" cssStyle="width: 180px">--%>
<%--<form:option value="1">一级</form:option>--%>
<%--<form:option value="2">二级</form:option>--%>
<%--<form:option value="3">三级</form:option>--%>
<%--</form:select>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr >--%>
<%--<td class="tit"><font color="red">*</font>涉及人员：</td>--%>
<%--<td class="controls">--%>
<%--<sys:treeselect id="involveEmployee" name="complaintMain.involveEmployee" value="${reportRegistration.complaintMain.involveEmployee}" labelName="" labelValue="${reportRegistration.complaintMain.employee.name}"--%>
<%--title="用户" url="/sys/office/treeData?type=3&officeType=2" isAll="true" dataMsgRequired="必填信息" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>--%>
<%--</td>--%>
<%--</tr>--%>
<%--</table>--%>
<%--</div>--%>
<%--<div class="tab-pane fade" id="annex">--%>
<%--<input type="hidden"  name="fjtype" value="0">--%>
<%--<td style="width: 450px;">--%>

<%--<input type="hidden" id="files" name="files" htmlEscape="false" class="input-xlarge"  value="${files}"/>--%>
<%--<input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">--%>
<%--&lt;%&ndash;<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />&ndash;%&gt;--%>
<%--<sys:ckfinder input="files" type="files"  uploadPath="/reportReigsation/annex" selectMultiple="true"  maxWidth="100" maxHeight="100"/>--%>
<%--</td>--%>
<%--</div>--%>
<%--</div>--%>


<%--&lt;%&ndash;<div class="control-group">&ndash;%&gt;--%>
<%--&lt;%&ndash;<label class="control-label">与患者关系：</label>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="controls">&ndash;%&gt;--%>
<%--&lt;%&ndash;<form:input path="patientRelation" htmlEscape="false" maxlength="1" class="input-xlarge "/>&ndash;%&gt;--%>
<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
<%--<table class="table-form">--%>
<%--<tr>--%>
<%--<td class="tit" width="160px">案件编号：</td>--%>
<%--<td width="476px">--%>
<%--<form:input path="complaintMain.caseNumber" htmlEscape="false" maxlength="20" class="input-xlarge required"/>--%>
<%--</td>--%>
<%--<td class="tit" width="160px">登记人员：</td>--%>
<%--<td width="476px">--%>
<%--<sys:treeselect id="registrationEmp" name="registrationEmp" value="${reportRegistration.registrationEmp}" labelName="" labelValue="${reportRegistration.djEmployee.name}"--%>
<%--title="用户" url="/sys/office/treeData?type=3&officeType=1" dataMsgRequired="必填信息" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>--%>

<%--</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td class="tit" width="180px">登记日期：</td>--%>
<%--<td >--%>
<%--&lt;%&ndash;<form:input path="visitorDate" htmlEscape="false" maxlength="10" class="input-xlarge "/>&ndash;%&gt;--%>
<%--<input name="registrationTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"--%>
<%--value="${reportRegistration.registrationTime}"--%>
<%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" />--%>
<%--</td>--%>
<%--<td class="tit" width="180px">纠纷发生时间：</td>--%>
<%--<td >--%>
<%--<input name="disputeTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"--%>
<%--value="${reportRegistration.disputeTime}"--%>
<%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td class="tit"><font color="red">*</font>是否重大：</td>--%>
<%--<td >--%>
<%--<form:select path="isMajor" style='width:110px;text-align: center;'>--%>
<%--<form:option value="1">是</form:option>--%>
<%--<form:option value="0">否</form:option>--%>
<%--</form:select>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr >--%>
<%--<td class="tit"><font color="red">*</font>投诉纠纷概要：</td>--%>
<%--<td colspan="3">--%>
<%--<form:textarea path="summaryOfDisputes" htmlEscape="false" class="input-xlarge required" style="margin: 0px; width: 938px; height: 125px;"/>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td class="tit"><font color="red">*</font>投诉纠纷焦点：</td>--%>
<%--<td colspan="3">--%>
<%--<form:textarea path="focus" htmlEscape="false" class="input-xlarge required" style="margin: 0px; width: 938px; height: 125px;"/>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr >--%>
<%--<td class="tit"><font color="red">*</font>患方要求：</td>--%>
<%--<td colspan="3">--%>
<%--<form:textarea path="patientAsk" htmlEscape="false" class="input-xlarge required" style="margin: 0px; width: 938px; height: 125px;"/>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr >--%>
<%--&lt;%&ndash;<td class="tit"><font color="red">*</font>下一处理环节：</td>--%>
<%--<td >--%>
<%--<form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge required"/>--%>
<%--</td>&ndash;%&gt;--%>
<%--<td class="tit"><font color="red">*</font>下一环节处理人：</td>--%>
<%--<td >--%>
<%--&lt;%&ndash;<form:input path="nextLinkMan" htmlEscape="false" maxlength="32" class="input-xlarge "/>&ndash;%&gt;--%>
<%--<sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${empty reportRegistration.nextLinkMan?fns:getUser().id:reportRegistration.nextLinkMan}" labelName="" labelValue="${empty reportRegistration.linkEmployee.name?fns:getUser().name:reportRegistration.linkEmployee.name}"--%>
<%--title="用户" url="/sys/office/treeData?type=3&officeType=1" dataMsgRequired="必填信息" cssClass="required"  allowClear="true" notAllowSelectParent="true" />--%>
<%--</td>--%>
<%--</tr>--%>
<%--</table>--%>
<%--<div class="form-actions">--%>
<%--<shiro:hasPermission name="registration:reportRegistration:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#flag').val('no')"/>&nbsp;</shiro:hasPermission>--%>
<%--<shiro:hasPermission name="registration:reportRegistration:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="下一步" onclick="$('#flag').val('yes')"/>&nbsp;</shiro:hasPermission>--%>
<%--<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
<%--</div>--%>
<%--<act:histoicFlow procInsId="${reportRegistration.complaintMain.procInsId}" />--%>
<%--</form:form>--%>
<%--</body>--%>
<%--</html>--%>