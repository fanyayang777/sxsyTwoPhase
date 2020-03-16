<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, rootId = "${not empty office.id ? office.id : '0'}";
			addRow("#treeTableList", tpl, data, rootId, true);
			$("#treeTable").treeTable({expandLevel : 1});
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
							type: getDictLabel(${fns:toJson(fns:getDictList('sys_office_type'))}, row.type)
						}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
	</script>
</head>
<body>

<ul class="nav nav-tabs">
	<li class="active"><a href="${ctx}/sys/office/list?id=${office.id}&parentIds=${office.parentIds}">机构列表</a></li>
	<shiro:hasPermission name="sys:office:edit"><li><a href="${ctx}/sys/office/form?parent.id=${office.id}&officeType=${office.officeType}">机构添加</a></li></shiro:hasPermission>
</ul>
<c:choose>
	<c:when test="${office.officeType == '2'}">
		<form:form id="searchForm" modelAttribute="office" action="${ctx}/sys/office/list?officeType=${office.officeType}" method="post" class="breadcrumb form-search ">
			<input type="hidden" id="company"name="company" value="${office.officeType}"/>
			<form:input type="hidden" path="parentIds" value="${empty office.parentIds ? '' : office.parentIds}"/>
			<ul class="ul-form">
				<li class="clearfix"></li>
				<li>
					<label>归属区域：</label>
						<sys:treeselect id="area" name="area.id" value="${office.area.id}" labelName="area.name" labelValue="${office.area.name}"
										title="区域" url="/sys/area/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
					<label>医院名称：</label>
					<form:input path="name" htmlEscape="false" maxlength="50"/>
				</li>
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
			</ul>
		</form:form>
	</c:when>
</c:choose>
<sys:message content="${message}"/>
<table id="treeTable" class="table table-striped table-bordered table-condensed">
	<thead><tr><th>机构名称</th><th>归属区域</th><th>机构编码</th><th>机构类型</th>
		<c:choose>
			<c:when test="${office.officeType == '2'}">
				<th>医院等级</th>
			</c:when>
		</c:choose>

		<th>备注</th><shiro:hasPermission name="sys:office:edit"><th>操作</th></shiro:hasPermission></tr></thead>
	<tbody id="treeTableList"></tbody>
</table>
<script type="text/template" id="treeTableTpl">
	<tr id="{{row.id}}" pId="{{pid}}">

		<td><a href="${ctx}/sys/office/form?id={{row.id}}">{{row.name}}</a></td>
			<td>{{row.area.name}}</td>
		<td>{{row.code}}</td>
		<td>{{dict.type}}</td>

			<c:choose>
			<c:when test="${office.officeType == '2'}">
				<td>{{row.labelName}}</td>
			</c:when>
		</c:choose>
		<td>{{row.remarks}}</td>
		<shiro:hasPermission name="sys:office:edit"><td>
			<a href="${ctx}/sys/office/form?id={{row.id}}&officeType=${office.officeType}">修改</a>
			<a href="${ctx}/sys/office/delete?id={{row.id}}" onclick="return confirmx('要删除该机构及所有子机构项吗？', this.href)">删除</a>
			<a href="${ctx}/sys/office/form?parent.id={{row.id}}&officeType=${office.officeType}">添加下级机构</a>
		</td></shiro:hasPermission>

	</tr>
</script>
</body>
</html>