<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>纠纷调解管理</title>
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
		<li class="active"><a href="${ctx}/complaintmain/complaintMain/self">我的待办</a></li>
<%--
		<li ><a href="${ctx}/complaintmain/complaintMain/home?type=myDone">我的已办</a></li>
--%>
<%--
		<shiro:hasPermission name="complaintmain:complaintMain:edit"><li><a href="${ctx}/complaintmain/complaintMain/form">纠纷调解添加</a></li></shiro:hasPermission>
--%>
	</ul>
	<form:form id="searchForm" modelAttribute="complaintMain" action="${ctx}/complaintmain/complaintMain/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">

			<%--<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>--%>
			<%--<li class="clearfix"></li>--%>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>案件编号</th>
				<th>患者姓名</th>
				<th>患者性别</th>
				<th>涉及医院</th>
				<th>涉及科室</th>
				<th>涉及人员</th>
				<th>当前节点</th>
				<th>修改时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="complaintMain">
			<tr>
				<td>
					${complaintMain.caseNumber}
				</td>
				<td>
					${complaintMain.patientName}
				</td>
				<td>
					<c:if test="${complaintMain.patientSex=='1'}">
						男
					</c:if>
					<c:if test="${complaintMain.patientSex=='2'}">
						女
					</c:if>
				</td>
				<td>
					${complaintMain.hospital.name}
				</td>
				<td>
					${complaintMain.involveDepartment}
				</td>
				<td>
					${empty complaintMain.employee.name?complaintMain.involveEmployee:complaintMain.employee.name}
				</td>
				<td>
					${complaintMain.nodeName}
				</td>
				<td>
					<fmt:formatDate value="${complaintMain.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${not empty complaintMain.url}">
						<c:if test="${ complaintMain.nodeName ne '案件反馈'}">
							<c:if test="${num ne '1'}">
							<a href="${ctx}${complaintMain.url}form?id=${complaintMain.key}">处理</a>
						</c:if>
					</c:if>
						<a href="${ctx}${complaintMain.url}form?id=${complaintMain.key}&type=view">详情</a>
					</c:if>
				</td>
			</tr>


		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>