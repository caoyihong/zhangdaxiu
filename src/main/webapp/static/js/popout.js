/************************后台管理专用*************************/
/******************************************************
**popout.js
**descript：用于弹出一个提示窗口
**history：2015-03-24 新增callback，用于响应用户的选择
*************************************************/
var warn=[];
warn["warning"]="/showimg?url=images/icon-images/warning.jpg";
warn["success"]="/showimg?url=images/icon-images/success.png";
warn["fail"]="/showimg?url=images/icon-images/fail.png";
var $msgbox=$("#msgbox").hide();
var $mask=$("#mask").hide();
$msgbox.find(".closed").click(function(){
	$msgbox.hide();
	$mask.hide();
	return false;
});
function msgbox(title,warning,content,button,callback){
	var func=this;//记录作用域
	$msgbox.find(".title").html(title || "title");
	$msgbox.find(".warning").attr("src",warn[warning || "warning"] );
	$msgbox.find(".content").html(content || "content");

	//动态添加按钮，允许的传入值是：YESORNO、YES、CANCEL
	//如果需要根据用户点击的按钮不同，作出不同的响应，则需要传入一个callback，否则传入一个空函数
	var len=button.length;
	$msgbox.find('.button').remove();
	for (var i=0;i<len;i++){
		$msgbox.append('<a href class="button" value="' + button[i].value + '">' + button[i].name + '</a>');
	}

	$msgbox.show();
	$mask.show();

	//响应click
	$msgbox.unbind();
	$msgbox.click(function(e){
		if($(e.target).attr("class")=="button"){
			callback.call(func,$(e.target).attr("value"));
			$msgbox.hide();
			$mask.hide();
		}
		return false;
	});
}