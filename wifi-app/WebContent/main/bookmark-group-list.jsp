<%@page import="main.java.dto.BookmarkGroupDto"%>
<%@page import="java.util.List"%>
<%@page import="main.java.dao.BookmarkGroupDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>북마크 그룹 관리</title>
	<link rel="stylesheet" href="../css/style.css">
	<script src="../js/bookmarkGroup.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div>
		<input type="button" onclick="location.href='add-bookmark-group.jsp'"
			value="븍마크 그룹 이름 추가">
	</div>

	<div class="section left">
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>북마크 이름</th>
					<th>순서</th>
					<th>등록일자</th>
					<th>수정일자</th>
					<th>비고</th>
				</tr>
			</thead>
			<tbody>
				<%  
	               BookmarkGroupDao bmgDao = new BookmarkGroupDao();
                   List<BookmarkGroupDto> list = bmgDao.selectBookmark();
                   if(list.size() != 0) {
                   for(int i=0; i<list.size(); i++) { %>
				<tr>
					<td><%= list.get(i).getId() %></td>
					<td><%= list.get(i).getName() %></td>
					<td><%= list.get(i).getOrderNo() %></td>
					<td><%= list.get(i).getMakeDate() %></td>
					<td><%= list.get(i).getUpdateDate() == null ? " " : list.get(i).getUpdateDate() %></td>
					<td><a
						href='update-bookmark-group-view.jsp?id=<%=list.get(i).getId() %>&bookmarkName=<%=list.get(i).getName() %>&orderNo=<%=list.get(i).getOrderNo() %>'>수정</a>
						<a href='#' onclick='deleteBookMark(<%=list.get(i).getId()%>);'>삭제</a></td>

				</tr>
				<% }
	               } else { %>
				<tr>
					<td id="no-bookmark-group-List" colspan="17">정보가 존재하지 않습니다.</td>
				</tr>
				<% } %>
			</tbody>
		</table>
	</div>
</body>
</html>