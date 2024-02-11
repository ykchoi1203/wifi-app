<%@page import="main.java.dao.BookmarkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% if(request.getParameter("bookmarkId") != null ) {
         BookmarkDao bmDao = new BookmarkDao();
         int result = bmDao.deleteBookMark(request.getParameter("bookmarkId"));
         if(result > 0) {
 			response.sendRedirect("bookmark-list.jsp");
 		} else {
 			 out.print("<script>alert('삭제에 실패했습니다. 전페이지로 돌아갑니다.'); history.back();</script>");
 		}
	}
   %>
</body>
</html>