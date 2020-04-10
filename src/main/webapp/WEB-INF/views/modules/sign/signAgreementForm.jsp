<%@ taglib prefix="fprm" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>签署协议管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			/**
			 * 字符串替换
			 * @param  {string} str    要被替换的字符串
			 * @param  {string} substr 要替换的字符串
			 * @param  {string} newstr 用于替换的字符串
			 * @return {string}        替换后的新字符串
			 */
			function replace(str, substr, newstr) {
				substr = substr.replace(/[.\\[\]{}()|^$?*+]/g, "\\$&"); // 转义字符串中的元字符
				var re = new RegExp(substr, "g"); // 生成正则
				return str.replace(re, newstr);
			}
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					var aa=$("#export").val();
					if(aa=='no'){
						loading('正在提交，请稍等...');
					}
					//协议约定事项 是个 listmap  格式 ；
					var list=[];
					$("input[type=checkbox]").each(function(){
						if($(this).attr("checked")){
							if($(this).attr("name").indexOf("meatterList") != -1){//协议约定事项的
								var map={};
								var agreedMatter1 = $('textarea[name="'+replace($(this).attr("name"),"label","agreedMatter")+'"]').val();
								var agreedMatterTd = $('td[name="'+replace($(this).attr("name"),"label","td")+'"]').text();
								map['name'] =agreedMatterTd;
								map['value'] =agreedMatter1;
								list.push(map);
							}else if($(this).attr("name").indexOf("mediationList") != -1){
								var mediation = $('textarea[name="'+replace($(this).attr("name"),"label","mediation")+'"]').val();
								$("#mediation").val(mediation);
							}else if($(this).attr("name").indexOf("performList") != -1){
								 var performAgreementMode = $('textarea[name="'+replace($(this).attr("name"),"label","performAgreementMode")+'"]').val();
								 $("#performAgreementMode").val(performAgreementMode);
							}else if($(this).attr("name").indexOf("agreementList") != -1){
								var agreementExplain = $('textarea[name="'+replace($(this).attr("name"),"label","agreementExplain")+'"]').val();
								$("#agreementExplain").val(agreementExplain);
							}
						}
					});
					$("#agreedMatter").val(JSON.stringify(list));
					$("input[type=checkbox]").each(function(){
						$(this).after("<input type=\"hidden\" name=\""+$(this).attr("name")+"\" value=\""
								+($(this).attr("checked")?"1":"0")+"\"/>");
						$(this).attr("name", "_"+$(this).attr("name"));
					});
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

		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});

		}
		function delRow(obj, prefix,key){
			var id = $(prefix+key);
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}

		}
		function clearCheckBox(na,table) {
			$("#"+table+" tr td input:checkbox").each(function() {
				this.checked = false;
			});
			$("input[name='"+na+"']").prop("checked",true);
		}

		function replace(str, substr, newstr) {
			substr = substr.replace(/[.\\[\]{}()|^$?*+]/g, "\\$&"); // 转义字符串中的元字符
			var re = new RegExp(substr, "g"); // 生成正则
			return str.replace(re, newstr);
		}

		function exportWord() {
			//协议约定事项 是个 listmap  格式 ；
			var list=[];
			$("input[type=checkbox]").each(function(){
				if($(this).attr("checked")){
					if($(this).attr("name").indexOf("meatterList") != -1){//协议约定事项的
						var map={};
						var agreedMatter1 = $('textarea[name="'+replace($(this).attr("name"),"label","agreedMatter")+'"]').val();
						var agreedMatterTd = $('td[name="'+replace($(this).attr("name"),"label","td")+'"]').text();
						map['name'] =agreedMatterTd;
						map['value'] =agreedMatter1;
						list.push(map);
					}else if($(this).attr("name").indexOf("mediationList") != -1){
						var mediation1 = $('textarea[name="'+replace($(this).attr("name"),"label","mediation")+'"]').val();
						$("#mediation").val(mediation1);
					}else if($(this).attr("name").indexOf("performList") != -1){
						var performAgreementMode1 = $('textarea[name="'+replace($(this).attr("name"),"label","performAgreementMode")+'"]').val();
						$("#performAgreementMode").val(performAgreementMode1);
					}else if($(this).attr("name").indexOf("agreementList") != -1){
						var agreementExplain1 = $('textarea[name="'+replace($(this).attr("name"),"label","agreementExplain")+'"]').val();
						$("#agreementExplain").val(agreementExplain1);
					}
				}
			});
			$("#agreedMatter").val(JSON.stringify(list));
			var aa=$("#export").val();
			var path="${ctx}/sign/signAgreement/pass";
			var data = {};
			var t = $('form').serializeArray();
			$.each(t, function() {
				if(this.name=='createDate'){
					var aa=new Date(this.value).format("yyyy-MM-dd hh:mm:ss");
					data [this.name] ='';
				}else if(this.name=='createBy'){
					data [this.name] = '';
				}else{
					data [this.name] = this.value;
				}
			});
			$.post(path,{'signAgreementId':"${signAgreement.signAgreementId}",'data':JSON.stringify(data),'export':aa,"print":"true",'agreedMatter':$("#agreedMatter").val(),'mediation':$("#mediation").val(),'performAgreementMode':$("#performAgreementMode").val(),'agreementExplain':$("#agreementExplain").val()},function(res){
				if(res.data.url!=''){
					var url='${pageContext.request.contextPath}'+res.data.url;
					<%--window.location.href='${pageContext.request.contextPath}'+res.data.url ;--%>
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

		Date.prototype.format = function (fmt) { //author: meizz
			var o = {
				"M+": this.getMonth() + 1, //月份
				"d+": this.getDate(), //日
				"h+": this.getHours(), //小时
				"m+": this.getMinutes(), //分
				"s+": this.getSeconds(), //秒
				"q+": Math.floor((this.getMonth() + 3) / 3), //季度
				"S": this.getMilliseconds() //毫秒
			};
			if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
			for (var k in o)
				if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
			return fmt;
		}
		function removeCssClass() {
			$('#summaryOfDisputes').removeClass('required');
			$('#ratifyAccord').removeClass('required');
			$('#agreementAmount').removeClass('required');
			$('#insuranceAmount').removeClass('required');
			$('#nextLinkManName').removeClass('required');
		}
		function addCssClass() {
			$('#summaryOfDisputes').addClass('required');
			$('#ratifyAccord').addClass('required');
			$('#agreementAmount').addClass('required');
			$('#insuranceAmount').addClass('required');
			$('#nextLinkManName').addClass('required');
		}


		function assignment(t) {
			var id=$(t).attr('name').split('.')[0]+'.content';
			$('input[name="'+replace(id,"","")+'"]').val($(t).val());
		}
	</script>
</head>
<body>

<ul class="nav nav-tabs">
	<li><a href="${ctx}/sign/signAgreement/">签署协议列表</a></li>
	<li class="active"><a href="${ctx}/sign/signAgreement/form?id=${signAgreement.id}">签署协议<shiro:hasPermission name="sign:signAgreement:edit">${not empty signAgreement.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sign:signAgreement:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="signAgreement" action="${ctx}/sign/signAgreement/save" method="post" class="form-horizontal">
	<form:hidden path="signAgreementId"/>
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
	<form:hidden path="recordInfo.recordId"/>
	<form:hidden path="mediateProgram.mediateProgramId"/>
	<form:hidden path="recordInfo.moduleType"/>
	<form:hidden path="recordInfo.cause"/>
	<%--<form:hidden path="agreementNumber"/>--%>
	<%--<form:hidden path="mediateProgram.patient"/>--%>
	<form:hidden path="mediateProgram.doctor"/>
	<form:hidden path="agreedMatter"/>
	<form:hidden path="mediation"/>
	<form:hidden path="performAgreementMode"/>
	<form:hidden path="agreementExplain"/>
	<input type="hidden"  id="export" name="export"/>
	<sys:message content="${message}"/>
<fieldset>

	<ul id="myTab" class="nav nav-tabs">
		<li class="active">
			<a href="#sign" data-toggle="tab">调解协议书</a>
		</li>
		<li>
			<a href="#meeting" data-toggle="tab">调解程序表</a>
		</li>
		<li>
			<a href="#recorded_patient" data-toggle="tab">签署协议会议记录</a>
		</li>
		<li>
			<a href="#annex" data-toggle="tab">附件</a>
		</li>
	</ul>
	<div id="myTabContent" class="tab-content" >
		<div class="tab-pane fade in active" id="sign">
			<div id="myTab1Content" class=""  >
				<legend style="color: black;">协议号</legend>
				<form:input path="agreementNumber" htmlEscape="false" maxlength="20" class="input-xlarge required agreementId" placeHolder="下方生成协议号" readonly="true"/>
				<%--<p style="font-size: 20px;color: black;">${signAgreement.agreementNumber}</p>--%>
				<table id="patientTable" class="table  table-bordered table-condensed">
					<legend style="color: black;">甲方（患方）</legend>
					<thead>
					<tr>
						<th class="hide"></th>
						<th >姓名</th>
						<th >性别</th>
						<th >与患者关系</th>
						<th >身份证号</th>
						<th >住址</th>
						<th >操作</th>
						<%--<shiro:hasPermission name="sign:signAgreement:edit">--%>
							<%--<th >&nbsp;</th>--%>
						<%--</shiro:hasPermission>--%>
					</tr>
					</thead>
					<tbody id="patientLinkEmpList"></tbody>
					<shiro:hasPermission name="sign:signAgreement:edit">
						<tfoot>
						<tr><td colspan="7"><a href="javascript:" onclick="addRow('#patientLinkEmpList', patientLinkEmpRowIdx, patientLinkEmpTp);patientLinkEmpRowIdx = patientLinkEmpRowIdx + 1;" class="btn" id="huan">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
				</table>

				<script type="text/template" id="patientLinkEmpTp">//<!--
						<tr id="patientLinkEmpList{{idx}}">
							<td class="hide">
								<input id="patientLinkEmpList{{idx}}_id" name="patientLinkEmpList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="patientLinkEmpList{{idx}}_patientLinkEmpId" name="patientLinkEmpList[{{idx}}].patientLinkEmpId" type="hidden" value="{{row.patientLinkEmpId}}"/>
								<input id="patientLinkEmpList{{idx}}_linkType" name="patientLinkEmpList[{{idx}}].linkType" type="hidden" value="{{row.linkType}}"/>
								<input id="patientLinkEmpList{{idx}}_relationId" name="patientLinkEmpList[{{idx}}].relationId" type="hidden" value="{{row.relationId}}"/>
								<input id="patientLinkEmpList{{idx}}_delFlag" name="patientLinkEmpList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>

							<td>
								<input id="patientLinkEmpList{{idx}}_patientLinkName" name="patientLinkEmpList[{{idx}}].patientLinkName" type="text" value="{{row.patientLinkName}}" maxlength="100" class="required" />
							</td>
							<td>
								<select id="patientLinkEmpList{{idx}}_patientLinkSex" name="patientLinkEmpList[{{idx}}].patientLinkSex" value="{{row.patientLinkSex}}" data-value="{{row.patientLinkSex}}" class="input-mini">
									<option value="1"  >男</option>
									<option value="2"  >女</option>
								</select>
							</td>
							<td>
								<%--<input id="patientLinkEmpList{{idx}}_patientRelation" name="patientLinkEmpList[{{idx}}].patientRelation" type="text" value="{{row.patientRelation}}" maxlength="100" class="required" />--%>
								<select id="patientLinkEmpList{{idx}}_patientRelation" name="patientLinkEmpList[{{idx}}].patientRelation" value="{{row.patientRelation}}" data-value="{{row.patientRelation}}" class="input-mini">
									<option value=""></option>
									<option value="1"  >本人</option>
									<option value="2"  >夫妻</option>
									<option value="3"  >子女</option>
									<option value="4"  >父母</option>
									<option value="5"  >兄妹</option>
									<option value="6"  >亲属</option>
									<option value="7"  >其他</option>
								</select>
							</td>
							<td>
								<input id="patientLinkEmpList{{idx}}_idNumber" name="patientLinkEmpList[{{idx}}].idNumber" type="text" value="{{row.idNumber}}" maxlength="20" class="required" />
							</td>
							<td>
								<input id="patientLinkEmpList{{idx}}_patientLinkAddress" name="patientLinkEmpList[{{idx}}].patientLinkAddress" type="text" value="{{row.patientLinkAddress}}" maxlength="20" class="required" />
							</td>

							<shiro:hasPermission name="sign:signAgreement:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#patientLinkEmpList{{idx}}','_patientLinkEmpId')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
				</script>


				<table id="patientDTable" class="table table-bordered table-condensed">
					<legend style="color: black;">委托（法定）代理人</legend>
					<thead>
					<tr>
						<th class="hide"></th>
						<th >姓名</th>
						<th >性别</th>
						<th >与患者关系</th>
						<th >身份证号</th>
						<th >住址</th>
						<th >操作</th>
						<%--<shiro:hasPermission name="sign:signAgreement:edit">--%>
							<%--<th >&nbsp;</th>--%>
						<%--</shiro:hasPermission>--%>
					</tr>
					</thead>
					<tbody id="patientLinkDList"></tbody>
					<shiro:hasPermission name="sign:signAgreement:edit">
						<tfoot>
						<tr><td colspan="7"><a href="javascript:" onclick="addRow('#patientLinkDList', patientLinkDRowIdx, patientLinkDTp);patientLinkDRowIdx = patientLinkDRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
				</table>
				<script type="text/template" id="patientLinkDTp">//<!--
						<tr id="patientLinkDList{{idx}}">
							<td class="hide">
								<input id="patientLinkDList{{idx}}_id" name="patientLinkDList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="patientLinkDList{{idx}}_patientLinkEmpId" name="patientLinkDList[{{idx}}].patientLinkEmpId" type="hidden" value="{{row.patientLinkEmpId}}"/>
								<input id="patientLinkDList{{idx}}_linkType" name="patientLinkDList[{{idx}}].linkType" type="hidden" value="{{row.linkType}}"/>
								<input id="patientLinkDList{{idx}}_relationId" name="patientLinkDList[{{idx}}].relationId" type="hidden" value="{{row.relationId}}"/>
								<input id="patientLinkDList{{idx}}_delFlag" name="patientLinkDList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>

							<td>
								<input id="patientLinkDList{{idx}}_patientLinkName" name="patientLinkDList[{{idx}}].patientLinkName" type="text" value="{{row.patientLinkName}}" maxlength="100" class="required" />
							</td>
							<td>
								<select id="patientLinkDList{{idx}}_patientLinkSex" name="patientLinkDList[{{idx}}].patientLinkSex" value="{{row.patientLinkSex}}" data-value="{{row.patientLinkSex}}" class="input-mini">
									<option value="1"  >男</option>
									<option value="2"  >女</option>
								</select>
							</td>
							<td>
								<%--<input id="patientLinkDList{{idx}}_patientRelation" name="patientLinkDList[{{idx}}].patientRelation" type="text" value="{{row.patientRelation}}" maxlength="100" class="required" />--%>
								<select id="patientLinkDList{{idx}}_patientRelation" name="patientLinkDList[{{idx}}].patientRelation" value="{{row.patientRelation}}" data-value="{{row.patientRelation}}" class="input-mini">

									<option value="1"  >本人</option>
									<option value="2"  >夫妻</option>
									<option value="3"  >子女</option>
									<option value="4"  >父母</option>
									<option value="5"  >兄妹</option>
									<option value="6"  >亲属</option>
									<option value="7"  >其他</option>
								</select>
							</td>
							<td>
								<input id="patientLinkDList{{idx}}_idNumber" name="patientLinkDList[{{idx}}].idNumber" type="text" value="{{row.idNumber}}" maxlength="20" class="required" />
							</td>
							<td>
								<input id="patientLinkDList{{idx}}_patientLinkAddress" name="patientLinkDList[{{idx}}].patientLinkAddress" type="text" value="{{row.patientLinkAddress}}" maxlength="20" class="required" />
							</td>

							<shiro:hasPermission name="sign:signAgreement:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#patientLinkDList{{idx}}','_patientLinkEmpId')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
				</script>

				<table id="hospitalTable" class="table table-bordered table-condensed">
					<legend style="color: black;">乙方（医方）</legend>
					<thead>
					<tr>
						<th class="hide"></th>
						<th style="width: 12%">医疗机构名称</th>
						<th >地址</th>
						<th >法定代表人</th>
						<th >职务</th>
						<th >委托代理人</th>
						<th >性别</th>
						<th >身份证号</th>
						<th >单位及职务</th>
						<th >操作</th>
						<%--<shiro:hasPermission name="sign:signAgreement:edit">--%>
							<%--<th >&nbsp;</th>--%>
						<%--</shiro:hasPermission>--%>
					</tr>
					</thead>
					<tbody id="medicalOfficeEmpList"></tbody>
					<shiro:hasPermission name="sign:signAgreement:edit">
						<tfoot>
						<tr><td colspan="7"><a href="javascript:" onclick="addRow('#medicalOfficeEmpList', medicalOfficeEmpRowIdx, medicalOfficeEmpTp);medicalOfficeEmpRowIdx = medicalOfficeEmpRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
				</table>
				<script type="text/template" id="medicalOfficeEmpTp">//<!--
						<tr id="medicalOfficeEmpList{{idx}}">
							<td class="hide">
								<input id="medicalOfficeEmpList{{idx}}_id" name="medicalOfficeEmpList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="medicalOfficeEmpList{{idx}}_medicalOfficeEmpId" name="medicalOfficeEmpList[{{idx}}].medicalOfficeEmpId" type="hidden" value="{{row.medicalOfficeEmpId}}"/>
								<input id="medicalOfficeEmpList{{idx}}_relationId" name="medicalOfficeEmpList[{{idx}}].relationId" type="hidden" value="{{row.relationId}}"/>
								<input id="medicalOfficeEmpList{{idx}}_delFlag" name="medicalOfficeEmpList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>

							<td>
								<input id="medicalOfficeEmpList{{idx}}_medicalOfficeName" name="medicalOfficeEmpList[{{idx}}].medicalOfficeName" type="text" value="{{row.medicalOfficeName}}" maxlength="100" class="required " />
							</td>
							<td>
								<input id="medicalOfficeEmpList{{idx}}_medicalOfficeAddress" name="medicalOfficeEmpList[{{idx}}].medicalOfficeAddress" type="text" value="{{row.medicalOfficeAddress}}" maxlength="100" class="required " />
							</td>
							<td>
								<input id="medicalOfficeEmpList{{idx}}_legalRepresentative" name="medicalOfficeEmpList[{{idx}}].legalRepresentative" type="text" value="{{row.legalRepresentative}}" maxlength="20" class="required input-mini" />
							</td>
							<td>
								<input id="medicalOfficeEmpList{{idx}}_medicalOfficePost" name="medicalOfficeEmpList[{{idx}}].medicalOfficePost" type="text" value="{{row.medicalOfficePost}}" maxlength="20" class="required input-mini" />
							</td>
							<td>
								<input id="medicalOfficeEmpList{{idx}}_medicalOfficeAgent" name="medicalOfficeEmpList[{{idx}}].medicalOfficeAgent" type="text" value="{{row.medicalOfficeAgent}}" maxlength="32" class="required input-mini" />
							</td>
							<td>
								<select id="medicalOfficeEmpList{{idx}}_medicalOfficeSex" name="medicalOfficeEmpList[{{idx}}].medicalOfficeSex" value="{{row.medicalOfficeSex}}" data-value="{{row.medicalOfficeSex}}" class="input-mini">
									<option value="1"  >男</option>
									<option value="2"  >女</option>
								</select>
							</td>
							<td>
								<input id="medicalOfficeEmpList{{idx}}_medicalOfficeIdcard" name="medicalOfficeEmpList[{{idx}}].medicalOfficeIdcard" type="text" value="{{row.medicalOfficeIdcard}}" maxlength="20" class="required " />
							</td>
							<td>
								<input id="medicalOfficeEmpList{{idx}}_medicalOfficeCompany" name="medicalOfficeEmpList[{{idx}}].medicalOfficeCompany" type="text" value="{{row.medicalOfficeCompany}}" maxlength="200" class="required " />
							</td>
							<shiro:hasPermission name="sign:signAgreement:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#medicalOfficeEmpList{{idx}}','_medicalOfficeEmpId')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
				</script>
				<table  class="table-form">
					<legend style="color: black;">纠纷概要</legend>
					<tr>
						<td >
							<form:textarea path="summaryOfDisputes" htmlEscape="false" class="input-xlarge required" cssStyle="width: 1500px;" rows="15"/>
						</td>
					</tr>
				</table>
				<table id="tjqk" class="table table-striped table-bordered table-condensed">
					<legend style="color: black;">调解情况</legend>
					<thead>
					<tr>
						<th></th>
						<th style="text-align:center;">类型</th>
						<th style="text-align:center;">内容</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${tjqk}" var="column" varStatus="vs">
						<tr><%--${column.delFlag eq '1'?' class="error" title="已删除的列，保存之后消失！"':''}--%>
							<td nowrap style="text-align:center;vertical-align:middle;width: 5%;">
								<input type="hidden" name="mediationList[${vs.index}].typeId" value="${column.typeId}"/>
								<input type="hidden" name="mediationList[${vs.index}].delFlag" value="${column.delFlag}"/>
								<input type="hidden" name="mediationList[${vs.index}].content" value="${column.content}"/>
								<input type="hidden" name="mediationList[${vs.index}].source" value="${empty column.source ? '' : column.source}"/>
								<input type="hidden" name="mediationList[${vs.index}].relationModel" value="${empty column.createDate ? '' : column.createDate}"/>
								<input type="checkbox" name="mediationList[${vs.index}].label" value="1" ${column.label eq '1' ? 'checked' : ''} onclick="clearCheckBox(this.name,'tjqk');"/>
							</td>
							<td style="text-align:center;width: 10%;">
								<input type="hidden" name="mediationList[${vs.index}].typeName" value="${column.typeName}"/>
									${column.typeName}
							</td>
							<td style="text-align:center;">
										<textarea  name="mediationList[${vs.index}].mediation" style="width: 90%;" onchange='assignment(this)'  rows="5">${column.content}</textarea>

							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
					<%--<p style="margin:0pt">--%>
					<%--<span style="color:#333333; font-family:宋体; font-size:14pt; font-weight:normal">年 月 日，经×××司法鉴定中心（×××医学会医疗事故鉴定），×××[2017]×××号鉴定结论：××××××××××××。</span>--%>
					<%--</p>--%>
				<table id="xyydsx" class="table table-striped table-bordered table-condensed">
					<legend style="color: black;">协议约定事项</legend>
					<thead>
					<tr>
						<th></th>
						<th style="text-align:center;">类型</th>
						<th style="text-align:center;width: 80%;">内容</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${xyydsx}" var="column" varStatus="vs">
						<tr><%--${column.delFlag eq '1'?' class="error" title="已删除的列，保存之后消失！"':''}--%>
							<td nowrap style="text-align:center;vertical-align:middle;width: 5%;">
								<input type="hidden" name="meatterList[${vs.index}].typeId" value="${column.typeId}"/>
								<input type="hidden" name="meatterList[${vs.index}].delFlag" value="${column.delFlag}"/>
								<input type="hidden" name="meatterList[${vs.index}].content" value="${column.content}"/>
								<input type="hidden" name="meatterList[${vs.index}].source" value="${empty column.source ? '' : column.source}"/>
								<input type="hidden" name="meatterList[${vs.index}].relationModel" value="${empty column.createDate ? '' : column.createDate}"/>
								<input type="checkbox" name="meatterList[${vs.index}].label" value="1" ${column.label eq '1' ? 'checked' : ''} /><%--onclick="clearCheckBox(this.name,'xyydsx')"--%>
							</td>
							<td name="meatterList[${vs.index}].td" style="text-align:center;vertical-align:middle;">
								<input type="hidden" name="meatterList[${vs.index}].typeName" value="${column.typeName}"/>
									${column.typeName}
							</td>
							<td style="text-align:center;vertical-align:middle;">
								<%--<input type="text" name="agreedMatter" value="${column.content}" style="width: 90%;word-wrap:break-word;height:100px;"/>--%>
								<textarea  name="meatterList[${vs.index}].agreedMatter" style="width: 90%;" onchange='assignment(this)' rows="5">${column.content}</textarea>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>


				<table id="lxxyfs" class="table table-striped table-bordered table-condensed">
					<legend style="color: black;">履行协议方式</legend>
					<thead>
					<tr>
						<th></th>
						<th style="text-align:center;">类型</th>
						<th style="text-align:center;width: 80%;">内容</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${lxxyfs}" var="column" varStatus="vs">
						<tr><%--${column.delFlag eq '1'?' class="error" title="已删除的列，保存之后消失！"':''}--%>
							<td nowrap style="text-align:center;vertical-align:middle;">
								<input type="hidden" name="performList[${vs.index}].typeId" value="${column.typeId}"/>
								<input type="hidden" name="performList[${vs.index}].delFlag" value="${column.delFlag}"/>
								<input type="hidden" name="performList[${vs.index}].content" value="${column.content}"/>
								<input type="hidden" name="performList[${vs.index}].source" value="${empty column.source ? '' : column.source}"/>
								<input type="hidden" name="performList[${vs.index}].relationModel" value="${empty column.createDate ? '' : column.createDate}"/>
								<input type="checkbox" name="performList[${vs.index}].label" value="1" ${column.label eq '1' ? 'checked' : ''} onclick="clearCheckBox(this.name,'lxxyfs')"/>
							</td>
							<td style="text-align:center;vertical-align:middle;">
								<input type="hidden" name="performList[${vs.index}].typeName" value="${column.typeName}"/>
									${column.typeName}
							</td>
							<td style="text-align:center;vertical-align:middle;">
									<textarea  name="performList[${vs.index}].performAgreementMode" style="width: 90%;" onchange='assignment(this)' rows="5">${column.content}</textarea>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>



				<table id="xysm" class="table table-striped table-bordered table-condensed">
					<legend style="color: black;">协议说明</legend>
					<thead>
					<tr>
						<th></th>
						<th style="text-align:center;">类型</th>
						<th style="text-align:center;">内容</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${xysm}" var="column" varStatus="vs">
						<tr>
							<td nowrap style="text-align:center;vertical-align:middle;">
								<input type="hidden" name="agreementList[${vs.index}].typeId" value="${column.typeId}"/>
								<input type="hidden" name="agreementList[${vs.index}].delFlag" value="${column.delFlag}"/>
								<input type="hidden" name="agreementList[${vs.index}].content" value="${column.content}"/>
								<input type="hidden" name="agreementList[${vs.index}].source" value="${empty column.source ? '' : column.source}"/>
								<input type="hidden" name="agreementList[${vs.index}].relationModel" value="${empty column.createDate ? '' : column.createDate}"/>
								<input type="checkbox" name="agreementList[${vs.index}].label" value="1" ${column.label eq '1' ? 'checked' : ''} onclick="clearCheckBox(this.name,'xysm');"/>
							</td>
							<td style="text-align:center;vertical-align:middle;">
								<input type="hidden" name="agreementList[${vs.index}].typeName" value="${column.typeName}"/>
									${column.typeName}
							</td>
							<td style="text-align:center;vertical-align:middle;">
								<textarea  name="agreementList[${vs.index}].agreementExplain" onchange='assignment(this)' style="width: 90%;" >${column.content}</textarea>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>


			</div>
			<div style="text-align: center;">
			<input id="agreementExport" class="btn btn-primary" type="submit" value="导 出" onclick="$('#export').val('agreementExport');" data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在导出数据之前请先保存数据。</h4>"/>
			<input id="agreementPrint" class="btn btn-primary" type="button" value="打 印" onclick="$('#export').val('agreementExport');exportWord();"  data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在打印数据之前请先保存数据。</h4>"/>
			</div>
		</div>

		<div class="tab-pane fade" id="meeting">
			<table class="table-form">
				<tr>
					<td class="tit" width="15%">时间:</td>
					<td colspan="2" width="35%">
						<input name="mediateProgram.meetingTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							   value="${signAgreement.mediateProgram.meetingTime}"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width:250px;"/>
					</td>
					<td class="tit" width="15%">地点:</td>
					<td >
						<form:select path="mediateProgram.address" class="input-xlarge" cssStyle="text-align:center;">
							<form:options items="${fns:getDictList('meeting')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
						<%--<form:input id="meetingAddress" path="mediateProgram.address" htmlEscape="false" maxlength="20"
									class="input-xlarge " value="${signAgreement.mediateProgram.address}" cssStyle="width: 250px;"/>
					--%>
					</td>
				</tr>
				<tr>

				<td class="tit" rowspan="4">一、介绍医调委、患方、医方的身份</td>
					<td class="tit" width="10%">调解员:</td>
					<td style="width: 150px;">
						<sys:treeselect id="mediator" name="mediateProgram.mediator"
										value="${empty signAgreement.mediateProgram.mediator ? fns:getUser().id : signAgreement.mediateProgram.mediator}" labelName="tjy"
										labelValue="${empty signAgreement.mediateProgram.mediatorUser.name ? fns:getUser().name : signAgreement.mediateProgram.mediatorUser.name}"
										title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass=""
										dataMsgRequired="必填信息"
										allowClear="true" isAll="true" notAllowSelectParent="true" disabled="true"/>
					</td>
					<td class="tit">书记员:</td>
					<td>
						<sys:treeselect id="clerk" name="mediateProgram.clerk"
										value="${empty signAgreement.mediateProgram.clerk ? fns:getUser().id : signAgreement.mediateProgram.clerk}" labelName="sjy"
										labelValue="${empty signAgreement.mediateProgram.clerkuser.name ? fns:getUser().name : signAgreement.mediateProgram.clerkuser.name}"
										title="用户" url="/sys/office/treeData?type=3&officeType=1"
										cssClass="" dataMsgRequired="必填信息"
										allowClear="true" isAll="true" notAllowSelectParent="true" disabled="true"/>
					</td>

				<tr>
				</tr>
				<tr>
					<td class="tit">患方:</td>
					<td colspan="3">
						<form:input path="mediateProgram.patient" value="${empty mediateProgram.patient ? signAgreement.complaintMain.patientName :mediateProgram.patient}" htmlEscape="false" class="input-xlarge" maxlength="20"/>
							<%--${empty signAgreement.mediateProgram.patient?signAgreement.complaintMain.patientName:signAgreement.mediateProgram.patient}--%>
					</td>
				</tr>
				<tr>
					<td class="tit">医方:</td>
					<td colspan="3">
						<form:input path="complaintMain.hospital.name" htmlEscape="false" class="input-xlarge" maxlength="20"/>
							<%--${signAgreement.complaintMain.hospital.name}--%>
					</td>
				</tr>
				</tr>
				<tr>
					<td class="tit" rowspan="2">二、医患双方确认以上参会人员身份有无要求回避</td>
					<td class="tit">患方:</td>
					<td colspan="3">
						<form:input path="mediateProgram.patientAvoid" value="${empty signAgreement.mediateProgram.patientAvoid ? '无' : signAgreement.mediateProgram.patientAvoid}" htmlEscape="false" maxlength="255" class="input-xlarge " cssStyle="width: 50%;"/>
					</td>
				</tr>
				<tr>
					<td class="tit">医方:</td>
					<td colspan="3">
						<form:input path="mediateProgram.doctorAvoid" value="${empty signAgreement.mediateProgram.doctorAvoid ? '无' : signAgreement.mediateProgram.doctorAvoid}" htmlEscape="false" maxlength="255" class="input-xlarge " cssStyle="width: 50%;"/>
					</td>
				</tr>
				<tr>
					<td class="tit">三、宣读有关纪律及注意事项</td>
					<td colspan="4">
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">1</span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、双方当事人及工作人员酒后不的参会，会议中不得吸烟、不得中途退场、不得当众喧哗。保持会场安静，遵守会场秩序。</span>

						</p>
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">2</span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、参会人应当将通讯工具关闭或调至静音状态（请大家配合一下）。会议期间不得录音、录像。</span>
						</p>
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">3</span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、一方陈述时，对方及其他参会人员不的发言，需要补充时，需在当事人（代理人）结束发言后，经主持人同意方可进行补充。发言时不得使用人身攻击言语及过激的言语。</span>
						</p>
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">4</span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、提供的证据应当真实、合法、有效，不得伪造、毁灭证据，妨碍调解人员正确作出调解。</span>
						</p>
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">5</span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、不得以暴力，威胁或者其他方法阻碍调解人员执行职务。</span>
						</p>
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">6</span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、对于有不良行为的参加人，山西省医疗纠纷人民调解委员会将责令其退出会议室。</span>
						</p>
					</td>
				</tr>
				<tr>
					<td class="tit">四、宣布纠纷当事人在人民调解活动中享有的权利</td>
					<td colspan="4">
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">（一）</span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal"> 选择或者接受人民调解员；</span>

						</p>
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">（二）</span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal"> 接受调解、拒绝调解或者要求终止调解；</span>
						</p>
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">（三）</span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal"> 要求调解公开进行或者不公开进行；</span>
						</p>
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">（四）</span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal"> 自主表达意愿、自愿达成调解协议。</span>
						</p>
					</td>
				</tr>
				<tr>
					<td class="tit">五、宣布纠纷当事人在人民调解活动中履行下列义务</td>
					<td colspan="4">
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">（一）</span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal"> 如实陈述纠纷事实；</span>

						</p>
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">（二）</span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal"> 遵守调解现场秩序，尊重人民调解员；</span>
						</p>
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">（三）</span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal"> 尊重对方当事人行使权力；</span>
						</p>
					</td>
				</tr>
				<tr>
					<td class="tit" rowspan="2">六、以上宣读内容听清楚了吗？有无异议？</td>
					<td class="tit">患方:</td>
					<td colspan="3">
						<form:input path="mediateProgram.patientClear" value="${empty signAgreement.mediateProgram.patientClear ? '清除,无异议' : signAgreement.mediateProgram.patientClear}" htmlEscape="false" maxlength="255" class="input-xlarge " cssStyle="width:50%;"/>
					</td>

				</tr>
				<tr>
					<td class="tit">医方:</td>
					<td colspan="3">
						<form:input path="mediateProgram.doctorClear" value="${empty signAgreement.mediateProgram.doctorClear ? '清除,无异议' : signAgreement.mediateProgram.doctorClear}" htmlEscape="false" maxlength="255" class="input-xlarge " cssStyle="width:50%;"/>
					</td>
				</tr>
				<tr>
					<td class="tit">七、开始调解</td>
					<td colspan="4">
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">1</span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、患方及其代理人陈述主要事实、医方过错及诉求，提交相关证据；</span>

						</p>
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">2</span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、医方及其代理人陈述，针对患方提出问题进行答辩，提交相关证据；</span>
						</p>
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">3</span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、调解员总结双方争议要点；</span>
						</p>
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">4</span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、医患双方就争议要点进行辩论；</span>
						</p>
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">5</span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、调解员调解；</span>
						</p>
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">6</span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、医患各方最后陈述；</span>
						</p>
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">7</span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、调解员总结；</span>
						</p>
					</td>
				</tr>
				<tr>
					<td class="tit">八、宣布调解结束</td>
					<td colspan="4">
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">患方署名：</span>
						</p>
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">医方署名：</span>
						</p>
						<p style="margin:0pt">
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
							<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
							<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">调解员署名：</span>
						</p>
					</td>
				</tr>

				<td colspan="4" style="text-align: center;">
					<input id="btnGenerate" class="btn btn-primary" type="submit" value="生成会议表" value="导 出"
						   onclick="$('#export').val('meeting')"  data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在生成会议表之前请先保存数据。</h4>"/>
					<input id="btnGeneratePrint" class="btn btn-primary" type="button" value="打 印" onclick="$('#export').val('meeting');exportWord();"  data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在打印数据之前请先保存数据。</h4>"/>

				</td>
			</table>
		</div>

		<div class="tab-pane fade" id="recorded_patient">
			<table class="table-form">

				<tr>
					<td class="tit"width="200px;">时间</td>
					<td width="10px;" style="border-right: hidden;">
						<input name="recordInfo.startTime" type="text" readonly="readonly" maxlength="20"
							   class="input-medium Wdate "
							   value="${signAgreement.recordInfo.startTime}"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width: 200px;"/>
					</td>
					<td style="text-align: center" colspan="2">至</td>
					<td style="border-bottom: hidden;border-left: hidden;">
						<input name="recordInfo.endTime" type="text" readonly="readonly" maxlength="20"
							   class="input-medium Wdate "
							   value="${signAgreement.recordInfo.endTime}"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" />
					</td>
					<td style="border-left: hidden;"></td>
				</tr>
				<tr>
					<td class="tit">地点</td>
					<td colspan="4">
						<form:input path="recordInfo.recordAddress" htmlEscape="false" maxlength="20"
									class="input-xlarge " cssStyle="width: 450px;"/>
					</td>
					<td style="border-left: hidden;"></td>
				</tr>
				<tr>
					<td class="tit">主持人</td>
					<td colspan="4">
							<sys:treeselect id="host" name="recordInfo.host"
											value="${signAgreement.recordInfo.host}" labelName="zcr"
											labelValue="${signAgreement.recordInfo.ytwHost.name}"
											title="用户" url="/sys/office/treeData?type=3&officeType=1"
											allowClear="true" isAll="true" notAllowSelectParent="true" dataMsgRequired="必填信息" cssClass="" />
					<td style="border-left: hidden;"></td>
				</tr>
				<tr>
					</td>

					<td class="tit" width="10px;">记录人</td>
					<td colspan="4">
						<sys:treeselect id="noteTaker" name="recordInfo.noteTaker"
										value="${signAgreement.recordInfo.noteTaker}" labelName="jlr"
										labelValue="${signAgreement.recordInfo.ytwNoteTaker.name}"
										title="用户" url="/sys/office/treeData?type=3&officeType=1"
										allowClear="true" notAllowSelectParent="true" isAll="true" dataMsgRequired="必填信息" cssClass=""/>
					</td>
					<td style="border-left: hidden;"></td>
				</tr>
				<tr>
					<td class="tit">患方参加人员</td>
					<td colspan="4">
						<form:input path="recordInfo.patient" htmlEscape="false" maxlength="100" class="input-xlarge " cssStyle="width: 450px;"/>
					</td>
					<td style="border-left: hidden;"></td>调解情况
				</tr>
				<tr>
					<td class="tit">医方参加人员</td>
					<td colspan="4">
						<form:input path="recordInfo.doctor" htmlEscape="false" maxlength="100" class="input-xlarge " cssStyle="width: 450px;"/>
					</td>
					<td style="border-left: hidden;"></td>
				</tr>
				<tr>
					<td class="tit">事由</td>
					<td colspan="4" style="font-size: 16px;">
						<span style="color:red;">${empty signAgreement.mediateProgram.patient?signAgreement.complaintMain.patientName:signAgreement.mediateProgram.patient}</span>&nbsp;与&nbsp;<span style="color: red;">${signAgreement.complaintMain.hospital.name}</span>&nbsp;医疗纠纷，经山西省医疗纠纷人民调解委员会调解员调查、调解后，医患双方自愿达成一致意见，今天，在山西省医疗纠纷人民调解委员会调解员主持下，签署人民调解协议书。
					</td>
					<td style="border-left: hidden;"></td>
				</tr>
				<tr>
					<td class="tit">笔录内容</td>
					<td colspan="4">
						<form:textarea path="recordInfo.recordContent" htmlEscape="false" class="input-xlarge"
									   style="margin: 0px; width: 938px;" rows="15px;"/>
					</td>
					<td style="border-left: hidden;"></td>
				</tr>
				<td colspan="6" style="text-align: center;">
					<input id="record" class="btn btn-primary" type="submit" value="导 出"
						   onclick="$('#export').val('record')" data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在导出数据之前请先保存数据。</h4>"/>
					<input id="recordPrint" class="btn btn-primary" type="button" value="打 印" onclick="$('#export').val('record');exportWord();"  data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在打印数据之前请先保存数据。</h4>"/>

				</td>
			</table>
		</div>
		<div class="tab-pane fade" id="annex">
			<table class="table-form">
				<tr>
					<input type="hidden" name="fjtype1" value="7">
					<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
						签到表：
					</td>
					<td style="width: 450px;">
						<input type="hidden" id="files1" name="files1" htmlEscape="false" class="input-xlarge"
							   value="${files1}"/>
						<input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
						<div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files" uploadPath="/sign/signAgreement/sign" selectMultiple="true"
																	  maxWidth="100" maxHeight="100"/></div>
					</td>
				</tr>
				<tr>
					<input type="hidden" name="fjtype2" value="8">
					<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
						会议记录：
					</td>
					<td style="width: 450px;">
						<input type="hidden" id="files2" name="files2" htmlEscape="false" class="input-xlarge"
							   value="${files2}"/>
						<input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">
						<div style="margin-top: -45px;"><sys:ckfinder input="files2" type="files" uploadPath="/sign/signAgreement/meet"
									  selectMultiple="true"
																	  maxWidth="100" maxHeight="100"/></div>
					</td>
				</tr>
				<tr>
					<input type="hidden" name="fjtype3" value="9">
					<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
						协议书：
					</td>
					<td style="width: 450px;">
						<input type="hidden" id="files3" name="files3" htmlEscape="false" class="input-xlarge"
							   value="${files3}"/>
						<input type="hidden" id="acceId3" name="acceId3" value="${acceId3}">
						<div style="margin-top: -45px;"><sys:ckfinder input="files3" type="files" uploadPath="/sign/signAgreement/xieyi"
									  selectMultiple="true"
																	  maxWidth="100" maxHeight="100"/></div>
					</td>
				</tr>
				<tr>
					<input type="hidden" name="fjtype4" value="10">
					<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
						其他材料：
					</td>
					<td style="width: 450px;">
						<input type="hidden" id="files4" name="files4" htmlEscape="false" class="input-xlarge"
							   value="${files4}"/>
						<input type="hidden" id="acceId4" name="acceId4" value="${acceId4}">
						<div style="margin-top: -45px;"><sys:ckfinder input="files4" type="files" uploadPath="/sign/signAgreement/other"
									  selectMultiple="true"
																	  maxWidth="100" maxHeight="100"/></div>
					</td>
				</tr>
			</table>
		</div>
	</div>

	<table class="table-form">
		<tr>
			<td class="tit"><font color="red" style="width: 10px;">*</font>签署协议/判决时间：</td>
			<td >
				<input id="ratifyAccord" name="ratifyAccord" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="${signAgreement.ratifyAccord}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width: 250px; height: 25px;"/>
				<span class="help-inline"> </span>
			</td>
				<%--<td class="tit" >交理赔时间：</td>--%>
				<%--<td >--%>
				<%--<input name="claimSettlementTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"--%>
				<%--value="${signAgreement.claimSettlementTime}"--%>
				<%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width: 250px; height: 25px;"/>--%>
				<%--<span class="help-inline"><font color="red" style="width: 10px;">*</font> </span>--%>
				<%--</td>--%>
		</tr>
		<tr>
			<td class="tit" ><font color="red" style="width: 10px;">*</font>协议金额：</td>
			<td >
				<form:input path="agreementAmount" htmlEscape="false" class="input-xlarge required number" maxlength="10"/>
			</td>
		</tr>
		<tr>
			<td class="tit"><font color="red" style="width: 10px;">*</font>保险金额：</td>
			<td >
				<form:input path="insuranceAmount" htmlEscape="false" class="input-xlarge required number" maxlength="10"/>
			</td>
		</tr>
			<%--<tr>--%>

			<%--<td class="tit">赔付时间：</td>--%>
			<%--<td >--%>
			<%--<input name="compensateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"--%>
			<%--value="${signAgreement.compensateTime}"--%>
			<%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width: 250px; height: 25px;"/>--%>
			<%--<span class="help-inline"><font color="red" style="width: 10px;">*</font> </span>--%>
			<%--</td>--%>
			<%--</tr>--%>
		<tr>
			<td class="tit"><font color="red" style="width: 10px;">*</font>下一环节处理人：</td>
			<td >
				<sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${empty signAgreement.nextLinkMan?fns:getUser().id:signAgreement.nextLinkMan}" labelName="" labelValue="${empty signAgreement.linkEmployee.name?fns:getUser().name:signAgreement.linkEmployee.name}"
								title="用户" url="/sys/office/treeData?type=3&officeType=1" dataMsgRequired="必填信息" cssClass="required" allowClear="true" isAll="true" notAllowSelectParent="true" />
			</td>
		</tr>
	</table>
</fieldset>
    <div class="form-actions" style="text-align: center;">

			<input type="button"  class="btn btn-primary" value="生成协议编号" style="width: 110px;" id="btn">

		<shiro:hasPermission name="sign:signAgreement:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#flag').val('no'),$('#export').val('no'),removeCssClass()"/>&nbsp;</shiro:hasPermission>
		<shiro:hasPermission name="sign:signAgreement:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="下一步" onclick="$('#flag').val('yes'),$('#export').val('no'),addCssClass()"/>&nbsp;</shiro:hasPermission>
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
	<act:histoicFlow procInsId="${signAgreement.complaintMain.procInsId}" />
</form:form>
<script type="text/javascript">
	window.onload=function(){
		var id = $("#agreementNumber")
		if(id.val()==""||id.val()==null){

		}else{
			$("#btn").attr("style","display:none;");
		}
	}

    $(function(){
        $("#btn").click(function(){
            $.ajax({
                url:"${ctx}/sign/signAgreement/agreement",
                type:"post",
                success: function(data) {
                    $("#agreementNumber").val(data);
                },
                error: function(err) {
                    alert(2);
                }
            });
            return false;
        });
    });

	var medicalOfficeEmpRowIdx = 0, medicalOfficeEmpTp = $("#medicalOfficeEmpTp").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
	var patientLinkEmpRowIdx = 0, patientLinkEmpTp = $("#patientLinkEmpTp").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
	var patientLinkDRowIdx = 0, patientLinkDTp = $("#patientLinkDTp").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
	$(document).ready(function() {
		var officeData = ${fns:toJson(signAgreement.medicalOfficeEmpList)};
		for (var i=0; i<officeData.length; i++){
			addRow('#medicalOfficeEmpList', medicalOfficeEmpRowIdx, medicalOfficeEmpTp, officeData[i]);
			medicalOfficeEmpRowIdx = medicalOfficeEmpRowIdx + 1;
		}

		var data = ${fns:toJson(signAgreement.patientLinkDList)};
		for (var i=0; i<data.length; i++){
			addRow('#patientLinkDList', patientLinkDRowIdx, patientLinkDTp, data[i]);
			patientLinkDRowIdx = patientLinkDRowIdx + 1;
		}

		var PatientData = ${fns:toJson(signAgreement.patientLinkEmpList)};
		for (var i=0; i<PatientData.length; i++){
			addRow('#patientLinkEmpList', patientLinkEmpRowIdx, patientLinkEmpTp, PatientData[i]);
			patientLinkEmpRowIdx = patientLinkEmpRowIdx + 1;
		}
	});
</script>
</body>
</html>
<%--
<div class="control-group">
			<label class="control-label">调解情况 多个逗号：</label>
			<div class="controls">
				<form:input path="mediation" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">协议约定事项  多个逗号隔开：</label>
			<div class="controls">
				<form:input path="agreedMatter" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">履行协议方式  多个逗号隔开：</label>
			<div class="controls">
				<form:input path="performAgreementMode" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">协议说明  多个逗号隔开：</label>
			<div class="controls">
				<form:input path="agreementExplain" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
<div class="control-group">
			<label class="control-label">处理人：</label>
			<div class="controls">
				<form:input path="handlePeople" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处理日期：</label>
			<div class="controls">
				<form:input path="handleTime" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>--%>




<%--<%@ page contentType="text/html;charset=UTF-8" %>--%>
<%--<%@ include file="/WEB-INF/views/include/taglib.jsp"%>--%>
<%--<html>--%>
<%--<head>--%>
<%--<title>签署协议管理</title>--%>
<%--<meta name="decorator" content="default"/>--%>
<%--<script type="text/javascript">--%>
<%--$(document).ready(function() {--%>
<%--//$("#name").focus();--%>
<%--$("#inputForm").validate({--%>
<%--submitHandler: function(form){--%>
<%--loading('正在提交，请稍等...');--%>
<%--$("input[type=checkbox]").each(function(){--%>
<%--$(this).after("<input type=\"hidden\" name=\""+$(this).attr("name")+"\" value=\""--%>
<%--+($(this).attr("checked")?"1":"0")+"\"/>");--%>
<%--$(this).attr("name", "_"+$(this).attr("name"));--%>
<%--});--%>
<%--form.submit();--%>
<%--},--%>
<%--errorContainer: "#messageBox",--%>
<%--errorPlacement: function(error, element) {--%>
<%--$("#messageBox").text("输入有误，请先更正。");--%>
<%--if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){--%>
<%--error.appendTo(element.parent().parent());--%>
<%--} else {--%>
<%--error.insertAfter(element);--%>
<%--}--%>
<%--}--%>
<%--});--%>
<%--});--%>

<%--function addRow(list, idx, tpl, row){--%>
<%--$(list).append(Mustache.render(tpl, {--%>
<%--idx: idx, delBtn: true, row: row--%>
<%--}));--%>
<%--$(list+idx).find("select").each(function(){--%>
<%--$(this).val($(this).attr("data-value"));--%>
<%--});--%>
<%--$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){--%>
<%--var ss = $(this).attr("data-value").split(',');--%>
<%--for (var i=0; i<ss.length; i++){--%>
<%--if($(this).val() == ss[i]){--%>
<%--$(this).attr("checked","checked");--%>
<%--}--%>
<%--}--%>
<%--});--%>
<%--}--%>
<%--function delRow(obj, prefix,key){--%>
<%--var id = $(prefix+key);--%>
<%--var delFlag = $(prefix+"_delFlag");--%>
<%--if (id.val() == ""){--%>
<%--$(obj).parent().parent().remove();--%>
<%--}else if(delFlag.val() == "0"){--%>
<%--delFlag.val("1");--%>
<%--$(obj).html("&divide;").attr("title", "撤销删除");--%>
<%--$(obj).parent().parent().addClass("error");--%>
<%--}else if(delFlag.val() == "1"){--%>
<%--delFlag.val("0");--%>
<%--$(obj).html("&times;").attr("title", "删除");--%>
<%--$(obj).parent().parent().removeClass("error");--%>
<%--}--%>
<%--}--%>
<%--</script>--%>
<%--</head>--%>
<%--<body>--%>
<%--<ul class="nav nav-tabs">--%>
<%--<li><a href="${ctx}/sign/signAgreement/">签署协议列表</a></li>--%>
<%--<li class="active"><a href="${ctx}/sign/signAgreement/form?id=${signAgreement.id}">签署协议<shiro:hasPermission name="sign:signAgreement:edit">${not empty signAgreement.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sign:signAgreement:edit">查看</shiro:lacksPermission></a></li>--%>
<%--</ul><br/>--%>
<%--<form:form id="inputForm" modelAttribute="signAgreement" action="${ctx}/sign/signAgreement/save" method="post" class="form-horizontal">--%>
<%--<form:hidden path="signAgreementId"/>--%>
<%--<form:hidden path="createDate"/>--%>
<%--<form:hidden path="createBy"/>--%>
<%--<form:hidden path="complaintMainId"/>--%>
<%--<form:hidden path="complaintMain.complaintMainId"/>--%>
<%--<form:hidden path="complaintMain.act.taskId"/>--%>
<%--<form:hidden path="complaintMain.act.taskName"/>--%>
<%--<form:hidden path="complaintMain.act.taskDefKey"/>--%>
<%--<form:hidden path="complaintMain.act.procInsId"/>--%>
<%--<form:hidden path="complaintMain.act.procDefId"/>--%>
<%--<form:hidden path="complaintMain.procInsId"/>--%>
<%--<form:hidden id="flag" path="complaintMain.act.flag"/>--%>
<%--<sys:message content="${message}"/>--%>

<%--<ul id="myTab" class="nav nav-tabs">--%>
<%--<li class="active">--%>
<%--<a href="#sign" data-toggle="tab">调解协议书</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="#annex" data-toggle="tab">附件</a>--%>
<%--</li>--%>
<%--</ul>--%>
<%--<div id="myTabContent" class="tab-content">--%>
<%--<div class="tab-pane fade in active" id="sign">--%>
<%--<ul id="myTab1" class="nav nav-tabs">--%>
<%--<li class="active">--%>
<%--<a href="#patient" data-toggle="tab">甲方(患方)</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="#hospital" data-toggle="tab">乙方(医方)</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="#jfgy" data-toggle="tab">纠纷概要</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="#tjqk" data-toggle="tab">调解情况</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="#xyydsx" data-toggle="tab">协议约定事项</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="#lxxyfs" data-toggle="tab">履行协议方式</a>--%>
<%--</li>--%>
<%--<li>--%>
<%--<a href="#xysm" data-toggle="tab">协议说明</a>--%>
<%--</li>--%>
<%--</ul>--%>
<%--<div id="myTab1Content" class="tab-content">--%>
<%--<div class="tab-pane fade in active" id="patient">--%>
<%--<table id="patientTable" class="table table-striped table-bordered table-condensed">--%>
<%--<legend>患方</legend>--%>
<%--<thead>--%>
<%--<tr>--%>
<%--<th class="hide"></th>--%>
<%--<th >姓名</th>--%>
<%--<th >与患者关系</th>--%>
<%--<th >身份证号</th>--%>
<%--<th >住址</th>--%>
<%--<th >操作</th>--%>
<%--<shiro:hasPermission name="sign:signAgreement:edit">--%>
<%--<th >&nbsp;</th>--%>
<%--</shiro:hasPermission>--%>
<%--</tr>--%>
<%--</thead>--%>
<%--<tbody id="patientLinkEmpList"></tbody>--%>
<%--<shiro:hasPermission name="sign:signAgreement:edit">--%>
<%--<tfoot>--%>
<%--<tr><td colspan="7"><a href="javascript:" onclick="addRow('#patientLinkEmpList', patientLinkEmpRowIdx, patientLinkEmpTp);patientLinkEmpRowIdx = patientLinkEmpRowIdx + 1;" class="btn">新增</a></td></tr>--%>
<%--</tfoot></shiro:hasPermission>--%>
<%--</table>--%>
<%--<script type="text/template" id="patientLinkEmpTp">//<!----%>
<%--<tr id="patientLinkEmpList{{idx}}">--%>
<%--<td class="hide">--%>
<%--<input id="patientLinkEmpList{{idx}}_id" name="patientLinkEmpList[{{idx}}].id" type="hidden" value="{{row.id}}"/>--%>
<%--<input id="patientLinkEmpList{{idx}}_patientLinkEmpId" name="patientLinkEmpList[{{idx}}].patientLinkEmpId" type="hidden" value="{{row.patientLinkEmpId}}"/>--%>
<%--<input id="patientLinkEmpList{{idx}}_linkType" name="patientLinkEmpList[{{idx}}].linkType" type="hidden" value="{{row.linkType}}"/>--%>
<%--<input id="patientLinkEmpList{{idx}}_relationId" name="patientLinkEmpList[{{idx}}].relationId" type="hidden" value="{{row.relationId}}"/>--%>
<%--<input id="patientLinkEmpList{{idx}}_delFlag" name="patientLinkEmpList[{{idx}}].delFlag" type="hidden" value="0"/>--%>
<%--</td>--%>

<%--<td>--%>
<%--<input id="patientLinkEmpList{{idx}}_patientLinkName" name="patientLinkEmpList[{{idx}}].patientLinkName" type="text" value="{{row.patientLinkName}}" maxlength="100" class="required" />--%>
<%--</td>--%>
<%--<td>--%>
<%--<input id="patientLinkEmpList{{idx}}_patientRelation" name="patientLinkEmpList[{{idx}}].patientRelation" type="text" value="{{row.patientRelation}}" maxlength="100" class="required" />--%>
<%--</td>--%>
<%--<td>--%>
<%--<input id="patientLinkEmpList{{idx}}_idNumber" name="patientLinkEmpList[{{idx}}].idNumber" type="text" value="{{row.idNumber}}" maxlength="20" class="required" />--%>
<%--</td>--%>
<%--<td>--%>
<%--<input id="patientLinkEmpList{{idx}}_patientLinkAddress" name="patientLinkEmpList[{{idx}}].patientLinkAddress" type="text" value="{{row.patientLinkAddress}}" maxlength="20" class="required" />--%>
<%--</td>--%>

<%--<shiro:hasPermission name="sign:signAgreement:edit"><td class="text-center" width="10">--%>
<%--{{#delBtn}}<span class="close" onclick="delRow(this, '#patientLinkEmpList{{idx}}','_patientLinkEmpId')" title="删除">&times;</span>{{/delBtn}}--%>
<%--</td></shiro:hasPermission>--%>
<%--</tr>//-->--%>
<%--</script>--%>


<%--<table id="patientDTable" class="table table-striped table-bordered table-condensed">--%>
<%--<legend>委托（法定）代理人</legend>--%>
<%--<thead>--%>
<%--<tr>--%>
<%--<th class="hide"></th>--%>
<%--<th >姓名</th>--%>
<%--<th >与患者关系</th>--%>
<%--<th >身份证号</th>--%>
<%--<th >住址</th>--%>
<%--<th >操作</th>--%>
<%--<shiro:hasPermission name="sign:signAgreement:edit">--%>
<%--<th >&nbsp;</th>--%>
<%--</shiro:hasPermission>--%>
<%--</tr>--%>
<%--</thead>--%>
<%--<tbody id="patientLinkDList"></tbody>--%>
<%--<shiro:hasPermission name="sign:signAgreement:edit">--%>
<%--<tfoot>--%>
<%--<tr><td colspan="7"><a href="javascript:" onclick="addRow('#patientLinkDList', patientLinkDRowIdx, patientLinkDTp);patientLinkDRowIdx = patientLinkDRowIdx + 1;" class="btn">新增</a></td></tr>--%>
<%--</tfoot></shiro:hasPermission>--%>
<%--</table>--%>
<%--<script type="text/template" id="patientLinkDTp">//<!----%>
<%--<tr id="patientLinkDList{{idx}}">--%>
<%--<td class="hide">--%>
<%--<input id="patientLinkDList{{idx}}_id" name="patientLinkDList[{{idx}}].id" type="hidden" value="{{row.id}}"/>--%>
<%--<input id="patientLinkDList{{idx}}_patientLinkEmpId" name="patientLinkDList[{{idx}}].patientLinkEmpId" type="hidden" value="{{row.patientLinkEmpId}}"/>--%>
<%--<input id="patientLinkDList{{idx}}_linkType" name="patientLinkDList[{{idx}}].linkType" type="hidden" value="{{row.linkType}}"/>--%>
<%--<input id="patientLinkDList{{idx}}_relationId" name="patientLinkDList[{{idx}}].relationId" type="hidden" value="{{row.relationId}}"/>--%>
<%--<input id="patientLinkDList{{idx}}_delFlag" name="patientLinkDList[{{idx}}].delFlag" type="hidden" value="0"/>--%>
<%--</td>--%>

<%--<td>--%>
<%--<input id="patientLinkDList{{idx}}_patientLinkName" name="patientLinkDList[{{idx}}].patientLinkName" type="text" value="{{row.patientLinkName}}" maxlength="100" class="required" />--%>
<%--</td>--%>
<%--<td>--%>
<%--<input id="patientLinkDList{{idx}}_patientRelation" name="patientLinkDList[{{idx}}].patientRelation" type="text" value="{{row.patientRelation}}" maxlength="100" class="required" />--%>
<%--</td>--%>
<%--<td>--%>
<%--<input id="patientLinkDList{{idx}}_idNumber" name="patientLinkDList[{{idx}}].idNumber" type="text" value="{{row.idNumber}}" maxlength="20" class="required" />--%>
<%--</td>--%>
<%--<td>--%>
<%--<input id="patientLinkDList{{idx}}_patientLinkAddress" name="patientLinkDList[{{idx}}].patientLinkAddress" type="text" value="{{row.patientLinkAddress}}" maxlength="20" class="required" />--%>
<%--</td>--%>

<%--<shiro:hasPermission name="sign:signAgreement:edit"><td class="text-center" width="10">--%>
<%--{{#delBtn}}<span class="close" onclick="delRow(this, '#patientLinkDList{{idx}}','_patientLinkEmpId')" title="删除">&times;</span>{{/delBtn}}--%>
<%--</td></shiro:hasPermission>--%>
<%--</tr>//-->--%>
<%--</script>--%>


<%--</div>--%>
<%--<div class="tab-pane fade" id="hospital">--%>
<%--<table id="hospitalTable" class="table table-striped table-bordered table-condensed">--%>
<%--<thead>--%>
<%--<tr>--%>
<%--<th class="hide"></th>--%>
<%--<th >医疗机构名称</th>--%>
<%--<th >地址</th>--%>
<%--<th >法定代表人</th>--%>
<%--<th >职务</th>--%>
<%--<th >委托代理人</th>--%>
<%--<th >性别</th>--%>
<%--<th >身份证号</th>--%>
<%--<th >单位及职务</th>--%>
<%--<th >操作</th>--%>
<%--<shiro:hasPermission name="sign:signAgreement:edit">--%>
<%--<th >&nbsp;</th>--%>
<%--</shiro:hasPermission>--%>
<%--</tr>--%>
<%--</thead>--%>
<%--<tbody id="medicalOfficeEmpList"></tbody>--%>
<%--<shiro:hasPermission name="sign:signAgreement:edit">--%>
<%--<tfoot>--%>
<%--<tr><td colspan="7"><a href="javascript:" onclick="addRow('#medicalOfficeEmpList', medicalOfficeEmpRowIdx, medicalOfficeEmpTp);medicalOfficeEmpRowIdx = medicalOfficeEmpRowIdx + 1;" class="btn">新增</a></td></tr>--%>
<%--</tfoot></shiro:hasPermission>--%>
<%--</table>--%>
<%--<script type="text/template" id="medicalOfficeEmpTp">//<!----%>
<%--<tr id="medicalOfficeEmpList{{idx}}">--%>
<%--<td class="hide">--%>
<%--<input id="medicalOfficeEmpList{{idx}}_id" name="medicalOfficeEmpList[{{idx}}].id" type="hidden" value="{{row.id}}"/>--%>
<%--<input id="medicalOfficeEmpList{{idx}}_medicalOfficeEmpId" name="medicalOfficeEmpList[{{idx}}].medicalOfficeEmpId" type="hidden" value="{{row.medicalOfficeEmpId}}"/>--%>
<%--<input id="medicalOfficeEmpList{{idx}}_relationId" name="medicalOfficeEmpList[{{idx}}].relationId" type="hidden" value="{{row.relationId}}"/>--%>
<%--<input id="medicalOfficeEmpList{{idx}}_delFlag" name="medicalOfficeEmpList[{{idx}}].delFlag" type="hidden" value="0"/>--%>
<%--</td>--%>

<%--<td>--%>
<%--<input id="medicalOfficeEmpList{{idx}}_medicalOfficeName" name="medicalOfficeEmpList[{{idx}}].medicalOfficeName" type="text" value="{{row.medicalOfficeName}}" maxlength="100" class="required" />--%>
<%--</td>--%>
<%--<td>--%>
<%--<input id="medicalOfficeEmpList{{idx}}_medicalOfficeAddress" name="medicalOfficeEmpList[{{idx}}].medicalOfficeAddress" type="text" value="{{row.medicalOfficeAddress}}" maxlength="100" class="required" />--%>
<%--</td>--%>
<%--<td>--%>
<%--<input id="medicalOfficeEmpList{{idx}}_legalRepresentative" name="medicalOfficeEmpList[{{idx}}].legalRepresentative" type="text" value="{{row.legalRepresentative}}" maxlength="20" class="required" />--%>
<%--</td>--%>
<%--<td>--%>
<%--<input id="medicalOfficeEmpList{{idx}}_medicalOfficePost" name="medicalOfficeEmpList[{{idx}}].medicalOfficePost" type="text" value="{{row.medicalOfficePost}}" maxlength="20" class="required" />--%>
<%--</td>--%>
<%--<td>--%>
<%--<input id="medicalOfficeEmpList{{idx}}_medicalOfficeAgent" name="medicalOfficeEmpList[{{idx}}].medicalOfficeAgent" type="text" value="{{row.medicalOfficeAgent}}" maxlength="32" class="required" />--%>
<%--</td>--%>
<%--<td>--%>
<%--<select id="medicalOfficeEmpList{{idx}}_medicalOfficeSex" name="medicalOfficeEmpList[{{idx}}].medicalOfficeSex" value="{{row.medicalOfficeSex}}" data-value="{{row.medicalOfficeSex}}" class="input-mini">--%>
<%--<option value=""></option>--%>
<%--<option value="1"  >男</option>--%>
<%--<option value="2"  >女</option>--%>
<%--</select>--%>
<%--</td>--%>
<%--<td>--%>
<%--<input id="medicalOfficeEmpList{{idx}}_medicalOfficeIdcard" name="medicalOfficeEmpList[{{idx}}].medicalOfficeIdcard" type="text" value="{{row.medicalOfficeIdcard}}" maxlength="20" class="required" />--%>
<%--</td>--%>
<%--<td>--%>
<%--<input id="medicalOfficeEmpList{{idx}}_medicalOfficeCompany" name="medicalOfficeEmpList[{{idx}}].medicalOfficeCompany" type="text" value="{{row.medicalOfficeCompany}}" maxlength="200" class="required" />--%>
<%--</td>--%>
<%--<shiro:hasPermission name="sign:signAgreement:edit"><td class="text-center" width="10">--%>
<%--{{#delBtn}}<span class="close" onclick="delRow(this, '#medicalOfficeEmpList{{idx}}','_medicalOfficeEmpId')" title="删除">&times;</span>{{/delBtn}}--%>
<%--</td></shiro:hasPermission>--%>
<%--</tr>//-->--%>
<%--</script>--%>
<%--</div>--%>
<%--<div class="tab-pane fade" id="jfgy">--%>
<%--<table  class="table-form">--%>
<%--<tr>--%>
<%--<td class="tit">--%>
<%--纠纷概要：--%>
<%--</td>--%>
<%--<td >--%>
<%--<form:textarea path="summaryOfDisputes" htmlEscape="false" class="input-xlarge required"/>--%>
<%--</td>--%>
<%--</tr>--%>
<%--</table>--%>
<%--</div>--%>
<%--<div class="tab-pane fade" id="tjqk">--%>
<%--<table id="contentTable" class="table table-striped table-bordered table-condensed">--%>
<%--<thead>--%>
<%--<tr>--%>
<%--<th></th>--%>
<%--<th style="text-align:center;">类型</th>--%>
<%--<th style="text-align:center;">内容</th>--%>
<%--</tr>--%>
<%--</thead>--%>
<%--<tbody>--%>
<%--<c:forEach items="${tjqk}" var="column" varStatus="vs">--%>
<%--<tr>&lt;%&ndash;${column.delFlag eq '1'?' class="error" title="已删除的列，保存之后消失！"':''}&ndash;%&gt;--%>
<%--<td nowrap style="text-align:center;vertical-align:middle;">--%>
<%--<input type="hidden" name="mediationList[${vs.index}].typeId" value="${column.typeId}"/>--%>
<%--<input type="hidden" name="mediationList[${vs.index}].delFlag" value="${column.delFlag}"/>--%>
<%--<input type="checkbox" name="mediationList[${vs.index}].label" value="1" ${column.label eq '1' ? 'checked' : ''}/>--%>
<%--</td>--%>
<%--<td style="text-align:center;vertical-align:middle;">--%>
<%--${column.typeName}--%>
<%--</td>--%>
<%--<td style="text-align:center;vertical-align:middle;">--%>
<%--${column.content}--%>
<%--</td>--%>
<%--</tr>--%>
<%--</c:forEach>--%>
<%--</tbody>--%>
<%--</table>--%>
<%--</div>--%>
<%--<div class="tab-pane fade" id="xyydsx">--%>
<%--<table id="content1Table" class="table table-striped table-bordered table-condensed">--%>
<%--<thead>--%>
<%--<tr>--%>
<%--<th></th>--%>
<%--<th style="text-align:center;">内容</th>--%>
<%--</tr>--%>
<%--</thead>--%>
<%--<tbody>--%>
<%--<c:forEach items="${xyydsx}" var="column" varStatus="vs">--%>
<%--<tr>&lt;%&ndash;${column.delFlag eq '1'?' class="error" title="已删除的列，保存之后消失！"':''}&ndash;%&gt;--%>
<%--<td nowrap style="text-align:center;vertical-align:middle;">--%>
<%--<input type="hidden" name="meatterList[${vs.index}].typeId" value="${column.typeId}"/>--%>
<%--<input type="hidden" name="meatterList[${vs.index}].delFlag" value="${column.delFlag}"/>--%>
<%--<input type="checkbox" name="meatterList[${vs.index}].label" value="1" ${column.label eq '1' ? 'checked' : ''}/>--%>
<%--</td>--%>
<%--<td style="text-align:center;vertical-align:middle;">--%>
<%--${column.content}--%>
<%--</td>--%>
<%--</tr>--%>
<%--</c:forEach>--%>
<%--</tbody>--%>
<%--</table>--%>
<%--</div>--%>
<%--<div class="tab-pane fade" id="lxxyfs">--%>
<%--<table id="content2Table" class="table table-striped table-bordered table-condensed">--%>
<%--<thead>--%>
<%--<tr>--%>
<%--<th></th>--%>
<%--<th style="text-align:center;">类型</th>--%>
<%--<th style="text-align:center;">内容</th>--%>
<%--</tr>--%>
<%--</thead>--%>
<%--<tbody>--%>
<%--<c:forEach items="${lxxyfs}" var="column" varStatus="vs">--%>
<%--<tr>&lt;%&ndash;${column.delFlag eq '1'?' class="error" title="已删除的列，保存之后消失！"':''}&ndash;%&gt;--%>
<%--<td nowrap style="text-align:center;vertical-align:middle;">--%>
<%--<input type="hidden" name="performList[${vs.index}].typeId" value="${column.typeId}"/>--%>
<%--<input type="hidden" name="performList[${vs.index}].delFlag" value="${column.delFlag}"/>--%>
<%--<input type="checkbox" name="performList[${vs.index}].label" value="1" ${column.label eq '1' ? 'checked' : ''}/>--%>
<%--</td>--%>
<%--<td style="text-align:center;vertical-align:middle;">--%>
<%--${column.typeName}--%>
<%--</td>--%>
<%--<td style="text-align:center;vertical-align:middle;">--%>
<%--${column.content}--%>
<%--</td>--%>
<%--</tr>--%>
<%--</c:forEach>--%>
<%--</tbody>--%>
<%--</table>--%>

<%--</div>--%>
<%--<div class="tab-pane fade" id="xysm">--%>
<%--<table id="content3Table" class="table table-striped table-bordered table-condensed">--%>
<%--<thead>--%>
<%--<tr>--%>
<%--<th></th>--%>
<%--<th style="text-align:center;">类型</th>--%>
<%--<th style="text-align:center;">内容</th>--%>
<%--</tr>--%>
<%--</thead>--%>
<%--<tbody>--%>
<%--<c:forEach items="${xysm}" var="column" varStatus="vs">--%>
<%--<tr>--%>
<%--<td nowrap style="text-align:center;vertical-align:middle;">--%>
<%--<input type="hidden" name="agreementList[${vs.index}].typeId" value="${column.typeId}"/>--%>
<%--<input type="hidden" name="agreementList[${vs.index}].delFlag" value="${column.delFlag}"/>--%>
<%--<input type="checkbox" name="agreementList[${vs.index}].label" value="1" ${column.label eq '1' ? 'checked' : ''}/>--%>
<%--</td>--%>
<%--<td style="text-align:center;vertical-align:middle;">--%>
<%--${column.typeName}--%>
<%--</td>--%>
<%--<td style="text-align:center;vertical-align:middle;">--%>
<%--${column.content}--%>
<%--</td>--%>
<%--</tr>--%>
<%--</c:forEach>--%>
<%--</tbody>--%>
<%--</table>--%>

<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="tab-pane fade" id="annex">--%>
<%--<table class="table-form">--%>
<%--<tr>--%>
<%--<input type="hidden" name="fjtype1" value="7">--%>
<%--<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">--%>
<%--签到表：--%>
<%--</td>--%>
<%--<td style="width: 450px;">--%>
<%--<input type="hidden" id="files1" name="files1" htmlEscape="false" class="input-xlarge"--%>
<%--value="${files1}"/>--%>
<%--<input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">--%>
<%--<sys:ckfinder input="files1" type="files" uploadPath="/sign/signAgreement/sign" selectMultiple="false"--%>
<%--maxWidth="100" maxHeight="100"/>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<input type="hidden" name="fjtype2" value="8">--%>
<%--<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">--%>
<%--会议记录：--%>
<%--</td>--%>
<%--<td style="width: 450px;">--%>
<%--<input type="hidden" id="files2" name="files2" htmlEscape="false" class="input-xlarge"--%>
<%--value="${files2}"/>--%>
<%--<input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">--%>
<%--<sys:ckfinder input="files2" type="files" uploadPath="/sign/signAgreement/meet"--%>
<%--selectMultiple="false"--%>
<%--maxWidth="100" maxHeight="100"/>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<input type="hidden" name="fjtype3" value="9">--%>
<%--<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">--%>
<%--协议书：--%>
<%--</td>--%>
<%--<td style="width: 450px;">--%>
<%--<input type="hidden" id="files3" name="files3" htmlEscape="false" class="input-xlarge"--%>
<%--value="${files3}"/>--%>
<%--<input type="hidden" id="acceId3" name="acceId3" value="${acceId3}">--%>
<%--<sys:ckfinder input="files3" type="files" uploadPath="/sign/signAgreement/xieyi"--%>
<%--selectMultiple="false"--%>
<%--maxWidth="100" maxHeight="100"/>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<input type="hidden" name="fjtype4" value="10">--%>
<%--<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">--%>
<%--其他材料：--%>
<%--</td>--%>
<%--<td style="width: 450px;">--%>
<%--<input type="hidden" id="files4" name="files4" htmlEscape="false" class="input-xlarge"--%>
<%--value="${files4}"/>--%>
<%--<input type="hidden" id="acceId4" name="acceId4" value="${acceId4}">--%>
<%--<sys:ckfinder input="files4" type="files" uploadPath="/sign/signAgreement/other"--%>
<%--selectMultiple="false"--%>
<%--maxWidth="100" maxHeight="100"/>--%>
<%--</td>--%>
<%--</tr>--%>
<%--</table>--%>
<%--</div>--%>
<%--</div>--%>

<%--<table class="table-form">--%>
<%--<tr>--%>
<%--<td class="tit" >协议号：</td>--%>
<%--<td >--%>
<%--<form:input path="agreementNumber" htmlEscape="false" maxlength="20" class="input-xlarge required"/>--%>
<%--</td>--%>
<%--<td class="tit">签署协议/判决时间：</td>--%>
<%--<td >--%>
<%--<input name="ratifyAccord" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"--%>
<%--value="${signAgreement.ratifyAccord}"--%>
<%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width: 250px; height: 25px;"/>--%>
<%--<span class="help-inline"><font color="red" style="width: 10px;">*</font> </span>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td class="tit" >协议金额：</td>--%>
<%--<td >--%>
<%--<form:input path="agreementAmount" htmlEscape="false" class="input-xlarge required"/>--%>
<%--</td>--%>
<%--<td class="tit">保险金额：</td>--%>
<%--<td >--%>
<%--<form:input path="insuranceAmount" htmlEscape="false" class="input-xlarge required"/>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td class="tit" >交理赔时间：</td>--%>
<%--<td >--%>
<%--<input name="claimSettlementTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"--%>
<%--value="${signAgreement.claimSettlementTime}"--%>
<%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width: 250px; height: 25px;"/>--%>
<%--<span class="help-inline"><font color="red" style="width: 10px;">*</font> </span>--%>
<%--</td>--%>
<%--<td class="tit">赔付时间：</td>--%>
<%--<td >--%>
<%--<input name="compensateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"--%>
<%--value="${signAgreement.compensateTime}"--%>
<%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width: 250px; height: 25px;"/>--%>
<%--<span class="help-inline"><font color="red" style="width: 10px;">*</font> </span>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td class="tit">下一环节处理人：</td>--%>
<%--<td >--%>
<%--<sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${empty signAgreement.nextLinkMan?fns:getUser().id:signAgreement.nextLinkMan}" labelName="" labelValue="${empty signAgreement.linkEmployee.name?fns:getUser().name:signAgreement.linkEmployee.name}"--%>
<%--title="用户" url="/sys/office/treeData?type=3&officeType=1" dataMsgRequired="必填信息" cssClass="required" allowClear="true" notAllowSelectParent="true" />--%>
<%--</td>--%>
<%--</tr>--%>
<%--</table>--%>
<%--<div class="form-actions">--%>
<%--<shiro:hasPermission name="sign:signAgreement:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#flag').val('no')"/>&nbsp;</shiro:hasPermission>--%>
<%--<shiro:hasPermission name="sign:signAgreement:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="下一步" onclick="$('#flag').val('yes')"/>&nbsp;</shiro:hasPermission>--%>
<%--<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
<%--</div>--%>
<%--<act:histoicFlow procInsId="${signAgreement.complaintMain.procInsId}" />--%>
<%--</form:form>--%>
<%--<script type="text/javascript">--%>
<%--var medicalOfficeEmpRowIdx = 0, medicalOfficeEmpTp = $("#medicalOfficeEmpTp").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");--%>
<%--var patientLinkEmpRowIdx = 0, patientLinkEmpTp = $("#patientLinkEmpTp").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");--%>
<%--var patientLinkDRowIdx = 0, patientLinkDTp = $("#patientLinkDTp").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");--%>
<%--$(document).ready(function() {--%>
<%--var officeData = ${fns:toJson(signAgreement.medicalOfficeEmpList)};--%>
<%--for (var i=0; i<officeData.length; i++){--%>
<%--addRow('#medicalOfficeEmpList', medicalOfficeEmpRowIdx, medicalOfficeEmpTp, officeData[i]);--%>
<%--medicalOfficeEmpRowIdx = medicalOfficeEmpRowIdx + 1;--%>
<%--}--%>

<%--var data = ${fns:toJson(signAgreement.patientLinkDList)};--%>
<%--for (var i=0; i<data.length; i++){--%>
<%--addRow('#patientLinkDList', patientLinkDRowIdx, patientLinkDTp, data[i]);--%>
<%--patientLinkDRowIdx = patientLinkDRowIdx + 1;--%>
<%--}--%>

<%--var PatientData = ${fns:toJson(signAgreement.patientLinkEmpList)};--%>
<%--for (var i=0; i<PatientData.length; i++){--%>
<%--addRow('#patientLinkEmpList', patientLinkEmpRowIdx, patientLinkEmpTp, PatientData[i]);--%>
<%--patientLinkEmpRowIdx = patientLinkEmpRowIdx + 1;--%>
<%--}--%>
<%--});--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>
<%--&lt;%&ndash;--%>
<%--<div class="control-group">--%>
<%--<label class="control-label">调解情况 多个逗号：</label>--%>
<%--<div class="controls">--%>
<%--<form:input path="mediation" htmlEscape="false" maxlength="200" class="input-xlarge "/>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="control-group">--%>
<%--<label class="control-label">协议约定事项  多个逗号隔开：</label>--%>
<%--<div class="controls">--%>
<%--<form:input path="agreedMatter" htmlEscape="false" maxlength="200" class="input-xlarge "/>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="control-group">--%>
<%--<label class="control-label">履行协议方式  多个逗号隔开：</label>--%>
<%--<div class="controls">--%>
<%--<form:input path="performAgreementMode" htmlEscape="false" maxlength="200" class="input-xlarge "/>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="control-group">--%>
<%--<label class="control-label">协议说明  多个逗号隔开：</label>--%>
<%--<div class="controls">--%>
<%--<form:input path="agreementExplain" htmlEscape="false" maxlength="200" class="input-xlarge "/>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="control-group">--%>
<%--<label class="control-label">处理人：</label>--%>
<%--<div class="controls">--%>
<%--<form:input path="handlePeople" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="control-group">--%>
<%--<label class="control-label">处理日期：</label>--%>
<%--<div class="controls">--%>
<%--<form:input path="handleTime" htmlEscape="false" maxlength="20" class="input-xlarge "/>--%>
<%--</div>--%>
<%--</div>&ndash;%&gt;--%>