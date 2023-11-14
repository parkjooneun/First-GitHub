<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../../common/head.jsp" %>
	<script src="/resources/js/board/board.js"></script>
</head>
<body>
	<%@ include file="../../common/header.jsp" %>
	<%@ include file="../../common/nav.jsp" %>

 <section id="post-form">
   <h2>상세보기</h2>
   
   
   <form id="detail-form">
  	   <input type="hidden" name="idx" value="${board.idx}" />
   
       <label for="title">제목:</label>
       <input type="text" id="title" name="title" value="${board.title}" required ${sessionScope.name eq board.writer ? '' : 'readonly'}>                           
       
        <label for="content">작성자:</label>
       <input type="text" id="writer" name="writer" value="${board.writer}" required disabled>
       
        <label for="content">조회수:</label>
       <input type="text" id="views" name="views" value="${board.views }" required disabled>

        <label for="content">작성일:</label>
       <input type="text" id="in-date" name="in-date" value="${board.inDate}" required disabled>
       
       <label for="content">내용:</label>
       <textarea id="content"  name="content" rows="4" required ${sessionScope.name eq board.writer ? '' : 'readonly'}>${board.content }</textarea>
       <img src="/resources/uploads/${board.fileName}">
       <!--      /resources/uploads/다운로드.jpg -->
       
       <!-- 글작성자 수정/삭제 가능 -->
       <c:if test="${sessionScope.name == board.writer}">
	       <button type="button" onclick="boardUpdate()">수정</button>
	       <button type="button" onclick="boardDelete()">삭제</button>
       </c:if>
   </form>
</section>

	<%@ include file="../../common/footer.jsp" %>
</body>
</html>