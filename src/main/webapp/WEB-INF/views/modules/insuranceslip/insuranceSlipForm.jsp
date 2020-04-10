<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>投保单管理</title>
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
		});
        function daoru() {
            $.jBox($("#importBox").html(), {
                title: "导入数据", buttons: {"关闭": true},
                bottomText: "导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
            });
        }
	</script>
	<script>
        //医院基本信息调用
        function policyHolderTreeselectCallBack(v, h, f,id){
            // document.getElementById("inputForm").reset();
            $("#policyHolderId").val(id);
            var path = "${ctx}/insuranceslip/insuranceSlip/officeInfo";
            $.post(path,{hospital:id},function (res) {
                //被保险人
                if (res.data.theInsured != '' && res.data.theInsured != undefined){
                    $("#theInsured").val(res.data.theInsured);
                }
                //投保人联系电话
                if (res.data.policyPhone != '' && res.data.policyPhone != undefined){
                    $("#policyPhone").val(res.data.policyPhone);
                    $("#theInsuredPhone").val(res.data.policyPhone);
                }
                //投保人：电子邮箱
                if (res.data.emailAddress != '' && res.data.emailAddress != undefined){
                    $("#emailAddress").val(res.data.emailAddress)
                }
                //投保人：通信地址
                if (res.data.sitePostcode != '' && res.data.sitePostcode != undefined){
                    $("#sitePostcode").val(res.data.sitePostcode);
                    $("#theInsuredSite").val(res.data.sitePostcode);
                }
                //医院等级
                if (res.data.hospitalGrade != '' && res.data.hospitalGrade != undefined){
                    $("#grade").val(res.data.hospitalGrade)
                }
                //床位数
                if (res.data.sickbedNumber != '' && res.data.sickbedNumber != undefined){
                    $("#sickbedNumber").val(res.data.sickbedNumber)
                }
                //被保人：邮编
                if (res.data.postalCode != '' && res.data.postalCode != undefined){
                    $("#postalCode").val(res.data.postalCode)
                }
                //每人基本保险费
                if (res.data.allEveryonePremium != '' && res.data.allEveryonePremium != undefined){
                    $("#allEveryonePremium").val(res.data.allEveryonePremium);
                }
                //年累计赔偿限额
                if (res.data.accumulatedQuota != '' && res.data.accumulatedQuota != undefined){
                    $("#accumulatedQuota").val(res.data.accumulatedQuota)
                }
                //精神赔偿限额
                if (res.data.spiritQuota != '' && res.data.spiritQuota != undefined){
                    $("#spiritQuota").val(res.data.spiritQuota)
                }
                //每次赔偿限额
                if (res.data.everytimeQuota != '' && res.data.everytimeQuota != undefined){
                    // $("#everytimeQuota").text(res.data.ecerytimeQuota)
                    var arr = new Array();
                    arr.push(res.data.everytimeQuota);
                    if (res.data.everytimeQuotaBig != '' && res.data.everytimeQuotaBig != undefined) {
                        arr.push(res.data.everytimeQuotaBig);
                    }
                    console.log("每次赔偿限额:" + arr);
                    for (var i = 0; i < arr.length ; i++){
                        document.getElementById("everytimeQuota").options[i] = new Option(arr[i],i);
                    }
                    $("#everytimeQuota").val($(this).find("option:first-child").val()).trigger('change')
                }
                //每人累计赔偿限额
                if (res.data.everyoneQuota != '' && res.data.everyoneQuota != undefined){
                    // $("#everyoneQuota").text(res.data.everyoneQuota)
                    var arr = new Array();
                    arr.push(res.data.everyoneQuota);
                    if (res.data.everyoneQuotaBig != '' && res.data.everyoneQuotaBig != undefined) {
                        arr.push(res.data.everyoneQuotaBig);
                    }
                    console.log("每人累计赔偿限额:" + arr);
                    for (var i = 0; i < arr.length ; i++){
                        document.getElementById("everyoneQuota").options[i] = new Option(arr[i],i);
                    }
                    $("#everyoneQuota").val($(this).find("option:first-child").val()).trigger('change')
                }
                //法律费用 累计限额
                if (res.data.lawAccumulatedQuota != '' && res.data.lawAccumulatedQuota != undefined){
                    $("#lawAccumulatedQuota").val(res.data.lawAccumulatedQuota)
                }
                //法律费用 每次限额
                if (res.data.lawEverytimeQuota != '' && res.data.lawEverytimeQuota != undefined){
                    $("#lawEverytimeQuota").val(res.data.lawEverytimeQuota)
                }
                //每床位保险费
                if (res.data.bedPremium != '' && res.data.bedPremium != undefined){
                    $("#bedPremium").val(res.data.bedPremium)
                }
                //医疗机构保险费
                if (res.data.medicalPremium != '' && res.data.medicalPremium != undefined){
                    $("#medicalPremium").val(res.data.medicalPremium)
                }
                //医院类别
                if (res.data.category != '' && res.data.category != undefined){
                    $("#category").val(res.data.category)
                }
                //默认系数
                $("#addReduce").val(1);
                $("#enchanceDeduction").val(1);
                $("#riskFloat").val(1);

            },"json")
        }
        //医务人员保险费
        function selmingdan() {
            var isCkecked = $("#sel").is(':checked');
            var premium = $("#allEveryonePremium").val();
            if (isCkecked){
                $("#selEveryonePremium").removeAttr("disabled");
                $("#selClinicOperationNumber").removeAttr("disabled");
                $("#selClinicNotoperationNumber").removeAttr("disabled");
                $("#selMedicalLaboratoryNumber").removeAttr("disabled");
                $("#selPremiumTotal").removeAttr("disabled");
                $("#selEveryonePremium").val(premium);
            }else {
                $("#selEveryonePremium").val("");
                $("#selClinicOperationNumber").val("");
                $("#selClinicNotoperationNumber").val("");
                $("#selMedicalLaboratoryNumber").val("");
                $("#selPremiumTotal").val("");

                $("#selEveryonePremium").prop('disabled','disabled');
                $("#selClinicOperationNumber").prop('disabled','disabled');
                $("#selClinicNotoperationNumber").prop('disabled','disabled');
                $("#selMedicalLaboratoryNumber").prop('disabled','disabled');
                $("#selPremiumTotal").prop('disabled','disabled');
            }
        }
        //提交
        function tijiao() {
            $("#arbitrator").removeAttr("disabled");
        }
        //诉讼
        function shusong() {
            $("#arbitrator").prop('disabled','disabled');
        }

        //附加险添加
        function addRisk() {
            var options = $("#addittionRisk option:selected");
            var neirong = options.val(); //下拉框选项内容
            var office = $("#policyHolderId").val(); //投保人
            var headCount = $("#allMedicalNumber").val();
            var path = "${ctx}/insuranceslip/insuranceSlip/officeInfo";
            $.post(path,{hospital:office,addittionRisk:neirong},function (res) {
                if (neirong == "changsuo"){
                    $("#addAndMoney").css('display','table-row');
                    //保险费
                    if (res.data.sitePremium1 != '' && res.data.sitePremium1 != undefined){
                        $("#addittionPremium").val(res.data.sitePremium1);
                    }
                    //年累计赔偿
                    if (res.data.siteYearAddup != '' && res.data.siteYearAddup != undefined){
                        $("#appendAccumulatedQuota").val(res.data.siteYearAddup);
                    }
					//每人赔偿
                    if (res.data.siteEachtime != '' && res.data.siteEachtime != undefined){
                        $("#appendEverytimeQuota").val(res.data.siteEachtime);
                    }
                } else if (neirong == "shanghai"){
                    if (res.data.accidentPremium != '' && res.data.accidentPremium != undefined){
                        var sum = headCount * res.data.accidentPremium;
                        $("#addittionPremium").val(sum);
                    }
                } else if (neirong == "chuanran"){
                    if (res.data.contagionPremium != '' && res.data.contagionPremium != undefined){
                        var sum = headCount * res.data.contagionPremium;
                        $("#addittionPremium").val(sum);
                    }
                } else if (neirong == "waipin"){

                }
			},"json")
        }


        function removeCssClass() {
            if ($("#policyPhone").hasClass("required") == true) {
                $("#policyPhone").removeClass("required");
            }
            if ($("#sitePostcode").hasClass("required") == true) {
                $("#sitePostcode").removeClass("required");
            }
            if ($("#emailAddress").hasClass("required") == true) {
                $("#emailAddress").removeClass("required");
            }
            if ($("#theInsured").hasClass("required") == true) {
                $("#theInsured").removeClass("required");
            }
            if ($("#theInsuredPhone").hasClass("required") == true) {
                $("#theInsuredPhone").removeClass("required");
            }
            if ($("#theInsuredSite").hasClass("required") == true) {
                $("#theInsuredSite").removeClass("required");
            }
            if ($("#postalCode").hasClass("required") == true) {
                $("#postalCode").removeClass("required");
            }
            if ($("#insureArea").hasClass("required") == true) {
                $("#insureArea").removeClass("required");
            }
			if ($("#category").hasClass("required") == true) {
                $("#category").removeClass("required");
            }
            if ($("#practiceNumber").hasClass("required") == true) {
                $("#practiceNumber").removeClass("required");
            }
            if ($("#grade").hasClass("required") == true) {
                $("#grade").removeClass("required");
            }
            if ($("#mold").hasClass("required") == true) {
                $("#mold").removeClass("required");
            }
            if ($("#nature").hasClass("required") == true) {
                $("#nature").removeClass("required");
            }
            if ($("#department").hasClass("required") == true) {
                $("#department").removeClass("required");
            }
            if ($("#sickbedNumber").hasClass("required") == true) {
                $("#sickbedNumber").removeClass("required");
            }
            if ($("#peopleNumber").hasClass("required") == true) {
                $("#peopleNumber").removeClass("required");
            }
            if ($("#medicalStaffNumber").hasClass("required") == true) {
                $("#medicalStaffNumber").removeClass("required");
            }


            if ($("#addReduce").hasClass("required") == true) {
                $("#addReduce").removeClass("required");
            }
            if ($("#enchanceDeduction").hasClass("required") == true) {
                $("#enchanceDeduction").removeClass("required");
            }
            if ($("#riskFloat").hasClass("required") == true) {
                $("#riskFloat").removeClass("required");
            }

        }

        //新保 计算
        function newBao() {
            console.log(111)
            var grade = $("#grade").val(); //等级
            var beds = $("#sickbedNumber").val();  //病床数
            var bedPremium = $("#bedPremium").val();  //每床单价
            var	allPremium = $("#allEveryonePremium").val();	//全部：每人基本费用
            var	allNumber = $("#allMedicalNumber").val();	//全部：医务人员人数
            var category = $("#category").val();	//等級系数

            if (grade != '' && beds != '' && bedPremium != '' && allPremium != '' && allNumber != '' && category != ''){
                var office = $("#policyHolderId").val();
                var path = "${ctx}/insuranceslip/insuranceSlip/officeInfo";
                $.post(path,{hospital:office},function (res) {
                    var beds2 = parseInt(beds); //床数
                    var bedPremium2 = parseInt(bedPremium);//每床单价
                    var allPremium2 = parseInt(allPremium);//每人单价
                    var allNumber2 = parseInt(allNumber);//人员人数
                    var formula; //公式
                    //（每床单价×（床位数）+每人单价×医务人员数）
                    var basic = bedPremium2+ "x" + beds2+"+"+allPremium2+"x" +allNumber2;
                    console.log(basic);
                    //计算（500元/床×（床位数）+400元/人×医务人员数）
                    var basicSum = beds2 * bedPremium2 + allPremium2 * allNumber2;
                    var coefficient = Number(res.data.coefficient); //医院类型系数
					var addReduce = Number($("#addReduce").val()); //赔偿限额后的系数
					var enchance = Number($("#enchanceDeduction").val()); //提高免赔额后的系数
					var riskFloat = Number($("#riskFloat").val()); //风险浮动系数
					console.log("xs"+coefficient)
                    if (grade == "三级甲等"){
						var sum = basicSum * 1.2 * coefficient * addReduce * enchance * riskFloat;
						formula = basic + "x" + 1.2  + "x" +  coefficient + "x" + addReduce + "x" + enchance + "x" + riskFloat + "=" + sum;

                        $("#computationalFormula").val(formula);
                        $("#oddicialReceiptsPermium").val(sum);

                    }else  if (grade == "三级乙等"){
                        var sum = basicSum * 1.1 * coefficient * addReduce * enchance * riskFloat;
                        formula = basic  + "x" +  1.1 + "x" + coefficient + "x" + addReduce + "x" + enchance + "x" + riskFloat + "=" + sum;
                        $("#computationalFormula").val(formula);
                        $("#oddicialReceiptsPermium").val(sum);
                    }else  if (grade == "二级甲等"){
                        var sum = basicSum * 1 * coefficient * addReduce * enchance * riskFloat;
                        formula = basic + "x" + 1  + "x" + coefficient + "x" + addReduce + "x" + enchance + "x" + riskFloat + "=" + sum;
                        $("#computationalFormula").val(formula);
                        $("#oddicialReceiptsPermium").val(sum);
                    }else  if (grade == "二级乙等"){
                        var sum = basicSum * 0.8 * coefficient * addReduce * enchance * riskFloat;
                        formula = basic + "x" +  0.8 + "x" + coefficient + "x" + addReduce + "x" + enchance + "x" + riskFloat + "=" + sum;
                        $("#computationalFormula").val(formula);
                        $("#oddicialReceiptsPermium").val(sum);
                    }else  if (grade == "一级甲等"){
                        var sum = basicSum * 1.2 * coefficient * addReduce * enchance * riskFloat;
                        formula = basic + "x" + 1.2 + "x" + coefficient + "x" + addReduce + "x" + enchance + "x" + riskFloat + "=" + sum;
                        $("#computationalFormula").val(formula);
                        $("#oddicialReceiptsPermium").val(sum);
                    }else  if (grade == "一级乙等"){
                        var sum = basicSum * 0.7 * coefficient * addReduce * enchance * riskFloat;
                        formula = basic + "x" + 0.7 + "x" + coefficient + "x" + addReduce + "x" + enchance + "x" + riskFloat + "=" + sum;
                        $("#computationalFormula").val(formula);
                        $("#oddicialReceiptsPermium").val(sum);
                    }else  if (grade == "社区卫生服务中心"){
                        var sum = 6000 + allPremium2 * allNumber2 * addReduce * enchance * riskFloat;
                        formula = 6000 + "+" + allPremium2 + "x" + allNumber2 + "x" + addReduce + "x" + enchance + "x" + riskFloat + "=" + sum;
                        $("#computationalFormula").val(formula);
                        $("#oddicialReceiptsPermium").val(sum);
                    }else  if (grade == "社区卫生服务站"){
                        var sum = 1500 + allPremium2 * allNumber2 * addReduce * enchance * riskFloat;
                        formula = 1500 + "+" + allPremium2 + "x" + allNumber2 + "x" +  addReduce + "x" + enchance + "x" + riskFloat + "=" + sum;
                        $("#computationalFormula").val(formula);
                        $("#oddicialReceiptsPermium").val(sum);
                    }else  if (grade == "乡村卫生室"){
                        var sum =1000 + addReduce * enchance * riskFloat;
                        formula = 1000 + addReduce + "x" + enchance + "x" + riskFloat + "=" + sum;
                        $("#computationalFormula").val(formula);
                        $("#oddicialReceiptsPermium").val(sum);
                    }else  if (grade == "民营医院"){
                        var sum = basicSum * 1.5 * coefficient * addReduce * enchance * riskFloat;
                        formula = basic + "x" + 1.5 + "x" + coefficient + "x" + addReduce + "x" + enchance + "x" + riskFloat + "=" + sum;
                        $("#computationalFormula").val(formula);
                        $("#oddicialReceiptsPermium").val(sum);
                    }else  if (grade == "个体诊所"){
                        var sum = 3000 + basicSum * addReduce * enchance * riskFloat;
                        formula = basic + "x" + 1.5 + "x" + coefficient + "x" + addReduce + "x" + enchance + "x" + riskFloat + "=" + sum;
                        $("#computationalFormula").val(formula);
                        $("#oddicialReceiptsPermium").val(sum);
                    }else {
                        formula = "待处理"
                        $("#computationalFormula").val(formula);
                    }
                },"json");
            }
        }
        //续保

        //保险费调整系数
        function premiumCoefficient(val,relationId) {
            var elementById = document.getElementById("everytimeQuota");
            var label0 = elementById.options[0].label;
            var label1 = elementById.options[1].label;
            if (val = 1) {
                $("#"+relationId).val(1.2);
            }
            if (val = 0) {
                $("#"+relationId).val(1);
            }
        }

        //医务人员人数
        function allNumber(val,relationId) {
            console.log(val);
            var oneMoney = $("#allEveryonePremium").val();//每人基本保险费
            var medicalPremium = $("#medicalPremium").val();	//医疗机构保险费
            if (val != '' && val != undefined &&
                oneMoney != '' && oneMoney != undefined){
                var sum = val * oneMoney;
                var sum2 = parseInt(sum);
                $("#"+relationId).val(sum2);
                if (medicalPremium != '' && medicalPremium != undefined) {
                    var medicalPremium2 = parseInt(medicalPremium);//医疗机构保险费
                    var zongSum = sum2 + medicalPremium2;
                    $("#basicPremiumTotal").val(zongSum); 	//基本保险费总计
                }
            }
        }
        //保险期间 有月份 有开始 算结束
		function bxStart(val) {
            var year = val.slice(0, 4); //年
            var month = val.slice(5, 7); //月
            var day = val.slice(8); //日
            var insuranceDate = $("#insuranceDate").val();
            if (insuranceDate != '') {
                var year2 = parseInt(year);
                var month2 = parseInt(month);
                var day2 = parseInt(day);
                var insuranceDate2 = parseInt(insuranceDate);
                var monthSum = month2 + insuranceDate2
                var newYear = year2 + 1;
                var newMonthSum = monthSum - 12;
                var newDay = day2 - 1;
                var newDate = newYear + "-" + newMonthSum + "-" + newDay;
                $("#insuranceEndTimeID").val(newDate);
            }
        }
		//追溯期间 有月份 有开始 算结束
		function zsqStart(val) {
			var year = val.slice(0, 4); //年
			var month = val.slice(5, 7); //月
			var day = val.slice(8); //日
			var retroactiveDate = $("#retroactiveDate").val();
			if (retroactiveDate != '') {
				var year2 = parseInt(year);
				var month2 = parseInt(month);
				var day2 = parseInt(day);
				var retroactiveDate2 = parseInt(retroactiveDate);
				var monthSum = month2 + retroactiveDate2
				var newYear = year2 + 1;
				var newMonthSum = monthSum - 12;
				var newDay = day2 - 1;
				var newDate = newYear + "-" + newMonthSum + "-" + newDay;
				$("#retroactiveEndDateID").val(newDate);
			}
		}
        //人数校验
        function NumberBlur() {
			var sum = Number($("#allMedicalNumber").val());
			var opera = Number($("#selClinicOperationNumber").val())
			var not = Number($("#selClinicNotoperationNumber").val());
			var yiji = Number($("#selMedicalLaboratoryNumber").val());
			if (opera != 0 && sum != 0 && not != 0 && yiji != 0){
			    var sel = opera + not + yiji;
			    if (sel != sum) {
					$("#tan").text("和总人数不符")
				}else{
                    $("#tan").text("")
				}
			}
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/insuranceslip/insuranceSlip/">投保单列表</a></li>
		<li class="active"><a href="${ctx}/insuranceslip/insuranceSlip/form?id=${insuranceSlip.insurancePolicyId}">投保单<shiro:hasPermission name="insuranceslip:insuranceSlip:edit">${not empty insuranceSlip.insurancePolicyId?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="insuranceslip:insuranceSlip:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="insuranceSlip" action="${ctx}/insuranceslip/insuranceSlip/save" method="post" class="form-horizontal">
		<form:hidden path="insurancePolicyId"/>
		<form:hidden path="reject"/>
		<input type="hidden" id="flag" name="flag"/>
		<input type="hidden" id="start" name="start"/>
		<sys:message content="${message}"/>
		<ul id="myTab" class="nav nav-tabs">
			<li class="active">
				<a href="#insurance" data-toggle="tab">投保单</a>
			</li>
			<li>
				<a href="#annex" data-toggle="tab" >附件</a>
			</li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="insurance">
				<h2 style="text-align: center;font-weight: bold">
					中国人寿财产保险股份有限公司
				</h2>
				<h3 style="text-align: center;font-weight: bold">
					医疗责任保险投保单
				</h3>
				<p>
					欢迎您到中国人寿财产保险股份有限公司投保！请您在投保前务必详细阅读相关保险条款，特别注意<b>责任免除、投保人及被保险人义务、赔偿处理</b>等内容，据实回答保险人就投保事项提出的相关询问，并用蓝色或黑色墨水笔如实填写保单，投保后相关内容发生变动，请及时通知保险人。
				</p>
				<table class="table-form">
					<tr>
						<td class="tit" width="7%"><font color="red">*</font>投保人名称</td>
						<td colspan="2" style="font-size: 16px;">
							<sys:treeselect id="policyHolder" name="policyHolder" value="${insuranceSlip.policyHolder}" labelName="policyHolder.name" labelValue="${fns:getOfficeId(insuranceSlip.policyHolder).name}"
											title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
						</td>
						<td class="tit" width="3%"><font color="red">*</font>联系电话</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="policyPhone" htmlEscape="false" maxlength="15" class="input-xlarge required"/>
						</td>
					</tr>
					<tr class="border-top-none">
						<td class="tit" width="10%"><font color="red">*</font>通信地址和邮编</td>
						<td colspan="2" style="font-size: 16px; ">
							<form:input path="sitePostcode" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
						</td>
						<td class="tit" width="10%"><font color="red">*</font>电子邮箱</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="emailAddress" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%"><font color="red">*</font>被保险人名称</td>
						<td colspan="2" style="font-size: 16px;">
							<form:input path="theInsured" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
						</td>
						<td class="tit" width="10%"><font color="red">*</font>联系电话</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="theInsuredPhone" htmlEscape="false" maxlength="15" class="input-xlarge required"/>
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%"><font color="red">*</font>被保险人地址</td>
						<td colspan="2" style="font-size: 16px;">
							<form:input path="theInsuredSite" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
						</td>
						<td class="tit" width="10%"><font color="red">*</font>邮政编码</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="postalCode" htmlEscape="false" maxlength="15" class="input-xlarge required"/>
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%">投保区域范围</td>
						<td colspan="2" style="font-size: 16px;">
							<form:input path="insureArea" htmlEscape="false" maxlength="32" class="input-xlarge required" cssStyle="width: 500px"/>
						</td>
						<td class="tit" width="10%">医院类别</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="category" htmlEscape="false" maxlength="32" class="input-xlarge required" />
						</td>
					</tr>
					<tr>
						<td rowspan="5" style="text-align: center">被保险人<br>基本情况</td>
						<td class="tit" width="10%">创建时间：</td>
						<td colspan="1" style="font-size: 16px;">
							<input name="creationTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
								   value="<fmt:formatDate value="${insuranceSlip.creationTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
						</td>
						<td class="tit" width="10%">执业许可证号：</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="practiceNumber" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%">医疗机构等级：</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="grade" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
								<%--<form:select path="grade" class="input-xlarge ">--%>
								<%--<form:option value="" label=""/>--%>
								<%--<form:options items="${fns:getDictList('hospital_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
								<%--</form:select>--%>
						</td>
						<td class="tit" width="10%">类型：</td>
						<td colspan="1" style="font-size: 16px;">
							<form:select path="mold" class="input-xlarge required">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('category')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%">所有制性质：</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="nature" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
						</td>
						<td class="tit" width="10%">主管部门：</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="department" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%">病床数：</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="sickbedNumber" htmlEscape="false" maxlength="5" class="input-xlarge required"/>
						</td>
						<td class="tit" width="10%">年诊疗人数：</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="peopleNumber" htmlEscape="false" maxlength="5" class="input-xlarge required"/>
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%">医务人员人数：</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="medicalStaffNumber" htmlEscape="false" class="input-xlarge required"/>
						</td>
					</tr>

					<tr>
						<td rowspan="3" style="text-align: center">医疗责任<br>赔偿限额:</td>
						<td class="tit" width="10%">每人赔偿限额:</td>
						<td colspan="2" style="font-size: 16px;">
								<%--<form:input path="everyoneQuota" htmlEscape="false" class="input-xlarge "/>--%>
							<form:select path="everyoneQuota" class="input-xlarge ">
								<%--<form:option value="" label=""/>--%>
								<%--<form:options items="${fns:getDictList('eceryone_quota')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
							</form:select>
						</td>
						<td class="tit" rowspan="1" width="10%" style="font-size: 16px; align-content: center">
							每次索赔免赔额
						</td>

					</tr>
					<tr>
						<td class="tit" width="10%">其中：精神损害赔偿额:</td>
						<td colspan="2" style="font-size: 16px;">
							<form:input path="spiritQuota" htmlEscape="false" class="input-xlarge "/>

						</td>
						<td rowspan="2" width="10%" style="font-size: 16px; align-content: center">
								<%--<form:input path="everytimeQuota" htmlEscape="false" class="input-xlarge "/>--%>
							<form:select path="everytimeQuota" class="input-xlarge " onchange="premiumCoefficient(this.value,'enchanceDeduction')">
								<%--<form:option value="" label=""/>--%>
								<%--<form:options items="${fns:getDictList('ecerytime_quota')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
							</form:select>
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%">累计赔偿限额:</td>
						<td  style="font-size: 16px;" >
							<form:input path="accumulatedQuota" htmlEscape="false" class="input-xlarge "/>
								<%--<form:select path="accumulatedQuota" class="input-xlarge ">--%>
								<%--<form:options items="${fns:getDictList('accumulated_quota1')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
								<%--</form:select>--%>
						</td>
					</tr>

					<tr>
						<td rowspan="1" style="text-align: center">法律费用<br>赔偿限额:</td>
						<td class="tit" width="10%">每次赔偿限额:</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="lawEverytimeQuota" htmlEscape="false" class="input-xlarge "/>
								<%--<form:select path="lawEverytimeQuota" class="input-xlarge ">--%>
								<%--<form:option value="" label=""/>--%>
								<%--<form:options items="${fns:getDictList('law_everytime_quota')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
								<%--</form:select>--%>
						</td>
						<td class="tit" width="10%">累计赔偿限额</td>
						<td style="font-size: 16px;" >
							<form:input path="lawAccumulatedQuota" htmlEscape="false" class="input-xlarge "/>
								<%--<form:select path="lawAccumulatedQuota" class="input-xlarge ">--%>
								<%--<form:option value="" label=""/>--%>
								<%--<form:options items="${fns:getDictList('law_accumulated_quota')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
								<%--</form:select>--%>
						</td>
					</tr>

					<tr id="addAndMoney" style="display: none">
						<td rowspan="1" style="text-align: center">附加险<br>医疗机构场所责任险</td>
						<td class="tit" width="10%">每人赔偿限额</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="appendEverytimeQuota"  htmlEscape="false" class="input-xlarge "/>
						</td>
						<td class="tit" width="10%">累计赔偿限额</td>
						<td  style="font-size: 16px;">
							<form:input path="appendAccumulatedQuota" htmlEscape="false" class="input-xlarge "/>
						</td>
					</tr>
					<%--<tr id="addAndyw1" style="display: none">--%>
						<%--<td rowspan="2" style="text-align: center">附加险<br>医务人员意外伤害保险</td>--%>
						<%--<td class="tit" width="10%">一般保险责任<br>保险金额</td>--%>
						<%--<td colspan="1" style="font-size: 16px;">--%>
							<%--<form:input path="accidentInsuranceOne"  htmlEscape="false" class="input-xlarge "/>--%>
						<%--</td>--%>
						<%--<td class="tit" width="10%">医疗纠纷<br>意外医疗</td>--%>
						<%--<td  style="font-size: 16px;">--%>
							<%--<form:input path="accidentInsurance" htmlEscape="false" class="input-xlarge "/>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<%--<tr id="addAndyw2" style="display: none">--%>
						<%--<td class="tit" width="10%">一般保险责任<br>意外医疗</td>--%>
						<%--<td colspan="1" style="font-size: 16px;">--%>
							<%--<form:input path="appendEverytimeQuota"  htmlEscape="false" class="input-xlarge "/>--%>
						<%--</td>--%>
						<%--<td class="tit" width="10%">医疗纠纷<br>保险金额</td>--%>
						<%--<td  style="font-size: 16px;">--%>
							<%--<form:input path="appendAccumulatedQuota" htmlEscape="false" class="input-xlarge "/>--%>
						<%--</td>--%>
					<%--</tr>--%>

					<%--<tr id="addAndchuan" style="display: none">--%>
						<%--<td rowspan="1" style="text-align: center">附加险<br>医护人员法定传染病责任保险</td>--%>
						<%--<td class="tit" width="10%">每人赔偿限额</td>--%>
						<%--<td colspan="1" style="font-size: 16px;">--%>
							<%--<form:input path="appendEverytimeQuota"  htmlEscape="false" class="input-xlarge "/>--%>
						<%--</td>--%>
						<%--<td class="tit" width="10%">累计赔偿限额</td>--%>
						<%--<td  style="font-size: 16px;">--%>
							<%--<form:input path="appendAccumulatedQuota" htmlEscape="false" class="input-xlarge "/>--%>
						<%--</td>--%>
					<%--</tr>--%>

					<tr style="text-align: center;font-weight: bold">
						<td colspan="5" style="text-align: center;height: 50px;font-size: 20px">
							基本保险费计算
						</td>
					</tr>
					<tr>
						<td rowspan="1" style="text-align: center">医疗机构<br>保险费</td>
						<td class="tit" width="10%">每床位保险费</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="bedPremium"  htmlEscape="false" class="input-xlarge"/>
						</td>
						<td class="tit" width="10%">医疗机构保险费</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="medicalPremium" htmlEscape="false" class="input-xlarge "/>
						</td>
					</tr>
					<tr>
						<td rowspan="6" style="text-align: center">医务人员<br>保险费</td>
						<td class="tit" colspan="2" width="10%">
							全部医务人员投保入口<br>(请提供全部人员名单)
							<input path="allWay" type="checkbox" name="mingdan" checked style="height: 20px;width: 20px;" />
						</td>
						<td class="tit" colspan="2" width="10%">
							选择科室投保入口<br>(请提供全部人员名单)
							<input path="selWay" type="checkbox" name="mingdan" style="height: 20px;width: 20px;" id="sel" onclick="selmingdan()" />
							<span id="tan" style="color: red"></span>
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%"></td>
						<td colspan="1" style="font-size: 16px;text-align: center">

						</td>
						<td class="tit" width="10%">每人基本保险费：</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="selEveryonePremium" disabled="true" htmlEscape="false" class="input-xlarge "/>
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%">医务人员人数：</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="allMedicalNumber" onblur="NumberBlur()" onchange="allNumber(this.value,'allPremiumTotal')" onkeyup="value=value.replace(/[^\d]/g,'')" htmlEscape="false" maxlength="4" class="input-xlarge "/>
						</td>
						<td class="tit" width="10%">临床手术科室人数：</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="selClinicOperationNumber" disabled="true" onblur="NumberBlur()" onkeyup="value=value.replace(/[^\d]/g,'')" htmlEscape="false" maxlength="4" class="input-xlarge "/>
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%">每人基本保险费：</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="allEveryonePremium" htmlEscape="false" class="input-xlarge "/>
						</td>
						<td class="tit" width="10%">临床非手术科室人数：</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="selClinicNotoperationNumber" onblur="NumberBlur()" disabled="true" onkeyup="value=value.replace(/[^\d]/g,'')" htmlEscape="false" maxlength="4" class="input-xlarge "/>
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%">保险费合计：</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="allPremiumTotal" htmlEscape="false" class="input-xlarge "/>
						</td>
						<td class="tit" width="10%">医技科室人数：</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="selMedicalLaboratoryNumber" onblur="NumberBlur()" disabled="true" onkeyup="value=value.replace(/[^\d]/g,'')" htmlEscape="false" maxlength="4" class="input-xlarge "/>
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%"></td>
						<td colspan="1" style="font-size: 16px;text-align: center">
						</td>
						<td class="tit" width="10%">保险费合计：</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="selPremiumTotal" disabled="true" htmlEscape="false" onkeyup="value=value.replace(/[^\d]/g,'')" class="input-xlarge "/>
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%">基本保险费总计</td>
						<td colspan="2" align="right" style="font-size: 16px;">
							<form:input path="basicPremiumTotal" htmlEscape="false" class="input-xlarge "/>
							元
						</td>
					</tr>
					<tr>
						<td colspan="5" style="text-align: center;height: 50px;font-size: 20px;font-weight: bold">
							保险费调整
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%">增加/减少赔偿限额后的<br>保险费调整系数</td>
						<td class="tit" width="10%">提高免赔额后的<br>保险费调整系数</td>
						<td class="tit" width="10%">风险浮动系数</td>
						<td class="tit" width="10%">调整后的保险费</td>
					</tr>
					<tr>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="addReduce" htmlEscape="false" class="input-xlarge required"/>
						</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="enchanceDeduction" htmlEscape="false" class="input-xlarge required"/>
						</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="riskFloat" htmlEscape="false" class="input-xlarge required"/>
						</td>
						<td colspan="1" style="font-size: 16px;">
							<form:input path="asleftPremium" htmlEscape="false" class="input-xlarge "/>
								<%--<b>元</b>--%>
						</td>
					</tr>
					<tr id="tr">
						<td class="tit" width="10%">附加保险费用</td>
						<td colspan="2" style="font-size: 16px;">
							<form:select path="addittionRisk" class="input-xlarge " onchange="addRisk()">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('additional_risk')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
							（小写）
							<form:input path="addittionPremium" htmlEscape="false" class="input-xlarge " cssStyle="width: 100px"/>
							元
						</td>
						<td>
							<input type="button" value="添加" onclick="addRow()">
								<%--<input type="button" value="删除" onclick="delRow(index)">--%>
							<input type="button" value="删除" onclick="delRow(this.parent().parent().id.substring(0,this.parent().parent().id.indexOf(r)))">
						</td>
							<%--<td>--%>
							<%--<input type="button" >--%>
							<%--</td>--%>
					</tr>
					<tr>
						<td colspan="5" style="text-align: center;height: 50px;font-size: 20px;font-weight: bold">
							实收保险费计算
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%">计算公式</td>
						<td colspan="3" style="font-size: 16px;">
							<form:input path="computationalFormula" htmlEscape="false" maxlength="64" class="input-xlarge "/>
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%">实收保险费</td>
						<td colspan="3" style="font-size: 16px;">
							（小写）<form:input path="oddicialReceiptsPermium" htmlEscape="false" class="input-xlarge " cssStyle="width: 100px"/>
							元
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%">保险期间</td>
						<td colspan="3" style="font-size: 16px;">
							<form:input path="insuranceDate" htmlEscape="false" maxlength="4" class="input-xlarge " cssStyle="width: 30px"/>
							个月,自
							<input name="insuranceStartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
								   value="<fmt:formatDate value="${insuranceSlip.insuranceStartTime}" pattern="yyyy-MM-dd"/>"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"
								   onchange="bxStart(this.value)"/>
							零时起，至
							<input name="insuranceEndTime" id="insuranceEndTimeID" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
								   value="<fmt:formatDate value="${insuranceSlip.insuranceEndTime}" pattern="yyyy-MM-dd"/>"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
							二十四时止。
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%">追溯期</td>
						<td colspan="3" style="font-size: 16px;">
							<form:input path="retroactiveDate" htmlEscape="false" maxlength="4" class="input-xlarge " cssStyle="width: 30px"/>
							个月,自
							<input name="retroactiveStratDate" onchange="zsqStart(this.value)" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
								   value="<fmt:formatDate value="${insuranceSlip.retroactiveStratDate}" pattern="yyyy-MM-dd "/>"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
							零时起，追溯至
							<input name="retroactiveEndDate" id="retroactiveEndDateID" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
								   value="<fmt:formatDate value="${insuranceSlip.retroactiveEndDate}" pattern="yyyy-MM-dd"/>"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
							二十四时止。
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%">保险费交付日期</td>
						<td colspan="3" style="font-size: 16px;">
							<input name="premiumDeliceryTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
								   value="<fmt:formatDate value="${insuranceSlip.premiumDeliceryTime}" pattern="yyyy-MM-dd"/>"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%">司法管辖</td>
						<td colspan="3" style="font-size: 16px;">
							中华人民共和国
						</td>
					</tr>
					<tr>
						<td rowspan="1" class="tit" width="10%">保险合同争议<br>处理方式选择</td>
						<td>
							<div class="radiobox ">
								<input type="radio" id="dispute" checked="checked" name="chuliType" style="width: 20px;height: 20px;" onchange="tijiao()"/>
								<span>提交</span>
								<form:input path="arbitrator" htmlEscape="false" class="input-xlarge " style="width: 50px" disabled="true"/>仲裁委员会仲裁；<br>
								<input type="radio" id="dispute" checked="checked" name="chuliType" style="width:20px;height: 20px;" onchange="shusong()"/>
								<span>诉讼</span>
							</div>
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%">以往投保情况<br>(如填写不下，<br>请另附纸说明)</td>
						<td colspan="3" style="font-size: 16px;">
							<form:textarea path="insureConditions" htmlEscape="false" class="input-xlarge" style="margin: 0px;width: 99%;font-size: 16px;" rows="5"/>
						</td>
					</tr>
					<tr>
						<td class="tit" width="10%">以往事故情况<br>(如填写不下，<br>请另附纸说明)</td>
						<td colspan="3" style="font-size: 16px;">
							<form:textarea path="accidentConditions" htmlEscape="false" class="input-xlarge" style="margin: 0px;width: 99%;font-size: 16px;" rows="5"/>
						</td>
					</tr>
					<tr>
						<td class="tit" width="5%">特别约定</td>
						<td colspan="3" style="font-size: 16px;">
							<form:textarea path="specialAgreement" htmlEscape="false" class="input-xlarge" style="margin: 0px;width: 99%;font-size: 16px;" rows="3"/>
						</td>
					</tr>
					<tr style="border-bottom: 0px">
						<td  colspan="4" style="font-size: 16px; height: 200px">
							<p>
								<b>投保人声明:</b> 上述所填内容属实；保险人已将《医疗责任保险条款》（包括责任免除内容向投保人作了明确说明；投保人对《医疗责任保险条款》（包括责任免除内容）和保险人的说明已经了解。
							</p>
						</td>

					</tr>
					<tr style="border-top: 0px">
						<td colspan="4" align="right" style=" height: 50px">
							投保人(签盖)
							<form:input path="signature" htmlEscape="false" maxlength="32" class="input-xlarge" cssStyle="width:80px"/>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="right" style=" height: 50px">
							年 月 日
							<input name="insureDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
								   value="<fmt:formatDate value="${insuranceSlip.insureDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
						</td>
					</tr>
					<tr>
						<td class="tit">承包性质：</td>
						<td>
							<input type="radio" id="xinbao" onclick="newBao()" checked="checked" name="cbType"  style="width: 20px;height: 20px;" />
							<span>新保</span>
							<input type="radio" id="xubao" checked="checked" name="cbType" style="width:20px;height: 20px;"/>
							<span>续保</span>
						</td>
						<td class="tit">
							业务员/代理人代码：
							<form:input path="agencyCode" htmlEscape="false" maxlength="32" class="input-xlarge" cssStyle="width:150px"/>
						</td>
						<td class="tit">
							业务员/代理人姓名：
							<form:input path="agencyName" htmlEscape="false" maxlength="32" class="input-xlarge" cssStyle="width:80px"/>
						</td>
					</tr>
				</table>
			</div>
			<div class="tab-pane fade" id="annex">
				<table style="height: 100px;" class="table-form">
					<tr>
						<td style="text-align: center; width: 30px; font-weight: bolder;height: 50px;">医责险花名册：</td>
						<input type="hidden" name="fjtype1" value="2">
						<td style="width: 45px;">
							<%--<input id="btnImport" onclick="daoru()" class="btn btn-primary" type="button" value="导入"/>--%>
							<input type="hidden" id="files1" name="files1" htmlEscape="false" class="input-xlarge"
								   value="${files1}"/>
							<input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
							<div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files"
																		  uploadPath="/InsuranceSlip/Patient/apply" selectMultiple="true"/>
							</div>
							<a href="${ctx}/insuranceslip/insuranceSlip/import/template">下载模板</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="insuranceslip:insuranceSlip:edit"><input id="btnSubmit" class="btn btn-primary"
																			type="submit" value="保 存"
																			onclick="$('#flag').val('no'),removeCssClass(),$('#start').val('0')"/>&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="insuranceslip:insuranceSlip:edit"><input id="btnSubmit1" class="btn btn-primary"
																			type="submit" value="提交审核"
																			onclick="$('#flag').val('yes'),$('#start').val('1')"/>&nbsp;</shiro:hasPermission>
			<c:if test="${start eq '1' }">
				<input id="btnSubmit" class="btn btn-success" type="submit" value="通 过" onclick="$('#start').val('2')"/>&nbsp;
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/insuranceslip/insuranceSlip/import" method="post" enctype="multipart/form-data"
			  class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" onclick="daoru02" type="submit" value="   导    入   "/>
			<a href="${ctx}/insuranceslip/insuranceSlip/import/template">下载模板</a>
		</form>
	</div>
</body>
</html>