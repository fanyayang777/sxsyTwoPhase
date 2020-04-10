<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>投诉接待管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			relation($("#patientSex").val()=='1' ? '男' : '女','patientSex2');
			$("#inputForm").validate({
				submitHandler: function(form){
					var aa=$("#export").val();
					getRepeat(aa,form);
					/*loading('正在提交，请稍等...');
					form.submit();*/
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
                //document.getElementById("btnSubmit").style.display="none";
				document.getElementById("badj").style.display="block";
                $("#treeTr").show();

            }else{
                document.getElementById("btnSubmit1").style.display="none";
                //document.getElementById("btnSubmit").style.display="inline";
                document.getElementById("badj").style.display="none";
				$("#treeTr").hide();
            }

            if(value==1){
                $("<td id='shiftBody' class='tit'>转办科室：</td>").insertAfter("#handleWay");
				$("#shiftHandle").show();
				$("#statusBody").remove();
				$("#statusHandle").hide();
				// $("#badj").remove();
                // document.getElementById("shiftHead").style.display="inline";
                // document.getElementById("shiftHandle").style.display="inline";
			}else  if(value==0){
				$("#shiftBody").remove();
				$("#shiftHandle").hide();
				$("<td id='statusBody' class='tit'>状态：</td>").insertAfter("#handleWay");
				$("#statusHandle").show();
                // $("#badj").remove();
			}else{
                $("#shiftBody").remove();
                $("#shiftHandle").hide();
                // document.getElementById("shiftHead").style.display="none";
                // document.getElementById("shiftHandle").style.display="none";
				$("#statusBody").remove();
				$("#statusHandle").hide();

			}


            // window.onload = function(){
            //    var handleWay = document.getElementById("handleWay");
            //    var badj = document.getElementById("badj");
            //    handleWay.onchange = function () {
            //        badj.style.display = this.value==2 ? "block":"none"
            // 	}
            // }
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
	<script type="text/javascript">
        $(document).ready(function () {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {

                    /*alert("sm"+getRepeat());
                    if(){
                        if(aa=='no') {
                            loading('正在提交，请稍等...');
                        }
                        //form.submit();
                    }*/
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
        function compareDate(){
            var a=$("#disputeTime").val();
            var b=$("#reportTime").val();
            var oDate1 = new Date(a);
            var oDate2 = new Date(b);
            if(oDate1 !=null && oDate2 !=null){
                if(oDate1.getTime() < oDate2.getTime()){
                    top.$.jBox.tip("纠纷发生时间应大于出险时间，请重新设置两个时间");
                    $("#disputeTime").val("");
                    $("#reportTime").val("");
                }
            }

        }

        function exportWord() {
            var aa=$("#export").val();
			loading('正在生成文件，请稍等...');
			setTimeout(function(){
				closeTip();
			}, 3000);
            var path="${ctx}/registration/reportRegistration/pass";
            $.post(path,{'reportRegistrationId':'${complaintInfo.reportRegistration.reportRegistrationId}','export':aa,"print":"true"},function(res){
                if(res.data.url!=''){
                    var url='${pageContext.request.contextPath}'+res.data.url;
                    <%--window.location.href='${pageContext.request.contextPath}'+res.data.url ;--%>
                    windowOpen(url,"pdf",1500,700);
                    // window.open(url, "_blank", "scrollbars=yes,resizable=1,modal=false,alwaysRaised=yes");
                }else{
                }
            },"json");
        }

        function getRepeat(aa,form) {//在保存前 根据身份证号 和 医院 进行去重验证
            var hospital=$("#involveHospitalId").val();
            var card=$("#complaintMain\\.patientCard").val();
            var complaintMainId=$("#complaintMainId").val();
            var handleWay=$("#handleWay select").val();
            if(handleWay=='2' && aa=='no'){//转医调委处理
				var path="${ctx}/complaintmain/complaintMain/getRepeat";
				$.ajaxSettings.async = false;//ajax 要设置成同步，异步的情况下sucess方法里面设值还没成功，方法就先返回了，这样也取不到值
				$.post(path,{'hospital':hospital,'card':card,'complaintMainId':complaintMainId},function(res){
					if(res.status=='1'){
						top.$.jBox.confirm("身份证号为    “"+card+"”   的患者已与   “"+res.data.name+"”   发生纠纷，案件编号为   "+res.data.number+"    请确认是否为新发生案件？","系统提示",function(v,h,f){
							if(v=="ok"){
								if(aa=='no') {
									loading('正在提交，请稍等...');
								}
								form.submit();
							}
						},{buttonsFocus:1, closed:function(){
							}});
					}else{
						if(aa=='no') {
							loading('正在提交，请稍等...');
						}
						form.submit();
					}
				},"json");
			}else{
				if(aa=='no') {
					loading('正在提交，请稍等...');
					form.submit();
				}else{
					loading('正在生成文件，请稍等...');
					setTimeout(function(){
						closeTip();
					}, 3000);
					form.submit();
				}
			}

        }

        //导出打印提示
        $(function (){
            $(function () { $("[data-toggle='tooltip']").tooltip({html : true }); });
        });

        //根据身份证 计算 年龄周岁
        function GetAge(identityCard) {
            var len = (identityCard + "").length;
            if (len == 0) {
                return 0;
            } else {
                if ((len != 15) && (len != 18))//身份证号码只能为15位或18位其它不合法
                {
                    return 0;
                }
            }
            var strBirthday = "";
            if (len == 18)//处理18位的身份证号码从号码中得到生日和性别代码
            {
                strBirthday = identityCard.substr(6, 4) + "/" + identityCard.substr(10, 2) + "/" + identityCard.substr(12, 2);
            }
            if (len == 15) {
                strBirthday = "19" + identityCard.substr(6, 2) + "/" + identityCard.substr(8, 2) + "/" + identityCard.substr(10, 2);
            }
            //时间字符串里，必须是“/”
            var birthDate = new Date(strBirthday);
            var nowDateTime = new Date();
            var age = nowDateTime.getFullYear() - birthDate.getFullYear();
            //再考虑月、天的因素;.getMonth()获取的是从0开始的，这里进行比较，不需要加1
            if (nowDateTime.getMonth() < birthDate.getMonth() || (nowDateTime.getMonth() == birthDate.getMonth() && nowDateTime.getDate() < birthDate.getDate())) {
                age--;
            }

            if(age!=null && age!=undefined && age!='${reportRegistration.complaintMain.patientAge}'){
                alertx("年龄校验，由'"+'${reportRegistration.complaintMain.patientAge}'+"'改为'"+age+"'！");
                $("#age").text(age);
                $("#patientAge").val(age);
            }
            return age;
        }


        function baoan(){
            document.getElementById('zhu').style.display='none';
        }
        function lf() {
            document.getElementById('zhu').style.display='block';
        }
        function hz() {
            document.getElementById('zhu').style.display='block';
        }
        function sj() {
            document.getElementById('zhu').style.display='block';
        }
        function fj() {
            document.getElementById('zhu').style.display='block';
        }

		function involveHospitalTreeselectCallBack(v, h, f,id){
        	$("#hospitalName").text($("#involveHospitalName").val());

			var path = "${ctx}/complaint/complaintInfo/officeInfo";
			$.ajaxSettings.async = false;//ajax 要设置成同步，异步的情况下sucess方法里面设值还没成功，方法就先返回了，这样也取不到值
			$.post(path,{hospital:id},function (res) {
				if (res.data.areaName != '' && res.data.areaName != undefined){
					$("#area").text(res.data.areaName)
				}
				if (res.data.hospitalGrade != '' && res.data.hospitalGrade != undefined){
					$("#grade").text(res.data.hospitalGrade);
					$("#complaintMain\\.hospitalGrade").val(res.data.gradeValue);

				}
				if (res.data.policyNumber != '' && res.data.policyNumber != undefined){
					$("#reportRegistration\\.policyNumber").val(res.data.policyNumber);
				}

			},"json");
        }

		function involveDepartmentTreeselectCallBack(v, h, f){
			$("#keshi").text($("#involveDepartmentName").val());
		}

        function relation(val,relationId) {
			$("#"+relationId).text(val);// 根据 传来的id  替换文本
		}

		function relationValue(val,relationId) {
			$("#"+relationId).val(val);// 根据 传来的id  替换 value
			$("#complaintMain\\."+relationId).val(val);// 根据 传来的id  替换 value
			$("#reportRegistration\\."+relationId).val(val);// 根据 传来的id  替换 value
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

		<form:hidden path="reportRegistration.reportRegistrationId"/>
		<form:hidden path="reportRegistration.createDate"/>
		<form:hidden path="reportRegistration.createBy"/>
		<form:hidden path="complaintMain.act.taskId"/>
		<form:hidden path="complaintMain.act.taskName"/>
		<form:hidden path="complaintMain.act.taskDefKey"/>
		<form:hidden path="complaintMain.procInsId"/>
		<form:hidden path="complaintMain.patientMobile"/>
		<form:hidden path="complaintMain.hospitalGrade"/>
		<input type="hidden"  id="export" name="export"/>

		<sys:message content="${message}"/>
		<ul id="myTab" class="nav nav-tabs">
			<li class="active">
				<a href="#visitor" data-toggle="tab" onclick="lf()">来访者信息</a>
			</li>
			<li>
				<a href="#patient" data-toggle="tab" onclick="hz()">患者信息</a>
			</li>
			<li>
				<a href="#hospital" data-toggle="tab" onclick="sj()">涉及医院信息</a>
			</li>
			<li id="badj" style="display: none" >
				<a href="#report" data-toggle="tab" onclick="baoan()">报案信息登记表</a>
			</li>
			<li>
				<a href="#annex" data-toggle="tab" onclick="fj()">附件</a>
			</li>
		</ul>

		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="visitor">
				<table class="table-form">
					<tr >
						<td class="tit" width="160px"><font color="red">*</font>访客姓名：</td>
						<td width="476px">
							<form:input path="visitorName" htmlEscape="false" maxlength="20" class="input-xlarge required" onchange="relation(this.value,'reportEmp')"/>
						</td>
						<td class="tit" width="180px">访客电话：</td>
						<td >
							<form:input path="visitorMobile" htmlEscape="false" maxlength="15" class="input-xlarge phone" onchange="relationValue(this.value,'patientMobile')"/>
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
						<td width="476px" >
							<form:input  path="patientName"  onchange ="relation(this.value,'patientName2')"  htmlEscape="false" maxlength="20" class="input-xlarge required"/>
						</td>
						<td class="tit" width="180px"><font color="red">*</font>患者性别：</td>
						<td >
							<form:select path="patientSex" class="input-medium" onchange="relation(this.value=='1'?'男':'女','patientSex2');">
								<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</td>
					</tr>
					<tr >
						<td class="tit"><font color="red">*</font>患者年龄：</td>
						<td >
							<form:input path="patientAge" htmlEscape="false"  class="input-xlarge required digits " maxlength="3" onchange="num(this.value);relation(this.value,'age')"/>
						</td>
					</tr>
				</table>
			</div>
			<div class="tab-pane fade" id="hospital">
				<table class="table-form">
					<tr >
						<td class="tit" width="160px"><font color="red">*</font>涉及医院：</td>
						<td width="476px">
							<c:set var="company" value="${fns:getUser().company}"/>
							<sys:treeselect id="involveHospital" name="involveHospital" value="${company.officeType eq '2' ? company.id : complaintInfo.involveHospital}" labelName="hospitalName" labelValue="${company.officeType eq '2' ? company.name : complaintInfo.hospitalName}"
											title="机构" url="/sys/office/treeData?type=1&officeType=2" isAll="true" cssClass="required" dataMsgRequired="请选择医院" allowClear="true" notAllowSelectParent="false" />
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
                                    <form:input path="involveEmployee" htmlEscape="false" onchange="relation(this.value,'chuxianyisheng')"
                                                class="input-xlarge "/>
							<%--<sys:treeselect id="involveEmployee" name="involveEmployee" value="${complaintInfo.involveEmployee}" labelName="employeeName" labelValue="${complaintInfo.employeeName}"--%>
											<%--title="用户" url="/sys/office/treeData?type=3&officeType=2" pid="involveDepartment" isAll="true" cssClass="required" dataMsgRequired="请选择人员" allowClear="true" notAllowSelectParent="true"/>--%>
						</td>
					</tr>
				</table>
			</div>
			<div class="tab-pane fade" id="report">
				<table class="table-form">
					<tr>
						<td class="tit" width="10%">医疗机构名称:</td>
						<td id="hospitalName" colspan="3" style="font-size: 16px; text-align: center">
								${complaintInfo.hospitalName}
						</td>
						<td class="tit" width="10%">保单号:</td>
						<td colspan="3">
							<form:input path="reportRegistration.policyNumber" htmlEscape="false" maxlength="255" class="input-small required" value="${empty complaintInfo.reportRegistration.policyNumber ? (empty reportRegistration.complaintMain.hospital.policyNumber ? '未参保' : reportRegistration.complaintMain.hospital.policyNumber) : reportRegistration.policyNumber}" cssStyle="width: 87%;height: 30px; text-align: center;" /></td>
					</tr>
					<tr>
						<td class="tit"><font color="red">*</font>纠纷发生时间:</td>
						<td width="15%">
							<input id="disputeTime" name="reportRegistration.disputeTime" type="text" readonly="readonly" maxlength="20"
								   class="input-small Wdate required"
								   value="${complaintInfo.reportRegistration.disputeTime}"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width:90%;height:30px;text-align: center;"  onchange="compareDate(this.value)"/>
						</td>
						<td class="tit" width="7%">机构等级:</td>
						<td id="grade" style="text-align: center;">
								${fns:getDictLabel(complaintInfo.complaintMain.hospitalGrade,'hospital_grade' ,'' )}
							<%--<form:select path="complaintMain.hospitalGrade" disabled="true" cssStyle="width: 80%; text-align: center;">
								<form:options items="${fns:getDictList('hospital_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>--%>
						</td>
						<td class="tit">所属城市:</td>
						<td width="10%" id="area" style="text-align: center;">

								${fns:getOfficeId(complaintInfo.complaintMain.involveHospital).area.name}
						</td>
						<td class="tit" width="7%"><font color="red">*</font>报案人姓名:</td>
						<td id="reportEmp" style="text-align: center;">
							<form:hidden path="reportRegistration.reportEmp" value="${complaintInfo.visitorName}"/>
								${complaintInfo.visitorName}
								<%--<form:input path="reportEmp" htmlEscape="false" maxlength="32" class="input-xlarge required" cssStyle="width: 90%;height: 30px;text-align: center;font-size: 16px;"/>--%>
						</td>
					</tr>
					<tr>
						<td class="tit">患者姓名:</td>
						<td id="patientName2" style="text-align: center">
							${complaintInfo.patientName}
								<%--<form:input path="complaintMain.patientName" readonly="true" htmlEscape="false" maxlength="20" class="input-xlarge required" cssStyle="width: 90%;height: 30px;text-align: center;font-size: 16px;"/>--%>
						</td>
						<td class="tit">性別:</td>
						<td id="patientSex2" style="text-align: center">
							<c:choose>
								<c:when test="${complaintInfo.patientSex eq 1}">
									男
								</c:when>
								<c:otherwise>
									女
								</c:otherwise>
							</c:choose>
						</td>
						<td class="tit">年齡:</td>
						<td id="age" style="text-align: center;">
								${complaintInfo.patientAge}
						</td>
						<td class="tit"><font color="red">*</font>身份证号:</td>
						<td>
								<form:input path="complaintMain.patientCard" htmlEscape="false" maxlength="20" onchange="GetAge(this.value);" class="input-xlarge required card" cssStyle="width: 90%;height: 30px;text-align: center;"/>
						</td>
					</tr>
					<tr>
						<td class="tit"><font color="red">*</font>出险时间:</td>
						<td>
								<input id="reportTime" name="reportRegistration.reportTime" type="text" readonly="readonly" maxlength="20"
								class="input-small Wdate required"
								value="${complaintInfo.reportRegistration.reportTime}"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width:90%;height:30px;text-align: center;"  onchange="compareDate(this.value)"/>
						</td>
						<td class="tit">科室:</td>
						<td id="keshi" style="text-align: center;">
								${complaintInfo.testTree}
						</td>
						<td class="tit">出险医生:</td>
						<td id="chuxianyisheng" style="text-align: center;">
							${complaintInfo.involveEmployee}
						</td>
						<td class="tit">是否使用医责险:</td>
						<td style="text-align: center;">
							<form:select path="reportRegistration.doctorRisk" style='width:110px;text-align: center;'>
								<form:option value="0">否</form:option>
								<form:option value="1">是</form:option>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="tit">
							联系电话:
						</td>
						<td colspan="3" >
							<p style="margin:0pt; orphans:0; widows:0"><font color="red">*</font>
								<span style="font-family:宋体; font-size:12pt; font-weight:bold">患方：</span>
								<span style="font-family:宋体; font-size:12pt; font-weight:bold;text-align: center">
									<form:input path="reportRegistration.patientMobile" htmlEscape="false" maxlength="15" class="input-xlarge required phone" cssStyle="width: 50%;height: 30px; font-size: 16px;"/>
								</span>
							</p>
						</td>
						<td colspan="4">
							<p style="margin:0pt; orphans:0; widows:0"><font color="red">*</font><span
									style="font-family:宋体; font-size:12pt; font-weight:bold">医方：</span>
								<span style="font-family:宋体; font-size:12pt; font-weight:bold">
                            <form:input path="reportRegistration.doctorMobile" htmlEscape="false" maxlength="15"
                                        class="input-xlarge required phone" cssStyle="width: 42%;height: 30px;font-size: 16px;"/>
                        </span>
							</p></td>
					</tr>
					<tr >
						<td class="tit">
							<font color="red">*</font>纠纷概要:
						</td>
						<td colspan="7" id="summaryOfDisputes">
								${complaintInfo.summaryOfDisputes}
						</td>
					</tr>
					<tr>
						<td class="tit">
							<font color="red">*</font>纠纷焦点:
						</td>
						<td  colspan="7" ><%--id="focus"--%>
							<form:textarea path="reportRegistration.focus" htmlEscape="false" class="input-xlarge required" style="margin: 0px;width: 99%;font-size: 16px;" rows="5" />
								<%--${complaintInfo.summaryOfDisputes}--%>
						</td>
					</tr>
					<tr>
						<td class="tit">
							<font color="red">*</font>患方要求:
						</td>
						<td id="patientAsk" colspan="7">

							${complaintInfo.reportRegistration.patientAsk}
						</td>
					</tr>
					<tr>
						<td class="tit"><font color="red">*</font>填表人签名:</td>
						<td colspan="3">
								<%--<sys:treeselect id="registrationEmp" name="registrationEmp"--%>
								<%--value="${empty reportRegistration.registrationEmp ? fns:getUser().id : reportRegistration.registrationEmp}" labelName=""--%>
								<%--labelValue="${empty reportRegistration.djEmployee.name ?  fns:getUser().name : reportRegistration.djEmployee.name}"--%>
								<%--title="用户" url="/sys/office/treeData?type=3&officeType=1" dataMsgRequired="必填信息"--%>
								<%--cssClass="required" allowClear="true" isAll="true" notAllowSelectParent="true" />--%>
							<sys:treeselect id="registrationEmp" name="reportRegistration.registrationEmp"
											value="${empty complaintInfo.reportRegistration.registrationEmp ? fns:getUser().id : complaintInfo.reportRegistration.registrationEmp}" labelName="emp"
											labelValue="${empty complaintInfo.reportRegistration.djEmployee.name ?  fns:getUser().name : complaintInfo.reportRegistration.djEmployee.name}"
											title="用户" url="/sys/office/treeData?type=3&officeType=1" dataMsgRequired="必填信息"
											cssClass="required" allowClear="true" isAll="true" notAllowSelectParent="true" />
						</td>
						<td class="tit"><font color="red">*</font>填表日期:</td>
						<td colspan="3">
							<input id="registrationTime" name="reportRegistration.registrationTime" type="text" readonly="readonly" maxlength="20"
								   class="input-medium Wdate required"
									value="${complaintInfo.reportRegistration.registrationTime}"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width:40%;height:30px;text-align: center;"/>
						</td>
						<>
					</tr>
					<tr>
						<td class="tit"><font color="red">*</font>报案号:</td>
						<td colspan="3" id="caseNumber">
							${complaintInfo.caseNumber}
								<%--<form:input path="complaintMain.caseNumber" htmlEscape="false" maxlength="20" class="input-xlarge required" cssStyle="width: 90%;height: 30px;border:hidden; text-align: center;" readonly="true"/>--%>
						</td>
						<td class="tit">审核人:</td>
						<td>
								<form:input path="reportRegistration.nextLink" htmlEscape="false" maxlength="32" class="input-xlarge " cssStyle="width: 90%;height: 30px; text-align: center;"/>
						</td>
						<td class="tit">审核日期:</td>
						<td>
							<input name="reportRegistration.patientRelation" type="text" readonly="readonly" maxlength="20"
								   class="input-medium Wdate "
								   value="${complaintInfo.reportRegistration.patientRelation}"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width:90%;height:30px;text-align: center;"/>
						</td>
					</tr>
					<tr>
						<td class="tit">备注:</td>
						<td colspan="7">
							<p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
									style="font-family:'Times New Roman'; font-size:12pt">1.</span><span
									style="font-family:宋体; font-size:12pt">表格中的案件编号由负责接案管理员填写；</span></p>
							<p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
									style="font-family:'Times New Roman'; font-size:12pt">2.</span><span
									style="font-family:宋体; font-size:12pt">报案号、审核日期由负责向经纪</span><span
									style="font-family:'Times New Roman'; font-size:12pt">/</span><span
									style="font-family:宋体; font-size:12pt">保险公司报案的工作人员填写；</span></p>
							<p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
									style="font-family:'Times New Roman'; font-size:12pt">3.</span><span
									style="font-family:宋体; font-size:12pt">纠纷发生时间是指患者首次向医疗机构提出索赔的时间；</span></p>
							<p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
									style="font-family:'Times New Roman'; font-size:12pt">4.</span><span
									style="font-family:宋体; font-size:12pt">出险时间系患者发生人身损害的时间；</span></p>
							<p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
									style="font-family:'Times New Roman'; font-size:12pt">5.</span><span
									style="font-family:宋体; font-size:12pt">报案人系填表人；</span></p>
							<p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
									style="font-family:'Times New Roman'; font-size:12pt">6.</span><span
									style="font-family:宋体; font-size:12pt">保内、保外案件均使用此表填写，未参保的在保单号内填写“未参保”；</span></p>
							<p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
									style="font-family:'Times New Roman'; font-size:12pt">7.</span><span
									style="font-family:宋体; font-size:12pt">审核人系负责向经纪</span><span
									style="font-family:'Times New Roman'; font-size:12pt">/</span><span
									style="font-family:宋体; font-size:12pt">保险公司报案的工作人员。</span></p>
						</td>
					</tr>
				</table>
			</div >
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
	<table class="table-form" id="zhu">
		<tr >
			<td class="tit" width="160px"><font color="red">*</font>案件编号：</td>
			<td width="476px">
				<form:input path="caseNumber" onchange="relation(this.value,'caseNumber')" htmlEscape="false" maxlength="20" readonly="true" class="input-xlarge required"/>
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
				<form:textarea path="summaryOfDisputes" htmlEscape="false" class="input-xlarge required" onchange="relation(this.value,'summaryOfDisputes');relation(this.value,'focus');" style="margin: 0px; width: 938px; height: 125px;"/>
			</td>
		</tr>
		<tr>
			<td class="tit"><font color="red">*</font>诉求：</td>
			<td colspan="3">
				<form:textarea path="appeal" htmlEscape="false" class="input-xlarge required" onchange="relation(this.value,'patientAsk')" style="margin: 0px; width: 939px; height: 24px;"/>
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
			<%--<td class="tit"><font color="red">*</font>下一环节处理人：</td>
			<td >
				&lt;%&ndash;<form:input path="nextLinkMan" htmlEscape="false" maxlength="32" class="input-xlarge "/>&ndash;%&gt;
				<sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${complaintInfo.nextLinkMan}" labelName="link.name" labelValue="${complaintInfo.link.name}"
								title="用户" url="/sys/office/treeData?type=3&officeType=1${fn:contains(fns:getUser().office.name, '工作站') ? '': '&isAll=true' }${fns:getUser().company.officeType=='2' ? '&next=yy' : ''}" role="${fns:getUser().company.officeType=='1' ?'distribution':'' }"  cssClass="required" dataMsgRequired="请选择下一环节处理人" allowClear="true" notAllowSelectParent="true"/>
			</td>--%>
		</tr>
	</table>
		<div class="form-actions">
			<shiro:hasPermission name="complaint:complaintInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#flag').val('no');$('#export').val('no')"/>&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="complaint:complaintInfo:edit"><input id="btnSubmit1" class="btn btn-primary" type="submit" value="下一步" onclick="$('#flag').val('yes');$('#export').val('no')"/>&nbsp;</shiro:hasPermission>
			<input id="reportExport" class="btn btn-primary" type="submit" value="导 出" onclick="$('#export').val('reportDis')" data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在导出数据之前请先保存数据。</h4>" />
			<input id="reportPrint" class="btn btn-primary" type="button" value="打 印" onclick="$('#export').val('reportDis');exportWord();" data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在打印数据之前请先保存数据。</h4>" />
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
    <c:if test="${not empty complaintInfo.complaintMain.procInsId}">
        <act:histoicFlow procInsId="${complaintInfo.complaintMain.procInsId}"/>
    </c:if>
</body>
</html>