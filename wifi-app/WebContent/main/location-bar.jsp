<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/location.js"></script>
</head>
<body>
	<div class="section right">
		<form method="GET" action="index.jsp">
			LAT: <input type="text" name="LAT" id="LAT" value='0.0' size="15">,
			LNT: <input type="text" name="LNT" id="LNT" value='0.0' size="15">
			<input type="button" onclick="getUserLocation();" value="내 위치 가져오기">
			<input type="submit" value="근처 와이파이 정보 보기">
		</form>
	</div>
	<script>
	window.onload = function() {
	    var lat = '<%= request.getParameter("LAT") != null ? request.getParameter("LAT") : "0.0" %>';
	    var lng = '<%= request.getParameter("LNT") != null ? request.getParameter("LNT") : "0.0" %>';
	    
	    document.getElementById("LAT").value = lat;
	    document.getElementById("LNT").value = lng;
	};
	</script>
</body>

</html>