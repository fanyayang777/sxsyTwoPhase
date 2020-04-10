<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>培训学习</title>
    <meta name="decorator" content="default"/>
    <script src="${ctxStatic}/echarts/dist/echarts.min.js" type="text/javascript"></script>
    <script src="${ctxStatic}/echarts/dist/echarts-gl.min.js"></script>
    <script src="${ctxStatic}/echarts/map/js/province/shanxi.js"></script>
    <script src="${ctxStatic}/echarts/dark.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            //浏览器时下窗口可视区域高度
            var cc=$(window).height()-10;
            $("#div").css("height",cc+"px");
        })
    </script>
</head>

<style type="text/css">

   body{
       background-color: white;
       margin: 0;
       padding: 0;
   }

</style>


<body>
<div id="div" style="width: 100%;">
    <iframe  src="http://www.haspt.com/#/index?send=2" width="100%"  frameborder="0"  style="overflow:visible;" scrolling="yes" height="100%"></iframe>
    <iframe  src="http://192.168.1.110:8080/#/index?send=2" width="100%"  frameborder="0"  style="overflow:visible;" scrolling="yes" height="100%"></iframe>

</div>


</body>
</html>
