<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报案信息管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/bootstrap/colResizable-1.6.min.js"></script>
	<script src="${ctxStatic}/bootstrap/bootstrap-table-resizable.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#contentTable").colResizable({
				liveDrag:true,//拖动列时更新表布局
				gripInnerHtml:"<div class='grip'></div>",
				draggingClass:"dragging",
				resizeMode:'overflow',//允许溢出父容器
				defaults : true,
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<style type="text/css">
		#contentTable {
			table-layout: fixed;
		}

		#contentTable th {
			text-align: center; /** 设置水平方向居中 */
			vertical-align: middle; /** 设置垂直方向居中 */
		}

		#contentTable td {
			word-break: keep-all; /* 不换行 */
			white-space: nowrap; /* 不换行 */
			overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
			text-overflow: ellipsis; /* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
		}

		#contentTable th:nth-of-type(23) {
			width: 10em;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/registration/reportRegistration/">报案信息列表</a></li>
<%--
		<shiro:hasPermission name="registration:reportRegistration:edit"><li><a href="${ctx}/registration/reportRegistration/form">报案信息添加</a></li></shiro:hasPermission>
--%>
	</ul>
	<form:form id="searchForm" modelAttribute="reportRegistration" action="${ctx}/registration/reportRegistration/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>案件编号：</label>
				<form:input path="complaintMain.caseNumber" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>报案时间：</label>
				<input name="reportTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="${reportRegistration.reportTime}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>患者姓名：</label>
				<form:input path="complaintMain.patientName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label style="width: 100px;">涉及医院：</label>
				<sys:treeselect id="involveHospital" name="complaintMain.involveHospital" value="${reportRegistration.complaintMain.involveHospital}" labelName="hospitalName" labelValue="${reportRegistration.complaintMain.hospital.name}"
								title="部门" url="/sys/office/treeData?type=1&officeType=2" isAll="true" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="button" class="btn btn-primary" type="reset" value="重置" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table  table-bordered table-condensed">
		<thead>
			<tr><th class="sort-column b.case_number" style="text-align: center;">案件编号</th>
				<th class="sort-column report_time" style="text-align: center;">报案时间</th>
				<th class="sort-column b.patient_name" style="text-align: center;">患者姓名</th>
				<th class="sort-column involve_hospital" style="text-align: center;">涉及医院</th>
				<%--<th class="sort-column b.hospital_grade" style="text-align: center;">保险公司</th>--%>
				<th class="sort-column dispute_time" style="text-align: center;">纠纷发生时间</th>
				<th class="sort-column report_emp" style="text-align: center;">报案人姓名</th>
				<th class="sort-column te.name" style="text-align: center;">涉及专业</th>
				<th class="sort-column a.is_major" style="text-align: center;">是否重大</th>
				<th  style="text-align: center;">是否媒体介入</th>
				<th style="text-align:center;">部门名称</th>
				<th style="text-align:center;">调解员</th>
				<shiro:hasPermission name="registration:reportRegistration:edit"><th style="text-align: center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="reportRegistration">
			<tr ${not empty reportRegistration.complaintMain.isMajor ? "style='background-color: rgb(157,211,250);'" : ""} >
				<td style="text-align: center;"><a href="${ctx}/registration/reportRegistration/form?id=${reportRegistration.reportRegistrationId}&type=view">
						${reportRegistration.complaintMain.caseNumber}
				</a></td>
				<td style="text-align:center;">
						${reportRegistration.reportTime}
				</td>
				<td style="text-align: center;">
						${reportRegistration.complaintMain.patientName}
				</td>
				<td style="text-align: center;">
						${reportRegistration.complaintMain.hospital.name}
				</td>
				<%--<td style="text-align: center;">

				</td>--%>
				<td style="text-align: center;">
						${reportRegistration.disputeTime}
				</td>

				<td style="text-align: center;">
						${reportRegistration.reportEmp}
				</td>
				<td style="text-align: center;">
						${reportRegistration.complaintMain.testTree}
				</td>

				<td style="text-align: center;">
                    <c:choose>
                        <c:when test="${not empty reportRegistration.complaintMain.isMajor}">
                            <a href="${ctx}/major/majorInfo/form?id=${reportRegistration.complaintMain.isMajor}&complaintMainId=${reportRegistration.complaintMainId}">是</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${ctx}/major/majorInfo/form?id=${reportRegistration.complaintMain.isMajor}&complaintMainId=${reportRegistration.complaintMainId}">否</a>
                        </c:otherwise>
                    </c:choose>

				</td>
				<td style="text-align: center;">

				</td>
				<td style="text-align: center;">
						${fns:getUserById(reportRegistration.createUser).office.name}
				</td>
				<td style="text-align: center;">
						${fns:getUserById(reportRegistration.createUser).name}
				</td>
				<shiro:hasPermission name="registration:reportRegistration:edit"><td style="text-align: center;">
					<c:if test="${fns:getUser().loginName eq reportRegistration.complaintMain.act.assigneeName}">
						<a href="${ctx}/registration/reportRegistration/form?id=${reportRegistration.reportRegistrationId}">处理</a>
						<%--<a href="${ctx}/stopmediate/stopMediate/form?complaintMainId=${reportRegistration.complaintMainId}&taskId=${reportRegistration.complaintMain.act.taskId}&module=badj&url1=/registration/reportRegistration/?repage">终止调解</a>--%>
					</c:if>
    				<a href="${ctx}/registration/reportRegistration/form?id=${reportRegistration.reportRegistrationId}&type=view">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>

