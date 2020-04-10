<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv= "Content-Type" content="text/html; charset=utf-8" >
    <title>首页</title>
</head>
<script src="${ctxStatic}/common/jeesite.js" type="text/javascript"></script>
<link href="${ctxStatic}/index/files/styles.css" type="text/css" rel="stylesheet"/>
<link href="${ctxStatic}/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet">

<style type="text/css">

    body {
        background-color: #f5f5f5;
    }

</style>
<body>
<br>
<div class="base">
    <div class="u1" id="myData">
        <div class="u22">
            <img class="u22_img" src="${ctxStatic}/index/images/u22.png"/>
        </div>

        <div class="u5">
            <div class="u5_text">
                <span>我的案件</span>
            </div>
        </div>

        <div class="u9">
            <div class="u9_text">
                <span>${empty listSize ? 0 : listSize}/${sum}</span>
            </div>
        </div>

<%--        <div class="u13">
            <div class="u13_div">
                <div class="u25">
                    <e>数据明细</e>
                </div>
                <div class="photo">
                    <img src="${ctxStatic}/index/images/u17.png" id="myData" alt="">
                </div>
            </div>

        </div>--%>

    </div>


    <div class="u2" id="notify">
        <div class="u22">
            <img class="u22_img" src="${ctxStatic}/index/images/u24.png"/>
        </div>

        <div class="u5">
            <div class="u5_text">
                <span>通告查询</span>
            </div>
        </div>

        <div class="u9">
            <div class="u9_text">
                <span style="margin-left: 20%;">${notifyPageSize}/${notifyCount}</span>
            </div>
        </div>

<%--        <div class="u13">
            <div class="u13_div">
                <div class="u25">
                    <e>数据明细</e>
                </div>
                <div class="photo">
                    <img src="${ctxStatic}/index/images/u17.png" alt="">
                </div>
            </div>

        </div>--%>

    </div>


    <div class="u3" id="tool">
        <div class="u22">
            <img class="u22_img" src="${ctxStatic}/index/images/u23.png"/>
        </div>

        <div class="u5">
            <div class="u5_text">
                <span>调解工具</span>
            </div>
        </div>

        <div class="u9">
            <div class="u9_text">
                <span style="margin-left: 20%;">14个</span>
            </div>
        </div>

<%--        <div class="u13">
            <div class="u13_div">
                <div class="u25">
                    <e>数据明细</e>
                </div>
                <div class="photo">
                    <img src="${ctxStatic}/index/images/u17.png" alt="">
                </div>
            </div>

        </div>--%>

    </div>


    <div class="u4" id="study">
        <div class="u22">
            <img class="u22_img" src="${ctxStatic}/index/images/u21.png"/>
        </div>

        <div class="u5">
            <div class="u5_text">
                <span>培训学习</span>
            </div>
        </div>

        <div class="u9">
            <div class="u9_text">
                <span style="margin-left: 20%;">7个</span>
            </div>
        </div>
        <script>
            var md = document.getElementById("myData");
            md.onclick=function(){
                addTabPage('我的案件','${ctx}/complaintmain/complaintMain/self');
            }
            var notify = document.getElementById("notify");
            notify.onclick=function(){
                addTabPage('我的通知','${ctx}/oa/oaNotify/self');
            }
            var tool = document.getElementById("tool");
            var s = document.getElementById("study");
            tool.onclick=function(){
                top.$.jBox.info('此功能正在开发中...', '提示', {closed:function(){
                    }});
                top.$('.jbox-body .jbox-icon').css('top','55px');
            }
            s.onclick=function(){
                // top.$.jBox.info('此功能正在开发中...', '提示', {closed:function(){
                //     }});
                addTabPage('培训学习','${ctx}/complaintmain/complaintMain/aa');
                sessionStorage.setItem("key","value");
                top.$('.jbox-body .jbox-icon').css('top','55px');
            }
        </script>
<%--        <div class="u13">
            <div class="u13_div">
                <div class="u25">
                    <e>数据明细</e>
                </div>
                <div class="photo">
                    <img src="${ctxStatic}/index/images/u17.png" alt="">
                </div>
            </div>

        </div>--%>

    </div>











</div>

<!-- ==================================================================================-->
<br>
<div id="table">
    <div id="left">
        <div class="tablebox11"><b style="color: rgba(0, 153, 204, 1);font-size: 18px; line-height: 35px;">&nbsp;&nbsp;案件列表</b></div>
        <%--<div class="tablebox2">
            <div class="text" style="color: #009999;">20200094</div>
            <div class="text">XX市第一人民医院</div>
            <div class="text">2019年10月30日</div>
            <div class="text" style="color: #009999;">已结案</div>
        </div>
        <div class="tablebox1">
            <div class="text" style="color: #009999;">20200094</div>
            <div class="text">XX市第一人民医院</div>
            <div class="text">2019年10月30日</div>
            <div class="text" style="color: #009999;">已结案</div>
        </div>
        <div class="tablebox2">
            <div class="text" style="color: #009999;">20200094</div>
            <div class="text">XX市第一人民医院</div>
            <div class="text">2019年10月30日</div>
            <div class="text" style="color: #009999;">已结案</div>
        </div>
        <div class="tablebox1">
            <div class="text" style="color: #009999;">20200094</div>
            <div class="text">XX市第一人民医院</div>
            <div class="text">2019年10月30日</div>
            <div class="text" style="color: #009999;">已结案</div>
        </div>
        <div class="tablebox2">
            <div class="text" style="color: #009999;">20200094</div>
            <div class="text">XX市第一人民医院</div>
            <div class="text">2019年10月30日</div>
            <div class="text" style="color: #009999;">已结案</div>
        </div>
        <div class="tablebox1">
            <div class="text" style="color: #009999;">20200094</div>
            <div class="text">XX市第一人民医院</div>
            <div class="text">2019年10月30日</div>
            <div class="text" style="color: #009999;">已结案</div>
        </div>
        <div class="tablebox2">
            <div class="text" style="color: #009999;">20200094</div>
            <div class="text">XX市第一人民医院</div>
            <div class="text">2019年10月30日</div>
            <div class="text" style="color: #009999;">已结案</div>
        </div>
        <div class="tablebox1">
            <div class="text" style="color: #009999;">20200094</div>
            <div class="text">XX市第一人民医院</div>
            <div class="text">2019年10月30日</div>
            <div class="text" style="color: #009999;">已结案</div>
        </div>
        <div class="tablebox2">
            <div class="text" style="color: #009999;">20200094</div>
            <div class="text">XX市第一人民医院</div>
            <div class="text">2019年10月30日</div>
            <div class="text" style="color: #009999;">已结案</div>
        </div>
        <div class="tablebox1">
            <div class="text" style="color: #009999;">20200094</div>
            <div class="text">XX市第一人民医院</div>
            <div class="text">2019年10月30日</div>
            <div class="text" style="color: #009999;">已结案</div>
        </div>
        <div class="tablebox2">
            <div class="text" style="color: #009999;">20200094</div>
            <div class="text">XX市第一人民医院</div>
            <div class="text">2019年10月30日</div>
            <div class="text" style="color: #009999;">已结案</div>
        </div>
        <div class="tablebox1">
            <div class="text" style="color: #009999;">20200094</div>
            <div class="text">XX市第一人民医院</div>
            <div class="text">2019年10月30日</div>
            <div class="text" style="color: #009999;">已结案</div>
        </div>
        <div class="tablebox2">
            <div class="text" style="color: #009999;">20200094</div>
            <div class="text">XX市第一人民医院</div>
            <div class="text">2019年10月30日</div>
            <div class="text" style="color: #009999;">已结案</div>
        </div>
        <div class="tablebox1">
            <div class="text" style="color: #009999;">20200094</div>
            <div class="text">XX市第一人民医院</div>
            <div class="text">2019年10月30日</div>
            <div class="text" style="color: #009999;">已结案</div>
        </div>--%>
        <table id="contentTable" class="table table-striped">
            <thead>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="complaintMain">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${not empty complaintMain.url}">
                                <c:choose>
                                    <c:when test="${ complaintMain.nodeName ne '案件反馈' and complaintMain.assignee eq fns:getUser().loginName}">
                                        <a href="${ctx}${complaintMain.url}form?id=${complaintMain.key}">${complaintMain.caseNumber}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${ctx}${complaintMain.url}form?id=${complaintMain.key}&type=view">${complaintMain.caseNumber}</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                ${complaintMain.caseNumber}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                            ${complaintMain.patientName}与${fns:abbr(complaintMain.hospital.name,20)}
                    </td>
                    <td>
                            ${complaintMain.nodeName}
                    </td>
                    <td>
                        <fmt:formatDate value="${complaintMain.createDate}" pattern="yyyy-MM-dd "/>
                    </td>
                </tr>


            </c:forEach>
            </tbody>
        </table>
    </div>







    <div id="right">
        <div class="tablebox33"><b style="color: rgba(0, 153, 204, 1);font-size: 18px; line-height: 35px;">&nbsp;&nbsp;通告</b></div>
        <%--<div class="tablebox4">
            <div class="tablebox1">
                <div class="text2">XX市第一人民医院</div>
                <div class="text2">2019年10月30日</div>
            </div>
        </div>
        <div class="tablebox3">
            <div class="tablebox1">
                <div class="text2">XX市第一人民医院</div>
                <div class="text2">2019年10月30日</div>
            </div>
        </div>
        <div class="tablebox4">
            <div class="tablebox1">
                <div class="text2">XX市第一人民医院</div>
                <div class="text2">2019年10月30日</div>
            </div>
        </div>
        <div class="tablebox3">
            <div class="tablebox1">
                <div class="text2">XX市第一人民医院</div>
                <div class="text2">2019年10月30日</div>
            </div>
        </div>
        <div class="tablebox4">
            <div class="tablebox1">
                <div class="text2">XX市第一人民医院</div>
                <div class="text2">2019年10月30日</div>
            </div>
        </div>
        <div class="tablebox3">
            <div class="tablebox1">
                <div class="text2">XX市第一人民医院</div>
                <div class="text2">2019年10月30日</div>
            </div>
        </div>
        <div class="tablebox4">
            <div class="tablebox1">
                <div class="text2">XX市第一人民医院</div>
                <div class="text2">2019年10月30日</div>
            </div>
        </div>
        <div class="tablebox3">
            <div class="tablebox1">
                <div class="text2">XX市第一人民医院</div>
                <div class="text2">2019年10月30日</div>
            </div>
        </div>
        <div class="tablebox4">
            <div class="tablebox1">
                <div class="text2">XX市第一人民医院</div>
                <div class="text2">2019年10月30日</div>
            </div>
        </div>
        <div class="tablebox3">
            <div class="tablebox1">
                <div class="text2">XX市第一人民医院</div>
                <div class="text2">2019年10月30日</div>
            </div>
        </div>
        <div class="tablebox4">
            <div class="tablebox1">
                <div class="text2">XX市第一人民医院</div>
                <div class="text2">2019年10月30日</div>
            </div>
        </div>
        <div class="tablebox3">
            <div class="tablebox1">
                <div class="text2">XX市第一人民医院</div>
                <div class="text2">2019年10月30日</div>
            </div>
        </div>
        <div class="tablebox4">
            <div class="tablebox1">
                <div class="text2">XX市第一人民医院</div>
                <div class="text2">2019年10月30日</div>
            </div>
        </div>
        <div class="tablebox3">
            <div class="tablebox1">
                <div class="text2">XX市第一人民医院</div>
                <div class="text2">2019年10月30日</div>
            </div>
        </div>--%>
        <table id="contentTables" class="table table-striped ">
            <tbody>
            <c:forEach items="${notifyPage.list}" var="oa">
                <tr>
                    <td><a href="${ctx}/oa/oaNotify/${requestScope.oa.self?'':'form'}?id=${oa.id}">
                            ${fns:abbr(oa.title,30)}
                    </a></td>
                    <td>
                        <fmt:formatDate value="${oa.createDate}" pattern="yyyy-MM-dd"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
