<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>台账信息展示管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在查询，请稍等...');
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
	<li><a href="${ctx}/machine/machineAccount/">台账信息展示列表</a></li>
	<li class="active"><a href="${ctx}/machine/machineAccount/form?id=${machineAccount.id}">台账信息展示<shiro:hasPermission name="machine:machineAccount:edit">${not empty machineAccount.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="machine:machineAccount:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="machineAccount" action="${ctx}/machine/machineAccount/save" method="post" class="form-horizontal">
	<form:hidden path="machineAccountId"/>
	<form:hidden path="createBy"/>
	<form:hidden path="createDate"/>
	<form:hidden path="delFlag" value="0"/>
	<form:hidden path="complaintMainId"/>
	<sys:message content="${message}"/>
	<fieldset>
		<table class="table-form">
			<tr >
				<td class="tit"><font color="red">*</font>报案时间：</td>
				<td >
					<input name="reportingTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						   value="${machineAccount.reportingTime}"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				</td>
				<td class="tit"><font color="red">*</font>部门：</td>
				<td >
					<sys:treeselect id="deptId" name="deptId" value="${machineAccount.deptId}" labelName="" labelValue="${machineAccount.deptId}"
									title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				</td>
			</tr>
			<tr >
				<td class="tit"><font color="red">*</font>调解员：</td>
				<td >
					<sys:treeselect id="mediatorId" name="mediatorId" value="${machineAccount.mediatorId}" labelName="" labelValue="${machineAccount.mediatorId}"
									title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				</td>
				<td class="tit"><font color="red">*</font>患者名称：</td>
				<td >
					<form:input path="patientName" htmlEscape="false" maxlength="10" class="input-xlarge "/>
				</td>
			</tr>
			<tr>
				<td class="tit"><font color="red">*</font>医院名称：</td>
				<td >
					<form:input path="hospitalId" htmlEscape="false" maxlength="32" class="input-xlarge "/>
				</td>
				<td class="tit">重大：</td>
				<td >
					<form:input path="major" htmlEscape="false" maxlength="50" class="input-xlarge "/>
				</td>
			</tr>
			<tr>

				<td class="tit">保险公司名称：</td>
				<td >
					<form:input path="insuranceCompany" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				</td>

				<td class="tit">保单号：</td>
				<td >
					<form:input path="policyNumber" htmlEscape="false" maxlength="50" class="input-xlarge "/>
				</td>
			<tr>
			<tr>
				<td class="tit">起保日期：</td>
				<td >
					<input name="startInsuranceTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						   value="${machineAccount.startInsuranceTime}"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				</td>

				<td class="tit">纠纷发生日期：</td>
				<td >
					<input name="disputesTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						   value="${machineAccount.disputesTime}"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				</td>
			</tr>
			<tr>

				<td class="tit">出险日期：</td>
				<td >
					<input name="riskTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						   value="${machineAccount.riskTime}"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				</td>

				<td class="tit">纠纷概要：</td>
				<td >
					<form:input path="summaryOfDisputes" htmlEscape="false" maxlength="500" class="input-xlarge "/>
				</td>
			</tr>
			<tr>

				<td class="tit">是否重大：</td>
				<td >
					<form:select path="isMajor" >
						<form:option value="1">是</form:option>
						<form:option value="0">否</form:option>
					</form:select>
				</td>

				<td class="tit">诊疗方式：</td>
				<td >
					<form:input path="treatmentMode" htmlEscape="false" maxlength="500" class="input-xlarge "/>
				</td>
			</tr>
			<tr>

				<td class="tit">治疗结果：</td>
				<td >
					<form:input path="treatmentResult" htmlEscape="false" maxlength="500" class="input-xlarge "/>
				</td>

				<td class="tit">患方反映焦点：</td>
				<td >
					<form:input path="patientsReflectFocus" htmlEscape="false" maxlength="500" class="input-xlarge "/>
				</td>
			</tr>
			<tr>

				<td class="tit">相关专业：</td>
				<td >
					<form:input path="relatedMajor" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				</td>

				<td class="tit">评估时间：</td>
				<td >
					<input name="assessTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						   value="${machineAccount.assessTime}"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				</td>
			</tr>
			<tr>

				<td class="tit">评估号：</td>
				<td >
					<form:input path="assessNumber" htmlEscape="false" maxlength="20" class="input-xlarge "/>
				</td>

				<td class="tit">责任比例：</td>
				<td >
					<form:input path="dutyRatio" htmlEscape="false" maxlength="50" class="input-xlarge "/>
				</td>
			</tr>
			<tr>

				<td class="tit">反馈时间：</td>
				<td >
					<input name="feedbackTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						   value="${machineAccount.feedbackTime}"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				</td>

				<td class="tit">协议号：</td>
				<td >
					<form:input path="agreementNumber" htmlEscape="false" maxlength="20" class="input-xlarge "/>
				</td>
			</tr>
			<tr>

				<td class="tit">签署协议/判决时间：</td>
				<td >
					<input name="ratifyAccord" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						   value="${machineAccount.ratifyAccord}"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				</td>

				<td class="tit">协议盖章时间：</td>
				<td >
					<input name="agreementStampTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						   value="${machineAccount.agreementStampTime}"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				</td>
			</tr>
			<tr>

				<td class="tit">协议金额：</td>
				<td >
					<form:input path="agreementAmount" htmlEscape="false" maxlength="20" class="input-xlarge "/>
				</td>

				<td class="tit">保险金额：</td>
				<td >
					<form:input path="insuranceAmount" htmlEscape="false" maxlength="20" class="input-xlarge "/>
				</td>
			</tr>
			<tr>

				<td class="tit">交理赔时间：</td>
				<td >
					<input name="claimSettlementTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						   value="${machineAccount.claimSettlementTime}"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				</td>

				<td class="tit">赔付时间：</td>
				<td >
					<input name="compensateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						   value="${machineAccount.compensateTime}"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				</td>
			</tr>
			<tr>

				<td class="tit">流转天数：</td>
				<td >
					<form:input path="flowDays" htmlEscape="false" maxlength="20" class="input-xlarge "/>
				</td>

				<td class="tit">移交人：</td>
				<td >
					<form:input path="handOver" htmlEscape="false" maxlength="20" class="input-xlarge "/>
				</td>
			</tr>
			<tr>

				<td class="tit">归档时间：</td>
				<td >
					<input name="archiveTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						   value="${machineAccount.archiveTime}"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				</td>

				<td class="tit">卷宗编号：</td>
				<td >
					<form:input path="fileNumber" htmlEscape="false" maxlength="20" class="input-xlarge "/>
				</td>
			</tr>
			<tr>

				<td class="tit">备注：</td>
				<td >
					<form:input path="remark" htmlEscape="false" maxlength="500" class="input-xlarge "/>
				</td>
			</tr>
		</table>
	</fieldset>
	<div class="form-actions">
		<shiro:hasPermission name="machine:machineAccount:edit">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存"/>&nbsp;
		</shiro:hasPermission>
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>


</form:form>
</body>
</html>