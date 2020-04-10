<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>类型管理</title>
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
		<li class="active"><a href="${ctx}/typeinfo/typeInfo/">类型列表</a></li>
		<shiro:hasPermission name="typeinfo:typeInfo:edit"><li><a href="${ctx}/typeinfo/typeInfo/form">类型总表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="typeInfo" action="${ctx}/typeinfo/typeInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>类型名称：</label>
				<form:input path="typeName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>类型名称</th>
				<th>内容</th>
				<th>关联模块</th>
				<shiro:hasPermission name="typeinfo:typeInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="typeInfo">
			<tr>
				<td><a href="${ctx}/typeinfo/typeInfo/form?id=${typeInfo.typeId}">
					${typeInfo.typeName}
				</a></td>
				<td>
					${typeInfo.content}
				</td>
				<td>
					${typeInfo.label}
				</td>
				<shiro:hasPermission name="typeinfo:typeInfo:edit"><td>
    				<a href="${ctx}/typeinfo/typeInfo/form?id=${typeInfo.typeId}">修改</a>
					<a href="${ctx}/typeinfo/typeInfo/delete?id=${typeInfo.typeId}" onclick="return confirmx('确认要删除该类型总表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>