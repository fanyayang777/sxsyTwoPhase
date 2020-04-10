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
	<form:form  class="form-horizontal">
		<sys:message content="${message}"/>
		<fieldset>
			<legend>台账详情</legend>
			<table class="table-form">
				<tr>
					<td class="tit">报案时间：</td>
					<td>${machineAccount.reportingTime}</td>
					<td class="tit">部门名称：</td>
							<td>${machineAccount.office.name}</td>
					<td class="tit">调解员</td>
							<td>${machineAccount.user.name}</td>
				</tr>
				<tr>
					<td class="tit">患者名称：</td>
					<td >${machineAccount.patientName}</td>
					<td class="tit">医院名称：</td>
					<td >${machineAccount.hospital.name}</td>
					<%--<td class="tit">重大：</td>
					<td >${machineAccount.major}</td>--%>
				</tr>
				<tr>
					<td class="tit">保险公司：</td>
					<td>${fns:getDictLabel(machineAccount.insuranceCompany, 'sys_office_form',machineAccount.insuranceCompany)}
							</td>
					<td class="tit">保单号：</td>
					<td>${machineAccount.policyNumber}</td>
					<td class="tit">起保日期：</td>
					<td>${machineAccount.startInsuranceTime}</td>
				</tr>
				<tr>
					<td class="tit">纠纷发生日期：</td>
					<td>${machineAccount.disputesTime}</td>
					<td class="tit">出险日期：</td>
					<td>${machineAccount.riskTime}</td>
					<td class="tit">是否重大：</td>
					<c:choose>
						<c:when test="${machineAccount.isMajor=='1' }">
							<td>是</td>
						</c:when>
						<c:otherwise>
							<td>否</td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td class="tit">纠纷概要：</td>
					<td colspan="5">${machineAccount.summaryOfDisputes}</td>

				</tr>
				<tr>
					<td class="tit">诊疗方式：</td>
					<td colspan="1">${machineAccount.treatmentMode}</td>
					<td class="tit">治疗结果：</td>
					<td colspan="1">${machineAccount.treatmentResult}</td>
					<td class="tit">涉及专业：</td>
					<td colspan="1">${machineAccount.relatedMajor}</td>
				</tr>
				<tr>
					<td class="tit">患者反映焦点：</td>
					<td colspan="5">
							${machineAccount.patientsReflectFocus}
					</td>
				</tr>
				<tr>
					<td class="tit">评估时间：</td>
					<td colspan="1">
							${machineAccount.assessTime}
					</td>
					<td class="tit">评估号：</td>
					<td colspan="1">
							${machineAccount.assessNumber}
					</td>
					<td class="tit">责任比例：</td>
					<td colspan="1">
							${machineAccount.dutyRatio}
					</td>
				</tr>
				<tr>
					<td class="tit">反馈时间：</td>
					<td colspan="1">
							${machineAccount.feedbackTime}
					</td>
					<td class="tit">协议号：</td>
					<td colspan="1">
							${machineAccount.agreementNumber}
					</td>
					<td class="tit">签署协议/判决时间：</td>
					<td colspan="1">
							${machineAccount.ratifyAccord}
					</td>
				</tr>

				<tr>
					<%--<td class="tit">协议盖章时间：</td>--%>
					<%--<td colspan="1">--%>
							<%--${machineAccount.agreementStampTime}--%>
					<%--</td>--%>
					<td class="tit">协议金额：</td>
					<td colspan="1">
							${machineAccount.agreementAmount}
					</td>
					<td class="tit">保险赔付金额：</td>
					<td colspan="1">
							${machineAccount.insuranceAmount}
					</td>
                        <td class="tit">医院赔付金额：</td>
                        <td colspan="1">
                                ${machineAccount.hospitalAmount}
                        </td>
				</tr>

				<tr>
					<td class="tit">交理赔时间：</td>
					<td colspan="1">
							${machineAccount.claimSettlementTime}
					</td>
					<td class="tit">赔付时间：</td>
					<td colspan="1">
							${machineAccount.compensateTime}
					</td>
					<td class="tit">流转天数：</td>
					<td colspan="1">
							${machineAccount.flowDays}
					</td>
				</tr>

				<tr>
					<td class="tit">移交人：</td>
					<td colspan="1">
							${machineAccount.handOver}
					</td>
					<td class="tit">归档时间：</td>
					<td colspan="1">
							${machineAccount.archiveTime}
					</td>
					<td class="tit">卷宗编号：</td>
					<td colspan="1">
							${machineAccount.fileNumber}
					</td>
				</tr>
				<tr>
					<td class="tit">备注：</td>
					<td colspan="5">
							${machineAccount.remark}
					</td>

				</tr>
			</table>
		</fieldset>

		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>