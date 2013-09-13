<%@ include file="header.jsp"%>
<%@ page contentType="text/html; charset=utf-8"%>

<script language="javascript">
	function searchBoard() {
		document.searchForm.action = '<c:url value="/board/searchBoard.html"/>';
		document.searchForm.submit();
	}
</script>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<form name="searchForm" method="POST">
		<tr class="list_menu">
			<td><select name="searchKey">
					<option value="subject"
						<c:if test="${searchKey == 'subject'}"><c:out value="selected"/></c:if>>제
						목</option>
					<option value="writer"
						<c:if test="${searchKey == 'writer'}"><c:out value="selected"/></c:if>>작성자</option>
			</select> <input type="text" name="searchVal"
				<c:if test="${!empty searchVal}">value="<c:out value="${searchVal}"/>"</c:if> />
				<input type="button" value="검색" onclick="searchBoard()" /> [총 글수 : <c:out
					value="${boardList.nrOfElements}" />] [페이지 : <c:out
					value="${boardList.page + 1}" />/<c:out
					value="${boardList.pageCount}" />]</td>
			<td align="right"><a
				href="<c:url value="/board/boardForm.html"/>">[글쓰기]</a></td>
		</tr>
	</form>
</table>

<table border="0" cellpadding="3" cellspacing="1" width="100%"
	align="center" bgcolor="#949EA5">
	<tr class="list_title">
		<td>번 호</td>
		<td>제 목</td>
		<td>작 성 자</td>
		<td>작 성 일</td>
		<td>조 회</td>
	</tr>
	<%--<c:forEach var="board" items="${list}"--%>
	<c:forEach var="board" items="${list.pageList}">
		<tr class="list_content">
			<td align="right"><c:out value="${board.boardId}" /></td>
			<td><c:if test="${!empty userSession.user}">
					<a
						href="<c:url value="/board/viewBoard.html?boardId=${board.boardId}"/>"><c:out
							value="${board.title}" /></a>
				</c:if> 
					<a
						href="<c:url value="/board/viewBoard.html?boardId=${board.boardId}"/>"><c:if test="${empty userSession.user}">
					<c:out value="${board.title}" />
				</c:if></td>
			<td align="center"><c:out value="${board.userid}" /></td>
			<td align="center"><fmt:formatDate value="${board.insertDate}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td align="center"><c:out value="${board.visited}" /></td>
		</tr>
	</c:forEach>
	<tr class="list_page">
		<td colspan="5">
			<%--<c:forEach var="i" begin="1" end="${pageSize}" step="1">

			<c:if test="${i == '${currentPage}'}" var="result">
					<c:out value="${currentPage} " escapeXml="false" />
				</c:if>
				<a href="/board/board/listBoard.html?page=${i}"> ${i} </a>
				
			</c:forEach></td>--%>
					<c:out value="${pageLink} " escapeXml="false" />
	</tr>
</table>

<%@ include file="footer.jsp"%>