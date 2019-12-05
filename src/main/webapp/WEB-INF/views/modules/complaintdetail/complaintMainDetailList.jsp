<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>投诉接待管理</title>
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
		<li class="active"><a href="${ctx}/complaintdetail/complaintMainDetail/">投诉接待列表</a></li>
		<shiro:hasPermission name="complaintdetail:complaintMainDetail:edit"><li><a href="${ctx}/complaintdetail/complaintMainDetail/form">投诉接待添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="complaintMainDetail" action="${ctx}/complaintdetail/complaintMainDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>访客姓名：</label>
				<form:input path="visitorName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>访客电话：</label>
				<form:input path="visitorMobile" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label>来访日期：</label>
				<input name="beginVisitorDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${complaintMainDetail.beginVisitorDate}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/> -
				<input name="endVisitorDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${complaintMainDetail.endVisitorDate}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/>
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
				<th class="sort-column case_number" style="text-align: center;">案件编号</th>
				<th class="sort-column visitor_date" style="text-align: center;">投诉时间</th>
				<th class="sort-column b.patient_name" style="text-align: center;">患者姓名</th>
				<th class="sort-column o.name" style="text-align: center;">涉及医院</th>
				<th class="sort-column summary_of_disputes" style="text-align: center;">投诉纠纷概要</th>

				<th class="sort-column appeal" style="text-align: center;">诉求</th>
				<th class="sort-column complaint_mode" style="text-align: center;">投诉方式</th>
				<th class="sort-column visitor_name" style="text-align: center;">访客姓名</th>
				<th class="sort-column patient_relation" style="text-align: center;">与患者关系</th>
				<th class="sort-column visitor_Number" style="text-align: center;">来访人数</th>

				<%--<th class="sort-column is_major" style="text-align: center;">案件分类</th>
				<th class="sort-column visitor_mobile" style="text-align: center;">访客电话</th>
				<th class="sort-column a.update_date" style="text-align: center;">更新日期</th>--%>
				<shiro:hasPermission name="complaintdetail:complaintMainDetail:edit"><th style="text-align: center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="complaintMainDetail">
			<tr>
				<td style="text-align: center;">
					<a href="${ctx}/complaintdetail/complaintMainDetail/form?id=${complaintMainDetail.complaintMainDetailId}&type=view">
							${complaintMainDetail.complaintMain.caseNumber}
					</a>
				</td>
				<td style="text-align: center;">
						${complaintMainDetail.visitorDate}
				</td>
				<td style="text-align: center;">
						${complaintMainDetail.complaintMain.patientName}
				</td>
				<td style="text-align: center;">
						${complaintMainDetail.complaintMain.hospital.name}
				</td>

				<td style="text-align: center;">
						${complaintMainDetail.summaryOfDisputes}
				</td>

				<td style="text-align: center;">
						${complaintMainDetail.appeal}
				</td>

				<td style="text-align: center;">
					<c:choose>
                        <c:when test="${complaintMainDetail.complaintMode=='0'}">
                            来电
                        </c:when>
                        <c:when test="${complaintMainDetail.complaintMode=='1'}">
                            来访
                        </c:when>
                        <c:when test="${complaintMainDetail.complaintMode=='2'}">
                            来信
                        </c:when>
                        <c:when test="${complaintMainDetail.complaintMode=='3'}">
                            其他
                        </c:when>
                    </c:choose>
				</td>
				<td style="text-align: center;">
					${complaintMainDetail.visitorName}
				</td>

				<td style="text-align: center;">
					<c:choose>
						<c:when test="${complaintMainDetail.patientRelation == '1'}">
							本人
						</c:when>
						<c:when test="${complaintMainDetail.patientRelation == '2'}">
							夫妻
						</c:when>
						<c:when test="${complaintMainDetail.patientRelation == '3'}">
							子女
						</c:when>
						<c:when test="${complaintMainDetail.patientRelation == '4'}">
							父母
						</c:when>
						<c:when test="${complaintMainDetail.patientRelation == '5'}">
							兄妹
						</c:when>
						<c:when test="${complaintMainDetail.patientRelation == '6'}">
							亲属
						</c:when>
						<c:when test="${complaintMainDetail.patientRelation == '7'}">
							其他
						</c:when>
					</c:choose>
				</td>
				<td style="text-align: center;">
						${complaintMainDetail.visitorNumber}
				</td>
				<%--<td style="text-align: center;">
						${complaintMainDetail.visitorMobile}
				</td>
				<td style="text-align: center;">
					${complaintMainDetail.typeName}
					&lt;%&ndash;${complaintMainDetail.isMajorlabel}&ndash;%&gt;
				</td>

				<td style="text-align: center;">
					<fmt:formatDate value="${complaintMainDetail.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>--%>
				<shiro:hasPermission name="complaintdetail:complaintMainDetail:edit"><td style="text-align: center;">
					<c:if test="${fns:getUser().id eq complaintMainDetail.createBy}">
						<a href="${ctx}/complaintdetail/complaintMainDetail/form?id=${complaintMainDetail.complaintMainDetailId}">处理</a>
					</c:if>
					<a href="${ctx}/complaintdetail/complaintMainDetail/form?id=${complaintMainDetail.complaintMainDetailId}&type=view">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>