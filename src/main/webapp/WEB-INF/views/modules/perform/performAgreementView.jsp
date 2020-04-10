<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>履行协议详情</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
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
            //达成调解
            $("#dctjDetail").attr("src","${ctx}/reachmediate/reachMediate/form?id=${map.dctj}&type=view&show2=y");
            var dctj= document.getElementById("dctjDetail");
            dctj.height=document.documentElement.clientHeight-130;
            dctj.width=document.documentElement.clientWidth;
            //签署协议
            $("#qsxyDetail").attr("src","${ctx}/sign/signAgreement/form?id=${map.qsxy}&type=view&show2=y");
            var qsxy= document.getElementById("qsxyDetail");
            qsxy.height=document.documentElement.clientHeight-130;
            qsxy.width=document.documentElement.clientWidth;
            }
        });
    </script>
</head>

    <body>
    <br/>
    <form:form class="form-horizontal">
        <sys:message content="${message}"/>
        <fieldset>
            <ul id="myTab1" class="nav nav-tabs">
                <li class="active">
                    <a href="#lxxy" data-toggle="tab">履行协议</a>
                </li>
                <c:if test="${empty show2}">
                <li>
                    <a href="#details" data-toggle="tab">详情</a>
                </li>
                </c:if>
            </ul>
            <legend>履行协议详情</legend>
            <div id="myTabContent1" class="tab-content">
                <div id="lxxy" class="tab-pane fade in active">
            <ul id="myTab" class="nav nav-tabs">
                <li class="active">
                    <a href="#patient" data-toggle="tab">履行协议情况</a>
                </li>
                <li>
                    <a href="#annex" data-toggle="tab">附件</a>
                </li>
            </ul>
            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="patient">
                    <table class="table-form">
                        <tr>
                            <td class="tit">协议赔付金额</td>
                            <td>
                                ${performAgreement.agreementPayAmount}
                            </td>
                            <td class="tit">保险公司赔付金额</td>
                            <td>
                                    ${performAgreement.insurancePayAmount}
                            </td>
                            <td class="tit">医院赔付金额</td>
                            <td>
                                ${performAgreement.hospitalPayAmount}
                            </td>
                        </tr>
                        <tr>
                            <td  class="tit">患方协议送达时间：</td>
                            <td>
                                    ${performAgreement.patientServiceTime}
                            </td>
                            <td class="tit">医方协议送达时间：</td>
                            <td>
                                    ${performAgreement.hospitalServiceTime}
                            </td>
                            <td class="tit">协议生效时间：</td>
                            <td>
                                    ${performAgreement.takeEffectTime}
                            </td>
                        </tr>
                        <tr>
                            <td  class="tit">交理赔时间</td>
                            <td>
                                ${performAgreement.claimSettlementTime}
                            </td>
                            <td class="tit">保险公司赔付时间</td>
                            <td>
                                    ${performAgreement.insurancePayTime}
                            </td>
                            <td class="tit">医院赔付时间</td>
                            <td>
                                ${performAgreement.hospitalPayTime}
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="tab-pane fade" id="annex">
                    <table class="table-form">
                        <tr style=" " >
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">转款凭证：</td>
                            <input type="hidden"  name="fjtype1" value="14">
                            <td style="width: 450px; ">
                                <input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
                                <input type="hidden" id="files1" name="files" htmlEscape="false" class="input-xlarge"  value="${files}"/>
                                    <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                                <div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files"  uploadPath="/performAgreement/Transfer" selectMultiple="true" readonly="true"/></div>
                            </td>

                        </tr>
                        <tr style="" >
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">收款收据：</td>
                            <input type="hidden" name="fjtype2" value="15">
                            <td style="width: 450px; ">
                                <input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">
                                <input type="hidden" id="files2" name="files1" htmlEscape="false" class="input-xlarge" value="${files1}" />
                                    <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                                <div style="margin-top: -45px;"><sys:ckfinder input="files2" type="files"  uploadPath="/performAgreement/Receipt" selectMultiple="true" readonly="true"/></div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
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
                        <li>
                            <a href="#dctj" data-toggle="tab">达成调解</a>
                        </li>
                        <li>
                            <a href="#qsxy" data-toggle="tab">签署协议</a>
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
                        <div class="tab-pane fade" id="dctj">
                            <iframe id="dctjDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                        </div>
                        <div class="tab-pane fade" id="qsxy">
                            <iframe id="qsxyDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
        <c:if test="${empty show2}">
        <div class="form-actions">
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
        <act:histoicFlow procInsId="${performAgreement.complaintMain.procInsId}" />
        </c:if>
    </form:form>
    </body>
</html>

