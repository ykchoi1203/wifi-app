<%@page import="main.java.dto.BookmarkGroupDto"%>
<%@page import="java.util.List"%>
<%@page import="main.java.dao.BookmarkGroupDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>북마크바</title>
<script src="../js/bookmarkGroup.js"></script>
</head>
<body>
	<div>
		<form method ='POST' id="bookmarkForm" action='insert-bookmark.jsp' onsubmit="return validateForm()">
			<select name='bookmarkGroup' id='bookmarkGroup'>
				<option value='0'>북마크 그룹 이름</option>
				<% 
				BookmarkGroupDao bgDao = new BookmarkGroupDao();
				List<BookmarkGroupDto> list = bgDao.selectBookmark();
				for(int i=0; i<list.size(); i++) {
				
				%> <option value='<%= list.get(i).getId()%>'><%= list.get(i).getName() %></option>
				<%} %>
			</select>
			<input type="hidden" name='mgrNo' id='mgrNo' value='<%= request.getParameter("mgrNo")%>'>
			<input type="submit" value="북마크 추가하기">
		</form>
	</div>
</body>
</html>