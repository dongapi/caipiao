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
<title>福彩3D单选</title>
</head>
<body>
<div style="width: 1600px">
	<h3 style="color: red;">福彩3D开奖结果：</h3>
	<table border="1" cellspacing="0" width="100%">
		<thead>
			<tr>
				<td align="center" style="white-space:nowrap;">期次</td>
				<td align="center" style="white-space:nowrap;">开奖号码</td>
				<td align="center" style="white-space:nowrap;">下期百位推荐</td>
				<td align="center" style="white-space:nowrap;">下期十位推荐</td>
				<td align="center" style="white-space:nowrap;">下期个位推荐</td>
				<td align="center" style="white-space:nowrap;">下期推荐号码(单选)</td>
				<td align="center" style="white-space:nowrap;">总数</td>
				<td align="center" style="white-space:nowrap;">64组合号码</td>
				<td align="center" style="white-space:nowrap;">合并后号码</td>
				<td align="center" style="white-space:nowrap;">合并后总数</td>
			</tr>
		</thead>
		<c:forEach items="${dxList}" var="cp">
			<tr >
				<td align="center"><c:out value="${cp.expect}"/></td>
				<td align="center">${cp.openCode}</td>
				<td align="center" title="${cp.baiResult.msg}">${cp.baiResult.list}</td>
				<td align="center" title="${cp.shiResult.msg}">${cp.shiResult.list}</td>
				<td align="center" title="${cp.geResult.msg}">${cp.geResult.list}</td>
				<td style="word-break:break-all">${cp.dxtjhm}</td>
				<td align="center"><c:out value="${cp.dxCount}"/></td>
				<td style="word-break:break-all"><c:out value="${cp.zwhm}"/></td>
				<td style="word-break:break-all">${cp.hbhm}</td>
				<td align="center"><c:out value="${cp.hbCount}"/></td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>