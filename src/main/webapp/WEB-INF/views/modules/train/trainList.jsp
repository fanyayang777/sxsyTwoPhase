<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>培训视频管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/train/train/">培训视频列表</a></li>
		<shiro:hasPermission name="train:train:edit"><li><a href="${ctx}/train/train/form">培训视频添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="train" action="${ctx}/train/train/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>是否付费：</label>
				<form:select path="payment" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>视频类型</th>
				<th>科室</th>
				<th>路径</th>
				<th>发往：小程序 网站 后台</th>
				<th>介绍</th>
				<th>评分</th>
				<th>是否付费</th>
				<shiro:hasPermission name="train:train:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="train">
			<tr>
				<td><a href="${ctx}/train/train/form?id=${train.id}">
					${train.title}
				</a></td>
				<td>
					${fns:getDictLabel(train.vidioType, 'video_type', '')}
				</td>
				<td>
					${train.department}
				</td>
				<td>
					${train.path}
				</td>
				<td>
					${fns:getDictLabel(train.send, '', '')}
				</td>
				<td>
					${train.introduce}
				</td>
				<td>
					${train.score}
				</td>
				<td>
					${fns:getDictLabel(train.payment, 'yes_no', '')}
				</td>
				<shiro:hasPermission name="train:train:edit"><td>
    				<a href="${ctx}/train/train/form?id=${train.trainId}">修改</a>
					<a href="${ctx}/train/train/delete?id=${train.trainId}" onclick="return confirmx('确认要删除该培训视频吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>