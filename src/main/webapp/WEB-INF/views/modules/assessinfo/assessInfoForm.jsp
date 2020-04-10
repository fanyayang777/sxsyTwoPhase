<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>案件评价管理</title>
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
    <li><a href="${ctx}/assessinfo/assessInfo/">案件评价列表</a></li>
    <li class="active"><a href="${ctx}/assessinfo/assessInfo/form?id=${assessInfo.id}">案件评价<shiro:hasPermission
            name="assessinfo:assessInfo:edit">${not empty assessInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="assessinfo:assessInfo:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="assessInfo" action="${ctx}/assessinfo/assessInfo/save" method="post"
           class="form-horizontal">
    <form:hidden path="assessId"/>
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
            <a href="#evaluate" data-toggle="tab">案件评价</a>
        </li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="evaluate">
            <table class="table-form">
                <tr>
                    <td class="tit">评价人</td>
                    <td>
                        <sys:treeselect id="appraiser" name="appraiser" value="${empty assessInfo.appraiser ? fns:getUser().id : assessInfo.appraiser}" labelName=""
                                        labelValue="${empty assessInfo.user.name ? fns:getUser().name : assessInfo.user.name}"
                                        title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass="required"
                                        allowClear="true"
                                        notAllowSelectParent="true" dataMsgRequired="必填信息"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">评价时间</td>
                    <td>
                        <input name="assessTime" type="text" readonly="readonly" maxlength="20"
                               class="input-medium Wdate required"
                               value="${assessInfo.assessTime}"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">评价分数</td>
                    <td>
                        <form:input path="assessGrade" htmlEscape="false" maxlength="3" class="input-xlarge required"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">评价内容</td>
                    <td>
                        <form:textarea path="assessContent" htmlEscape="false" class="input-xlarge required"
                                       style="margin: 0px; width: 500px; height: 125px;"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <table class="table-form">
        <tr>
            <%--<td class="tit">下一环节</td>--%>
            <%--<td>--%>
                <%--<form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge"/>--%>
            <%--</td>--%>
            <%--<td class="tit">下一环节处理人</td>
            <td>
                <sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${assessInfo.nextLinkMan}" labelName=""
                                labelValue="${assessInfo.linkEmployee.name}"
                                title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass="required"
                                allowClear="true" notAllowSelectParent="true"  dataMsgRequired="必填信息"/>
            </td>--%>
        </tr>
    </table>
    <div class="form-actions">
        <shiro:hasPermission name="assessinfo:assessInfo:edit"><input id="btnSubmit" class="btn btn-primary"
                                                                      type="submit" value="保 存"
                                                                      onclick="$('#flag').val('no')"/>&nbsp;</shiro:hasPermission>
        <shiro:hasPermission name="assessinfo:assessInfo:edit"><input id="btnSubmit" class="btn btn-primary"
                                                                      type="submit" value="归档"
                                                                      onclick="$('#flag').val('yes')"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
    <act:histoicFlow procInsId="${assessInfo.complaintMain.procInsId}" />
</form:form>
</body>
</html>