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
<form:form id="searchForm" action="${ctx}/complaintmain/complaintMain/head?type=${type}&commonType=yydj" method="post" class="breadcrumb form-search">
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
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="统计"/></li>
    </ul>
</form:form>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<%--<div id="aa" style="width: 100%;margin: 0 auto;background : #ffffff ;background-size:cover;">&lt;%&ndash;background-image: url('${ctxStatic}/images/background.jpg');&ndash;%&gt;
    <div style="clear: both;"></div>
</div>--%>
<c:if test="${yearDate eq 2018 or yearDate eq 2019}">
    <div id="aa" style="width: 98%; border: 0px;border-style:solid;height: 400px;float: left;margin-top: 6px;margin-left: 0.5%;background-image: url('${ctxStatic}/images/hos.png');background-repeat: no-repeat; background-size :60% 100% ;background-position: center">
    </div>
    <div id="main4" style="width: 98%; border: 0px;border-style:solid;height: 450px;float: left;margin-top: 6px;margin-left: 0.5%;background-image: url('${ctxStatic}/images/hos1.png');background-repeat: no-repeat; background-size :60% 80% ;background-position: center">
    </div>

    <div id="bb" style="width: 98%; height: 450px;float: left;margin-top: 6px;margin-left: 0.5%;background-image: url('${ctxStatic}/images/hos2.png') ;background-repeat: no-repeat ; background-size :60% 80% ;background-position: center">
    </div>
</c:if>
<c:if test="${yearDate ne 2018 and yearDate ne 2019}">
    <div id="main3" style="margin:0 auto;width: 80%;height:611px;background : #ffffff ;background-size:cover;"></div>
</c:if>
<%--<div id="bb" style="float : right;width: 49%;height:500px;">--%>
    <%--<table class="table table-striped table-bordered table-condensed">--%>
        <%--<thead>--%>
        <%--<tr>--%>
            <%--<th style="text-align:center;">专业名称</th>--%>
            <%--<th style="text-align:center;">数量</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
        <%--<tbody>--%>
        <%--<c:forEach items="${yydjTableInfo}" var="info">--%>
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
<%--</div>--%>
<script type="text/javascript">
    var myChart3 = echarts.init(document.getElementById('main3'), 'dark');
    //饼状图
    <%--optionBZ = {--%>
        <%--backgroundColor:'rgba(128, 128, 128, 0.5)', //rgba设置透明度0.1--%>
        <%--title : {--%>
            <%--text: '各等级医院纠纷数量统计',--%>
            <%--x:0,--%>
            <%--textStyle:{--%>
                <%--color:'rgb(255,255,255)',--%>
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
            <%--data: ['三级甲等','三级乙等','二级甲等','二级乙等','乡镇卫生院','社区服务站','民营医院','门诊']--%>
        <%--},--%>
        <%--color:['#B24971','#E9D082','#1CDEAA','#1CB7DE','#711CDE','#DE1C67','#D8DE1C','#1CDE29'],--%>
        <%--series : [--%>
            <%--{--%>
                <%--name: '投诉数量',--%>
                <%--type: 'pie',--%>
                <%--radius : '55%',--%>
                <%--center: ['50%', '60%'],--%>
                <%--data: ${asdf},--%>
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
    optionBZ = {
        backgroundColor:'rgba(0, 0, 0, 0)', //rgba设置透明度0.1
        title : {
            text: '2020年医疗机构等级',
            x: 'center',    //---主题居中
            y: 'top',
            textAlign: 'left',
            textStyle:{
                color:'rgb(0,0,0)',
                fontSize:20,
            }
        },
        xAxis: {
            type: 'category',
            axisLine: {
                // show: false,
                lineStyle: {
                    color: 'black',  //---x线的颜色
                }
            },
            axisLabel:{
                show: true,
                fontSize: 15,
                textStyle:{
                    color: 'black', //---x线下字的颜色
                }
            },
            data: ['三等甲级', '三等乙级', '二等甲级', '民营机构', '其他机构']
        },
        yAxis: {
            type: 'value',
            name:'占全年百分比',
            nameLocation: 'middle', //---位置居中
            nameGap:60,             //---与y轴距离
            nameRotate: 90,         //---角度
            nameTextStyle:{         //---name的样式
                color:'black',
                fontSize: 18,
                padding: 10
            },
            axisLabel: {
                show: true,
                interval: 'auto',
                formatter: '{value}.00%',
                fontSize: 15,
                textStyle:{
                    color: 'black', //---x线下字的颜色
                }
            },
            axisLine: {
                // show: false,
                lineStyle: {
                    color: 'black',

                }
            },
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
        series: [{
            data: ['25.04','2.60','51.45','14.05','6.87'],
            <%--data: ${dutyNum},--%>
            type: 'line',
            label: {
                show: true,
                position: 'top',
                formatter: '{c}%',　　　　//这是关键，在需要的地方加上就行了
                fontSize: '15',
                color: 'black',
            },
            showSymbol:true,
            symbol:'circle',    //设定为实心点
            symbolSize: 10,     //设定实心点的大小
            hoverAnimation: false,
            animation: false,
            // markPoint: {     //显示一定区域内的最大值和最小值
            //     data:[
            //         {type:'max',name:'最大值·'},
            //         {type:'min',name:'最小值'},
            //     ]
            // }
        }]
    };


    myChart3.setOption(optionBZ);
</script>

</body>
</html>
