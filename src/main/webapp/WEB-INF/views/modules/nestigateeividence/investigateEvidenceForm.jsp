<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>调查取证管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					var aa=$("#export").val();
					if(aa=='no'){
						loading('正在提交，请稍等...');
					}
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
		function exportWord() {
			var aa=$("#export").val();
			var path="${ctx}/nestigateeividence/investigateEvidence/pass";
			$.post(path,{'investigateEvidenceId':'${investigateEvidence.investigateEvidenceId}','export':aa,"print":"true"},function(res){
				if(res.data.url!=''){
					var url='${pageContext.request.contextPath}'+res.data.url;
					//window.location.href='${pageContext.request.contextPath}'+res.data.url ;
					windowOpen(url,"pdf",1500,700);
					// window.open(url, "_blank", "scrollbars=yes,resizable=1,modal=false,alwaysRaised=yes");
				}else{
				}
			},"json");
		}

		//导出和打印加提示
		$(function (){
			$(function () { $("[data-toggle='tooltip']").tooltip({html : true }); });
		});

        function removeCssClass() {
            $('#startTime').removeClass('required');
            $('#investigateEvidence\\.startTime').removeClass('required');
            $('#endTime').removeClass('required');
            $('#investigateEvidence\\.endTime').removeClass('required');
            $('#investigator').removeClass('required');
            $('#investigateEvidence\\.investigator').removeClass('required');
            $('#noteTaker').removeClass('required');
            $('#investigateEvidence\\.noteTaker').removeClass('required');
            $('#content').removeClass('required');
            $('#investigateEvidence\\.content').removeClass('required');
        }
        function addCssClass() {
            $('#startTime').addClass('required');
            $('#investigateEvidence\\.startTime').addClass('required');
            $('#endTime').addClass('required');
            $('#investigateEvidence\\.endTime').addClass('required');
            $('#investigator').addClass('required');
            $('#investigateEvidence\\.investigator').addClass('required');
            $('#noteTaker').addClass('required');
            $('#investigateEvidence\\.noteTaker').addClass('required');
            $('#content').addClass('required');
            $('#investigateEvidence\\.content').addClass('required');
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/nestigateeividence/investigateEvidence/">列表</a></li>
		<li class="active"><a href="${ctx}/nestigateeividence/investigateEvidence/form?id=${investigateEvidence.id}" id="c">调查取证<shiro:hasPermission name="nestigateeividence:investigateEvidence:edit">${not empty investigateEvidence.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="nestigateeividence:investigateEvidence:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="investigateEvidence" action="${ctx}/nestigateeividence/investigateEvidence/save" method="post" class="form-horizontal">
		<form:hidden path="investigateEvidenceId"/>
		<form:hidden path="createDate"/>
		<form:hidden path="createBy"/>
		<form:hidden path="complaintMainId"/>
		<form:hidden path="complaintMain.complaintMainId"/>
		<form:hidden path="complaintMain.act.taskId"/>
		<form:hidden path="complaintMain.act.taskName"/>
		<form:hidden path="complaintMain.act.taskDefKey"/>
		<form:hidden path="complaintMain.act.procInsId"/>
		<form:hidden path="complaintMain.act.procDefId"/>
		<form:hidden path="complaintMain.procInsId"/>
		<form:hidden id="flag" path="complaintMain.act.flag"/>
		<form:hidden path="investigateEvidence.investigateEvidenceId"/>
		<form:hidden path="investigateEvidence.complaintMainId"/>
		<form:hidden path="investigateEvidence.createDate"/>
		<form:hidden path="investigateEvidence.createBy"/>
		<form:hidden path="investigateEvidence.investigateType"/>
		<form:hidden path="investigateEvidence.nextLinkMan"/>
		<form:hidden path="respondentInfo.respondentId"/>
		<form:hidden path="respondentInfo2.respondentId"/>
		<form:hidden path="respondentInfo3.respondentId"/>
		<form:hidden path="respondentInfo4.respondentId"/>
		<input type="hidden"  id="export" name="export"/>
		<sys:message content="${message}"/>
		<ul id="myTab" class="nav nav-tabs">
			<li class="active">
				<a href="#visitor" data-toggle="tab">患方调查笔录</a>
			</li>
			<li>
				<a href="#patient" data-toggle="tab">医方调查笔录</a>
			</li>
			<li>
				<a href="#hospital" data-toggle="tab">附件</a>
			</li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="visitor">
				<input type="hidden" name="investigateType" value="1">
				<table class="table-form">
					<tr >
						<td class="tit" width="140px" style=" border-right:1px #e2e2e2 solid;"><font color="red">*</font>调查时间：</td>
						<td style="width: 105px;">
							<input id="startTime" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
								   value="${investigateEvidence.startTime}"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width: 250px;height: 25px;"/>
							<span class="help-inline" style="width: 10px;"><font color="red" style="width: 10px;">*</font> </span>
						</td>
						<td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;border-Left:1px #e2e2e2 solid;"><font color="red">*</font>结束时间：</td>

						<td width="195px;">
							<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
								   value="${investigateEvidence.endTime}"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width: 250px; height: 25px;"/>
							<span class="help-inline"><font color="red" style="width: 10px;">*</font> </span>
						</td>
					</tr>
					<tr >
						<td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;"><font color="red">*</font>调查地点：</td>
						<td style="width: 105px;">
							<form:select path="address" class="input-xlarge" cssStyle="text-align:center;">
								<form:options items="${fns:getDictList('meeting')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
							<%--<form:input path="" htmlEscape="false" maxlength="200" class="input-xlarge required" cssStyle="width:480px;"/>--%>
						</td>
						<td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;border-Left:1px #e2e2e2 solid;"><font color="red">*</font>调查事由：</td>

						<td width="195px;">
							<form:input path="cause" htmlEscape="false" maxlength="500" class="input-xlarge required" readonly="true" cssStyle="width:480px;" />
						</td>
					</tr>
					<tr >
						<td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;"><font color="red">*</font>调查人：</td>
						<td style="width: 105px;">
							<form:input path="investigator" htmlEscape="false" maxlength="32" class="input-xlarge required" cssStyle="width:480px;"/>
						</td>
						<td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;border-Left:1px #e2e2e2 solid;"><font color="red">*</font>记录人：</td>

						<td width="195px;">
							<form:input path="noteTaker" htmlEscape="false" maxlength="32" class="input-xlarge required" cssStyle="width:480px;"/>
						</td>
					</tr>
					<%--<tr >
						<td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;"><font color="red">*</font>反应焦点：</td>
						<td style="width: 105px;">
							<form:input path="focus" htmlEscape="false" maxlength="500" class="input-xlarge required" cssStyle="width:480px;"/>
						</td>
					</tr>--%>
					<tr >
						<td class="tit" width="140px" style="border-right:1px #e2e2e2 solid; " ><font color="red">*</font>笔录内容：</td>
						<td style="width: 105px;" colspan="3">
							<form:textarea path="content" htmlEscape="false" rows="15" class="input-xxlarge required" cssStyle="width:1300px;"/>
						</td>
					</tr>
					<tr >
						<td colspan="4">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input id="patientExport" class="btn btn-primary" type="submit" value="导 出" onclick="$('#export').val('patientTake')" data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在导出数据之前请先保存数据。</h4>"/>
							<input id="patientPrint" class="btn btn-primary" type="button" value="打 印" onclick="$('#export').val('patientTake');exportWord();" data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在打印数据之前请先保存数据。</h4>"/><%--promptx('打印文件','打印机名称',document.getElementById('inputForm').action)--%>

						</td>
					</tr>
				</table>
				<ul  class="nav nav-tabs">
					<li class="active">
						<a href="#investigation1" data-toggle="tab">被调查人1</a>
					</li>
					<li>
						<a href="#investigation2" data-toggle="tab">被调查人2</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane fade in active" id="investigation1">
						<table class="table-form">
							<tr >
								<td class="tit" width="140px">被调查人身份:</td>
								<td style="border: hidden;border-left: 1px #e2e2e2 solid;" colspan="5">
									<form:radiobuttons path="respondentInfo.respondentIdentity" items="${fns:getDictList('investigation')}" itemLabel="label" itemValue="value" htmlEscape="false" cssStyle=""/>
								</td>
							</tr>
							<tr >
								<td class="tit">姓名：</td>
								<td width="200px">
									<form:input path="respondentInfo.respondentName" htmlEscape="false" maxlength="10" class="input-xlarge " cssStyle=""/>
								</td>
								<td class="tit" width="140px">性别：</td>
								<td width="100px">
									<form:select path="respondentInfo.respondentSex" class="input-medium " style="text-align:center;" cssStyle="width: 100px; text-align: center;">
										<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false" />
									</form:select>
								</td>
								<td class="tit" width="140px">年龄：</td>
								<td>
									<form:input path="respondentInfo.respondentAge" htmlEscape="false" maxlength="4" class="input-xlarge " cssStyle="width: 50px;text-align: center;"/>
								</td>

							</tr>
							<tr >
								<td class="tit">联系方式：</td>
								<td style="">
									<form:input path="respondentInfo.respondentMobile" htmlEscape="false" maxlength="15" class="input-xlarge "/>
								</td>
								<td class="tit"  style="">职务:</td>
								<td style=" " colspan="3">
									<form:input path="respondentInfo.respondentPost" htmlEscape="false" maxlength="30" class="input-xlarge " cssStyle="width: 90px;"/>
								</td>
							</tr>
							<tr>
								<td class="tit" style="">工作单位:</td>
								<td style="" colspan="5">
									<form:input path="respondentInfo.respondentWorkUnit" htmlEscape="false" maxlength="30" class="input-xlarge " cssStyle="width: 535px;"/>
								</td>
							</tr>
						</table>
					</div>
					<div class="tab-pane " id="investigation2">
						<table class="table-form">
							<tr >
								<td class="tit" width="140px">被调查人身份:</td>
								<td style="border: hidden;border-left: 1px #e2e2e2 solid;" colspan="5">
									<form:radiobuttons path="respondentInfo2.respondentIdentity" items="${fns:getDictList('investigation')}" itemLabel="label" itemValue="value" htmlEscape="false" cssStyle=""/>
								</td>
							</tr>
							<tr >
								<td class="tit">姓名：</td>
								<td width="200px">
									<form:input path="respondentInfo2.respondentName" htmlEscape="false" maxlength="10" class="input-xlarge " cssStyle=""/>
								</td>
								<td class="tit" width="140px">性别：</td>
								<td width="100px">
									<form:select path="respondentInfo2.respondentSex" class="input-medium " style="text-align:center;" cssStyle="width: 100px; text-align: center;">
										<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false" />
									</form:select>
								</td>
								<td class="tit" width="140px">年龄：</td>
								<td>
									<form:input path="respondentInfo2.respondentAge" htmlEscape="false" maxlength="4" class="input-xlarge " cssStyle="width: 50px;text-align: center;"/>
								</td>

							</tr>
							<tr >
								<td class="tit">联系方式：</td>
								<td style="">
									<form:input path="respondentInfo2.respondentMobile" htmlEscape="false" maxlength="15" class="input-xlarge "/>
								</td>
								<td class="tit"  style="">职务:</td>
								<td style=" " colspan="3">
									<form:input path="respondentInfo2.respondentPost" htmlEscape="false" maxlength="30" class="input-xlarge " cssStyle="width: 90px;"/>
								</td>
							</tr>
							<tr>
								<td class="tit" style="">工作单位:</td>
								<td style="" colspan="5">
									<form:input path="respondentInfo2.respondentWorkUnit" htmlEscape="false" maxlength="30" class="input-xlarge " cssStyle="width: 535px;"/>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="patient">
				<input type="hidden" name="investigateType2" value="2">
				<table class="table-form">
					<tr >
						<td class="tit" width="140px" style=" border-right:1px #e2e2e2 solid;"><font color="red">*</font>调查时间：</td>
						<td style="width: 105px;">
							<input name="investigateEvidence.startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
								   value="${investigateEvidence.investigateEvidence.startTime}"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width: 250px;height: 25px;"/>
							<span class="help-inline" style="width: 10px;"><font color="red" style="width: 10px;">*</font> </span>
						</td>
						<td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;border-Left:1px #e2e2e2 solid;"><font color="red">*</font>结束时间：</td>

						<td width="195px;">
							<input name="investigateEvidence.endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
								   value="${investigateEvidence.investigateEvidence.endTime}"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width: 250px; height: 25px;"/>
							<span class="help-inline"><font color="red" style="width: 10px;">*</font> </span>
						</td>
					</tr>
					<tr >
						<td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;"><font color="red">*</font>调查地点：</td>
						<td style="width: 105px;">
							<form:select path="investigateEvidence.address" class="input-xlarge" cssStyle="text-align:center;">
								<form:options items="${fns:getDictList('meeting')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
							<%--<form:input path="investigateEvidence.address" htmlEscape="false" maxlength="200" class="input-xlarge required" cssStyle="width:480px;"/>
							<input type="hidden" value="${investigateEvidence.investigateEvidence.address}"/>--%>
						</td>
						<td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;border-Left:1px #e2e2e2 solid;"><font color="red">*</font>调查事由：</td>

						<td width="195px;">
							<form:input path="investigateEvidence.cause" htmlEscape="false" maxlength="500" class="input-xlarge required" cssStyle="width:480px;" readonly="true"/>
						</td>
					</tr>
					<tr >
						<td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;"><font color="red">*</font>调查人：</td>
						<td style="width: 105px;">
							<form:input path="investigateEvidence.investigator" htmlEscape="false" maxlength="32" class="input-xlarge required" cssStyle="width:480px;"/>
						</td>
						<td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;border-Left:1px #e2e2e2 solid;"><font color="red">*</font>记录人：</td>

						<td width="195px;">
							<form:input path="investigateEvidence.noteTaker" htmlEscape="false" maxlength="32" class="input-xlarge required" cssStyle="width: 480px;"/>
						</td>
					</tr>
					<%--<tr >--%>
						<%--<td class="tit" width="140px" style="border: hidden;border-right:1px #e2e2e2 solid;"><font color="red">*</font>反应焦点：</td>--%>
						<%--<td style="width: 105px;">--%>
							<%--<form:input path="focus" htmlEscape="false" maxlength="500" class="input-xlarge " cssStyle="border: hidden; width: 350px;"/>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<tr >
						<td class="tit" width="140px" style="border-right:1px #e2e2e2 solid; "><font color="red">*</font>笔录内容：</td>
						<td style="width: 105px;" colspan="3">
							<form:textarea path="investigateEvidence.content" htmlEscape="false" rows="15" class="input-xxlarge required" cssStyle="width:1300px;"/>
						</td>
					</tr>
					<tr >
						<td colspan="4">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input id="doctorExport" class="btn btn-primary" type="submit" value="导 出" onclick="$('#export').val('hospitalTake')" data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在导出数据之前请先保存数据。</h4>"/>
							<input id="doctorPrint" class="btn btn-primary" type="button" value="打 印" onclick="$('#export').val('hospitalTake');exportWord();" data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在打印数据之前请先保存数据。</h4>"/><%--promptx('打印文件','打印机名称',document.getElementById('inputForm').action)--%>

						</td>
					</tr>
					</table>
				<ul  class="nav nav-tabs">
					<li class="active">
						<a href="#investigation3" data-toggle="tab">被调查人1</a>
					</li>
					<li>
						<a href="#investigation4" data-toggle="tab">被调查人2</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane fade in active" id="investigation3">
						<table class="table-form">
							<tr >
								<td class="tit" width="140px">被调查人身份:</td>
								<td style="border: hidden;border-left: 1px #e2e2e2 solid;" colspan="5">
									<form:radiobuttons path="respondentInfo3.respondentIdentity" items="${fns:getDictList('investigation')}" itemLabel="label" itemValue="value" htmlEscape="false" cssStyle=""/>
								</td>
							</tr>
							<tr >
								<td class="tit">姓名：</td>
								<td width="200px">
									<form:input path="respondentInfo3.respondentName" htmlEscape="false" maxlength="10" class="input-xlarge " cssStyle=""/>
								</td>
								<td class="tit" width="140px">性别：</td>
								<td width="100px">
									<form:select path="respondentInfo3.respondentSex" class="input-medium " style="text-align:center;" cssStyle="width: 100px; text-align: center;">
										<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false" />
									</form:select>
								</td>
								<td class="tit" width="140px">年龄：</td>
								<td>
									<form:input path="respondentInfo3.respondentAge" htmlEscape="false" maxlength="4" class="input-xlarge " cssStyle="width: 50px;text-align: center;"/>
								</td>

							</tr>
							<tr >
								<td class="tit">联系方式：</td>
								<td style="">
									<form:input path="respondentInfo3.respondentMobile" htmlEscape="false" maxlength="15" class="input-xlarge "/>
								</td>
								<td class="tit"  style="">职务:</td>
								<td style=" " colspan="3">
									<form:input path="respondentInfo3.respondentPost" htmlEscape="false" maxlength="30" class="input-xlarge " cssStyle="width: 90px;"/>
								</td>
							</tr>
							<tr>
								<td class="tit" style="">工作单位:</td>
								<td style="" colspan="5">
									<form:input path="respondentInfo3.respondentWorkUnit" htmlEscape="false" maxlength="30" class="input-xlarge " cssStyle="width: 535px;"/>
								</td>
							</tr>
						</table>
					</div>
					<div class="tab-pane fade" id="investigation4">
						<table class="table-form">
							<tr >
								<td class="tit" width="140px">被调查人身份:</td>
								<td style="border: hidden;border-left: 1px #e2e2e2 solid;" colspan="5">
									<form:radiobuttons path="respondentInfo4.respondentIdentity" items="${fns:getDictList('investigation')}" itemLabel="label" itemValue="value" htmlEscape="false" cssStyle=""/>
								</td>
							</tr>
							<tr >
								<td class="tit">姓名：</td>
								<td width="200px">
									<form:input path="respondentInfo4.respondentName" htmlEscape="false" maxlength="10" class="input-xlarge " cssStyle=""/>
								</td>
								<td class="tit" width="140px">性别：</td>
								<td width="100px">
									<form:select path="respondentInfo4.respondentSex" class="input-medium " style="text-align:center;" cssStyle="width: 100px; text-align: center;">
										<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false" />
									</form:select>
								</td>
								<td class="tit" width="140px">年龄：</td>
								<td>
									<form:input path="respondentInfo4.respondentAge" htmlEscape="false" maxlength="4" class="input-xlarge " cssStyle="width: 50px;text-align: center;"/>
								</td>

							</tr>
							<tr >
								<td class="tit">联系方式：</td>
								<td style="">
									<form:input path="respondentInfo4.respondentMobile" htmlEscape="false" maxlength="15" class="input-xlarge "/>
								</td>
								<td class="tit"  style="">职务:</td>
								<td style=" " colspan="3">
									<form:input path="respondentInfo4.respondentPost" htmlEscape="false" maxlength="30" class="input-xlarge " cssStyle="width: 90px;"/>
								</td>
							</tr>
							<tr>
								<td class="tit" style="">工作单位:</td>
								<td style="" colspan="5">
									<form:input path="respondentInfo4.respondentWorkUnit" htmlEscape="false" maxlength="30" class="input-xlarge " cssStyle="width: 535px;"/>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="hospital">
				<table class="table-form">

					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">患方笔录：</td>
						<input type="hidden"  name="fjtype1" value="3">
					<td style="width: 450px; ">

						<input type="hidden" id="files1" name="files1" htmlEscape="false" class="input-xlarge"  value="${files1}"/>
						<input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
							<%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
						<div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files"  uploadPath="/nestigateeividence/investigateEvidence/huanbilu" selectMultiple="true" maxWidth="100" maxHeight="100"/></div>
					</td>

					</tr>
					<tr style="" >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">患方补充材料：</td>
						<input type="hidden" name="fjtype2" value="4">
					<td style="width: 450px; ">
						<input type="hidden" id="files2" name="files2" htmlEscape="false" class="input-xlarge" value="${files2}" />
						<input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">
							<%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
						<div style="margin-top: -45px;"><sys:ckfinder input="files2" type="files"  uploadPath="/nestigateeividence/investigateEvidence/huanbuchong" selectMultiple="true" maxWidth="100" maxHeight="100"/></div>
					</td>

					</tr>
					<tr style="" >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医方笔录：</td>
						<input type="hidden"  name="fjtype3" value="5">
					<td style="width: 450px; ">

						<input type="hidden" id="files3" name="files3" htmlEscape="false" class="input-xlarge"  value="${files3}"/>
						<input type="hidden" id="acceId3" name="acceId1" value="${acceId3}">
							<%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
						<div style="margin-top: -45px;"><sys:ckfinder input="files3" type="files"  uploadPath="/nestigateeividence/investigateEvidence/yibilu" selectMultiple="true" maxWidth="100" maxHeight="100"/></div>
					</td>

					</tr>
					<tr style="" >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医方补充材料：</td>
						<input type="hidden" name="fjtype4" value="6">
					<td style="width: 450px;">
						<input type="hidden" id="files4" name="files4" htmlEscape="false" class="input-xlarge" value="${files4}" />
						<input type="hidden" id="acceId4" name="acceId1" value="${acceId4}">
							<%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
						<div style="margin-top: -45px;"><sys:ckfinder input="files4" type="files"  uploadPath="/nestigateeividence/investigateEvidence/yibuchong" selectMultiple="true" maxWidth="100" maxHeight="100"/></div>
					</td>

					</tr>
				</table>


			</div>
		</div>


		<%--<div class="control-group">--%>
			<%--<label class="control-label">被调查类型 1 患方  2 医方：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="investigateType" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>

		<table class="table-form" style="margin-top: 20px;">
			<%--<tr style=" ">
				<td class="tit" width="100px" style="display: inline-block; border-right:1px #e2e2e2 solid; width: 140px;padding-top: 8px;height: 25px;"><font color="red">*</font>处理人：</td>
				<td style="width: 450px;display: inline-block;margin-left: 1px;">
					<form:input path="handlePeople" htmlEscape="false" maxlength="32" class="input-xlarge " cssStyle="width: 435px;"/>
				</td>
				<td class="tit" width="100px" style="display: inline-block; border-right:1px #e2e2e2 solid; width: 120px;padding-top: 8px;height: 25px;margin-left: 0px;"><font color="red">*</font>处理日期：</td>

				<td width="195px;" style="width: 540px;display: inline-block;margin-left: 750px;margin-top: -62px;height: 30px;">
					<input name="witnessTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
						   value="<fmt:formatDate value="${investigateEvidence.handleTime}" pattern="yyyy-MM-dd "/>"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',isShowClear:false});" style="width: 270px; height: 25px; margin-top: -3px;"/>
				</td>
			</tr>--%>
			<tr  style="">

				<td class="tit" width="100px" style="display: inline-block; border-right:1px #e2e2e2 solid; width: 120px;padding-top: 8px;height: 25px;margin-top: -15px;margin-left: 0px;"><font color="red">*</font>下一环节处理人：</td>

				<td width="540px;" style="display:inline-block;margin-top: -15px;margin-left: 1px;">
					<sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${empty investigateEvidence.nextLinkMan?fns:getUser().id:investigateEvidence.nextLinkMan}" labelName="" labelValue="${empty investigateEvidence.linkEmployee.name?fns:getUser().name:investigateEvidence.linkEmployee.name}"
									title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass="required" allowClear="true" isAll="true" notAllowSelectParent="true"  cssStyle="width: 230px;" dataMsgRequired="必填信息"/>

				</td>

			</tr>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="nestigateeividence:investigateEvidence:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#flag').val('no'),$('#export').val('no'),removeCssClass()"/>&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="nestigateeividence:investigateEvidence:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="下一步" onclick="$('#flag').val('yes'),$('#export').val('no'),addCssClass()"/>&nbsp;</shiro:hasPermission>
			<%--<shiro:hasPermission name="nestigateeividence:investigateEvidence:edit"><input id="btnSubmit" class="btn btn-primary"--%>
																					<%--type="submit" value="导出全部"--%>
																					<%--onclick="$('#export').val('all')"/>&nbsp;</shiro:hasPermission>--%>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<act:histoicFlow procInsId="${investigateEvidence.complaintMain.procInsId}" />
	</form:form>
</body>
</html>