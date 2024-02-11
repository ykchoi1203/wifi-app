<%@page import="main.java.dto.BookmarkDto"%>
<%@page import="java.util.List"%>
<%@page import="main.java.dao.BookmarkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>북마크 보기</title>
	<script src="../js/bookmark.js"></script>
	<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="section left">
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>북마크 이름</th>
					<th>와이파이명</th>
					<th>등록일자</th>
					<th>비고</th>
				</tr>
			</thead>
			<tbody>
				<%
				BookmarkDao bmDao = new BookmarkDao();
				List<BookmarkDto> list = bmDao.selectBookmarkList();
				if (list.size() != 0) {
					for (int i = 0; i < list.size(); i++) {
				%>
				<tr>
					<td><%=list.get(i).getId()%></td>
					<td><%=list.get(i).getBookmarkName()%></td>
					<td><%=list.get(i).getWifiName()%></td>
					<td><%=list.get(i).getMakeDate()%></td>
					<td><a href='#' onclick="deleteBookMark('<%=list.get(i).getId()%>');">삭제</a></td>
				</tr>
				<%
					}
				} else {
				%>
				<tr>
					<td id="no-bookmark-List" colspan="17">정보가 존재하지 않습니다.</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>