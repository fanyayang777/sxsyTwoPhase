<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>统计</title>
    <meta name="decorator" content="default"/>
    <script src="${ctxStatic}/echarts/dist/echarts.js" type="text/javascript"></script>
    <script src="${ctxStatic}/echarts/dist/echarts-gl.min.js"></script>
    <script src="${ctxStatic}/echarts/map/js/province/shanxi1.js"></script>
    <%--<script src="${ctxStatic}/echarts/theme/dark.js"></script>--%>
</head>
<style type="text/css">

   body{
       background-color: white;
   }

</style>


<body>
<form:form id="searchForm" action="${ctx}/complaintmain/complaintMain/head?type=tj&newType=duty" method="post" class="breadcrumb form-search">
    <ul class="ul-form" style="height: 35px;">
        <li id="year" style="">
            <label>日期(年)：</label>
            <input id="yearDate" name="yearDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="${yearDate}"
                   onclick="WdatePicker({dateFmt:'yyyy',isShowClear:true}); $('#beginMonthDate').val('');$('#endMonthDate').val('');"/>
        </li>
        <%--<li id="month" style="">
            <label>日期(月)：</label>
            <input id="beginMonthDate" name="beginMonthDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="${beginMonthDate}"
                   onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true});$('#yearDate').val('');"/>
            -
            <input id="endMonthDate" name="endMonthDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="${endMonthDate}"
                   onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true});$('#yearDate').val('');"/>

        </li>--%>
        <%--<li>
            <label>类型：</label>
            <select name="newType" class="input-small ">
                <option value=""></option>
                <option value="duty">责任度</option>
                <option value="amountRatio">赔偿金额比例</option>
            </select>
        </li>--%>

        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="统计"/></li>
    </ul>
</form:form>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<c:if test="${yearDate ne 2018 and yearDate ne 2019}">
<div id="aa" style="margin:0 auto;width: 80%;height:611px;background : #ffffff ;background-size:cover;"><%--background-image: url('${ctxStatic}/images/background.jpg');--%>
</div>
</c:if>
<c:if test="${yearDate eq 2018 or yearDate eq 2019}">
    <div id="main4" align="center" style="width: 98%; border: 0px;border-style:solid;float: left;margin-top: 6px;margin-left: 0.5%;">
        <img class="img" style="max-width: 80%;height: 80%;" src="${ctxStatic}/images/peifu.png">
    </div>
</c:if>


<%--<div id="bb" style="float : right;width: 49%;height:500px;">&lt;%&ndash;background-image: url('${ctxStatic}/images/background.jpg');&ndash;%&gt;--%>
    <%--<table class="table table-striped table-bordered table-condensed">--%>
        <%--<thead>--%>
        <%--<tr>--%>
            <%--<th style="text-align:center;">责任名称</th>--%>
            <%--<th style="text-align:center;">数量</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
        <%--<tbody>--%>
        <%--<c:forEach items="${dutyTableInfo}" var="info">--%>
            <%--<tr>--%>
                <%--<td style="text-align:center;">--%>
                        <%--${info.name}--%>
                <%--</td>--%>
                <%--<td style="text-align:center;">--%>
                        <%--${info.value}--%>
                <%--</td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
        <%--</tbody>--%>
    <%--</table>--%>
</div>
<div align="center">
    <input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
</div>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('aa'),'dark');

    //饼状图
    <%--option = {--%>
        <%--backgroundColor:'rgba(0, 0, 0, 0.1)', //rgba设置透明度0.1--%>
        <%--title : {--%>
            <%--text: '责任度统计',--%>
            <%--x:0,--%>
            <%--textStyle:{--%>
                <%--color:'rgb(0,0,0)',--%>
                <%--fontSize:14--%>
            <%--}--%>
        <%--},--%>
        <%--tooltip : {--%>
            <%--trigger: 'item',--%>
            <%--formatter: "{a} <br/>{b} : {c} ({d}%)"--%>
        <%--},--%>
        <%--legend: {--%>
            <%--orient: 'vertical',--%>
            <%--left: 'right',--%>
            <%--top : '50px',--%>
            <%--data: ['无责','轻微责任','次要责任','对等责任','主要责任','全部责任','无法判定']--%>
        <%--},--%>
        <%--color:['#8d73b2','#5fdecc','#1CDEAA','#1CB7DE','#711CDE','#DE1C67','#D8DE1C'],--%>
        <%--series : [--%>
            <%--{--%>
                <%--name: '责任数量',--%>
                <%--type: 'pie',--%>
                <%--radius : '55%',--%>
                <%--center: ['50%', '60%'],--%>
                <%--data: ${dutyNum},--%>
                <%--itemStyle: {--%>
                    <%--emphasis: {--%>
                        <%--shadowBlur: 10,--%>
                        <%--shadowOffsetX: 0,--%>
                        <%--shadowColor: 'rgba(255, 255, 0, 0.5)'--%>
                    <%--}--%>
                <%--}--%>
            <%--}--%>
        <%--]--%>
    <%--};--%>

    //fyy

    //饼状图

    //饼状图
    option = {
        backgroundColor:'rgba(0, 0, 0, 0)', //rgba设置透明度0.1
        title : {
            text: '2020年度医疗纠纷评估责任度分析',
            x:0,
            textStyle:{
                color:'rgb(0,0,0)',
                fontSize:14
            }
        },
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                }, //区域缩放，区域缩放还原
                dataView: {
                    readOnly: false
                }, //数据视图
                magicType: {
                    type: ['line', 'bar'],
                },  //切换为折线图，切换为柱状图
                restore: {},  //还原
                saveAsImage: {},   //保存为图片

            },
            iconStyle:{
                normal:{
                    color:'blue',//设置颜色
                }
            }

        },
        tooltip: {
            trigger: 'item',
            formatter:'{a} <br/>{b} : {c}%',　　　　//这是关键，在需要的地方加上就行了
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            },

        },
        legend: {
            data: ['2020年'],
            orient: 'vertical',
            top: '20px',
            textStyle:{
                color : 'black',
                fontSize : 18
            }
        },
        grid: {
            borderWidth: 0,
            y: 80,
            y2: 50
        },
        xAxis: [
            {
                type: 'category',
                // show: false,
                axisLine: {
                    lineStyle: {
                        color: 'black',
                    }
                },
                axisLabel:{
                    show: true,
                    textStyle:{
                        color: 'black',
                    }
                },
                data: ${name}
                <%--data: ${dutyName},--%>
            }
        ],
        yAxis: {
            type: 'value',
            axisLabel: {
                show: true,
                interval: 'auto',
                formatter: '{value}%',
                textStyle:{
                    color: 'black',
                }
            },
            axisLine: {
                lineStyle: {
                    color: 'black',
                }
            },
            show: true              //---是否显示
        },
        series: [
            {
                name: '2020年',
                type: 'bar',
                label: {
                    show: true,
                    position: 'top',
                    formatter: function (a) {
                        return a.value+"%";
                    },　　　　//这是关键，在需要的地方加上就行了
                    fontSize: '15',
                    color: 'black',
                },

                itemStyle:{					//---图形形状
                    // 	color:'#65A1D8',        //---柱形颜色
                    normal: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#65A1D8'},                   //柱图渐变色
                                {offset: 0.5, color: '#84B4DF'},                 //柱图渐变色
                                {offset: 1, color: '#E5EFF8'},                   //柱图渐变色
                            ]
                        )
                    },
                    barBorderRadius:[0,0,0,0],
                },
                barWidth:'35',				//---柱形宽度
                barCategoryGap:'20%',		//---柱形间距
                radius: '55%',
                <%--data: ${dutyNum},--%>
                data: ${dutyNum}
            }
        ]
    };


    myChart.setOption(option);
    myChart.off('click');//防止重复绑定 事件
    myChart.on('click',function(params){
    });
</script>
</body>
</html>
