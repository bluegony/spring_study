<%@ include file="header.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script language="javascript">
	function saveUser() {
		document.userForm.submit();
	}
</script>

<!-- Support for Spring errors object -->
<table align="center"><tr><td class="table_default">
<spring:bind path="userForm.*">
	<c:forEach var="error" items="${status.errorMessages}">
		<b><font color="red"><br>. <c:out value="${error}"/></font></b>
	</c:forEach>
</spring:bind>
</td></tr></table>

<c:if test="${userForm.newUser}">
<form name="userForm" action="<c:url value="/user/insertUserForm.html"/>" method="POST">
</c:if>
<c:if test="${!userForm.newUser}">
<form name="userForm" action="<c:url value="/user/editUserForm.html"/>" method="POST">
</c:if>

<table class="table_default" border="0" cellpadding="3" cellspacing="1" width="80%" align="center" bgcolor="#949EA5">
<tr height="30"> 
	<td bgcolor="#7B869A" colspan="2" align="center">
		<c:if test="${userForm.newUser}">
			<font color="#FFFFFF"><b>회원 가입</b></font>
		</c:if>		
		<c:if test="${!userForm.newUser}">
			<font color="#FFFFFF"><b>회원 수정</b></font>
		</c:if>	
	</td>
</tr>
<tr height="30"> 
	<td bgcolor="#FFFFFF" align="right">회원 ID</td>
	<td bgcolor="#FFFFFF">
	<c:if test="${userForm.newUser}">
		<spring:bind path="userForm.user.userid">
			<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" size="10" maxlength="10"/>
		</spring:bind>
	</c:if>
	<c:if test="${!userForm.newUser}">
		<c:out value="${status.value}"/>
	</c:if>
	</td>
</tr>
<tr height="30"> 
	<td bgcolor="#FFFFFF" align="right">비밀번호</td>
	<td bgcolor="#FFFFFF">
		<spring:bind path="userForm.user.passwd">
			<input type="password" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" size="10" maxlength="10"/>
		</spring:bind>
	</td>
</tr>
<tr height="30"> 
	<td bgcolor="#FFFFFF" align="right">비밀번호 확인</td>
	<td bgcolor="#FFFFFF">
		<spring:bind path="userForm.repeatedPasswd">
			<input type="password" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" size="10" maxlength="10"/>
		</spring:bind>
	</td>
</tr>
<tr height="30"> 
	<td bgcolor="#FFFFFF" align="right">이름</td>
	<td bgcolor="#FFFFFF">
		<spring:bind path="userForm.user.userName">
			<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" size="10" maxlength="10"/>
		</spring:bind>
	</td>
</tr>
<tr height="30"> 
	<td bgcolor="#FFFFFF" align="right">이메일</td>
	<td bgcolor="#FFFFFF">
		<spring:bind path="userForm.user.email">
			<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" size="40" maxlength="50"/>
		</spring:bind>
	</td>
</tr>
<tr height="30"> 
	<td bgcolor="#FFFFFF" colspan="2" align="center">
		[ 
		<a href="<c:url value="javascript:saveUser()"/>">저장</a> | 
		<a href="<c:url value="/board/listBoard.html"/>">리스트</a> 
		]
	</td>
</tr>
</table>

</form>

<%@ include file="footer.jsp" %>