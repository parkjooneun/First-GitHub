<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../common/head.jsp" %>
	<script src="/resources/js/member/register.js"></script>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<%@ include file="../common/nav.jsp" %>

  <section>
    <div class="signup-form">
        <h2>회원가입</h2>
        <form action="/register.do" method="POST">
          <div class="input-container2">
        </div>
        <div class="input-container2">
          <label for="new-username">이름:</label>
          <input type="text" id="new-username" name="new-username" onkeyup="validateName()" required>
          <span id="nameMsg"></span>
        </div>
        <br>
        <div class="input-container2">
          <label for="new-userid">아이디:</label>
          <input type="text" id="new-userid" name="new-userid" onkeyup="duplicateId();" required>
          <span id="idCheck"></span>
        </div>
        <br>
        <div class="input-container2">
          <label for="new-password">비밀번호:</label>
          <input type="password" id="new-password" name="new-password" onkeyup="validatePassword()" required>
          <span id="passwordMsg"></span>
          </div>
          <br>
          <div class="input-container2">
            <label for="confirm-password">비밀번호 확인:</label>
            <input type="password" id="confirm-password" name="confirm-password" onkeyup="validatePassword()" required>
	        <span id="passwordChkMsg"></span>
          </div>
  		<br>
          <button type="submit">회원가입</button>
        </form>
      </div>
  </section>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>

<script>
	// 아이디 중복확인
	function duplicateId() {
		const id = document.getElementById("new-userid").value;
		const idCheck = document.getElementById("idCheck");
		$.ajax({
			type: "POST",
			url: "/duplicateId.do",
			data: { id : id },
			success : function(res) {
				console.log(res);
				if(res === 'true') { // 중복(true) : 빨간색
					idCheck.style.color = "red";
					idCheck.innerHTML = "중복된 아이디입니다.";
				} else { // 사용가능(false) : 초록색
					idCheck.style.color = "green";
					idCheck.innerHTML = "사용 가능한 아이디입니다.";               
				}
			},
			error : function(response) {
			}
		})
	}
	
	// 이름 유효성 검사
    function validateName() {   
       const nameRegex = /^[가-힣]{2,}$/;
       const name = document.getElementById("new-username").value;
       const nameMsg = document.getElementById("nameMsg");

       if(name == "") {
    	   nameMsg.innerHTML = "이름을 입력해주세요.";
    	   nameMsg.style.color = "red";
       } else if(nameRegex.test(name)) {
    	   nameMsg.innerHTML = "사용 가능한 이름입니다.";
    	   nameMsg.style.color = "green";
       } else {
    	   nameMsg.innerHTML = "이름은 한글만 가능합니다.";
    	   nameMsg.style.color = "red";
       }
   }
	
	// 비밀번호 유효성 검사 + 비밀번호 확인하고 같은지
    function validatePassword() {
       const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[@$!%*?&\#])[A-Za-z\d@$!%*?&\#]{6,20}$/;
       const password = document.getElementById("new-password").value;
       const msg = document.getElementById("passwordMsg");
       
       if(password == "") {
           msg.innerHTML = "비밀번호를 입력하세요.";
           msg.style.color = "red";
       } else if(passwordRegex.test(password)) {
           msg.innerHTML = "사용 가능한 비밀번호 입니다.";
           msg.style.color = "green";
       } else {
           msg.innerHTML = "패스워드 정책에 맞지 않습니다.";
           msg.style.color = "red";
       }

       const passwordChk = document.getElementById("confirm-password").value;
       const msg2 = document.getElementById("passwordChkMsg");

       if(password === passwordChk) {
           msg2.innerHTML = "패스워드가 동일합니다.";
           msg2.style.color = "green";
       } else {
           msg2.innerHTML = "패스워드가 동일하지 않습니다..";
           msg2.style.color = "red";
       }
   }
</script>






















