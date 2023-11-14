<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name = (String)session.getAttribute("name");
%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="./views/common/head.jsp" %>
</head>
<body>
	<%@ include file="./views/common/header.jsp" %>
	<%@ include file="./views/common/nav.jsp" %>
	
  <section>
    <h2>홈</h2>
    <p>${sessionScope.name} 안녕하세요! 홈페이지에 오신 것을 환영합니다.</p>
  </section>
  
	<%@ include file="./views/common/footer.jsp" %>
</body>
</html>