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
<c:url value="/chat/polling.html" var="con_url" />
<c:url value="/chat/push.html" var="push_url" />
<script type="text/javascript">
$(function(){
	$("#enterRoom").click(connect);
	
	$("#postMessage").click(push);
	
});

function connect() {
	$.ajax({
		type:"GET",
		url:"${con_url}",
		cache:false,
		dataType:"json",
		complete:function(){
			connect();
		},
		success:function(data){
			if(data.message ==undefined || data.message == ""){
				return;
			}
			var text = data.name+" : "+data.message;
			text = $("<div>").append(text);
			$("#result").append(text)
		}
	})	
}
function push() {
	$.ajax({
		type:"POST",
		url:"${push_url}",
		cache:false,
		dataType:"json",
		data:{"name":$("#name").val(),"message":$("#message").val()},
		complete:function(){
		},
		success:function(data){
			if(data.message ==undefined || data.message == ""){
				return;
			}
			var text = data.name+" : "+data.message;
			text = $("<div>").append(text);
			$("#result").append(text)
		}
	})	
}
</script>
</head>
<body>

	<h1>
		<c:out value="${msg }" />
	</h1>

	<button id="enterRoom">입장</button>
	<span> 이름:</span>
	<input type="text" id="name" />
	<input type="text" id="message" />
	<button id="postMessage">send</button>
	<div id="result"> </div>
	
</body>
</html>