  function nameCheck() {
    const nameRegex = /^[가-힣]{2,}$/;
    const name = document.getElementById("new-username").value;
    const msg = document.getElementById("nameMsg");

    if(nameRegex.test(name)) {
      msg.innerHTML = "사용 가능한 이름입니다.";
      msg.style.color = "green";
    } else {
      msg.innerHTML = "사용 안됨";
      msg.style.color = "red";
    }
  }