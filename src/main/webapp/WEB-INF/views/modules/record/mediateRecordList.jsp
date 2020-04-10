<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>调解志管理</title>
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
		<li class="active"><a href="${ctx}/record/mediateRecord/">调解志列表</a></li>
		<shiro:hasPermission name="record:mediateRecord:edit"><li><a href="${ctx}/record/mediateRecord/form">调解志添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="mediateRecord" action="${ctx}/record/mediateRecord/" method="post" class="breadcrumb form-search">
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
				<th>时间</th>
				<th>内容</th>
				<th>结果</th>
				<shiro:hasPermission name="record:mediateRecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mediateRecord">
			<tr>
				<td><a href="${ctx}/record/mediateRecord/form?id=${mediateRecord.id}">
					${mediateRecord.time}
				</a></td>
				<td>
					${mediateRecord.content}
				</td>
				<td>
					${mediateRecord.result}
				</td>
				<shiro:hasPermission name="record:mediateRecord:edit"><td>
    				<a href="${ctx}/record/mediateRecord/form?id=${mediateRecord.id}">修改</a>
					<a href="${ctx}/record/mediateRecord/delete?id=${mediateRecord.id}" onclick="return confirmx('确认要删除该调解志吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>