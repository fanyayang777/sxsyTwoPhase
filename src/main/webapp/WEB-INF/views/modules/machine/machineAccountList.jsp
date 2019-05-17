<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>台账信息展示管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true},
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/machine/machineAccount/import" method="post" enctype="multipart/form-data"
			  class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/machine/machineAccount/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/machine/machineAccount/">台账信息展示列表</a></li>
		<shiro:hasPermission name="machine:machineAccount:edit"><li><a href="${ctx}/machine/machineAccount/form">台账信息展示添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="machineAccount" action="${ctx}/machine/machineAccount/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>报案时间：</label>
				<input name="reportingTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${machineAccount.reportingTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>部门：</label>
				<sys:treeselect id="deptId" name="deptId" value="${machineAccount.deptId}" labelName="" labelValue="${machineAccount.deptId}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>调解员：</label>
				<sys:treeselect id="mediatorId" name="mediatorId" value="${machineAccount.mediatorId}" labelName="" labelValue="${machineAccount.mediatorId}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>医院名称：</label>
				<form:input path="hospitalId" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>保单号：</label>
				<form:input path="policyNumber" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>起保日期：</label>
				<input name="startInsuranceTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${machineAccount.startInsuranceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>卷宗编号：</label>
				<form:input path="fileNumber" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>报案时间</th>
				<th>部门主键</th>
				<th>调解员</th>
				<th>患者名称</th>
				<th>医院名称</th>
				<th>重大</th>
				<th>保险公司名称</th>
				<th>保单号</th>
				<th>起保日期</th>
				<th>纠纷发生日期</th>
				<th>出险日期</th>
				<th>纠纷概要</th>
				<th>是否重大</th>
				<th>诊疗方式</th>
				<th>治疗结果</th>
				<th>患方反映焦点</th>
				<th>相关专业</th>
				<th>评估时间</th>
				<th>评估号</th>
				<th>责任比例</th>
				<th>反馈时间</th>
				<th>协议号</th>
				<th>签署协议/判决时间</th>
				<th>协议盖章时间</th>
				<th>协议金额</th>
				<th>保险金额</th>
				<th>交理赔时间</th>
				<th>赔付时间</th>
				<th>流转天数</th>
				<th>移交人</th>
				<th>归档时间</th>
				<th>卷宗编号</th>
				<th>备注</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>更新人</th>
				<th>修改时间</th>
				<shiro:hasPermission name="machine:machineAccount:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="machineAccount">
			<tr>
				<td><a href="${ctx}/machine/machineAccount/form?id=${machineAccount.machineAccountId}">
					${machineAccount.reportingTime}
				</a></td>
				<td>
					${machineAccount.deptId}
				</td>
				<td>
					${machineAccount.mediatorId}
				</td>
				<td>
					${machineAccount.patientName}
				</td>
				<td>
					${machineAccount.hospitalId}
				</td>
				<td>
					${machineAccount.major}
				</td>
				<td>
					${machineAccount.insuranceCompany}
				</td>
				<td>
					${machineAccount.policyNumber}
				</td>
				<td>
					${machineAccount.startInsuranceTime}
				</td>
				<td>
					${machineAccount.disputesTime}
				</td>
				<td>
					${machineAccount.riskTime}
				</td>
				<td>
					${machineAccount.summaryOfDisputes}
				</td>
				<td>
					${machineAccount.isMajor}
				</td>
				<td>
					${machineAccount.treatmentMode}
				</td>
				<td>
					${machineAccount.treatmentResult}
				</td>
				<td>
					${machineAccount.patientsReflectFocus}
				</td>
				<td>
					${machineAccount.relatedMajor}
				</td>
				<td>
					${machineAccount.assessTime}
				</td>
				<td>
					${machineAccount.assessNumber}
				</td>
				<td>
					${machineAccount.dutyRatio}
				</td>
				<td>
					${machineAccount.feedbackTime}
				</td>
				<td>
					${machineAccount.agreementNumber}
				</td>
				<td>
					${machineAccount.ratifyAccord}
				</td>
				<td>
					${machineAccount.agreementStampTime}
				</td>
				<td>
					${machineAccount.agreementAmount}
				</td>
				<td>
					${machineAccount.insuranceAmount}
				</td>
				<td>
					${machineAccount.claimSettlementTime}
				</td>
				<td>
					${machineAccount.compensateTime}
				</td>
				<td>
					${machineAccount.flowDays}
				</td>
				<td>
					${machineAccount.handOver}
				</td>
				<td>
					${machineAccount.archiveTime}
				</td>
				<td>
					${machineAccount.fileNumber}
				</td>
				<td>
					${machineAccount.remark}
				</td>
				<td>
					${machineAccount.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${machineAccount.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${machineAccount.updateBy.id}
				</td>
				<td>
					<fmt:formatDate value="${machineAccount.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="machine:machineAccount:edit"><td>
    				<a href="${ctx}/machine/machineAccount/form?id=${machineAccount.id}">修改</a>
					<a href="${ctx}/machine/machineAccount/delete?id=${machineAccount.id}" onclick="return confirmx('确认要删除该台账信息展示吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>