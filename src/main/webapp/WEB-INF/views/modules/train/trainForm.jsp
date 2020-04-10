<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>培训视频管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//给多选框 赋值
			var send = '${training.send}';
			var arr=send.split(",");
			$("#send").val(arr).trigger('change');

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
		<li><a href="${ctx}/train/train/">培训视频列表</a></li>
		<li class="active"><a href="${ctx}/train/train/form?id=${train.id}">培训视频<shiro:hasPermission name="train:train:edit">${not empty train.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="train:train:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="train" action="${ctx}/train/train/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">视频类型：</label>
			<div class="controls">
				<form:select path="vidioType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('video_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">科室：</label>
			<div class="controls">
				<sys:treeselect id="department" name="department" value="${training.department}" labelName="typeName"
								labelValue="${training.testTree}" title="涉及科室"
								url="/test/testTree/treeData?mold=2" isAll="true" allowClear="true"
								notAllowSelectParent="true"  cssClass="required" dataMsgRequired="请选择科室"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">课程介绍：</label>
			<div class="controls">
				<form:textarea path="introduce" htmlEscape="false" maxlength="500" class="input-xlarge " style="margin: 0px;width: 39%;font-size: 16px;" rows="5"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发往：</label>
			<div class="controls">
				<form:select path="send" class="input-xlarge required" multiple="true" >
					<form:option value="0">平台</form:option>
					<form:option value="1">小程序</form:option>
					<form:option value="2">网站</form:option>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">视频文件：</label>
			<div class="controls">
				<form:hidden id="path" path="path" htmlEscape="false" maxlength="1000" class="input-xlarge"/>
				<sys:ckfinder input="path" type="files" uploadPath="/train/training" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评分：</label>
			<div class="controls">
				<form:textarea path="score" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片路径：</label>
			<div class="controls">
				<form:hidden id="picturePath" path="picturePath" htmlEscape="false" maxlength="1000" class="input-xlarge"/>
				<sys:ckfinder input="picturePath" type="files" uploadPath="/train/train" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否付费：</label>
			<div class="controls">
				<form:select path="payment" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">课程章节：</label>
			<div class="controls">
				<form:input path="courseChapter" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">购买人数：</label>
			<div class="controls">
				<form:input path="buyNumber" htmlEscape="false" maxlength="6" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">视频价格：</label>
			<div class="controls">
				<form:input path="videoPrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">适用人群：</label>
			<div class="controls">
				<form:input path="intendedFor" htmlEscape="false" maxlength="30" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预备知识：</label>
			<div class="controls">
				<form:input path="preknowledge" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">讲师名字：</label>
			<div class="controls">
				<form:input path="lecturerName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">讲师所属单位：</label>
			<div class="controls">
				<form:input path="lecturerCompany" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="train:train:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>