function form_submit(){
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var errordiv = document.getElementById("error");

	if(username.length == 0) {
		errordiv.innerHTML = "请输入用户名";
		return false;
	}
	if(password.length == 0) {
		errordiv.innerHTML = "请输入密码";
		return false;
	}
	
	document.getElementById("login").submit();
}
function form_reset(){
	document.getElementById("login").reset();
}
function reloadcode(img){
    img.setAttribute('src','images/checkcode.jpg?'+Math.random());
}

function deleteArt(tid) {
	var res = confirm("确定删除吗?");
	if(res == true) {
		window.location.href ="/cms/backend/DeleteArticleServlet?tid=" + tid;
	}else {
	}
}

function deleteArts() {
	var res = confirm("确定删除吗?");
	if(res == true) {
		var subBoxes = document.getElementsByName("checkbox2");
		var url = "/cms/backend/DeleteArticleServlet?";
		for (var int = 0; int < subBoxes.length; int++) {
			if(subBoxes[int].checked) {
				url = url + "tid="+ subBoxes[int].value + "&";
			}
		}
		url = url.substring(0, url.length-1);
		window.location.href = url;
	}else {
	}
}

function deleteChannel(cid) {
	var res = confirm("确定删除吗?");
	if(res == true) {
		window.location.href ="/cms/backend/DeleteChannelServlet?cid=" + cid;
	}else {
	}
}

function deleteChannels() {
	console.log("delete");
	var res = confirm("确定删除吗?");
	if(res == true) {
		var subBoxes = document.getElementsByName("checkbox2");
		var url = "/cms/backend/DeleteChannelServlet?";
		for (var int = 0; int < subBoxes.length; int++) {
			if(subBoxes[int].checked) {
				url = url + "cid="+ subBoxes[int].value + "&";
			}
		}
		url = url.substring(0, url.length-1);
		window.location.href = url;
	}else {
	}
}

function selectAll(box) {
	var subBoxes = document.getElementsByName("checkbox2");
	for (var int = 0; int < subBoxes.length; int++) {
		subBoxes[int].checked = box.checked;
	}
}

function pagesizeChanged(select) {
	var url = document.getElementById("firstPage").href + "&pagesize=" + select.value;
	window.location = url;
}