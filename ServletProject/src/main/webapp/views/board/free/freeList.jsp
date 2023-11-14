<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	<%@ include file="../../common/head.jsp" %>
	<script src="/resources/js/board/board.js"></script>
</head>
<body>
	<%@ include file="../../common/header.jsp" %>
	<%@ include file="../../common/nav.jsp" %>

    <section>
        <div class="table-container">
        <form action="/freeList.do" method="GET">
        	<input type="hidden" name="cpage" value="1">
        	<input type="search" name="searchText" placeholder="제목을 입력해주세요.">
        	<button type="submit">검색</button>
        </form>
          <table class="table table-hover" id="content">
          <thead>
            <tr>
              <th>글번호</th>
              <th>제목</th>
              <th>작성자</th>
              <th>작성일</th>
              <th>조회수</th>
            </tr>
          </thead>
          <tbody class="table-group-divider" id="table-content">

			<c:choose>
				<c:when test="${empty list}">
					<tr>
						<td colspan="5" class="text-center">등록된 글이 없습니다.</td>
					</tr>
				</c:when>
				
				<c:otherwise>
					<c:forEach var="item" items="${list}">
						<tr onclick="detailPage(${item.idx})">
							<td>${row}</td>
							<td>${item.title}</td>
							<td>${item.writer}</td>
							<td>${item.inDate}</td>
							<td>${item.views}</td>
						</tr>
						<c:set var="row" value="${row-1 }" />
					</c:forEach>
				</c:otherwise>
			</c:choose>
			
          </tbody>
        </table>
        <nav aria-label="Page navigation example">
        
        
  <ul class="pagination">
  
  
  	<c:choose>
  		<c:when test="${pi.currentPage == 1}">
		    <li class="page-item">
		      <a class="page-link" href="#" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
	    </c:when>
	    
	    <c:otherwise>
  		    <li class="page-item">
		      <a class="page-link" href="/freeList.do?cpage=${pi.currentPage-1}&searchText=${searchText}" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
	    </c:otherwise>
    </c:choose>
    
    
    <c:forEach var="page" begin="${pi.startPage}" end="${pi.endPage}">
	    <li class="page-item"><a class="page-link" href="/freeList.do?cpage=${page}&searchText=${searchText}">${page}</a></li>
    </c:forEach>
    
    
    <c:choose>
    	<c:when test="${pi.currentPage == pi.maxPage}">
		    <li class="page-item">
		      <a class="page-link" href="#" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
	    </c:when>
	    
	    <c:otherwise>
  		    <li class="page-item">
		      <a class="page-link" href="/freeList.do?cpage=${pi.currentPage+1}&searchText=${searchText}" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
	    </c:otherwise>
    </c:choose>
    
    
    
			  </ul>
			</nav>
		<c:if test="${!empty sessionScope.name}">
          <button class="add-button" onclick="window.location.href = '/form/freeEnrollForm.do'">등록</button>
      	</c:if>
      </div>
</section>

	<%@ include file="../../common/footer.jsp" %>
</body>
</html>