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

    <section>
        <div class="table-container">
          <table class="table table-hover" id="content">
          <thead>
            <tr>
              <th>ì ëª©</th>
              <th>ê¸ì´ì´</th>
              <th>ë´ì©</th>
            </tr>
          </thead>
          <tbody class="table-group-divider" id="table-content">

          </tbody>
        </table>
            <button class="add-button" onclick="window.location.href = 'enrollForm.html'">ë±ë¡</button>
        </div>
  </section>

	<%@ include file="../common/footer.jsp" %>
</body>
</html>