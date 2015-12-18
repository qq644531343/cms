<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--  items:数据数 maxPageItems:每页数 maxIndexPages显示多少个页标 -->
	<pg:pager items="2" maxPageItems="5" maxIndexPages="3" export="currentPage=pageNumber">
		<pg:first>
			<a href="${pageUrl }">首页</a>
		</pg:first>
		<pg:prev>
			<a href="${pageUrl }">上页</a>
		</pg:prev>
		<pg:pages>
			<c:choose>
				<c:when test="${currentPage eq pageNumber }">
					<font color="#ff0000">${pageNumber }</font>
				</c:when>
				<c:otherwise>
				<a href="${pageUrl }">${pageNumber }</a>
			</c:otherwise>
			</c:choose>
		</pg:pages>
		<pg:next>
			<a href="${pageUrl }">下页</a>
		</pg:next>
		<pg:last>
			<a href="${pageUrl }">尾页</a>
		</pg:last>
	</pg:pager>
</body>
</html>