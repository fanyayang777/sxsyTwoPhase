<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>患方联系人管理</title>
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
		<li class="active"><a href="${ctx}/patientlinkemp/patientLinkEmp/">患方联系人列表</a></li>
		<shiro:hasPermission name="patientlinkemp:patientLinkEmp:edit"><li><a href="${ctx}/patientlinkemp/patientLinkEmp/form">患方联系人添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="patientLinkEmp" action="${ctx}/patientlinkemp/patientLinkEmp/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>update_date</th>
				<th>remarks</th>
				<shiro:hasPermission name="patientlinkemp:patientLinkEmp:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="patientLinkEmp">
			<tr>
				<td><a href="${ctx}/patientlinkemp/patientLinkEmp/form?id=${patientLinkEmp.id}">
					<fmt:formatDate value="${patientLinkEmp.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${patientLinkEmp.remarks}
				</td>
				<shiro:hasPermission name="patientlinkemp:patientLinkEmp:edit"><td>
    				<a href="${ctx}/patientlinkemp/patientLinkEmp/form?id=${patientLinkEmp.id}">修改</a>
					<a href="${ctx}/patientlinkemp/patientLinkEmp/delete?id=${patientLinkEmp.id}" onclick="return confirmx('确认要删除该患方联系人吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>