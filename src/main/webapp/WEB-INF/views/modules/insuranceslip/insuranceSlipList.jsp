<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>投保单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/insuranceslip/insuranceSlip/">投保单列表</a></li>
		<shiro:hasPermission name="insuranceslip:insuranceSlip:edit"><li><a href="${ctx}/insuranceslip/insuranceSlip/form">投保单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="insuranceSlip" action="${ctx}/insuranceslip/insuranceSlip/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>投保人：</label>
				<sys:treeselect id="policyHolder" name="policyHolder" value="${insuranceSlip.policyHolder}" labelName="policyHolder.name" labelValue="${insuranceSlip.policyHolder}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>投保人：联系电话：</label>
				<form:input path="policyPhone" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label>投保人：通信地址和邮编：</label>
				<form:input path="sitePostcode" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>投保人</th>
				<th>投保人：联系电话</th>
				<th>投保人：通信地址和邮编</th>
				<th>投保人：电子邮箱</th>
				<th>基本保险费总计</th>
				<th>实收保险费</th>
				<th>仲裁员</th>
				<th>修改时间</th>
				<shiro:hasPermission name="insuranceslip:insuranceSlip:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="insuranceSlip">
			<tr>
				<td><a href="${ctx}/insuranceslip/insuranceSlip/form?id=${insuranceSlip.id}">
					${fns:getOfficeId(insuranceSlip.policyHolder).name}
				</a></td>
				<td>
					${insuranceSlip.policyPhone}
				</td>
				<td>
					${insuranceSlip.sitePostcode}
				</td>
				<td>
					${insuranceSlip.emailAddress}
				</td>
				<td>
					${insuranceSlip.basicPremiumTotal}
				</td>
				<td>
					${insuranceSlip.oddicialReceiptsPermium}
				</td>
				<td>
					${insuranceSlip.arbitrator}
				</td>
				<td>
					<fmt:formatDate value="${insuranceSlip.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="insuranceslip:insuranceSlip:edit"><td>
    				<a href="${ctx}/insuranceslip/insuranceSlip/form?id=${insuranceSlip.insurancePolicyId}">修改</a>
					<a href="${ctx}/insuranceslip/insuranceSlip/delete?id=${insuranceSlip.insurancePolicyId}" onclick="return confirmx('确认要删除该投保单吗？', this.href)">删除</a>
					<c:if test="${state eq '1'}">
						<a href="${ctx}/insuranceslip/insuranceSlip/delete?id=${insuranceSlip.insurancePolicyId}&state=${state}">审核</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>