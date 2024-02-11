<%@page import="main.java.dto.WifiDto"%>
<%@page import="main.java.dao.WifiDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>와이파이 세부사항</title>
	<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<jsp:include page="bookmark-bar.jsp" />
	<div>
		<table>
			<% if(request.getParameter("mgrNo") != null) {
                WifiDao wifiDao = new WifiDao();
                WifiDto wifi = wifiDao.selectDetailWifi(request.getParameter("mgrNo"));
           	%>
			<tr>
				<th class='rotate-th'>거리(km)</th>
				<td class='oddtd'>0.0000</td>
			</tr>
			<tr>
				<th class='rotate-th'>관리번호</th>
				<td class='eventd'><%= wifi.getX_SWIFI_MGR_NO().length() != 0 ?  wifi.getX_SWIFI_MGR_NO() : "-" %> </td>
			</tr>
			<tr>
				<th class='rotate-th'>자치구</th>
				<td class='oddtd'><%= wifi.getX_SWIFI_MGR_NO().length() != 0 ? wifi.getX_SWIFI_WRDOFC() : "-" %> </td>
			</tr>
			<tr>
				<th class='rotate-th'>와이파이명</th>
				<td class='eventd'><a href='detail-wifi.jsp?mgrNo=<%= wifi.getX_SWIFI_MGR_NO() %>'><%= wifi.getX_SWIFI_MAIN_NM().length() != 0 ? wifi.getX_SWIFI_MAIN_NM() : "-" %></a> </td>
			</tr>
			<tr>
				<th class='rotate-th'>도로명주소</th>
				<td class='oddtd'><%= wifi.getX_SWIFI_ADRES1().length() != 0 ? wifi.getX_SWIFI_ADRES1() : "-" %> </td>
			</tr>
			<tr>
				<th class='rotate-th'>상세주소</th>
				 <td class='eventd'><%= wifi.getX_SWIFI_ADRES2().length() != 0 ? wifi.getX_SWIFI_ADRES2() : "-" %> </td>
			</tr>
			<tr>
				<th class='rotate-th'>설치위치(홈)</th>
				 <td class='oddtd'><%= wifi.getX_SWIFI_INSTL_FLOOR().length() != 0 ? wifi.getX_SWIFI_INSTL_FLOOR(): "-" %> </td>
			</tr>
			<tr>
				<th class='rotate-th'>설치유형</th>
				<td class='eventd'><%= wifi.getX_SWIFI_INSTL_TY().length() != 0 ? wifi.getX_SWIFI_INSTL_TY() : "-" %> </td>
			</tr>
			<tr>
				<th class='rotate-th'>설치기관</th>
				<td class='oddtd'><%= wifi.getX_SWIFI_INSTL_MBY().length() != 0 ? wifi.getX_SWIFI_INSTL_MBY() : "-" %> </td>
			</tr>
			<tr>
				<th class='rotate-th'>서비스구분</th>
				<td class='eventd'><%= wifi.getX_SWIFI_SVC_SE().length() != 0 ? wifi.getX_SWIFI_SVC_SE() : "-" %> </td>
			</tr>
			<tr>
				<th class='rotate-th'>망종류</th>
				<td class='oddtd'><%= wifi.getX_SWIFI_CMCWR().length() != 0 ? wifi.getX_SWIFI_CMCWR() : "-" %> </td>
			</tr>
			<tr>
				<th class='rotate-th'>설치년도</th>
				<td class='eventd'><%= wifi.getX_SWIFI_CNSTC_YEAR().length() != 0 ? wifi.getX_SWIFI_CNSTC_YEAR() : "-" %> </td>
			</tr>
			<tr>
				<th class='rotate-th'>실내외구분</th>
				<td class='oddtd'><%= wifi.getX_SWIFI_INOUT_DOOR().length() != 0 ? wifi.getX_SWIFI_INOUT_DOOR() : "-" %> </td>
			</tr>
			<tr>
				<th class='rotate-th'>WiFi접속환경</th>
				<td class='eventd'><%= wifi.getX_SWIFI_REMARS3().length() != 0 ? wifi.getX_SWIFI_REMARS3() : "-" %> </td>
			</tr>
			<tr>
				<th class='rotate-th'>X좌표</th>
				<td class='oddtd'><%= wifi.getLAT().length() != 0 ? wifi.getLAT() : "-" %> </td>
			</tr>
			<tr>
				<th class='rotate-th'>Y좌표</th>
				<td class='eventd'><%= wifi.getLNT().length() != 0 ? wifi.getLNT() : "-" %> </td>
			</tr>
			<tr>
				<th class='rotate-th'>작업일자</th>
				<td class='oddtd'><%= wifi.getWORK_DTTM().length() != 0 ? wifi.getWORK_DTTM() : "-"%> </td>
			</tr>
			<%} %>
		</table>
	</div>
</body>
</html>