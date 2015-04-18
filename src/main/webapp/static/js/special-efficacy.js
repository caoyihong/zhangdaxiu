$(document).ready(function(){
	//首页-平台案例分享分类切换
	$("#all1").mouseenter(function(){
		$("#left-content4-content1").css("display","block");
		$("#left-content4-content2").css("display","none");
		$("#left-content4-content3").css("display","none");
		$("#left-content4-content4").css("display","none");
		$(this).addClass("left-content4-header-active");
		$("#all2").removeClass("left-content4-header-active");
		$("#all3").removeClass("left-content4-header-active");
		$("#all4").removeClass("left-content4-header-active");
	});
	$("#all2").mouseenter(function(){
		$("#left-content4-content1").css("display","none");
		$("#left-content4-content2").css("display","block");
		$("#left-content4-content3").css("display","none");
		$("#left-content4-content4").css("display","none");
		$(this).addClass("left-content4-header-active");
		$("#all1").removeClass("left-content4-header-active");
		$("#all3").removeClass("left-content4-header-active");
		$("#all4").removeClass("left-content4-header-active");
	});
	$("#all3").mouseenter(function(){
		$("#left-content4-content1").css("display","none");
		$("#left-content4-content2").css("display","none");
		$("#left-content4-content3").css("display","block");
		$("#left-content4-content4").css("display","none");
		$(this).addClass("left-content4-header-active");
		$("#all2").removeClass("left-content4-header-active");
		$("#all1").removeClass("left-content4-header-active");
		$("#all4").removeClass("left-content4-header-active");
	});
	$("#all4").mouseenter(function(){
		$("#left-content4-content1").css("display","none");
		$("#left-content4-content2").css("display","none");
		$("#left-content4-content3").css("display","none");
		$("#left-content4-content4").css("display","block");
		$(this).addClass("left-content4-header-active");
		$("#all2").removeClass("left-content4-header-active");
		$("#all3").removeClass("left-content4-header-active");
		$("#all1").removeClass("left-content4-header-active");
	});
	//首页-搜索类别选择
	$(".index-top span").click(function(){
		$(this).addClass("index-top-active");
		$(".index-top span").not(this).removeClass("index-top-active");
	});
	//首页-导航下拉菜单
	$(".navigate-ul1>li").mouseenter(function(){
		$(this).children().css("color","rgb(69, 68, 68)");
	});
	$(".navigate-ul1>li").mouseleave(function(){
		$(this).children().css("color","#FFFFFF");
	});
	//回到顶部
	$(".nmove-a2").click(function(){
		document.body.scrollTop =0;
	});
	//首页-右侧关注我们始终位于浏览器底部
	addEvent_regist();//注册事件
	openbox();//展开收起
	searchboxOpen();//机构检索框收起
});
//首页-轮播
	$(function(){
            $(".left-content2").luara({width:"688",height:"150",interval:4500,selected:"seleted",deriction:"left"});

        });
//注册切换表单事件
function addEvent_regist(){
	$(".modi-form").click(function(){
		resetCodeTime();//切换表单时重置验证码倒数
		//切换步骤指示
		$(".step-img>img").attr("src","/showimg?url=images/icon-images/step-sign2.png");
		//切换图片选中状态
		$(".modi-form>img").each(function(){
			if($(this).attr("src").indexOf("check")!=-1){
				var Url = $(this).attr("src").replace("_check.png",".png");
				$(this).attr("src",Url);
			}
		});
		$(".title-active").removeClass("title-active");
		var imgUrl = $(this).find("img").attr("src").replace(".png","_check.png");
		$(this).find("img").attr("src",imgUrl);
		$(this).next().addClass("title-active");
		$(".formbox").addClass("visible");
		$(".formbox>div").removeClass("visible");
		$("."+$(this).attr("target")).addClass("visible");
	});
	//验证码倒数事件
	$(".phoneCode").click(function(){
		var $this = $(this);
		if(!$this.hasClass("waitcode")){
			if($this.prev().val()==""){
				$this.parent().parent().next().text("请输入手机号!");
				$this.prev().addClass("warn-border");
			}else{
				if(!/^1\d{10}$/.test($this.prev().val())){
					$this.parent().parent().next().text("请输入正确的手机号!");
					$this.prev().addClass("warn-border");
				}else{
					//获取验证码
					var formname = $("form:visible").attr("class");
					var type = formname=="bridge-form"?2:1;
					if (type == 1) {
						type = formname=="company-form"?1:0;
					}
					$.ajax({
						url:"/common/phonecode",
						type:"get",
						data:{
							type:type,
							phone:$this.prev().val(),
						},
						success:function(data){
							if(data==0){
								$this.parent().parent().next().text("该手机号已经被注册过!");
								$this.prev().addClass("warn-border");
							}else{
								phoneback = data;
								s = 60;
								isrun= 1;
								$thiscode = $this;
								times();
								$this.addClass("waitcode");
							}
						}
					});
					
				}
				
			}
			
		}
	});
}
//60秒倒数事件
var  s,$thiscode,isrun=1;
function times(){
	 s--;
	$thiscode.text(s+"秒后可重新获取!");
	if ( s <= 0 ){
		 s = 60;
		 $thiscode.removeClass("waitcode");
		 $thiscode.text("获取验证码");
	}else{
		console.log(s);
		if(isrun)
		setTimeout('times()', 1000);
	}
}
//倒数重置
function resetCodeTime(){
	isrun = 0;
	$(".phoneCode").text("获取验证码");
	$(".phoneCode").removeClass("waitcode");
	s=60;
} 
//评分进度条
function loadGradeBar(){
	$(".pro-bar-inner").css("width",$(".pro-bar").width()*Number($(".pro-value").text().replace("%",""))/100);
	$(".service-bar-inner").css("width",$(".service-bar").width()*Number($(".ser-value").text().replace("%",""))/100);
}
//展开收起的按钮
function openbox(){
	$(".moreservicetype").click(function(){
		var $this = $(this);
		var status = $this.attr("status");
		if(status=="putaway"){
			$this.prev().removeClass("box-content-open");
			$this.attr("status","open");
			$this.html("[收起]");
		} else{
			$this.prev().addClass("box-content-open");
			$this.attr("status","putaway");
			$this.html("[展开更多]");
		}
	});
}
//机构检索条件展开收起
function searchboxOpen(){
	$(".close-search-box").click(function(){
		var $s_box_inner  = $(".search-box-inner");
		var status = $s_box_inner.attr("status");
		if(status=="open"){
			$s_box_inner.addClass("search-box-inner-putaway");
			$s_box_inner.attr("status","puaway");
			$(this).text("展开条件");
		}else{
			$s_box_inner.removeClass("search-box-inner-putaway");
			$s_box_inner.attr("status","open");
			$(this).text("收起条件");
		}
	});
}
function slide_Down(showName){
	$(showName).slideDown();
	$("body,html").animate({scrollTop:parseInt($(showName).offset().top)-20},600);
	$(showName).css("border-color","#43B6FF");
	$(showName).css("box-shadow","3px 5px 3px #96D4FD");
}