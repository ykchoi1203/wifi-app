<%@page import="main.java.dao.BookmarkGroupDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String bookmarkGroupName = request.getParameter("bookmarkName");
		String orderNo = request.getParameter("orderNo");
		String orderNoChange = request.getParameter("orderNo_change");
		String bookmarkGroupId = request.getParameter("bookmarkGroupId");
		BookmarkGroupDao bmgDao = new BookmarkGroupDao();
		
		int result = bmgDao.updateBookMarkGroup(bookmarkGroupId, bookmarkGroupName, orderNoChange, orderNo);
		if(result > 0) {
			response.sendRedirect("bookmark-group-list.jsp");
		} else {
			out.print("<script>alert('수정에 실패했습니다. 전페이지로 돌아갑니다.'); history.back();</script>");
		}
	%>
</body>
</html>