<%@page import="main.java.dao.BookmarkGroupDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>북마크 그룹 insert</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String bookmarkName = request.getParameter("bookmarkName");
		String orderNo = request.getParameter("orderNo");
		BookmarkGroupDao bmgDao = new BookmarkGroupDao();
		int result = bmgDao.insertBookMarkGroup(bookmarkName, orderNo);
		if(result > 0) {
			response.sendRedirect("bookmark-group-list.jsp");
		} else {
			 out.print("<script>alert('추가에 실패했습니다. 전페이지로 돌아갑니다.'); history.back();</script>");
		}
	%>
</body>
</html>