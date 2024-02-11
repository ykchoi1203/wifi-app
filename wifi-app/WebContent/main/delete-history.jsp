<%@page import="main.java.dao.HistoryDao"%>
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
		String historyId = request.getParameter("id");
		HistoryDao historyDao = new HistoryDao();
		historyDao.deleteHistory(historyId);
		response.sendRedirect("history-list.jsp");
	%>
</body>
</html>