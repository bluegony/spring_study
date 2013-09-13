<%@ include file="header.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %>

<c:if test="${empty message}">
	<b><font color="RED"><c:url value="${message}"/></font></b>
</c:if>

<form action="<c:url value="/board/logon.html"/>" method="POST">

<c:if test="${!empty logonForwardAction}">
	<input type="hidden" name="forwardAction" value="<c:url value="${logonForwardAction}"/>"/>
</c:if>

<table class="table_default" border="0" cellpadding="3" cellspacing="1" width="300" align="center" bgcolor="#949EA5">
<tr height="30"> 
	<td bgcolor="#7B869A" colspan="2" align="center">
		<font color="#FFFFFF"><b>로그인</b></font>
	</td>
</tr>
<tr> 
	<td bgcolor="#FFFFFF" align="right">회원ID : </td>
	<td bgcolor="#FFFFFF"><input type="text" name="userid" size="10" maxlength="10"></td>
</tr>
<tr> 
	<td bgcolor="#FFFFFF" align="right">비밀번호 : </td>
	<td bgcolor="#FFFFFF"><input type="password" name="passwd" size="10" maxlength="10"></td>
</tr>
<tr> 
	<td bgcolor="#7B869A" colspan="2" align="center"><input type="submit" value="로그인"></td>
</tr>
</table>

</form>

<center>
<a href="<c:url value="/board/newUser.html"/>">회원가입</a>
</center>

<%@ include file="footer.jsp" %>