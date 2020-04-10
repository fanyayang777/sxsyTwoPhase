<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>案件反馈管理</title>
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
		<li class="active"><a href="${ctx}/casefeedback/caseFeedback/">案件反馈列表</a></li>
		<%--<shiro:hasPermission name="casefeedback:caseFeedback:edit">--%>
			<%--<li><a href="${ctx}/casefeedback/caseFeedback/form">案件反馈添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="caseFeedback" action="${ctx}/casefeedback/caseFeedback/" method="post" class="breadcrumb form-search">
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
                <sys:treeselect id="involveHospital" name="complaintMain.involveHospital" value="${caseFeedback.complaintMain.involveHospital}" labelName="hospitalName" labelValue="${caseFeedback.complaintMain.hospital.name}"
                                title="部门" url="/sys/office/treeData?type=1&officeType=2" isAll="true" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
            </li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnReset" class="btn btn-primary" type="reset" value="重置"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column case_number" style="text-align: center;">案件编号</th>
				<th class="sort-column patient_name" style="text-align: center;">患者姓名</th>
				<th class="sort-column involve_hospital" style="text-align: center;">涉及医院</th>
				<th class="sort-column sign.summary_of_disputes" style="text-align: center;">纠纷概要</th>
				<th class="sort-column case_number" style="text-align: center;">案件分类</th>
				<th class="sort-column au.diagnosis_mode" style="text-align: center;">诊疗方式</th>
				<th class="sort-column au.treatment_outcome" style="text-align: center;">诊疗结果</th>
				<th class="sort-column r.patient_mobile" style="text-align: center;">调解结果</th>
				<th class="sort-column b.is_major" style="text-align: center;">是否重大</th>
				<th  style="text-align: center;">是否媒体介入</th>
				<th  style="text-align: center;">部门名称</th>
				<th  style="text-align: center;">调解员</th>
				<shiro:hasPermission name="casefeedback:caseFeedback:edit"><th style="text-align: center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="caseFeedback">
			<tr ${not empty caseFeedback.complaintMain.isMajor ? "style='background-color: rgb(157,211,250);'" : ""}>
				<td style="text-align: center;"><a href="${ctx}/casefeedback/caseFeedback/form?id=${caseFeedback.feedbackId}&type=view">
						${caseFeedback.complaintMain.caseNumber}
				</a></td>
				<td style="text-align: center;">
						${caseFeedback.complaintMain.patientName}
				</td>
				<td style="text-align: center;">
						${caseFeedback.complaintMain.hospital.name}
				</td>
				<td style="text-align: center;">
						${caseFeedback.summaryOfDisputes}
				</td>
				<td style="text-align: center;">
						${caseFeedback.area.name}
				</td>

				<td style="text-align: center;">
						${caseFeedback.auditAcceptance.diagnosisMode}
				</td>
				<td style="text-align: center;">
						${caseFeedback.auditAcceptance.treatmentOutcome}
				</td>
				<td style="text-align: center;">
				</td>
				<td style="text-align: center;">
					<c:choose>
						<c:when test="${not empty caseFeedback.complaintMain.isMajor}">
							<a href="${ctx}/major/majorInfo/form?id=${caseFeedback.complaintMain.isMajor}&complaintMainId=${caseFeedback.complaintMainId}">是</a>
						</c:when>
						<c:otherwise>
							<a href="${ctx}/major/majorInfo/form?id=${caseFeedback.complaintMain.isMajor}&complaintMainId=${caseFeedback.complaintMainId}">否</a>
						</c:otherwise>
					</c:choose>
				</td>
				<td style="text-align: center;">
				</td>
                <td style="text-align: center;">
						${fns:getUserById(caseFeedback.createUser).office.name}
                </td>
                <td style="text-align: center;">
						${fns:getUserById(caseFeedback.createUser).name}
                </td>
				<shiro:hasPermission name="casefeedback:caseFeedback:edit"><td style="text-align: center;">
    				<a href="${ctx}/casefeedback/caseFeedback/form?id=${caseFeedback.feedbackId}">反馈</a>
					<a href="${ctx}/casefeedback/caseFeedback/form?id=${caseFeedback.feedbackId}&type=view">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>