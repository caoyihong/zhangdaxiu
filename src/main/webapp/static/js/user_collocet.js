/******************用户收藏专用********************/
var commandName=[];
commandName["cancelUserCollect"]="/work/cancelstore";//取消用户收藏 ids是userid
commandName["cancelAgencyCollect"]="/work/cancelstore";//取消机构收藏 ids是agencyid
var but1=[{
		name:"确定",
		value:1
	}];
var but2=[{
		name:"确定",
		value:1
	},
	{
		name:"取消",
		value:0
	}];
function cancelCollect(com,id,type,context,callback){
	$.ajax({
		url:commandName[com],
		async : false,
		type:"post",
		context: context,/**传入callback的运行作用域，方便操作**/
		data:{
			id:id,
			type:type
		},
		success:function(data){
			callback.call(this,data);
		},
          error:function(data){
			//弹出一个窗口告诉用户连接失效啥的
			msgbox("Sorry","fail","发生了未知错误，如果这种事情持续出现，请与客服联系！",but1,function(){});
		},
		
	});
}
var $msgbox;
function msgbox(title,context,content,button,callback){
	$msgbox.find(".title").html(title || "title");
	$msgbox.find(".content").html(content || "content");
	//动态添加按钮，允许的传入值是：YESORNO、YES、CANCEL
	//如果需要根据用户点击的按钮不同，作出不同的响应，则需要传入一个callback，否则传入一个空函数
	var len=button.length;
	$msgbox.find('.button').remove();
	for (var i=0;i<len;i++){
		$msgbox.append('<a href class="button" value="' + button[i].value + '">' + button[i].name + '</a>');
	}
	//设置弹窗的位置
	$msgbox.css("top",$(context).offset().top).css("left",$(context).offset().left);
	$msgbox.show();
	//响应click
	$msgbox.unbind();
	$msgbox.click(function(e){
		if($(e.target).attr("class")=="button"){
			callback.call(context,$(e.target).attr("value"));
			$msgbox.hide();
		}
		return false;
	});
}
function getQueryString(paramname){
     var reg = new RegExp("(^|&)"+ paramname +"=([^&]*)(&|$)");
     var param = window.location.search.substr(1).match(reg);
     if(param!=null)return  decodeURI(param[2]); return null;
}
function getRealAddress(){
	var url = window.location.href;
	if(url.indexOf("?")!=-1){
		return url.split("?")[0] 
	}else{
		return url;
	}
}
function ini(){//设置初始状态
	//设置收藏中介人页面的奇偶显示不同
	$(".item_user:odd").css("margin","0 0 24px 11px");
	$(".item_user:even").css("margin","0 0 24px");
	$msgbox=$("#msgbox").hide();
	//给头像加链接
	$(".photo").click(function(){
		location.assign($(this).next().attr("href"));
	});
}
function bindClick(){
	$(".item_user .cancelCollect").click(function(){
		var context=this;
		var id=$(this).parents(".item_user").attr("userid");
		msgbox("提示",context,"确定要取消收藏该中介人吗？",but2,function(value){
			if(value==1){
				cancelCollect("cancelUserCollect",id,0,context,function(result){
					if(result=="success"){
						//msgbox("操作成功","success","您已成功取消收藏，点击确定刷新页面！",but1,function(){
							//location.reload();
							$(context).parents(".item_user").remove();
						//});
					}
					else{
						msgbox("失败","fail","取消收藏失败，请重新登录后再次尝试！",but1,function(){});
					}
				});
			}
		});
		return false;
	});
	$(document).click(function(e){
		if(!$(e.target).is("#msgbox")){
			$("#msgbox").hide();
		}
	});
	$(".item_agency .cancelCollect").click(function(){
		var context=this;
		var id=$(this).parents(".item_agency").attr("agencyid");
		msgbox("提示",context,"确定要取消收藏该中介吗？",but2,function(value){
			if(value==1){
				cancelCollect("cancelAgencyCollect",id,1,context,function(result){
					if(result=="success"){
						//msgbox("操作成功","success","您已成功取消收藏，点击确定刷新页面！",but1,function(){
							//location.reload();
							$(context).parents(".item_agency").remove();
						//});
					}
					else{
						msgbox("失败","fail","取消收藏失败，请重新登录后再次尝试！",but1,function(){});
					}
				});
			}
		});
		return false;
	});
}
//下拉菜单更新
function updateSelect(){
	$(".sel-wrap").on("change", function() {
		var o;
		var opt = $(this).find('option');
		opt.each(function(i) {
			if (opt[i].selected == true) {
				o = opt[i].innerHTML;
			}
		})
		$(this).find('label').html(o + '<span class="col-arrow_down"><img src="' + $(this).attr("imgsrc") + '" /></span>');
	}).trigger('change');	
}
//区域选择更新
function updateProvince(pid){
	var len=area_array.length;
	var innerhtml='';
	for(var i=0;i<len;i++){
		if(area_array[i]==undefined){
			continue;
		}
		innerhtml+='<option value="' + area_array[i] + '" key=' + i + '>' + area_array[i] + '</option>';
	}
	$(pid).html(innerhtml);
}
function updateCity(pid,cid,aid){
	//根据所选择的省份更新可供选择的城市
	$(pid).on("change",function(){
		var key=$(this).find("option:selected").attr("key");
		var innerhtml='<option value="请选择" selected="selected">请选择</option>';
		$(aid).html(innerhtml).next().html('请选择<span class="col-arrow_down"><img src="/showimg?url=images/icon-images/arrow_down_gray.png" /></span>');
		if(sub_array[key]==undefined){
			$(cid).html(innerhtml).next().html('请选择<span class="col-arrow_down"><img src="/showimg?url=images/icon-images/arrow_down_gray.png" /></span>');
			$(aid + "_hide").show();
			return ;
		}
		$(aid + "_hide").show();
		if(sub_arr[key*100+1]==undefined){
			//直辖市取消显示第三个框
			$(aid + "_hide").hide();
		}
		var len=sub_array[key].length;
		//需要一并把area的显示更新掉
		innerhtml="";
		for(var i=0;i<len;i++){
			if(sub_array[key][i]==undefined){
				continue;
			}
			innerhtml+='<option value="' + sub_array[key][i] + '" key=' + i + ' >' + sub_array[key][i] + '</option>';
		}
		//更新city的显示并将请选择设为选中项
		$(cid).html(innerhtml).next().html('请选择<span class="col-arrow_down"><img src="/showimg?url=images/icon-images/arrow_down_gray.png" /></span>');
	});
}
function updateArea(pid,cid,aid){
	//根据所选择的城市更新可供选择的区块
	$(cid).on("change",function(){
		var key=$(this).find("option:selected").attr("key");
		if(sub_arr[key]==undefined){
			//直辖市没有第三级区划
			innerhtml+='<option value="' + $(this).val() + '"  selected="selected">' + $(this).val() + '</option>';
			$(aid).html(innerhtml).next().html($(this).val() + '<span class="col-arrow_down"><img src="/showimg?url=images/icon-images/arrow_down_gray.png" /></span>');
			return ;
		}
		var len=sub_arr[key].length;
		var data=[];
		var innerhtml='';
		for(var i=0;i<len;i++){
			if(sub_arr[key][i]==undefined){
				continue;
			}
			innerhtml+='<option value="' + sub_arr[key][i] + '">' + sub_arr[key][i] + '</option>';
		}
		//更新area的显示并将请选择设为选中项
		$(aid).html(innerhtml).next().html('请选择<span class="col-arrow_down"><img src="/showimg?url=images/icon-images/arrow_down_gray.png" /></span>');
	});
}
function updatePCA(pid,cid,aid){
	updateProvince(pid);
	updateCity(pid,cid,aid);
	updateArea(pid,cid,aid);
}
$(document).ready(function(){
	bindClick();
	updatePCA("#province00","#city00","#area00");
	updatePCA("#province01","#city01","#area01")
	updateSelect();
	ini();
});