<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.6.4.min.js"></script>

<style type="text/css">
<!--
#clock {
	width: 450px;
	background-color: #000000;
	color: lime;
	text-align: center;
	border: 10px solid blue;
	font-size: 90px;
	font-family: monospace;
	font-weight: bold;
}
-->
</style>
<c:url value="/ajax/jsonIn1.html" var="json1url"/>
<script type="text/javascript">
$(function(){
	$("#jsonIn").click(function(){
		alert("${json1url}");
		$.ajax({
			type:"POST",
			url:"/webhello/ajax/jsonIn1.html",
			contentType:"application/json;charset=utf-8",	
			data:'{"custId":"1","custName":"김태희"}',
			dataType:"html",
			success:function(data){
				alert(data);
			},
			error:function(XMLHttpRequest,textStatus,errorThrown){
				alert("error!!!"+XMLHttpRequest+" "+textStatus+" "+errorThrown);
			}
		});
	});
	
});
</script>

</head>
<body>
	<h1>
		<c:out value="${msg}" />
		<br /> customer count = ${ccount }<br />
	</h1>

	<h2>customer list</h2>
	<table BORDER="1">
		<tr>
			<th id="jsonIn">id</th>
			<th>이름</th>
			<th>주소</th>
			<th>email</th>
			<th>상세</th>
			<th>편집</th>
		</tr>
		<c:forEach items="${customerList}" var="cunit">
			<tr>
				<td>${cunit.custId}</td>
				<td>${cunit.custName}</td>
				<td>${cunit.custAddr}</td>
				<td>${cunit.custEmail}</td>
				<c:url value="/customer.html?customerId=${cunit.custId}" var="d_url"/>
				<td><a href="${d_url}">상세</a></td>
				<c:url value="/editCustomer.html?customerId=${cunit.custId}" var="e_url"/>
				<td><a href="${e_url}">편집</a></td>
			</tr>
		</c:forEach>
	</table>

	<hr />
	<hr />
	<h2>customer info</h2>
	<hr />
	id: ${cinfo.custId}
	<br /> name: ${cinfo.custName}
	<br /> address: ${cinfo.custAddr}
	<br /> email: ${cinfo.custEmail}
	<br />
	<hr />

</body>
</html>