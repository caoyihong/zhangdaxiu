/*截取字符串显示省略号*/
function cutString(content,len){
	if(content.length>len){
		return content.substring(0,len)+"...";
	}else{
		return content;
	}
	
}
function switchBr(value){
	return value.replace(/\n/g,"<br>");
}
function formatHTML(value){
	//用br代替回车
	return removeSpace(switchBr(value));
}
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
/*格式化日期 (个人动态类似)*/
function dateFormat(dt){
	dt = timeFormat(dt);
	var time = NewDate(dt);
	var date  = time.getTime()/1000;
	var now = new Date().getTime()/1000;
	var flag = now-date;
	var a=60,b=60*60,c = 60*60*24,d = 60*60*24*30,e = 60*60*24*30*12;
	var str = "";
	
	var hours = time.getHours();
	var minutes = time.getMinutes();
	if (hours < 10) {
		hours = "0"+hours;
	}
	
	if (minutes < 10) {
		minutes = "0"+minutes;
	}
	
	if(flag<c){
		str = hours + " : " + minutes ;
	}
	if(c<=flag&&flag<d){
		var dayago = parseInt(flag/c);
		if (dayago == 1) {
			str = "昨天 "+ hours + " : " + minutes ;
		}else if (dayago == 2) {
			str = "前天 "+ hours + " : " + minutes ;
		}else {
			
			str = parseInt(time.getMonth()+1)  + "月" +time.getDate() +"日 " + hours + " : " + minutes ;
		}
	}
	if(flag>=e){
		var yearago = parseInt(flag/e);
		if (yearago == 1) {
			str = "去年 "+ time.getMonth()+1 + "月" +time.gettime() +"日 " + hours + " : " + minutes ;
		}else if (dayago == 2) {
			str = "前年 "+ time.getMonth()+1 + "月" +time.gettime() +"日 " + hours + " : " + minutes ;
		}else {
			str = time.getFullYear() + "年"+ time.getMonth()+1 + "月" +time.getDate() +"日 " + hours + " : " + minutes ;
		}
	}
	return str;
	
}

/*创建日期对象的兼容方法  日期字符串格式  (2010-12-01 10:10:10)*/
function NewDate(d) { 
	var str = d.split(' ')[0].split("-"); 
	var str1 = d.split(' ')[1].split(":");
	var date = new Date(); 
	date.setFullYear(str[0], str[1] - 1, str[2]); 
	date.setHours(str1[0], str1[1], str1[2]);
	//console.log(date);
	return date; 
} 
/*消息的日期格式化*/
function dateFormat_Msg(dt){
	dt  = dt.replace(".0","");
	var date  = NewDate(dt).getTime()/1000;
	var now = new Date().getTime()/1000;
	var flag = now-date;
	var a=60,b=60*60,c = 60*60*24,d = 60*60*24*30,e = 60*60*24*30*12;
	var str = "刚刚";
	if(a<=flag&&flag<b){
		str = parseInt(flag/a)+"分钟前";
	}
	if(b<=flag&&flag<c){
		str =parseInt(flag/b)+"小时前";
	}
	if(c<=flag&&flag<d){
		str = parseInt(flag/c)+"天前";
	}
	if(d<=flag&&flag<e){
	
		str = parseInt(flag/d)+"月前";
	}
	if(flag>=e){
		str = parseInt(flag/e)+"年前";
	}
	return str;
	
}
//获取url参数
function getQueryString(paramname){
    var reg = new RegExp("(^|&)"+ paramname +"=([^&]*)(&|$)");
     var r =window.location.search.substr(1).match(reg);
     if(r!=null)return  decodeURI(r[2]); return null;
}
//替换url参数
function  changeParmOfUrl(paramname,val){
	var r =window.location.search.substr(1);
	var params = r.split("&");
	$.each(params,function(index,data){
		if(data.indexOf(paramname)!=-1){
			params[index] = paramname+"="+val;
		}
	});
	var newUrl = getRealAddress()+"?"+params.join("&");
	return newUrl;
}
//获取不带参数的地址
function getRealAddress(){
	var url = window.location.href;
	if(url.indexOf("?")!=-1){
		return url.split("?")[0] 
	}else{
		return url;
	}
}
//匹配邮箱
function checkEmail(email){
	return /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(email);
}
//匹配汉字
function checkWords(realname){
	return /^[\u4e00-\u9fa5]+$/.test(realname);
}
//匹配网址
function  checkNormalUrl(url){
	return /^((https?|ftp|news):\/\/)?([a-z]([a-z0-9\-]*[\.。])+([a-z]{2}|aero|arpa|biz|com|coop|edu|gov|info|int|jobs|mil|museum|name|nato|net|org|pro|travel)|(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]))(\/[a-z0-9_\-\.~]+)*(\/([a-z0-9_\-\.]*)(\?[a-z0-9+_\-\.%=&]*)?)?(#[a-z][a-z0-9_]*)?$/ .test(url);
}
//匹配身份证号
function checkIdNo(idNo){
	return /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/.test(idNo);
}
//匹配传真
function checkFax(fax){
	return   /^(\d{3,4}-)?\d{7,8}$/.test(fax);
}
//匹配邮编
function checkMailCode(mailcode){
	return /^[1-9][0-9]{5}$/.test(mailcode);
}
//匹配企业编号
function checkIdCard(idCard){
	return /^\d{15}$/.test(idCard);
}
//匹配空格
function checkSpace(val){
	return val.length!=val.replace(" ","").length;
}
//无值返回true
function isNullVal(val){
	return val==""||val==undefined||val==null;
}
//禁止滚动
var isRoll = 1;
function stopRoll(){
	if(document.addEventListener){    
		document.addEventListener('DOMMouseScroll',scrollFunc,false);    
	}//W3C    
	window.onmousewheel=document.onmousewheel=scrollFunc;//IE/Opera/Chrome 
	function scrollFunc(e){
		if (!isRoll) {
			// 阻止浏览器滚动
			e = e || window.event;
		
			if (e.preventDefault)
				e.preventDefault();
			else
				e.returnValue = false;
		}
	}
}
/*将dom滚动至可视范围(兼容)	*/
function showDom(id){
	var cur_bro = judgeBrowser();
	if(cur_bro=="ie"){
		 document.getElementById(id).scrollIntoView(false);
	}
	if(cur_bro=="chrome"){
		 document.getElementById(id).scrollIntoViewIfNeeded(true);
	}
};
//删除当前页面历史记录的跳转
function unBackedLoad(url){
	 window.location.replace(url);
     window.location.href=url;
}

function updateProvince(){
	var len=area_array.length;
	var innerhtml='';
	for(var i=0;i<len;i++){
		if(area_array[i]==undefined){
			continue;
		}
		innerhtml+='<option value="' + area_array[i] + '" key=' + i + '>' + area_array[i] + '</option>';
	}
	$("#province").html(innerhtml);
}
function updateCity(){
	//根据所选择的省份更新可供选择的城市
	$("#province").on("change",function(){
		var key=$(this).find("option:selected").attr("key");
		var innerhtml='<option value="请选择" selected="selected">请选择</option>';
		$("#area").html(innerhtml).next().html('请选择<span class="col-arrow_down"><img src="/showimg?url=images/icon-images/arrow_down_gray.png" /></span>');
		if(sub_array[key]==undefined){
			$("#city").html(innerhtml).next().html('请选择<span class="col-arrow_down"><img src="/showimg?url=images/icon-images/arrow_down_gray.png" /></span>');
			$("#area_hide").show();
			return ;
		}
		$("#area_hide").show();
		if(sub_arr[key*100+1]==undefined){
			//直辖市取消显示第三个框
			$("#area_hide").hide();
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
		$("#city").html(innerhtml).next().html('请选择<span class="col-arrow_down"><img src="/showimg?url=images/icon-images/arrow_down_gray.png" /></span>');
	});
}
function updateArea(){
	//根据所选择的城市更新可供选择的区块
	$("#city").on("change",function(){
		var key=$(this).find("option:selected").attr("key");
		if(sub_arr[key]==undefined){
			//直辖市没有第三级区划
			innerhtml+='<option value="' + $(this).val() + '"  selected="selected">' + $(this).val() + '</option>';
			$("#area").html(innerhtml).next().html($(this).val() + '<span class="col-arrow_down"><img src="/showimg?url=images/icon-images/arrow_down_gray.png" /></span>');
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
		$("#area").html(innerhtml).next().html('请选择<span class="col-arrow_down"><img src="/showimg?url=images/icon-images/arrow_down_gray.png" /></span>');
	});
}
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
function setInitArea(province,city,dis){
	$("#province option").each(function(){
		if($(this).attr("value")==$.trim(province)){
			$(this).attr("selected","selected");
		}
	});
	$("#province").change();
	$("#city option").each(function(){
		if($(this).attr("value")==$.trim(city)){
			$(this).attr("selected","selected");
		}
	});
	$("#city").change();
	$("#area option").each(function(){
		if($.trim($(this).attr("value"))==$.trim(dis)){
			$(this).attr("selected","selected");
		}
	});
}

function checkPassStrong(pass){
	var pass_lev = 0;
	if(/[a-z]/g.test(pass))
		pass_lev++;
	if(/[0-9]/g.test(pass))
		pass_lev++;
	if(/(.[^a-z0-9])/g.test(pass))
		pass_lev++;
	if(pass.length>10){
		pass_lev++;
	}
	if (pass.length<6||pass.length>16)
		pass_lev = 0;
	var _color1="#ccd1d9",_color2="#ccd1d9",_color3="#ccd1d9";
	switch(pass_lev){
		case 0:_color1="#ccd1d9";_color2="#ccd1d9";_color3="#ccd1d9";break;
		case 1:_color1="#f1e000";_color2="#ccd1d9";_color3="#ccd1d9";break;
		case 2:_color1="#f1e000";_color2="#f38f00";_color3="#ccd1d9";break;
		default:_color1="#f1e000";_color2="#f38f00";_color3="#cd0400";
	}
	
	$(".pass-strong1")[0].style.background = _color1;
	$(".pass-strong2")[0].style.background = _color2;
	$(".pass-strong3")[0].style.background = _color3;
}

//弹出loading框
function toggleLoading(){
	if($(".loading-img").size()>0){
		$(".loading-img").remove();
	}else{
		$("body").append("<img class='loading-img' src='/showimg?url=images/icon-images/loading.gif'>");
	}
}
//表单去空格的方法
function trimForm(fname){
	$("form[name="+fname+"] input,form[name="+fname+"] select,form[name="+fname+"] textarea").each(function(){
		$(this).val($.trim($(this).val()));
	});
}