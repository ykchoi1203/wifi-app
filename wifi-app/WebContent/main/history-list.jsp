<%@page import="main.java.dto.HistoryDto"%>
<%@page import="java.util.List"%>
<%@page import="main.java.dao.HistoryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>위치 히스토리 목록</title>
	<script src="../js/wifi.js"></script>
	<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp" />
		<div class="section left">
			<table class='table'>
				<thead>
					<tr>
						<th>ID</th>
						<th>X좌표</th>
						<th>Y좌표</th>
						<th>조회일자</th>
						<th>비고</th>
					</tr>
				</thead>
				<tbody>
					<%
					HistoryDao historyDao = new HistoryDao();
					List<HistoryDto> list = historyDao.getHistory();
					for(HistoryDto history : list) {%>
					<tr>
						<td><%=history.getId() %></td>
						<td><%=history.getLat() %></td>
						<td><%=history.getLnt() %></td>
						<td><%=history.getWorkDate() %></td>
						<td>
							<form id="deleteForm_<%=history.getId() %>" action="delete-history.jsp" method="get">
								<input type="hidden" name="id" value="<%=history.getId() %>">
								<button type="button" onclick="confirmDelete('<%=history.getId() %>')">삭제</button>
							</form>
						</td>
					</tr>
					<% }
					if(list.size() == 0) { 
					%>
					<tr>
						<td id="no-location" colspan="17">위치 히스토리 목록이 없습니다. 조회 후 확인하세요.</td>
					</tr>
					<% } %>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>