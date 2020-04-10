<%@ page import="com.sayee.sxsy.modules.assessappraisal.entity.AssessAppraisal" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>评估鉴定管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#inputForm").validate({
                submitHandler: function(form){
                    var aa=$("#export").val();
                    if(aa=='no') {
                        loading('正在提交，请稍等...');
                    }
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

            var c="${assessAppraisal.applyType}"

            if(c==""){
                $("#a").text("医疗纠纷技术评估");
                $("#jd").hide();
                $("#pg").show();
            }else{
                $("#a").text("${fns:getDictLabel(assessAppraisal.applyType,'assessmentAppraisal','未知')}");
                $("#jd").show();
                $("#pg").hide();
            }
            if(${fns:getDictLabel(assessAppraisal.applyType,"assessmentAppraisal",'未知')=="医疗纠纷技术评估"}){
                $("#jd").hide();
                $("#pg").show();
            }else if(${fns:getDictLabel(assessAppraisal.applyType,"assessmentAppraisal",'未知')=="医疗责任保险事故鉴定"}){
                $("#jd").show();
                $("#pg").hide();
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
            $("#a1").hide();
        }
        function addRow2(list, idx, tpl, row){
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
            $("#a2").hide();

        }
        function addRow3(list, idx, tpl, row){
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
            $("#a3").hide();

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
            $("#a1").show();
        }
        function delRow2(obj, prefix,key){
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
            $("#a2").show();
        }
        function delRow3(obj, prefix,key){
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
            $("#a3").show();
        }

    </script>
</head>
<body>
<br/>
<form:form id="inputForm" modelAttribute="assessAppraisal" action="${ctx}/assessappraisal/assessAppraisal/save" method="post" class="form-horizontal">
    <form:hidden path="assessAppraisalId"/>
    <form:hidden path="createDate"/>
    <form:hidden path="createBy"/>
    <form:hidden path="recordInfo1.recordId"/>
    <form:hidden path="recordInfo1.yrecordInfo.recordId"/>
    <form:hidden path="proposal.proposalId"/>
    <form:hidden path="proposal.assessAppraisalId"/>
    <form:hidden path="complaintMainId"/>
    <form:hidden path="complaintMain.complaintMainId"/>
    <form:hidden path="complaintMain.act.taskId"/>
    <form:hidden path="complaintMain.act.taskName"/>
    <form:hidden path="complaintMain.act.taskDefKey"/>
    <form:hidden path="complaintMain.act.procInsId"/>
    <form:hidden path="complaintMain.act.procDefId"/>
    <form:hidden path="complaintMain.procInsId"/>
    <form:hidden id="flag" path="complaintMain.act.flag"/>
    <form:hidden path="complaintMain.hospital.name"/>
    <form:hidden path="clerks.name"/>
    <form:hidden path="hosts.name"/>
    <%--<form:hidden path="recordInfo1.patient"/>--%>
    <form:hidden path="recordInfo1.doctor"/>
    <form:hidden path="recordInfo1.host"/>
    <form:hidden path="recordInfo1.noteTaker"/>
    <form:hidden path="recordInfo1.yrecordInfo.patient"/>
    <form:hidden path="recordInfo1.yrecordInfo.doctor"/>
    <form:hidden path="recordInfo1.yrecordInfo.recordAddress"/>
    <form:hidden path="recordInfo1.yrecordInfo.host"/>
    <form:hidden path="recordInfo1.yrecordInfo.noteTaker"/>
    <form:hidden path="complaintMain.patientSex"/>
    <input type="hidden" id="aa" name="proposal.proposalCode" value="${assessAppraisal.proposal.proposalCode}"/>
    <input type="hidden"  id="export" name="export"/>
    <sys:message content="${message}"/>
<fieldset>
    <ul id="myTab1" class="nav nav-tabs">
        <li class="active">
            <a href="#pgjd" data-toggle="tab">评估鉴定</a>
        </li>
        <c:if test="${empty show2}">
            <li>
                <a href="#details" data-toggle="tab">详情</a>
            </li>
        </c:if>
    </ul>
    <legend>评估鉴定详情</legend>

    <div id="myTabContent1" class="tab-content">
        <div id="pgjd" class="tab-pane fade in active">
            <ul id="myTab" class="nav nav-tabs">
                <li class="active">
                    <a href="#meetings" data-toggle="tab">评估鉴定会议表</a>
                </li>
                <li>
                    <a href="#recordhf" data-toggle="tab">评估鉴定会笔录(患方)</a>
                </li>
                <li>
                    <a href="#recordyf" data-toggle="tab">评估鉴定会笔录(医方)</a>
                </li>
                <li>
                    <a href="#zjopinion" data-toggle="tab">专家评估意见</a>
                </li>
                <li>
                    <a href="#opinion" data-toggle="tab">意见书</a>
                </li>
                <li>
                    <a href="#fj" data-toggle="tab">附件</a>
                </li>
            </ul>
            <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="meetings">
            <table class="table-form">
                <tr>
                    <td class="tit" width="15%">
                        会议类型
                    </td>
                    <td colspan="4">
                            ${fns:getDictLabel(assessAppraisal.applyType, 'assessmentAppraisal', '未知')}
                            <%--<form:select path="applyType" class="input-medium" cssStyle="text-align:center;width: 15%;" onchange="show_input(this.value,'a')">--%>
                            <%--<form:options items="${fns:getDictList('assessmentAppraisal')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
                            <%--</form:select>--%>
                    </td>
                </tr>
                <tr>
                    <td class="tit">时间</td>
                    <td>
                            ${assessAppraisal.recordInfo1.startTime}
                    </td>
                    <td class="tit" width="10%">至</td>
                    <td colspan="2">
                            ${assessAppraisal.recordInfo1.endTime}
                    </td>
                </tr>
                <tr>
                    <td class="tit">地点</td>
                    <td colspan="4">
                            ${fns:getDictLabel(assessAppraisal.recordInfo1.recordAddress,'meeting','')}
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        一、主持人向评估委员会成员介绍纠纷概要、纠纷焦点
                    </td>
                    <td colspan="4"></td>
                </tr>
                <tr>
                    <td class="tit">
                        二、医患双方代表进入会场
                    </td>
                    <td colspan="4"></td>
                </tr>
                <tr>
                    <td class="tit" rowspan="6">三、介绍评估委员会成员、确认医患双方及参会人员身份</td>
                    <td class="tit" width="5%">医学专家:</td>
                    <td>
                            ${assessAppraisal.medicalExpertName}
                    </td>
                    <td class="tit" width="18%">法学专家:</td>
                    <td>
                            ${assessAppraisal.legalExpertName}
                    </td>
                </tr>
                <tr>
                    <td class="tit">主持人:</td>
                    <td>
                            ${assessAppraisal.hosts.name}
                    </td>
                    <td class="tit">书记员:</td>
                    <td>
                            ${assessAppraisal.clerks.name}
                    </td>
                </tr>
                <tr>
                    <td class="tit">患方:</td>
                    <td colspan="3">
                            ${assessAppraisal.recordInfo1.patient}
                    </td>
                </tr>
                <tr>
                    <td class="tit">医方:</td>
                    <td colspan="3">
                            ${assessAppraisal.complaintMain.hospital.name}
                    </td>
                </tr>
                <tr>
                    <td class="tit" rowspan="2">请问医患双方确认以上参会人员身份有无要求回避</td>
                    <td class="tit">患方:</td>
                    <td colspan="2">
                            ${assessAppraisal.patientAvoid}
                    </td>
                </tr>
                <tr>
                    <td class="tit">医方:</td>
                    <td colspan="2">
                            ${assessAppraisal.doctorAvoid}
                    </td>
                </tr>
                <tr>
                    <td class="tit" rowspan="3">四、宣读有关纪律及注意事项</td>
                    <td colspan="4">
                        <p style="margin:0pt">
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">1</span>
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
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">2</span>
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
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">3</span>
                            <span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、医患双方要如实陈述纠纷事实，提供的证据应当真实、合法、有效，不得伪造、毁灭证据，妨碍评估人员正确作出调解。</span>
                        </p>
                        <p style="margin:0pt">
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">4</span>
                            <span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、双方当事人不得因纠纷矛盾相互指责、谩骂使用人身攻击性语言，激化矛盾；不得对评估委员会成员进行威胁、恐吓，以非法手段 干扰评估会的正常工作秩序。</span>
                        </p>
                        <p style="margin:0pt">
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">5</span>
                            <span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、当事人或代理人陈述时，其他参加人员不得发言，需要补充时，须在当事人或代理人结束发言后，经主持人同意方可进行补充。</span>
                        </p>
                        <p style="margin:0pt">
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">6</span>
                            <span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、对于有不良行为的参加人，山西省医疗纠纷人民调解委员会将责令其退出会议室。</span>
                        </p>
                        <p style="margin:0pt">
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">7</span>
                            <span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、要求各位专家客观公正进行分析并得出结论。</span>
                        </p>
                        <p style="margin:0pt">
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">8</span>
                            <span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、本次会议的主要目的是要通过医学专家评估及法律顾问责任认定，完成本次医疗纠纷的定责、定损、计赔事宜，评估结论将会在会后3到5个工作日内由主管调解员负责口头告知医患双方。若医患双方对评估结论有异议，有权申请医学会鉴定或司法诉讼。</span>

                        </p>
                    </td>
                </tr>
                <tr>
                    <td class="tit" rowspan="2">以上宣读内容医患双方是否听清楚？有无异议？</td>
                    <td class="tit">患方:</td>
                    <td colspan="2">
                            ${assessAppraisal.patientClear}
                    </td>
                </tr>
                <tr>
                    <td class="tit">医方:</td>
                    <td colspan="2">
                            ${assessAppraisal.doctorClear}
                    </td>
                </tr>
                <tr>
                    <td class="tit">五、患方陈述、提出诉求，提交证明材料。</td>
                    <td colspan="4"></td>
                </tr>
                <tr>
                    <td class="tit">六、医方陈述，提供证明材料。</td>
                    <td colspan="4"></td>
                </tr>
                <tr>
                    <td class="tit">七、医方退场。</td>
                    <td colspan="4"></td>
                </tr>
                <tr>
                    <td class="tit">八、专家提问、质证</td>
                    <td colspan="4"></td>
                </tr>
                <tr>
                    <td class="tit">九、患方确认笔录，签名，按手印，退场。</td>
                    <td colspan="4"></td>
                </tr>
                <tr>
                    <td class="tit">十、医方入场，专家提问，质证。</td>
                    <td colspan="4"></td>
                </tr>
                <tr>
                    <td class="tit">十一、医方确认笔录，签名、按手印、注明日期，退场</td>
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
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">时间：</span>
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
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">时间：</span>
                        </p>
                    </td>
                </tr>
                <tr>
                    <td class="tit">十二、评估组成员进行讨论分析，并在规定时间内得出结论。</td>
                    <td colspan="4">
                        <p style="margin:0pt">
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">主持人署名：</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">时间：</span>
                        </p>
                        <p style="margin:0pt">
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">书记人署名：</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
                            <span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">时间：</span>
                        </p>
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade " id="recordhf">
            <table class="table-form">
                <tr>
                    <td class="tit" width="200px;">
                        笔录内容：
                    </td>
                    <td colspan="3">
                        <form:textarea path="recordInfo1.recordContent" htmlEscape="false" rows="20" maxlength="500" class="input-xxlarge required" cssStyle="width: 80%;font-size: 16px;" disabled="true"/>
                    </td>

                </tr>
            </table>
        </div>
        <div class="tab-pane fade " id="recordyf">
            <table class="table-form">
                <tr>
                    <td class="tit" width="200px;">
                        笔录内容：
                    </td>
                    <td colspan="3">
                        <form:textarea path="recordInfo1.yrecordInfo.recordContent" htmlEscape="false" rows="20" maxlength="500" class="input-xxlarge required" cssStyle="width: 80%;font-size: 16px;" disabled="true"/>
                    </td>

                </tr>
            </table>
        </div>
        <div class="tab-pane fade " id="zjopinion">
            <table class="table-form">
                <tr>
                    <td class="tit" width="15%">
                        患者姓名：
                    </td>
                    <td width="35%">
                        <form:input path="complaintMain.patientName" htmlEscape="false" maxlength="10" class="input-xlarge required" readonly="true"/>
                    </td>
                    <td class="tit" width="15%">
                        患者性别：
                    </td>
                    <td width="35%">
                        <c:choose>
                            <c:when test="${assessAppraisal.complaintMain.patientSex=='1'}">
                                男
                            </c:when>
                            <c:when test="${assessAppraisal.complaintMain.patientSex=='2'}">
                                女
                            </c:when>
                            <c:otherwise>
                                无
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        患者年龄：
                    </td>
                    <td>
                        <form:input path="complaintMain.patientAge" htmlEscape="false" maxlength="32" class="input-xlarge required" readonly="true"/>
                    </td>
                    <td class="tit">
                        住院号：
                    </td>
                    <td>
                        <form:input path="hospitalNumber" htmlEscape="false" maxlength="32" class="input-xlarge required" disabled="true"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        涉及医院：
                    </td>
                    <td colspan="3">
                            ${assessAppraisal.complaintMain.hospital.name}
                    </td>

                </tr>
                <tr>
                    <td class="tit">诊断分析：</td>
                    <td colspan="3">
                        <form:textarea path="diagnosticAnalysis" htmlEscape="false" rows="2" maxlength="500" class="input-xxlarge required" cssStyle="width: 560px;" disabled="true"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">治疗分析：</td>
                    <td colspan="3">
                        <form:textarea path="treatmentAnalysis" htmlEscape="false" rows="2" maxlength="500" class="input-xxlarge required" cssStyle="width: 560px;" disabled="true"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">其他医疗分析：</td>
                    <td colspan="3">
                        <form:textarea path="otherMedicalAnalysis" htmlEscape="false" rows="2" maxlength="500" class="input-xxlarge required" cssStyle="width: 560px;" disabled="true"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">违反18项核心制度：</td>
                    <td colspan="2">
                        <form:select path="eighteenItems" cssStyle="width: 60%; text-align: center;">
                            <form:options items="${fns:getDictList('eighteen_items')}" itemLabel="label" itemValue="value" htmlEscape="false" disabled="true"/>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        责任度：
                    </td>
                    <td>
                        <form:select path="responsibilityRatio" class="input-medium" style="text-align:center" disabled="true">
                            <%--<form:options items="${fns:getDictList('assessmentAppraisal')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
                            <form:option value="无责"/>
                            <form:option value="轻微责任"/>
                            <form:option value="次要责任"/>
                            <form:option value="对等责任"/>
                            <form:option value="主要责任"/>
                            <form:option value="全部责任"/>
                        </form:select>
                    </td>
                    <td class="tit">比例(%):</td>
                    <td>
                            ${assessAppraisal.scale}
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        法律顾问分析：
                    </td>
                    <td>
                        <form:input path="legalExpert" htmlEscape="false" maxlength="32" class="input-xlarge required" cssStyle="width: 560px;" disabled="true"/>
                    </td>
                    <td class="tit">
                        计算金额：
                    </td>
                    <td>
                        <form:input path="calculatedAmount" htmlEscape="false" class="input-xlarge required number" maxlength="10"  disabled="true"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        其他：
                    </td>
                    <td colspan="3">
                        <form:input path="other" htmlEscape="false" maxlength="32" class="input-xlarge required" cssStyle="width: 560px;" disabled="true"/>
                    </td>
                </tr>

            </table>
        </div>
        <div class="tab-pane fade " id="opinion">
            <div id="pgjdMyTabContent1" class="tab-content">
                <div class="tab-pane fade in active" id="affected">
                    <h2 style="margin: auto; width: 600px;">
						<span id="a">
						</span>
                        <span style="display: inline-block;text-align:center;width: 100px;">意见书</span>
                    </h2>
                    <p style="float:right;width:300px;height: 25px;font-size: 20px;" id="code">
                            ${assessAppraisal.proposal.proposalCode}
                    </p>
                    <table id="patientTable" class="table table-striped table-bordered table-condensed">
                        <legend style="color: black;">一、基本情况</legend>

                        <legend>患方</legend>
                        <thead>
                        <tr>
                            <th class="hide"></th>
                            <th >姓名</th>
                            <th >性别</th>
                            <th >身份证号</th>
                            <th >住址</th>
                        </tr>
                        </thead>
                        <tbody id="patientLinkEmpList"></tbody>
                    </table>
                    <script type="text/template" id="patientLinkEmpTp">
                        <tr id="patientLinkEmpList{{idx}}">
                            <td class="hide">
                            <td class="hide">
                                <input id="patientLinkEmpList{{idx}}_id" name="patientLinkEmpList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
                                <input id="patientLinkEmpList{{idx}}_patientLinkEmpId" name="patientLinkEmpList[{{idx}}].patientLinkEmpId" type="hidden" value="{{row.patientLinkEmpId}}"/>
                                <input id="patientLinkEmpList{{idx}}_linkType" name="patientLinkEmpList[{{idx}}].linkType" type="hidden" value="{{row.linkType}}"/>
                                <input id="patientLinkEmpList{{idx}}_relationId" name="patientLinkEmpList[{{idx}}].relationId" type="hidden" value="{{row.relationId}}"/>
                                <input id="patientLinkEmpList{{idx}}_delFlag" name="patientLinkEmpList[{{idx}}].delFlag" type="hidden" value="0"/>
                            </td>
                            </td>

                            <td>
                                <input id="patientLinkEmpList{{idx}}_patientLinkName" name="patientLinkEmpList[{{idx}}].patientLinkName" type="text" value="{{row.patientLinkName}}" maxlength="100" class="required" disabled="disabled"/>
                            </td>
                            <td>
                                <select id="patientLinkEmpList{{idx}}_patientLinkSex" name="patientLinkEmpList[{{idx}}].patientLinkSex" data-value="{{row.patientLinkSex}}" class="input-mini" disabled="disabled">
                                    <option value=""></option>
                                    <option value="1">男</option>
                                    <option value="2">女</option>
                                </select>
                            </td>
                            <td>
                                <input id="patientLinkEmpList{{idx}}_idNumber" name="patientLinkEmpList[{{idx}}].idNumber" type="text" value="{{row.idNumber}}" maxlength="20" class="required" disabled="disabled"/>
                            </td>
                            <td>
                                <input id="patientLinkEmpList{{idx}}_patientLinkAddress" name="patientLinkEmpList[{{idx}}].patientLinkAddress" type="text" value="{{row.patientLinkAddress}}" maxlength="20" class="required" disabled="disabled"/>
                            </td>
                        </tr>
                    </script>
                    <table id="patientDTable" class="table table-striped table-bordered table-condensed">
                        <legend>患方联系人</legend>
                        <thead>
                        <tr>
                            <th class="hide"></th>
                            <th >姓名</th>
                            <th >与患者关系</th>
                            <th >电话</th>
                        </tr>
                        </thead>
                        <tbody id="patientLinkDList"></tbody>
                    </table>
                    <script type="text/template" id="patientLinkDTp">
                        <tr id="patientLinkDList{{idx}}">
                            <td class="hide">
                                <input id="patientLinkDList{{idx}}_id" name="patientLinkDList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
                                <input id="patientLinkDList{{idx}}_patientLinkEmpId" name="patientLinkDList[{{idx}}].patientLinkEmpId" type="hidden" value="{{row.patientLinkEmpId}}"/>
                                <input id="patientLinkDList{{idx}}_linkType" name="patientLinkDList[{{idx}}].linkType" type="hidden" value="{{row.linkType}}"/>
                                <input id="patientLinkDList{{idx}}_relationId" name="patientLinkDList[{{idx}}].relationId" type="hidden" value="{{row.relationId}}"/>
                                <input id="patientLinkDList{{idx}}_delFlag" name="patientLinkDList[{{idx}}].delFlag" type="hidden" value="0"/>
                            </td>

                            <td>
                                <input id="patientLinkDList{{idx}}_patientLinkName" name="patientLinkDList[{{idx}}].patientLinkName" type="text" value="{{row.patientLinkName}}" maxlength="100" class="required" disabled="disabled"/>
                            </td>
                            <td>
                                <select id="patientLinkDList{{idx}}_patientRelation" name="patientLinkDList[{{idx}}].patientRelation" value="{{row.patientRelation}}" data-value="{{row.patientRelation}}" class="input-mini" disabled="disabled">
                                    <option value=""></option>
                                    <option value="1"  >亲人</option>
                                    <option value="2"  >朋友</option>
                                    <option value="3"  >代理人</option>
                                </select>
                            </td>
                            <td>
                                <input id="patientLinkDList{{idx}}_patientLinkMobile" name="patientLinkDList[{{idx}}].patientLinkMobile" type="text" value="{{row.patientLinkMobile}}" maxlength="20" class="required" disabled="disabled"/>
                            </td>
                        </tr>
                    </script>
                    <legend>医方</legend>
                    <table id="hospitalTable" class="table table-striped table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th class="hide"></th>
                            <th >医疗机构名称</th>
                            <th >委托代理人</th>
                            <th >联系方式</th>
                            <th >职务</th>
                        </tr>
                        </thead>
                        <tbody id="medicalOfficeEmpList"></tbody>
                    </table>
                    <script type="text/template" id="medicalOfficeEmpTp">
                        <tr id="medicalOfficeEmpList{{idx}}">
                            <td class="hide">
                                <input id="medicalOfficeEmpList{{idx}}_id" name="medicalOfficeEmpList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
                                <input id="medicalOfficeEmpList{{idx}}_medicalOfficeEmpId" name="medicalOfficeEmpList[{{idx}}].medicalOfficeEmpId" type="hidden" value="{{row.medicalOfficeEmpId}}"/>
                                <input id="medicalOfficeEmpList{{idx}}_relationId" name="medicalOfficeEmpList[{{idx}}].relationId" type="hidden" value="{{row.relationId}}"/>
                                <input id="medicalOfficeEmpList{{idx}}_delFlag" name="medicalOfficeEmpList[{{idx}}].delFlag" type="hidden" value="0"/>
                            </td>

                            <td>
                                <input id="medicalOfficeEmpList{{idx}}_medicalOfficeName" name="medicalOfficeEmpList[{{idx}}].medicalOfficeName" type="text" value="{{row.medicalOfficeName}}" maxlength="100" class="required" disabled="disabled"/>
                            </td>
                            <td>
                                <input id="medicalOfficeEmpList{{idx}}_medicalOfficeAgent" name="medicalOfficeEmpList[{{idx}}].medicalOfficeAgent" type="text" value="{{row.medicalOfficeAgent}}" maxlength="32" class="required" disabled="disabled"/>
                            </td>
                            <td>
                                <input id="medicalOfficeEmpList{{idx}}_medicalOfficeMobile" name="medicalOfficeEmpList[{{idx}}].medicalOfficeMobile" type="text" value="{{row.medicalOfficeMobile}}" maxlength="200" class="required" disabled="disabled"/>
                            </td>
                            <td>
                                <input id="medicalOfficeEmpList{{idx}}_medicalOfficePost" name="medicalOfficeEmpList[{{idx}}].medicalOfficePost" type="text" value="{{row.medicalOfficePost}}" maxlength="20" class="required" disabled="disabled"/>
                            </td>
                        </tr>
                    </script>
                    <legend style="color: black;">二、评估时间</legend>
                    <span style="font-family:宋体; font-size:16pt; font-weight:normal; text-decoration:underline;color: black;" id="time1">

					</span>
                    <legend style="color: black;">三、诊疗概要</legend>
                    <table class="table-form">
                        <tr>
                            <td colspan="3">
                                <form:textarea path="proposal.treatmentSummary" htmlEscape="false" rows="10" maxlength="500" class="input-xxlarge required" cssStyle="width: 1374px;" disabled="true"/>
                            </td>
                        </tr>
                    </table>
                    <legend style="color: black;">四、争议要点</legend>
                    <table class="table-form">
                        <tr>
                            <td class="tit">患方认为:</td>
                            <td colspan="3">
                                <form:textarea path="proposal.patientThink" htmlEscape="false" rows="5" maxlength="500" class="input-xxlarge required" cssStyle="width: 1374px;" disabled="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tit">医方认为:</td>
                            <td colspan="3">
                                <form:textarea path="proposal.doctorThink" htmlEscape="false" rows="5" maxlength="500" class="input-xxlarge required" cssStyle="width: 1374px;" disabled="true"/>
                            </td>
                        </tr>
                    </table>
                    <legend style="color: black;">五、分析意见</legend>
                    <table class="table-form" id="fxyj">

                        <tr>
                            <th></th>
                            <th>类型</th>
                            <th>内容</th>
                        </tr>
                        <c:forEach items="${fxyj}" var="column" varStatus="vs">
                            <tr>
                                <td nowrap style="text-align:center;vertical-align:middle;">
                                    <input type="hidden" name="typeInfosList[${vs.index}].typeId" value="${column.typeId}" />
                                    <input type="hidden" name="typeInfosList[${vs.index}].delFlag" value="${column.delFlag}"/>
                                    <input type="checkbox" name="typeInfosList[${vs.index}].label" value="1" ${column.label eq '1' ? 'checked' : ''} onclick="clearCheckBox(this.name,'fxyj')" disabled="disabled"/>
                                </td>
                                <td style="text-align:center;vertical-align:middle;">${column.typeName}</td>
                                <td style="text-align:center;vertical-align:middle;">${column.content}</td>
                            </tr>
                        </c:forEach>
                    </table>
                    <table class="table-form">
                        <tr>
                            <td class="tit">
                                诊断:
                            </td>
                            <td>
                                <form:textarea path="proposal.diagnosis" htmlEscape="false" rows="2" maxlength="500" class="input-xxlarge required" cssStyle="width: 1374px;" disabled="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tit">
                                治疗:
                            </td>
                            <td>
                                <form:textarea path="proposal.treatment" htmlEscape="false" rows="2" maxlength="500" class="input-xxlarge required" cssStyle="width: 1374px;" disabled="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tit">
                                其他:
                            </td>
                            <td>
                                <form:textarea path="proposal.other" htmlEscape="false" rows="2" maxlength="500" class="input-xxlarge required" cssStyle="width: 1374px;" disabled="true"/>
                            </td>
                        </tr>
                    </table>
                    <legend style="color: black;">六、结论</legend>
                    <table class="table-form" id="jl">
                        <tr>

                            <td></td>
                            <th>类型</th>
                            <th>内容</th>
                        </tr>
                        <c:forEach items="${jl}" var="column" varStatus="vs">
                            <tr id="${column.type1}">
                                <td nowrap style="text-align:center;vertical-align:middle;">
                                    <input type="hidden" name="typeInfosList2[${vs.index}].typeId" value="${column.typeId}"/>
                                    <input type="hidden" name="typeInfosList2[${vs.index}].delFlag" value="${column.delFlag}"/>
                                    <input type="checkbox" name="typeInfosList2[${vs.index}].label" value="1" ${column.label eq '1' ? 'checked' : ''} onclick="clearCheckBox(this.name,'jl')" disabled="disabled"/>
                                </td>

                                <td style="text-align:center;vertical-align:middle;">
                                        ${column.typeName}
                                </td>
                                <td style="text-align:center;vertical-align:middle;">
                                        ${column.content}
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <legend style="color: black;">七、说明</legend>
                    <span style="font-size: 20px;">该意见仅作为山西省医疗纠纷人民调解委员会调解医疗纠纷的参考，不具备法律效率。</span><br><br>
                </div>

            </div>
        </div>
        <div class="tab-pane fade " id="fj">
            <table class="table-form">
                <tr style=" " >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">签到表：</td>
                    <input type="hidden"  name="fjtype1" value="16">
                    <td style="width: 450px; ">

                        <input type="hidden" id="files1" name="files1" htmlEscape="false" class="input-xlarge"  value="${files1}"/>
                        <input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files"  uploadPath="/assessappraisal/assessAppraisal/qiandao" selectMultiple="true" readonly="true"/></div>
                    </td>

                </tr>
                <tr style=" " >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">患方笔录：</td>
                    <input type="hidden"  name="fjtype2" value="17">
                    <td style="width: 450px; ">

                        <input type="hidden" id="files2" name="files2" htmlEscape="false" class="input-xlarge"  value="${files2}"/>
                        <input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files2" type="files"  uploadPath="/assessappraisal/assessAppraisal/huanbl" selectMultiple="true" readonly="true"/></div>
                    </td>

                </tr>
                <tr style="" >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">患方补充材料：</td>
                    <input type="hidden" name="fjtype3" value="18">
                    <td style="width: 450px; ">
                        <input type="hidden" id="files3" name="files3" htmlEscape="false" class="input-xlarge" value="${files3}" />
                        <input type="hidden" id="acceId3" name="acceId3" value="${acceId3}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files3" type="files"  uploadPath="/assessappraisal/assessAppraisal/huanbc" selectMultiple="true" readonly="true"/></div>
                    </td>

                </tr>
                <tr style="" >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医方笔录：</td>
                    <input type="hidden"  name="fjtype4" value="19">
                    <td style="width: 450px; ">

                        <input type="hidden" id="files4" name="files4" htmlEscape="false" class="input-xlarge"  value="${files4}"/>
                        <input type="hidden" id="acceId4" name="acceId4" value="${acceId4}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files4" type="files"  uploadPath="/assessappraisal/assessAppraisal/yibl" selectMultiple="true" readonly="true"/></div>
                    </td>

                </tr>
                <tr style="" >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医方补充材料：</td>
                    <input type="hidden" name="fjtype5" value="20">
                    <td style="width: 450px;">
                        <input type="hidden" id="files5" name="files5" htmlEscape="false" class="input-xlarge" value="${files5}" />
                        <input type="hidden" id="acceId5" name="acceId5" value="${acceId5}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files5" type="files"  uploadPath="/assessappraisal/assessAppraisal/yibc" selectMultiple="true" readonly="true"/></div>
                    </td>

                </tr>
                <tr style="" >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">专家评估意见表：</td>
                    <input type="hidden" name="fjtype6" value="21">
                    <td style="width: 450px;">
                        <input type="hidden" id="files6" name="files6" htmlEscape="false" class="input-xlarge" value="${files6}" />
                        <input type="hidden" id="acceId6" name="acceId6" value="${acceId6}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files6" type="files"  uploadPath="/assessappraisal/assessAppraisal/zhuanjiayj" selectMultiple="true" readonly="true"/></div>
                    </td>

                </tr>
                <tr style="" >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医疗损害核算单：</td>
                    <input type="hidden" name="fjtype7" value="22">
                    <td style="width: 450px;">
                        <input type="hidden" id="files7" name="files7" htmlEscape="false" class="input-xlarge" value="${files7}" />
                        <input type="hidden" id="acceId7" name="acceId7" value="${acceId7}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files7" type="files"  uploadPath="/assessappraisal/assessAppraisal/yiliaosh" selectMultiple="true" readonly="true"/></div>
                    </td>

                </tr>
            </table>
        </div>
    </div>
            <table class="table-form">
                <tr>

                    <td class="tit" width="215px">
                        下一环节处理人：
                    </td>
                    <td>
                        <sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${empty assessAppraisal.nextLinkMan?fns:getUser().id:assessAppraisal.nextLinkMan}" labelName="" labelValue="${empty assessAppraisal.linkEmployee.name?fns:getUser().name:assessAppraisal.linkEmployee.name}"
                                        title="用户" url="/sys/office/treeData?type=3&officeType=1" dataMsgRequired="必填信息" cssClass="required" allowClear="true" notAllowSelectParent="true" />
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
            </div>
        </div>
    </div>
</fieldset>
    <c:if test="${empty show2}">
        <div class="form-actions">
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
        <act:histoicFlow procInsId="${assessAppraisal.complaintMain.procInsId}" />
    </c:if>
</form:form>
<script type="text/javascript">
    var medicalOfficeEmpRowIdx = 0, medicalOfficeEmpTp = $("#medicalOfficeEmpTp").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
    var patientLinkEmpRowIdx = 0, patientLinkEmpTp = $("#patientLinkEmpTp").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
    var patientLinkDRowIdx = 0, patientLinkDTp = $("#patientLinkDTp").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
    $(document).ready(function() {
        var data = ${fns:toJson(assessAppraisal.medicalOfficeEmpList)};
        if(0!=data.length){
            $("#a3").hide()
        }
        for (var i=0; i<data.length; i++){
            addRow3('#medicalOfficeEmpList', medicalOfficeEmpRowIdx, medicalOfficeEmpTp, data[i]);
            medicalOfficeEmpRowIdx = medicalOfficeEmpRowIdx + 1;
        }

        var data1 = ${fns:toJson(assessAppraisal.patientLinkDList)};
        if(0!=data1.length){
            $("#a2").hide()
        }
        for (var i=0; i<data1.length; i++){
            addRow2('#patientLinkDList', patientLinkDRowIdx, patientLinkDTp, data1[i]);
            patientLinkDRowIdx = patientLinkDRowIdx + 1;
        }

        var PatientData = ${fns:toJson(assessAppraisal.patientLinkEmpList)};
        if(0!=PatientData.length){
            $("#a1").hide()
        }
        for (var i=0; i<PatientData.length; i++){
            addRow('#patientLinkEmpList', patientLinkEmpRowIdx, patientLinkEmpTp, PatientData[i]);
            patientLinkEmpRowIdx = patientLinkEmpRowIdx + 1;
        }

    });
</script>
</body>
</html>
