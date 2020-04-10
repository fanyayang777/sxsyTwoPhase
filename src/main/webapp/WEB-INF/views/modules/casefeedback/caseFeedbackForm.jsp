<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>案件反馈管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
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
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/casefeedback/caseFeedback/">案件反馈列表</a></li>
		<li class="active"><a href="${ctx}/casefeedback/caseFeedback/form?id=${caseFeedback.id}">案件反馈<shiro:hasPermission name="casefeedback:caseFeedback:edit">${not empty caseFeedback.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="casefeedback:caseFeedback:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="caseFeedback" action="${ctx}/casefeedback/caseFeedback/save" method="post" class="form-horizontal">
		<form:hidden path="feedbackId"/>
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
		<div class="control-group">
			<label class="control-label">反馈给人员：</label>
			<div class="controls">
				<sys:treeselect id="feedbackEmp" name="feedbackEmp" value="${caseFeedback.feedbackEmp}" labelName="" labelValue="${caseFeedback.feedbackEmps.name}"
					title="用户" url="/sys/office/treeData?type=3&officeType=1" isAll="false" cssClass="input-xxlarge required" allowClear="true" notAllowSelectParent="true" checked="true" dataMsgRequired="必填信息"/>
				<span class="helper"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">反馈给部门：</label>
			<div class="controls">
				<sys:treeselect id="feedbackOffice" name="feedbackOffice" value="${caseFeedback.feedbackOffice}" labelName="" labelValue="${caseFeedback.feedbackOffices.name}"
					title="机构" url="/sys/office/treeData?type=1&officeType=2" isAll="true" cssClass="input-xxlarge required" allowClear="true" notAllowSelectParent="true" checked="true" dataMsgRequired="必填信息"/>
				<span class="helper"><font color="red">*</font></span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="casefeedback:caseFeedback:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<act:histoicFlow procInsId="${caseFeedback.complaintMain.procInsId}" />
	</form:form>
</body>
</html>