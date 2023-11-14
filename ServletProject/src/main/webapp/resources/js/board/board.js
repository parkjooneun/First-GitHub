function detailPage(idx) {
	const pageUrl = '/freeDetail.do?idx=' + idx;
	window.location.href = pageUrl;
}

function boardUpdate() {
	const form = document.getElementById("detail-form");
	
	form.action = "/boardUpdate.do";
	form.method = "post";
	form.submit();
}

function boardDelete() {
	const form = document.getElementById("detail-form");
	
	form.action = "/boardDelete.do";
	form.method = "post";
	form.submit();
}





