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
<script type="text/javascript">
$(function(){
	setTimeout(rflash, 30000);
})
function rflash(){
	setTimeout(rflash, 30000);
	window.location.reload();
}
</script>
<title>${area}11选5开奖结果</title>
</head>
<body>
<div style="width: 900px">
	<h3 style="color: red;">${area}11选5   开奖结果：</h3>
	<table border="1" cellspacing="0" width="100%">
		<thead>
			<tr>
				<td align="center">期次</td>
				<td align="center">开奖号码</td>
				<td align="center" >一</td>
				<td align="center" >二</td>
				<td align="center" >三</td>
				<td align="center" >四</td>
				<td align="center" >五</td>
				<td align="center" >六</td>
				<td align="center" >七</td>
				<td align="center" >八</td>
				<td align="center" >九</td> 
				<td align="center" >十</td> 
				<td align="center" >十一</td> 
				<td align="center">任一推荐号码</td>
			</tr>
		</thead>
		<c:if test="${fn:substring(list[0].expect,8,10) != 85 }">
			<tr>
				<td align="center"><c:out value="${list[0].expect+1}"/></td>
				<td align="center"></td>
				<td align="center">${bean.meth1}</td>
				<td align="center">${bean.meth2}</td>
				<td align="center">${bean.meth3}</td>
				<td align="center">${bean.meth4}</td>
				<td align="center">${bean.meth5}</td>
				<td align="center">${bean.meth6}</td>
				<td align="center">${bean.meth7}</td>
				<td align="center">${bean.meth8}</td>
				<td align="center">${bean.meth9}</td>
				<td align="center">${bean.meth10}</td>
				<td align="center">${bean.meth11}</td>
				<td align="center"><c:out value="${numONE}"/></td>
			</tr>
		</c:if>
		<c:forEach items="${list}" var="cp">
			<tr >
				<td align="center"><c:out value="${cp.expect}"/></td>
				<td align="center">${cp.openCode}</td>
				<td align="center"><c:out value="${cp.recordBean.methodBean.meth1}"/></td>
				<td align="center"><c:out value="${cp.recordBean.methodBean.meth2}"/></td>
				<td align="center"><c:out value="${cp.recordBean.methodBean.meth3}"/></td>
				<td align="center"><c:out value="${cp.recordBean.methodBean.meth4}"/></td>
				<td align="center"><c:out value="${cp.recordBean.methodBean.meth5}"/></td>
				<td align="center"><c:out value="${cp.recordBean.methodBean.meth6}"/></td>
				<td align="center"><c:out value="${cp.recordBean.methodBean.meth7}"/></td>
				<td align="center"><c:out value="${cp.recordBean.methodBean.meth8}"/></td>
				<td align="center"><c:out value="${cp.recordBean.methodBean.meth9}"/></td>
				<td align="center"><c:out value="${cp.recordBean.methodBean.meth10}"/></td>
				<td align="center"><c:out value="${cp.recordBean.methodBean.meth11}"/></td>
				<td align="center"><c:out value="${cp.recordBean.ONElist}"/></td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>