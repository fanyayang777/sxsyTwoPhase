<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>统计</title>
    <meta name="decorator" content="default"/>
    <script src="${ctxStatic}/echarts/dist/echarts.min.js" type="text/javascript"></script>
    <script src="${ctxStatic}/echarts/dist/echarts-gl.min.js"></script>
    <script src="${ctxStatic}/echarts/map/js/province/shanxi.js"></script>
    <script src="${ctxStatic}/echarts/dark.js"></script>
</head>
<style type="text/css">

   body{
       background-color: white;
   }

</style>


<body>
<form:form id="searchForm" action="${ctx}/complaintmain/complaintMain/head?type=${type}&commonType=jfjd" method="post" class="breadcrumb form-search">
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

            </li>
            <li>
                <label>类型：</label>
                <select name="newType" class="input-small ">
                    <option value=""></option>
                    <option value="duty">责任度</option>
                    <option value="amountRatio">赔偿金额比例</option>
                </select>
            </li>--%>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="统计"/></li>
        <%--<li class="btns"><input id="expSubmit" class="btn btn-primary" type="submit" value="导出"/></li>--%>
    </ul>
</form:form>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<%--<div id="aa" style="width: 100%;margin: 0 auto;background : #ffffff ;background-size:cover;">&lt;%&ndash;background-image: url('${ctxStatic}/images/background.jpg');&ndash;%&gt;


    <div style="clear: both;"></div>
</div>--%>
<c:if test="${yearDate eq 2018 or yearDate eq 2019}">

<div id="aa" style="width: 98%; border: 0px;border-style:solid;height: 400px;float: left;margin-top: 6px;margin-left: 0.5%;background-image: url('${ctxStatic}/images/foucs1.png');background-repeat: no-repeat; background-size :60% 100% ;background-position: center">
</div>
<div id="main4" style="width: 98%; border: 0px;border-style:solid;height: 450px;float: left;margin-top: 6px;margin-left: 0.5%;background-image: url('${ctxStatic}/images/foucs2.png');background-repeat: no-repeat; background-size :60% 100% ;background-position: center">
</div>

<div id="bb" style="width: 98%; height: 450px;float: left;margin-top: 6px;margin-left: 0.5%;background-image: url('${ctxStatic}/images/foucs.png');background-repeat: no-repeat; background-size :60% 100% ;background-position: center">
</div>
</c:if>
<c:if test="${yearDate ne 2018 and yearDate ne 2019}">
    <div id="main"  style="width: 99%;height: 500px; border: 0px;border-style:solid;float: left;" ></div>
</c:if>
<%--<div id="bb" style="float : right;width: 49%;height:500px;">--%>
<%--    <table class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th style="text-align:center;">焦点名称</th>
            <th style="text-align:center;">数量</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${jfjdTableInfo}" var="info">
            <tr>
                <td style="text-align:center;">
                        ${info.typeName}
                </td>
                <td style="text-align:center;">
                        ${info.num}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>--%>
</div>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'), 'dark');
    //案件类型统计option
    option = {
        backgroundColor:'rgba(157, 211, 250, 0)', //rgba设置透明度0.1
        title: {
            text: '纠纷焦点统计',
            x:'center',
            textStyle:{
                color:'rgb(54, 169, 206)',
                fontSize:20
            }
        },

        // color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
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
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ${list},
                axisTick: {
                    alignWithLabel: true
                },
                axisLabel: {
                    color:'#000000'
                },
                lineStyle: {
                    color: '#000000', // 颜色
                    width: 3 // 粗细
                },
                axisLine:{
                    lineStyle:{
                        color:'#000000',
                    }
                }
            }
        ],
        yAxis : [
            {
                type : 'value',
                name : '占全年百分比',
                nameRotate: 90,
                nameGap: 45,
                nameLocation: "middle",
                nameTextStyle : {
                    color:'#000000',
                    fontSize:'20',
                },
                axisLabel: {
                    show: true,
                    interval: 'auto',
                    formatter: '{value} %',
                    color:'#000000',
                },
                axisLine:{
                    lineStyle:{
                        color:'#000000',
                    }
                },
            },

        ],
        series : [
            {
                name:'百分比',
                type:'bar',
                barWidth: 40,
                data:${list2},
                rawdate:${ids},
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: 'rgb(37,198,255)'},

                                {offset: 1, color: 'rgb(31,111,181)'}
                            ]
                        )
                    }
                },
                label:{
                    show:true,
                    formatter: function (a) {
                        return a.value+"%";
                    },
                    position: 'top',
                    textStyle: {
                        color: 'black',
                        fontSize: 14
                    },
                    color:'#000000'
                }
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
