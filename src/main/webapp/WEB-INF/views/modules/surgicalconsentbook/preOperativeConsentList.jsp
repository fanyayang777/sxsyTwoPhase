<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
	<title>术前同意书管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
</head>
<body>
<div id="importBox" class="hide">
	<form id="importForm" action="${ctx}/surgicalconsentbook/preOperativeConsent/export" method="post" enctype="multipart/form-data"
		  class="form-search" style="padding-left:20px;text-align:center;" ><br/>
		<a href="${ctx}/surgicalconsentbook/preOperativeConsent/export?type=bl">'见证会笔录'模版</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="${ctx}/surgicalconsentbook/preOperativeConsent/export?type=qd">'见证签到表'模版</a>
	</form>
</div>
<ul class="nav nav-tabs">
	<li class="active"><a href="${ctx}/surgicalconsentbook/preOperativeConsent/">术前同意书列表</a></li>
	<shiro:hasPermission name="surgicalconsentbook:preOperativeConsent:edit"><li><a href="${ctx}/surgicalconsentbook/preOperativeConsent/form?surgicalConsentId=${preOperativeConsent.id}">术前同意书添加</a></li></shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="preOperativeConsent" action="${ctx}/surgicalconsentbook/preOperativeConsent/" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
	<ul class="ul-form">
		<li><label style="width: 110px;">手术同意书编号：</label>
			<form:input path="surgicalConsentId" htmlEscape="false" maxlength="64" class="input-small"/>
		</li>
		<li><label>手术类型：</label>
			<form:select path="operationType" class="input-small" style="text-align:center" >
				<form:option value="">无</form:option>
				<form:options items="${fns:getDictList('surgeryType')}" itemLabel="label" itemValue="value" htmlEscape="false" />
			</form:select>
		</li>

		<li><label>见证医院：</label>
			<sys:treeselect id="hospital" name="hospital" value="${preOperativeConsent.hospital}" labelName="hospitalName" labelValue="${preOperativeConsent.officeName}"
							title="机构" url="/sys/office/treeData?type=1&officeType=2" isAll="true" cssClass="required" dataMsgRequired="请选择医院" allowClear="true" notAllowSelectParent="false" cssStyle="width :120px"/>
		</li>
		<li><label >见证人：</label>
			<sys:treeselect id="witness" name="witness" value="${preOperativeConsent.witness}" labelName="jzr" labelValue="${preOperativeConsent.witnessName}"
							title="用户" url="/sys/office/treeData?type=3&officeType=1"   cssClass="required" dataMsgRequired="请选择见证人" allowClear="true" notAllowSelectParent="true" cssStyle="width :120px"/>
		</li>
		<li><label style="margin-left: 40px;">记录人：</label>
			<sys:treeselect id="recordMan" name="recordMan" value="" labelName="jlr" labelValue="${preOperativeConsent.recordManName}"
							title="用户" url="/sys/office/treeData?type=3&officeType=1"   cssClass="required" dataMsgRequired="请选择记录人" allowClear="true" notAllowSelectParent="true" cssStyle="width :120px"/>
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
		<th class="sort-column surgical_consent_id" style="text-align: center;">手术同意书编号</th>
		<th class="sort-column witness_time" style="text-align: center;">见证时间</th>
		<th class="sort-column hospital" style="text-align: center;">见证医院</th>
		<th class="sort-column department" style="text-align: center;">科室</th>
		<th class="sort-column operation_type" style="text-align: center;">手术专业</th>

		<th class="sort-column medical_side" style="text-align: center;">主管医生</th>
		<th class="sort-column other_doctors" style="text-align: center;">医方其他参会人员</th>
		<th class="sort-column patient" style="text-align: center;">患者</th>
		<th class="sort-column operation_client" style="text-align: center;">手术中授权委托人</th>
		<th class="sort-column affected_party" style="text-align: center;">其他患方家属</th>
		<th class="sort-column operation_name" style="text-align: center;">手术名称</th>
		<th class="sort-column operation_date" style="text-align: center;">手术日期</th>
		<th class="sort-column postoperative_visit" style="text-align: center;">术后回访</th>
		<th class="sort-column insured" style="text-align: center;">是否投保</th>
		<th class="sort-column is_dangerous" style="text-align: center;">是否出险</th>
		<th class="sort-column compensation_amount" style="text-align: center;">赔偿金额</th>
		<%--<th class="sort-column witness_locations" style="text-align: center;">见证地点</th>--%>
		<th class="sort-column witness" style="text-align: center;">见证人</th>
		<th class="sort-column record_man" style="text-align: center;">记录人</th>

		<th class="sort-column remarks" style="text-align: center;">备注信息</th>
		<shiro:hasPermission name="surgicalconsentbook:preOperativeConsent:edit"><th class="sort-column" style="text-align: center;">操作</th></shiro:hasPermission>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="preOperativeConsent">
		<tr>
			<td style="text-align: center;">
				<a href="${ctx}/surgicalconsentbook/preOperativeConsent/form?id=${preOperativeConsent.id}">
					${preOperativeConsent.surgicalConsentId}</a>
			</td>
			<td style="text-align: center;">
				<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${preOperativeConsent.witnessTime}"/>

			</td>
			<td style="text-align: center;">
					${preOperativeConsent.officeName}
			</td>
			<td style="text-align: center;">
					${preOperativeConsent.departmentName}
			</td>
			<td style="text-align: center;">
					${preOperativeConsent.operationType}
			</td>

			<td style="text-align: center;">
					${preOperativeConsent.medicalSide}
			</td>
			<td style="text-align: center;">
					${preOperativeConsent.otherDoctors}
			</td>
			<td style="text-align: center;">
					${preOperativeConsent.patient}
			</td>
			<td style="text-align: center;">
					${preOperativeConsent.operationClient}
			</td>
			<td style="text-align: center;">
					${preOperativeConsent.affectedParty}
			</td>

			<td style="text-align: center;">
					${preOperativeConsent.operationName}
			</td>
			<td style="text-align: center;">
				<fmt:formatDate pattern="yyyy-MM-dd" value="${preOperativeConsent.operationDate}"/>

			</td>
			<td style="text-align: center;">
					${preOperativeConsent.postoperativeVisit}
			</td>
			<td style="text-align: center;">
				<c:choose>
					<c:when test="${preOperativeConsent.insured=='1'}">
						否
					</c:when>
					<c:when test="${preOperativeConsent.insured=='2'}">
						是
					</c:when>
				</c:choose>
			</td>
			<td style="text-align: center;">
				<c:choose>
					<c:when test="${preOperativeConsent.isDangerous=='1'}">
						否
					</c:when>
					<c:when test="${preOperativeConsent.isDangerous=='2'}">
						是
					</c:when>
				</c:choose>
			</td>

			<td style="text-align: center;">
					${preOperativeConsent.compensationAmount}
			</td>

			<%--<td style="text-align: center;">
					${preOperativeConsent.witnessLocations}
			</td>--%>
			<td style="text-align: center;">
					${preOperativeConsent.witnessName}
			</td>
			<td style="text-align: center;">
					${preOperativeConsent.recordManName}
			</td>

			<td style="text-align: center;">
					${preOperativeConsent.remarks}
			</td>

			<shiro:hasPermission name="surgicalconsentbook:preOperativeConsent:edit"><td style="text-align: center;">
				<a href="${ctx}/surgicalconsentbook/preOperativeConsent/form?id=${preOperativeConsent.id}">修改</a>
				<c:if test="${preOperativeConsent.createBy.id eq fns:getUser().id}">
					<a href="${ctx}/surgicalconsentbook/preOperativeConsent/delete?id=${preOperativeConsent.id}" onclick="return confirmx('确认要删除该术前同意书吗？', this.href)">删除</a>
				</c:if>
			</td></shiro:hasPermission>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>