<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>花名册管理</title>
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
		<li class="active"><a href="${ctx}/roster/roster/">花名册列表</a></li>
		<shiro:hasPermission name="roster:roster:edit"><li><a href="${ctx}/roster/roster/form">花名册添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="roster" action="${ctx}/roster/roster/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>医院：</label>
				<form:input path="hospital" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>手机号：</label>
				<form:input path="phone" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>医院</th>
				<th>姓名</th>
				<th>所属岗位</th>
				<th>执业资格证号码</th>
				<th>手机号</th>
				<th>修改时间</th>
				<shiro:hasPermission name="roster:roster:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="roster">
			<tr>
				<td><a href="${ctx}/roster/roster/form?id=${roster.id}">
					${roster.hospital}
				</a></td>
				<td>
					${roster.name}
				</td>
				<td>
					${roster.post}
				</td>
				<td>
					${roster.practiceNumber}
				</td>
				<td>
					${roster.phone}
				</td>
				<td>
					<fmt:formatDate value="${roster.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="roster:roster:edit"><td>
    				<a href="${ctx}/roster/roster/form?id=${roster.id}">修改</a>
					<a href="${ctx}/roster/roster/delete?id=${roster.id}" onclick="return confirmx('确认要删除该花名册吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>