<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>医方人员机构关联信息管理</title>
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
		<li class="active"><a href="${ctx}/medicalofficeemp/medicalOfficeEmp/">医方人员机构关联信息列表</a></li>
		<shiro:hasPermission name="medicalofficeemp:medicalOfficeEmp:edit"><li><a href="${ctx}/medicalofficeemp/medicalOfficeEmp/form">医方人员机构关联信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="medicalOfficeEmp" action="${ctx}/medicalofficeemp/medicalOfficeEmp/" method="post" class="breadcrumb form-search">
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
				<shiro:hasPermission name="medicalofficeemp:medicalOfficeEmp:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="medicalOfficeEmp">
			<tr>
				<td><a href="${ctx}/medicalofficeemp/medicalOfficeEmp/form?id=${medicalOfficeEmp.id}">
					<fmt:formatDate value="${medicalOfficeEmp.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${medicalOfficeEmp.remarks}
				</td>
				<shiro:hasPermission name="medicalofficeemp:medicalOfficeEmp:edit"><td>
    				<a href="${ctx}/medicalofficeemp/medicalOfficeEmp/form?id=${medicalOfficeEmp.id}">修改</a>
					<a href="${ctx}/medicalofficeemp/medicalOfficeEmp/delete?id=${medicalOfficeEmp.id}" onclick="return confirmx('确认要删除该医方人员机构关联信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>