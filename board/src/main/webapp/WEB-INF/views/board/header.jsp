<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/style.css"/>
<script type="text/javascript" src="../js/board.js"></script>
<title>SPRING SAMPLE PROJECT 테스트</title>
<meta content="text/html; charset=utf-8" http-equiv="content-type">

<meta http-equiv="cache-control" content="max-age=0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT">
<meta http-equiv="pragma" content="no-cache">
</head>
<body>

<table width="100%" class="table_default">
<tr>
	<td align="left">
		<b>SPRING SAMPLE PROJECT.</b>
	</td>
	<td align="right">
		<c:if test="${!empty userSession.user}">
			[<a href="<c:url value="/board/logout.html"/>">로그아웃</a> | <c:out value="${userSession.user.userName}"/>]님 어서오세요~ 
		</c:if>
		<c:if test="${empty userSession.user}">
			[<a href="<c:url value="/user/insertUserForm.html"/>">회원가입</a> | <a href="<c:url value="/board/logonForm.html"/>">로그인</a>]
		</c:if>
	</td>
</tr>
</table>
<hr size="1"/>