<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>상세</h2>
	<c:url value="/editCustomer.html" var="url" />
	<form name="form1" action="${url}" method="POST">
		<input type="hidden" name="pId" value="${custInfo.custId}"/>
		<dl>
			<dt>이름</dt>
			<dd>
				<input type="text" name="pName" value="${custInfo.custName}" />
			</dd>
			<dt>주소</dt>
			<dd>
				<input type="text" name="pAddr" value="${custInfo.custAddr}" />
			</dd>
			<dt>email</dt>
			<dd>
				<input type="text" name="pEmail" value="${custInfo.custEmail}" />
			</dd>
		</dl>
			<input type="submit" value="수정">
	</form>
</body>
</html>