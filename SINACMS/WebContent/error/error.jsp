<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/error/";
	
	Object errorcode = request.getAttribute("javax.servlet.error.status_code");
	if(errorcode != null) {
		String code = errorcode.toString();
		int statuscode = Integer.parseInt(code);
		if(statuscode == 404){
			request.setAttribute("error", "您访问的页面不见了！");
		}else if(statuscode == 500) {
			request.setAttribute("error", "服务器挂了!!");
		}else {
			request.setAttribute("error", request.getAttribute("javax.servlet.error.message") + "("+code+")");
		}
	}else if(request.getAttribute("error") == null) {
		request.setAttribute("error", request.getAttribute("javax.servlet.error.exception"));
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出现错误啦！</title>
<STYLE type=text/css>
INPUT {
	FONT-SIZE: 12px
}

TD {
	FONT-SIZE: 12px
}

.p2 {
	FONT-SIZE: 15px;
}

.p6 {
	FONT-SIZE: 12px;
	COLOR: #1b6ad8
}

A {
	COLOR: #1b6ad8;
	TEXT-DECORATION: none
}

A:hover {
	COLOR: red
}
</STYLE>
</head>
<body>

	<TABLE cellSpacing=0 cellPadding=0 width=540 align=center border=0>
		<TBODY>
			<TR>
				<TD vAlign=top height=270>
					<DIV align=center>
						<BR>
						<IMG height=211 src="error.gif" width=329><BR>
						<BR>
						<TABLE cellSpacing=0 cellPadding=0 width="50%" border=0  style="margin-top: 30px">
							<TBODY>
								<TR>
									<TD><FONT class=p2>出错的原因是：</FONT></TD>
								</TR>
								<TR>
									<TD height=8></TD>
								<TR>
									<TD>
										<P>
											<FONT color=#ff0000 class=p2>
												<IMG height=13 src="emessage.gif" width=12 style="vertical-align: top;" />
												${error}
											</FONT>
										</P>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</DIV>
				</TD>
			</TR>
			<TR>
				<TD height=5></TD>
			<TR>
				<TD align="center">
					<center>
						<TABLE cellSpacing=0 cellPadding=0 width=120 border=0>
							<TBODY>
								<TR onclick="javascript:history.go(-1)">
									<TD width=6><IMG height=26 src="left.gif" width=7></TD>
									<TD background="bg.gif">
										<DIV align=center>
											<FONT class=p6> <A href="javascript:void(0)">返回上一页</A></FONT>
										</DIV>
									</TD>
									<TD width=7><IMG src="right.gif"></TD>
								</TR>
							</TBODY>
						</TABLE>
					</CENTER>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
</body>
</html>