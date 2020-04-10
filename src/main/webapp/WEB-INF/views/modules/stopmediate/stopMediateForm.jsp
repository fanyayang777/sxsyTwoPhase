<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>终止调解管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					var aa=$("#export").val();
					if(aa!='yes'){
						loading('正在提交，请稍等...');
					}
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


		function exportWord() {
			var aa=$("#export").val();
			var path="${ctx}/stopmediate/stopMediate/exportWord";
			$.post(path,{'stopMediateId':'${stopMediate.stopMediateId}','export':aa,"print":"true"},function(res){
				if(res.data.url!='' && res.data.url!=undefined){
					var url='${pageContext.request.contextPath}'+res.data.url;
					<%--window.location.href='${pageContext.request.contextPath}'+res.data.url ;--%>
					windowOpen(url,"pdf",1500,700);
				}else{
					alertx("请先保存数据,在进行打印!");
				}
			},"json");
		}

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<%--<li><a href="${ctx}/stopmediate/stopMediate/">终止调解列表</a></li>--%>
			<li><a href="${ctx}${stopMediate.power}">${stopMediate.info}</a></li>
			<li class="active">
			<a href="${ctx}/stopmediate/stopMediate/form?id=${stopMediate.id}">终止调解
		</a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="stopMediate" action="${ctx}/stopmediate/stopMediate/save" method="post" class="form-horizontal">
		<form:hidden path="stopMediateId"/>
		<form:hidden path="createDate"/>
		<form:hidden path="createBy"/>
        <form:hidden path="complaintMainId"/>
        <form:hidden path="complaintMain.complaintMainId"/>
		<form:hidden path="complaintMain.act.taskId" />
		<form:hidden path="complaintMain.act.taskName"/>
		<form:hidden path="complaintMain.act.taskDefKey"/>
		<form:hidden path="complaintMain.act.procInsId"/>
		<form:hidden path="complaintMain.act.procDefId"/>
		<form:hidden path="complaintMain.procInsId"/>
		<input type="hidden"  id="export" name="export"/>
		<input type="hidden"  id="flag" name="flag"/>
		<input type="hidden" id="urlRegistration" name="urlRegistration" value="${url1}">
		<input type="hidden" id="urlAuditacceptance" name="urlAuditacceptance" value="${url2}">
		<input type="hidden" id="urlInvestigateEvidence" name="urlInvestigateEvidence" value="${url3}">
		<input type="hidden" id="urlAssessAppraisal" name="urlAssessAppraisal" value="${url4}">
		<input type="hidden" id="urlAssessApply" name="urlAssessApply" value="${url5}">
		<input type="hidden" id="urlAssessAudit" name="urlAssessAudit" value="${url6}">
		<input type="hidden" id="urlMediateEvidence" name="urlMediateEvidence" value="${url7}">
		<input type="hidden" id="urlReachMediate" name="urlReachMediate" value="${url8}">
		<input type="hidden" id="urlSignAgreement" name="urlSignAgreement" value="${url9}">
		<input type="hidden" id="urlPerformAgreement" name="urlPerformAgreement" value="${url10}">
		<sys:message content="${message}"/>
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active">
			<table class="table-form">
				<tr>
					<td class="tit">患者姓名</td>
					<td>
						<form:input path="patientName" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
					</td>
					<td class="tit">涉及医院</td>
					<td>
						<sys:treeselect id="involveHospital" name="involveHospital" value="${empty stopMediate.involveHospital?stopMediate.complaintMain.involveHospital:stopMediate.involveHospital}" labelName="" labelValue="${empty stopMediate.involveHospital?stopMediate.hospital.name:stopMediate.complaintMain.hospital.name}"
										title="机构" url="/sys/office/treeData?type=1&officeType=2" isAll="true" cssClass="required" allowClear="true" notAllowSelectParent="true" dataMsgRequired="必填信息"/>
					</td>
				</tr>
				<tr>
					<td>终止日期</td>
					<td>
						<input name="stopTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
							   value="${stopMediate.stopTime}"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
					</td>
				</tr>
				<tr>
					<td>终止调解描述</td>
					<td colspan="3">
						<form:textarea path="stopDescribe" htmlEscape="false" class="input-xlarge required" style="margin: 0px; width: 938px; height: 125px;"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
		<table class="table-form">
			<tr>
				<td class="tit">下一环节处理人</td>
				<td>
					<sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${stopMediate.nextLinkMan}" labelName="nextLink" labelValue="${stopMediate.linkEmployee.name}"
									title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass="" allowClear="true" notAllowSelectParent="true" dataMsgRequired="必填信息"/>
				</td>
			</tr>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="stopmediate:stopMediate:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#flag').val('no'),$('#export').val('no')"/>&nbsp;
			</shiro:hasPermission>
			<shiro:hasPermission name="stopmediate:stopMediate:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="下一步" onclick="$('#flag').val('yes'),$('#export').val('no')"/>&nbsp;
			</shiro:hasPermission>
			<shiro:hasPermission name="stopmediate:stopMediate:edit">
				<input id="export" type = "submit" class="btn btn-primary" value = "导出" onclick="$('#export').val('yes')"/>
			</shiro:hasPermission>
			<input id="doctorPrint" class="btn btn-primary" type="button" value="打 印" onclick="$('#export').val('yes'); exportWord();"/><%--promptx('打印文件','打印机名称',document.getElementById('inputForm').action)--%>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<act:histoicFlow procInsId="${stopMediate.complaintMain.procInsId}" />
	</form:form>
</body>
</html>