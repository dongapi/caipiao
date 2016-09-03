<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String basepath = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<script type="text/javascript" src="js/jquery-1.6.2.js"></script>
<script type="text/javascript">
$(function(){
// 	$('#bj11x5').click(function(){
<%-- 		window.location.href="<%=basepath%>/queryCp?cpCode=bj11x5"; --%>
// 	});
// 	$('#cq11x5').click(function(){
<%-- 		window.location.href="<%=basepath%>/queryCp?cpCode=cq11x5"; --%>
// 	});
})
</script>
</head>
<body>
<h3 style="color: red;">11选5：</h3>
<a id="bj11x5" href="<%=basepath%>/query11_5?cpCode=bj11x5" target="_blank">查询北京11选5开奖结果</a> |
<a id="sh11x5" href="<%=basepath%>/query11_5?cpCode=sh11x5" target="_blank">查询上海11选5开奖结果</a> <br/>
<h3 style="color: red;">福彩3D：</h3>
<a id="3d" href="<%=basepath%>/query3D?cpCode=fc3d" target="_blank">福彩3D开奖结果(推荐号码组合)</a> |
<a id="3d" href="<%=basepath%>/query3D?cpCode=fc3d&flg=mtd" target="_blank">福彩3D开奖结果(方法排除明细)</a>

</body>
</html>