<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>投诉接待管理展示</title>
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
            ba();
        });
        function ba(){
            var tname = '${complaintMainDetail.typeName}';
            if(tname=="其他"){
                $("#qitaanjian").show();
            }
        }
    </script>
</head>
<body>
<form:form class="form-horizontal">
<sys:message content="${message}"/>
<fieldset>
    <legend>投诉接待详情</legend>
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
                <tr >
                    <td class="tit" width="270px">来访者姓名：</td>
                    <td width="300px">
                            ${complaintMainDetail.visitorName}
                    </td>
                    <td class="tit" width="300px">联系电话：</td>
                    <td >
                            ${complaintMainDetail.visitorMobile}
                    </td>
                </tr>
                <tr >
                    <td class="tit" width="270px">与患者关系：</td>
                    <td width="300px">
                            ${complaintMainDetail.patientRelation}
                    </td>
                    <td class="tit" width="300px">来访人数：</td>
                    <td >
                            ${complaintMainDetail.visitorNumber}
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="patient">
            <table class="table-form">
                <tr >
                    <td class="tit" width="270px">患者姓名：</td>
                    <td width="300px">
                            ${complaintMainDetail.complaintMain.patientName}
                    </td>
                    <td class="tit" width="300px">患者性别：</td>
                    <td >
                        <c:choose>
                            <c:when test="${complaintMainDetail.complaintMain.patientSex == '1'}">
                                男
                            </c:when>
                            <c:otherwise >
                                女
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr >
                    <td class="tit">患者年龄：</td>
                    <td >
                            ${complaintMainDetail.complaintMain.patientAge}
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="hospital">
            <table class="table-form">
                <tr >
                    <td class="tit" width="270px">涉及医院所属地区：</td>
                    <td width="300px">
                            ${empty office.area.name ? complaintMainDetail.complaintMain.hospital.area.name :office.area.name}
                    </td>
                    <td class="tit" width="300px">涉及医院：</td>
                    <td >
                            ${complaintMainDetail.complaintMain.hospital.name}
                    </td>
                </tr>
                <tr >
                    <td class="tit">涉及科室：</td>
                    <td>
                        ${complaintMainDetail.complaintMain.testTree}
                    </td>
                    <td class="tit">涉及人员：</td>
                    <td >
                            ${empty complaintMainDetail.complaintMain.employee.name?complaintMainDetail.complaintMain.involveEmployee:complaintMainDetail.complaintMain.employee.name}
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
                                                                      uploadPath="/complaintDetail/Patient/apply" selectMultiple="true" readonly="true"/></div>
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
                                                                                         uploadPath="/complaintDetail/Doctor/apply" selectMultiple="true" readonly="true"/></div>
                    </td>
                </tr>
            </table>

        </div>

    </div>

    <table class="table-form">
        <tr>
            <td class="tit" width="270px">案件编号：</td>
            <td width="300px">
                    ${complaintMainDetail.complaintMain.caseNumber}
            </td>
            <td class="tit" width="300px">来访日期：</td>
            <td>
                    ${complaintMainDetail.visitorDate}
            </td>
        </tr>
        <tr>
            <td class="tit">投诉方式：</td>
            <c:choose>
                <c:when test="${complaintMainDetail.complaintMode == '0'}">
                    <td>来电</td>
                </c:when>
                <c:when test="${complaintMainDetail.complaintMode == '1'}">
                    <td>来访</td>
                </c:when>
                <c:when test="${complaintMainDetail.complaintMode == '2'}">
                    <td>来信</td>
                </c:when>
                <c:when test="${complaintMainDetail.complaintMode == '3'}">
                    <td>其他</td>
                </c:when>
            </c:choose>
            <%--<td class="tit">是否重大：</td>--%>
            <%--<c:choose>--%>
                <%--<c:when test="${complaintMainDetail.isMajor == '1'}">--%>
                    <%--<td>是</td>--%>
                <%--</c:when>--%>
                <%--<c:otherwise>--%>
                    <%--<td>否</td>--%>
                <%--</c:otherwise>--%>
            <%--</c:choose>--%>
            <td class="tit">案件分类：</td>
            <td>${complaintMainDetail.typeName}</td>
        </tr>
        <tr id="qitaanjian" class="hide">
            <td class="tit">其他案件分类：</td>
            <td>${complaintMainDetail.otherType}</td>
        </tr>
        <tr>
            <td class="tit">投诉纠纷概要：</td>
            <td colspan="3" style="word-break:break-all;white-space: normal;">
                    ${complaintMainDetail.summaryOfDisputes}
            </td>
        </tr>
        <tr>
            <td class="tit">诉求：</td>
            <td colspan="3" style="word-break:break-all;white-space: normal;">
                    ${complaintMainDetail.appeal}
            </td>
        </tr>
        <tr>
            <td class="tit">接待人员：</td>
            <td>
                    ${complaintMainDetail.jdEmployee.name}
            </td>

            <td class="tit">接待时间：</td>
            <td>
                    ${complaintMainDetail.receptionDate}
            </td>
        </tr>
        <tr>
            <td class="tit">下一环节处理人：</td>
            <td>
                    ${complaintMainDetail.linkEmployee.name}
            </td>
        </tr>
    </table>
</fieldset>
<c:if test="${empty show2}">
    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</c:if>
</form:form>
</body>
</html>
