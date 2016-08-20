<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%String basepath = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=basepath%>/js/jquery-1.6.2.js"></script>
<title>福彩3D开奖结果</title>
</head>
<body>
<div style="width: 1200px">
	<h3 style="color: red;">福彩3D开奖结果：</h3>
	<table border="1" cellspacing="0" width="100%">
		<thead>
			<tr>
				<td align="center">期次</td>
				<td align="center">开奖号码</td>
				<td align="center" >百位推荐号码</td>
				<td align="center" >十位推荐号码</td>
				<td align="center" >个位推荐号码</td>
				<td align="center" width="600px">全部号码组合</td>
				<td align="center" >组合数量合计</td>
			</tr>
		</thead>
		<c:if test="${fn:substring(list[0].expect,8,10) != 85 }">
			<tr>
				<td align="center">本期</td>
				<td align="center">未开奖</td>
				<td align="center"><c:out value="${baiWei}"/></td>
				<td align="center"><c:out value="${shiWei}"/></td>
				<td align="center"><c:out value="${geWei}"/></td>
				<td style="word-break:break-all"><c:out value="${zuhe}"/></td>
				<td align="center"><c:out value="${count}"/></td>
			</tr>
		</c:if>
		<c:forEach items="${list}" var="cp">
			<tr >
				<td align="center"><c:out value="${cp.expect}"/></td>
				<td align="center">${cp.openCode}</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>