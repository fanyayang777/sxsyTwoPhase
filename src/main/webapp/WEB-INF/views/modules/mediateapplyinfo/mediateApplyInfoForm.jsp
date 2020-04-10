<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>调节申请管理</title>
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
		<li><a href="${ctx}/mediateapplyinfo/mediateApplyInfo/">调节申请列表</a></li>
		<li class="active"><a href="${ctx}/mediateapplyinfo/mediateApplyInfo/form?id=${mediateApplyInfo.id}">调节申请<shiro:hasPermission name="mediateapplyinfo:mediateApplyInfo:edit">${not empty mediateApplyInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="mediateapplyinfo:mediateApplyInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mediateApplyInfo" action="${ctx}/mediateapplyinfo/mediateApplyInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">申请人：</label>
			<div class="controls">
				<form:input path="applyer" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">与患者关系：</label>
			<div class="controls">
				<form:input path="patientRelation" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">患者姓名：</label>
			<div class="controls">
				<form:input path="patientName" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">患者性别：</label>
			<div class="controls">
				<form:input path="patientSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">患者年龄：</label>
			<div class="controls">
				<form:input path="patientAge" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">患方电话：</label>
			<div class="controls">
				<form:input path="patientMobile" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">涉及医院：</label>
			<div class="controls">
				<sys:treeselect id="involveHospital" name="involveHospital" value="${mediateApplyInfo.involveHospital}" labelName="" labelValue="${mediateApplyInfo.}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">纠纷概要：</label>
			<div class="controls">
				<form:input path="summaryOfDisputes" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">当事人申请事项：</label>
			<div class="controls">
				<form:input path="applyMatter" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请医院：</label>
			<div class="controls">
				<form:input path="applyHospital" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">代理人：</label>
			<div class="controls">
				<form:input path="agent" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">医方电话：</label>
			<div class="controls">
				<form:input path="hospitalMobile" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请类型 1患方2医方：</label>
			<div class="controls">
				<form:input path="applyType" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="mediateapplyinfo:mediateApplyInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>