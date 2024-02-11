<%@page import="main.java.util.ApiExplorer"%>
<%@page import="main.java.dao.WifiDao"%>
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
		WifiDao wifiDao = new WifiDao();
		ApiExplorer api = new ApiExplorer();
		int count = wifiDao.wifiInsert(api.getWifi());
	%>
	<p><%=count %>개의 정보를 저장했습니다.</p>
	
	<a href="index.jsp">홈으로 돌아가기</a>
</body>
</html>