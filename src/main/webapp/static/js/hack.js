$(function(){
	if(judgeBrowser()=="firefox"){
		$(".search-result-content").css({"border":"1px solid rgb(204, 204, 204)","outline":"none"});
	}
});


/*浏览器判断*/
function judgeBrowser(){
	var bro = "";
	var isChrome=navigator.userAgent.toUpperCase().indexOf("CHROME")!=-1?true:false; //谷歌
	var isFireFox = navigator.userAgent.toUpperCase().indexOf("FIREFOX")!=-1?true:false;//火狐
	var isIE = okIE(); //ie
	if(isChrome)
		bro="chrome";
	if(isFireFox){
		bro="firefox";
	}
	if(isIE){
		bro="ie";
	}
	return bro;
}
function okIE(){ //ie?  
    if (!!window.ActiveXObject || "ActiveXObject" in window)  
        return true;  
    else  
        return false;  
}