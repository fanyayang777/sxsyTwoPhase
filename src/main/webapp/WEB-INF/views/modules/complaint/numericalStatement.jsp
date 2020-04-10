<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作量统计</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			changeDate('day');
            $("#btnExportSubmit").click(function () {
                top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
                    if(v=="ok"){
                        $("#searchForm").attr("action","${ctx}/complaint/complaintInfo/export?export='yes'&dateType=${type}");
                        $("#searchForm").submit();
                    }
                },{buttonsFocus:1, closed:function(){
						$("#searchForm").attr("action","${ctx}/complaint/complaintInfo/statement");
					}});
                top.$('.jbox-body .jbox-icon').css('top','55px');

            });
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        function changeDate(value){
		    if(value=='day'){
		        $("#day").show();
		        $("#month").hide();
                $("#visitorMonthDate").val("");
			}else if(value=='month'){
                $("#day").hide();
                $("#month").show();
                $("#visitorDate").val("");
			}
            $("#involveDepartmentId").val("");
            $("#involveEmployeeId").val("");
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/complaint/complaintInfo/">工作量统计</a></li>
	</ul>
	<form:form id="searchForm" action="${ctx}/complaint/complaintInfo/statement" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>类型：</label>
				<select id="type" name="type"  class="input-medium" onchange="changeDate(this.value)">
					<option value="day">日表</option>
					<option value="month">月表</option>
				</select>
			</li>
			<li id="day" style="">
				<label>日期(开始)：</label>
				<input id="visitorDate" name="visitorDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="${visitorDate}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>

				<label>日期(开始：</label>
				<input id="visitorDateEnd" name="visitorDateEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="${visitorDateEnd}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>

			<li id="month" style="">
				<label>日期(开始)：</label>
				<input id="visitorMonthDate" name="visitorMonthDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="${visitorMonthDate}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true});"/>
				<label>日期(结束)：</label>
				<input id="visitorMonthDateEnd" name="visitorMonthDateEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="${visitorMonthDate}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true});"/>
			</li>

			<li><label>部门：</label>
				<sys:treeselect id="involveDepartment" name="involveDepartment" value="${involveDepartment}" labelName="departmentName" labelValue=""
					title="部门" url="/sys/office/treeData?type=2&officeType=1" isAll="true"  cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>人员</label>
				<sys:treeselect id="involveEmployee" name="involveEmployee" value="${involveEmployee}" labelName="employeeName" labelValue=""
								title="部门" url="/sys/office/treeData?type=3&officeType=1" isAll="true"  cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="统计"/></li>
			<li class="btns"><input id="btnExportSubmit" class="btn btn-primary" type="button" value="导出" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center" class="sort-column u.office_id">部门</th>
				<th style="text-align: center" class="sort-column a.create_by">人员</th>
				<th style="text-align: center" class="sort-column a.visitor_date">接待日期</th>
				<th style="text-align: center">同意书见证</th>
				<th style="text-align: center">投诉接待数量(总)</th>
				<th style="text-align: center">医院转办数量</th>
				<th style="text-align: center">转调解数量</th>
				<th style="text-align: center">当面处理数量</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="statement">
			<c:forEach items="${statement}" var="single">
				<tr>
					<td style="text-align: center" >
							${single.deptName}
					</td>
					<td style="text-align: center" >
							${single.name}
					</td>
					<td style="text-align: center" >
							${single.visitor_date}
					</td>
					<td style="text-align: center" >
                            ${empty single.sq ? "0" : single.sq}
					</td>
					<td style="text-align: center" >
							${empty single.amount? "0" : single.amount}
					</td>
					<td style="text-align: center" >
							${empty single.zb? "0" : single.zb}
					</td>
					<td style="text-align: center" >
							${empty single.ytw? "0" : single.ytw}
					</td>
					<td style="text-align: center" >
							${empty single.dm? "0" : single.dm}
					</td>
				</tr>
			</c:forEach>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>