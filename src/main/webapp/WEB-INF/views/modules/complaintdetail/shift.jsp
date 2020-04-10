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
<form:form id="inputForm" modelAttribute="complaintMainDetail" action="${ctx}/complaintdetail/complaintMainDetail/saveShift"
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
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="visitor">
            <table class="table-form">
        <tr>
            <td class="tit"><font color="red">*</font>转交人：</td>
            <td>
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
                                                                                    onclick="$('#flag').val('no')"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>