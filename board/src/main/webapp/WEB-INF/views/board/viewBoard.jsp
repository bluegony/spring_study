<%@ include file="header.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script language="javascript">
	function saveMemo() {
		if (document.memoForm.content.value == '') {
			alert('메모 내용을 입력해 주세요');
			document.memoForm.content.focus();
			return;
		}
		document.memoForm.action = '<c:url value="/memo/insertMemo.html"/>';
		document.memoForm.submit();
	}
	function deleteMemo(memoId) {
		document.memoForm.memoId.value = memoId;
		document.memoForm.action = '<c:url value="/memo/deleteMemo.html"/>';
		document.memoForm.submit();
	}
</script>

<table class="table_default" border="0" cellpadding="3" cellspacing="1" width="100%" align="center" bgcolor="#949EA5">
<tr height="30"> 
	<td bgcolor="#7B869A" align="right" width="20%"><font color="#FFFFFF"><b>제 목</b></font></td>
	<td bgcolor="#FFFFFF" colspan="3"><c:out value="${board.title}"/></td>
</tr>
<tr height="30"> 
	<td bgcolor="#7B869A" align="right" width="20%"><font color="#FFFFFF"><b>작성일</b></font></td>
	<td bgcolor="#FFFFFF" width="30%"><fmt:formatDate value="${board.insertDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<td bgcolor="#7B869A" align="right" width="20%"><font color="#FFFFFF"><b>작성자</b></font></td>
	<td bgcolor="#FFFFFF" width="30%"><c:out value="${board.userid}"/></td>
</tr>
<tr height="30"> 
	<td bgcolor="#7B869A" align="right" width="20%"><font color="#FFFFFF"><b>조회수</b></font></td>
	<td bgcolor="#FFFFFF" width="30%"><c:out value="${board.visited}"/></td>
	<td bgcolor="#7B869A" align="right" width="20%"><font color="#FFFFFF"><b>추천수</b></font></td>
	<td bgcolor="#FFFFFF" width="30%"><c:out value="${board.recom}"/></td>
</tr>
<tr> 
	<td bgcolor="#7B869A" align="right"><font color="#FFFFFF"><b>내 용</b></font></td>
	<td bgcolor="#FFFFFF"  colspan="3"><c:out value="${board.content}" escapeXml="false"/></td>
</tr>
<tr height="30"> 
	<td bgcolor="#FFFFFF" colspan="4" align="center">
		[ <a href="<c:url value="/board/listBoard.html"/>">리스트</a> ]
		[ <a href="<c:url value="/board/recomBoard.html?boardId=${board.boardId}"/>">글추천</a> ]
		<c:if test="${userSession.user.userid == board.userid}">
		[ <a href="<c:url value="/board/deleteBoard.html?boardId=${board.boardId}"/>">글삭제</a> ]
		</c:if>
		<c:if test="${userSession.user.userid != board.userid}">
		[ <a href="<c:url value="/board/deleteBoard.html?boardId=${board.boardId}"/>">글삭제</a> ]
		</c:if>
		<c:if test="${userSession.user.userid == board.userid}">
		[ <a href="<c:url value="/board/editBoardForm.html?boardId=${board.boardId}"/>">글수정</a> ]
		</c:if>
		<c:if test="${userSession.user.userid != board.userid}">
		[ <a href="<c:url value="/board/editBoardForm.html?boardId=${board.boardId}"/>">글수정</a> ]
		</c:if>
	</td>
</tr>
</table>

<br/>

<form name="memoForm">
<input type="hidden" name="boardId" value="<c:out value="${board.boardId}"/>">
<input type="hidden" name="memoId">
<table class="table_default" border="0" cellpadding="3" cellspacing="1" width="100%" align="center" bgcolor="#949EA5">
<tr height="30"> 
	<td bgcolor="#7B869A" width="100" align="center"><font color="#FFFFFF" align="center"><b>메모</b></font></td>
	<td bgcolor="#FFFFFF"><input type="text" name="content" size="80" maxlength="80"></td>
	<td bgcolor="#FFFFFF" align="right">[ <a href="<c:url value="javascript:saveMemo()"/>">저장</a> ]</td>
</tr>
<tr height="30">
	<td bgcolor="#FFFFFF" colspan="3">
		<table cellpadding="3" cellspacing="3" width="100%">
		<c:forEach var="memo" items="${memoList}">
		<tr height="30">
			<td class="list_content">
				<c:out value="${memo.userid}"/>(<fmt:formatDate value="${memo.insertDate}" pattern="yyyy-MM-dd HH:mm:ss"/>)
				... <c:out value="${memo.content}"/>
				<c:if test="${userSession.user.userid == memo.userid}">
				[ <a href="<c:url value="javascript:deleteMemo(${memo.memoId})"/>">삭제</a> ]
				</c:if>				
				<c:if test="${userSession.user.userid != memo.userid}">
				[ 삭제 ]
				</c:if>	
			</td>
		<tr>
		</c:forEach>
		</table>
	</td>
</tr>
</table>
</form>


<%@ include file="footer.jsp" %>