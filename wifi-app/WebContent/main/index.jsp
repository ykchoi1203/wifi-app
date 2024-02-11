<%@page import="main.java.dto.WifiDto"%>
<%@page import="java.util.List"%>
<%@page import="main.java.dao.HistoryDao"%>
<%@page import="main.java.dao.WifiDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>와이파이 정보 구하기</title>
	<script src="../js/wifi.js"></script>
	<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<div>
		<jsp:include page="header.jsp" />
		<jsp:include page="location-bar.jsp" />
		<div>
			<table>
				<thead>
					<tr>
						<th>거리(Km)</th>
						<th>관리번호</th>
						<th>자치구</th>
						<th>와이파이명</th>
						<th>도로명주소</th>
						<th>상세주소</th>
						<th>설치위치(홈)</th>
						<th>설치유형</th>
						<th>설치기관</th>
						<th>서비스구분</th>
						<th>망종류</th>
						<th>설치년도</th>
						<th>실내외구분</th>
						<th>WiFi접속환경</th>
						<th>X좌표</th>
						<th>Y좌표</th>
						<th>작업일자</th>
					</tr>
				</thead>
				<tbody>
					<% if(request.getParameter("LAT") != null && request.getParameter("LNT") != null) {
                    WifiDao wifiDao = new WifiDao();
                    HistoryDao historyDao = new HistoryDao();
                    historyDao.insertHistory(request.getParameter("LAT"),request.getParameter("LNT"));
                    List<WifiDto> list = wifiDao.selectNearWifi(request.getParameter("LAT"), request.getParameter("LNT"));
                    for(int i=0; i<list.size(); i++) { %>
					<tr>
						<td><%= list.get(i).getDistance() %></td>
						<td><%= list.get(i).getX_SWIFI_MGR_NO() %></td>
						<td><%= list.get(i).getX_SWIFI_WRDOFC() %></td>
						<td><a
							href='detail-wifi.jsp?mgrNo=<%= list.get(i).getX_SWIFI_MGR_NO() %>'><%= list.get(i).getX_SWIFI_MAIN_NM() %></a></td>
						<td><%= list.get(i).getX_SWIFI_ADRES1() %></td>
						<td><%= list.get(i).getX_SWIFI_ADRES2() %></td>
						<td><%= list.get(i).getX_SWIFI_INSTL_FLOOR() %></td>
						<td><%= list.get(i).getX_SWIFI_INSTL_TY() %></td>
						<td><%= list.get(i).getX_SWIFI_INSTL_MBY() %></td>
						<td><%= list.get(i).getX_SWIFI_SVC_SE() %></td>
						<td><%= list.get(i).getX_SWIFI_CMCWR() %></td>
						<td><%= list.get(i).getX_SWIFI_CNSTC_YEAR() %></td>
						<td><%= list.get(i).getX_SWIFI_INOUT_DOOR() %></td>
						<td><%= list.get(i).getX_SWIFI_REMARS3() %></td>
						<td><%= list.get(i).getLAT() %></td>
						<td><%= list.get(i).getLNT() %></td>
						<td><%= list.get(i).getWORK_DTTM() %></td>
					</tr>
					<% }
                } else { %>
					<tr>
						<td id="no-location" colspan="17">위치정보를 입력한 후에 조회해 주세요.</td>
					</tr>
					<% } %>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>