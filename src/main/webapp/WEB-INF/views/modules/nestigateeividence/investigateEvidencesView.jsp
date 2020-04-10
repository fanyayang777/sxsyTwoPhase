<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>调查取证管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            //投诉接待详情
            $("#tsjdDetail").attr("src","${ctx}/complaintdetail/complaintMainDetail/form?id=${map.tsjd}&type=view");
            var tsjd= document.getElementById("tsjdDetail");
            tsjd.height=document.documentElement.clientHeight-130;
            tsjd.width=document.documentElement.clientWidth;
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
        });
    </script>
</head>
<body>
<%--<ul class="nav nav-tabs">--%>
<%--<li><a href="${ctx}/nestigateeividence/investigateEvidence/">列表</a></li>--%>
<%--<li class="active"><a href="${ctx}/nestigateeividence/investigateEvidence/form?id=${investigateEvidence.id}" id="c">调查取证<shiro:hasPermission name="nestigateeividence:investigateEvidence:edit">${not empty investigateEvidence.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="nestigateeividence:investigateEvidence:edit">查看</shiro:lacksPermission></a></li>--%>
<%--</ul><br/>--%>
<br/>
<form:form id="inputForm" modelAttribute="investigateEvidence" action="${ctx}/nestigateeividence/investigateEvidence/save" method="post" class="form-horizontal">
    <form:hidden path="investigateEvidenceId"/>
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
    <form:hidden path="investigateEvidence.investigateEvidenceId"/>
    <form:hidden path="investigateEvidence.complaintMainId"/>
    <form:hidden path="investigateEvidence.createDate"/>
    <form:hidden path="investigateEvidence.createBy"/>
    <form:hidden path="investigateEvidence.investigateType"/>
    <form:hidden path="investigateEvidence.nextLinkMan"/>
    <sys:message content="${message}"/>
<fieldset>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#dcqz" data-toggle="tab">调查取证</a>
        </li>
        <c:if test="${empty show2}">
        <li>
            <a href="#details" data-toggle="tab">详情</a>
        </li>
        </c:if>
    </ul>
    <legend>调查取证详情</legend>
    <div id="myTabContent" class="tab-content">
    <div id="dcqz" class="tab-pane fade in active">
    <ul id="myTab1" class="nav nav-tabs">
        <li class="active">
            <a href="#visitor" data-toggle="tab">患方调查笔录</a>
        </li>
        <li>
            <a href="#patient" data-toggle="tab">医方调查笔录</a>
        </li>
        <li>
            <a href="#hospital" data-toggle="tab">附件</a>
        </li>
    </ul>
    <div id="myTabContent1" class="tab-content">
        <div class="tab-pane fade in active" id="visitor">
            <table class="table-form">
                <tr >
                    <td class="tit" width="220px">
                        <font color="red">*</font>调查时间：
                    </td>
                    <td width="460px">
                            ${investigateEvidence.startTime}
                    </td>
                    <td class="tit" width="220px"><font color="red">*</font>结束时间：</td>
                    <td width="460px">
                            ${investigateEvidence.endTime}
                    </td>
                </tr>
                <tr >
                    <td class="tit"><font color="red">*</font>调查地点：</td>
                    <td >
                            ${investigateEvidence.addressLabel}
                    </td>
                    <td class="tit"><font color="red">*</font>调查事由：</td>

                    <td>
                            ${investigateEvidence.cause}
                    </td>
                </tr>
                <tr >
                    <td class="tit"><font color="red">*</font>调查人：</td>
                    <td >
                            ${investigateEvidence.investigator}
                    </td>
                    <td class="tit"><font color="red">*</font>记录人：</td>

                    <td>
                            ${investigateEvidence.noteTaker}
                    </td>
                </tr>
                <%--<tr >
                    <td class="tit"><font color="red">*</font>反应焦点：</td>
                    <td >
                            ${investigateEvidence.focus}
                    </td>
                </tr>--%>
                <tr>
                    <td class="tit"><font color="red">*</font>笔录内容：</td>
                    <td colspan="3">
                        <form:textarea path="content" htmlEscape="false" rows="15" maxlength="500" class="input-xxlarge required" cssStyle="width:900px;" readonly="true"/>
                    </td>
                </tr>
            </table>
            <ul  class="nav nav-tabs">
                <li class="active">
                    <a href="#investigation1" data-toggle="tab">被调查人1</a>
                </li>
                <li>
                    <a href="#investigation2" data-toggle="tab">被调查人2</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane fade in active" id="investigation1">
                    <table class="table-form">

                        <tr >
                            <td class="tit" width="140px" ><font color="red">*</font>姓名：</td>
                            <td width="150px">
                                    ${investigateEvidence.respondentInfo.respondentName}
                            </td>
                            <td class="tit" width="80px"><font color="red">*</font>性别：</td>
                            <td width="180px">
                                    ${fns:getDictLabel(investigateEvidence.respondentInfo.respondentSex,'sex', '无')}
                            </td>
                            <td class="tit" width="80px"><font color="red">*</font>年龄：</td>
                            <td width="60px">
                                    ${investigateEvidence.respondentInfo.respondentAge}
                            </td>
                            <td class="tit" width="70px"><font color="red">*</font>联系方式：</td>
                            <td width="150px">
                                    ${investigateEvidence.respondentInfo.respondentMobile}
                            </td>
                            <td class="tit" width="70px"><font color="red">*</font>被调查人身份:</td>
                            <td class="280px">
                                    ${fns:getDictLabel(investigateEvidence.respondentInfo.respondentIdentity,'investigation', '无')}
                            </td>
                        </tr>
                        <tr >
                            <td class="tit" style=""><font color="red">*</font>工作单位:</td>
                            <td >
                                    ${investigateEvidence.respondentInfo.respondentWorkUnit}
                            </td>
                            <td class="tit"  ><font color="red">*</font>职务:</td>
                            <td >
                                    ${investigateEvidence.respondentInfo.respondentPost}
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="tab-pane " id="investigation2">
                    <table class="table-form">
                        <tr >
                            <td class="tit" width="140px" ><font color="red">*</font>姓名：</td>
                            <td width="150px">
                                    ${investigateEvidence.respondentInfo2.respondentName}
                            </td>
                            <td class="tit" width="80px"><font color="red">*</font>性别：</td>
                            <td width="180px">
                                    ${fns:getDictLabel(investigateEvidence.respondentInfo2.respondentSex,'sex', '无')}
                            </td>
                            <td class="tit" width="80px"><font color="red">*</font>年龄：</td>
                            <td width="60px">
                                    ${investigateEvidence.respondentInfo2.respondentAge}
                            </td>
                            <td class="tit" width="70px"><font color="red">*</font>联系方式：</td>
                            <td width="150px">
                                    ${investigateEvidence.respondentInfo2.respondentMobile}
                            </td>
                            <td class="tit" width="70px"><font color="red">*</font>被调查人身份:</td>
                            <td class="280px">
                                    ${fns:getDictLabel(investigateEvidence.respondentInfo2.respondentIdentity,'investigation', '无')}
                            </td>
                        </tr>
                        <tr >
                            <td class="tit" ><font color="red">*</font>工作单位:</td>
                            <td >
                                    ${investigateEvidence.respondentInfo2.respondentWorkUnit}
                            </td>
                            <td class="tit"><font color="red">*</font>职务:</td>
                            <td >
                                    ${investigateEvidence.respondentInfo2.respondentPost}
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div class="tab-pane fade" id="patient">
            <input type="hidden" name="investigateType2" value="2">
            <table class="table-form">
                <tr >
                    <td class="tit" width="220px"><font color="red">*</font>调查时间：</td>
                    <td width="460px">
                            ${investigateEvidence.investigateEvidence.startTime}
                    </td>
                    <td class="tit" width="220px"><font color="red">*</font>结束时间：</td>
                    <td width="460px">
                        ${investigateEvidence.investigateEvidence.endTime}
                    </td>
                </tr>
                <tr >
                    <td class="tit" ><font color="red">*</font>调查地点：</td>
                    <td >
                            ${investigateEvidence.investigateEvidence.addressLabel}
                    </td>
                    <td class="tit"><font color="red">*</font>调查事由：</td>

                    <td width="195px;">
                            ${investigateEvidence.investigateEvidence.cause}
                    </td>
                </tr>
                <tr >
                    <td class="tit"><font color="red">*</font>调查人：</td>
                    <td >
                            ${investigateEvidence.investigateEvidence.investigator}
                    </td>
                    <td class="tit"><font color="red">*</font>记录人：</td>

                    <td>
                            ${investigateEvidence.investigateEvidence.noteTaker}
                    </td>
                </tr>
                <tr >
                    <td class="tit"><font color="red">*</font>笔录内容：</td>
                    <td colspan="3">
                        <form:textarea path="investigateEvidence.content" htmlEscape="false" rows="15" maxlength="500" class="input-xxlarge required" cssStyle="width:900px;" readonly="true"/>
                    </td>
                </tr>
            </table>
            <ul  class="nav nav-tabs">
                <li class="active">
                    <a href="#investigation3" data-toggle="tab">被调查人1</a>
                </li>
                <li>
                    <a href="#investigation4" data-toggle="tab">被调查人2</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane fade in active" id="investigation3">
                    <table class="table-form">
                        <tr >
                            <td class="tit" width="140px" ><font color="red">*</font>姓名：</td>
                            <td width="150px">
                                    ${investigateEvidence.respondentInfo3.respondentName}
                            </td>
                            <td class="tit" width="80px"><font color="red">*</font>性别：</td>
                            <td width="180px">
                                    ${fns:getDictLabel(investigateEvidence.respondentInfo3.respondentSex,'sex', '无')}
                            </td>
                            <td class="tit" width="80px"><font color="red">*</font>年龄：</td>
                            <td width="60px">
                                    ${investigateEvidence.respondentInfo3.respondentAge}
                            </td>
                            <td class="tit" width="70px"><font color="red">*</font>联系方式：</td>
                            <td width="150px">
                                    ${investigateEvidence.respondentInfo3.respondentMobile}
                            </td>
                            <td class="tit" width="70px"><font color="red">*</font>被调查人身份:</td>
                            <td class="280px">
                                    ${fns:getDictLabel(investigateEvidence.respondentInfo3.respondentIdentity,'investigation', '无')}
                            </td>
                        </tr>
                        <tr >
                            <td class="tit"><font color="red">*</font>工作单位:</td>
                            <td>
                                    ${investigateEvidence.respondentInfo3.respondentWorkUnit}
                            </td>
                            <td class="tit" ><font color="red">*</font>职务:</td>
                            <td>
                                    ${investigateEvidence.respondentInfo3.respondentPost}
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="tab-pane fade" id="investigation4">
                    <table class="table-form">
                        <tr >
                            <td class="tit" width="140px" ><font color="red">*</font>姓名：</td>
                            <td width="150px">
                                    ${investigateEvidence.respondentInfo4.respondentName}
                            </td>
                            <td class="tit" width="80px"><font color="red">*</font>性别：</td>
                            <td width="180px">
                                    ${fns:getDictLabel(investigateEvidence.respondentInfo4.respondentSex,'sex', '无')}
                            </td>
                            <td class="tit" width="80px"><font color="red">*</font>年龄：</td>
                            <td width="60px">
                                    ${investigateEvidence.respondentInfo4.respondentAge}
                            </td>
                            <td class="tit" width="70px"><font color="red">*</font>联系方式：</td>
                            <td width="150px">
                                    ${investigateEvidence.respondentInfo4.respondentMobile}
                            </td>
                            <td class="tit" width="70px"><font color="red">*</font>被调查人身份:</td>
                            <td class="280px">
                                    ${fns:getDictLabel(investigateEvidence.respondentInfo4.respondentIdentity,'investigation', '无')}
                            </td>
                        </tr>
                        <tr >
                            <td class="tit"><font color="red">*</font>工作单位:</td>
                            <td>
                                    ${investigateEvidence.respondentInfo4.respondentWorkUnit}
                            </td>
                            <td class="tit"><font color="red">*</font>职务:</td>
                            <td >
                                    ${investigateEvidence.respondentInfo4.respondentPost}
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div class="tab-pane fade" id="hospital">
            <table class="table-form">

                <tr style=" " >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">患方笔录：</td>
                    <input type="hidden"  name="fjtype1" value="3">
                    <td style="width: 450px; ">

                        <input type="hidden" id="files1" name="files1" htmlEscape="false" class="input-xlarge"  value="${files1}"/>
                        <input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
                            <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                        <div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files"  uploadPath="/nestigateeividence/investigateEvidence/huanbilu" selectMultiple="true" maxWidth="100" maxHeight="100" readonly="true"/></div>
                    </td>

                </tr>
                <tr style="" >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">患方补充材料：</td>
                    <input type="hidden" name="fjtype2" value="4">
                    <td style="width: 450px; ">
                        <input type="hidden" id="files2" name="files2" htmlEscape="false" class="input-xlarge" value="${files2}" />
                        <input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">
                            <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                        <div style="margin-top: -45px;"><sys:ckfinder input="files2" type="files"  uploadPath="/nestigateeividence/investigateEvidence/huanbuchong" selectMultiple="true" maxWidth="100" maxHeight="100" readonly="true"/></div>
                    </td>

                </tr>
                <tr style="" >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医方笔录：</td>
                    <input type="hidden"  name="fjtype3" value="5">
                    <td style="width: 450px; ">

                        <input type="hidden" id="files3" name="files3" htmlEscape="false" class="input-xlarge"  value="${files3}"/>
                        <input type="hidden" id="acceId3" name="acceId1" value="${acceId3}">
                            <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                        <div style="margin-top: -45px;"><sys:ckfinder input="files3" type="files"  uploadPath="/nestigateeividence/investigateEvidence/yibilu" selectMultiple="true" maxWidth="100" maxHeight="100" readonly="true"/></div>
                    </td>

                </tr>
                <tr style="" >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医方补充材料：</td>
                    <input type="hidden" name="fjtype4" value="6">
                    <td style="width: 450px;">
                        <input type="hidden" id="files4" name="files4" htmlEscape="false" class="input-xlarge" value="${files4}" />
                        <input type="hidden" id="acceId4" name="acceId1" value="${acceId4}">
                            <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                        <div style="margin-top: -45px;"><sys:ckfinder input="files4" type="files"  uploadPath="/nestigateeividence/investigateEvidence/yibuchong" selectMultiple="true" maxWidth="100" maxHeight="100" readonly="true"/></div>
                    </td>

                </tr>
            </table>


        </div>
    </div>

    <table class="table-form" style="margin-top: 20px;">
        <tr  style="">
            <td class="tit" width="280px"><font color="red">*</font>下一环节处理人：</td>

            <td width="540px;">
                    ${investigateEvidence.linkEmployee.name}
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
            </div>
        </div>
    </div>
</fieldset>
    <c:if test="${empty show2}">
    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" style="margin-left: 550px;"/>
    </div>
    <act:histoicFlow procInsId="${investigateEvidence.complaintMain.procInsId}" />
    </c:if>
</form:form>
</body>
</html>