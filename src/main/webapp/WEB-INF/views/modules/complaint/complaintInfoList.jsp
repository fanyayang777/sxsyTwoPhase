<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>投诉接待管理</title>
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
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导出模版", buttons:{"关闭":true},
					bottomText:""});
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
            width: 20em;
        }
    </style>
</head>
<body>
<div id="importBox" class="hide">
	<form id="importForm" action="${ctx}/surgicalconsentbook/preOperativeConsent/export?type=dj" method="post" enctype="multipart/form-data"
		  class="form-search" style="padding-left:20px;text-align:center;" ><br/>
		<input id="butSum" class="btn btn-primary" type="submit" value="'投诉处理登记表'模版">
	</form>
</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/complaint/complaintInfo/">
			<c:choose>
				<c:when test="${ node eq 'sjy'}">
					数据员审核列表
				</c:when>
				<c:when test="${ node eq 'fpy'}">
					分配员列表
				</c:when>
				<c:otherwise>
					投诉接待列表
				</c:otherwise>
			</c:choose>
		</a></li>
		<c:if test="${node ne 'sjy' and node ne 'fpy'}">
			<shiro:hasPermission name="complaint:complaintInfo:edit"><li><a href="${ctx}/complaint/complaintInfo/form">投诉接待添加</a></li></shiro:hasPermission>
		</c:if>
	</ul>
	<form:form id="searchForm" modelAttribute="complaintInfo" action="${ctx}/complaint/complaintInfo/?node=${node}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>案件编号：</label>
				<form:input path="caseNumber" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>访客姓名：</label>
				<form:input path="visitorName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>访客电话：</label>
				<form:input path="visitorMobile" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label>患者姓名：</label>
				<form:input path="patientName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<c:if test="${fns:getUser().company.officeType eq '1'}">
			<li><label>涉及医院：</label>
				<sys:treeselect id="involveHospital" name="involveHospital" value="${complaintInfo.involveHospital}" labelName="hospitalName" labelValue="${complaintInfo.hospitalName}"
					title="部门" url="/sys/office/treeData?type=1&officeType=2" isAll="true" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
			</li>
			</c:if>
			<li><label>涉及科室：</label>
				<sys:treeselect id="involveDepartment" name="involveDepartment" value="${complaintInfo.involveDepartment}" labelName="testTree"
								labelValue="${complaintInfo.testTree}" title="涉及科室"
								url="/test/testTree/treeData?mold=2" isAll="true" allowClear="true"
								notAllowSelectParent="true" checked="true"/>
				<%--<sys:treeselect id="involveDepartment" name="involveDepartment" value="${complaintInfo.involveDepartment}" labelName="departmentName" labelValue="${complaintInfo.departmentName}"--%>
					<%--title="部门" url="/sys/office/treeData?type=2&officeType=2" isAll="true" pid="involveHospital" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>--%>
			</li>
			<li><label>接待日期：</label>
				<input name="receptionDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="${complaintInfo.receptionDate}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd 0:0',isShowClear:true});"/>
				　--　
				<input id="receptionEndDate" name="receptionEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					   value="${complaintInfo.receptionEndDate}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59'});"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="btnImport" class="btn btn-primary" type="button" value="导出模版"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column a.case_number">案件编号</th>
				<th class="sort-column a.visitor_name">访客姓名</th>
				<th class="sort-column a.visitor_mobile">访客电话</th>
				<th class="sort-column a.patient_relation">与患者关系</th>
				<th class="sort-column a.patient_name">患者姓名</th>
				<th class="sort-column a.patient_sex">患者性别</th>
				<th class="sort-column a.patient_age">患者年龄</th>
				<th class="sort-column a.visitor_number">来访人数</th>
				<th class="sort-column a.involve_hospital">涉及医院</th>
				<th class="sort-column a.involve_department">涉及科室</th>
				<th class="sort-column a.involve_employee">涉及人员</th>
				<th class="sort-column a.summary_of_disputes">投诉纠纷概要</th>
				<th class="sort-column t.name">投诉原因</th>
				<th class="sort-column a.is_mediate">处理情况</th>
                <th class="sort-column a.expected_closure">结案预期</th>
                <th class="sort-column a.closing_method">结案方式</th>
                <th class="sort-column a.amount_involved">涉及金额 </th>
				<th>创建人</th>
				<shiro:hasPermission name="complaint:complaintInfo:edit"><th width="100px">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="complaintInfo">
			<tr>
				<td>
					    ${complaintInfo.caseNumber}
				</td>
				<td>
					    ${complaintInfo.visitorName}
				</td>
				<td>
					    ${complaintInfo.visitorMobile}
				</td>
				<td>
					    ${complaintInfo.relationName}
				</td>
				<td>
					    ${complaintInfo.patientName}
				</td>
				<td>
					    ${complaintInfo.sexName}
				</td>
				<td>
					    ${complaintInfo.patientAge}
				</td>
				<td>
					    ${complaintInfo.visitorNumber}
				</td>
				<td>
					    ${complaintInfo.hospitalName}
				</td>
				<td>
						${complaintInfo.testTree}
				</td>
				<td>
						${empty complaintInfo.employeeName?complaintInfo.involveEmployee:complaintInfo.employeeName}
				</td>
				<td>
					    ${complaintInfo.summaryOfDisputes}
				</td>
                <td>
                        ${complaintInfo.typeName}
                </td>
				<td>
                    <c:if test="${complaintInfo.handleWay eq 0}">
                        <c:if test="${complaintInfo.status eq 0}">
                            处理中
                        </c:if>
                        <c:if test="${complaintInfo.status eq 1}">
                            协调中
                        </c:if>
                        <c:if test="${complaintInfo.status eq 2}">
                            结案
                        </c:if>
                    </c:if>
                    <c:if test="${complaintInfo.handleWay eq 1}">
                        转办处理
                    </c:if>
					<c:if test="${complaintInfo.handleWay eq 2}">
						转医调委
					</c:if>
                    <c:if test="${complaintInfo.handleWay eq 3}">
						法院诉讼
                    </c:if>
					<c:if test="${complaintInfo.handleWay eq 4}">
						行政调解
                    </c:if>

				</td>

                <td>
                        ${complaintInfo.expectedClosure}
                </td>
                <td>
                        ${complaintInfo.closingMethod}
                </td>
                <td>
                        ${complaintInfo.amountInvolved}
                </td>
                <td>
                        ${complaintInfo.createBy.name}
                </td>
				<shiro:hasPermission name="complaint:complaintInfo:edit"><td>
					<c:if test="${complaintInfo.complaintMain.act.taskDefKey eq 'enrollment' or (complaintInfo.createBy.id eq fns:getUser().id and empty complaintInfo.complaintMain.procInsId)}">
						<a href="${ctx}/complaint/complaintInfo/form?id=${complaintInfo.complaintId}">处理</a>
						<a href="${ctx}/complaint/complaintInfo/delete?id=${complaintInfo.complaintId}" onclick="return confirmx('确认要删除该投诉接待吗？', this.href)">删除</a>
					</c:if>
					<c:if test="${node eq 'sjy'}">
						<a href="${ctx}/complaint/complaintInfo/form?id=${complaintInfo.complaintId}&type=view&node=${node}">审核</a>
					</c:if>
					<c:if test="${node eq 'fpy'}">
						<a href="${ctx}/complaint/complaintInfo/form?id=${complaintInfo.complaintId}&type=view&node=${node}">分配</a>
					</c:if>
					<a href="${ctx}/complaint/complaintInfo/form?id=${complaintInfo.complaintId}&type=view">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>