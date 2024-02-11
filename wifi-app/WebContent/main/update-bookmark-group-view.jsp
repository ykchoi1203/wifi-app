<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>북마크 그룹 수정</title>
	<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<form method="post" action="update-bookmark-group.jsp">
		<table>
		<tr>
			<th class='small-rotate-th'>북마크 이름</th>
			<td class='oddtd'><input type="text" name="bookmarkName" id="bookmarkName" value='<%= request.getParameter("bookmarkName") %>' size="15"></td>
		</tr>
		
		<tr>
			<th class='small-rotate-th'>순서</th>
			<td class='eventd'><input type="text" name="orderNo_change" id="orderNo_change" value='<%= request.getParameter("orderNo") %>' size="15"></td>
		</tr>
		<tr>
			<td colspan='2'><a href='#' onClick="history.back()">돌아가기</a> |
			<input type='submit'  value='수정'><td>
		</tr>
		</table>
		<div>
			<input type="hidden" name="bookmarkGroupId" id="bookmarkGroupId" value = '<%= request.getParameter("id") %>' >
			<input type="hidden" name="orderNo" id="orderNo" value = '<%= request.getParameter("orderNo") %>' >
			
		</div>
	</form>
</body>
</html>