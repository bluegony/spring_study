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
	<p>
	filename = ${filename}<br/>
	size = ${size}<br/>
	contentType = ${contentType}<br/>
	</p> 
	

			<c:forEach items="${uploadComponents.uploadItems}" var="item" varStatus="status">
			<dd>
				<input type="file" name="uploadItems[${status.index}].file"  />
				<input type="text" name="uploadItems[${status.index}].name"  />
			</dd>
			</c:forEach>
			
</body>
</html>