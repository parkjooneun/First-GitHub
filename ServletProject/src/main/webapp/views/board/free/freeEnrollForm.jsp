<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../../common/head.jsp" %>
</head>
<body>
	<%@ include file="../../common/header.jsp" %>
	<%@ include file="../../common/nav.jsp" %>

 <section id="post-form">
   <h2>새 글 작성</h2>
   <form action="/freeEnroll.do" method="POST" enctype="multipart/form-data">                    
       <label for="title">제목:</label>
       <input type="text" id="title" name="title" required>
       
       <label for="content">내용:</label>
       <textarea id="content" name="content" rows="4" required></textarea>
       
       <!-- 파일 업로드 필드 추가 -->
       <input type="file" id="file" name="file">
       <br><br>
       
       <button type="submit">작성</button>
   </form>
</section>

	<%@ include file="../../common/footer.jsp" %>
</body>
</html>