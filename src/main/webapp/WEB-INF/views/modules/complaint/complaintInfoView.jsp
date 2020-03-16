<%@page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>投诉接待管理详情</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#inputForm").validate({
                submitHandler: function(form){
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });


            var value='${complaintInfo.handleWay}';
            if(value==''){
                value = 0;
            }
            next(value);
        });
        function next(value) {
            var name='${fns:getUser().company.officeType}';
            if(name=='2'){//医院
                $("#pass").hide();
                $("#result").hide();
                $("#yq").hide();
                $("#method").hide();
            }else{
                $("#pass").show();
                $("#result").show();
                $("#yq").show();
                $("#method").show();
            }


            if(value==1){
                $("<td id='shiftBody' class='tit'>转办科室：</td>").insertAfter("#handleWay");
                $("#shiftHandle").show();
                $("#statusBody").remove();
                $("#statusHandle").hide();
                // document.getElementById("shiftHead").style.display="inline";
                // document.getElementById("shiftHandle").style.display="inline";
            }else  if(value==0){
                $("#shiftBody").remove();
                $("#shiftHandle").hide();
                $("<td id='statusBody' class='tit'>状态：</td>").insertAfter("#handleWay");
                $("#statusHandle").show();
            }else{
                $("#shiftBody").remove();
                $("#shiftHandle").hide();
                // document.getElementById("shiftHead").style.display="none";
                // document.getElementById("shiftHandle").style.display="none";
                $("#statusBody").remove();
                $("#statusHandle").hide();
            }
        }
    </script>
</head>
<body>
<form:form class="form-horizontal" id="inputForm" modelAttribute="complaintInfo" action="${ctx}/complaint/complaintInfo/audit?node=${node}" method="post">
    <input type="hidden" id="flag" name="flag"/>
    <input type="hidden" id="status" name="status"/>
    <form:hidden path="complaintMainId"/>
    <form:hidden path="complaintMain.complaintMainId"/>
    <form:hidden path="complaintMain.act.taskId"/>
    <form:hidden path="complaintMain.act.taskName"/>
    <form:hidden path="complaintMain.act.taskDefKey"/>
    <form:hidden path="complaintMain.procInsId"/>
    <form:hidden path="complaintMain.act.procDefId"/>
    <form:hidden path="createBy.id" />
    <form:hidden path="createBy.loginName" />
    <form:hidden path="reportRegistration.reportRegistrationId" />
    <sys:message content="${message}"/>
<br>
    <legend>医院投诉接待详情</legend>
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
                    <td class="tit" width="160px"><font color="red">*</font>访客姓名：</td>
                    <td width="476px">
                            ${complaintInfo.visitorName}
                    </td>
                    <td class="tit" width="180px">访客电话：</td>
                    <td>
                            ${complaintInfo.visitorMobile}
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>与患者关系：</td>
                    <td>
                        ${fns:getDictLabel(complaintInfo.patientRelation,'patient_relation','未知')}
                    </td>
                    <td class="tit"><font color="red">*</font>来访人数：</td>
                    <td>
                            ${complaintInfo.visitorNumber}
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="patient">
            <table class="table-form">
                <tr>
                    <td class="tit" width="160px"><font color="red">*</font>患者姓名：</td>
                    <td width="476px">
                            ${complaintInfo.patientName}
                    </td>
                    <td class="tit" width="180px"><font color="red">*</font>患者性别：</td>
                    <td>
                            <%--<form:input path="patientSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
                        ${fns:getDictLabel(complaintInfo.patientSex,'sex','未知')}
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>患者年龄：</td>
                    <td>
                            ${complaintInfo.patientAge}
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="hospital">
            <table class="table-form">
                <tr>
                    <td class="tit" width="160px"><font color="red">*</font>涉及医院：</td>
                    <td width="476px">
                        ${complaintInfo.hospitalName}
                    </td>
                    <td class="tit" width="180px"><font color="red">*</font>涉及科室：</td>
                    <td>
                            <%--<sys:treeselect id='involveDepartment' name='involveDepartment' value='${complaintInfo.involveDepartment}' labelName='departmentName' labelValue='${complaintInfo.departmentName}' title='部门' url='/sys/office/treeData?type=2&officeType=2'--%>
                            <%--pid='involveHospital' isAll='true' cssClass='required' dataMsgRequired='请选择科室' allowClear='true' notAllowSelectParent='true' disabled='true'/>--%>
                                    ${complaintInfo.testTree}
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>涉及人员：</td>
                    <td>
                        ${empty complaintInfo.employeeName?complaintInfo.involveEmployee:complaintInfo.employeeName}
                            <%--<sys:treeselect id="involveEmployee" name="involveEmployee" value="${complaintInfo.involveEmployee}" labelName="employeeName" labelValue="${complaintInfo.employeeName}"--%>
                            <%--title="用户" url="/sys/office/treeData?type=3&officeType=2" pid="involveDepartment" isAll="true" cssClass="required" dataMsgRequired="请选择人员" allowClear="true" notAllowSelectParent="true"/>--%>
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
                                                                      uploadPath="/complaint/Patient/apply" selectMultiple="true" readonly="true"/></div>
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
                                                                                         uploadPath="/complaint/Patient/apply" selectMultiple="true" readonly="true"/></div>
                    </td>
                </tr>
            </table>

        </div>
    </div>
    <table class="table-form">
        <tr>
            <td class="tit" width="160px"><font color="red">*</font>案件编号：</td>
            <td width="476px">
                    ${complaintInfo.caseNumber}
            </td>
            <td class="tit" width="180px"><font color="red">*</font>来访日期：</td>
            <td>
                    <%--<form:input path="visitorDate" htmlEscape="false" maxlength="10" class="input-xlarge "/>--%>
                    ${complaintInfo.visitorDate}
            </td>
        </tr>
        <tr>
            <td class="tit"><font color="red">*</font>投诉方式：</td>
            <td>
                    <%--<form:input path="complaintMode" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
                <c:choose>
                    <c:when test="${complaintInfo.complaintMode == '0'}">
                        来电
                    </c:when>
                    <c:when test="${complaintInfo.complaintMode == '1'}">
                        来访
                    </c:when>
                    <c:when test="${complaintInfo.complaintMode == '2'}">
                        来信
                    </c:when>
                    <c:when test="${complaintInfo.complaintMode == '3'}">
                        其他
                    </c:when>
                </c:choose>
            </td>
            <td class="tit"><font color="red">*</font>是否重大：</td>
            <td>
                <c:choose>
                    <c:when test="${complaintInfo.isMajor == '0'}">
                        否
                    </c:when>
                    <c:when test="${complaintInfo.isMajor == '1'}">
                        是
                    </c:when>
                </c:choose>
            </td>

        </tr>
        <tr>
            <td class="tit">投诉类别：</td>
            <td>
                ${complaintInfo.typeName}
            </td>
        </tr>
        <tr>
            <td class="tit"><font color="red">*</font>投诉纠纷概要：</td>
            <td colspan="3">
                    ${complaintInfo.summaryOfDisputes}
            </td>
        </tr>
        <tr>
            <td class="tit"><font color="red">*</font>诉求：</td>
            <td colspan="3">
                    ${complaintInfo.appeal}
            </td>
        </tr>
        <tr>
            <td class="tit">处理方式：</td>
            <td id="handleWay">
                <c:choose>
                    <c:when test="${complaintInfo.handleWay == '0'}">
                        当面处理
                    </c:when>
                    <c:when test="${complaintInfo.handleWay == '1'}">
                        转办处理
                    </c:when>
                    <c:when test="${complaintInfo.handleWay == '2'}">
                        转医调委
                    </c:when>
                    <c:when test="${complaintInfo.handleWay == '3'}">
                        法院诉讼
                    </c:when>
                    <c:when test="${complaintInfo.handleWay == '4'}">
                        行政调解
                    </c:when>
                </c:choose>
            </td>
            <td id="shiftHandle">
                            ${fns:getDictLabel(complaintInfo.shiftHandle, 'department','' )}
            </td>

            <td id="statusHandle">
                <c:choose>
                    <c:when test="${complaintInfo.status == '0'}">
                        处理中
                    </c:when>
                    <c:when test="${complaintInfo.status == '1'}">
                        协调中
                    </c:when>
                    <c:when test="${complaintInfo.status == '2'}">
                        结案
                    </c:when>
                </c:choose>
            </td>


        </tr>
        <c:if test="${complaintInfo.handleWay != '2'}"><tr>
            <td class="tit"><font color="red">*</font>处理经过</td>
            <td colspan="3">
                    ${complaintInfo.handlePass}
            </td>
        </tr>
            <tr>
                <td class="tit"><font color="red">*</font>处理结果</td>
                <td colspan="3">
                        ${complaintInfo.handleResult}
                </td>
            </tr>
        </c:if>
        <tr>
            <td class="tit"><font color="red">*</font>接待人员：</td>
            <td>
                    <%--<form:input path="receptionEmployee" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
                ${complaintInfo.employee.name}
            </td>

            <td class="tit"><font color="red">*</font>接待时间：</td>
            <td>
                    <%--<form:input path="receptionDate" htmlEscape="false" maxlength="20" class="input-xlarge "/>--%>
                ${complaintInfo.receptionDate}
            </td>
        </tr>

        <tr>
                <%--<td class="tit"><font color="red">*</font>是否进入医调委调解：</td>--%>
                <%--<td>--%>
                <%--<form:select id="isMediate" path="isMediate" style='width:110px;text-align: center;' >--%>
                <%--<form:option value="0">否</form:option>--%>
                <%--<form:option value="1">是</form:option>--%>
                <%--</form:select>--%>
                <%--</td>--%>
                <%--<td class="hidden"><font color="red">*</font>下一处理环节：</td>--%>
                <%--<td class="hidden">--%>
                <%--<form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
                <%--</td>--%>
            <td class="tit"><font color="red">*</font>下一环节处理人：</td>
            <td>
                    <%--<form:input path="nextLinkMan" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
                ${complaintInfo.link.name}
            </td>
        </tr>
    </table>
</br>
    <table class="table-form">
        <c:if test="${node eq 'sjy' }">
            <div class="control-group">
                <label class="control-label">回复内容:</label>
                <div class="controls">
                    <form:textarea path="complaintMain.act.comment" htmlEscape="false" rows="4" maxlength="200" class="required input-xxlarge"/>
                </div>
            </div>
        </c:if>
        <c:if test="${node eq 'fpy' }">
            <div class="control-group">
                <label class="control-label">调解员:</label>
                <div class="controls">
                    <sys:treeselect id="nextLinkMan" name="reportRegistration.nextLinkMan" value="${complaintInfo.reportRegistration.nextLinkMan}" labelName="link.name" labelValue="${complaintInfo.reportRegistration.nextLinkMan}"
                                    title="用户" url="/sys/office/treeData?type=3&officeType=1" isAll="true"  cssClass="required" dataMsgRequired="请选择下一环节处理人" allowClear="true" notAllowSelectParent="true"/>
                </div>
            </div>
            </c:if>


    </table>
</fieldset>

    <c:if test="${empty show2}">
        <div class="form-actions">
            <c:if test="${node eq 'sjy' }">
                <input id="btnSubmit" class="btn btn-success" type="submit" value="通 过" onclick="$('#status').val('0')"/>&nbsp;
                <input id="btnSubmit" class="btn btn-inverse" type="submit" value="驳 回" onclick="$('#status').val('1')"/>&nbsp;
            </c:if>
            <c:if test="${node eq 'fpy' }">
                <input id="btnSubmit" class="btn btn-success" type="submit" value="通 过" onclick="$('#status').val('0')"/>&nbsp;
            </c:if>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
    </c:if>
    <act:histoicFlow procInsId="${complaintInfo.complaintMain.procInsId}"/>
</form:form>
</body>
</html>
