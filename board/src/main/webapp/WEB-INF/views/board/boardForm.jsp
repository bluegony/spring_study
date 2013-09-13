<%@ include file="header.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script language="javascript">
	function saveBoard() {
		document.boardForm.submit();
	}
</script>

<!-- Support for Spring errors object -->
<table align="center"><tr><td class="table_default">
<spring:bind path="boardForm.*">
	<c:forEach var="error" items="${status.errorMessages}">
		<b><font color="red"><br>. <c:out value="${error}"/></font></b>
	</c:forEach>
</spring:bind>
</td></tr></table>

<c:if test="${boardForm.newBoard}">
<c:url value="/board/insertBoardForm.html" var="url"/>
</c:if>
<c:if test="${!boardForm.newBoard}">
<c:url value="/board/editBoardForm.html" var="url"/>
</c:if>

<%--<form name="boardForm" action="${url }" method="POST">--%>
<form:form modelAttribute="boardForm" name="boardForm" action="${url}" method="POST">
<input type="hidden" name="boardId" value="${boardForm.boardId }"/>
<input type="hidden" name="visited" value="${boardForm.visited}"/>
<input type="hidden" name="insertdate" value="${boardForm.insertDate}"/>
<input type="hidden" name="recom" value="${boardForm.recom}"/>
<input type="hidden" name="userid" value="${boardForm.userid}"/>

<table class="table_default" border="0" cellpadding="3" cellspacing="1" width="80%" align="center" bgcolor="#949EA5">
<tr height="30"> 
	<td bgcolor="#7B869A" colspan="2" align="center">
		<c:if test="${boardForm.newBoard}">
			<font color="#FFFFFF"><b>글 쓰기</b></font>
		</c:if>		
		<c:if test="${!boardForm.newBoard}">
			<font color="#FFFFFF"><b>글 수정</b></font>
		</c:if>	
	</td>
</tr>
<tr height="30"> 
	<td bgcolor="#FFFFFF" align="right">제 목</td>
	<td bgcolor="#FFFFFF">
		<spring:bind path="boardForm.title">
			<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" size="60" maxlength="60"/>
		</spring:bind>
	</td>
</tr>
<tr height="30"> 
	<td bgcolor="#FFFFFF" align="right">HTML 사용 여부 </td>
	<td bgcolor="#FFFFFF">
		<spring:bind path="boardForm.htmlyn">
			<select name="<c:out value="${status.expression}"/>">
				<c:forEach var="htmlyn" items="${htmlyns}">
					<option <c:if test="${htmlyn == status.value}">selected</c:if> value="<c:out value="${htmlyn}"/>"><c:out value="${htmlyn}"/></option>
				</c:forEach>
			</select>
		</spring:bind>
	</td>
</tr>
<tr> 
	<td bgcolor="#FFFFFF" align="right">내 용</td>
	<td bgcolor="#FFFFFF">
		<spring:bind path="boardForm.content">
			<textarea name="<c:out value="${status.expression}"/>" rows="15" cols="60"><c:out value="${status.value}"/></textarea>
		</spring:bind>
	</td>
</tr>
<tr height="30"> 
	<td bgcolor="#FFFFFF" colspan="2" align="center">
		[ 
		<a href="<c:url value="javascript:saveBoard()"/>">저장</a> | 
		<a href="<c:url value="/board/listBoard.html"/>">리스트</a> 
		]
	</td>
</tr>
</table>

</form:form>

<%@ include file="footer.jsp"%>