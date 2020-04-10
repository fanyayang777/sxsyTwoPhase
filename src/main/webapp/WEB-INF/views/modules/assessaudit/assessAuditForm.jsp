<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>评估鉴定审批管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
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
<ul class="nav nav-tabs">
    <li><a href="${ctx}/assessaudit/assessAudit/">评估鉴定审批列表</a></li>
    <li class="active"><a href="${ctx}/assessaudit/assessAudit/form?id=${assessAudit.id}">评估鉴定审批<shiro:hasPermission
            name="assessaudit:assessAudit:edit">${not empty assessAudit.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="assessaudit:assessAudit:edit">查看</shiro:lacksPermission></a></li>
</ul>
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
    <ul id="myTab" class="nav nav-tabs">
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

    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="patient">
            <table class="table-form">
                <tr>
                    <td class="tit" width="180px"><font color="red">*</font>申请人：</td>
                    <td width="429px">
                        <form:input path="patientApplyer" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
                    </td>
                    <td class="tit" width="160px"><font color="red">*</font>与患者关系：</td>
                    <td width="476px">
                        <form:select path="patientRelation" class="input-medium">
                            <form:options items="${fns:getDictList('patient_relation')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td class="tit" width="180px"><font color="red">*</font>电话：</td>
                    <td width="429px">
                        <form:input path="patientMobile" htmlEscape="false" maxlength="15" cssClass="mobile required input-xlarge"/>
                    </td>
                    <td class="tit" width="199px">患者姓名：</td>
                    <td width="522px">
                        <form:input path="patientName" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit" width="180px">性别：</td>
                    <td >
                            <%--<form:input path="patientSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
                        <form:select path="patientSex" class="input-medium">
                            <form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                        </form:select>
                    </td>
                    <td class="tit">年龄：</td>
                    <td >
                        <form:input path="patientAge" htmlEscape="false" maxlength="4" class="input-xlarge required"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit" width="199px"><font color="red">*</font>涉及医院：</td>
                    <td width="522px">
                        <sys:treeselect id="involveHospital" name="involveHospital" value="${empty assessAudit.involveHospital ? assessAudit.complaintMain.involveHospital : assessAudit.involveHospital}" labelName="" labelValue="${empty assessAudit.involveHospital ? assessAudit.complaintMain.hospital.name : assessAudit.sjOffice.name}"
                                        title="机构" url="/sys/office/treeData?type=1&officeType=2" dataMsgRequired="必填信息" cssClass="required" isAll="true" allowClear="true" notAllowSelectParent="false"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit" width="199px"><font color="red">*</font>申请事项：</td>
                    <td width="522px" colspan="3">
                        <form:textarea path="patientApplyMatter" htmlEscape="false" maxlength="200" class="input-xlarge required" cssStyle="width: 1200px;height:100px"/>
                    </td>

                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="hospital">
            <table class="table-form">
                <tr>
                    <td class="tit" width="180px"><font color="red">*</font>申请医院：</td>
                    <td width="429px">
                        <sys:treeselect id="hospitalApply" name="hospitalApply" value="${empty assessAudit.hospitalApply ? assessAudit.complaintMain.involveHospital : assessAudit.hospitalApply}" labelName="" labelValue="${empty assessAudit.hospitalApply ? assessAudit.complaintMain.hospital.name : assessAudit.sqOffice.name}"
                                        title="机构" url="/sys/office/treeData?type=1&officeType=2" dataMsgRequired="必填信息" cssClass="required" isAll="true" allowClear="true" notAllowSelectParent="false"/>
                    </td>
                    <td class="tit" width="199px"><font color="red">*</font>代理人：</td>
                    <td>
                        <form:input path="agent" htmlEscape="false" maxlength="10" class="input-xlarge required"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>电话：</td>
                    <td class="controls">
                        <form:input path="hospitalMobile" htmlEscape="false" maxlength="15" cssClass="mobile required input-xlarge"/>
                    </td>
                    <td class="tit"><font color="red">*</font>医方姓名：</td>
                    <td class="controls">
                        <form:input path="hospitalName" htmlEscape="false" maxlength="10" class="input-xlarge required"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>性别：</td>
                    <td class="controls">
                        <%--<form:input path="hospitalSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
                        <form:select path="hospitalSex" class="input-medium">
                            <form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                        </form:select>
                    </td>
                    <td class="tit"><font color="red">*</font>年龄：</td>
                    <td class="controls">
                        <form:input path="hospitalAge" htmlEscape="false" maxlength="4" class="input-xlarge required"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit" width="199px"><font color="red">*</font>申请事项：</td>
                    <td width="522px" colspan="3">
                        <form:textarea path="hospitalApplyMatter" htmlEscape="false" maxlength="200" class="input-xlarge required" cssStyle="width: 1200px;height:100px"/>
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
                        <div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files"  uploadPath="/assessaudit/assessAudit/Huan" selectMultiple="true" /></div>
                    </td>

                </tr>
                <tr style="" >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医方申请表：</td>
                    <input type="hidden" name="fjtype2" value="13">
                    <td style="width: 450px; ">
                        <input type="hidden" id="files2" name="files2" htmlEscape="false" class="input-xlarge" value="${files2}" />
                        <input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files2" type="files"  uploadPath="/assessaudit/assessAudit/Hospital" selectMultiple="true" /></div>
                    </td>

                </tr>
            </table>
        </div>
    </div>
    <table class="table-form">
        <tr>
            <td class="tit" width="225px;">申请类型：</td>
            <td>
                <form:select path="applyType" class="input-xlarge ">
                    <form:option value="" label=""/>
                    <form:options items="${fns:getDictList('assessmentAppraisal')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </td>
            <td class="tit">地点：</td>
            <td>
                <form:input path="auditAddress" htmlEscape="false" maxlength="200" class="input-xlarge required "/>
            </td>
        </tr>
        <tr>
            <td class="tit">医学专家：</td>
            <td>
                <form:input path="medicalExpert" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
            </td>
            <td class="tit">日期：</td>
            <td>
                <input name="date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
                       value="${assessAudit.date}"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
            </td>
        </tr>
        <tr>
            <td class="tit">法律专家：</td>
            <td>
                <form:input path="legalExpert" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
            </td>
            <td class="tit">下一环节处理人：</td>
            <td >
                <sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${empty assessAudit.nextLinkMan?fns:getUser().id:assessAudit.nextLinkMan}" labelName="" labelValue="${empty assessAudit.linkEmployee.name?fns:getUser().name:assessAudit.linkEmployee.name}"
                                title="用户" url="/sys/office/treeData?type=3&officeType=1" dataMsgRequired="必填信息" cssClass="required" allowClear="true" notAllowSelectParent="true" />
            </td>
        </tr>

    </table>
    <div class="form-actions">
        <shiro:hasPermission name="assessaudit:assessAudit:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#flag').val('no')"/>&nbsp;</shiro:hasPermission>
        <shiro:hasPermission name="assessaudit:assessAudit:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="下一步" onclick="$('#flag').val('yes')"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
    <act:histoicFlow procInsId="${assessAudit.complaintMain.procInsId}" />
</form:form>
</body>
</html>