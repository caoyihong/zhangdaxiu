function switchRN(value){
	//将br标签换成换行符
	return value.replace(/<br>|<BR>/g,"\n");
}
function removeSpace(value){
	//去除空白
	return value.replace(/[ ]/g,"");
}
function formatText(value){
	//用回车替代br
	return removeSpace(switchRN(value));
}
function switchBr(value){
	return value.replace(/\n/g,"<br>");
}
function formatHTML(value){
	//用br代替回车
	return removeSpace(switchBr(value));
}
$(document).ready(function(){
	$(".perfect-your-aply").click(function(){
		if($(this).html()=="完善我的申请"){
			$(this).html("放弃修改");
			var strAply=formatText($(this).parents(".modi-request-exper-item-right").find(".request-exper-item-right-content").html());
			$(".modi-lawer-custom-replybox").slideDown();
			$("body,html").animate({scrollTop:parseInt($(this).next().offset().top)-100},1000);
			$(".modi-lawer-custom-replybox textarea").val(strAply);
			$(this).next().find("textarea").trigger("input").trigger("propertychange");
		}
		else{
			$(this).html("完善我的申请");
			$("body,html").animate({scrollTop:parseInt($(this).parents(".modi-request-exper-item-right").find(".request-exper-item-right-content").offset().top-100)},1000);
			$(".modi-lawer-custom-replybox").slideUp();
		}
		return false;
	});
	$("#changeApply").click(function(){
		//往回传参数，等获得反馈之后修改显示内容
		var strAply=$(".modi-lawer-custom-replybox textarea").val();
		var $htmlshow=$(this).parents(".modi-request-exper-item-right").find(".request-exper-item-right-content");
		var id=$htmlshow.attr("applyid");
		var htmlAply=formatHTML(strAply);
		var token = $("#formToken").val();
		$.ajax({
			url:"/work/updateApply",
			type:"post",
			data:{
				applyid:id,
				content:htmlAply,
				formToken: token
			},
			success:function(data){
				if (data != "error") {
					$htmlshow.html(htmlAply);
					$("body,html").animate({scrollTop:parseInt($htmlshow.offset().top-100)},1000);
					$(".modi-lawer-custom-replybox").slideUp();
					$(".perfect-your-aply").html("完善我的申请");
					//读取修改时间
					$htmlshow.next().html("<span>回复时间:</span><span>" + data + "</span>");
				}else {
					window.location.reload();
				}
			}
         		/*error:function(data){
				//强制页面刷新
				location.reload();
			},*/
		});
		return false;
	});
	if($(".selfreply").length!=0){
		$("#more").nextAll().hide();
	}
	$("#more").click(function(){
		if($(this).html()=="显示其他人的申请"){
			$(this).html("收起显示");
			$(this).nextAll().slideDown();
			$("body,html").animate({scrollTop:parseInt($(this).offset().top)},1000);
		}
		else{
			$(this).html("显示其他人的申请");
			$(this).nextAll().slideUp();
			$("body,html").animate({scrollTop:parseInt($(".selfreply").offset().top)-100},1000);
		}
	});
	$(".modi-lawer-custom-replybox-top textarea").bind("input propertychange",function(){
		var len=$(this).val().length;
		$(this).parent().next().find(".words-left").html('您还可以输入<span>' + (1000-parseInt(len)) + '</span>个文字.');
		//修改高度
		$(this).height('0px');
		var height=this.scrollHeight + 'px';
		$(this).css("height",height);
		$(this).parent().css("height",height);
	});
	//为用户评论做文字裁剪以及添加按钮
	//判断文字有没有裁剪，如果裁剪，则添加一个按钮用于展开
	var $judgeContent=$(".judge-service .rmid-content-level");
	var strContent=$(".judge-service .rmid-content-level").attr("fulltext");
	var cutResult ="";
	if (strContent != undefined) {
		var cutResult=cutString(strContent,50);
		if(strContent.length!=cutResult.length){
			$judgeContent.html(cutResult + '<a href="" id="judgeMore">[展开]</a>');
			$judgeContent.click(function(e){
				if($(e.target).is("#judgeMore")){
					if($("#judgeMore").html()=="[展开]"){
						$judgeContent.html(strContent + '<a href="" id="judgeMore">[收起]</a>');
					}
					else{
						$judgeContent.html(cutResult + '<a href="" id="judgeMore">[展开]</a>');
					}
				}
				return false;
			});
		}
		else{
			$judgeContent.html(cutResult);
		}
	}
});