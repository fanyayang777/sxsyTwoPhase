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
       background-color: black;
   }

</style>


<body>
<form:form id="searchForm" action="${ctx}/complaintmain/complaintMain/head" method="post" class="breadcrumb form-search">
    <ul class="ul-form" style="height: 35px;">
        <li id="year" style="">
            <label>日期(年)：</label>
            <input id="yearDate" name="yearDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="${yearDate}"
                   onclick="WdatePicker({dateFmt:'yyyy',isShowClear:true}); $('#beginMonthDate').val('');$('#endMonthDate').val('');"/>
        </li>
        <li id="month" style="">
            <label>日期(月)：</label>
            <input id="beginMonthDate" name="beginMonthDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="${beginMonthDate}"
                   onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true});$('#yearDate').val('');"/>
            -
            <input id="endMonthDate" name="endMonthDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="${endMonthDate}"
                   onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true});$('#yearDate').val('');"/>

        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="统计"/></li>
    </ul>
</form:form>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="aa" style="width: 100%;margin: 0 auto;background : #ffffff ;background-size:cover;"><%--background-image: url('${ctxStatic}/images/background.jpg');--%>
    <div id="main"  style="width: 30%; border: 0px;border-style:solid;height:370px;float: left;" ></div>
    <div id="main1" style="width: 39%; height: 200px;border: 0px;border-style:solid;float: left;margin-left: 0.5%;"></div>
    <div id="main2" style="width: 30%; border: 0px;border-style:solid;height: 370px;float: left;margin-left: 0.5%;"></div>
    <div style="clear: both;"></div>
    <div id="main3" style="width: 30%; border: 0px;border-style:solid;height:370px;float: left;margin-top: 6px;"></div>
    <div id="bigMain" style="width: 39%;border: 0px;border-style:solid;height: 534px;float: left;margin-left: 0.5%;margin-top: -360px;"></div>
    <div id="main4" style="width: 30%; border: 0px;border-style:solid;height: 370px;float: left;margin-top: 6px;margin-left: 0.5%;"></div>

    <div style="clear: both;"></div>
</div>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'), 'dark');
    //var myChart1 = echarts.init(document.getElementById('main1'), 'dark');
    var myChart2 = echarts.init(document.getElementById('main2'), 'dark');
    var myChart3 = echarts.init(document.getElementById('main3'), 'dark');
    var myChart4 = echarts.init(document.getElementById('main4'), 'dark');
    //案件类型统计option
    option = {
        backgroundColor:'rgba(128, 128, 128, 0.5)', //rgba设置透明度0.1
        title: {
            text: '投诉类型统计',
            left: 0,
            textStyle:{
                color:'rgb(255,255,255)',
                fontSize:14
            }
        },

        // color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
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
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'数量',
                type:'bar',
                barWidth: '60%',
                data:${list2},
                rawdate:${ids},
                itemStyle:{
                    normal:{
                        color:'#4ad2ff'
                    }
                }
            }
        ]
    };

    optionZX = {
        backgroundColor:'rgba(128, 128, 128, 0.5)', //rgba设置透明度0.1
        title : {
            text: '各月份投诉数量',
            x:0,
            textStyle:{
                color:'rgb(255,255,255)',
                fontSize:14
            }
        },
        xAxis: {
            type: 'category',
            data: ${monthData}
        },
        yAxis: {
            type: 'value'
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        series: [{
            name:'数量',
            data: ${number},
            type: 'line'
        }]
    };
    //饼状图
    optionBZ = {
        backgroundColor:'rgba(128, 128, 128, 0.5)', //rgba设置透明度0.1
        title : {
            text: '各等级医院投诉数量统计',
            x:0,
            textStyle:{
                color:'rgb(255,255,255)',
                fontSize:14
            }
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'right',
            top : '50px',
            data: ['三级甲等','三级乙等','二级甲等','二级乙等','乡镇卫生院','社区服务站','民营医院','门诊']
        },
        color:['#B24971','#E9D082','#1CDEAA','#1CB7DE','#711CDE','#DE1C67','#D8DE1C','#1CDE29'],
        series : [
            {
                name: '投诉数量',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data: ${asdf},
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(255, 255, 0, 0.5)'
                    }
                }
            }
        ]
    };
    //仪表盘
   /* optionYB = {
        backgroundColor:'rgba(128, 128, 128, 0.5)', //rgba设置透明度0.1
        title:{
            text:'案件数量统计',
            x:0,
            textStyle:{
                color:'rgb(255,255,255)',
                fontSize:14
            }
        },
        tooltip : {
            formatter: "{a} <br/>{c} {b}"
        },

        series : [
            {
                name: '案件总数(件)',
                type: 'gauge',
                z: 10,
                min: 0,
                max: 1000,
                // splitNumber: 11,
                radius: '85%',
                axisLine: {            // 坐标轴线
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: [[0.2, 'lime'],[0.8, '#1e90ff'],[1, '#ff4500']],
                        width: 3,
                        shadowColor : '#fff', //默认透明
                        shadowBlur: 10
                    }
                },
                axisTick: {            // 坐标轴小标记
                    length: 15,        // 属性length控制线长
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: 'auto'
                    }
                },
                splitLine: {           // 分隔线
                    length: 18,         // 属性length控制线长
                    lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                        color: 'auto'
                    }
                },
                axisLabel: {
                    backgroundColor: 'auto',
                    borderRadius: 2,
                    color: '#eee',
                    padding: 1,
                    textShadowBlur: 1,
                    textShadowOffsetX: 1,
                    textShadowOffsetY: 1,
                    textShadowColor: '#222',
                    fontSize:10
                },
                title : {
                    // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    // fontWeight: 'bolder',
                    fontSize: 20,
                    fontStyle: 'italic'
                },
                detail : {
                    //其余属性默认使用全局文本样式，详见TEXTSTYLE
                    // formatter: function (value) {
                    //     value = (value + '').split('.');
                    //     value.length < 2 && (value.push('00'));
                    //     return ('00' + value[0]).slice(-2)
                    //         + '.' + (value[1] + '00').slice(0, 2);
                    // },
                    fontWeight: 'bolder',
                    fontSize: 14,
                    // borderRadius: 3,
                    backgroundColor: '#444',
                    borderColor: '#aaa',
                    shadowBlur: 5,
                    shadowColor: '#333',
                    shadowOffsetX: 0,
                    shadowOffsetY: 3,
                    borderWidth: 2,
                    textBorderColor: '#000',
                    textBorderWidth: 2,
                    textShadowBlur: 2,
                    textShadowColor: '#fff',
                    textShadowOffsetX: 0,
                    textShadowOffsetY: 0,
                    fontFamily: 'Arial',
                    width: 25,
                    color: '#eee',
                    rich: {}
                },
                data:[{value: ${listZ}}]
            },
            {
                name: '已完成案件数量(件)',
                type: 'gauge',
                center: ['31%', '55%'],    // 默认全局居中
                radius: '75%',
                z:200,
                min:0,
                max:1000,
                endAngle:45,
                // splitNumber:7,
                axisLine: {            // 坐标轴线
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: [[0.2, 'lime'],[0.8, '#1e90ff'],[1, '#ff4500']],
                        width: 3,
                        shadowColor : '#fff', //默认透明
                        shadowBlur: 10
                    }
                },
                axisTick: {            // 坐标轴小标记
                    length:12,        // 属性length控制线长
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: 'auto'
                    }
                },
                splitLine: {           // 分隔线
                    length:20,         // 属性length控制线长
                    lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                        color: 'auto'
                    }
                },
                pointer: {
                    width:5
                },
                title: {
                    offsetCenter: [0, '-30%'],       // x, y，单位px
                },
                detail: {
                    // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder'
                },
                data:[{value: ${ywc}}]
            },
            {
                name: '未完成案件数量(件)',
                type: 'gauge',
                center: ['69%', '55%'],    // 默认全局居中
                radius: '75%',
                z:200,
                min:0,
                max:1000,
                startAngle:135,
                // splitNumber:7,
                axisLine: {            // 坐标轴线
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: [[0.2, 'lime'],[0.8, '#1e90ff'],[1, '#ff4500']],
                        width: 3,
                        shadowColor : '#fff', //默认透明
                        shadowBlur: 10
                    }
                },
                axisTick: {            // 坐标轴小标记
                    length:12,        // 属性length控制线长
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: 'auto'
                    }
                },
                splitLine: {           // 分隔线
                    length:20,         // 属性length控制线长
                    lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                        color: 'auto'
                    }
                },
                pointer: {
                    width:5
                },
                title: {
                    offsetCenter: [0, '-30%'],       // x, y，单位px
                },
                detail: {
                    // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder'
                },
                data:[{value: ${wwc}}]
            }

        ]
    };*/

    optionZY = {
        backgroundColor:'rgba(128, 128, 128, 0.5)', //rgba设置透明度0.1
        title:{
            text:'各专业案件统计',
            x:0,
            textStyle:{
                color:'rgb(255,255,255)',
                fontSize:14
            }
        },
        tooltip : {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            }
        },
        grid: {
            top:'12%',
            left: '6%',
            right: '6%',
            bottom: '6%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ${nameList}
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [

            {
                name:'各专业案件数量',
                type:'line',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                areaStyle: {normal: {}},
                data:${departmentList}
            }
        ]
    };

    myChart.setOption(option);
    myChart.off('click');//防止重复绑定 事件
    myChart.on('click',function(params){
        // console.log(params);
        // console.log(params.data);
        // alert(option.series[params.seriesIndex].rawdate[params.dataIndex]);
    });
    //myChart1.setOption(optionYB);
    myChart2.setOption(optionZX);
    myChart3.setOption(optionBZ);
    myChart4.setOption(optionZY);




    var rawData = ${areaList};

    var rawsData = [];
    function provinceMap(id,cityData){

        function sortRule(a,b){return a.value-b.value;}
        cityData.sort(sortRule);
        var provinceName=cityData.map(name => name.name);

        var provinceChart =  echarts.init(document.getElementById(id));

        var geoCoordMap = {
            '太原市': [112.53,37.87]
            ,'阳曲':[112.65,38.05]
            ,'娄烦':[111.78,38.05]
            ,'清徐':[112.33,37.62]
            ,'大同市':[113.3, 40.12]
            ,'阳泉市':[113.5733,37.8533]
            ,'长治市':[113.08,36.18]
            ,'天镇':[114.08,40.42]
            ,'灵丘':[114.2, 39.47]
            ,'怀仁':[113.1, 39.82]
            ,'山阴':[112.82,39.52]
            ,'平鲁':[112.12,39.53]
            ,'右玉':[112.33,40.18]
            ,'阳高':[113.72,40.38]
            ,'广灵':[113.27,39.75]
            ,'浑源':[113.68,39.7]
            ,'应县':[113.18,39.58]
            ,'朔县':[112.42,39.32]
            ,'左云':[112.67,40.02]
            ,'忻县':[112.7, 38.38]
            ,'代县':[112.97,39.07]
            ,'五台':[113.32,38.72]
            ,'静乐':[111.9, 38.37]
            ,'保德':[111.09,38.01]
            ,'河曲':[111.17,39.38]
            ,'神池':[112.17,39.1]
            ,'原平':[112.7, 38.73]
            ,'繁峙':[113.28,39.2]
            ,'定襄':[112.95,38.5]
            ,'岢岚':[111.58,38.7]
            ,'五寨':[111.82,38.93]
            ,'偏关':[111.47,39.45]
            ,'宁武':[112.28,39]
            ,'榆次':[112.72,37.68]
            ,'孟县':[113.37,38.01]
            ,'昔阳':[113.68,37.62]
            ,'左权':[113.35,37.07]
            ,'太谷':[112.53,37.42]
            ,'平遥':[112.18,37.2]
            ,'灵石':[111.77,36.83]
            ,'寿阳':[113.17,37.88]
            ,'平定':[113.62,37.79]
            ,'和顺':[113.55,37.33]
            ,'榆社':[112.97,37.08]
            ,'祁县':[112.33,37.36]
            ,'介休':[111.88,37.03]
            ,'离石':[111.13,37.53]
            ,'兴县':[111.22,38.47]
            ,'方由':[111.24,37.86]
            ,'岚县':[111.62,38.28]
            ,'交城':[112.14,37.55]
            ,'文水':[112.02,37.42]
            ,'汾阳':[111.75,37.27]
            ,'孝义':[111.8, 37.12]
            ,'交口':[111.2, 36.97]
            ,'石楼':[110.83,37]
            ,'中阳':[111.17,37.37]
            ,'临县':[110.95,37.95]
            ,'柳林':[110.85,37.45]
            ,'襄垣':[113.02,36.55]
            ,'黎城':[113.4, 36.56]
            ,'壶关':[113.23,35.11]
            ,'高平':[112.88,35.48]
            ,'阳城':[112.38,35.84]
            ,'长子':[112.87,36.13]
            ,'沁源':[112.32,36.5]
            ,'潞城':[113.22,36.33]
            ,'武乡':[112.83,36.83]
            ,'平顺':[113.43,36.19]
            ,'陵川':[113.27,35.78]
            ,'晋城市':[112.83,35.52]
            ,'沁水':[112.15,35.67]
            ,'屯留':[112.87,36.32]
            ,'沁县':[112.68,36.75]
            ,'临汾市':[111.5, 36.08]
            ,'汾西':[111.53,36.63]
            ,'安泽':[112.2, 36.15]
            ,'古县':[111.9, 36.29]
            ,'翼城':[111.68,35.73]
            ,'曲沃':[111.33,35.63]
            ,'吉县':[110.65,36.12]
            ,'大宁':[110.72,36.47]
            ,'侯马':[111.45,35.03]
            ,'永和':[110.64,36.62]
            ,'洪洞':[111.68,36.25]
            ,'霍县':[111.72,36.57]
            ,'浮山':[111.83,35.97]
            ,'襄汾':[111.43,35.86]
            ,'乡宁':[110.8, 35.97]
            ,'蒲县':[111.07,36.42]
            ,'运城市':[110.97,35.03]
            ,'闻喜':[111.2, 35.37]
            ,'垣曲':[111.63,35.3]
            ,'芮城':[110.68,34.71]
            ,'临猗':[110.78,35.15]
            ,'新绛':[111.22,35.62]
            ,'河津':[110.7, 35.58]
            ,'夏县':[111.22,35.12]
            ,'绛县':[111.58,35.48]
            ,'平陆':[111.2, 34.12]
            ,'永济':[110.42,34.88]
            ,'万荣':[110.83,110.8]
            ,'稷山':[110.97,35.6]
        };
        var provinceOption = {
            backgroundColor:'rgba(128, 128, 128, 0)', //rgba设置透明度0.1
            legend: [],
            xAxis: [{
                type: "value",
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    show: false
                },
                splitLine: {
                    show: false
                },
            }],
            yAxis: [{
                type: "category",

                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false,
                    alignWithLabel: true
                },
                axisLabel: {
                    textStyle:{
                        show:false
                    }
                },
                data:provinceName,
            }],
            grid: [{
                left: "70%",
                right: "20",
                bottom: "10",
                top:'60%',
                containLabel: true
            }],
            title:[{
                text:'山西省案件投诉量',
                x:0,
                textStyle:{
                    color:'rgb(0,0,0)',
                    fontSize:12
                }
            },{
                text: "各市投诉量",
                textStyle: {
                    fontWeight: "bold",
                    fontSize: 14
                },
                top: "55%",
                left: "69%"
            }],
            tooltip:[{
                show:true,
            }],
            //backgroundColor:'#aaa',

            geo:{
                show:true,
                map:'山西',
                type:'map',
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true,
                        fontSize:15,
                        fontWeight:'bold',
                        color:'rgb(80,50,180)',
                    }
                },
                roam: false,
                itemStyle: {
                    normal: {
                        color:'rgba(128, 128, 128, 0.4)',
                        borderColor: '#fff',
                        borderWidth: '0.8',
                    },
                    emphasis: {
                        areaColor: '#E9E842',
                    },
                }
            },
            series: [{
                name: "投诉数量",
                type: "bar",
                data: cityData,
                barWidth:7,
                barCategoryGap: "50%",
                label: {
                    normal: {
                        left:'right',
                        show: true,
                        position: "right",
                        formatter: function(params) {
                            return params.data.value;
                        },
                        textStyle: {
                            color: "#fff" //color of value
                        }
                    }
                },
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                            offset: 0,
                            color: "rgb(231,91,250)" // 0% 处的颜色
                        }, {
                            offset: 1,
                            color: "rgb(80,50,180)" // 100% 处的颜色
                        }], false),
                        barBorderRadius: [30, 30,30, 30],
                    },
                }
            }],


        };//option
        provinceChart.setOption(provinceOption);

        function renderEachCity() {
            var width=$('#bigMain').width();
            var height=$('#bigMain').height();
            // option.xAxis.push();
            // option.yAxis.push();
            // option.grid.push();map
            // option.series.push();
            echarts.util.each(rawData, function(dataItem, idx) {

                var geoCoord = geoCoordMap[dataItem.name];
                var coord = provinceChart.convertToPixel('geo', dataItem.name);
                idx += '';


                provinceOption.xAxis.push({
                    id: idx,
                    gridId: idx,
                    type: 'category',
                    show: false
                });
                provinceOption.yAxis.push({
                    id: idx,
                    gridId: idx,
                    show: false
                });
                provinceOption.grid.push({
                    id: idx,
                    width: 10,
                    height: (dataItem.value),
                    left: coord[0],
                    bottom:height-coord[1]+10,
                });


                provinceOption.series.push({
                    name:dataItem.name,
                    type: 'bar',
                    xAxisId: idx,
                    yAxisId: idx,
                    road:true,
                    itemStyle: {
                        normal: {
                            color:new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: 'rgba(231,91,250,1)'
                            }, {
                                offset: 1,
                                color: 'rgba(80,50,180,0.9)'
                            }], false),
                            barBorderRadius: [30, 30,30, 30],
                            borderColor:'rgba(255,255,255,0.8)',
                        },
                        emphasis:{
                            color:new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 1,
                                color: 'rgb(231,91,250)'
                            }, {
                                offset: 0,
                                color: 'rgb(80,50,180)'
                            }], false)
                        }
                    },
                    data: [dataItem.value]
                });


            });
            provinceChart.setOption(provinceOption);
        }

        renderEachCity();

        var a=0;//省级 跳入 市级，返回上一级 是 初始化为0
        provinceChart.off('click');//防止重复绑定 事件
        provinceChart.on('click',function(params){
            if(params.name!=undefined && params.name!='' && a==0){
                a=1;
                provinceChart.clear();
                Maps('bigMain',rawsData,params.name);
            }
        })

    }

    function Maps(id,cityData,cityName){
        var name=cityData.map(name=>name.name);


        var chartCity =  echarts.init(document.getElementById(id));
        $.getJSON('${ctxStatic}/echarts/map/json/shanxi/'+cityName+'.json', function (usaJson) {
            echarts.registerMap('city', usaJson);
            var geoCoordMap = {
                '时代互联':[114.085947,22.547],
                '朗达互动':[114.005947,22.547],
                '飞远网络':[113.353986,21.924979],
                '科飞科技':[113.353986,21.924979],
                '明飞互联':[113.353986,21.924979],
                '万网网络':[113.353986,21.924979],
                '大朗科技':[113.353986,21.924979],
                '腾度科技':[112.353986,22.924979]



            };
            //不是本市 的数据
            echarts.util.each(rawsData, function(dataItem, idx) {

                var coord = chartCity.convertToPixel('geo', dataItem.name);
            });
            var optionCity = {
                backgroundColor:'rgba(128, 128, 128, 0)', //rgba设置透明度0.1
                legend: [],
                xAxis: [{
                    type: "value",
                    axisLine: {
                        show: false
                    },
                    axisTick: {
                        show: false
                    },
                    axisLabel: {
                        show: false
                    },
                    splitLine: {
                        show: false
                    }
                }],
                yAxis: [{
                    type: "category",

                    axisLine: {
                        show: false
                    },
                    axisTick: {
                        show: false,
                        alignWithLabel: true
                    },
                    axisLabel: {
                        textStyle:{
                            show:false
                        }
                    },
                    data:name,
                }],
                grid: [{
                    left: "70%",
                    right: "20",
                    bottom: "10",
                    top:'60%',
                    containLabel: true
                },],
                title:[{
                    text:cityName+'投诉数量',
                    x:0,
                    textStyle:{
                        color:'rgb(255,255,255)',
                        fontSize:14
                    }
                },{
                    text: "各县投诉量",
                    textStyle: {
                        color: "#fff",
                        fontWeight: "bold",
                        fontSize: 14
                    },
                    top: "60%",
                    left: "80%"
                }],
                tooltip:[{
                    // formatter:function(params){
                    //     console.log(params)
                    //     var content='',
                    //     content=params.name+params.value[0]+params.value[1]+params.value[2];
                    //     return content;
                    // },
                    show:true,
                }],
                // backgroundColor:'#fff',
                // visualMap: {
                //     show: false,
                //     min: 0,
                //     max: 3000,
                //     inRange: {
                //         color: ['#00ffff', '#006edd'],
                //          color: ['#00467F', '#A5CC82']
                //     },
                //     calculable:true

                // },

                geo:{
                    left:'10',
                    show:true,
                    map:'city',
                    type:'map',
                    zlevel:0,
                    label: {
                        normal: {
                            show: false
                        },
                        emphasis: {
                            show: true,
                            fontSize:20,
                            color:'rgb(255,255,255)',
                        }
                    },
                    roam: false,
                    itemStyle: {
                        normal: {
                            color:'rgba(128, 128, 128, 0.4)',
                            borderColor: '#fff',
                            borderWidth: '0.8',
                        },
                        emphasis: {
                            areaColor: '#2B91B7',
                        }
                        // normal: {
                        //     color: 'rgb(0,90,157)',
                        //     borderColor: 'rgb(0,90,157)',
                        //     borderWidth: 1,
                        // },
                        // emphasis: {
                        //     areaColor: '#2B91B7',
                        // }
                    }
                },
                series: [{
                    name: "投诉量",
                    type: "bar",
                    data: cityData,
                    barWidth:7,
                    barCategoryGap: "50%",
                    zlevel:2,
                    label: {
                        normal: {
                            left:'right',
                            show: true,
                            position: "right",
                            formatter: function(params) {
                                return params.data.value;
                            },
                            textStyle: {
                                color: "#fff" //color of value
                            }
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                                offset: 0,
                                color: "rgb(231,91,250)" // 0% 处的颜色
                            }, {
                                offset: 1,
                                color: "rgb(80,50,180)" // 100% 处的颜色
                            }], false),
                            barBorderRadius: [30, 30,30, 30],
                        }
                    }
                }]

            };//option
            chartCity.setOption(optionCity);

            function renderEachCitys() {
                var width=$('#'+id).width();
                var height=$('#'+id).height();
                // option.xAxis.push();
                // option.yAxis.push();
                // option.grid.push();
                // option.series.push();
                echarts.util.each(rawsData, function(dataItem, idx) {

                    var geoCoord = geoCoordMap[dataItem.name];
                    var coord = chartCity.convertToPixel('geo', dataItem.name);
                    idx += '';
                    if(coord!=undefined){
                        optionCity.xAxis.push({
                            id: idx,
                            gridId: idx,
                            type: 'category',
                            //show: true,
                            data:[dataItem.name],
                            boundaryGap:true,
                            axisLine: {
                                show: false
                            },
                            axisTick: {


                                show: false,

                            },
                            axisLabel: {
                                interval:0,
                                fontSize:10,

                                color: "#fff",



                            }
                        });
                        optionCity.yAxis.push({
                            id: idx,
                            gridId: idx,
                            show: false
                        });
                        optionCity.grid.push({
                            id: idx,
                            width: 10,
                            height: (dataItem.value),
                            left: coord[0],
                            bottom:height-coord[1]+10,
                        });


                        optionCity.series.push({
                            name:dataItem.name,
                            type: 'bar',
                            xAxisId: idx,
                            yAxisId: idx,
                            zlevel:1,
                            road:true,
                            itemStyle: {
                                normal: {
                                    color:new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                        offset: 0,
                                        color: 'rgba(231,91,250,1)'
                                    }, {
                                        offset: 1,
                                        color: 'rgba(80,50,180,0.8)'
                                    }], false),
                                    borderColor:'rgba(255,255,255,0.8)',
                                    barBorderRadius: [30, 30,30, 30],
                                },
                                emphasis:{
                                    color:new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                        offset: 1,
                                        color: 'rgb(231,91,250)'
                                    }, {
                                        offset: 0,
                                        color: 'rgb(80,50,180)'
                                    }], false)
                                }
                            },
                            data: [dataItem.value]
                        });
                    }

                });
                chartCity.setOption(optionCity);
            }

            renderEachCitys();


            chartCity.on('click',function(params){
                chartCity.clear();
                provinceMap('bigMain',rawData);
            })
        });
    }

    provinceMap('bigMain',rawData);
</script>

</body>
</html>
