<%@page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>投诉接待管理详情</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
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
            
            if (value == 2){
                document.getElementById("badj").style.display="block";
            }else {
                document.getElementById("badj").style.display="none";
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
    </script>
    <%--报案登记--%>
    <script type="text/javascript">
        $(document).ready(function () {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    var aa=$("#export").val();
                    getRepeat(aa,form);
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
            var a=$("#jiufen").val();
            var b=$("#chuxian").val();
            var oDate1 = new Date(a);
            var oDate2 = new Date(b);
            if(oDate1 !=null && oDate2 !=null){
                if(oDate1.getTime() < oDate2.getTime()){
                    top.$.jBox.tip("纠纷发生时间应大于出险时间，请重新设置两个时间");
                    $("#jiufen").val("");
                    $("#chuxian").val("");
                }
            }

        }

        function exportWord() {
            var aa=$("#export").val();
            var path="${ctx}/registration/reportRegistration/pass";
            $.post(path,{'reportRegistrationId':'${reportRegistration.reportRegistrationId}','export':aa,"print":"true"},function(res){
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
            var hospital=$("#complaintMain\\.involveHospital").val();
            var card=$("#complaintMain\\.patientCard").val();
            var complaintMainId=$("#complaintMainId").val();
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
                $("#complaintMain\\.patientAge").val(age);
            }
            return age;
        }

        function removeCssClass() {
            $('#disputeTime').removeClass('required');
            $('#nextLinkManName').removeClass('required');
            $('#reportEmp').removeClass('required');
            $('#complaintMain\\.patientCard').removeClass('required');
            $('#reportTime').removeClass('required');
            $('#patientMobile').removeClass('required');
            $('#doctorMobile').removeClass('required');
            $('#summaryOfDisputes').removeClass('required');
            $('#focus').removeClass('required');
            $('#patientAsk').removeClass('required');
            $('#registrationEmpName').removeClass('required');
            $('#registrationTime').removeClass('required');
        }
        function addCssClass() {
            $('#disputeTime').addClass('required');
            $('#nextLinkManName').addClass('required');
            $('#reportEmp').addClass('required');
            $('#complaintMain\\.patientCard').addClass('required');
            $('#reportTime').addClass('required');
            ('#patientMobile').addClass('required');
            $('#doctorMobile').addClass('required');
            $('#summaryOfDisputes').addClass('required');
            $('#focus').addClass('required');
            $('#patientAsk').addClass('required');
            $('#registrationEmpName').addClass('required');
            $('#registrationTime').addClass('required');
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

        function involveHospitalTreeselectCallBack(v, h, f){
            $("#hospitalName").text($("#involveHospitalName").val());
        }

        function involveDepartmentTreeselectCallBack(v, h, f){
            $("#keshi").text($("#involveDepartmentName").val());
        }
        //访客姓名 == 报案人
        function visitorNameTreeselectCallBack() {
            $("#visitorName2").text($("#visitorName").val());
        }
        //患者 == 患者
        function patientNameTreeselectCallBack() {
            $("#patientName2").text($("#patientName").val());
        }

        //患者性别
        function patientSexCallBack() {
            $("#patientSex2").text($("#patientSex").val())
            // var sex = $("#patientSex").val();
            // if (sex == 1){
            //     $("#patientSex2").text("男");
            // }else {
            //     $("#patientSex2").text("女");
            // }

        }
        //患者年龄
        function patientAgeCallBack() {
            $("#age").text($("#patientAge").val());
        }
        //访客电话 == 患方（联系电话）
        function visitorMobileCallBack() {
            $("#reportRegistration.patientMobile").text($("#visitorMobile").val());
        }

        //投诉纠纷概要 == 纠纷概要
        function summaryOfDisputesCallBack() {
            $("#summaryOfDisputes2").text($("#summaryOfDisputes").val());
        }

        // 诉求 ==  患方要求
        function appealCallBack() {
            $("#patientAsk").text($("#appeal").val());
        }
        //案件编号 == 报案号
        function caseNumberCallBack() {
            $("#caseNumber2").text($("#caseNumber").val());
        }

        //所属城市
        function area() {
            $("#area").text()
        }
    </script>
</head>
<body>
<form:form class="form-horizontal" id="inputForm" modelAttribute="complaintInfo" action="${ctx}/complaint/complaintInfo/audit?node=${node}" method="post">
    <input type="hidden" id="flag" name="flag"/>
    <input type="hidden" id="status" name="status"/>
    <form:hidden path="complaintMainId"/>
    <form:hidden path="complaintMain.complaintMainId"/>
    <form:hidden path="complaintMain.act.taskId"/>
    <form:hidden path="complaintMain.act.taskName"/>
    <form:hidden path="complaintMain.act.taskDefKey"/>
    <form:hidden path="complaintMain.procInsId"/>
    <form:hidden path="complaintMain.act.procDefId"/>
    <form:hidden path="createBy.id" />
    <form:hidden path="createBy.loginName" />
    <form:hidden path="reportRegistration.reportRegistrationId" />
    <sys:message content="${message}"/>
<br>
    <legend>医院投诉接待详情</legend>
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
                <tr>
                    <td class="tit" width="160px"><font color="red">*</font>访客姓名：</td>
                    <td width="476px">
                            ${complaintInfo.visitorName}
                    </td>
                    <td class="tit" width="180px">访客电话：</td>
                    <td>
                            ${complaintInfo.visitorMobile}
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>与患者关系：</td>
                    <td>
                        ${fns:getDictLabel(complaintInfo.patientRelation,'patient_relation','未知')}
                    </td>
                    <td class="tit"><font color="red">*</font>来访人数：</td>
                    <td>
                            ${complaintInfo.visitorNumber}
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="patient">
            <table class="table-form">
                <tr>
                    <td class="tit" width="160px"><font color="red">*</font>患者姓名：</td>
                    <td width="476px">
                            ${complaintInfo.patientName}
                    </td>
                    <td class="tit" width="180px"><font color="red">*</font>患者性别：</td>
                    <td id="patientSex">
                            <%--<form:input path="patientSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
                        ${fns:getDictLabel(complaintInfo.patientSex,'sex','未知')}
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>患者年龄：</td>
                    <td>
                            ${complaintInfo.patientAge}
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="hospital">
            <table class="table-form">
                <tr>
                    <td class="tit" width="160px"><font color="red">*</font>涉及医院：</td>
                    <td width="476px">
                        ${complaintInfo.hospitalName}
                    </td>
                    <td class="tit" width="180px"><font color="red">*</font>涉及科室：</td>
                    <td>
                            <%--<sys:treeselect id='involveDepartment' name='involveDepartment' value='${complaintInfo.involveDepartment}' labelName='departmentName' labelValue='${complaintInfo.departmentName}' title='部门' url='/sys/office/treeData?type=2&officeType=2'--%>
                            <%--pid='involveHospital' isAll='true' cssClass='required' dataMsgRequired='请选择科室' allowClear='true' notAllowSelectParent='true' disabled='true'/>--%>
                                    ${complaintInfo.testTree}
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>涉及人员：</td>
                    <td>
                        ${empty complaintInfo.employeeName?complaintInfo.involveEmployee:complaintInfo.employeeName}
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
                    <td colspan="3" style="text-align: center">
                        <%--<form:input path="reportRegistration.policyNumber" htmlEscape="false" maxlength="255" class="input-small required" value="${empty complaintInfo.reportRegistration.policyNumber ? (empty reportRegistration.complaintMain.hospital.policyNumber ? '未参保' : reportRegistration.complaintMain.hospital.policyNumber) : reportRegistration.policyNumber}" cssStyle="width: 87%;height: 30px; text-align: center;" />--%>
                                ${empty complaintInfo.reportRegistration.policyNumber ? (empty complaintInfo.reportRegistration.complaintMain.hospital.policyNumber ? '未参保' : complaintInfo.reportRegistration.complaintMain.hospital.policyNumber) : complaintInfo.reportRegistration.policyNumber}
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>纠纷发生时间:</td>
                    <td width="15%">
                        <input id="disputeTime" name="reportRegistration.disputeTime" type="text" readonly="readonly" maxlength="20"
                               class="input-small Wdate required" disabled="disabled"
                               value="${complaintInfo.reportRegistration.disputeTime}"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width:90%;height:30px;text-align: center;" id="jiufen" onchange="compareDate(this.value)"/>
                    </td>
                    <td class="tit" width="7%">机构等级:</td>
                    <td>
                        <form:select path="complaintMain.hospitalGrade" disabled="true" cssStyle="width: 80%; text-align: center;">
                            <form:options items="${fns:getDictList('hospital_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                        </form:select>
                    </td>
                    <td class="tit">所属城市:</td>
                    <td width="10%" style="text-align: center;" id="area">
                            ${fns:getOfficeId(complaintInfo.complaintMain.involveHospital).area.name}
                    </td>
                    <td class="tit" width="7%"><font color="red">*</font>报案人姓名:</td>
                    <td id="visitorName2" style="text-align: center">
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
                        <form:input path="complaintMain.patientCard" htmlEscape="false" maxlength="20" onchange="GetAge(this.value);" class="input-xlarge required card" cssStyle="width: 90%;height: 30px;text-align: center;" readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>出险时间:</td>
                    <td>
                        <input id="reportTime" name="reportRegistration.reportTime" type="text" readonly="readonly" maxlength="20"
                               class="input-small Wdate required" disabled="disabled"
                               value="${complaintInfo.reportRegistration.reportTime}"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width:90%;height:30px;text-align: center;" id="chuxian" onchange="compareDate(this.value)"/>
                    </td>
                    <td class="tit">科室:</td>
                    <td id="keshi" style="text-align: center">
                            ${complaintInfo.testTree}
                    </td>
                    <td class="tit">出险医生:</td>
                    <td style="text-align: center;">
                            ${complaintInfo.involveEmployee}
                    </td>
                    <td class="tit">是否使用医责险:</td>
                    <td style="text-align: center;">
                        <form:select path="reportRegistration.doctorRisk" disabled="true" style='width:110px;text-align: center;'>
                            <form:option value="0">否</form:option>
                            <form:option value="1">是</form:option>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        联系电话:
                    </td>
                    <td colspan="3">
                        <p style="margin:0pt; orphans:0; widows:0"><font color="red">*</font>
                            <span style="font-family:宋体; font-size:12pt; font-weight:bold">患方：</span>
                            <span style="font-family:宋体; font-size:12pt; font-weight:bold;text-align: center">
                                            ${complaintInfo.reportRegistration.patientMobile}
										<%--<form:input path="reportRegistration.patientMobile"  htmlEscape="false" maxlength="15" class="input-xlarge required phone" cssStyle="width: 50%;height: 30px; font-size: 16px;"/>--%>
								</span>
                        </p>
                    </td>
                    <td colspan="4">
                        <p style="margin:0pt; orphans:0; widows:0"><font color="red">*</font><span
                                style="font-family:宋体; font-size:12pt; font-weight:bold">医方：</span>
                            <span style="font-family:宋体; font-size:12pt; font-weight:bold;text-align: center">
                                 ${complaintInfo.reportRegistration.doctorMobile}
                            <%--<form:input path="reportRegistration.doctorMobile" htmlEscape="false" maxlength="15"--%>
                                        <%--class="input-xlarge required phone" cssStyle="width: 42%;height: 30px;font-size: 16px;"--%>
                                        <%--/>--%>
                        </span>
                        </p></td>
                </tr>
                <tr >
                    <td class="tit">
                        <font color="red">*</font>纠纷概要:
                    </td>
                    <td colspan="7" id="summaryOfDisputes2">
                        <input type="hidden" name="reportRegistration.summaryOfDisputes" value="${complaintInfo.summaryOfDisputes}">
                            <%--<form:textarea path="reportRegistration.summaryOfDisputes" id="summaryOfDisputes2" htmlEscape="false" class="input-xlarge required" style="margin: 0px;width: 99%;font-size: 16px;" rows="5" />--%>
                            ${complaintInfo.summaryOfDisputes}
                            <%--<form:textarea path="summaryOfDisputes" htmlEscape="false" class="input-xlarge required"
                            style="margin: 0px;width: 99%;font-size: 16px;" rows="15"/>--%>
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        <font color="red">*</font>纠纷焦点:
                    </td>
                    <td  colspan="7">
                        ${complaintInfo.reportRegistration.focus}
                        <%--<form:textarea path="reportRegistration.focus" htmlEscape="false" class="input-xlarge required" style="margin: 0px;width: 99%;font-size: 16px;" rows="5" />--%>
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        <font color="red">*</font>患方要求:
                    </td>
                    <td  colspan="7">
                        ${complaintInfo.reportRegistration.patientAsk}
                        <%--<form:textarea path="reportRegistration.patientAsk" htmlEscape="false" class="input-xlarge required" style="margin: 0px;width: 99%;font-size: 16px;" rows="2"/>--%>
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>填表人签名:</td>
                    <td colspan="3">
                            ${fns:getUserById(complaintInfo.reportRegistration.registrationEmp).name }
                    </td>
                    <td class="tit"><font color="red">*</font>填表日期:</td>
                    <td colspan="3">
                        <input id="registrationTime" name="reportRegistration.registrationTime" type="text" readonly="readonly" maxlength="20"
                               class="input-medium Wdate required" disabled="disabled"
                               value="${complaintInfo.reportRegistration.registrationTime}"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" style="width:40%;height:30px;text-align: center;"/>
                    </td>
                    <>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>报案号:</td>
                    <td colspan="3" id="caseNumber2">
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
                               class="input-medium Wdate " disabled="disabled"
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
                                                                      uploadPath="/complaint/Patient/apply" selectMultiple="true" readonly="true"/></div>
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
                                                                                         uploadPath="/complaint/Patient/apply" selectMultiple="true" readonly="true"/></div>
                    </td>
                </tr>
            </table>

        </div>
    </div>
    <table class="table-form" id="zhu">
        <tr>
            <td class="tit" width="160px"><font color="red">*</font>案件编号：</td>
            <td width="476px">
                    ${complaintInfo.caseNumber}
            </td>
            <td class="tit" width="180px"><font color="red">*</font>来访日期：</td>
            <td>
                    <%--<form:input path="visitorDate" htmlEscape="false" maxlength="10" class="input-xlarge "/>--%>
                    ${complaintInfo.visitorDate}
            </td>
        </tr>
        <tr>
            <td class="tit"><font color="red">*</font>投诉方式：</td>
            <td>
                    <%--<form:input path="complaintMode" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
                <c:choose>
                    <c:when test="${complaintInfo.complaintMode == '0'}">
                        来电
                    </c:when>
                    <c:when test="${complaintInfo.complaintMode == '1'}">
                        来访
                    </c:when>
                    <c:when test="${complaintInfo.complaintMode == '2'}">
                        来信
                    </c:when>
                    <c:when test="${complaintInfo.complaintMode == '3'}">
                        其他
                    </c:when>
                </c:choose>
            </td>
            <td class="tit"><font color="red">*</font>是否重大：</td>
            <td>
                <c:choose>
                    <c:when test="${complaintInfo.isMajor == '0'}">
                        否
                    </c:when>
                    <c:when test="${complaintInfo.isMajor == '1'}">
                        是
                    </c:when>
                </c:choose>
            </td>

        </tr>
        <tr>
            <td class="tit">投诉类别：</td>
            <td>
                ${complaintInfo.typeName}
            </td>
        </tr>
        <tr>
            <td class="tit"><font color="red">*</font>投诉纠纷概要：</td>
            <td colspan="3">
                    ${complaintInfo.summaryOfDisputes}
            </td>
        </tr>
        <tr>
            <td class="tit"><font color="red">*</font>诉求：</td>
            <td colspan="3">
                    ${complaintInfo.appeal}
            </td>
        </tr>
        <tr>
            <td class="tit">处理方式：</td>
            <td id="handleWay">
                <c:choose>
                    <c:when test="${complaintInfo.handleWay == '0'}">
                        当面处理
                    </c:when>
                    <c:when test="${complaintInfo.handleWay == '1'}">
                        转办处理
                    </c:when>
                    <c:when test="${complaintInfo.handleWay == '2'}">
                        转医调委
                    </c:when>
                    <c:when test="${complaintInfo.handleWay == '3'}">
                        法院诉讼
                    </c:when>
                    <c:when test="${complaintInfo.handleWay == '4'}">
                        行政调解
                    </c:when>
                </c:choose>
            </td>
            <td id="shiftHandle">
                            ${fns:getDictLabel(complaintInfo.shiftHandle, 'department','' )}
            </td>

            <td id="statusHandle">
                <c:choose>
                    <c:when test="${complaintInfo.status == '0'}">
                        处理中
                    </c:when>
                    <c:when test="${complaintInfo.status == '1'}">
                        协调中
                    </c:when>
                    <c:when test="${complaintInfo.status == '2'}">
                        结案
                    </c:when>
                </c:choose>
            </td>


        </tr>
        <c:if test="${complaintInfo.handleWay != '2'}"><tr>
            <td class="tit"><font color="red">*</font>处理经过</td>
            <td colspan="3">
                    ${complaintInfo.handlePass}
            </td>
        </tr>
            <tr>
                <td class="tit"><font color="red">*</font>处理结果</td>
                <td colspan="3">
                        ${complaintInfo.handleResult}
                </td>
            </tr>
        </c:if>
        <tr>
            <td class="tit"><font color="red">*</font>接待人员：</td>
            <td>
                    <%--<form:input path="receptionEmployee" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
                ${complaintInfo.employee.name}
            </td>

            <td class="tit"><font color="red">*</font>接待时间：</td>
            <td>
                    <%--<form:input path="receptionDate" htmlEscape="false" maxlength="20" class="input-xlarge "/>--%>
                ${complaintInfo.receptionDate}
            </td>
        </tr>

       <%-- <tr>
                &lt;%&ndash;<td class="tit"><font color="red">*</font>是否进入医调委调解：</td>&ndash;%&gt;
                &lt;%&ndash;<td>&ndash;%&gt;
                &lt;%&ndash;<form:select id="isMediate" path="isMediate" style='width:110px;text-align: center;' >&ndash;%&gt;
                &lt;%&ndash;<form:option value="0">否</form:option>&ndash;%&gt;
                &lt;%&ndash;<form:option value="1">是</form:option>&ndash;%&gt;
                &lt;%&ndash;</form:select>&ndash;%&gt;
                &lt;%&ndash;</td>&ndash;%&gt;
                &lt;%&ndash;<td class="hidden"><font color="red">*</font>下一处理环节：</td>&ndash;%&gt;
                &lt;%&ndash;<td class="hidden">&ndash;%&gt;
                &lt;%&ndash;<form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge "/>&ndash;%&gt;
                &lt;%&ndash;</td>&ndash;%&gt;
            <td class="tit"><font color="red">*</font>下一环节处理人：</td>
            <td>
                    &lt;%&ndash;<form:input path="nextLinkMan" htmlEscape="false" maxlength="32" class="input-xlarge "/>&ndash;%&gt;
                ${complaintInfo.reportRegistration.linkEmployee.name}
            </td>
        </tr>--%>
    </table>
</br>
    <table class="table-form">
        <c:if test="${node eq 'sjy' }">
            <div class="control-group">
                <label class="control-label">回复内容:</label>
                <div class="controls">
                    <form:textarea path="complaintMain.act.comment" htmlEscape="false" rows="4" maxlength="200" class="required input-xxlarge"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">分配员:</label>
                <div class="controls">
                    <sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${complaintInfo.nextLinkMan}" labelName="link.name" labelValue="${complaintInfo.nextLinkMan}"
                                    title="用户" url="/sys/office/treeData?type=2&officeType=1" isAll="true" role="anjianfenpeiyuan" cssClass="required" dataMsgRequired="请选择下一环节处理人" allowClear="true" notAllowSelectParent="true"/>
                </div>
            </div>
        </c:if>
        <c:if test="${node eq 'fpy' }">
            <div class="control-group">
                <label class="control-label">调解员:</label>
                <div class="controls">
                    <sys:treeselect id="nextLinkMan" name="reportRegistration.nextLinkMan" value="${complaintInfo.reportRegistration.nextLinkMan}" labelName="link.name" labelValue="${complaintInfo.reportRegistration.nextLinkMan}"
                                    title="用户" url="/sys/office/treeData?type=3&officeType=1" isAll="true"  cssClass="required" dataMsgRequired="请选择下一环节处理人" allowClear="true" notAllowSelectParent="true"/>
                </div>
            </div>
        </c:if>


    </table>
</fieldset>

    <c:if test="${empty show2}">
        <div class="form-actions">
            <c:if test="${node eq 'sjy' }">
                <input id="btnSubmit" class="btn btn-success" type="submit" value="通 过" onclick="$('#status').val('0');addCssClass()"/>&nbsp;
                <input id="btnSubmit" class="btn btn-inverse" type="submit" value="驳 回" onclick="$('#status').val('1');removeCssClass()"/>&nbsp;
            </c:if>
            <c:if test="${node eq 'fpy' }">
                <input id="btnSubmit" class="btn btn-success" type="submit" value="通 过" onclick="$('#status').val('0')"/>&nbsp;
            </c:if>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
    </c:if>
    <c:if test="${not empty complaintInfo.complaintMain.procInsId}">
        <act:histoicFlow procInsId="${complaintInfo.complaintMain.procInsId}"/>
    </c:if>
</form:form>
</body>
</html>
