<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>案件评价管理</title>
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
		<li class="active"><a href="${ctx}/assessinfo/assessInfo/">案件评价列表</a></li>

		<%--<shiro:hasPermission name="assessinfo:assessInfo:edit"><li><a href="${ctx}/assessinfo/assessInfo/form">案件评价添加</a></li></shiro:hasPermission>--%>

	</ul>
	<form:form id="searchForm" modelAttribute="assessInfo" action="${ctx}/assessinfo/assessInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>案件编号：</label>
				<form:input path="complaintMain.caseNumber" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>患者姓名：</label>
				<form:input path="complaintMain.patientName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label style="width: 100px;">涉及医院：</label>
				<sys:treeselect id="involveHospital" name="complaintMain.involveHospital" value="${assessInfo.complaintMain.involveHospital}" labelName="hospitalName" labelValue="${assessInfo.complaintMain.hospital.name}"
								title="部门" url="/sys/office/treeData?type=1&officeType=2" isAll="true" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
			</li>
			<li><label>归档人：</label>
				<sys:treeselect id="createBy" name="createBy.id" value="${assessInfo.createBy.id}" labelName=""
								labelValue="${assessInfo.summaryInfo.user.name}"
								title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass="required"
								allowClear="true" notAllowSelectParent="true"  dataMsgRequired="必填信息"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnReset" class="btn btn-primary" type="reset" value="重置"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column b.case_number" style="text-align: center;">案件编号</th>
				<th class="sort-column b.patient_name" style="text-align: center;">患者姓名</th>
				<th class="sort-column b.involve_hospital" style="text-align: center;">涉及医院</th>
				<th class="sort-column su.filing_time" style="text-align: center;">卷宗归档时间</th>
				<th class="" style="text-align: center;">归档人</th>
				<th class="" style="text-align: center;">接收人</th>
				<th class="sort-column file_number" style="text-align: center;">卷宗编号</th>
				<th class="sort-column a.assess_grade" style="text-align: center;">评分</th>
				<th class="sort-column a.appraiser" style="text-align: center;">评分人</th>
				<th  style="text-align: center;">部门名称</th>
				<th  style="text-align: center;">调解员</th>
				<shiro:hasPermission name="assessinfo:assessInfo:edit"><th style="text-align: center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="assessInfo">
			<tr>
				<td style="text-align: center;"><a href="${ctx}/assessinfo/assessInfo/form?id=${assessInfo.assessId}&type=view">
						${assessInfo.complaintMain.caseNumber}
				</a></td>
				<td style="text-align: center;">
						${assessInfo.complaintMain.patientName}
				</td>
				<td style="text-align: center;">
						${assessInfo.complaintMain.hospital.name}
				</td>
				<td style="text-align: center;">
						${assessInfo.handleTime}
				</td>
				<td style="text-align: center;">
						${assessInfo.summaryInfo.user.name}
				</td>

				<td style="text-align: center;">
				</td>
				<td style="text-align: center;">
						${assessInfo.summaryInfo.fileNumber}
				</td>

				<td style="text-align: center;">
						${assessInfo.assessGrade}
				</td>
				<td style="text-align: center;">
						${assessInfo.user.name}
				</td>
				<td style="text-align: center;">
						${fns:getUserById(assessInfo.createUser).office.name}
				</td>
				<td style="text-align: center;">
						${fns:getUserById(assessInfo.createUser).name}
				</td>
				<shiro:hasPermission name="assessinfo:assessInfo:edit"><td style="text-align: center;">
					<c:if test="${empty assessInfo.handleTime}">
						<a href="${ctx}/assessinfo/assessInfo/form?id=${assessInfo.assessId}">处理</a>
					</c:if>
					<a href="${ctx}/assessinfo/assessInfo/form?id=${assessInfo.assessId}&type=view">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>