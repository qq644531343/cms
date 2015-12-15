<%@page import="com.sina.cms.dao.ArticleDAO"%>
<%@page import="com.sina.cms.model.ArticleModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/backend/";
%>
<%
	String tidString = request.getParameter("tid");
	if(tidString == null) return;
	int tid = Integer.parseInt(tidString);
	
	ArticleModel article = new ArticleDAO().queryArticle(tid, true);
	session.setAttribute("article", article);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加文章</title>
 <link href="css/article.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div id="formwrapper">
		<h3>编辑网站文章</h3>
		<form action="UpdateArticleServlet" method="post">
			<fieldset>
				<legend>文章基本信息</legend>
				<div>
					<label for="title">文章标题</label> <input type="text" name="title"
						id="title" value="${article.title }" size="60" maxlength="200" /> <font color="#ff0000">*</font>(最多200个字符)<br />
				</div>
				<div>
					<label for="source">文章来源</label> <input type="text" name="source"
						id="source" value="${article.originUrl }" size="30" maxlength="512" /> (最多512个字符)<br />
				</div>
				<div>
					<label for="content">文章内容</label>
					<textarea rows="20" cols="100" name="content" id="content" >${article.content }</textarea>
					<br />
				</div>
				<div class="enter">
					<input name="submit" type="submit" class="buttom" value="提交" /> <input
						name="reset" type="reset" class="buttom" value="重置" /> <input
						name="return" type="button" class="buttom" value="返回列表页面"
						onclick="window.location = 'SearchArticleServlet'" />
				</div>
			</fieldset>
		</form>
	</div>

</body>
</html>