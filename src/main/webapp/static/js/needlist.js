/******************需求列表专用********************/
var commandName=[];
commandName["delete"]="/users/normal/deleteNeed";//删除 id是needid
commandName["changestate"]="/users/normal/changeNeedStatus";//更改状态 id是needid
commandName["reply"]="/work/needreply";//回复 id是needid
commandName["report"]="report";//举报 id是needid，args是conversation
commandName["restore"]="/users/normal/recoverNeed";//恢复 id是needid
commandName["deletereply"]="deletereply";//删除回复 id是needid，args是conversation
commandName["needDetailed"]="/common/showorder";//点击需求细节时跳转到其他页面 什么参数也不传

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
function clearDate(){
	$("input.datepicker").val("");
}
function editNeed(com,needID,args,context,callback){
	//传回CRUD命令以及ID+参数，提供一个callback确定
	//needID及detailed都是数组
	//拼接数组成字符串
	function joint(value){
		var len=value.length;
		var result="";
		for(var i=0;i<len;i++){
			result+=value[i].toString() + ",";
		}
		return result;
	}
	$.ajax({
		url:commandName[com],
		type:"post",
		context:context,/**传入callback的运行作用域，方便操作**/
		data:{
			"needIDs":$.trim(joint(needID)),
			"args":$.trim(joint(args))
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
 function getElementLeft(element){
    var actualLeft = element.offsetLeft;
    var current = element.offsetParent;
    while (current !== null){        
        actualLeft += current.offsetLeft;
        current = current.offsetParent;
    }
    return actualLeft;
}

function getElementTop(element){
    var actualTop = element.offsetTop;
    var current = element.offsetParent;
    while (current !== null){        
        actualTop += current. offsetTop;
        current = current.offsetParent;
    }
    return actualTop;
}
function getChinese(value){
	var patrn=/[\u2E80-\uFE4F]+/;
	return value.match(patrn)[0];
}
/*function popSelect(arr,callback){
	//显示弹出的列表，并在做出选择时调用callback，需要call调用传入作用域
	$editicon=$(this).find(".edit-icon");
	var strNode="";
	for(var i=0;i<arr.length;i++){
		strNode+= '<a href class="finish">' + arr[i] + '</a>'
	}
	$node=$('<div class="smallselect">' + strNode + '</div>');
	$(this).after($node);
	$(this).parent().mouseleave(function(){
		$(this).find(".smallselect").hide();
		return false;
	});
	$editicon.parent().click(function(){
		//显示一个小窗体，并绑定事件用于编辑需求状态
		var $smallselect=$(this).parent().find(".smallselect").toggle();
		return false;
	});
	$(this).parent().find(".smallselect a").click(function(){
		//改变状态的代码
		var html=$(this).html();
		callback.call(this,html);
		return false;
	});
}*/
function refresh(){
	/*******************************
	**这里设置需求状态
	**
	**
	*******************************/
	$(".need-stat > a").each(function(){
		//将状态的格式颜色改掉
		if(getChinese($(this).html())=='纠纷'){
			$(this).css("background","#FFB03B");
		}
		if(getChinese($(this).html())=='待解决'){
			$(this).css("background","#8CC152");
		}
		if(getChinese($(this).html())=='被接单'|| (getChinese($(this).html())=='已接单')){
			$(this).css("background","#FB6E52");
		}
		//将状态是纠纷、被接单的项设为不可选
		if((getChinese($(this).html())=='纠纷') || (getChinese($(this).html())=='被接单')){
			var $checkbox=$(this).parent().parent().find("input[type=checkbox]");
			
			//$checkbox.attr("disabled","disabled");
			$checkbox.attr("dis","dis").click(function(){
				$(this).prop("checked",false);
			});
			//增加一个小弹窗用于提示
			//添加事件响应
			$checkbox.mouseover(function(e){
				$(".tips").css("top",getElementTop($(this)[0])+30 + "px").css("left",getElementLeft($(this)[0])+30 + "px");
				$(".tips").show();
			});
			$checkbox.mouseleave(function(){
				$(".tips").hide();
			});
		}
/*		if(getChinese($(this).html())=='被接单'){
			//为被接单的项添加状态修改的弹框
			popSelect.call(this,["纠纷","完成"],function(html){
				//被接单状态改变时的处理函数
				msgbox.call(this,"提示","warning","您确定要将需求状态设置为" + html + "?",but2,function(userselect){
					if(userselect=="1"){
						//选择是，ajax后台editNeed(com,needID,args,callback)
						var needID=[];
						var args=[];
						needID.push($(this).parent().parent().parent().attr("needID"));
						args.push(html);
						editNeed("changestate",needID,args,this,function(value){
							if(value=="success"){
								$(this).html(getChinese($(this).parent().parent().find("> a").html()));
//								$(this).parent().parent().find(" > a").html(html + '<span class="edit-icon"><img src="images/icon-images/arrow_down_circle.png" /></span>');
								msgbox.call(this,"提示","success","状态设置成功！",but1,function(){});
							}
							else{
								msgbox.call(this,"提示","fail","状态设置失败！您可以稍后重试，如果这种状况多次出现，请与客服联系！",but1,function(){});
							}
						});
					}
					else{
						//选择否清理现场
						//暂时没发现要清理的东西
					}
				});
				return false;
			});
		}
		else if(getChinese($(this).html())=='纠纷'){
			popSelect.call(this,["被接单","完成"],function(value){
				//纠纷状态改变时的处理函数
				msgbox.call(this,"提示","warning","您确定要将需求状态设置为" + value + "?",but2,function(userselect){
					if(userselect=="1"){
						//选择是，ajax后台editNeed(com,needID,args,callback)
						var needID=[];
						var args=[];
						needID.push($(this).parent().parent().parent().attr("needID"));
						args.push(html);
						editNeed("changestate",needID,args,this,function(value){
							if(value=="success"){
								$(this).html(getChinese($(this).parent().parent().find("> a").html()));
//								$(this).parent().parent().find(" > a").html(html + '<span class="edit-icon"><img src="images/icon-images/arrow_down_circle.png" /></span>');
								msgbox.call(this,"提示","success","状态设置成功！",but1,function(){});
							}
							else{
								msgbox.call(this,"提示","fail","状态设置失败！您可以稍后重试，如果这种状况多次出现，请与客服联系！",but1,function(){});
							}
						});
					}
					else{
						//选择否清理现场
						//暂时没发现要清理的东西
					}
				});
				return false;
			});
		}
		else{
			//对于已完成的需求将不显示下拉的小箭头
//			$(this).find(".edit-icon").html("");
			$(this).click(function(){
				return false;
			});
		}
		*/
	});
}
function control_normaluser(){
	$(".tab-title").click(function(){
		var $this = $(this);
		$(".tab-title").removeClass("tab-title-active");
		$this.addClass("tab-title-active");
		$(".tab-form-box>div").removeClass("visible");
		$(".tab-form-box>div[formname="+$this.attr("target")+"]").addClass("visible");
	});
}
function ini(){//设置成初始状态
	//设置右侧按钮
	$(".item").find(".need-detailed").hide();
	$(".popbut").html('<a><img src="/showimg?url=images/icon-images/spread-icon.png" />展开</a>');
	//设置标题
	$(".item .need-title").each(function(){
		$(this).html(cutString($(this).attr("fulltext"),30));
	});
	$(".item").css("border","1px solid #C9C9C9").css("margin-bottom","0px").css("background","none");
	$(".self-intro-setting-form[formname=need-all] .item:gt(0)").css("border-top","none");
	$(".self-intro-setting-form[formname=need-wait] .item:gt(0)").css("border-top","none");
	$(".self-intro-setting-form[formname=need-recycle] .item:gt(0)").css("border-top","none");
}
function bindClick(){
	function bindSelectAll(boxSelector){
		if($(this).is(":checked")){
			$(boxSelector + " .itemlist-list input[type=checkbox]").each(function(){
				if($(this).attr("dis")!=="dis"){
					$(this).prop("checked",true);
				}
			});
		}
		else{
			$(boxSelector + " .itemlist-list input[type=checkbox]").each(function(){
				if($(this).attr("dis")!=="dis"){
					$(this).prop("checked",false);
				}
			});
		}
	}
	$("#selectAll01").click(function(){
		bindSelectAll.call(this,".self-intro-setting-form[formname=need-all]");
	});
	$("#selectAll02").click(function(){
		bindSelectAll.call(this,".self-intro-setting-form[formname=need-wait]");
	});
	$("#selectAll03").click(function(){
		bindSelectAll.call(this,".self-intro-setting-form[formname=need-recycle]");
	});
	function bindDeleteSelected(boxSelector){
		var checkedlist=[];
		var $checkboxlist=$(boxSelector + " .itemlist-list input[type=checkbox]");
		$checkboxlist.each(function(){
			if($(this).is(":checked")){
				checkedlist.push($(this).parent().attr("needID"));
			}
		});
		if(checkedlist.length==0){
			//提示没有选中的项
			msgbox("提示","warning","您没有选择任何需求！",but1,function(){return false;});
			return false;
		}
		else{
			//提示是否确定要删除
			msgbox("提示","warning","您确定要删除选中项吗？",but2,function(value){
				if(value=="1"){
					//传回删除的内容
					editNeed("delete",checkedlist,[],this,function(value){
						if(value=="success"){
							//刷新页面
							msgbox("提示","success","删除成功，点击确定刷新。",but1,function(){
								location.reload();
							});
						}
						else{
							//提示一下，啥也不做
							msgbox("提示","fail","您的权限不够或系统错误，如果这个问题持续出现，请与客服联系。",but1,function(){});
						}
					});
				}
				else{
					//清理现场
					//暂时没有要清理的

				}
				return false;
			});
			return false;
		}
	}
	$("#deleteSelected01").click(function(){
		return bindDeleteSelected.call(this,".self-intro-setting-form[formname=need-all]");
	});
	$("#deleteSelected02").click(function(){
		return bindDeleteSelected.call(this,".self-intro-setting-form[formname=need-wait]");
	});
	$("#deleteSelected03").click(function(){
		return bindDeleteSelected.call(this,".self-intro-setting-form[formname=need-recycle]");
	});
	$("#reStore03").click(function(){
		var checkedlist=[];
		var $checkboxlist=$(".self-intro-setting-form[formname=need-recycle] .itemlist-list input[type=checkbox]");
		$checkboxlist.each(function(){
			if($(this).is(":checked")){
				checkedlist.push($(this).parent().attr("needID"));
			}
		});
		if(checkedlist.length==0){
			//提示没有选中的项
			msgbox("提示","warning","您没有选择任何需求！",but1,function(){return false;});
			return false;
		}
		else{
			//提示是否确定要删除
			msgbox("提示","warning","您确定要恢复选中项吗？",but2,function(value){
				if(value=="1"){
					//传回删除的内容
					editNeed("restore",checkedlist,[],this,function(value){
						if(value=="success"){
							//刷新页面
							msgbox("提示","success","选中项恢复成功，点击确定刷新。",but1,function(){
								location.reload();
							});
						}
						else{
							//提示一下，啥也不做
							msgbox("提示","fail","您的权限不够或系统错误，如果这个问题持续出现，请与客服联系。",but1,function(){});
						}
					});
				}
				else{
					//清理现场
					//暂时没有要清理的

				}
				return false;
			});
			return false;
		}		
	});
	$(".item .need-detailed").click(function(e){
		if($(e.target).is(".seemore")){
			//location.assign( commandName["needDetailed"] + "?needid=" + $(this).parents(".item").attr("needID"));
			return true;
		}
		return false;
	});
	$(".item").click(function(e){
		if(!($(e.target).is("input") || $(e.target).is("textarea") || $(e.target).is("a")) || $(e.target).parent().is(".popbut")){
			if($(this).find(".need-detailed").is(":hidden")){
				ini();
				$(this).find(".need-detailed").toggle();
				//判断当前的状态，1、修改标题的字数；2、修改popbut的html；3、修改边框颜色
				$(this).find(".popbut").html('<a><img src="/showimg?url=images/icon-images/packup-icon.png" />收起</a>');
				var $needtitle=$(this).find(".need-title");
				$needtitle.html($needtitle.attr("fulltext"));
				$(this).css("border","1px solid #EE8C00").css("margin-bottom","13px").css("background","#FEF7EA");
				//边框修复
				$(this).prev().css("border-bottom","none");
				$(this).next().css("border-top","1px solid #C9C9C9");
			}
			else{
				ini();
			}
			return false;
		}else{
			return true;
		}
	});
	$(".answer .answer").click(function(){
		$(this).parent().parent().parent().find(".need-conversation-typein textarea").focus();
		return false;
	});
	$(".answer .delete").click(function(){//对回复的操作会发送回去需求id，然后args里存回复id
		var context=this;
		msgbox("提示","warning","您确定要删除该回复吗？",but2,function(value){
			if(value=="1"){
				//传回删除的内容
				var needid=[];
				needid.push($(context).parents(".item").attr("needid"));
				var args=[];
				editNeed("deletereply",needid,args,context,function(value){
					if(value=="success"){
						msgbox("提示","success","回复已经删除，点击确定刷新！",but1,function(userselect){
							if(userselect=="1"){
								location.reload();
							}
						});
					}
					else{
						msgbox("提示","fail","删除失败，请稍后重试！",but1,function(){return false;});
					}
				});
			}
			else{
				//清理现场
				//暂时没有要清理的

			}
			return false;
		});
		return false;
	});
	$(".answer .report").click(function(){
		var context=this;
		msgbox("提示","warning","您确定要举报该回复吗？",but2,function(value){
			if(value=="1"){
				//传回删除的内容
				var needid=[];
				needid.push($(context).parents(".item").attr("needid"));
				var args=[];
				editNeed("report",needid,args,context,function(value){
					if(value=="success"){
						msgbox("提示","success","举报成功，点击确定刷新！",but1,function(userselect){
							if(userselect=="1"){
								location.reload();
							}
						});
					}
					else{
						msgbox("提示","fail","举报失败，请稍后重试！",but1,function(){return false;});
					}
				});
			}
			else{
				//清理现场
				//暂时没有要清理的

			}
			return false;
		});
		return false;
	});
	$(".item .sendbut").click(function(){
		var needid=[];
		needid.push($(this).parents(".item").attr("needid"));
		var args=[];
		args.push($(this).parent().find("textarea").val());
		editNeed("reply",needid,args,this,function(value){
			if(value=="success"){
				msgbox("提示","success","回复成功，点击确定刷新！",but1,function(userselect){
					if(userselect=="1"){
						location.reload();
					}
				});
			}
			else{
				msgbox("提示","fail","回复失败，请稍后重试！",but1,function(){return false;});
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
function getNextDay(d){
        d = new Date(d);
        d = +d + 1000*60*60*24;
        d = new Date(d);
        return d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();     
}

function cutText(maxlen){
	//专用函数，让needdetailed显示的字符在100个以内
	//如果有多个p标签，则保留第一个删除其他的，然后把第一个截取前一百个字
	$(".need-conversation").each(function(){
		var $ps=$(this).find("p");
		if($ps.length<=1){
			$ps.html(cutString($ps.html(),maxlen) + '<a target= _blank href="/common/showorder/' + $(this).parents(".item").attr("needid") + '" class="seemore">[查看更多]</a>');
		}
		else{
			var len=maxlen;
			$ps.each(function(){
				
				if(len>0){
					if($(this).html().length<len){
						len=len-$(this).html().length;
						return ;
					}
					else{
						$(this).html(cutString($ps.html(),len) + '<a target= _blank href="/common/showorder/' + $(this).parents(".item").attr("needid") + '" class="seemore">[查看更多]</a>');
						len=0;
					}
				}
				else{
					$(this).remove();
				}
				
			});
		}
	});
}
$(document).ready(function(){
	updateSelect();
	refresh();
	ini();
	control_normaluser();
	cutText(100);
	bindClick();
	$("body").append('<div class="tips">当前状态不可以修改！</div>');

	//开始日期要小于结束日期
	$('.datepicker[name=begintime]').on('focus',function(){
	    $(this).datetimepicker({
	        lang:'ch',
	        timepicker:false,
	        format:'Y-m-d',
	        formatDate:'Y-m-d',
	        minDate:false
	    });
	});
	$('.datepicker[name=begintime]').on('blur',function(){
	    $(this).parent().next().next().find('.datepicker[name=endtime]').datetimepicker({
	        lang:'ch',
	        timepicker:false,
	        format:'Y-m-d',
	        formatDate:'Y-m-d',
	        minDate:getNextDay($(this).val())
	    });
	});
});


//显示所有需求
function allNeeds(domId,buildMethodName,page){
	title=$("#hidtitle").val();
	starttime=$("#hidstarttime").val();
	endtime=$("#hidendtime").val();
	type=$("#hidtype").val();
	employeename=$("#hidemployeename").val();
	status=$("#hidstatus").val();
	allNeeds_ajax(domId, buildMethodName, page, title, starttime, endtime, type, employeename, status);
}
function searchAllNeeds(domId,buildMethodName,page){
	title=$("#title").val();
	starttime=$("#starttime").val();
	endtime=$("#endtime").val();
	type=$("#type").val();
	employeename=$("#employeename").val();
	status=$("#status").val();
	
	allNeeds_ajax(domId, "allNeeds", page, title, starttime, endtime, type, employeename, status);
	
	$("#hidtitle").val(title);
	$("#hidstarttime").val(starttime);
	$("#hidendtime").val(endtime);
	$("#hidtype").val(type);
	$("#hidemployeename").val(employeename);
	$("#hidstatus").val(status);
}

function allNeeds_ajax(domId,buildMethodName,page,title,starttime,endtime,type,employeename,status){
	$.ajax({
		url:"/users/normal/allNeeds",
		type:"get",
		async:false,
		data:{
			currentPage:page,
			title:title,
			starttime:starttime,
			endtime:endtime,
			type:type,
			employeename:employeename,
			status:status,
		},
		dataType:"json",
		success:function(data){
			document.getElementById("allNeeds").innerHTML = "";
			var needs = data.list;
			var inhtml = "";
			for ( var int = 0; int < needs.length; int++) {
				var need = needs[int]['need'];
				inhtml += " <li class='item' needID="+need.id +" ><input type='checkbox' name id='cb01' needid='"+need.id+"' />"
							+" <span class='need-title' fulltext='"+need.title+"'>"+need.title+"</span>";
				if(needs[int]['status'] == 0) {
					inhtml += "<span class='payedimg'>预</span>";
				}else {
					inhtml += "<span class='payedblank'></span>";
				}
				inhtml += "<span class='need-publishtime'>"+$.formatDate("yyyy-MM-dd",new Date(need.createtime))+"</span>"
						+"<span class='need-kind'>"+need.type+"</span><span class='need-stat'>";
				
				if (needs[int]['status'] == 1) {
					inhtml += "<a href='/common/showorder/"+need.id+"'>被接单<span class='edit-icon'><img src='/showimg?url=images/icon-images/arrow_down_circle.png' /></span></a></span><span class='lawer-name'>";
				}else if (needs[int]['status'] == 2) {
					inhtml += "<a href='/common/showorder/"+need.id+"'>已完成<span class='edit-icon'><img src='/showimg?url=images/icon-images/arrow_down_circle.png' /></span></a></span><span class='lawer-name'>";
				}else if (needs[int]['status'] == 0) {
					inhtml += "<a href='/common/showorder/"+need.id+"'>待解决<span class='edit-icon'><img src='/showimg?url=images/icon-images/arrow_down_circle.png' /></span></a></span><span class='lawer-name'>";
				}else if (needs[int]['status'] == 3) {
					inhtml += "<a href='/common/showorder/"+need.id+"'>纠纷<span class='edit-icon'><img src='/showimg?url=images/icon-images/arrow_down_circle.png' /></span></a></span><span class='lawer-name'>";
				}
				if (needs[int]['employee'] != null) {
					inhtml += "<a href='/agency/"+needs[int]['employee']['agencyid']+"/agencye/"+needs[int]['employee']['id']+"'>"+needs[int]['employee']['name']+"</a>"
				}
				inhtml += "</span><div class='need-detailed'><span class='need-detailed-arrow_top'>" +
						"<img src='/showimg?url=images/icon-images/arrow_down_orange.png' /></span>" +
						"<span class='need-price'><img class='icon_left' src='/showimg?url=images/icon-images/bonuse.png'/>" +
						"<h1>悬赏金额：<span><span class='price'>"+need.price+"</span>万元</span>" +
						"<img class='icon_right' src='/showimg?url=images/icon-images/taskprice.png' />" +
						"<span class='published'><span class='title'>发布时间：</span>" +
						"<span class='date'>"+$.formatDate("yyyy-MM-dd HH:mm:ss",new Date(need.createtime))+"</span></span></h1></span>" +
						"<span class='need-content'><img class='icon_left' src='/showimg?url=images/icon-images/detailed.png' />" +
						"<span class='need-conversation ask' conversation=0 ><p>"+need.detail+"</p></span></span></div><div class='popbut'><a><img src='/showimg?url=images/icon-images/spread-icon.png' />展开</a></div></li>";
			}
			document.getElementById("allNeeds").innerHTML = inhtml;
			bindClick();
			initPaginator_ajax(domId,buildMethodName,page,data.pages);//拼装完成后调用
		}
	});
}

function deleteNeeds(domId,buildMethodName,page) {
	$.ajax({
		url:"/users/normal/deleteNeeds",
		type:"get",
		async:false,
		data:{
			currentPage:page,
		},
		dataType:"json",
		success:function(data){
			document.getElementById("deleteNeeds").innerHTML = "";
			var needs = data.list;
			var inhtml = "";
			for ( var int = 0; int < needs.length; int++) {
				var need = needs[int]['need'];
				inhtml +="<li class='item' needID="+need.id+" >"+
			            "<input type='checkbox' name id='cb01' needid='"+need.id+"' />"+
			            "<span class='need-title' fulltext='"+need.title+"'>"+need.title+"</span>"+
			            "<span class='payedblank'></span>"+
			            "<span class='need-publishtime'>"+$.formatDate("yyyy-MM-dd",new Date(need.createtime))+"</span>"+
			            "<span class='need-kind'>"+need.type+"</span>"+
			            "<span class='need-stat'>"+
			                "<a href>已删除<span class='edit-icon'><img src='/showimg?url=images/icon-images/arrow_down_circle.png' /></span>"+
			                "</a>"+
			            "</span>"+
			            "<span class='lawer-name'>";
				if (needs[int]['employee'] != null) {
					inhtml += "<a href='/agency/"+needs[int]['employee']['agencyid']+"/agencye/"+needs[int]['employee']['id']+"'>"+needs[int]['employee']['name']+"</a>";
				}
				inhtml += "</span>"+
			            "<div class='need-detailed'>"+
			                "<span class='need-detailed-arrow_top'><img src='/showimg?url=images/icon-images/arrow_down_orange.png' /></span>"+
			                "<span class='need-price'>"+
			                    "<img class='icon_left' src='/showimg?url=images/icon-images/bonuse.png' />"+
			                    "<h1>悬赏金额：<span><span class='price'>"+need.price+"</span>万元</span>"+
			                    "<img class='icon_right' src='/showimg?url=images/icon-images/taskprice.png' /></h1>"+
			                "</span>"+
			                "<span class='need-content'>"+
			                    "<img class='icon_left' src='/showimg?url=images/icon-images/detailed.png' />"+
			                    "<h1>需求详情：</h1>"+
			                    "<span class='need-conversation ask' conversation=0 >"+
									 "<h2>"+
			                            "<span class='title'>提问时间：</span>"+
			                            "<span class='date'>"+$.formatDate("yyyy-MM-dd",new Date(need.createtime))+"</span>"+
			                        "</h2>"+
			                        "<p>"+need.detail+"</p>"+
			                    "</span>"+
			                "</span>"+
			            "</div>"+
			            "<div class='popbut'>"+
			                "<a><img src='/showimg?url=images/icon-images/spread-icon.png' />展开</a>"+
			            "</div>"+
			        "</li>";
				
				
			}
			document.getElementById("deleteNeeds").innerHTML = inhtml;
			bindClick();
			initPaginator_ajax(domId,buildMethodName,page,data.pages);//拼装完成后调用
		}
	});
}


