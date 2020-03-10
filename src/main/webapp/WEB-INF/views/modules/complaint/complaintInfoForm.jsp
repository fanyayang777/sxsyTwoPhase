<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>投诉接待管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
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
			var value='${complaintInfo.handleWay}';
			if(value==''){
			    value = 0;
            }
            next(value);
        });
        function next(value) {
        	var name='${fns:getUser().company.officeType}';
			if(name=='2'){//医院
				$("#pass").hide();
				$("#result").hide();
				$("#yq").hide();
				$("#method").hide();
			}else{
				$("#pass").show();
				$("#result").show();
				$("#yq").show();
				$("#method").show();
			}


            if(value==2){
                document.getElementById("btnSubmit1").style.display="inline";
                document.getElementById("btnSubmit").style.display="none";

                $("#treeTr").show();

            }else{
                document.getElementById("btnSubmit1").style.display="none";
                document.getElementById("btnSubmit").style.display="inline";
				$("#treeTr").hide();
            }

            if(value==1){
                $("<td id='shiftBody' class='tit'>转办科室：</td>").insertAfter("#handleWay");
				$("#shiftHandle").show();
				$("#statusBody").remove();
				$("#statusHandle").hide();
                // document.getElementById("shiftHead").style.display="inline";
                // document.getElementById("shiftHandle").style.display="inline";
			}else  if(value==0){
				$("#shiftBody").remove();
				$("#shiftHandle").hide();
				$("<td id='statusBody' class='tit'>状态：</td>").insertAfter("#handleWay");
				$("#statusHandle").show();
			}else{
                $("#shiftBody").remove();
                $("#shiftHandle").hide();
                // document.getElementById("shiftHead").style.display="none";
                // document.getElementById("shiftHandle").style.display="none";
				$("#statusBody").remove();
				$("#statusHandle").hide();
			}
        }

        function num(value){
        	if(value>=128){
        		alertx("患者年龄输入有误，请重新输入！");
        		$("#patientAge").val("");
			}
		}

		function money(val){
			if(val<0){
				alertx("金额不能为负数，请重新输入！");
				$("#amountInvolved").val("");
				return;
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/complaint/complaintInfo/">投诉接待列表</a></li>
		<li class="active"><a href="${ctx}/complaint/complaintInfo/form?id=${complaintInfo.complaintId}">投诉接待<shiro:hasPermission name="complaint:complaintInfo:edit">${not empty complaintInfo.complaintId?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="complaint:complaintInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="complaintInfo" action="${ctx}/complaint/complaintInfo/save" method="post" class="form-horizontal">
		<form:hidden path="complaintId"/>
		<form:hidden path="complaintMainId"/>
		<form:hidden path="complaintMain.complaintMainId"/>
		<form:hidden path="createDate"/>
		<form:hidden path="createBy"/>
		<input type="hidden" id="flag" name="flag"/>
		<sys:message content="${message}"/>
		<ul id="myTab" class="nav nav-tabs">
			<li class="active">
				<a href="#visitor" data-toggle="tab">来访者信息</a>
			</li>
			<li>
				<a href="#patient" data-toggle="tab">患者信息</a>
			</li>
			<li>
				<a href="#hospital" data-toggle="tab">涉及医院信息</a>
			</li>
			<li>
				<a href="#annex" data-toggle="tab">附件</a>
			</li>
		</ul>

		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="visitor">
				<table class="table-form">
					<tr >
						<td class="tit" width="160px"><font color="red">*</font>访客姓名：</td>
						<td width="476px">
							<form:input path="visitorName" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
						</td>
						<td class="tit" width="180px">访客电话：</td>
						<td >
							<form:input path="visitorMobile" htmlEscape="false" maxlength="15" class="input-xlarge phone"/>
						</td>
					</tr>
					<tr >
						<td class="tit"><font color="red">*</font>与患者关系：</td>
							<td>
								<form:select path="patientRelation" class="input-medium">
									<form:options items="${fns:getDictList('patient_relation')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</td>
						<td class="tit"><font color="red">*</font>来访人数：</td>
						<td >
							<form:input path="visitorNumber" htmlEscape="false" maxlength="10" class="input-xlarge required digits"/>
						</td>
					</tr>
				</table>
			</div>
			<div class="tab-pane fade" id="patient">
				<table class="table-form">
					<tr >
						<td class="tit" width="160px"><font color="red">*</font>患者姓名：</td>
						<td width="476px">
							<form:input path="patientName" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
						</td>
						<td class="tit" width="180px"><font color="red">*</font>患者性别：</td>
						<td >
							<%--<form:input path="patientSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
							<form:select path="patientSex" class="input-medium">
								<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</td>
					</tr>
					<tr >
						<td class="tit"><font color="red">*</font>患者年龄：</td>
						<td >
							<form:input path="patientAge" htmlEscape="false" class="input-xlarge required digits " maxlength="3" onchange="num(this.value)"/>
						</td>
					</tr>
				</table>
			</div>
			<div class="tab-pane fade" id="hospital">
				<table class="table-form">
					<tr >
						<td class="tit" width="160px"><font color="red">*</font>涉及医院：</td>
						<td width="476px">
							<sys:treeselect id="involveHospital" name="involveHospital" value="${not empty fns:getUser().company.officeType and  fns:getUser().company.officeType=='2' and empty complaintInfo.involveHospital ? fns:getUser().office.id : complaintInfo.involveHospital}" labelName="hospitalName" labelValue="${not empty fns:getUser().company.officeType and  fns:getUser().company.officeType=='2' and empty complaintInfo.involveHospital ? fns:getUser().office.name :  complaintInfo.hospitalName}"
											title="机构" url="/sys/office/treeData?type=1&officeType=2" isAll="true" cssClass="required" dataMsgRequired="请选择医院" allowClear="true" notAllowSelectParent="false"/>
						</td>
						<td class="tit" width="180px"><font color="red">*</font>涉及科室：</td>
						<td >
							<%--<sys:treeselect id='involveDepartment' name='involveDepartment' value='${complaintInfo.involveDepartment}' labelName='departmentName' labelValue='${complaintInfo.departmentName}' title='部门' url='/sys/office/treeData?type=2&officeType=2'--%>
											<%--pid='involveHospital' isAll='true' cssClass='required' dataMsgRequired='请选择科室' allowClear='true' notAllowSelectParent='true' disabled='true'/>--%>
                            <%--<form:select path="involveDepartment" class="input-medium">--%>
                                <%--<form:options items="${fns:getDictList('department')}" itemLabel="label" itemValue="value"--%>
                                              <%--htmlEscape="false"/>--%>
                            <%--</form:select>--%>
								<sys:treeselect id="involveDepartment" name="involveDepartment" value="${complaintInfo.involveDepartment}" labelName="typeName"
												labelValue="${complaintInfo.testTree}" title="涉及科室"
												url="/test/testTree/treeData?mold=2" isAll="true" allowClear="true"
												notAllowSelectParent="true" checked="true" cssClass="required" dataMsgRequired="请选择科室"/>
                        </td>
					</tr>
					<tr >
						<td class="tit">涉及人员：</td>
						<td>
                                    <form:input path="involveEmployee" htmlEscape="false"
                                                class="input-xlarge " value="${empty complaintInfo.employeeName?complaintInfo.involveEmployee:complaintInfo.employeeName}"/>
							<%--<sys:treeselect id="involveEmployee" name="involveEmployee" value="${complaintInfo.involveEmployee}" labelName="employeeName" labelValue="${complaintInfo.employeeName}"--%>
											<%--title="用户" url="/sys/office/treeData?type=3&officeType=2" pid="involveDepartment" isAll="true" cssClass="required" dataMsgRequired="请选择人员" allowClear="true" notAllowSelectParent="true"/>--%>
						</td>
					</tr>
				</table>
			</div>
			<div class="tab-pane fade" id="annex">
				<table style="height: 100px;" class="table-form">
					<tr style=" ">
						<td style="text-align: center; width: 20px; font-weight: bolder;height: 50px;">患方材料：</td>
						<input type="hidden" name="fjtype1" value="2">
						<td style="width: 45px;">
							<input type="hidden" id="files1" name="files1" htmlEscape="false" class="input-xlarge"
								   value="${files1}"/>
							<input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
							<div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files"
																		  uploadPath="/complaint/Patient/apply" selectMultiple="true"/></div>
						</td>
					</tr>
					<tr style=" ">
						<td style="text-align: center; width: 20px; font-weight: bolder;height: 50px;">医方材料：</td>
						<input type="hidden" name="fjtype2" value="1">
						<td style="width: 45px;">
							<input type="hidden" id="files2" name="files2" htmlEscape="false" class="input-xlarge"
								   value="${files2}"/>
							<input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">
							<div style="margin-bottom: 0px;margin-top: -45px;"><sys:ckfinder input="files2" type="files"
																		  uploadPath="/complaint/Doctor/apply" selectMultiple="true"/></div>
						</td>
					</tr>
				</table>

				</div>
			</div>
		</div>
	<table class="table-form">
		<tr >
			<td class="tit" width="160px"><font color="red">*</font>案件编号：</td>
			<td width="476px">
				<form:input path="caseNumber" htmlEscape="false" maxlength="20" readonly="true" class="input-xlarge required"/>
			</td>
			<td class="tit" width="180px"><font color="red">*</font>来访日期：</td>
			<td >
				<%--<form:input path="visitorDate" htmlEscape="false" maxlength="10" class="input-xlarge "/>--%>
				<input name="visitorDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${complaintInfo.visitorDate}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
			</td>
		</tr>
		<tr >
			<td class="tit"><font color="red">*</font>投诉方式：</td>
			<td >
				<%--<form:input path="complaintMode" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
				<form:select path="complaintMode" style='width:110px;text-align: center;'>
					<form:option value="0">来电</form:option>
					<form:option value="1">来访</form:option>
					<form:option value="2">来信</form:option>
					<form:option value="3">其他</form:option>
				</form:select>
			</td>
			<td class="tit"><font color="red">*</font>是否重大：</td>
			<td >
				<form:select path="isMajor" style='width:110px;text-align: center;'>
					<form:option value="0">否</form:option>
					<form:option value="1">是</form:option>
				</form:select>
			</td>

		</tr>
		<tr>
			<td class="tit">投诉原因：</td>
			<td>
				<sys:treeselect id="complaintType" name="complaintType" value="${complaintInfo.complaintType}" labelName="typeName" labelValue="${complaintInfo.typeName}" title="调解类别"
								url="/test/testTree/treeData?mold=1" isAll="true" allowClear="true" notAllowSelectParent="true"/>
			</td>
		</tr>
		<tr>
			<td class="tit"><font color="red">*</font>投诉纠纷概要：</td>
			<td colspan="3">
				<form:textarea path="summaryOfDisputes" htmlEscape="false" class="input-xlarge required" style="margin: 0px; width: 938px; height: 125px;"/>
			</td>
		</tr>
		<tr>
			<td class="tit"><font color="red">*</font>诉求：</td>
			<td colspan="3">
				<form:textarea path="appeal" htmlEscape="false" class="input-xlarge required" style="margin: 0px; width: 939px; height: 24px;"/>
			</td>
		</tr>
		<tr>
			<td  class="tit">处理方式：</td>
			<td id="handleWay">
				<form:select path="handleWay" style='width:110px;text-align: center;' onchange="next(this.value)">
					<form:option value="0">当面处理</form:option>
					<form:option value="1">转办处理</form:option>
					<form:option value="2">转医调委</form:option>
					<form:option value="3">法院诉讼</form:option>
					<form:option value="4">行政调解</form:option>
				</form:select>
			</td>
			<td id="shiftHandle">
				<%--<sys:treeselect id="shiftHandle" name="shiftHandle" value="${complaintInfo.shiftHandle}" labelName="shiftHandleName" labelValue="${complaintInfo.shiftHandleName}" title="部门" url="/sys/office/treeData?type=2&officeType=2"--%>
								<%--pid="${fns:getUser().company.id}" isAll="true" cssClass="required" dataMsgRequired="请选择科室" allowClear="true" notAllowSelectParent="true" disabled="true"/>--%>
				<form:select path="shiftHandle" class="input-medium">
					<form:options items="${fns:getDictList('department')}" itemLabel="label" itemValue="value"
								  htmlEscape="false"/>
				</form:select>
			</td>

			<td id="statusHandle">
				<form:select path="status" style='width:110px;text-align: center;'>
					<form:option value="0">处理中</form:option>
					<form:option value="1">协调中</form:option>
					<form:option value="2">结案</form:option>
				</form:select>
			</td>

		</tr>
		<tr id="pass">
			<td class="tit"><font color="red">*</font>处理经过：</td>
			<td colspan="3">
				<form:textarea path="handlePass" htmlEscape="false" class="input-xlarge required" style="margin: 0px; width: 938px; height: 125px;"/>
			</td>
		</tr>
		<tr id="result">
			<td class="tit">处理结果：</td>
			<td colspan="3">
				<form:textarea path="handleResult" htmlEscape="false" class="input-xlarge " style="margin: 0px; width: 938px; height: 125px;"/>
			</td>
		</tr>
		<tr id="method">
			<td class="tit">结案方式：</td>
			<td colspan="3">
				<form:textarea path="closingMethod" htmlEscape="false" class="input-xlarge " style="margin: 0px; width: 938px; height: 125px;"/>
			</td>
		</tr>
		<tr id="yq">
			<td class="tit">结案预期：</td>
			<td >
				<input id="expectedClosure" name="expectedClosure" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="${complaintInfo.expectedClosure}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
			</td>
			<td class="tit">涉及金额：</td>
			<td >
				<form:input path="amountInvolved" htmlEscape="false" maxlength="50" class="input-xlarge  number" onchange="money(this.value);"/>
			</td>
		</tr>
		<tr>
			<td class="tit"><font color="red">*</font>接待人员：</td>
			<td >
				<%--<form:input path="receptionEmployee" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
				<sys:treeselect id="receptionEmployee" name="receptionEmployee" value="${empty complaintInfo.receptionEmployee ? fns:getUser().id : complaintInfo.receptionEmployee}" labelName="employee.name" labelValue="${empty complaintInfo.employee.name ?fns:getUser().name : complaintInfo.employee.name}"
								title="用户" url="/sys/office/treeData?type=3&${fns:getUser().company.officeType=='1' ? 'officeType=1&isAll=true' :'officeType=2'}"  cssClass="input-big required" dataMsgRequired="请选择接待人" allowClear="true" notAllowSelectParent="true"/>
			</td>

			<td class="tit"><font color="red">*</font>接待时间：</td>
			<td >
				<%--<form:input path="receptionDate" htmlEscape="false" maxlength="20" class="input-xlarge "/>--%>
				<input name="receptionDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${complaintInfo.receptionDate}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
			</td>
		</tr>

		<tr id="treeTr">
			<%--<td class="tit"><font color="red">*</font>是否进入医调委调解：</td>--%>
			<%--<td>--%>
				<%--<form:select id="isMediate" path="isMediate" style='width:110px;text-align: center;' >--%>
					<%--<form:option value="0">否</form:option>--%>
					<%--<form:option value="1">是</form:option>--%>
				<%--</form:select>--%>
			<%--</td>--%>
			<%--<td class="hidden"><font color="red">*</font>下一处理环节：</td>--%>
			<%--<td class="hidden">--%>
				<%--<form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
			<%--</td>--%>
			<td class="tit"><font color="red">*</font>下一环节处理人：</td>
			<td >
				<%--<form:input path="nextLinkMan" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
				<sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${complaintInfo.nextLinkMan}" labelName="link.name" labelValue="${complaintInfo.link.name}"
								title="用户" url="/sys/office/treeData?type=3&officeType=1${fn:contains(fns:getUser().office.name, '工作站') ? '': '&isAll=true' }${fns:getUser().company.officeType=='2' ? '&next=yy' : ''}" role="${fns:getUser().company.officeType=='1' ?'distribution':'' }"  cssClass="required" dataMsgRequired="请选择下一环节处理人" allowClear="true" notAllowSelectParent="true"/>
			</td>
		</tr>
	</table>
		<div class="form-actions">
			<shiro:hasPermission name="complaint:complaintInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#flag').val('no')"/>&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="complaint:complaintInfo:edit"><input id="btnSubmit1" class="btn btn-primary" type="submit" value="下一步" onclick="$('#flag').val('yes')"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>