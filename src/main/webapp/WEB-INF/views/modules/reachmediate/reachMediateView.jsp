<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>达成调解管理</title>
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
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
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
        function delRow(obj, prefix){
            var id = $(prefix+"_mediateRecord");
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

    </script>
</head>
<body>
<br/>
<form:form id="inputForm" modelAttribute="reachMediate" action="${ctx}/reachmediate/reachMediate/save" method="post" class="form-horizontal">
    <form:hidden path="reachMediateId"/>
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
    <sys:message content="${message}"/>
    <legend>达成调解详情</legend>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#mediation" data-toggle="tab">调解志</a>
        </li>
        <li>
            <a href="#meeting" data-toggle="tab">调解会议表</a>
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
                    <th width="100">内容</th>
                    <th width="100">结果</th>
                    <shiro:hasPermission name="reachmediate:reachMediate:edit">
                        <th width="100">&nbsp;</th>
                    </shiro:hasPermission>
                </tr>
                </thead>
                <tbody id="mediateEvidenceList"></tbody>
                <shiro:hasPermission name="reachmediate:reachMediate:edit">
                    <tfoot>
                    <tr><td colspan="7"></td></tr>
                    </tfoot></shiro:hasPermission>
            </table>
            <script type="text/template" id="reachMediateTpl">
						<tr id="mediateEvidenceList{{idx}}">
							<td class="hide">
								<input id="mediateEvidenceList{{idx}}_id" name="mediateEvidenceList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="mediateEvidenceList{{idx}}_mediateRecord" name="mediateEvidenceList[{{idx}}].mediateRecord" type="hidden" value="{{row.mediateRecord}}"/>
								<input id="mediateEvidenceList{{idx}}_relationId" name="mediateEvidenceList[{{idx}}].relationId" type="hidden" value="{{row.relationId}}"/>
								<input id="mediateEvidenceList{{idx}}_delFlag" name="mediateEvidenceList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td >
								<%--<input id="mediateEvidenceList{{idx}}_time" name="mediateEvidenceList[{{idx}}].time" type="text" value="{{row.time}}" maxlength="32" class="input-small "/>--%>
								<input id="mediateEvidenceList{{idx}}_time" name="mediateEvidenceList[{{idx}}].time" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
                                    value="{{row.time}}"
                                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" disabled="disabled"/>
							</td>
							<td>
								<input id="mediateEvidenceList{{idx}}_content" name="mediateEvidenceList[{{idx}}].content" type="text" value="{{row.content}}" maxlength="100" class="required" disabled="disabled"/>
							</td>
							<td>
								<input id="mediateEvidenceList{{idx}}_result" name="mediateEvidenceList[{{idx}}].result" type="text" value="{{row.result}}" maxlength="32" class="required" disabled="disabled"/>
							</td>
							<shiro:hasPermission name="reachmediate:reachMediate:edit"><td class="text-center" width="10">
								{{delBtn}}<span class="close" onclick="delRow(this, '#mediateEvidenceList{{idx}}')" title="删除">&times;</span>{{delBtn}}
							</td></shiro:hasPermission>
						</tr>
            </script>
        </div>
        <div class="tab-pane fade" id="meeting">
            <table class="table-form">
                <tr>
                    <td class="tit">时间:</td>
                    <td>
                        ${reachMediate.reaMeetingTime}
                    </td>
                    <td class="tit">地点:</td>
                    <td>
                        ${reachMediate.reaAddress}
                    </td>
                </tr>
                <tr>
                    <td class="tit">案件:</td>
                    <td>
                            ${reachMediate.reaCaseInfo}
                    </td>
                    <td class="tit">医方:</td>
                    <td class="controls">
                       ${reachMediate.doctorUser.name}
                    </td>
                </tr>
                <tr>
                    <td class="tit">医调委人员:</td>
                    <td>
                       ${reachMediate.ytwUser.name}
                    </td>
                    <td class="tit">患方</td>
                    <td>
                        ${reachMediate.reaPatient}
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="recorded_patient">
            <table class="table-form">
                <tr>
                    <td class="tit">开始时间</td>
                    <td>
                       ${reachMediate.recordInfo.startTime}
                    </td>
                    <td class="tit">结束时间</td>
                    <td>
                        ${reachMediate.recordInfo.endTime}
                    </td>
                </tr>
                <tr>
                    <td class="tit">地点</td>
                    <td>
                            ${reachMediate.recordInfo.recordAddress}
                    </td>
                    <td class="tit">事由</td>
                    <td>
                            ${reachMediate.recordInfo.cause}
                    </td>
                </tr>
                <tr>
                    <td class="tit">主持人</td>
                    <td>
                        ${reachMediate.recordInfo.ytwHost.name}
                    </td>
                    <td class="tit">记录人</td>
                    <td>
                        ${reachMediate.recordInfo.ytwNoteTaker.name}
                    </td>
                </tr>
                <tr>
                    <td class="tit">患方</td>
                    <td>
                            ${reachMediate.recordInfo.patient}
                    </td>
                    <td class="tit">医方</td>
                    <td>
                        ${reachMediate.recordInfo.yfDoctor.name}
                    </td>
                </tr>
                <tr>
                    <td class="tit">其他参加人员</td>
                    <td>
                            ${reachMediate.recordInfo.otherParticipants}
                    </td>
                </tr>
                <tr>
                    <td class="tit">笔录内容</td>
                    <td colspan="3">
                            ${reachMediate.recordInfo.recordContent}
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="recorded_doctor">
            <table class="table-form">
                <tr>
                    <td class="tit">开始时间</td>
                    <td>
                        ${reachMediate.recordInfo.yrecordInfo.startTime}
                    </td>
                    <td class="tit">结束时间</td>
                    <td>
                       ${reachMediate.recordInfo.yrecordInfo.endTime}
                    </td>
                </tr>
                <tr>
                    <td class="tit">地点</td>
                    <td>
                            ${reachMediate.recordInfo.yrecordInfo.recordAddress}
                    </td>
                    <td class="tit">事由</td>
                    <td>
                            ${reachMediate.recordInfo.yrecordInfo.cause}
                    </td>
                </tr>
                <tr>
                    <td class="tit">主持人</td>
                    <td>
                        ${reachMediate.recordInfo.yrecordInfo.ytwHost.name}
                    </td>
                    <td class="tit">记录人</td>
                    <td>
                        ${reachMediate.recordInfo.yrecordInfo.ytwNoteTaker.name}
                    </td>
                </tr>
                <tr>
                    <td class="tit">患方</td>
                    <td>
                            ${reachMediate.recordInfo.yrecordInfo.patient}
                    </td>
                    <td class="tit">医方</td>
                    <td>
                        ${reachMediate.recordInfo.yrecordInfo.yfDoctor.name}
                    </td>
                </tr>
                <tr>
                    <td class="tit">其他参加人员</td>
                    <td>
                            ${reachMediate.recordInfo.yrecordInfo.otherParticipants}
                    </td>
                </tr>
                <tr>
                    <td class="tit">笔录内容</td>
                    <td colspan="3">
                            ${reachMediate.recordInfo.yrecordInfo.recordContent}
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="annex">
            <table class="table-form">
                <tr >
                    <input type="hidden" name="fjtype1" value="7">
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                        签到表：
                    </td>
                    <td style="width: 450px; ">
                        <input type="hidden" id="files1" name="files1" htmlEscape="false" class="input-xlarge"
                               value="${files1}"/>
                        <input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
                            <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>--%>
                            <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                        <div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files" uploadPath="/mediate/mediateEvidence/sign" selectMultiple="false"
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
                        <div style="margin-top: -45px;"><sys:ckfinder input="files2" type="files" uploadPath="/mediate/mediateEvidence/huanRecord"
                                                                      selectMultiple="false"
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
                        <div style="margin-top: -45px;"><sys:ckfinder input="files3" type="files" uploadPath="/mediate/mediateEvidence/huanAdd"
                                                                      selectMultiple="false"
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
                        <div style="margin-top: -45px;"><sys:ckfinder input="files4" type="files" uploadPath="/mediate/mediateEvidence/yiRecord"
                                                                      selectMultiple="false"
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
                        <div style="margin-top: -45px;"><sys:ckfinder input="files5" type="files" uploadPath="/mediate/mediateEvidence/yiAdd"
                                                                      selectMultiple="false"
                                                                      maxWidth="100" maxHeight="100"/></div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <table class="table-form">
        <tr>
            <td class="tit">调解结果</td>
            <td>
                <c:choose>
                    <c:when test="${reachMediate.reaMediateResult=='1'}">
                        成功
                    </c:when>
                    <c:otherwise>
                        失败
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td class="tit">会议总结</td>
            <td colspan="3">
                    ${reachMediate.reaSummary}
            </td>
        </tr>
        <%--<tr>--%>
                <%--&lt;%&ndash;<td class="tit"><font color="red">*</font>下一处理环节：</td>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<td>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge "/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
            <%--<td class="tit"><font color="red">*</font>下一环节处理人：</td>--%>
            <%--<td>--%>
                <%--<sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${reachMediate.nextLinkMan}" labelName=""--%>
                                <%--labelValue="${reachMediate.linkEmployee.name}"--%>
                                <%--title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass="required" allowClear="true"--%>
                                <%--notAllowSelectParent="true" checked="true" dataMsgRequired="必填信息"/>--%>
            <%--</td>--%>
        <%--</tr>--%>
    </table>
    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" style="margin-left: 550px;"/>
    </div>
    <act:histoicFlow procInsId="${reachMediate.complaintMain.procInsId}" />
</form:form>
<script type="text/javascript">
    var reachMediateRowIdx = 0, reachMediateTpl = $("#reachMediateTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
    $(document).ready(function() {
        var data = ${fns:toJson(reachMediate.mediateEvidenceList)};
        for (var i=0; i<data.length; i++){
            addRow('#mediateEvidenceList', reachMediateRowIdx, reachMediateTpl, data[i]);
            reachMediateRowIdx = reachMediateRowIdx + 1;
        }
    });
</script>
</body>
</html>