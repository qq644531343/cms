<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>CMS 后台管理工作平台</title>
	<link rel="stylesheet" type="text/css" href="css/style.css"/>
	<script type="text/javascript" src="js/js.js"></script>

</head>
<body>
<div id="top"> </div>

<form id="login" action="login" method="post" >
  <div id="center">
  <c:if test="${sessionScope.error != null}">
  	<div id="errorserver">${ error }</div>
  	<c:remove var="error" scope="session"/>
  </c:if>
  <div id="error"></div>
    <div id="center_middle">
      <div class="user">
        <label>用户名：
        <input type="text" name="username" id="username" value="${param.username}"/>
        </label>
      </div>
      <div class="user">
        <label>密　码：
        <input type="password" name="password" id="password" value="${param.password}" />
        </label>
      </div>
      <div class="chknumber">
        <label>验证码：
        <input name="checkcode" type="text" id="checkcode" maxlength="4" class="chknumber_input" />
        </label>
        <img src="images/checkcode.jpg" id="safecode" onclick="reloadcode(this)" title="点击刷新"  style="vertical-align: top;margin-left: 5px;"/>
      </div>
    </div>
    <div id="center_middle_right"></div>
    <div id="center_submit">
      <div class="button"> <img src="images/dl.gif" width="57" height="20" onclick="form_submit()" > </div>
      <div class="button"> <img src="images/cz.gif" width="57" height="20" onclick="form_reset()"> </div>
    </div>
  </div>
</form>

<div id="footer">新浪网技术(中国)有限公司</div>
</body>
</html>

