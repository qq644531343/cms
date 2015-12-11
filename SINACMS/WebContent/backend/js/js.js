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
