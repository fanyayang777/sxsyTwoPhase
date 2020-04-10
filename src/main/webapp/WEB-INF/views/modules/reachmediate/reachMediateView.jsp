<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>达成调解管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {


        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }

            //投诉接待详情
            $("#tsjdDetail").attr("src","${ctx}/complaintdetail/complaintMainDetail/form?id=${map.tsjd}&type=view");
            var tsjd= document.getElementById("tsjdDetail");
            tsjd.height=document.documentElement.clientHeight-130;
            tsjd.width=document.documentElement.clientWidth;
            var show='${show2}';
            if(show=='' || show== null){
            //报案登记
            $("#badjDetail").attr("src","${ctx}/registration/reportRegistration/form?id=${map.badj}&type=view&show2=y");
            var badj= document.getElementById("badjDetail");
            badj.height=document.documentElement.clientHeight-130;
            badj.width=document.documentElement.clientWidth;
            //审核受理
            $("#shslDetail").attr("src","${ctx}/auditacceptance/auditAcceptance/form?id=${map.shsl}&type=view&show2=y");
            var shsl= document.getElementById("shslDetail");
            shsl.height=document.documentElement.clientHeight-130;
            shsl.width=document.documentElement.clientWidth;
            //调查取证
            $("#dcqzDetail").attr("src","${ctx}/nestigateeividence/investigateEvidence/form?id=${map.dcqz}&type=view&show2=y");
            var dcqz= document.getElementById("dcqzDetail");
            dcqz.height=document.documentElement.clientHeight-130;
            dcqz.width=document.documentElement.clientWidth;
            //质证调解
            $("#zztjDetail").attr("src","${ctx}/mediate/mediateEvidence/form?id=${map.zztj}&type=view&show2=y");
            var zztj= document.getElementById("zztjDetail");
            zztj.height=document.documentElement.clientHeight-130;
            zztj.width=document.documentElement.clientWidth;
            <%--//评估坚定申请--%>
            <%--$("#pgjdsqDetail").attr("src","${ctx}/assessapply/assessApply/form?id=${map.pgjdsq}&type=view&show2=y");--%>
            <%--var pgjdsq= document.getElementById("pgjdsqDetail");--%>
            <%--pgjdsq.height=document.documentElement.clientHeight-130;--%>
            <%--pgjdsq.width=document.documentElement.clientWidth;--%>
            <%--//评估鉴定审批--%>
            <%--$("#pgjdspDetail").attr("src","${ctx}/assessaudit/assessAudit/form?id=${map.pgjdsq}&type=view&show2=y");--%>
            <%--var pgjdsp= document.getElementById("pgjdspDetail");--%>
            <%--pgjdsp.height=document.documentElement.clientHeight-130;--%>
            <%--pgjdsp.width=document.documentElement.clientWidth;--%>
            //评估鉴定
            $("#pgjdDetail").attr("src","${ctx}/assessappraisal/assessAppraisal/form?id=${map.pgjd}&type=view&show2=y");
            var pgjd= document.getElementById("pgjdDetail");
            pgjd.height=document.documentElement.clientHeight-130;
            pgjd.width=document.documentElement.clientWidth;
            }
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
    <form:hidden path="reaPatient"/>
    <sys:message content="${message}"/>
<fieldset>
    <ul id="myTab1" class="nav nav-tabs">
        <li class="active">
            <a href="#dctj" data-toggle="tab">达成调解</a>
        </li>
        <c:if test="${empty show2}">
        <li>
            <a href="#details" data-toggle="tab">详情</a>
        </li>
        </c:if>
    </ul>
    <legend>达成调解详情</legend>
    <div id="myTabContent1" class="tab-content">
        <div id="dctj" class="tab-pane fade in active">
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#mediation" data-toggle="tab">调解志</a>
        </li>
        <li>
            <a href="#meeting" data-toggle="tab">调解程序表</a>
        </li>
        <%--<li>
            <a href="#recorded_patient" data-toggle="tab">调解会笔录（患方）</a>
        </li>
        <li>
            <a href="#recorded_doctor" data-toggle="tab">调解会笔录（医方）</a>
        </li>--%>
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
                    <th width="10" style="text-align: center">时间</th>
                    <th width="10" style="text-align: center">角色</th>
                    <th width="10" style="text-align: center">方式</th>
                    <th width="100" style="text-align: center">内容</th>
                    <th width="100" style="text-align: center">结果</th>
                </tr>
                </thead>
                <tbody id="mediateEvidenceList"></tbody>
                <shiro:hasPermission name="reachmediate:reachMediate:edit">
                    <tfoot>
                        <%--<tr><td colspan="7"><a href="javascript:" onclick="addRow('#mediateEvidenceList', mediateEvidenceRowIdx, mediateEvidenceTpl);mediateEvidenceRowIdx = mediateEvidenceRowIdx + 1;" class="btn">新增</a></td></tr>--%>
                    </tfoot></shiro:hasPermission>
            </table>
            <script type="text/template" id="mediateEvidenceTpl">//<!--
						<tr id="mediateEvidenceList{{idx}}">
							<td style="text-align: center">
							    {{row.time}}
							</td>
							<td style="text-align: center">
							    <select id="mediateEvidenceList{{idx}}_roleType" name="mediateEvidenceList[{{idx}}].roleType" value="{{row.roleType}}" data-value="{{row.roleType}}" class="input-mini" disabled="disabled">
									<option value=""></option>
									<option value="1"  >医方</option>
									<option value="2"  >患方</option>
									<option value="3"  >医患双方</option>
								</select>
							</td>
							<td style="text-align: center">
							    <select id="mediateEvidenceList{{idx}}_way" name="mediateEvidenceList[{{idx}}].way" value="{{row.way}}" data-value="{{row.way}}" class="input-mini" disabled="disabled">
									<option value=""></option>
									<option value="1"  >电话沟通</option>
									<option value="2"  >单方调解</option>
									<option value="3"  >调解会</option>
									<option value="4"  >其他</option>
								</select>
							</td>
							<td style="text-align: center">
								{{row.content}}
							</td>
							<td style="text-align: center">
							    {{row.result}}
							</td>
						</tr>//-->
            </script>
        </div>
        <div class="tab-pane fade" id="meeting">
            <table id="programTable" class="table table-striped table-bordered table-condensed">
                <thead>
                <tr>
                    <th class="hide"></th>
                    <th  style="text-align: center">时间</th>
                    <th  style="text-align: center">地点</th>
                    <th  style="text-align: center">调解员</th>
                    <th  style="text-align: center">书记员</th>
                    <th  style="text-align: center">患方</th>
                    <th  style="text-align: center">患方笔录</th>
                    <th  style="text-align: center">医方</th>
                    <th  style="text-align: center">医方笔录</th>
                    <th  style="text-align: center">会议次数</th>
                </tr>
                </thead>
                <tbody id="mediateProgramList"></tbody>
            </table>
            <script type="text/template" id="mediateProgramTpl">//<!--
						<tr id="mediateProgramList{{idx}}">
							<td style="text-align: center;width : 8% ;">
                                   {{row.meetingTime}}
							</td>
							<td style="text-align: center;width : 5% ;">
							        {{row.addressLabel}}
							</td>
							<td style="text-align: center;width : 5% ;">
							        {{row.mediatorUser.name}}
							</td>
							<td style="text-align: center;width : 5% ;">
							        {{row.clerkuser.name}}
							</td>
							<td style="text-align: center;width : 5% ;">
							        {{row.patient}}
							</td>
							<td style="text-align: center;width : 30% ;">
							       <textarea style="boder:0,border-radius:5px;width: 255px;height: 100px;padding: 10px;" readonly="true"> {{row.patientContent}}</textarea>
							</td>
							<td style="text-align: center;width : 7% ;">
							        {{row.doctorOffice.name}}
							</td>
							<td style="text-align: center;width : 30% ;word-break : break-all;">
							        <textarea style="boder:0,border-radius:5px;width: 255px;height: 100px;padding: 10px;" readonly="true"> {{row.doctorContent}}</textarea>
							</td>
							<td style="text-align: center;width : 5% ;">
							        第{{row.meetingFrequency}}次会议
							</td>
						</tr>//-->
            </script>
        </div>
        <div class="tab-pane fade" id="recorded_patient">
            <table class="table-form">
                <%--<tr>--%>
                    <%--<td class="tit">开始时间</td>--%>
                    <%--<td>--%>
                       <%--${reachMediate.recordInfo.startTime}--%>
                    <%--</td>--%>
                    <%--<td class="tit">结束时间</td>--%>
                    <%--<td>--%>
                        <%--${reachMediate.recordInfo.endTime}--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td class="tit">地点</td>--%>
                    <%--<td>--%>
                            <%--${reachMediate.recordInfo.recordAddress}--%>
                    <%--</td>--%>
                    <%--&lt;%&ndash;<td class="tit">事由</td>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<td>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;${reachMediate.recordInfo.cause}&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
                    <%--<td class="tit">调解员</td>--%>
                    <%--<td>--%>
                            <%--${reachMediate.recordInfo.ytwHost.name}--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td class="tit">书记员</td>--%>
                    <%--<td>--%>
                        <%--${reachMediate.recordInfo.ytwNoteTaker.name}--%>
                    <%--</td>--%>
                    <%--<td class="tit">患方</td>--%>
                    <%--<td>--%>
                            <%--${reachMediate.recordInfo.patient}--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td class="tit">医方</td>--%>
                    <%--<td>--%>
                        <%--${reachMediate.recordInfo.yfOffice.name}--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td class="tit">其他参加人员</td>--%>
                    <%--<td>--%>
                            <%--${reachMediate.recordInfo.otherParticipants}--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <tr>
                    <td class="tit" width="140px">笔录内容</td>
                    <td colspan="3">
                            ${reachMediate.recordInfo.recordContent}
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="recorded_doctor">
            <table class="table-form">
                <%--<tr>--%>
                    <%--<td class="tit">开始时间</td>--%>
                    <%--<td>--%>
                        <%--${reachMediate.recordInfo.yrecordInfo.startTime}--%>
                    <%--</td>--%>
                    <%--<td class="tit">结束时间</td>--%>
                    <%--<td>--%>
                       <%--${reachMediate.recordInfo.yrecordInfo.endTime}--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td class="tit">地点</td>--%>
                    <%--<td>--%>
                            <%--${reachMediate.recordInfo.yrecordInfo.recordAddress}--%>
                    <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<td class="tit">事由</td>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<td>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;${reachMediate.recordInfo.yrecordInfo.cause}&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
                    <%--<td class="tit">调解员</td>--%>
                    <%--<td>--%>
                            <%--${reachMediate.recordInfo.yrecordInfo.ytwHost.name}--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td class="tit">书记员</td>--%>
                    <%--<td>--%>
                        <%--${reachMediate.recordInfo.yrecordInfo.ytwNoteTaker.name}--%>
                    <%--</td>--%>
                    <%--<td class="tit">患方</td>--%>
                    <%--<td>--%>
                            <%--${reachMediate.recordInfo.yrecordInfo.patient}--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td class="tit">医方</td>--%>
                    <%--<td>--%>
                        <%--${reachMediate.recordInfo.yrecordInfo.yfOffice.name}--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td class="tit">其他参加人员</td>--%>
                    <%--<td>--%>
                            <%--${reachMediate.recordInfo.yrecordInfo.otherParticipants}--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <tr>
                    <td class="tit" width="140px">笔录内容</td>
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
        <%--<tr>
            <td class="tit" width="140px">调解结果</td>
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
        </tr>--%>
        <%--<tr>--%>
            <%--<td class="tit">会议总结</td>--%>
            <%--<td colspan="3">--%>
                    <%--${reachMediate.reaSummary}--%>
            <%--</td>--%>
        <%--</tr>--%>
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
        <tr>
            <td class="tit" style="width: 30%;">下一环节处理人</td>
            <td>
                    ${reachMediate.linkEmployee.name}
            </td>
        </tr>
    </table>
        </div>
        <div class="tab-pane fade" id="details">
            <ul id="iframe" class="nav nav-tabs">
                <li class="active">
                    <a href="#tsjd" data-toggle="tab">投诉接待</a>
                </li>
                <li>
                    <a href="#badj" data-toggle="tab">报案登记</a>
                </li>
                <li>
                    <a href="#shsl" data-toggle="tab">审核受理</a>
                </li>
                <li>
                    <a href="#dcqz" data-toggle="tab">调查取证</a>
                </li>
                <li>
                    <a href="#zztj" data-toggle="tab">质证调解</a>
                </li>
                <%--<li>--%>
                    <%--<a href="#pgjdsq" data-toggle="tab">评估鉴定申请</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="#pgjdsp" data-toggle="tab">评估鉴定审批</a>--%>
                <%--</li>--%>
                <li>
                    <a href="#pgjd" data-toggle="tab">评估鉴定</a>
                </li>
            </ul>
            <div id="iframeTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="tsjd">
                    <iframe id="tsjdDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                </div>
                <div class="tab-pane fade" id="badj">
                    <iframe id="badjDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                </div>
                <div class="tab-pane fade" id="shsl">
                    <iframe id="shslDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                </div>
                <div class="tab-pane fade" id="dcqz">
                    <iframe id="dcqzDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                </div>
                <div class="tab-pane fade" id="zztj">
                    <iframe id="zztjDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                </div>
                <%--<div class="tab-pane fade" id="pgjdsq">--%>
                    <%--<iframe id="pgjdsqDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>--%>
                <%--</div>--%>
                <%--<div class="tab-pane fade" id="pgjdsp">--%>
                    <%--<iframe id="pgjdspDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>--%>
                <%--</div>--%>
                <div class="tab-pane fade" id="pgjd">
                    <iframe id="pgjdDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                </div>
            </div>
        </div>
    </div>
</fieldset>
    <c:if test="${empty show2}">
    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" style="margin-left: 550px;"/>
    </div>
    <act:histoicFlow procInsId="${reachMediate.complaintMain.procInsId}" />
    </c:if>
</form:form>
<script type="text/javascript">
    var mediateEvidenceRowIdx = 0, mediateEvidenceTpl = $("#mediateEvidenceTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
    $(document).ready(function() {
        var data = ${fns:toJson(reachMediate.mediateEvidenceList)};
        for (var i=0; i<data.length; i++){
            addRow('#mediateEvidenceList', mediateEvidenceRowIdx, mediateEvidenceTpl, data[i]);
            mediateEvidenceRowIdx = mediateEvidenceRowIdx + 1;
        }
    });
    var mediateProgramRowIdx = 0, mediateProgramTpl = $("#mediateProgramTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
    $(document).ready(function() {
        var data = ${fns:toJson(reachMediate.mediateProgramList)};
        for (var i=0; i<data.length; i++){
            addRow('#mediateProgramList', mediateProgramRowIdx, mediateProgramTpl, data[i]);
            mediateProgramRowIdx = mediateProgramRowIdx + 1;
        }
    });
</script>
</body>
</html>