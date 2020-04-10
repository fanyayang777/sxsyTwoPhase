<%@ taglib prefix="IntellijIdeaRulezzz" uri="http://java.sun.com/jsp/jstl/functionss" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>案件总结管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
				    var flag=$("#flag").val();
				    if(flag=='yes'){
                        getFileNum(form)
                    }else{
						var aa=$("#export").val();
						if(aa=='no'){
							loading('正在提交，请稍等...');
						}
                        form.submit();
                    }

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
        //下一步的时候 显示卷宗编号
        function getFileNum(form) {//在保存前 获得卷宗编号
            var path="${ctx}/summaryinfo/summaryInfo/getFileNum";
            $.ajaxSettings.async = false;//ajax 要设置成同步，异步的情况下sucess方法里面设值还没成功，方法就先返回了，这样也取不到值
            $.post(path,{'hospital':''},function(res){
                if(res.data.number!='' && res.data.number!=null && res.data.number!=undefined){
                    top.$.jBox.confirm("卷宗编号为    “"+res.data.number+"”   ，    请确认是否保存？","系统提示",function(v,h,f){
                        if(v=="ok"){
                            $("#fileNumber").val(res.data.number);
                            loading('正在提交，请稍等...');
                            form.submit();
                        }
                    },{buttonsFocus:1, closed:function(){
                    }});
                }else{
                    alertx("卷宗编号生成失败，请联系工程师修复！");
                }
            },"json");
        }

		function exportWord() {
			var aa=$("#export").val();
			var path="${ctx}/summaryinfo/summaryInfo/pass";
			$.ajaxSettings.async = true;
			$.post(path,{'summaryId':"${summaryInfo.summaryId}",'export':aa,"print":"true"},function(res){
				if(res.data.url!=''){
					var url='${pageContext.request.contextPath}'+res.data.url;
					windowOpen(url,"pdf",1500,700);
				}else{
				}
			},"json");
		}

		//导出和打印加提示
		$(function (){
			$(function () { $("[data-toggle='tooltip']").tooltip({html : true }); });
		});

		function removeCssClass() {
			$('#nextLinkManName').removeClass('required');
			$('#filingTime').removeClass('required');
			$('#summaryEmp').removeClass('required');
			$('#summaryTime').removeClass('required');
			$('#summary').removeClass('required');
			$('#mediatePass').removeClass('required');

		}
		function addCssClass() {
			$('#nextLinkManName').addClass('required');
			$('#filingTime').addClass('required');
			$('#summaryEmp').addClass('required');
			$('#summaryTime').addClass('required');
			$('#summary').addClass('required');
			$('#mediatePass').addClass('required');

		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/summaryinfo/summaryInfo/">案件总结列表</a></li>
		<%--<li class="active"><a href="${ctx}/summaryinfo/summaryInfo/form?id=${summaryInfo.id}">案件总结<shiro:hasPermission name="summaryinfo:summaryInfo:edit">${not empty summaryInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="summaryinfo:summaryInfo:edit">查看</shiro:lacksPermission></a></li>--%>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="summaryInfo" action="${ctx}/summaryinfo/summaryInfo/save" method="post" class="form-horizontal">
        <form:hidden path="summaryId"/>
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
		<input type="hidden"  id="export" name="export"/>
		<form:hidden path="fileNumber"/>
		<sys:message content="${message}"/>
		<ul id="myTab" class="nav nav-tabs">
			<li class="active">
				<a href="#Summary" data-toggle="tab">案件总结</a>
			</li>
			<li>
				<a href="#attachment" data-toggle="tab">补录附件</a>
			</li>

		</ul>
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active" id="Summary">
			<table class="table-form">
				<%--<tr>
					<td style="text-align: center;" >卷宗编号:</td>
					<td >
						${summaryInfo.fileNumber}
					</td>
				</tr>
				<tr>
					<td style="text-align: center;">归档时间:</td>
					<td>
						<input id="filingTime" name="filingTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
							   value="${summaryInfo.filingTime}"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width:270px;"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;">总结人:</td>
					<td>
						<form:input path="summaryEmp" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;" >总结时间:</td>
					<td>
						<input id="summaryTime" name="summaryTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
							   value="${summaryInfo.summaryTime}"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width:270px;"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;" colspan="1">总结:</td>
					<td>
						<form:textarea path="summary" htmlEscape="false" rows="5" maxlength="500" class="input-xxlarge required" cssStyle="width: 1500px;"/>
					</td>
				</tr>--%>
					<tr>
						<td class="tit" style="text-align: center;" width="13%">受理时间:</td>
						<td width="20%">
							<input id="acceptanceTime" name="acceptanceTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
								   value="${summaryInfo.acceptanceTime}"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"  disabled="disabled"/>
						</td>
						<td class="tit" style="text-align: center;" width="13%" >结案时间:</td>
						<td width="20%">
							<input id="ratifyAccord" name="ratifyAccord" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
								   value="${summaryInfo.ratifyAccord}"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"  disabled="disabled"/>
						</td>
						<td class="tit" style="text-align: center;" width="13%">调解天数:</td>
						<td>
							<form:input path="flowDays" htmlEscape="false" maxlength="32" class="input-medium required"/>
						</td>
					</tr>
					<tr>
					<td style="text-align: center;">责任度:</td>
					<td>
						<form:input path="responsibilityRatio" htmlEscape="false" maxlength="32" class="input-medium required" readonly="true"/>
					</td>
					<td style="text-align: center;" >调解次数:</td>
					<td>
						<form:input path="meetingFrequency" htmlEscape="false" maxlength="32" class="input-medium required" readonly="true"/>
					</td>
					<td style="text-align: center;" >调解结果:</td>
					<td>
						<form:select  path="mediateResult"  class="input-medium">
							<form:option value="1">成功</form:option>
							<form:option value="2">终止</form:option>
							<form:option value="3">销案</form:option>
						</form:select>
					</td>
				</tr>
                    <tr>
                        <td  class="tit">患方协议送达时间：</td>
                        <td>
                            <input type="hidden"  name="performAgreement.performAgreementId" value="${summaryInfo.performAgreement.performAgreementId}"/>
                            <input id="patientServiceTime" name="performAgreement.patientServiceTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                                   value="${summaryInfo.performAgreement.patientServiceTime}"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"
                            />
                        </td>
                        <td class="tit">医方协议送达时间：</td>
                        <td>
                            <input id="hospitalServiceTime" name="performAgreement.hospitalServiceTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                                   value="${summaryInfo.performAgreement.hospitalServiceTime}"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
                        </td>
                        <td class="tit">协议生效时间：</td>
                        <td>
                            <input id="takeEffectTime" name="performAgreement.takeEffectTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                                   value="${summaryInfo.performAgreement.takeEffectTime}"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
                        </td>

                    </tr>
                    <tr>
                        <td  class="tit">交理赔时间：</td>
                        <td>
                            <input id="claimSettlementTime"name="performAgreement.claimSettlementTime"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                                   value="${summaryInfo.performAgreement.claimSettlementTime}"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
                        </td>
                        <td class="tit">保险公司赔付时间：</td>
                        <td>
                            <input id="insurancePayTime" name="performAgreement.insurancePayTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                                   value="${summaryInfo.performAgreement.insurancePayTime}"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
                        </td>
                        <td class="tit">医院赔付时间：</td>
                        <td>
                            <input id="hospitalPayTime" name="performAgreement.hospitalPayTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                                   value="${summaryInfo.performAgreement.hospitalPayTime}"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
                        </td>

                    </tr>
					<tr>
						<td style="text-align: center;" >调解经过:</td>
						<td colspan="5">
							<form:textarea path="mediatePass" htmlEscape="false" rows="4" maxlength="65535" class="input-xxlarge required" cssStyle="width: 1000px;"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;" >经验总结:</td>
						<td colspan="5">
							<form:textarea path="summary" htmlEscape="false" rows="4" maxlength="65535" class="input-xxlarge required" cssStyle="width: 1000px;"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;" >其他:</td>
						<td colspan="5">
							<form:textarea path="other" htmlEscape="false" rows="4" maxlength="65535" class="input-xxlarge" cssStyle="width: 1000px;"/>
						</td>
					</tr>
					<td colspan="6" style="text-align: center;">
						<input id="record" class="btn btn-primary" type="submit" value="导 出"
							   onclick="$('#export').val('summ');removeCssClass()" data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在导出数据之前请先保存数据。</h4>"/>
						<input id="recordPrint" class="btn btn-primary" type="button" value="打 印" onclick="$('#export').val('summ');exportWord();removeCssClass()"  data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在打印数据之前请先保存数据。</h4>"/>

					</td>
			</table>
		</div>
		<div class="tab-pane fade" id="attachment">
			<ul id="myTab1" class="nav nav-tabs">
				<li class="active">
					<a href="#huanfang" data-toggle="tab">患方相关附件</a>
				</li>
				<li>
					<a href="#yifang" data-toggle="tab">医方相关附件</a>
				</li>
			</ul>
			<div id="myTabContent1" class="tab-content">
			<div class="tab-pane fade in active" id="huanfang">
				<table class="table-form">
					<tr style=" " >
					<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">患方调解申请：</td>
					<input type="hidden"  name="fjtype1" value="25">
					<td style="width: 450px; ">
						<input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
						<input type="hidden" id="files1" name="files1" htmlEscape="false" class="input-xlarge"  value="${files1}"/>
						<div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files"  uploadPath="/summaryinfo/summaryInfo/huan/mediation" selectMultiple="true" /></div>
					</td>
				</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医疗纠纷材料：</td>
						<input type="hidden"  name="fjtype2" value="26">
						<td style="width: 450px; ">
							<input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">
							<input type="hidden" id="files2" name="files2" htmlEscape="false" class="input-xlarge"  value="${files2}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files2" type="files"  uploadPath="/summaryinfo/summaryInfo/huan/medicalDisputes" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">当事人身份相关证件：</td>
						<input type="hidden"  name="fjtype3" value="27">
						<td style="width: 450px; ">
							<input type="hidden" id="acceId3" name="acceId3" value="${acceId3}">
							<input type="hidden" id="files3" name="files3" htmlEscape="false" class="input-xlarge"  value="${files3}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files3" type="files"  uploadPath="/summaryinfo/summaryInfo/huan/partyDocuments" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">授权委托书：</td>
						<input type="hidden"  name="fjtype4" value="28">
						<td style="width: 450px; ">
							<input type="hidden" id="acceId4" name="acceId4" value="${acceId4}">
							<input type="hidden" id="files4" name="files4" htmlEscape="false" class="input-xlarge"  value="${files4}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files4" type="files"  uploadPath="/summaryinfo/summaryInfo/huan/powerOfAttorney" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">委托人、被委托人相关证件：</td>
						<input type="hidden"  name="fjtype5" value="29">
						<td style="width: 450px; ">
							<input type="hidden" id="acceId5" name="acceId5" value="${acceId5}">
							<input type="hidden" id="files5" name="files5" htmlEscape="false" class="input-xlarge"  value="${files5}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files5" type="files"  uploadPath="/summaryinfo/summaryInfo/huan/PrincipalRelatedDocuments" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">病例及相关检查资料：</td>
						<input type="hidden"  name="fjtype6" value="30">
						<td style="width: 450px; ">
							<input type="hidden" id="acceId6" name="acceId6" value="${acceId6}">
							<input type="hidden" id="files6" name="files6" htmlEscape="false" class="input-xlarge"  value="${files6}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files6" type="files"  uploadPath="/summaryinfo/summaryInfo/huan/caseInformation" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医疗费用及其他相关费用票据：</td>
						<input type="hidden"  name="fjtype7" value="31">
						<td style="width: 450px; ">
							<input type="hidden" id="acceId7" name="acceId7" value="${acceId7}">
							<input type="hidden" id="files7" name="files7" htmlEscape="false" class="input-xlarge"  value="${files7}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files7" type="files"  uploadPath="/summaryinfo/summaryInfo/huan/feeNotes" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">误工证明：</td>
						<input type="hidden"  name="fjtype8" value="32">
						<td style="width: 450px; ">
							<input type="hidden" id="acceId8" name="acceId8" value="${acceId8}">
							<input type="hidden" id="files8" name="files8" htmlEscape="false" class="input-xlarge"  value="${files8}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files8" type="files"  uploadPath="/summaryinfo/summaryInfo/huan/proofOfMissingWork" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">患者死亡相关证明：</td>
						<input type="hidden"  name="fjtype9" value="33">
						<td style="width: 450px; ">
							<input type="hidden" id="acceId9" name="acceId9" value="${acceId9}">
							<input type="hidden" id="files9" name="files9" htmlEscape="false" class="input-xlarge"  value="${files9}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files9" type="files"  uploadPath="/summaryinfo/summaryInfo/huan/patientDeathCertificate" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">新生儿出生证明：</td>
						<input type="hidden"  name="fjtype10" value="34">
						<td style="width: 450px; ">
							<input type="hidden" id="acceId10" name="acceId10" value="${acceId10}">
							<input type="hidden" id="files10" name="files10" htmlEscape="false" class="input-xlarge"  value="${files10}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files10" type="files"  uploadPath="/summaryinfo/summaryInfo/huan/neonatal" selectMultiple="true" /></div>
						</td>
					</tr>
				</table>
			</div>
			<div class="tab-pane fade " id="yifang">
				<table class="table-form">
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医方调解申请：</td>
						<input type="hidden"  name="fjtype11" value="35">
						<td style="width: 450px; ">
							<input type="hidden" id="acceId11" name="acceId11" value="${acceId11}">
							<input type="hidden" id="files11" name="files11" htmlEscape="false" class="input-xlarge"  value="${files11}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files11" type="files"  uploadPath="/summaryinfo/summaryInfo/yifang/yiMediation" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">法定代表人身份证明：</td>
						<input type="hidden"  name="fjtype12" value="36">
						<td style="width: 450px; ">
							<input type="hidden" id="acceId12" name="acceId12" value="${acceId12}">
							<input type="hidden" id="files12" name="files12" htmlEscape="false" class="input-xlarge"  value="${files12}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files12" type="files"  uploadPath="/summaryinfo/summaryInfo/yifang/yiLegalRepresentative" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">授权委托书：</td>
						<input type="hidden"  name="fjtype13" value="37">
						<td style="width: 450px; ">
							<input type="hidden" id="acceId13" name="acceId13" value="${acceId13}">
							<input type="hidden" id="files13" name="files13" htmlEscape="false" class="input-xlarge"  value="${files13}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files13" type="files"  uploadPath="/summaryinfo/summaryInfo/yifang/yiPowerOfAttorney" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">委托人、被委托人相关证件：</td>
						<input type="hidden"  name="fjtype14" value="38">
						<td style="width: 450px; ">
							<input type="hidden" id="acceId14" name="acceId14" value="${acceId14}">
							<input type="hidden" id="files14" name="files14" htmlEscape="false" class="input-xlarge"  value="${files14}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files14" type="files"  uploadPath="/summaryinfo/summaryInfo/yifang/yiPrincipalDocuments" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">执业许可证副本：</td>
						<input type="hidden"  name="fjtype15" value="39">
						<td style="width: 450px; ">
							<input type="hidden" id="acceId15" name="acceId15" value="${acceId15}">
							<input type="hidden" id="files15" name="files15" htmlEscape="false" class="input-xlarge"  value="${files15}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files15" type="files"  uploadPath="/summaryinfo/summaryInfo/yifang/yiLicenseToPractise" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">诊疗分析意见：</td>
						<input type="hidden"  name="fjtype16" value="40">
						<td style="width: 450px; ">
							<input type="hidden" id="acceId16" name="acceId16" value="${acceId16}">
							<input type="hidden" id="files16" name="files16" htmlEscape="false" class="input-xlarge"  value="${files16}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files16" type="files"  uploadPath="/summaryinfo/summaryInfo/yifang/yiZhenLiaoAnalysis" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">出险医务人员相关证件
							（身份证、执业证书）：</td>
						<input type="hidden"  name="fjtype17" value="41">
						<td style="width: 450px; ">
							<input type="hidden" id="acceId17" name="acceId17" value="${acceId17}">
							<input type="hidden" id="files17" name="files17" htmlEscape="false" class="input-xlarge"  value="${files17}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files17" type="files"  uploadPath="/summaryinfo/summaryInfo/yifang/yiChuXianRenDocuments" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">相关证据材料：</td>
						<input type="hidden"  name="fjtype18" value="42">
						<td style="width: 450px; ">
							<input type="hidden" id="acceId18" name="acceId18" value="${acceId18}">
							<input type="hidden" id="files18" name="files18" htmlEscape="false" class="input-xlarge"  value="${files18}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files18" type="files"  uploadPath="/summaryinfo/summaryInfo/yifang/yiRelevantEvidence" selectMultiple="true" /></div>
						</td>
					</tr>
					<tr style=" " >
						<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">病历资料：</td>
						<input type="hidden"  name="fjtype19" value="43">
						<td style="width: 450px; ">
							<input type="hidden" id="acceId19" name="acceId19" value="${acceId19}">
							<input type="hidden" id="files19" name="files19" htmlEscape="false" class="input-xlarge"  value="${files19}"/>
							<div style="margin-top: -45px;"><sys:ckfinder input="files19" type="files"  uploadPath="/summaryinfo/summaryInfo/yifang/yiMedicalRecords" selectMultiple="true" /></div>
						</td>
					</tr>
				</table>
			</div>
			</div>
		</div>
	</div>

		<table class="table-form">
			<%--<tr>--%>
				<%--<td class="tit">--%>
					<%--处理人：--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--<form:input path="handlePeople" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
				<%--</td>--%>
				<%--<td class="tit">--%>
					<%--处理日期：--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--<input name="handleTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "--%>
						<%--value="${assessAppraisal.handleTime}"--%>
						<%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>--%>
				<%--</td>--%>
			<%--</tr>--%>
			<tr>
				<%--<td class="tit">--%>
					<%--下一处理环节：--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--<form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
				<%--</td>--%>
				<td class="tit">
					下一环节处理人：
				</td>
				<td>
					<sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${summaryInfo.nextLinkMan}" labelName="" labelValue="${summaryInfo.linkEmployee.name}"
								title="用户" url="/sys/office/treeData?type=3&officeType=1" role="raters" cssClass="required" isAll="true" allowClear="true" notAllowSelectParent="true"  dataMsgRequired="必填信息"/>
				</td>
	</tr>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="summaryinfo:summaryInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#flag').val('no');removeCssClass();$('#export').val('no');">&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="summaryinfo:summaryInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="下一步" onclick="$('#flag').val('yes');addCssClass()"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<act:histoicFlow procInsId="${summaryInfo.complaintMain.procInsId}" />
	</form:form>
</body>
</html>s