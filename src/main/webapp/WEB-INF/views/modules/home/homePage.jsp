<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>统计</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/echarts/dist/echarts.min.js" type="text/javascript"></script>
</head>
<body>
<ul class="nav nav-tabs">
	<%--<li ><a href="${ctx}/complaintmain/complaintMain/self">我的待办</a></li>--%>
	<li class="active"><a href="${ctx}/complaintmain/complaintMain/home?type=myDone">我的已办</a></li>
</ul>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 30%; height: 300px;"></div>
<script type="text/javascript">
	var myChart = echarts.init(document.getElementById('main'));
	option = {
		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b}: {c} ({d}%)"
		},
		legend: {
			orient: 'vertical',
			x: 'left',
			data:['已完成案件','未完成案件']
		},
		series: [
			{
				name:'案件统计',
				type:'pie',
				radius: ['50%', '70%'],
				avoidLabelOverlap: false,
				label: {
					normal: {
						show: false,
						position: 'center'
					},
					emphasis: {
						show: true,
						textStyle: {
							fontSize: '30',
							fontWeight: 'bold'
						}
					}
				},
				labelLine: {
					normal: {
						show: false
					}
				},
				data:[
					{value:'${ywc}', name:'已完成案件'},
					{value:'${wwc}', name:'未完成案件'}
				]
			}
		]
	};

	myChart.setOption(option);


</script>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>案件编号</th>
		<th>患者姓名</th>
		<th>患者性别</th>
		<th>患者年龄</th>
		<th>患者身份证</th>
		<th>操作节点</th>
		<th>修改时间</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${list}" var="complaintMain">
		<tr>
			<td>
					${complaintMain.caseNumber}
			</td>
			<td>
					${complaintMain.patientName}
			</td>
			<td>
				<c:if test="${complaintMain.patientSex=='1'}">
					男
				</c:if>
				<c:if test="${complaintMain.patientSex=='2'}">
					女
				</c:if>
			</td>
			<td>
					${complaintMain.patientAge}
			</td>
			<td>
					${complaintMain.patientCard}
			</td>
			<td>
					${complaintMain.nodeName}
			</td>
			<td>
				<fmt:formatDate value="${complaintMain.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td>
				<a href="${ctx}${complaintMain.url}form?id=${complaintMain.key}&type=view">详情</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</body>
</html>
