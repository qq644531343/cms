<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp"%>
<script type="text/javascript" src="/cms/backend/js/js.js"></script>

<!--  items:数据数 maxPageItems:每页数 maxIndexPages显示多少个页标 -->
<pg:pager items="${param.total }" url="${param.url}" maxPageItems="${pagesize}"
	maxIndexPages="10" export="currentPage=pageNumber">
	<pg:param name="title"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="33%"><div align="left">
					<span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp; 共有<strong>${total }</strong>条记录，
						当前第<strong>${currentPage }</strong> 页， <pg:last>
									共 <strong>${pageNumber }</strong>页
								</pg:last>
					</span>
				</div></td>
			<td width="67%" align=right vAlign="center" noWrap>
			<pg:first>
					<a id="firstPage" href="${pageUrl }">首页</a>
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
				</pg:pages> <pg:next>
					<a href="${pageUrl }">下页</a>
				</pg:next> <pg:last>
					<a href="${pageUrl }">尾页</a>
				</pg:last> <select name="pagesize" onchange="pagesizeChanged(this)">
					<c:forEach begin="5" end="50" step="5" var="i">
						<option value="${i }"
							<c:if test="${i eq pagesize }">selected</c:if>>${i }</option>
					</c:forEach>
			</select></td>
		</tr>
	</table>
</pg:pager>