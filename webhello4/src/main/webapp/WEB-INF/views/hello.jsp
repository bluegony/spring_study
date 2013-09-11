<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<c:url value="/ajax/jsonIn1.html" var="url"/>
<c:url value="/ajax/xmlIn1.html" var="x_url"/>
<c:url value="/ajax/xmlIn2.html" var="x_url2"/>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#xmlIn2").click(function(){
			$.ajax({
				type: "POST",
				url: "${x_url2}",
				contentType: "application/xml;charset=utf-8",
				data:  "<Customer><custId>99</custId><custName>ryu</custName></Customer>",
				dataType: "html",
				success: function(data) {
					alert(data);
					var name = $(data).find("custName").text();
					alert(name);
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("에러 발생!");
					alert("textStatus=" + textStatus);  //timeout, error, notmodified, parseerror
					alert(errorThrown);
				}
				
			});
		});
		$("#xmlIn").click(function(){
			$.ajax({
				type: "POST",
				url: "${x_url}",
				contentType: "application/xml;charset=utf-8",
				data: "<Customer><custId>99</custId><custName>ryu</custName></Customer>",
				dataType: "html",
				success: function(data) {
					alert(data);
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("에러 발생!");
					alert("textStatus=" + textStatus);  //timeout, error, notmodified, parseerror
					alert(errorThrown);
				}
				
			});
		});
		$("#jsonIn").click(function(){
			$.ajax({
				type: "POST",
				url: "${url}",
				contentType: "application/json;charset=utf-8",
				data: '{"custId":"1", "custName":"김길동"}',
				dataType: "html",
				success: function(data) {
					alert(data);
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("에러 발생!");
					alert("textStatus=" + textStatus);  //timeout, error, notmodified, parseerror
					alert(errorThrown);
				}
				
			});
		});
	});
</script>	
</head>
<body>
	<h1>
		현재 고객수: ${custCnt}
	</h1>
	<h2>고객 정보</h2>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>이름</th>
			<th>주소</th>
			<th>이메일주소</th>
			<td></td>
		</tr>
	<c:forEach items="${custList}" var="cust">
	<tr>
		 <td><c:out value="${cust.custId }" /></td> 
		 <td><c:out value="${cust.custName }" /></td> 
		 <td><c:out value="${cust.custAddr }" /> </td>
		 <td><c:out value="${cust.custEmail }" /> </td>
		 <c:url value="/customer.html?custId=${cust.custId }" var="d_url"/>
		 <c:url value="/custedit.html?custId=${cust.custId }" var="e_url"/>
		 <td><a href="${d_url }">상세</a><a href="${e_url }">편집</a></td>
	</tr>
	</c:forEach>
	</table>
		<span id="jsonIn">JSON형식으로 송신해서 HTML로 수신...</span><br/>
		<span id="xmlIn">xml형식으로 송신해서 HTML로 수신...</span><br/>
		<span id="xmlIn2">xml2 형식으로 송신해서 HTML로 수신...</span><br/>
		
		
	<form name="form1"  action="${url}" method="POST">
	<table>
		<tr><td>이름</td><td> <input type="text" name="custName"/></tr>
		<tr><td>주소</td><td><input type="text" name="custAddr"/></tr>
		<tr><td>이메일</td><td><input type="text" name="custEmail"/></tr>
		<tr><td><input type="submit" value="입력"/></td><tr/>
	</table>
	</form>
	
</body>
</html>