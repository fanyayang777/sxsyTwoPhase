<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>统计</title>
    <meta name="decorator" content="default"/>
    <script src="${ctxStatic}/echarts/dist/echarts.min.js" type="text/javascript"></script>
    <script src="${ctxStatic}/echarts/dist/echarts-gl.min.js"></script>
    <script src="${ctxStatic}/echarts/map/js/province/shanxi.js"></script>
    <script src="${ctxStatic}/echarts/theme/dark.js"></script>
</head>
<style type="text/css">

   body{
       background-color: white;
   }

</style>


<body>
<form:form id="searchForm" action="${ctx}/complaintmain/complaintMain/head?type=tj&newType=amout" method="post" class="breadcrumb form-search">
    <ul class="ul-form" style="height: 35px;">
        <li id="year" style="">
            <label>日期(年)：</label>
            <input id="yearDate" name="yearDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="${yearDate}"
                   onclick="WdatePicker({dateFmt:'yyyy',isShowClear:true}); $('#beginMonthDate').val('');$('#endMonthDate').val('');"/>
        </li>
            <%--  <li id="month" style="">
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
    </ul>
</form:form>

<c:if test="${yearDate ne 2018 and yearDate ne 2019}">
    <div id="aa" style="float : left; width: 50%;height:450px;margin: 0 auto;background : #ffffff ;background-size:cover;"><%--background-image: url('${ctxStatic}/images/background.jpg');--%>
    </div>
    <div id="bb" style="float : right; width: 50%;height:450px;margin: 0 auto;background : #ffffff ;background-size:cover;"><%--background-image: url('${ctxStatic}/images/background.jpg');--%>
    </div>
    <div id="cc" style="float : left; width: 60%;height:500px;margin: 20px auto;background : #ffffff ;background-size:cover;"><%--background-image: url('${ctxStatic}/images/background.jpg');--%>
    </div>
    <div id="dd" style="float : right; width: 40%;height:250px;margin: 10px auto;background : #ffffff ;background-size:cover;"><%--background-image: url('${ctxStatic}/images/background.jpg');--%>
    </div>
    <div id="ee" style="float : right; width: 40%;height:250px;margin: 10px auto;background : #ffffff ;background-size:cover;"><%--background-image: url('${ctxStatic}/images/background.jpg');--%>
    </div>
    <div id="ff" style="float : right; width: 100%;height:500px;margin: 20px auto;background : #ffffff ;background-size:cover;"><%--background-image: url('${ctxStatic}/images/background.jpg');--%>
    </div>
</c:if>
<c:if test="${yearDate eq 2018 or yearDate eq 2019}">

    <div id="new" style="width: 49%; height: 450px;float: left;margin-top: 6px;margin-left: 0.5%;">
        <img class="img" style="width: 100%;height: 100%;display: block;" src="${ctxStatic}/images/pei1.png">
    </div>

    <div id="new1" style="width: 49%; height: 450px;float: left;margin-top: 6px;margin-left: 0.5%;">
        <img class="img" style="width: 100%;height: 100%;display: block;" src="${ctxStatic}/images/pei2.png">
    </div>
    <div id="new2" style="width: 60%; height: 540px;float: left;margin-top: 6px;margin-left: 0.5%;">
        <img class="img" style="width: 100%;height: 100%;display: block;" src="${ctxStatic}/images/pei5.png">
    </div>
    <div id="new13" style="width: 38%; height: 265px;float: left;margin-top: 6px;margin-left: 0.5%;">
        <img class="img" style="width: 100%;height: 100%;display: block;" src="${ctxStatic}/images/pei3.png">
    </div>
    <div id="new4" style="width: 38%; height: 265px;float: left;margin-top: 6px;margin-left: 0.5%;">
        <img class="img" style="width: 100%;height: 100%;display: block;" src="${ctxStatic}/images/pei4.png">
    </div>
    <div id="new5" style="float : left; width: 99%;height:450px;margin: 0 auto;background-image : url('${ctxStatic}/images/pei6.png') ;background-size:100% 100%;"><%--background-image: url('${ctxStatic}/images/background.jpg');--%>
    </div>
</c:if>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->


<%--<div id="bb" style="float : right;width: 49%;height:500px;">&lt;%&ndash;background-image: url('${ctxStatic}/images/background.jpg');&ndash;%&gt;--%>
    <%--<table class="table table-striped table-bordered table-condensed">--%>
        <%--<thead>--%>
        <%--<tr>--%>
            <%--<th style="text-align:center;">赔付额</th>--%>
            <%--<th style="text-align:center;">数量</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
        <%--<tbody>--%>
            <%--<tr>--%>
                <%--<td style="text-align:center;">--%>
                    <%--2万及以下--%>
                <%--</td>--%>
                <%--<td style="text-align:center;">--%>
                    <%--<c:forEach items="${amountTableInfo}" var="info" varStatus="status">--%>
                        <%--${info['2万及以下']}--%>
                    <%--</c:forEach>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td style="text-align:center;">--%>
                    <%--2万到10万及以下--%>
                <%--</td>--%>
                <%--<td style="text-align:center;">--%>
                    <%--<c:forEach items="${amountTableInfo}" var="info" varStatus="status">--%>
                        <%--${info['2万到10万及以下']}--%>
                    <%--</c:forEach>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td style="text-align:center;">--%>
                    <%--10万到50万及以下--%>
                <%--</td>--%>
                <%--<td style="text-align:center;">--%>
                    <%--<c:forEach items="${amountTableInfo}" var="info" varStatus="status">--%>
                        <%--${info['10万到50万及以下']}--%>
                    <%--</c:forEach>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td style="text-align:center;">--%>
                    <%--50万以上--%>
                <%--</td>--%>
                <%--<td style="text-align:center;">--%>
                    <%--<c:forEach items="${amountTableInfo}" var="info" >--%>
                        <%--${info['50万以上']}--%>
                    <%--</c:forEach>--%>
                <%--</td>--%>
            <%--</tr>--%>
        <%--</tbody>--%>
    <%--</table>--%>
<%--</div>--%>
<div align="center">
    <input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
</div>

<script type="text/javascript">
    var myChart1 = echarts.init(document.getElementById('aa'));
    var myChart2 = echarts.init(document.getElementById('bb'));
    var myChart3 = echarts.init(document.getElementById('cc'));
    var myChart4 = echarts.init(document.getElementById('dd'));
    var myChart5 = echarts.init(document.getElementById('ee'));
    var myChart6 = echarts.init(document.getElementById('ff'));
    //柱状图
    <%--option = {--%>
        <%--backgroundColor:'rgba(0, 0, 0, 0.1)',--%>
        <%--xAxis: {--%>
            <%--type: 'category',--%>
            <%--data: ${keyList}--%>
        <%--},--%>
        <%--tooltip : {--%>
            <%--trigger: 'axis',--%>
            <%--axisPointer : {            // 坐标轴指示器，坐标轴触发有效--%>
                <%--type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'--%>
            <%--}--%>
        <%--},--%>
        <%--yAxis: {--%>
            <%--type: 'value'--%>
        <%--},--%>
        <%--series: [{--%>
            <%--data: ${valueList},--%>
            <%--type: 'bar'--%>
        <%--}]--%>
    <%--};--%>

    optionCS = {
        backgroundColor:'rgba(0, 0, 0, 0)', //rgba设置透明度0.1
        title : {
            text: '2020年度各地市件均赔付额',
            x: 'center',    //---主题居中
            y: 'top',
            textAlign: 'left',
            textStyle:{
                color:'rgb(0,0,0)',
                fontSize:20,

            }
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        // legend: {
        //     data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎', '百度', '谷歌', '必应', '其他']
        // },
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
        xAxis: [
            {
                type: 'category',
                axisLine: {
                    // show: false,
                    lineStyle: {
                        color: 'black',  //---x线的颜色
                    }
                },
                axisLabel:{
                    show: true,
                    textStyle:{
                        color: 'black', //---x线下字的颜色
                    }
                },
                <%--data: ${areaList.name},--%>
                data: ['朔州', '长治', '吕梁', '大同', '晋中', '太原', '晋城','临汾','阳泉','运城']
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '单位：万元',
                axisLabel: {
                    show: true,
                    interval: 'auto',
                    formatter: '{value}'
                },
                axisLine: {
                    // show: false,
                    lineStyle: {
                        color: 'black',

                    }
                },
                // show: true              //---是否显示
            }
        ],
        series: [
            {
                name: '均赔付额',
                type: 'bar',
                barWidth: '40%',    //---设置柱形的宽度
                color: '#95A3CA',
                label: {
                    show: true,
                    position: 'top',
                    formatter: '{c}',　　　　//这是关键，在需要的地方加上就行了
                    fontSize: '15',
                    color: 'black',
                },

                <%--data: ${areaTableInfo},--%>
                data: [13.4 , 13.3 , 10.6 , 9.6 , 8.8 , 7.2 , 6.2 , 6.2 , 5.9 , 5.7]
            }
        ]
    };

    optionZY = {
        backgroundColor:'rgba(0, 0, 0, 0)', //rgba设置透明度0.1
        title : {
            text: '2020年度各专业件均赔付额',
            x: 'center',    //---主题居中
            y: 'top',
            textAlign: 'left',
            textStyle:{
                color:'rgb(0,0,0)',
                fontSize:20,

            }
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        // legend: {
        //     data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎', '百度', '谷歌', '必应', '其他']
        // },
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
        xAxis: [
            {
                type: 'category',
                axisLine: {
                    // show: false,
                    lineStyle: {
                        color: 'black',  //---x线的颜色
                    }
                },
                axisLabel:{
                    show: true,
                    textStyle:{
                        color: 'black', //---x线下字的颜色
                    }
                },
                data: ['神外', '呼吸', '普外', '产科', '心内', '神内', '儿科','急诊','妇科','骨科']
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '单位：万元',
                nameLocation: 'end', //---单位的位置
                axisLabel: {
                    show: true,
                    interval: 'auto',
                    formatter: '{value}'
                },
                axisLine: {
                    // show: false,
                    lineStyle: {
                        color: 'black',

                    }
                },
                // show: true              //---是否显示
            }
        ],
        series: [
            {
                name: '均赔付额',
                type: 'bar',
                barWidth: '40%',    //---设置柱形的宽度
                // color: '#95A3CA',
                color: '#5268A4',
                label: {
                    show: true,
                    position: 'top',
                    formatter: '{c}',　　　　//这是关键，在需要的地方加上就行了
                    fontSize: '15',
                    color: 'black',
                },


                data: [12.8 , 10.5 , 9.5 , 9.2 , 8.8 , 8.7 , 8.5 , 7.7 , 7.1 , 6.9]
            }
        ]
    };

    optionDB = {
        backgroundColor:'rgba(0, 0, 0, 0)', //rgba设置透明度0.1
        title : {
            text: '赔付额年度对比分析',
            x: 'center',    //---主题居中
            y: 'top',
            textAlign: 'left',
            textStyle:{
                color:'rgb(0,0,0)',
                fontSize:20,

            }
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        // legend: {
        //     data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎', '百度', '谷歌', '必应', '其他']
        // },
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
        xAxis: [
            {
                type: 'category',
                axisLine: {
                    // show: false,
                    lineStyle: {
                        color: 'black',  //---x线的颜色
                    }
                },
                axisLabel:{
                    show: true,
                    textStyle:{
                        color: 'black', //---x线下字的颜色
                    }
                },
                data: ['2万元以下', '2-10万元', '10-50万元', '50万元以上']
            }
        ],
        yAxis: [
            {
                type: 'value',
                // name: '单位：万元',
                nameLocation: 'end', //---单位的位置
                axisLabel: {
                    show: true,
                    interval: 'auto',
                    formatter: '{value}.00%'
                },
                axisLine: {
                    // show: false,
                    lineStyle: {
                        color: 'black',

                    }
                },
                // show: true              //---是否显示
            }
        ],
        series: [
            {
                name: '均赔额年度比例',
                type: 'bar',
                barWidth: '30%',    //---设置柱形的宽度
                color: '#1A576B',
                label: {
                    show: true,
                    position: 'top',
                    formatter: '{c}%',　　　　//这是关键，在需要的地方加上就行了
                    fontSize: '15',
                    color: 'black',
                },


                data: [13.41 , 13.3 , 10.6 , 9.6 , 8.8 , 7.2 , 6.2 , 6.2 , 5.9 , 5.7]
            },
            {
                name: '均赔额年度比例',
                type: 'bar',
                barWidth: '30%',    //---设置柱形的宽度
                color: '#E86074',
                label: {
                    show: true,
                    position: 'top',
                    formatter: '{c}%',　　　　//这是关键，在需要的地方加上就行了
                    fontSize: '15',
                    color: 'black',
                },


                data: [13.4 , 13.3 , 10.6 , 9.6 , 8.8 , 7.2 , 6.2 , 6.2 , 5.9 , 5.7]
            },
        ]
    };

    optionBT1 = {
        title: {
            text: '2020年',
            // subtext: '纯属虚构',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        // legend: {
        //     orient: 'vertical',
        //     left: 'left',
        //     data: ['2万元以下', '2-10万元', '10-50万元', '50万以上']
        // },
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
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                label: {
                    show: true,
                    position: 'top',
                    formatter: '{a}{d}%',　　　　//这是关键，在需要的地方加上就行了
                    fontSize: '15',
                    color: 'black',
                },
                data: [
                    {value: 335, name: '2万元以下'},
                    {value: 310, name: '2-10万元'},
                    {value: 234, name: '10-50万元'},
                    {value: 135, name: '50万以上'},
                ],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    optionBT2 = {
        title: {
            text: '2019年',
            // subtext: '纯属虚构',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        // legend: {
        //     orient: 'vertical',
        //     left: 'left',
        //     data: ['2万元以下', '2-10万元', '10-50万元', '50万以上']
        // },
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
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                label: {
                    show: true,
                    position: 'top',
                    formatter: '{a}{d}%',　　　　//这是关键，在需要的地方加上就行了
                    fontSize: '15',
                    color: 'black',
                },
                data: [
                    {value: 335, name: '2万元以下'},
                    {value: 310, name: '2-10万元'},
                    {value: 234, name: '10-50万元'},
                    {value: 135, name: '50万以上'},
                ],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    optionQS = {
        backgroundColor:'rgba(0, 0, 0, 0)', //rgba设置透明度0.1
        title : {
            text: '近五年来三类医疗机构赔付金额趋势',
            x: 'center',    //---主题居中
            y: 'top',
            textAlign: 'left',
            textStyle:{
                color:'rgb(0,0,0)',
                fontSize:20,
            }
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            // padding: [100,0,0,100],
            data: ['三级医院', '二级医院', '其他（一级、民营、诊所）']
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
            data: ['2016年', '2017年', '2018年', '2019年', '2020年']
        },
        yAxis: {

            type: 'value',
            name:'单位：万元',
            nameLocation: 'end', //---位置居中
            // nameGap:60,             //---与y轴距离
            // nameRotate: 90,         //---角度
            nameTextStyle:{         //---name的样式
                color:'black',
                fontSize: 18,
                padding: 10
            },
            axisLabel: {
                show: true,
                interval: 'auto',
                formatter: '{value}',
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
        series: [
            {
                name: '三级医院',
                data: ['29.04','20.0','51.45','14.05','6.87'],
                type: 'line',
                label: {
                    show: true,
                    position: 'top',
                    formatter: '{c}',　　　　//这是关键，在需要的地方加上就行了
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
            },
            {
                name: '二级医院',
                data: ['25.04','21.60','21.45','24.05','10.87'],
                type: 'line',
                label: {
                    show: true,
                    position: 'top',
                    formatter: '{c}',　　　　//这是关键，在需要的地方加上就行了
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
            },
            {
                name: '一级',
                data: ['20.04','2.60','41.45','14.05','14.87'],
                type: 'line',
                label: {
                    show: true,
                    position: 'top',
                    formatter: '{c}',　　　　//这是关键，在需要的地方加上就行了
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
            }

        ]
    };


    myChart1.setOption(optionCS);
    myChart1.off('click');//防止重复绑定 事件
    myChart1.on('click',function(params){
    });
    myChart2.setOption(optionZY);
    myChart2.off('click');//防止重复绑定 事件
    myChart2.on('click',function(params){
    });
    myChart3.setOption(optionDB);
    myChart3.off('click');//防止重复绑定 事件
    myChart3.on('click',function(params){
    });
    myChart4.setOption(optionBT1);
    myChart4.off('click');//防止重复绑定 事件
    myChart4.on('click',function(params){
    });
    myChart5.setOption(optionBT2);
    myChart5.off('click');//防止重复绑定 事件
    myChart5.on('click',function(params){
    });
    myChart6.setOption(optionQS);
    myChart6.off('click');//防止重复绑定 事件
    myChart6.on('click',function(params){
    });

</script>

</body>
</html>
