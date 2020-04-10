<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>台账信息展示管理</title>
    <meta name="decorator" content="default"/>
    <script src="${ctxStatic}/bootstrap/colResizable-1.6.min.js"></script>
    <script src="${ctxStatic}/bootstrap/bootstrap-table-resizable.js"></script>
    <script src="${ctxStatic}/bootstrap/bootstrap-table-all.js"></script>
    <script src="${ctxStatic}/bootstrap/bootstrap-table-zh-CN.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#contentTable").colResizable({
                liveDrag:true,//拖动列时更新表布局
                gripInnerHtml:"<div class='grip'></div>",
                draggingClass:"dragging",
                resizeMode:'overflow',//允许溢出父容器
                defaults : true,
                /*//拖动事件
                onDrag: function () {
                    $('#contentTable').bootstrapTable("resetView")
                }*/
            });
            $("#btnExportFiles").click(function () {
                $.jBox($("#importExport").html(), {
                    title: "导出数据", buttons: {"关闭": true}
                    , bottomText: "若日期为空,默认选择当天。若没有弹出下载框，则代表这段日期无数据可导出！"
                });
            });
            $("#btnImport").click(function () {
                $.jBox($("#importBox").html(), {
                    title: "导入数据", buttons: {"关闭": true},
                    bottomText: "导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
                });
            });
        });
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
        var flag=true;
        function show() {
            if(flag==true){
                $("#style").attr('disabled','disabled');
                flag=false;
            }else{
                $("#style").removeAttr('disabled');
                flag=true;
            }
        }
    </script>
    <style id="style" type="text/css">
        #contentTable {
            width: 400em;
            table-layout: fixed;
        }
        #contentTable th {
            text-align: center; /** 设置水平方向居中 */
            vertical-align: middle; /** 设置垂直方向居中 */
        }
        #contentTable td {
            word-break: keep-all; /* 不换行 */
            white-space: nowrap; /* 不换行 */
            overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
            text-overflow: ellipsis; /* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
        }
        #contentTable th:nth-of-type(23) {
            width: 10em;
        }
    </style>
</head>
<body>
<div id="importBox" class="hide">
    <form id="importForm" action="${ctx}/machine/machineAccount/import" method="post" enctype="multipart/form-data"
          class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
        <input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
        <input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
        <a href="${ctx}/machine/machineAccount/import/template">下载模板</a>
    </form>
</div>
<div id="importExport" class="hide">
    <form id="importForm1" action="${ctx}/machine/machineAccount/export" method="post"
          enctype="multipart/form-data" class="form-search" style="padding-left:20px;text-align:center;"><br/>

        <span>开始时间：</span>
        <input name="startInsuranceTime1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
               value="${machineAccount.startInsuranceTime1}"
               onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00',isShowClear:true});"/>
        <br/>
        <br/>

        <span>结束时间：</span>
        <input id="endInsuranceTime1" name="endInsuranceTime1" type="text" readonly="readonly" maxlength="20"
               class="input-medium Wdate" style="width:163px;"
               value="${machineAccount.endInsuranceTime1}"
               onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59'});"/>

        <br/>
        <br/>
        <input id="btnExportSubmit" class="btn btn-primary" type="submit" value="   导    出   "/>
    </form>
</div>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/machine/machineAccount/">台账信息展示列表</a></li>
    <shiro:hasPermission name="machine:machineAccount:edit">
        <li><a href="${ctx}/machine/machineAccount/form">台账信息展示添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="machineAccount" action="${ctx}/machine/machineAccount/" method="post"
           onsubmit="loading('正在查询，请稍等...');"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
    <ul class="ul-form">
        <li><label>报案时间：</label>
            <input id="reportingTime" name="reportingTime" type="text" readonly="readonly" maxlength="20"
                   class="input-medium Wdate" style="width:163px;"
                   value="${machineAccount.reportingTime}"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
            　--　
            <input id="endReportingTime" name="endReportingTime" type="text" readonly="readonly" maxlength="20"
                   class="input-medium Wdate" style="width:163px;"
                   value="${machineAccount.endReportingTime}"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
        </li>
        <li><label>部门：</label>
            <sys:treeselect id="deptId" name="deptId" value="${machineAccount.deptId}" labelName="office.name"
                            labelValue="${machineAccount.office.name}"
                            title="部门" url="/sys/office/treeData?type=2&officeType=1" cssClass="input-small" allowClear="true"
                            notAllowSelectParent="true"/>
        </li>
        <li><label>调解员：</label>
            <sys:treeselect id="mediatorId" name="mediatorId" value="${machineAccount.mediatorId}" labelName="user.name"
                            labelValue="${machineAccount.user.name}"
                            title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass="input-small" allowClear="false"
                            notAllowSelectParent="true"/>
        </li>
        <li><label>所属区域：</label>
            <form:input path="areaId" htmlEscape="false" maxlength="50" class="input-medium"/>
        </li>
        <li><label>医院名称：</label>
            <sys:treeselect id="hospitalId" name="hospitalId" value="${machineAccount.hospitalId}" labelName="hospital.name"
                            labelValue="${machineAccount.hospital.name}"
                            title="部门" url="/sys/office/treeData?type=2&officeType=2" cssClass="input-small" allowClear="true"
                            notAllowSelectParent="true"/>
        </li>
        <%--<li><label>保单号：</label>
            <form:input path="policyNumber" htmlEscape="false" maxlength="50" class="input-medium"/>
        </li>
        <li><label>起保日期：</label>
            <input name="startInsuranceTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="${machineAccount.startInsuranceTime}"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
            　--　
            <input id="endInsuranceTime" name="endInsuranceTime" type="text" readonly="readonly" maxlength="20"
                   class="input-medium Wdate" style="width:163px;"
                   value="${machineAccount.endInsuranceTime}"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
        </li>
        <li><label>卷宗编号：</label>
            <form:input path="fileNumber" htmlEscape="false" maxlength="20" class="input-medium"/>
        </li>--%>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
            <input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
            <input id="btnExportFiles" class="btn btn-primary" type="button" value="导出"/>
        </li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed" data-toggle="table" data-url="./complaintMainDetail" data-method="post"
       data-show-columns="true"
       data-buttons-align="left"
       data-show-toggle="true"
       ontoggle="show()"
>
    <thead>
    <tr>
        <th class="sort-column a.case_number">案件编号</th>
        <th class="sort-column case_situation">案件情况</th>
        <th class="sort-column a.area_id">所属地区</th>
        <th class="sort-column a.dept_id">所属部门</th>
        <th class="sort-column mediator_id">调解员</th>
        <th class="sort-column patient_name">患者名称</th>
        <th class="sort-column hospital_id">涉及医院</th>
        <th class="sort-column hospital_grade">机构等级</th>
        <th class="sort-column insurance_company">保险公司</th>
        <th class="sort-column summary_of_disputes">纠纷概要</th>
        <th class="sort-column related_major">涉及专业</th>
        <th class="sort-column treatment_mode">诊疗方式</th>
        <th class="sort-column treatment_result">治疗结果</th>
        <th class="sort-column risk_people">出险医生</th>
        <th class="sort-column patients_reflect_focus">纠纷焦点</th>
        <th class="sort-column is_major">是否重大</th>
        <th class="sort-column is_media">是否媒体介入</th>
        <th class="sort-column risk_time">出险日期</th>
        <th class="sort-column disputes_time">纠纷发生日期</th>
        <th class="sort-column reporting_time">报案时间</th>
        <th class="sort-column acceptance_time">受理时间</th>
        <th class="sort-column eighteen_items">涉及核心制度</th>
        <th class="sort-column meeting_frequency">调解次数</th>
        <th class="sort-column assess_time">评估时间</th>
        <th class="sort-column host">主持人</th>
        <th class="sort-column clerk">书记员</th>
        <th class="sort-column medical_expert">医学专家</th>
        <th class="sort-column legal_expert">法学律师</th>
        <th class="sort-column duty_ratio">责任比例</th>
        <th class="sort-column count_amount">计算金额</th>
        <th class="sort-column assess_number">评估号</th>
        <th class="sort-column mediate_result">调解结果	</th>
        <th class="sort-column is_judicial">是否司法确认</th>
        <th class="sort-column agreement_number">协议号</th>
        <th class="sort-column ratify_accord">协议签署时间</th>
        <th class="sort-column agreement_stamp_time">协议盖章时间</th>
        <th class="sort-column flow_days">流转天数</th>
        <th class="sort-column agreement_amount">协议金额</th>
        <th width="100" class="sort-column insurance_amount">保险赔付金额</th>
        <th width="100" class="sort-column hospital_amount">医院赔付金额</th>
        <th class="sort-column claim_settlement_time">交理赔时间</th>
        <th class="sort-column claim_settlement_day">提交理赔天数</th>
        <th class="sort-column insurance_pay_time">保险赔付时间</th>
        <th class="sort-column hospital_pay_time">医院赔付时间</th>
        <th class="sort-column settlement_flow_days">理赔流转天数</th>
        <th class="sort-column archive_time">卷宗归档时间</th>
        <th class="sort-column hand_over">移交人</th>
        <th class="sort-column file_number">卷宗编号</th>
        <th class="sort-column assess_grade">卷宗评分</th>
        <th class="sort-column appraiser">评分人</th>
        <th class="sort-column file_place">卷宗位置</th>
        <th class="sort-column remark">备注</th>
        <th class="sort-column a.create_by">创建者</th>
        <th width="150" class="sort-column a.create_date">创建时间</th>
        <th class="sort-column a.update_by">更新人</th>
        <th width="150" class="sort-column a.update_date">修改时间</th>
        <shiro:hasPermission name="machine:machineAccount:edit">
            <th data-switchable="false">操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody class="as">
    <c:forEach items="${page.list}" var="machineAccount">

        <tr>

            <td>
                    ${machineAccount.caseNumber}
            </td>
                <%--<td>
                        ${machineAccount.caseSituation}
                </td>--%>
            <td>

                <c:choose>
                <c:when test="${not empty machineAccount.nodeName }">
                <a href="${ctx}/machine/machineAccount/form?id=${machineAccount.machineAccountId}&type=view">
                        ${machineAccount.nodeName}
                </a></td>
            </c:when>
            <c:otherwise>
                ${machineAccount.caseSituation}
            </c:otherwise>
            </c:choose>

            <td>
                    ${machineAccount.area.name}
                    <%--
                                        ${empty fns:getOfficeId(machineAccount.hospitalId).area.name  ? fns:officeId(machineAccount.hospitalId).area.name : fns:getOfficeId(machineAccount.hospitalId).area.name}
                    --%>
            </td>
            <td>
                <c:choose>
                    <c:when test="${ empty machineAccount.deptId }">
                        ${fns:getUserById(machineAccount.createBy.id).office.name}
                    </c:when>
                    <c:when test="${ empty machineAccount.office.name }">
                        ${machineAccount.deptId}
                    </c:when>
                    <c:otherwise>
                        ${machineAccount.office.name}
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${ empty machineAccount.mediatorId }">
                        ${machineAccount.createBy.name}
                    </c:when>
                    <c:when test="${ empty machineAccount.user.name }">
                        ${machineAccount.mediatorId}
                    </c:when>
                    <c:otherwise>
                        ${machineAccount.user.name}
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                    ${machineAccount.patientName}
            </td>
            <td>
                <c:choose>
                    <c:when test="${empty machineAccount.hospital.name}">
                        ${machineAccount.hospitalId}
                    </c:when>
                    <c:otherwise>
                        ${machineAccount.hospital.name}
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                    ${machineAccount.hospitalGrade}
            </td>
            <td>
                    ${fns:getDictLabel(machineAccount.insuranceCompany, 'sys_office_form',machineAccount.insuranceCompany)}
            </td>
            <td>
                    ${machineAccount.summaryOfDisputes}
            </td>
            <td>
                    ${empty machineAccount.relatedName ? machineAccount.relatedMajor : machineAccount.relatedName}
            </td>
            <td>
                    ${machineAccount.treatmentMode}
            </td>
            <td>
                    ${machineAccount.treatmentResult}
            </td>
            <td>
                    ${machineAccount.riskPeople}
            </td>
            <td>
                    ${machineAccount.patientsReflectFocus}
            </td>
            <td>
                <c:choose>
                    <c:when test="${machineAccount.isMajor=='1' }">
                        是
                    </c:when>
                    <c:otherwise>
                        否
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${machineAccount.isMedia=='1' }">
                        是
                    </c:when>
                    <c:otherwise>
                        否
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                    ${machineAccount.riskTime}
            </td>
            <td>
                    ${machineAccount.disputesTime}
            </td>
            <td>
                    ${machineAccount.reportingTime}
            </td>

            <td>
                    ${machineAccount.acceptanceTime}
            </td>
            <td>
                    ${machineAccount.eighteenItems}
                    <%--${fns:getDictLabel(machineAccount.eighteenItems,'eighteen_items','未知')}--%>
            </td>

            <td>
                    ${machineAccount.meetingFrequency}
            </td>
            <td>
                    ${machineAccount.assessTime}
            </td>
            <td>
                    ${machineAccount.hostUser.name}
                    <%--${fns:getUserById(machineAccount.host).name}--%>
            </td>
            <td>
                    ${machineAccount.clerkUser.name}
                    <%--${fns:getUserById(machineAccount.clerk).name}--%>
            </td>
            <td>
                    ${machineAccount.medicalExpert}
            </td>
            <td>
                    ${machineAccount.legalExpert}
            </td>
            <td>
                    ${machineAccount.dutyRatio}
            </td>
            <td>
                    ${machineAccount.countAmount}
            </td>
            <td>
                    ${machineAccount.assessNumber}
            </td>
            <td>
                <c:choose>
                    <c:when test="${machineAccount.mediateResult eq 1}">
                        成功
                    </c:when>
                    <c:when test="${machineAccount.mediateResult eq 2}">
                        终止
                    </c:when>
                    <c:when test="${machineAccount.mediateResult eq 3}">
                        销案
                    </c:when>
                    <c:otherwise>

                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                    ${machineAccount.isJudicial}
            </td>
            <td>
                    ${machineAccount.agreementNumber}
            </td>
            <td>
                    ${machineAccount.ratifyAccord}
            </td>
            <td>
                    ${machineAccount.agreementStampTime}
            </td>
            <td>
                    ${machineAccount.flowDays}
            </td>

            <td>
                    ${machineAccount.agreementAmount}
            </td>
            <td>
                    ${machineAccount.insuranceAmount}
            </td>
            <td>
                    ${machineAccount.hospitalAmount}
            </td>
            <td>
                    ${machineAccount.claimSettlementTime}
            </td>
            <td>
                    ${machineAccount.claimSettlementDay}
            </td>
            <td>
                    ${machineAccount.insurancePayTime}
            </td>
            <td>
                    ${machineAccount.hospitalPayTime}
            </td>
            <td>
                    ${machineAccount.settlementFlowDays}
            </td>
            <td>
                    ${machineAccount.archiveTime}
            </td>
            <td>
                    ${machineAccount.handOver}
            </td>
            <td>
                    ${machineAccount.fileNumber}
            </td>
            <td>
                    ${machineAccount.assessGrade}
            </td>
            <td>
                    ${machineAccount.appraiser}
            </td>
            <td>
                    ${machineAccount.filePlace}
            </td>
            <td>
                    ${machineAccount.remark}
            </td>
            <td>
                    ${machineAccount.createBy.name}
            </td>
            <td>
                <fmt:formatDate value="${machineAccount.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                    ${machineAccount.updateBy.name}
            </td>
            <td>
                <fmt:formatDate value="${machineAccount.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <shiro:hasPermission name="machine:machineAccount:edit">
                <td>
                    <a href="${ctx}/machine/machineAccount/form?id=${machineAccount.machineAccountId}&type=view">详情</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>