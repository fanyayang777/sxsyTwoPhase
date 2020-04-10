<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>质证调解管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    var aa=$("#export").val();
                    var flag=$("#flag").val();
                    var tip=$("#nextLink").val()=='0' ? '评估鉴定' :'签署协议';
                    if(flag=='yes'){//点击 下一步的 时候
                        top.$.jBox.confirm("您下一处理环节是    “"+tip+"”    ，您确认要提交吗？","系统提示",function(v,h,f){
                            if(v=="ok"){
                                if(aa=='no'){
                                    loading('正在提交，请稍等...');
                                }
                                form.submit();
                            }
                        },{buttonsFocus:1, closed:function(){

                        }});
                    }else{//点击保存的时候
                        if(aa=='no'){
                            loading('正在提交，请稍等...');
                        }
                        form.submit();
                    }
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
            removeCssClass();
            changeNext();
        });

        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }

        function addRow(list, idx, tpl, row) {
            $(list).append(Mustache.render(tpl, {
                idx: idx, delBtn: true, row: row
            }));
            $(list + idx).find("select").each(function () {
                $(this).val($(this).attr("data-value"));
            });
            $(list + idx).find("input[type='checkbox'], input[type='radio']").each(function () {
                var ss = $(this).attr("data-value").split(',');
                for (var i = 0; i < ss.length; i++) {
                    if ($(this).val() == ss[i]) {
                        $(this).attr("checked", "checked");
                    }
                }
            });
        }

        function delRow(obj, prefix, key) {
            var id = $(prefix + key);
            var delFlag = $(prefix + "_delFlag");
            if (id.val() == "") {
                $(obj).parent().parent().remove();
            } else if (delFlag.val() == "0") {
                delFlag.val("1");
                $(obj).html("&divide;").attr("title", "撤销删除");
                $(obj).parent().parent().addClass("error");
            } else if (delFlag.val() == "1") {
                delFlag.val("0");
                $(obj).html("&times;").attr("title", "删除");
                $(obj).parent().parent().removeClass("error");
            }
        }

        function synValue(val, id) {
            $("#" + id).text(val);
        }

        function removeCssClass() {
            if ($("#meetingTime").hasClass("required") == true) {
                $("#meetingTime").removeClass("required");
            }
            if ($("#meetingAddress").hasClass("required") == true) {
                $("#meetingAddress").removeClass("required");
            }
            if ($("#userIdName").hasClass("required") == true) {
                $("#userIdName").removeClass("required");
            }
            if ($("#clerkName").hasClass("required") == true) {
                $("#clerkName").removeClass("required");
            }
            if ($("#doctorName").hasClass("required") == true) {
                $("#doctorName").removeClass("required");
            }
            if ($("#patient").hasClass("required") == true) {
                $("#patient").removeClass("required");
            }
            if ($("#other").hasClass("required") == true) {
                $("#other").removeClass("required");
            }
        }
        function exportWord(txt) {
            var aa=$("#export").val();
            var path="${ctx}/mediate/mediateEvidence/pass";
            $.post(path,{'mediateEvidenceId':'${mediateEvidence.mediateEvidenceId}','export':aa,"print":"true",'nums':txt},function(res){
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

        var intN=0;
        function changeNext() {
            var next=$("#nextLink").val();
            if(next==0){
                //省直人员
                $("#aaName").hide();
                $("#aaButton").hide();

                $("#nextLinkManName").show();
                $("#nextLinkManButton").show();
                //签署协议 状态下  为空
                if(intN>0){
                    $("#aaId").val("");
                    $("#aaName").val("");
                    $("#nextLinkManId").val("");
                    $("#nextLinkManName").val("");
                }


                $("#aaId").attr("name","cc");
                $("#nextLinkManId").attr("name","nextLinkMan");
            }else{
                //全部人员
                $("#nextLinkManName").hide();
                $("#aaName").show();
                $("#aaButton").show();
                $("#nextLinkManButton").hide();
                //默认自己
                if(intN>0){
                    $("#aaId").val("");
                    $("#aaName").val("");
                    $("#nextLinkManId").val("");
                    $("#nextLinkManName").val("");
                }
                $("#nextLinkManId").attr("name","cc");
                $("#aaId").attr("name","nextLinkMan");
            }
            intN++;
        }

        function removeCssClass() {
            $('#nextLinkManName').removeClass('required');
            $('#aaName').removeClass('required');
        }
        function addCssClass() {
            $('#nextLinkManName').addClass('required');
            $('#aaName').addClass('required');
        }

    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/mediate/mediateEvidence/">质证调解列表</a></li>
    <li class="active"><a href="${ctx}/mediate/mediateEvidence/form?id=${mediateEvidence.id}">质证调解<shiro:hasPermission
            name="mediate:mediateEvidence:edit">${not empty mediateEvidence.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="mediate:mediateEvidence:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="mediateEvidence" action="${ctx}/mediate/mediateEvidence/save" method="post"
           class="form-horizontal">
    <form:hidden path="mediateEvidenceId"/>
    <form:hidden path="createDate"/>
    <form:hidden path="createBy"/>
    <form:hidden path="complaintMainId"/>
    <form:hidden path="recordInfo.recordId"/>
    <form:hidden path="recordInfo.yrecordInfo.recordId"/>
    <form:hidden path="complaintMain.complaintMainId"/>
    <form:hidden path="complaintMain.act.taskId"/>
    <form:hidden path="complaintMain.act.taskName"/>
    <form:hidden path="complaintMain.act.taskDefKey"/>
    <form:hidden path="complaintMain.act.procInsId"/>
    <form:hidden path="complaintMain.act.procDefId"/>
    <form:hidden path="complaintMain.procInsId"/>
    <form:hidden id="flag" path="complaintMain.act.flag"/>
    <form:hidden path="doctor"/>
    <form:hidden path="doctorOffice.id"/>
    <form:hidden path="recordInfo.startTime"/>
    <form:hidden path="recordInfo.endTime"/>
    <form:hidden path="recordInfo.recordAddress"/>
    <form:hidden path="recordInfo.patient"/>
    <form:hidden path="recordInfo.yrecordInfo.startTime"/>
    <form:hidden path="recordInfo.yrecordInfo.endTime"/>
    <form:hidden path="recordInfo.yrecordInfo.recordAddress"/>
    <form:hidden path="recordInfo.yrecordInfo.patient"/>
    <form:hidden path="recordInfo.host"/>
    <form:hidden path="recordInfo.noteTaker"/>
    <form:hidden path="recordInfo.doctor"/>
    <form:hidden path="recordInfo.yrecordInfo.host"/>
    <form:hidden path="recordInfo.yrecordInfo.noteTaker"/>
    <form:hidden path="recordInfo.yrecordInfo.doctor"/>
    <form:hidden path="recordInfo.yrecordInfo.ytwHost.name"/>

    <input type="hidden" id="export" name="export"/>
    <sys:message content="${message}"/>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#mediation" data-toggle="tab">调解志</a>
        </li>
        <li>
            <a href="#meeting" data-toggle="tab">调解程序表</a>
        </li>
        <li>
            <a href="#recorded_patient" data-toggle="tab">调解会笔录（患方）</a>
        </li>
        <li>
            <a href="#recorded_doctor" data-toggle="tab">调解会笔录（医方）</a>
        </li>
        <li>
            <a href="#annex" data-toggle="tab">附件</a>
        </li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="mediation">
            <table id="contentTable" class="table table-striped table-bordered table-condensed">
                <thead>
                <tr>
                    <th class="hide"></th>
                    <th width="10">时间</th>
                    <th width="10">角色</th>
                    <th width="10">方式</th>
                    <th width="100">内容</th>
                    <th width="80">结果</th>
                    <shiro:hasPermission name="mediate:mediateEvidence:edit">
                        <th width="100">&nbsp;</th>
                    </shiro:hasPermission>
                </tr>
                </thead>
                <tbody id="mediateEvidenceList"></tbody>
                <shiro:hasPermission name="mediate:mediateEvidence:edit">
                    <tfoot>
                    <tr>
                        <td colspan="7"><a href="javascript:"
                                           onclick="addRow('#mediateEvidenceList', mediateEvidenceRowIdx, mediateEvidenceTpl);mediateEvidenceRowIdx = mediateEvidenceRowIdx + 1;"
                                           class="btn">新增</a></td>
                    </tr>
                    </tfoot>
                </shiro:hasPermission>
            </table>
            <script type="text/template" id="mediateEvidenceTpl">//<!--
						<tr id="mediateEvidenceList{{idx}}">
							<td class="hide">
								<input id="mediateEvidenceList{{idx}}_id" name="mediateEvidenceList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="mediateEvidenceList{{idx}}_mediateRecord" name="mediateEvidenceList[{{idx}}].mediateRecord" type="hidden" value="{{row.mediateRecord}}"/>
								<input id="mediateEvidenceList{{idx}}_relationId" name="mediateEvidenceList[{{idx}}].relationId" type="hidden" value="{{row.relationId}}"/>
								<input id="mediateEvidenceList{{idx}}_delFlag" name="mediateEvidenceList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td >
								<%--<input id="mediateEvidenceList{{idx}}_time" name="mediateEvidenceList[{{idx}}].time" type="text" value="{{row.time}}" maxlength="32" class="input-small "/>--%>
								<input id="mediateEvidenceList{{idx}}_time" name="mediateEvidenceList[{{idx}}].time" type="text"  maxlength="20" class="input-medium Wdate required" "
                                    value="{{row.time}}"
                                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
							</td>
							<td>
							    <select id="mediateEvidenceList{{idx}}_roleType" name="mediateEvidenceList[{{idx}}].roleType" value="{{row.roleType}}" data-value="{{row.roleType}}" class="input-mini">
									<option value=""></option>
									<option value="1"  >医方</option>
									<option value="2"  >患方</option>
									<option value="3"  >医患双方</option>
								</select>
							</td>
							<td>
							    <select id="mediateEvidenceList{{idx}}_way" name="mediateEvidenceList[{{idx}}].way" value="{{row.way}}" data-value="{{row.way}}" class="input-mini">
									<option value=""></option>
									<option value="1"  >电话沟通</option>
									<option value="2"  >单方调解</option>
									<option value="3"  >调解会</option>
									<option value="4"  >其他</option>
								</select>
							</td>
							<td>
								<input id="mediateEvidenceList{{idx}}_content" name="mediateEvidenceList[{{idx}}].content" type="text" value="{{row.content}}" maxlength="100" class="required" />
							</td>
							<td>
								<input id="mediateEvidenceList{{idx}}_result" name="mediateEvidenceList[{{idx}}].result" type="text" value="{{row.result}}" maxlength="32" class="required" />
							</td>
							<shiro:hasPermission name="mediate:mediateEvidence:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#mediateEvidenceList{{idx}}','_mediateRecord')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
            </script>
        </div>
        <div class="tab-pane fade" id="meeting">
            <table class="table-form">
                <%--<p style="margin:0pt; text-align:center">--%>
                    <%--<span style="color:#333333; font-family:宋体; font-size:15pt; font-weight: bolder;">山西省医疗纠纷人民调解委员会</span>--%>
                <%--<p style="margin:0 auto ;width: 270px;">--%>
                    <%--<span style="color:#333333; font-family:宋体; font-size:15pt; font-weight:bolder;">医疗纠纷调解会工作程序</span>--%>
                <%--</p>--%>
                <%--</p>--%>
                    <tr>
                        <td class="tit">时间：</td>
                        <td colspan="2" width="35%">
                            <input id="meetingTime" name="meetingTime" type="text" readonly="readonly" maxlength="20"
                                   class="input-medium Wdate required"
                                   value="${mediateEvidence.meetingTime}"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"
                                   onchange="changeClass()"/>
                            <font color="red">*如果不选择时间，保存无效!</font>
                        </td>
                        <td class="tit">地点：</td>
                        <td>
                            <form:select path="meetingAddress" class="input-xlarge" cssStyle="text-align:center;">
                                <form:options items="${fns:getDictList('meeting')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                            </form:select>
                            <%--<form:input id="meetingAddress" path="meetingAddress" htmlEscape="false" maxlength="20"
                                        class="input-xlarge required" value="${mediateEvidence.meetingAddress}"/>--%>
                        </td>
                    </tr>
                    <tr>
                        <td rowspan="4" class="tit"><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">一、介绍医调委、患方、医方的身份</span></td>
                        <td class="tit" width="10%">调解员:</td>
                        <td style="width: 150px;">
                            <sys:treeselect id="userId" name="userId"
                                            value="${empty mediateEvidence.userId ? fns:getUser().id :mediateEvidence.userId}" labelName="tjy"
                                            labelValue="${empty mediateEvidence.ytwUser.name ? fns:getUser().name :mediateEvidence.ytwUser.name}"
                                            title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass="required"
                                            dataMsgRequired="必填信息"
                                            allowClear="true" isAll="true" notAllowSelectParent="true" disabled="true"/>
                        </td>
                        <td class="tit">书记员：</td>
                        <td>
                            <sys:treeselect id="clerk" name="clerk"
                                            value="${empty mediateEvidence.clerk ? fns:getUser().id : mediateEvidence.clerk}" labelName="sjy"
                                            labelValue="${empty mediateEvidence.clerk ? fns:getUser().name : mediateEvidence.clerk}"
                                            title="用户" url="/sys/office/treeData?type=3&officeType=1"
                                            cssClass="required" dataMsgRequired="必填信息"
                                            allowClear="true" isAll="true" notAllowSelectParent="true" disabled="true"/>
                        </td>
                    </tr>
                    <tr></tr>
                    <tr>
                        <td class="tit">患方：</td>
                        <td colspan="3">
                            <span style=" font-family:宋体; font-size:12pt; font-weight:normal;">
                                <form:input id="patient" path="patient" htmlEscape="false" maxlength="20"
                                            class="input-xlarge" value="${empty mediateEvidence.patient?mediateEvidence.complaintMain.patientName:mediateEvidence.complaintMain.patientName}"/>
                                    <%--${mediateEvidence.complaintMain.patientName}--%>
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <td class="tit">医方：</td>
                        <td colspan="3">
                            <span style=" font-family:宋体; font-size:12pt; font-weight:normal;">
                                <form:input id="doctorOffice.name" path="doctorOffice.name" htmlEscape="false" maxlength="20"
                                            class="input-xlarge" />
                                    <%--${empty mediateEvidence.doctorOffice.name?mediateEvidence.complaintMain.hospital.name:mediateEvidence.doctorOffice.name}--%>
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <td rowspan="2" class="tit"><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">二、医患双方确认以上参会人员身份有无要求回避</span></td>
                        <td class="tit">患方：</td>
                        <td colspan="3">
                            <form:input id="patientAvoid" path="patientAvoid" htmlEscape="false" maxlength="20"
                                        class="input-xlarge " value="${empty mediateEvidence.patientAvoid ? '无' : mediateEvidence.patientAvoid}"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tit">医方：</td>
                        <td colspan="3">
                            <form:input id="doctorAvoid" path="doctorAvoid" htmlEscape="false" maxlength="20"
                                        class="input-xlarge " value="${empty mediateEvidence.doctorAvoid ? '无' : mediateEvidence.doctorAvoid }"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tit"><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">三、宣读有关纪律及注意事项：</span></td>
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
                        <td class="tit"><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">四、宣布纠纷当事人在人民调解活动中享有的权利：</span></td>
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
                        <td class="tit"><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">五、宣布纠纷当事人在人民调解活动中履行下列义务：</span></td>
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
                        <td class="tit" rowspan="2"><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">六、以上宣读内容听清楚了吗?有异议吗?</span></td>
                        <td class="tit">患者：</td>
                        <td colspan="3">
                            <form:input id="patientClear" path="patientClear" htmlEscape="false" maxlength="20"
                                        class="input-xlarge " value="${empty mediateEvidence.patientClear ? '清楚，无异议' : mediateEvidence.patientClear}"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tit">医方：</td>
                        <td colspan="3">
                            <form:input id="doctorClear" path="doctorClear" htmlEscape="false" maxlength="20"
                                        class="input-xlarge " value="${empty mediateEvidence.doctorClear ? '清楚，无异议' :mediateEvidence.doctorClear}"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tit"><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">七、开始调解</span></td>
                        <td colspan="7">
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
                        <td class="tit"><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">八、宣布调解结束</span></td>
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
                    <input id="btnGenerate" class="btn btn-primary" type="submit" value="生成会议表"
                           onclick="return promptx('当前有${proSize}次会议记录','请选择某一次的会议记录',document.getElementById('inputForm').action+'?mediateEvidenceId=${mediateEvidence.mediateEvidenceId}&export=meeting&nums=');" data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在导出数据之前请先保存数据。</h4>"/>
                    <input id="btnGeneratePrint" class="btn btn-primary" type="button" value="打 印" onclick="$('#export').val('meeting'); promptx('当前有${proSize}次会议记录','请选择某一次的会议记录',exportWord);" data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在打印数据之前请先保存数据。</h4>"/>

                </td>
            </table>
        </div>
        <div class="tab-pane fade" id="recorded_patient">
            <table class="table-form">
                    <%--<tr>--%>
                    <%--<td class="tit">开始时间</td>--%>
                    <%--<td>--%>
                    <%--<input name="recordInfo.startTime" type="text" readonly="readonly" maxlength="20"--%>
                    <%--class="input-medium Wdate required"--%>
                    <%--value="${mediateEvidence.recordInfo.startTime}"--%>
                    <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>--%>
                    <%--</td>--%>
                    <%--<td class="tit">结束时间</td>--%>
                    <%--<td>--%>
                    <%--<input name="recordInfo.endTime" type="text" readonly="readonly" maxlength="20"--%>
                    <%--class="input-medium Wdate required"--%>
                    <%--value="${mediateEvidence.recordInfo.endTime}"--%>
                    <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>--%>
                    <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                    <%--<td class="tit">地点</td>--%>
                    <%--<td>--%>
                    <%--<form:input path="recordInfo.recordAddress" htmlEscape="false" maxlength="20"--%>
                    <%--class="input-xlarge required"/>--%>
                    <%--</td>--%>
                    <%--<td class="tit">事由</td>--%>
                    <%--<td>--%>
                    <%--<form:input path="recordInfo.cause" htmlEscape="false" maxlength="20" class="input-xlarge required"/>--%>
                    <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                    <%--<td class="tit">主持人</td>--%>
                    <%--<td>--%>
                    <%--<sys:treeselect id="h_host" name="recordInfo.host"--%>
                    <%--value="${mediateEvidence.recordInfo.host}" labelName=""--%>
                    <%--labelValue="${mediateEvidence.recordInfo.ytwHost.name}"--%>
                    <%--title="用户" url="/sys/office/treeData?type=3&officeType=1"--%>
                    <%--allowClear="true" notAllowSelectParent="true" dataMsgRequired="必填信息" cssClass="required" />--%>
                    <%--</td>--%>
                    <%--<td class="tit">记录人</td>--%>
                    <%--<td>--%>
                    <%--<sys:treeselect id="h_noteTaker" name="recordInfo.noteTaker"--%>
                    <%--value="${mediateEvidence.recordInfo.noteTaker}" labelName=""--%>
                    <%--labelValue="${mediateEvidence.recordInfo.ytwNoteTaker.name}"--%>
                    <%--title="用户" url="/sys/office/treeData?type=3&officeType=1"--%>
                    <%--allowClear="true" notAllowSelectParent="true" dataMsgRequired="必填信息" cssClass="required" />--%>
                    <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                    <%--<td class="tit">患方</td>--%>
                    <%--<td>--%>
                    <%--<form:input path="recordInfo.patient" htmlEscape="false" maxlength="20" class="input-xlarge required"/>--%>
                    <%--</td>--%>
                    <%--<td class="tit">医方</td>--%>
                    <%--<td>--%>
                    <%--<sys:treeselect id="h_doctor" name="recordInfo.doctor"--%>
                    <%--value="${mediateEvidence.recordInfo.doctor}" labelName=""--%>
                    <%--labelValue="${mediateEvidence.recordInfo.yfDoctor.name}"--%>
                    <%--title="用户" url="/sys/office/treeData?type=3&officeType=2"--%>
                    <%--allowClear="true" notAllowSelectParent="true" isAll="true" dataMsgRequired="必填信息" cssClass="required" />--%>
                    <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                    <%--<td class="tit">其他参加人员</td>--%>
                    <%--<td>--%>
                    <%--<form:input path="recordInfo.otherParticipants" htmlEscape="false" maxlength="20"--%>
                    <%--class="input-xlarge required"/>--%>
                    <%--</td>--%>
                    <%--</tr>--%>
                <tr>
                    <td class="tit">笔录内容</td>
                    <td colspan="3">
                        <form:textarea path="recordInfo.recordContent" htmlEscape="false" class="input-xlarge "
                                       style="margin: 0px; width: 938px; height: 125px;"/>
                        </br>
                        <span><font color="red" size="5">温馨提示：保存之前请先选择“调解程序表中的'时间'”,否则笔录内容不进行保存！</font></span>
                    </td>
                </tr>
            </table>
            <div style="width: 200px;margin: auto">
               <%-- <input id="pContentExport" class="btn btn-primary" type="submit" value="导 出" onclick="$('#export').val('pContent')" data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在生成意见书之前请先保存数据。</h4>"/>
                <input id="pContentPrint" class="btn btn-primary" type="button" value="打 印" onclick="$('#export').val('pContent');exportWord();" data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在打印数据之前请先保存数据。</h4>"/>
                --%>
                <input id="pContentExport" class="btn btn-primary" type="submit"  value="导 出"
                       onclick="return promptx('当前有${proSize}次会议记录','请选择某一次的会议记录',document.getElementById('inputForm').action+'?mediateEvidenceId=${mediateEvidence.mediateEvidenceId}&export=pContent&nums=');" data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在导出数据之前请先保存数据。</h4>"/>
                <input id="pContentPrint" class="btn btn-primary" type="button" value="打 印" onclick="$('#export').val('pContent'); promptx('当前有${proSize}次会议记录','请选择某一次的会议记录',exportWord);" data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在打印数据之前请先保存数据。</h4>"/>

            </div>
        </div>
        <div class="tab-pane fade" id="recorded_doctor">
            <table class="table-form">
                    <%--<tr>
                        <td class="tit">开始时间</td>
                        <td id="d_startTime">
                            &lt;%&ndash;<input name="recordInfo.yrecordInfo.startTime" type="text" readonly="readonly" maxlength="20"
                                   class="input-medium Wdate required"
                                   value="${mediateEvidence.recordInfo.yrecordInfo.startTime}"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>&ndash;%&gt;
                        </td>
                        <td class="tit">结束时间</td>
                        <td>
                            <input name="recordInfo.yrecordInfo.endTime" type="text" readonly="readonly" maxlength="20"
                                   class="input-medium Wdate required"
                                   value="${mediateEvidence.recordInfo.yrecordInfo.endTime}"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tit">地点</td>
                        <td>
                            <form:input path="recordInfo.yrecordInfo.recordAddress" htmlEscape="false" maxlength="20"
                                        class="input-xlarge required"/>
                        </td>
                        <td class="tit">事由</td>
                        <td>
                            <form:input path="recordInfo.yrecordInfo.cause" htmlEscape="false" maxlength="20"
                                        class="input-xlarge required"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tit">主持人</td>
                        <td>
                            <sys:treeselect id="y_host" name="recordInfo.yrecordInfo.host"
                                            value="${mediateEvidence.recordInfo.yrecordInfo.host}" labelName=""
                                            labelValue="${mediateEvidence.recordInfo.yrecordInfo.ytwHost.name}"
                                            title="用户" url="/sys/office/treeData?type=3&officeType=1"
                                            allowClear="true" notAllowSelectParent="true" dataMsgRequired="必填信息" cssClass="required" />
                        </td>
                        <td class="tit">记录人</td>
                        <td>
                            <sys:treeselect id="y_noteTaker" name="recordInfo.yrecordInfo.noteTaker"
                                            value="${mediateEvidence.recordInfo.yrecordInfo.noteTaker}" labelName=""
                                            labelValue="${mediateEvidence.recordInfo.yrecordInfo.ytwNoteTaker.name}"
                                            title="用户" url="/sys/office/treeData?type=3&officeType=1"
                                            allowClear="true" notAllowSelectParent="true" dataMsgRequired="必填信息" cssClass="required" />
                        </td>
                    </tr>
                    <tr>
                        <td class="tit">患方</td>
                        <td>
                            <form:input path="recordInfo.yrecordInfo.patient" htmlEscape="false" maxlength="20"
                                        class="input-xlarge required"/>
                        </td>
                        <td class="tit">医方</td>
                        <td>
                            <sys:treeselect id="y_doctor" name="recordInfo.yrecordInfo.doctor"
                                            value="${mediateEvidence.recordInfo.yrecordInfo.doctor}" labelName=""
                                            labelValue="${mediateEvidence.recordInfo.yrecordInfo.yfDoctor.name}"
                                            title="用户" url="/sys/office/treeData?type=3&officeType=2"
                                            allowClear="true" notAllowSelectParent="true" isAll="true" dataMsgRequired="必填信息" cssClass="required" />
                        </td>
                    </tr>
                    <tr>
                        <td class="tit">其他参加人员</td>
                        <td>
                            <form:input path="recordInfo.yrecordInfo.otherParticipants" htmlEscape="false" maxlength="20"
                                        class="input-xlarge required"/>
                        </td>
                    </tr>--%>
                <tr>
                    <td class="tit">笔录内容</td>
                    <td colspan="3">
                        <form:textarea path="recordInfo.yrecordInfo.recordContent" htmlEscape="false"
                                       class="input-xlarge "
                                       style="margin: 0px; width: 938px; height: 125px;"/>
                        </br>
                        <span><font color="red" size="5">温馨提示：保存之前请先选择“调解程序表中的'时间'”,否则笔录内容不进行保存！</font></span>
                    </td>
                </tr>
            </table>
            <div style="width: 200px;margin: auto">
                <%--<input id="dContentExport" class="btn btn-primary" type="submit" value="导 出" onclick="$('#export').val('dContent')" data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在生成意见书之前请先保存数据。</h4>"/>
                <input id="dContentPrint" class="btn btn-primary" type="button" value="打 印" onclick="$('#export').val('dContent');exportWord();" data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在打印数据之前请先保存数据。</h4>"/>
--%>
                    <input id="dContentExport" class="btn btn-primary" type="submit"  value="导 出"
                           onclick="return promptx('当前有${proSize}次会议记录','请选择某一次的会议记录',document.getElementById('inputForm').action+'?mediateEvidenceId=${mediateEvidence.mediateEvidenceId}&export=dContent&nums=');" data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在导出数据之前请先保存数据。</h4>"/>
                    <input id="dContentPrint" class="btn btn-primary" type="button" value="打 印" onclick="$('#export').val('dContent'); promptx('当前有${proSize}次会议记录','请选择某一次的会议记录',exportWord);" data-toggle="tooltip" data-placement="top" title="<h4 style='color:yellow;'>在打印数据之前请先保存数据。</h4>"/>

            </div>
        </div>
        <div class="tab-pane fade" id="annex">
            <table class="table-form">
                <tr>
                    <input type="hidden" name="fjtype1" value="7">
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                        签到表：
                    </td>
                    <td style="width: 450px; ">
                        <input type="hidden" id="files1" name="files1" htmlEscape="false" class="input-xlarge"
                               value="${files1}"/>
                        <input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files"
                                                                      uploadPath="/mediate/mediateEvidence/sign"
                                                                      selectMultiple="true"
                                                                      maxWidth="100" maxHeight="100"/></div>
                    </td>
                </tr>
                <tr>
                    <input type="hidden" name="fjtype2" value="8">
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                        患方笔录：
                    </td>
                    <td style="width: 450px; ">
                        <input type="hidden" id="files2" name="files2" htmlEscape="false" class="input-xlarge"
                               value="${files2}"/>
                        <input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">
                            <%--<form:hidden id="files1" path="files1" htmlEscape="false" maxlength="255" class="input-xlarge"/>--%>
                            <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                        <div style="margin-top: -45px;"><sys:ckfinder input="files2" type="files"
                                                                      uploadPath="/mediate/mediateEvidence/huanRecord"
                                                                      selectMultiple="true"
                                                                      maxWidth="100" maxHeight="100"/></div>
                    </td>
                </tr>
                <tr>
                    <input type="hidden" name="fjtype3" value="9">
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                        患方补充材料：
                    </td>
                    <td style="width: 450px; ">
                        <input type="hidden" id="files3" name="files3" htmlEscape="false" class="input-xlarge"
                               value="${files3}"/>
                        <input type="hidden" id="acceId3" name="acceId3" value="${acceId3}">
                            <%--<form:hidden id="files2" path="files2" htmlEscape="false" maxlength="255" class="input-xlarge"/>--%>
                            <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                        <div style="margin-top: -45px;"><sys:ckfinder input="files3" type="files"
                                                                      uploadPath="/mediate/mediateEvidence/huanAdd"
                                                                      selectMultiple="true"
                                                                      maxWidth="100" maxHeight="100"/></div>
                    </td>
                </tr>
                <tr>
                    <input type="hidden" name="fjtype4" value="10">
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                        医方笔录：
                    </td>
                    <td style="width: 450px; ">
                        <input type="hidden" id="files4" name="files4" htmlEscape="false" class="input-xlarge"
                               value="${files4}"/>
                        <input type="hidden" id="acceId4" name="acceId4" value="${acceId4}">
                            <%--<form:hidden id="nameImage" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>--%>
                            <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                        <div style="margin-top: -45px;"><sys:ckfinder input="files4" type="files"
                                                                      uploadPath="/mediate/mediateEvidence/yiRecord"
                                                                      selectMultiple="true"
                                                                      maxWidth="100" maxHeight="100"/></div>
                    </td>
                </tr>
                <tr>
                    <input type="hidden" name="fjtype5" value="11">
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                        医方补充材料：
                    </td>
                    <td style="width: 450px; ">
                        <input type="hidden" id="files5" name="files5" htmlEscape="false" class="input-xlarge"
                               value="${files5}"/>
                        <input type="hidden" id="acceId5" name="acceId5" value="${acceId5}">
                            <%--<form:hidden id="nameImage" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>--%>
                            <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                        <div style="margin-top: -45px;"><sys:ckfinder input="files5" type="files"
                                                                      uploadPath="/mediate/mediateEvidence/yiAdd"
                                                                      selectMultiple="true"
                                                                      maxWidth="100" maxHeight="100"/></div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <table class="table-form">
        <%--<tr>
            <td class="tit">调解结果</td>
            <td>
                <form:select path="mediateResult" cssStyle="width: 180px">
                    <form:option value="1">成功</form:option>
                    <form:option value="2">失败</form:option>
                </form:select>
            </td>
        </tr>--%>
            <%--<tr>--%>
            <%--<td class="tit">会议总结</td>--%>
            <%--<td colspan="3">--%>
            <%--<form:textarea path="summary" htmlEscape="false" class="input-xlarge required"--%>
            <%--style="margin: 0px; width: 938px; height: 125px;"/>--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>
                <td class="tit">处理人</td>
                <td>
                    <form:input path="handlePeople" htmlEscape="false" maxlength="20" class="input-xlarge "/>
                </td>
                <td class="tit">处理日期</td>
                <td>
                    <input name="handleTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                           value="${mediateEvidence.handleTime}"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
                </td>
            </tr>--%>
        <tr>
            <td class="tit"><font color="red">*</font>下一处理环节：</td>
            <td>
                <form:select path="nextLink" cssStyle="width: 180px" onchange="changeNext();">
                    <form:option value="0">评估鉴定</form:option>
                    <form:option value="2">签署协议</form:option>
                </form:select>
            </td>
            <td class="tit"><font color="red">*</font>下一环节处理人：</td>
            <td>
                    <%--<form:input path="nextLinkMan" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
                <sys:treeselect id="nextLinkMan" name="nextLinkMan"
                                value="${empty mediateEvidence.nextLinkMan? (fn:contains(fns:getUser().office.name,'工作站') ? '' : fns:getUser().id ):mediateEvidence.nextLinkMan}"
                                labelName=""
                                labelValue="${empty mediateEvidence.linkEmployee.name ? (fn:contains(fns:getUser().office.name,'工作站') ? '' : fns:getUser().name) : mediateEvidence.linkEmployee.name}"
                                title="用户" url="/sys/office/treeData?type=3&officeType=1${fn:contains(fns:getUser().office.name,'工作站') ? '&next=sz' :''}" allowClear="true"
                                notAllowSelectParent="true" dataMsgRequired="必填信息" cssClass="required" isAll="true"/>
                <sys:treeselect id="aa" name="nextLinkMan"
                                value="${empty mediateEvidence.nextLinkMan?fns:getUser().id:mediateEvidence.nextLinkMan}"
                                labelName=""
                                labelValue="${empty mediateEvidence.linkEmployee.name?fns:getUser().name:mediateEvidence.linkEmployee.name}"
                                title="用户" url="/sys/office/treeData?type=3&officeType=1" allowClear="true"
                               notAllowSelectParent="true" dataMsgRequired="必填信息" isAll="true" cssClass="required"/>

            </td>

        </tr>
    </table>
    <%--<div class="control-group">--%>
    <%--<label class="control-label"><font color="red">*</font>调解结果：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:select path="mediateResult" cssStyle="width: 180px">--%>
    <%--<form:option value="1">成功</form:option>--%>
    <%--<form:option value="2">失败</form:option>--%>
    <%--</form:select>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">会议总结：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:textarea path="summary" htmlEscape="false" rows="4" class="input-xxlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">处理人：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="handlePeople" htmlEscape="false" maxlength="10" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">处理日期：</label>--%>
    <%--<div class="controls">--%>
    <%--<input name="handleTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "--%>
    <%--value="<fmt:formatDate value="${mediateEvidence.handleTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
    <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">下一处理环节：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">下环节处理人：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="nextLinkMan" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <div class="form-actions">
        <shiro:hasPermission name="mediate:mediateEvidence:edit"><input id="btnSubmit" class="btn btn-primary"
                                                                        type="submit" value="保 存"
                                                                        onclick="$('#flag').val('no'),$('#export').val('no'),removeCssClass()"/>&nbsp;</shiro:hasPermission>
        <shiro:hasPermission name="mediate:mediateEvidence:edit"><input id="btnSubmit" class="btn btn-primary"
                                                                        type="submit" value="下一步"
                                                                        onclick="$('#flag').val('yes'),$('#export').val('no'),addCssClass()"/>&nbsp;</shiro:hasPermission>

        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
    <act:histoicFlow procInsId="${mediateEvidence.complaintMain.procInsId}"/>
</form:form>

<script type="text/javascript">
    var mediateEvidenceRowIdx = 0,
        mediateEvidenceTpl = $("#mediateEvidenceTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g, "");
    $(document).ready(function () {
        var data = ${fns:toJson(mediateEvidence.mediateEvidenceList)};
        console.log(data)
        for (var i = 0; i < data.length; i++) {
            addRow('#mediateEvidenceList', mediateEvidenceRowIdx, mediateEvidenceTpl, data[i]);
            mediateEvidenceRowIdx = mediateEvidenceRowIdx + 1;
        }
    });

    function changeClass() {

        if ($("#meetingTime").hasClass("required") == true) {
            $("#meetingTime").removeClass("required");
        } else {
            $("#meetingTime").addClass("required");
        }

        if ($("#meetingAddress").hasClass("required") == true) {
            $("#meetingAddress").removeClass("required");
        } else {
            $("#meetingAddress").addClass("required");
        }

        if ($("#userIdName").hasClass("required") == true) {
            $("#userIdName").removeClass("required");
        } else {
            $("#userIdName").addClass("required");
        }

        if ($("#clerkName").hasClass("required") == true) {
            $("#clerkName").removeClass("required");
        } else {
            $("#clerkName").addClass("required");
        }

        if ($("#doctorName").hasClass("required") == true) {
            $("#doctorName").removeClass("required");
        } else {
            $("#doctorName").addClass("required");
        }

        if ($("#patient").hasClass("required") == true) {
            $("#patient").removeClass("required");
        } else {
            $("#patient").addClass("required");
        }

        if ($("#other").hasClass("required") == true) {
            $("#other").removeClass("required");
        } else {
            $("#other").addClass("required");
        }

        if ($("#doctorOffice\\.name").hasClass("required") == true) {
            $("#doctorOffice\\.name").removeClass("required");
        } else {
            $("#doctorOffice\\.name").addClass("required");
        }

        if ($("#doctorClear").hasClass("required") == true) {
            $("#doctorClear").removeClass("required");
        } else {
            $("#doctorClear").addClass("required");
        }

        if ($("#patientClear").hasClass("required") == true) {
            $("#patientClear").removeClass("required");
        } else {
            $("#patientClear").addClass("required");
        }

        if ($("#doctorAvoid").hasClass("required") == true) {
            $("#doctorAvoid").removeClass("required");
        } else {
            $("#doctorAvoid").addClass("required");
        }

        if ($("#patientAvoid").hasClass("required") == true) {
            $("#patientAvoid").removeClass("required");
        } else {
            $("#patientAvoid").addClass("required");
        }
    }
</script>
</body>
</html>