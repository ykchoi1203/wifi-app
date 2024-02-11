<%@page import="main.java.dao.BookmarkGroupDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>북마크 그룹 삭제</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String bookmarkGroupId = request.getParameter("bookmarkGroupId");
		BookmarkGroupDao bmgDao = new BookmarkGroupDao();
		
		int result = bmgDao.deleteBookMarkGroup(bookmarkGroupId);
		if(result > 0) {
			response.sendRedirect("bookmark-group-list.jsp");
		} else {
			System.out.println(result);
			 out.print("<script>alert('삭제에 실패했습니다. 전페이지로 돌아갑니다.'); history.back();</script>");
		}
	%>
</body>
</html>