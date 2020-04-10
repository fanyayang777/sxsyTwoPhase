<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>投诉接待管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    $("#visitorName").addClass("required");
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
            aa();
        });
        function removeCssClass() {
            $('#visitorName').removeClass('required');
            $('#nextLinkManName').removeClass('required');
            $('#receptionDate').removeClass('required');
            $('#receptionEmployee').removeClass('required');
            $('#appeal').removeClass('required');
            $('#summaryOfDisputes').removeClass('required');
            $('#isMajorName').removeClass('required');
            $('#visitorDate').removeClass('required');
            $('#complaintMain\\.involveEmployee').removeClass('required');
            $('#involveDepartmentName').removeClass('required');
            $('#involveHospitalName').removeClass('required');
            $('#areaName').removeClass('required');
            $('#visitorMobile').removeClass('required');
            $('#visitorNumber').removeClass('required');
            $('#complaintMain\\.patientName').removeClass('required');
            $('#complaintMain\\.patientAge').removeClass('required');
        }
        function addCssClass() {
            $('#visitorName').addClass('required');
            $('#nextLinkManName').addClass('required');
            $('#receptionDate').addClass('required');
            $('#receptionEmployee').addClass('required');
            $('#appeal').addClass('required');
            $('#summaryOfDisputes').addClass('required');
            $('#isMajorName').addClass('required');
            $('#visitorDate').addClass('required');
            $('#complaintMain\\.involveEmployee').addClass('required');
            $('#involveDepartmentName').addClass('required');
            $('#involveHospitalName').addClass('required');
            $('#areaName').addClass('required');
            $('#visitorMobile').addClass('required');
            $('#visitorNumber').addClass('required');
            $('#complaintMain\\.patientName').addClass('required');
            $('#complaintMain\\.patientAge').addClass('required');
        }

        function aa() {
            var a = '${complaintMainDetail.typeName}';
            if (a == "其他") {
                $("#qitaanjian").show();
            }

        }

        function num(value){
            if(value>=128){
                alertx("患者年龄输入有误，请重新输入！");
                $("#complaintMain\\.patientAge").val("");
            }
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/complaintdetail/complaintMainDetail/">投诉接待列表</a></li>
    <li class="active"><a
            href="${ctx}/complaintdetail/complaintMainDetail/form?id=${complaintMainDetail.complaintMainDetailId}">投诉接待<shiro:hasPermission
            name="complaintdetail:complaintMainDetail:edit">${not empty complaintMainDetail.complaintMainDetailId?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="complaintdetail:complaintMainDetail:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="complaintMainDetail" action="${ctx}/complaintdetail/complaintMainDetail/save"
           method="post" class="form-horizontal">
    <form:hidden path="complaintMainDetailId"/>
    <form:hidden path="complaintMainId"/>
    <form:hidden path="complaintMain.complaintMainId"/>
    <form:hidden path="complaintMain.act.taskId"/>
    <form:hidden path="complaintMain.act.taskName"/>
    <form:hidden path="complaintMain.act.taskDefKey"/>
    <form:hidden path="complaintMain.act.procInsId"/>
    <form:hidden path="complaintMain.act.procDefId"/>
    <form:hidden id="flag" path="complaintMain.act.flag"/>
    <sys:message content="${message}"/>

    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#visitor" data-toggle="tab">来访者信息</a>
        </li>
        <li>
            <a href="#patient" data-toggle="tab">患者信息</a>
        </li>
        <li>
            <a href="#hospital" data-toggle="tab">涉及医院信息</a>
        </li>
        <li>
            <a href="#annex" data-toggle="tab">附件</a>
        </li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="visitor">
            <table class="table-form">
                <tr>
                    <td class="tit" width="180px"><font color="red">*</font>来访者姓名：</td>
                    <td width="462px">
                        <form:input path="visitorName" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
                    </td>
                    <td class="tit" width="180px"><font color="red">*</font>联系电话：</td>
                    <td>
                        <form:input path="visitorMobile" htmlEscape="false" maxlength="15"
                                    class="input-xlarge required phone"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>与患者关系：</td>
                    <td>
                        <form:select path="patientRelation" class="input-medium required">
                            <form:options items="${fns:getDictList('patient_relation')}" itemLabel="label"
                                          itemValue="value" htmlEscape="false"/>
                        </form:select>
                    </td>
                    <td class="tit"><font color="red">*</font>来访人数：</td>
                    <td>
                        <form:input path="visitorNumber" htmlEscape="false" maxlength="10"
                                    class="input-xlarge required digits"/>
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="patient">
            <table class="table-form">
                <tr>
                    <td class="tit" width="180px"><font color="red">*</font>患者姓名：</td>
                    <td width="462px">
                        <form:input path="complaintMain.patientName" htmlEscape="false" maxlength="20"
                                    class="input-xlarge required"/>
                    </td>
                    <td class="tit" width="180px"><font color="red">*</font>患者性别：</td>
                    <td>
                            <%--<form:input path="patientSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
                        <form:select path="complaintMain.patientSex" class="input-medium">
                            <form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value"
                                          htmlEscape="false"/>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>患者年龄：</td>
                    <td>
                        <form:input path="complaintMain.patientAge" htmlEscape="false" maxlength="3"
                                    class="input-small required digits" onchange="num(this.value)"/>岁
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="hospital">
            <table class="table-form">
                <tr>
                    <td class="tit" width="180px"><font color="red">*</font>涉及医院所属地区：</td>
                    <td class="controls" width="462px">
                        <sys:treeselect id="area" name="area.id" value="${office.area.id}" labelName="area.name"
                                        labelValue="${ empty office.area.name ? complaintMainDetail.complaintMain.hospital.area.name :office.area.name}"
                                        title="区域" url="/sys/area/treeData" cssClass="required" dataMsgRequired="请选择区域"/>
                    </td>
                    <td class="tit" width="180px"><font color="red">*</font>涉及医院：</td>
                    <td class="controls" width="">
                        <sys:treeselect id="involveHospital" name="complaintMain.involveHospital"
                                        value="${complaintMainDetail.complaintMain.involveHospital}"
                                        labelName="${complaintMainDetail.complaintMain.hospital.name}"
                                        labelValue="${complaintMainDetail.complaintMain.hospital.name}"
                                        title="机构" url="/sys/office/treeData?type=1&officeType=2" isAll="true"
                                        cssClass="required" dataMsgRequired="请选择医院" allowClear="true"
                                        notAllowSelectParent="false" pid="area"/>
                        <span class="help-inline"> </span>
                    </td>

                </tr>
                <tr>
                    <td class="tit" width="180px"><font color="red">*</font>涉及科室：</td>
                    <td class="controls">
                        <%--<sys:treeselect id="involveDepartment" name="complaintMain.involveDepartment"--%>
                                        <%--value="${complaintMainDetail.complaintMain.involveDepartment}"--%>
                                        <%--labelName="${complaintMainDetail.complaintMain.department.name}"--%>
                                        <%--labelValue="${complaintMainDetail.complaintMain.department.name}"--%>
                                        <%--title="部门" url="/sys/office/treeData?type=2&officeType=2"--%>
                                        <%--pid="involveHospital" isAll="true" cssClass="required"--%>
                                        <%--dataMsgRequired="请选择科室" allowClear="true" notAllowSelectParent="true"--%>
                                        <%--disabled="true"/>--%>
                        <%--<span class="help-inline"> </span>--%>
                        <%--<form:select path="complaintMain.involveDepartment" class="input-medium" multiple="true" >--%>
                            <%--<form:options items="${fns:getDictList('department')}" itemLabel="label" itemValue="value"--%>
                                          <%--htmlEscape="false"/>--%>
                        <%--</form:select>--%>
                            <sys:treeselect id="involveDepartment" name="complaintMain.involveDepartment" value="${complaintMainDetail.complaintMain.involveDepartment}" labelName="testTree"
                                            labelValue="${complaintMainDetail.complaintMain.testTree}" title="涉及科室"
                                            url="/test/testTree/treeData?mold=2" isAll="true" allowClear="true" cssClass="required" dataMsgRequired="请选择科室"
                                            notAllowSelectParent="true" checked="true"/>
                    </td>
                    <td class="tit"><font color="red">*</font>涉及人员：</td>
                    <td>
                                <form:input path="complaintMain.involveEmployee" htmlEscape="false" class=" "
                                             value="${empty complaintMainDetail.complaintMain.employee.name?complaintMainDetail.complaintMain.involveEmployee:complaintMainDetail.complaintMain.employee.name}"/>
                        <%--<sys:treeselect id="involveEmployee" name="complaintMain.involveEmployee"--%>
                                        <%--value="${complaintMainDetail.complaintMain.involveEmployee}"--%>
                                        <%--labelName="${complaintMainDetail.complaintMain.employee.name}"--%>
                                        <%--labelValue="${complaintMainDetail.complaintMain.employee.name}"--%>
                                        <%--title="用户" url="/sys/office/treeData?type=3&officeType=2" isAll="true"--%>
                                        <%--pid="involveDepartment" cssClass="required" dataMsgRequired="请选择人员"--%>
                                        <%--allowClear="true" notAllowSelectParent="true"/>--%>
                        <span class="help-inline"> </span>
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="annex">
            <table style="height: 100px;" class="table-form">
                <tr style=" ">
                    <td style="text-align: center; width: 20px; font-weight: bolder;height: 50px;">患方材料：</td>
                    <input type="hidden" name="fjtype1" value="2">
                    <td style="width: 45px;">
                        <input type="hidden" id="files1" name="files1" htmlEscape="false" class="input-xlarge"
                               value="${files1}"/>
                        <input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files"
                                                                      uploadPath="/complaintDetail/Patient/apply" selectMultiple="true"/></div>
                    </td>
                </tr>
                <tr style=" ">
                    <td style="text-align: center; width: 20px; font-weight: bolder;height: 50px;">医方材料：</td>
                    <input type="hidden" name="fjtype2" value="1">
                    <td style="width: 45px;">
                        <input type="hidden" id="files2" name="files2" htmlEscape="false" class="input-xlarge"
                               value="${files2}"/>
                        <input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">
                        <div style="margin-bottom: 0px;margin-top: -45px;"><sys:ckfinder input="files2" type="files"
                                                                                         uploadPath="/complaintDetail/Doctor/apply" selectMultiple="true"/></div>
                    </td>
                </tr>
            </table>

        </div>

    </div>
    <table class="table-form">
        <tr>
            <td class="tit" width="180px"><font color="red">*</font>案件编号：</td>
            <td width="">
                <form:input path="complaintMain.caseNumber" htmlEscape="false" maxlength="20"
                            readonly="true" class="input-xlarge required"/>
            </td>
            <td class="tit" width="180px"><font color="red">*</font>来访日期：</td>
            <td>
                <input id="visitorDate" name="visitorDate" type="text" readonly="readonly" maxlength="20"
                       class="input-medium Wdate required"
                       value="${complaintMainDetail.visitorDate}"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
            </td>
        </tr>
        <tr>
            <td class="tit"><font color="red">*</font>投诉方式：</td>
            <td>
                    <%--<form:input path="complaintMode" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
                <form:select path="complaintMode" style='width:110px;text-align: center;'>
                    <form:option value="0">来电</form:option>
                    <form:option value="1">来访</form:option>
                    <form:option value="2">来信</form:option>
                    <form:option value="3">其他</form:option>
                </form:select>
            </td>
            <td class="tit"><font color="red">*</font>案件分类：</td>
            <td>
                <sys:treeselect id="isMajor" name="isMajor" value="${complaintMainDetail.isMajor}" labelName="typeName"
                                labelValue="${complaintMainDetail.typeName}" title="案件分类" cssClass="required"
                                url="/test/testTree/treeData?mold=1" variable="qitaanjian" allowClear="true" dataMsgRequired="请选择案件分类"
                                notAllowSelectParent="true" isAll="true"/>
                    <%--<form:select path="isMajor" style='width:110px;text-align: center;'>--%>
                    <%--<form:option value="1">是</form:option>--%>
                    <%--<form:option value="0">否</form:option>--%>
                    <%--</form:select>--%>
            </td>
        </tr>
        <tr id="qitaanjian" class="hide">
            <td class="tit">其他案件分类：</td>
            <td>
                <form:input path="otherType" htmlEscape="false" maxlength="20" class="input-xlarge "/>
            </td>
        </tr>
        <tr>
            <td class="tit"><font color="red">*</font>投诉纠纷概要：</td>
            <td colspan="3">
                <form:textarea path="summaryOfDisputes" htmlEscape="false" class="input-xlarge required"
                               style="margin: 0px; width: 938px; height: 125px;"/>
            </td>
        </tr>
        <tr>
            <td class="tit"><font color="red">*</font>诉求：</td>
            <td colspan="3">
                <form:textarea path="appeal" htmlEscape="false" class="input-xlarge required"
                               style="margin: 0px; width: 939px; height: 24px;"/>
            </td>
        </tr>
        <tr>
            <td class="tit"><font color="red">*</font>接待人员：</td>
            <td>
                    <%--<form:input path="receptionEmployee" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
                <sys:treeselect id="receptionEmployee" name="receptionEmployee"
                                value="${empty complaintMainDetail.receptionEmployee ? fns:getUser().id : complaintMainDetail.receptionEmployee}"
                                labelName="${complaintMainDetail.receptionEmployee}"
                                labelValue="${empty complaintMainDetail.jdEmployee.name ? fns:getUser().name : complaintMainDetail.jdEmployee.name}"
                                title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass="input-big required"
                                dataMsgRequired="请选择接待人" allowClear="true" notAllowSelectParent="true" isAll="true"/>
            </td>

            <td class="tit"><font color="red">*</font>接待时间：</td>
            <td>
                    <%--<form:input path="receptionDate" htmlEscape="false" maxlength="20" class="input-xlarge "/>--%>
                <input id="receptionDate" name="receptionDate" type="text" readonly="readonly" maxlength="20"
                       class="input-medium Wdate required"
                       value="${complaintMainDetail.receptionDate}"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
            </td>
        </tr>
        <tr>
                <%--<td class="tit"><font color="red">*</font>下一处理环节：</td>
                <td >
                    <form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge "/>
                </td>--%>
            <td class="tit"><font color="red">*</font>下一环节处理人：</td>
            <td>
                    <%--<form:input path="nextLinkMan" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
                <sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${complaintMainDetail.nextLinkMan}"
                                labelName="linkMan"
                                labelValue="${complaintMainDetail.linkEmployee.name}"
                                title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass="input-big required"
                                dataMsgRequired="请选择处理人" allowClear="true" notAllowSelectParent="true" isAll="true"/>
            </td>
        </tr>
    </table>

    <div class="form-actions">
        <shiro:hasPermission name="complaintdetail:complaintMainDetail:edit"><input id="btnSubmit"
                                                                                    class="btn btn-primary"
                                                                                    type="submit" value="保 存"
                                                                                    onclick="$('#flag').val('no'),removeCssClass()"/>&nbsp;</shiro:hasPermission>
        <shiro:hasPermission name="complaintdetail:complaintMainDetail:edit"><input id="btnSubmit"
                                                                                    class="btn btn-primary"
                                                                                    type="submit" value="下一步"
                                                                                    onclick="$('#flag').val('yes'),addCssClass()"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
    <%--<c:if test="${not empty complaintMainDetail.complaintMainDetailId}">
        <act:histoicFlow procInsId="${complaintMainDetail.complaintMain.act.procInsId}" />
    </c:if>--%>
</form:form>
</body>
</html>