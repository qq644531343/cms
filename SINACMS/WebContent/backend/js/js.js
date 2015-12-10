function form_submit(){
	document.getElementById("login").submit();
}
function form_reset(){
	document.getElementById("login").reset();
}
function reloadcode(img){
    img.setAttribute('src','images/checkcode.jpg?'+Math.random());
}