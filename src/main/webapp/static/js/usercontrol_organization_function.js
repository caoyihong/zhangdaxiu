$(document).ready(function(){

	$(".usercontrol-checkform").on("submit",function(){

		//个人中心_普通用户失去焦点
		function control_normal_formblur(){
			$(".usualEmail").blur(function(){
				checkControl_normal_Email();
			});
			$(".realname").blur(function(){
				checkControl_normal_realname();
			});
			$(".idNo").blur(function(){
				checkControl_normal_idNo();
			});
		}
		function  checkControl_normal_Email(){
			var email = $.trim($(".usualEmail").val());
			if(email==""){
				return true;
			}
			if(!checkEmail(email)){
				$(".usualEmail-err").text("邮箱格式错误!");
				addWarn_border(".usualEmail",1);
				return false;
			}else{
				$(".usualEmail-err").text("");
				addWarn_border(".usualEmail",0);
				return true;
			}
		}
		function  checkControl_normal_realname(){
			var realname = $.trim($(".realname").val());
			if(realname==""){
				return true;
			}
			if(!checkWords(realname)){
				$(".realname-err").text("姓名格式错误!");
				addWarn_border(".realname",1);
				return false;
			}else{
				$(".realname-err").text("");
				addWarn_border(".realname",0);
				return true;
			}
		}
		function checkControl_normal_idNo(){
			var idNo = $.trim($(".idNo").val());
			if(idNo==""){
				return true;
			}
			if(!checkIdNo(idNo)){
				$(".idNo-err").text("身份证格式错误!");
				addWarn_border(".idNo",1);
				return false;
			}else{
				$(".idNo-err").text("身份证号码一经填写无法修改,请慎重填写!");
				addWarn_border(".idNo",0);
				return true;
			}
		}
		
		var isEmailOk = checkControl_normal_Email();
		var isRealNameOk = checkControl_normal_realname();
		var isIdNoOk = checkControl_normal_idNo();
		var f = false;
		$(this).find(".control-input").each(function(){
			if($(this).val()!="" && $(this).val() != "请选择" ){
				f = true;
			}
		});
		if(!f){
			$(this).find(".detail-ad-err").text("您当前没有对资料进行任何修改!");
			return false;
		}else{
			$(this).find(".detail-ad-err").text("");
		}
		showDom("maodian");
		return isEmailOk&&isRealNameOk&&isIdNoOk;
	});
});