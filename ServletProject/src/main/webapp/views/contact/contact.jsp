<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../common/head.jsp" %>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<%@ include file="../common/nav.jsp" %>

  <section id="contact">
    <h2>ì°ë½íê¸°</h2>
    <p>ë¬¸ìì¬í­ì´ë ì°ë½íê³  ì¶ì ë´ì©ì ìì±íì¸ì.</p>
    <form >
        <label for="name">ì´ë¦:</label>
        <input type="text" id="name" name="name" required>
        
        <label for="email">ì´ë©ì¼:</label>
        <input type="email" id="email" name="email" required>
        
        <label for="message">ë©ìì§:</label>
        <textarea id="message" name="message" rows="4" required></textarea>
        
        <button type="submit">ì ì¡</button>
    </form>
  </section>

	<%@ include file="../common/footer.jsp" %>
</body>
</html>