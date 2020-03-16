<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
<ul class="nav nav-tabs">
	<li><a href="${ctx}/sys/office/list?id=${office.parent.id}&parentIds=${office.parentIds}&officeType=${office.officeType}">机构列表</a></li>
	<li class="active"><a href="${ctx}/sys/office/form?id=${office.id}&parent.id=${office.parent.id}&officeType=${office.officeType}">机构<shiro:hasPermission name="sys:office:edit">${not empty office.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:office:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="office" action="${ctx}/sys/office/save" method="post" class="form-horizontal">
	<form:hidden path="id"/>
	<form:hidden path="officeType" value="${officeType}"/>
	<sys:message content="${message}"/>
	<c:if test="${office.officeType ne '2'}">
	<div class="control-group">
		<label class="control-label">上级机构:</label>
		<div class="controls">
			<sys:treeselect id="office" name="parent.id" value="${office.parent.id}" labelName="parent.name" labelValue="${office.parent.name}"
							title="机构" url="/sys/office/treeData?type=${office.type}&officeType=${office.officeType}" extId="${office.id}" cssClass="" allowClear="${office.currentUser.admin}"/>
		</div>
	</div>
	</c:if>
	<div class="control-group">
		<label class="control-label">归属区域:</label>
		<div class="controls">
			<sys:treeselect id="area" name="area.id" value="${office.area.id}" labelName="area.name" labelValue="${office.area.name}"
							title="区域" url="/sys/area/treeData" cssClass="required"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">机构名称:</label>
		<div class="controls">
			<form:input id="name" path="name" htmlEscape="false" maxlength="50" class="required"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">机构编码:</label>
		<div class="controls">
			<form:input path="code" htmlEscape="false" maxlength="50" cssClass="required"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">机构类型:</label>
		<div class="controls">
			<form:select path="type" class="input-medium">
				<form:options items="${fns:getDictList('sys_office_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
		</div>
	</div>
	<c:choose>
		<c:when test="${office.officeType == '2'}">
			<div class="control-group">
				<label class="control-label">机构类别:</label>
				<div class="controls">
					<form:select path="grade" class="input-medium">
						<form:options items="${fns:getDictList('sys_office_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">床位:</label>
				<div class="controls">
					<form:input path="beds" htmlEscape="false" maxlength="50" cssClass=""/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">医院等级:</label>
				<div class="controls">
					<form:select path="hospitalGrade" class="input-medium">
						<form:options items="${fns:getDictList('hospital_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">是否参保:</label>
				<div class="controls">
					<form:select path="isInsured">
						<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">保险公司:</label>
				<div class="controls">
					<form:select path="insuranceCompany" class="input-medium">
						<form:options items="${fns:getDictList('sys_office_form')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">保单号:</label>
				<div class="controls">
					<form:input path="policyNumber" htmlEscape="false" maxlength="50" cssClass=""/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">参保开始日期:</label>
				<div class="controls">
					<input name="insuredTime" type="text" readonly="readonly" maxlength="20"
						   class="input-medium Wdate "
						   value="${office.insuredTime}"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">参保结束日期:</label>
				<div class="controls">
					<input name="insuredEndTime" type="text" readonly="readonly" maxlength="20"
						   class="input-medium Wdate "
						   value="${office.insuredEndTime}"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">法定代表人:</label>
				<div class="controls">
					<form:input path="legalRepresentative" htmlEscape="false" maxlength="50" cssClass=""/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">主要负责人:</label>
				<div class="controls">
					<form:input path="primaryPerson" htmlEscape="false" maxlength="50" cssClass=""/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">法人联系电话:</label>
				<div class="controls">
					<form:input path="representPhone" htmlEscape="false" maxlength="50" cssClass="phone"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">分管院长:</label>
				<div class="controls">
					<form:input path="directorCharge" htmlEscape="false" maxlength="50" cssClass=""/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">分管院长联系电话:</label>
				<div class="controls">
					<form:input path="directorPhone" htmlEscape="false" maxlength="50" cssClass="phone"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">医务科/纠纷办负责人:</label>
				<div class="controls">
					<form:input path="disputeHead" htmlEscape="false" maxlength="50" cssClass=""/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">医务科/纠纷办负责人联系电话:</label>
				<div class="controls">
					<form:input path="disputePhone" htmlEscape="false" maxlength="50" cssClass="phone"/>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="control-group">
				<label class="control-label">主负责人:</label>
				<div class="controls">
					<sys:treeselect id="primaryPerson" name="primaryPerson.id" value="${office.primaryPerson.id}" labelName="office.primaryPerson.name" labelValue="${office.primaryPerson.name}"
									title="用户" url="/sys/office/treeData?type=3&officeType=${office.officeType}" allowClear="true" notAllowSelectParent="true" dataMsgRequired="必填信息" cssClass=""/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">副负责人:</label>
				<div class="controls">
					<sys:treeselect id="deputyPerson" name="deputyPerson.id" value="${office.deputyPerson.id}" labelName="office.deputyPerson.name" labelValue="${office.deputyPerson.name}"
									title="用户" url="/sys/office/treeData?type=3&officeType=${office.officeType}" allowClear="true" notAllowSelectParent="true" dataMsgRequired="必填信息" cssClass=""/>
				</div>
			</div>
			<%--<div class="control-group">
				<label class="control-label">传真:</label>
				<div class="controls">
					<form:input path="fax" htmlEscape="false" maxlength="50"/>
				</div>
			</div>--%>
		</c:otherwise>
	</c:choose>
	<div class="control-group">
		<label class="control-label">是否可用:</label>
		<div class="controls">
			<form:select path="useable">
				<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
		</div>
	</div>
	<div class="control-group">
		<c:choose>
			<c:when test="${office.officeType == '2'}">
				<label class="control-label">医院地址:</label>
			</c:when>
			<c:otherwise>
				<label class="control-label">联系地址:</label>
			</c:otherwise>
		</c:choose>
		<div class="controls">
			<form:input path="address" htmlEscape="false" maxlength="50" cssClass=""/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">邮政编码:</label>
		<div class="controls">
			<form:input path="zipCode" htmlEscape="false" maxlength="50" cssClass=" "/>
		</div>
	</div>
	<%--<div class="control-group">
		<label class="control-label">负责人:</label>
		<div class="controls">
			<form:input path="master" htmlEscape="false" maxlength="50" cssClass=""/>
		</div>
	</div>--%>
	<div class="control-group">
		<label class="control-label">机构电话(办公):</label>
		<div class="controls">
			<form:input path="phone" htmlEscape="false" maxlength="50" cssClass="phone"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">邮箱:</label>
		<div class="controls">
			<form:input path="email" htmlEscape="false" maxlength="50" cssClass="input-email"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">备注:</label>
		<div class="controls">
			<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
		</div>
	</div>
	<c:if test="${office.officeType == '2'}">
		<div class="control-group">
			<label class="control-label">附件：</label>
			<div class="controls">
				<form:hidden id="fax" path="fax" htmlEscape="false" />
				<sys:ckfinder input="fax" type="files" uploadPath="/office/fax" selectMultiple="true"/>
		</div>
		</div>
	</c:if>
	<c:if test="${empty office.id}">
		<div class="control-group">
			<label class="control-label">快速添加下级部门:</label>
			<div class="controls">
				<form:checkboxes path="childDeptList" items="${fns:getDictList('sys_office_common')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
		</div>
	</c:if>
	<div class="form-actions">
		<shiro:hasPermission name="sys:office:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
</form:form>
</body>
</html>