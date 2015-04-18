$(function(){
	regist_submit_form();
	login_formblur();//登录失去焦点
	//登录返回错误信息
	login_err();
	control_normaluser();
	control_normal_formblur();//普通用户失去焦点
	checkModifyPass_Blur();//修改密码失去焦点
	control_modify();
	
	addJudgeEvent();//评价框事件
	showExtraImg();
	formWithoutModi_Listen();
	//lawfirmappoint();
});
function regist_submit_form(){//注册表单提交事件
	$(".normalUser_sub").click(function(){//普通用户

		//校验方法
		if (checkRegistForm("normal-form")) {
			$(".normalUser_sub").unbind();
			//提交方法
			$.ajax({
				url:"/normalsubmit ",
				type:"post",
				async:false,
				data:{
					formType:0,
					name:$.trim($(".normal-form input[name=name]").val()),
					phone:$.trim($(".normal-form input[name=phone]").val()),
					park:$.trim($(".normal-form select[name=park]").val()),
					password:$.trim($(".normal-form input[name=password]").val()),
					confirmpass:$.trim($(".normal-form input[name=confirmpass]").val()),
					phonecode:$.trim($(".normal-form input[name=phonecode]").val()),
					phoneback:phoneback,
					formToken:$("#formToken").val()
				},
				success:function(data){
					if(data=="success"){
						//提交成功跳抓转
						goto_suc();
						$(".login-name").text($.trim($(".normal-form input[name=name]").val()));
					}
				}
			});
			
		}	
	});
	$(".bridgeUser_sub").click(function(){//中介用户
		
		//校验方法
		if (checkRegistForm("bridge-form")) {
			$(".bridgeUser_sub").unbind();
			//提交方法
			$.ajax({
				url:"/agencysubmit",
				type:"post",
				async:false,
				data:{
					name:$.trim($(".bridge-form input[name=name]").val()),
					phone:$.trim($(".bridge-form input[name=phone]").val()),
					park:$.trim($(".normal-form select[name=park]").val()),
					password:$.trim($(".bridge-form input[name=password]").val()),
					confirmpass:$.trim($(".bridge-form input[name=confirmpass]").val()),
					phonecode:$.trim($(".bridge-form input[name=phonecode]").val()),
					identity:$("#identity").val(),
					phoneback:phoneback,
					formToken:$("#formToken").val()
				},
				success:function(data){
					if(data=="success"){
						//提交成功跳抓转
						goto_suc();
						//获取用户名 改变按钮
						$(".login-name").text($.trim($(".normal-form input[name=name]").val()));
						$(".pub_need").text("立即查询需求");
						$(".pub_need").attr("href","/work/addtask");
						$(".pub_item").text("关联中介机构");
						$(".pub_item").attr("href","/work/addtask");
					}
				}
			});
		}
	
	});
	$(".companyUser_sub").click(function(){
		//校验方法
		if (checkRegistForm("company-form")) {
			$(".companyUser_sub").unbind();
				//提交方法
			$.ajax({
				url:"/normalsubmit ",
				type:"post",
				async:false,
				data:{
					formType:1,
					name:$.trim($(".company-form input[name=name]").val()),
					phone:$.trim($(".company-form input[name=phone]").val()),
					park:$.trim($(".normal-form select[name=park]").val()),
					password:$.trim($(".company-form input[name=password]").val()),
					confirmpass:$.trim($(".company-form input[name=confirmpass]").val()),
					phonecode:$.trim($(".company-form input[name=phonecode]").val()),
					phoneback:phoneback,
					industry:$.trim($(".company-form input[name=industry]").val()),
					formToken:$("#formToken").val()
				},
				success:function(data){
					if(data=="success"){
						//提交成功跳抓转
						goto_suc();
						$(".login-name").text($.trim($(".normal-form input[name=name]").val()));
					}
				}
			});
		}
	
	});
	$(".check-accept").click(function(){
		$(".accept-err").text("");
	});
	$(".check-phone").focus(function(){//手机号获取焦点取消错误提示
		$(this).removeClass("warn-border");
		$(this).parent().parent().next().text("");
	});
	$(".check-name,.check-name-company").blur(function(){//用户名失去焦点检验是否被注册过
		var $this = $(this);
		var name = $.trim($(this).val());
		var formname = $("form:visible").attr("class");
		var type = formname=="bridge-form"?2:1;
		if($this.hasClass("check-name")){
			if(! username_blur(name,"."+formname)){
				return false;
			};
		}
		if($this.hasClass("check-name-company")){
			if(! companyuserBlur(name,"."+formname)){
				return false;
			};
		}
		
		if(name!=""){
			$.ajax({
				url:"/checkuserbyname ",
				type:"post",
				data:{
					name:name,
					type:type,
				},
				success:function(data){
					if(data=="error"){
						$this.addClass("warn-border");
						$this.attr("name_registed","true");
						$this.parent().parent().next().text("该用户名已经被注册过!");
					}else{
						$this.removeClass("warn-border");
						$this.attr("name_registed","false");
						$this.parent().parent().next().text("");
					}
				}
			});
		}
		
	});
	
	$(".check-phone").blur(function(){//手机号失去焦点检验是否被注册过
		var $this = $(this);
		var phone = $.trim($(this).val());
		var formname = $("form:visible").attr("class");
		var type = formname=="bridge-form"?2:1;
		if (type == 1) {
			type = formname=="company-form"?1:0;
		}
		if(phone!=""&&/^1\d{10}$/.test(phone)){//手机号合法才去查询
			$.ajax({
				url:"/checkuserbyphone",
				type:"post",
				data:{
					phone:phone,
					type:type,
				},
				success:function(data){
					if(data=="error"){
						$this.addClass("warn-border");
						$this.attr("phone_registed","true");
						$this.parent().parent().next().text("该手机号已经被注册过!");
					}else{
						$this.attr("phone_registed","false");
						$this.parent().parent().next().text("");
					}
				}
			});
		}
		
	});
}
function goto_suc(){
		//切换步骤指示
		$(".step-img>img").attr("src","/showimg?url=images/icon-images/step-sign1.png");
		$(".formbox").removeClass("visible");
		$(".registcomplete").addClass("visible");
		$(".role-box").css("display","none");
}
//校验注册表单的方法

function checkRegistForm(formSign){
	formSign = "."+formSign;
	//取值
	var username = $(formSign+" .check-name").val(),user_checked = username==undefined?true:false;
	var companyname = $(formSign+" .check-name-company").val(),company_user_checked = companyname==undefined?true:false;
	var phNo = $(formSign+" .check-phone").val(),phNo_checked = phNo==undefined?true:false;
	var code = $(formSign+" .check-code").val(),code_checked = code==undefined?true:false;
	var pass = $(formSign+" .check-pass").val(),pass_checked = pass==undefined?true:false;
	var repeatPass = $(formSign+" .check-passrepeat").val(),repeatPass_checked =repeatPass==undefined?true:false;
	var accept = $(formSign+" .check-accept:checked").val()==undefined?0:1,accept_checked = accept==undefined?true:false;
	var workroad = $(formSign+" .check-workroad").val() ,workroad_checked = workroad==undefined?true:false;
	//校验
	if(username!=undefined){
		if(""==username){//用户名
			$(formSign+" .name-err").text("请输入用户名!");
			addWarn_border(formSign+" .check-name",1);
		}else{
			if(checkWords(username)){
				if(username.length<2||username.length>7){
					$(formSign+" .name-err").text("纯汉字用户名长度应在2到7之间!");
					addWarn_border(formSign+" .check-name",1);
				}else{
					if($(formSign+" .check-name").attr("name_registed")=="true"){
						$(formSign+" .name-err").text("该用户名已经被注册过!");
						addWarn_border(formSign+" .check-name",1);
					}else{
						user_checked = true;
						addWarn_border(formSign+" .check-name",0);
						$(formSign+" .name-err").text("");
					}
				}
			}else{
				if(username.replace(/[^\x00-\xff]/g,"aa").length>15||username.replace(/[^\x00-\xff]/g,"aa").length<6){
					$(formSign+" .name-err").text("非纯汉字的用户名请输入6到15个字符!");
					addWarn_border(formSign+" .check-name",1);
				}else{
					if(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(username)){
							user_checked = true;
							addWarn_border(formSign+" .check-name",0);
							$(formSign+" .name-err").text("");
					}else{
						if(!/^[\u4E00-\u9FA5A-Za-z_][\u4E00-\u9FA5A-Za-z0-9_]+$/.test(username)){
							$(formSign+" .name-err").text("用户名只可包含汉字,下划线,字母或数字,且不能以数字开头!");
							addWarn_border(formSign+" .check-name",1);
						}else{
							if($(formSign+" .check-name").attr("name_registed")=="true"){
								$(formSign+" .name-err").text("该用户名已经被注册过!");
								addWarn_border(formSign+" .check-name",1);
							}else{
								user_checked = true;
								addWarn_border(formSign+" .check-name",0);
								$(formSign+" .name-err").text("");
							}
						}
					}
				}
			}
			
		}
	}
	if(companyname!=undefined){
		if(""==companyname){//企业名称
			$(formSign+" .name-err").text("请输入企业名称!");
			addWarn_border(formSign+" .check-name-company",1);
		}else{
			if(checkWords(companyname)){
				if(companyname.length<2||companyname.length>20){
					$(formSign+" .name-err").text("企业名称应在2到20之间!");
					addWarn_border(formSign+" .check-name-company",1);
				}else{
					if($(formSign+" .check-name-company").attr("name_registed")=="true"){
						$(formSign+" .name-err").text("该用户名已经被注册过!");
						addWarn_border(formSign+" .check-name-company",1);
					}else{
						company_user_checked = true;
						addWarn_border(formSign+" .check-name-company",0);
						$(formSign+" .name-err").text("");
					}
				}
			}
		}
	}

	if(""==phNo){//手机号
		$(formSign+" .phone-err").text("请输入手机号!");
		addWarn_border(formSign+" .check-phone",1);
	}else{
		if(!/^1\d{10}$/.test(phNo)){
			$(formSign+" .phone-err").text("请输入正确的手机号!");
			addWarn_border(formSign+" .check-phone",1);
		}else{
			if($(formSign+" .check-phone").attr("phone_registed")=="true"){
				$(formSign+" .phone-err").text("该手机号已经被注册过!");
				addWarn_border(formSign+" .check-phone",1);
			}else{
				phNo_checked = true;
				addWarn_border(formSign+" .check-phone",0);
				$(formSign+" .phone-err").text("");
			}
			
		}
	}
	if(""==code){//手机验证码
		$(formSign+" .code-err").text("请输入验证码!");
		addWarn_border(formSign+" .check-code",1);
	}else{
		//比对验证码是否正确
		if(code!=phoneback){
			$(formSign+" .code-err").text("验证码错误!");
			addWarn_border(formSign+" .check-code",1);
		}else{
			code_checked = true;
			$(formSign+" .code-err").text("");
			addWarn_border(formSign+" .check-code",0);
		}	
	}
	if(""==pass){//密码
		$(formSign+" .pass-err").text("请输入密码!");
		addWarn_border(formSign+" .check-pass",1);
	}else{
		if(pass.length>20||pass.length<6){
			$(formSign+" .pass-err").text("请输入6到20位之间的密码!");
			addWarn_border(formSign+" .check-pass",1);
		}else{
			pass_checked =true;
			addWarn_border(formSign+" .check-pass",0);
			$(formSign+" .pass-err").text("");
		}
	}
	if(""==repeatPass){//重复密码
		$(formSign+" .passrepeat-err").text("请输入重复密码!");
		addWarn_border(formSign+" .check-passrepeat",1);
	}else{
		if(pass!=repeatPass){
			$(formSign+" .passrepeat-err").text("两次密码输入不一致!");
			addWarn_border(formSign+" .check-passrepeat",1);
		}else{
			repeatPass_checked = true;
			addWarn_border(formSign+" .check-passrepeat",0);
			$(formSign+" .passrepeat-err").text("");
		}
	}
	if(accept==0){//接受协议		
		$(formSign+" .accept-err").text("您未同意本网站协议!");
		//addWarn_border(formSign+" .check-accept",1);
	}else{
		$(formSign+" .accept-err").text("");
		accept_checked = true;
	}
	if(workroad==""){
		$(formSign+" .workroad-err").text("请选择所属行业!");
			addWarn_border(formSign+" .check-workroad",1);
	}else{
		$(formSign+" .workroad-err").text("");
			addWarn_border(formSign+" .check-workroad",0);
			workroad_checked = true;
	}
	return user_checked&&phNo_checked&&code_checked&&pass_checked&&repeatPass_checked&&accept_checked&&workroad_checked&&company_user_checked ;
	
}
//失去焦点时的校验
function checkRegist_blur(){
	$(".normal-form input").blur(function(){
		checkRegistForm("normal-form");
	});
	$(".bridge-form input").blur(function(){
		checkRegistForm("bridge-form");
	});
	$(".company-form input").blur(function(){
		checkRegistForm("company-form");
	});
}
//用户名失去焦点
function username_blur(username,formSign){
	if(checkWords(username)){
		if(username.length<2||username.length>7){
			$(formSign+" .name-err").text("纯汉字用户名长度应在2到7之间!");
			addWarn_border(formSign+" .check-name",1);
			return false;
		}
	}else{
		if(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(username)){
			addWarn_border(formSign+" .check-name",0);
			$(formSign+" .name-err").text("");
		}else{
			if(username.replace(/[^\x00-\xff]/g,"aa").length>15||username.replace(/[^\x00-\xff]/g,"aa").length<6){
				$(formSign+" .name-err").text("非纯汉字用户名请输入6到15个字符!");
				addWarn_border(formSign+" .check-name",1);
				return false;
			}else{
				if(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(username)){
						addWarn_border(formSign+" .check-name",0);
						$(formSign+" .name-err").text("");
				}else{
					if(!/^[\u4E00-\u9FA5A-Za-z_][\u4E00-\u9FA5A-Za-z0-9_]+$/.test(username)){
						$(formSign+" .name-err").text("用户名只可包含汉字,下划线,字母或数字,且不能以数字开头!");
						addWarn_border(formSign+" .check-name",1);
						return false;
					}
				}
			}
		}
		
	}
	return true;
}
function companyuserBlur(companyname,formSign){
	if(""==companyname){//企业名称
		$(formSign+" .name-err").text("请输入企业名称!");
		addWarn_border(formSign+" .check-name-company",1);
		return false;
	}else{
		if(checkWords(companyname)){
			if(companyname.length<2||companyname.length>20){
				$(formSign+" .name-err").text("企业名称应在2到20之间!");
				addWarn_border(formSign+" .check-name-company",1);
				return false;
			}else{
				if($(formSign+" .check-name-company").attr("name_registed")=="true"){
					$(formSign+" .name-err").text("该用户名已经被注册过!");
					addWarn_border(formSign+" .check-name-company",1);
					return false;
				}else{
					addWarn_border(formSign+" .check-name-company",0);
					$(formSign+" .name-err").text("");
				}
			}
		}else{
			$(formSign+" .name-err").text("请输入中文企业名称!");
			addWarn_border(formSign+" .check-name-company",1);
			return false;
		}
	}
	return true;
}
//input修改警示框
function addWarn_border(classname,flag){
	if(flag)
		$(classname).addClass("warn-border");
	else
		$(classname).removeClass("warn-border");
}
var phoneback;//后台反回的验证码


//登录校验
function check_loginForm(){
	//校验账号
	var loginname = $.trim($(".check-loginname").val());
	var loginpass = $.trim($(".check-loginpass").val());
	if(loginname==""){
		$(".loginname-err").text("请输入用户名!");
		$(".check-loginname").addClass("warn-border");
		return false;
	}else{
		if(!/^1\d{10}$/.test(loginname)){//不匹配手机
			if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(loginname)){//不匹配邮箱
				if(!/^[\u4E00-\u9FA5A-Za-z_][\u4E00-\u9FA5A-Za-z0-9_]+$/.test(loginname)){//普通用户名不匹配
					$(".loginname-err").text("用户名格式错误!");
					$(".check-loginname").addClass("warn-border");
					return false;
				}else{
					if(loginname.replace(/[^\x00-\xff]/g,"aa").length>20||loginname.replace(/[^\x00-\xff]/g,"aa").length<4){
						$(".loginname-err").text("账号长度错误!");
						$(".check-loginname").addClass("warn-border");
						return false;
					}else{
//						if($(".check-loginname").attr("loginname_registed")=="true"){
//							$(".loginname-err").text("当前身份无此用户!");
//							$(".check-loginname").addClass("warn-border");
//							return false;
//						}else{
							$(".loginname-err").text("");
							$(".check-loginname").removeClass("warn-border");
//						}
					}		
				}
			}else{
//				if($(".check-loginname").attr("loginname_registed")=="true"){
//						$(".loginname-err").text("当前身份无此用户!");
//						$(".check-loginname").addClass("warn-border");
//						return false;
//					}else{
						$(".loginname-err").text("");
						$(".check-loginname").removeClass("warn-border");
//					}
			}
		}else{
//			if($(".check-loginname").attr("loginname_registed")=="true"){
//						$(".loginname-err").text("当前身份无此用户!");
//						$(".check-loginname").addClass("warn-border");
//						return false;
//					}else{
						$(".loginname-err").text("");
						$(".check-loginname").removeClass("warn-border");
//			}
		}
	}
	//校验密码
	if (loginpass==""){
		$(".loginpass-err").text("请输入密码!");
		$(".check-loginpass").addClass("warn-border");
		return false;
	}else{
		if(loginpass.length<6||loginpass.length>20){
			$(".loginpass-err").text("密码长度错误!");
			$(".check-loginpass").addClass("warn-border");
			return false;
		}else{
			$(".loginpass-err").text("");
			$(".check-loginpass").removeClass("warn-border");
		}
	}
	return true;
}
//登录失去焦点事件
function login_formblur(){
	$(".check-loginname,.check-loginpass").focus(function(){
		$(this).parent().parent().next().text("");
		$(this).removeClass("warn-border");
	});
	$(".check-loginname").blur(function(){
		var loginname = $.trim($(this).val());
		if(loginname==""){
			$(".loginname-err").text("请输入用户名!");
			$(".check-loginname").addClass("warn-border");
		}else{
			if(!/^1\d{10}$/.test(loginname)){//不匹配手机
				if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(loginname)){//不匹配邮箱
					if(!/^[\u4E00-\u9FA5A-Za-z_][\u4E00-\u9FA5A-Za-z0-9_]+$/.test(loginname)){//普通用户名不匹配
						$(".loginname-err").text("用户名格式错误!");
						$(".check-loginname").addClass("warn-border");
					}else{

						$(".loginname-err").text("");
						$(".check-loginname").removeClass("warn-border");
//						check_loginRegisted(loginname);
					}
				}else{
					$(".loginname-err").text("");
					$(".check-loginname").removeClass("warn-border");
//					check_loginRegisted(loginname);
				}
			}else{
				$(".loginname-err").text("");
				$(".check-loginname").removeClass("warn-border");
//				check_loginRegisted(loginname);
			}
			
		}
	});
	$(".check-loginpass").blur(function(){
		var loginpass= $.trim($(this).val());
		if (loginpass==""){
			$(".loginpass-err").text("请输入密码!");
			$(".check-loginpass").addClass("warn-border");
		}else{
			if(loginpass.length<6||loginpass.length>20){
				$(".loginpass-err").text("密码长度错误!");
				$(".check-loginpass").addClass("warn-border");
			}else{
				$(".loginpass-err").text("");
				$(".check-loginpass").removeClass("warn-border");
			}
		}
	});
}
//登陆判断当前用户是否存在
function check_loginRegisted(username){
	var type  = $("input[name=logintype]:checked").val();
	$.ajax({
		url:"/checkuserexist ",
		type:"post",
		data:{
			value:username,
			type:type,
		},
		success:function(data){
			if(data=="success"){
				$(".check-loginname").addClass("warn-border");
				$(".check-loginname").attr("loginname_registed","true");
				$(".loginname-err").text("当前身份无此用户!");
			}else{
				$(".check-loginname").attr("loginname_registed","false");
				$(".loginname-err").text("");
			}
		}
	});
}
//登录信息返回
function login_err(){
	var hiddenMsg = $("#hiddenErrMsg").val();
	if(hiddenMsg!=""){
		$(".check-loginpass").addClass("warn-border");
		$(".loginpass-err").text(hiddenMsg);
	}
}
//事务所法务预约筛选条件
function lawfirmappoint(){
	$(".condition").click(function(){

		var $this =$(this);
		var clsName,con_name = $this.find("a").text();
		if($this.parent().hasClass("contenttwo")){
			clsName = "con-checkedcone";
			$("."+clsName).remove();
			var str = "<div class='condition-checked "+clsName+"'><span>"+con_name+"</span><span class='iconfont icon-del condi-del' title='删除此条件'></span></div></div>";

			$(".lawfirm-appoint-condition-checked").prepend(str);
		}else{
			clsName = "con-checkedctwo";
			$("."+clsName).remove();
		var str = "<div class='condition-checked "+clsName+"'><span>"+con_name+"</span><span class='iconfont icon-del condi-del' title='删除此条件'></span></div></div>";
		$(".lawfirm-appoint-condition-checked").append(str);
		}	

	});
}
//机构检索跳转
function intermediarySearch(){
	var province="",city="",services="",agencyType="";
	$(".searchthis").click(function(){
		var $this = $(this);
		$this.parent().find(".searchthis").removeClass("searchthis-active");
		$this.addClass("searchthis-active");
		$(".searchthis-active").each(function(){
			var conditionBox = $(this).parent();
			var val = $.trim($(this).attr("value"));
			if(conditionBox.hasClass("provinces")){
				province = val;
			}
			if(conditionBox.hasClass("citys")){
				city = val;
			}
			if(conditionBox.hasClass("servicestype")){
				services = val;
			}
			if(conditionBox.hasClass("mechanism")){
				agencyType = val;
			}
		});
		if($this.parent().hasClass("provinces")){
			city="";
		}
		window.location.href=getRealAddress()+"?province="+province+"&city="+city+"&services="+services+"&agencyType="+agencyType;
	});
} 

/*检索框初始化*/
function initSearchbox(){//null 无条件
	//加载省市数据
	loadAreaData();
	var province = getQueryString("province");
	var city = getQueryString("city");
	var services = getQueryString("services");
	var agencyType = getQueryString("agencyType");
	if(province!=null&&province!=""){
		var $checked_province = $(".provinces>.searchthis[value="+province+"]");
		$checked_province.addClass("searchthis-active");
		//加载区/市
		loadCityData($checked_province.attr("procode"));
	}else{
		$(".provinces>.searchthis:first").addClass("searchthis-active");
		$(".search-row-city").addClass("unvisible");
	}
	if(city!=null&&city!=""){
		$(".citys>.searchthis[value="+city+"]").addClass("searchthis-active");
	}else{
		$(".citys>.searchthis:first").addClass("searchthis-active");
	}
	if(services!=null&&services!=""){
		$(".servicestype>.searchthis[value="+services+"]").addClass("searchthis-active");
	}else{
		$(".servicestype>.searchthis:first").addClass("searchthis-active");
	}
	if(agencyType!=null&&agencyType!=""){
		$(".mechanism>.searchthis[value="+agencyType+"]").addClass("searchthis-active");
	}else{
		$(".mechanism>.searchthis:first").addClass("searchthis-active");
	}
	intermediarySearch();
}
//获取参数
function getQueryString(paramname){
     var reg = new RegExp("(^|&)"+ paramname +"=([^&]*)(&|$)");
     var param = window.location.search.substr(1).match(reg);
     if(param!=null)return  decodeURI(param[2]); return null;
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
//加载省数据
function loadAreaData(){
	var $province = $(".provinces");
	$province.empty();
	var proval= "<a href='javascript:void(0)' class='searchthis' value=''>全部</a>";
	$.each(area_array,function(index,data){
		if(data!="请选择"&&data!=undefined&&data!=""){
			proval+= "<a href='javascript:void(0)' class='searchthis' procode='"+index+"' value='"+data+"'>"+data+"</a>";
		}
	});
	$province.append(proval);
}
////加载区市数据
function loadCityData(procode){
	console.log(procode);
	var $city = $(".citys");
	$city.empty();
	var cityval= "<a href='javascript:void(0)' class='searchthis' value=''>全部</a>";
	$.each(sub_array[procode],function(index,data){
		if(data!="请选择"&&data!=undefined&&data!=""){
			cityval+= "<a href='javascript:void(0)' class='searchthis' citycode='"+index+"' value='"+data+"'>"+data+"</a>";
		}
	});
	$city.append(cityval);
}
/*法务预约检索框初始化*/
function initLawerAppointSearchbox(){//null 无条件
	var services = getQueryString("services");//服务类型
	var agencyType = getQueryString("agencyType");//专业类别
	var workType = getQueryString("workType");//工作年限
	if(services!=null&&services!=""){
		$(".servicestype>.condition>a[value="+services+"]").addClass("condition-active");
	}else{
		$(".servicestype>.condition>a:first").addClass("condition-active");
	}
	if(agencyType!=null&&agencyType!=""){
		$(".mechanism>.condition>a[value="+agencyType+"]").addClass("condition-active");
	}else{
		$(".mechanism>.condition>a:first").addClass("condition-active");
	}
	if(workType!=null&&workType!=""){
		$(".workType>.condition>a[value="+workType+"]").addClass("condition-active");
	}else{
		$(".workType>.condition>a:first").addClass("condition-active");
	}
	chooseCondition();
}
//法务预约检索条件跳转事件
function chooseCondition(){
	var services="",agencyType="",workType="",begindate = "",enddate = "";
	$(".condition").click(function(){
		var $this = $(this);
		$this.parent().find(".condition>a").removeClass("condition-active");
		$this.find("a").addClass("condition-active");
		$(".condition-active").each(function(){
			var conditionBox = $(this).parent().parent();
			var val = $.trim($(this).attr("value"));
			if(conditionBox.hasClass("servicestype")){
				services = val;
			}
			if(conditionBox.hasClass("mechanism")){
				agencyType = val;
			}
			if(conditionBox.hasClass("workType")){
				workType = val;
			}
		});
		begindate = $(".begindate").val();enddate = $(".enddate").val();
		window.location.href=getRealAddress()+"?services="+services+"&agencyType="+agencyType+"&workType="+workType+"&begindate="+begindate+"&enddate="+enddate;
	});
}
//律师展示筛选条件
function initShaiXuan(){
	var orderType = getQueryString("orderType");//
	if(orderType!=null&&orderType!=""){
		$(".screening-content[value="+orderType+"]").addClass("screening-content-active");
	}else{
		$(".screening-content[value=全部]").addClass("screening-content-active");
	}
}
//分页初始化
function initPaginator(domid){
	var currentPage,totalPages;
	 if (arguments.length == 1){
		 currentPage =$("#currentPage").val();
		 totalPages = $("#totalPages").val();
	 }else{
		 currentPage = arguments[1];
		 totalPages = arguments[2];
	 }
	var options = {
			bootstrapMajorVersion : 3,
			currentPage : $.trim(currentPage),
			totalPages : $.trim(totalPages),
			numberOfPages :8 ,
			itemTexts : function(type, page, current) {
				switch (type) {
				case "first":
					return "首页";
				case "prev":
					return "前一页";
				case "next":
					return "下一页";
				case "last":
					return "尾页";
				case "page":
					return page;
				}
			},
			pageUrl : function(type, page, current) {	
					var originalUrl = window.location.href;
					if(originalUrl.indexOf("currentPage")!=-1){
						return changeParmOfUrl("currentPage",page);
					}else{
						return originalUrl+(originalUrl.indexOf("?")!=-1?"":"?")+"&currentPage="+page;
					}		
			}
		}
		$('#'+domid).bootstrapPaginator(options);
}

//控件id,要执行的函数名,当前页
function loadPageByParam(domId,buildMethodName,page){
	eval(buildMethodName+"('"+domId+"','"+buildMethodName+"',"+page+")");
}
//拼装查询函数例子

//拼装查询函数例子
function agencyeStore(domId,buildMethodName,page){
	var province= $("#province00").val();
	var city= $("#city00").val();
	var district= $("#area00").val();
	if(province=="请选择"){
		province=null;
	}
	if(city=="请选择"){
		city=null;
	}
	if(district=="请选择"){
		district=null;
	}
	toggleLoading();
	$.ajax({
		url:"/users/agencye/AjaxagencyEmployeeStores?currentPage="+page,
		type:"get",
		async:false,
		dataType:"json",
		data : {
			name : $.trim($("#agencyename").val()),
			agencyname : $.trim($("#employee_agencyname").val()),
			province :province,
			city : city,
			district : district
		},
		success:function(data){
			//alert($(data.list).size());
			setTimeout(function(){toggleLoading();},200);
			
			if($(data.list).size()==0){
				var innerHtml = "<div class='tips_empty'><span class='tips-content'>没有您想要的结果，换个条件试试吧</span></div>";
			}else{
				var agencye = data.list;
				var innerHtml = "<ul class='itemlist-list clearfix'>";
				for ( var i = 0; i < agencye.length; i++) {
					//alert(agencye[i].agencyname);
					innerHtml += "<li class='item_user clearfix' userid='"+agencye[i].id+"' ><div class='photo'><img src='/showimg?url="+agencye[i].head+
					"' href='/agency/"+agencye[i].agencyid+"/agencye/"+agencye[i].id+"'/></div><a href='/agency/"+agencye[i].agencyid+"/agencye/"+agencye[i].id+
					"' class='content'><div class='name'>"+agencye[i].name+"</div>";
					if(agencye[i].status!=2){
						innerHtml += "<div class='authentication'><img src='/showimg?url=images/icon-images/crown.png' /><span>已实名认证</span></div>";
					}
//				未实名认证
					if(agencye[i].status==2){
						innerHtml += "<div class='noauthentication'><img src='/showimg?url=images/icon-images/crown_gray.png' /><span>未实名认证</span></div>";
					}
					innerHtml +="<div class='agency'>"+agencye[i].agencyname+"</div></a><a class='cancelCollect'>取消收藏</a></li>";
					
				}
				innerHtml +="</ul>";
			}
			
			innerHtml +="<div class='paginator-bar clearfix'><ul id='appointPaginator' class='pagination'></ul></div>";
			$("#agencyeStoreList").html(innerHtml);
			initPaginator_ajax(domId,buildMethodName,page,data.pages);//拼装完成后调用
			bindClick();
		},
	});
}
//中介机构检索
function agencyStore(domId,buildMethodName,page){
	var province= $("#province01").val();
	var city= $("#city01").val();
	var district= $("#area01").val();
	if(province=="请选择"){
		province=null;
	}
	if(city=="请选择"){
		city=null;
	}
	if(district=="请选择"){
		district=null;
	}
	toggleLoading();
	$.ajax({
		url:"/users/agencye/AjaxagencyStores?currentPage="+page,
		type:"get",
		async:false,
		dataType:"json",
		data : {
			name : $.trim($("#agencyname").val()),
			province :province,
			city : city,
			district : district
		},
		success:function(data){
			//alert($(data.list).size());
			setTimeout(function(){toggleLoading();},200);
			if($(data.list).size()==0){
				var innerHtml = "<div class='tips_empty'><span class='tips-content'>没有您想要的结果，换个条件试试吧</span></div>";
			}else{
				var agency = data.list;
				var innerHtml = "<ul class='itemlist-list clearfix'>";
				for ( var i = 0; i < agency.length; i++) {
					//alert(agency[i].name);
					innerHtml +="<li class='item_agency' agencyid="+agency[i].id+"><div class='photo'>" +
							"<img src='/showimg?url="+agency[i].head+"' style='cursor:pointer;'/></div><a href='/agency/"+agency[i].id+"' class='content'>"+
							"<div class='name'>"+agency[i].name+"</div>";
					if(agency[i].status==1){
						innerHtml += "<div class='authentication'><img src='/showimg?url=images/icon-images/crown.png' /><span>已认证</span></div>";
					}
					if(agency[i].status!=1){
						innerHtml += "<div class='noauthentication'><img src='/showimg?url=images/icon-images/crown_gray.png' /><span>未认证</span></div>";
					}
					innerHtml +="<div class='area'>地址："+agency[i].address+"</div><div class='phone'>电话："+agency[i].phone+"</div></a>"+
					"<a class='cancelCollect'>取消收藏</a></li>";
				}
				innerHtml +="</ul>";
			}
			innerHtml +="<div class='paginator-bar clearfix'><ul id='appointPaginator1' class='pagination'></ul></div>";
			$("#agencyStoreList").html(innerHtml);
			initPaginator_ajax(domId,buildMethodName,page,data.pages);//拼装完成后调用
			bindClick();
		},
	});
	
//	<ul class="itemlist-list clearfix">
//   	<#list agency.list as agen>
//        <li class="item_agency" agencyid="${agen['id']}" >
//            <div class="photo">
//                <img src="/showimg?url=${agen.head}" style="cursor:pointer;"/>
//            </div>
//            <a href="/agency/${agen['id']}" class="content">
//               <div class="name">${agen["name"]}</div>
//               <div class="authentication">
//                   <img src="/showimg?url=images/icon-images/crown.png" />
//                   <span>已认证</span>
//               </div>
//               <div class="area">地址：${agen["address"]}</div>
//               <div class="phone">电话：${agen["phone"]}</div>
//            </a>
//            <a class="cancelCollect">取消收藏</a>
//        </li>
//   	</#list>
//    </ul>
}
//分页初始化ajax
function initPaginator_ajax(domid,buildMethodName,page,totalPages){
	var options = {
			bootstrapMajorVersion:3,
			currentPage : page,
			totalPages : totalPages,
			numberOfPages :8 ,
			itemTexts : function(type, page, current) {
				switch (type) {
				case "first":
					return "首页";
				case "prev":
					return "前一页";
				case "next":
					return "下一页";
				case "last":
					return "尾页";
				case "page":
					return page;
				}
			},
			onPageClicked:function(event, originalEvent, type,page){
				loadPageByParam(domid,buildMethodName,page)
			}
		}
		$('#'+domid).bootstrapPaginator(options);
}

//个人中心设置
function control_normaluser(){
	$(".tab-title").click(function(){
		if(imgSelect){
			imgSelect.cancelSelection();
		}
		var $this = $(this);
		$(".tab-title").removeClass("tab-title-active");
		$this.addClass("tab-title-active");
		$(".tab-form-box>div").removeClass("visible");
		$(".tab-form-box>div[formname="+$this.attr("target")+"]").addClass("visible");
		if($this.attr("target")=="collect-agency"){
			loadPageByParam('appointPaginator1','agencyStore',1)
		}
	});
}
//个人中心_普通用户验证
function control_normal_formCheck(){
	var isEmailOk = checkControl_normal_Email();
	var addressOk = checkControl_normal_address();
	var postcodeOk,faxOk;
	if($(".postcode").size()!=0){
		 postcodeOk = checkControl_normal_postcode();
		 faxOk  = checkControl_normal_fax();
	}
	if(!formModiFlag){
		$(".detail-ad-err").text("您当前没有对资料进行任何修改!");
		$(".self-intro-setting-form select:visible").each(function(){
			if($(this).val()!= "请选择"){
				$(".detail-ad-err").text("");
			}
		});
		return false;
	}else{
		$(".detail-ad-err").text("");
	}
	
	trimForm("trimform");
	if($(".postcode").size()!=0)
		return isEmailOk&&addressOk&&postcodeOk&&faxOk;
	else
		return isEmailOk&&addressOk;
}
//专业机构验证
function control_organi_formCheck(){
	var isEmailOk = checkControl_normal_Email();
	var orgname = checkOrginazitionname();
	var url =  checkOriginazitionUrl();
	if(!checkNoModi()){
		$(".detail-ad-err").text("您当前没有对资料进行任何修改!");
		return false;
	}else{
		$(".detail-ad-err").text("");
	}
	return isEmailOk&&orgname&&url;
}
//校验企业名称
function  checkOrginazitionname(){
	var orgname = $.trim($(".organizitionname").val());
	if(orgname==""){
		addWarn_border("organizitionname",0);
		$(".organizitionname-err").text("");
		return true;
	}
	if(checkWords(orgname)){
		if(orgname.length>20||orgname.length<2){
			addWarn_border(".organizitionname",1);
			$(".organizitionname-err").text("企业名称应在2到20个汉字之间!");
			return false;
		}
	}else{
		addWarn_border("organizitionname",1);
		$(".organizitionname-err").text("请输入合法的企业名称!");
		return false;
	}
	addWarn_border("organizitionname",0);
	$(".organizitionname-err").text("");
	return true;
}
//校验网址
function checkOriginazitionUrl(){
	var url = $.trim($(".organizitionurl").val());
	if(url==""){
		addWarn_border("organizitionurl",1);
		$(".organizitionurl-err").text("");
		return true;
	}
	if(!checkNormalUrl(url)){
		addWarn_border("organizitionurl",0);
		$(".organizitionurl-err").text("请输入正确的网址!");
		return false;
	}
	addWarn_border("organizitionurl",1);
	$(".organizitionurl-err").text("");
	return true;
}
//个人中心失去焦点
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
	$(".organizitionname").blur(function(){
		checkOrginazitionname();
	});
	$(".organizitionurl").blur(function(){
		 checkOriginazitionUrl();
	});
	$(".postcode").blur(function(){
		checkControl_normal_postcode();
	});
	$(".fax").blur(function(){
		checkControl_normal_fax();
	});
	$(".idcard").blur(function(){
		checkControl_normal_idCard();
	});
	
}
function  checkControl_normal_Email(){
	var email = $.trim($(".usualEmail").val());
	var  usertype = $("#usertype").val();
	if(email==""){
		$(".usualEmail-err").text("");
		addWarn_border(".usualEmail",0);
		return true;
	}
	if(!checkEmail(email)){
		$(".usualEmail-err").text("邮箱格式错误!");
		addWarn_border(".usualEmail",1);
		return false;
	}else{
		if(checkEmailRegist(email,usertype)){//检测邮箱是否注册
			$(".usualEmail-err").text("");
			addWarn_border(".usualEmail",0);
			return true;
		}else{
			return false;
		}
	}
}
//邮编
function  checkControl_normal_postcode(){
	var postcode = $.trim($(".postcode").val());
	var  usertype = $("#usertype").val();
	if(postcode==""){
		$(".postcode-err").text("");
		addWarn_border(".postcode",0);
		return true;
	}
	if(!checkMailCode(postcode)){
		$(".postcode-err").text("邮编格式错误!");
		addWarn_border(".postcode",1);
		return false;
	}else{
		$(".postcode-err").text("");
		addWarn_border(".postcode",0);
		return true;
	}
}
function  checkControl_normal_fax(){
	var fax = $.trim($(".fax").val());
	var  usertype = $("#usertype").val();
	if(fax==""){
		$(".fax-err").text("");
		addWarn_border(".fax",0);
		return true;
	}
	if(!checkFax(fax)){
		$(".fax-err").text("传真格式错误!");
		addWarn_border(".fax",1);
		return false;
	}else{
		$(".fax-err").text("");
		addWarn_border(".fax",0);
		return true;
	}
}
function  checkControl_normal_address(){
	var select = $(".sel-wrap:visible select");
	var flag = true;
	$.each(select,function(index,data){
		if(index==0&&$(data).val()=="请选择"){
			flag = true;
			return false;
			
		}
		
		if(index!=0&&$(data).val()=="请选择"){
				$(".address-err").text("请选择完整的地区!");
				flag = false;
		}
	});
	if(flag){
		$(".address-err").text("");
	}
	return flag;
}
function  checkControl_normal_realname(){
	var realname = $.trim($(".realname").val());
	if(realname==""){
		$(".realname-err").text("请填写真实姓名!");
		addWarn_border(".realname",1);
		return false;
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
		$(".idNo-err").text("请填写身份证号!");
		addWarn_border(".idNo",1);
		return false;
	}
	if(!checkIdNo(idNo)){
		$(".idNo-err").text("身份证格式错误!");
		addWarn_border(".idNo",1);
		return false;
	}else{
		$(".idNo-err").text("");
		addWarn_border(".idNo",0);
		return true;
	}
}
function checkControl_normal_idCard(){
	var idcard = $.trim($(".idcard").val());
	if(idcard==""){
		$(".idcard-err").text("请填写企业编号!");
		addWarn_border(".idcard",1);
		return false;
	}
	if(!checkIdCard(idcard)){
		$(".idcard-err").text("企业编号格式错误!");
		addWarn_border(".idcard",1);
		return false;
	}else{
		$(".idcard-err").text("");
		addWarn_border(".idcard",0);
		return true;
	}
}

function checkEmailRegist(email,type){//检验是否注册的ajax
	var flag  = false;
	$.ajax({
		url:"/checkuserexist ",
		type:"post",
		async:false,
		data:{
			value:email,
			type:type,
		},
		success:function(data){
			if(data=="error"){
				addWarn_border(".usualEmail",1);
				$(".usualEmail-err").text("该邮箱已经被注册过!");
				flag=false;
			}else{
				addWarn_border(".usualEmail",0);
				$(".usualEmail-err").text("");
				flag = true;
			}
		}
	});
	return flag;
}
//全部为空
function  checkNoModi(){//全部为空返回false
	var f = false;
	$(".self-intro-setting-form .control-input").each(function(){
		if($.trim($(this).val())!="" && $(this).val() != "请选择" ){
			f = true;
		}
	});
	//var ff = checkImg();
	return f;
}
function checkImg(){
	delNullFIle();
	if($(".fileinput").size()==0){
		return false;
	}else{
		return true;
	}
}

//个人中心修改密码验证
function checkModifyPass(){
	if(checkModifyOldPass()&&checkModifyNewPass()&&checkModifyRepeatPass()){
		var oldPass = $.trim( $(".oldPass").val());
		var newpass = $.trim($(".newPass").val());
		var url = $("#usertype").val()=="2"?"/users/agencye/changePassword":"/users/normal/changePassword";
		$.ajax({//提交修改密码的ajax
			url:url,
			type:"post",
			data:{
				oldPass:oldPass,
				newpass:newpass
			},
			success:function(data){
				//修改成功
				if(data=="success"){
					$(".self-pass-setting-form").html("<div class='clearfix modisucess'><img src='/showimg?url=images/icon-images/suc.png'/><div>您的密码已经修改成功！</div></div>");
				}else if(data=="frequent"){
					//一星期内不能重复修改提示
					$("*[formname=self-pass] .tab-tip-msg").html("<div class='lighting'>一周内只能修改一次密码,请不要频繁修改!</div>");
				}else {
					$(".oldPass-err").text("旧密码输入错误");
				}
				
			}
		});
	}
}
function checkModifyPass_Blur(){
	$(".oldPass").blur(function(){
		checkModifyOldPass();
	});
	$(".newPass").blur(function(){
		checkModifyNewPass();
	});
	$(".newPassRepeat").blur(function(){
		checkModifyRepeatPass();
	});
	$(".save-pass-btn").click(function(){
		checkModifyPass();
	});
	$(".newPass").keyup(function(){
		checkModifyNewPass();
		checkPassStrong($.trim($(this).val()));
	});
}

function checkModifyOldPass(){
	var oldPass = $.trim( $(".oldPass").val());
	if(oldPass==""){
		$(".oldPass-err").text("请输入旧密码!");
		$(".oldPass").addClass("warn-border");
		return false;
	}
	$(".oldPass-err").text("");
	$(".oldPass").removeClass("warn-border");
	return true;
}
function  checkModifyNewPass(){
	var newpass = $.trim($(".newPass").val());
	if(newpass==""){
		$(".newPass-err").text("请输入新密码!");
		$(".newPass").addClass("warn-border");
		return false;
	}else{
		if(newpass.length<6||newpass.length>16){
			$(".newPass-err").text("密码应在6到16位之间!");
			$(".newPass").addClass("warn-border");
			return false;
		}else{
			if(checkSpace(newpass)){
				$(".newPass-err").text("密码不能包含空格!");
				$(".newPass").addClass("warn-border");
				return false;
			}else{
				if(newpass==$.trim( $(".oldPass").val())){
					$(".newPass-err").text("新密码不能与旧密码相同!");
					$(".newPass").addClass("warn-border");
					return false;
				}
			}
		}
	}
	$(".newPass-err").text("");
	$(".newPass").removeClass("warn-border");
	return true;
}

function checkModifyRepeatPass(){
	var repeatpass = $.trim($(".newPassRepeat").val());
	if(repeatpass==""){
		$(".newPassRepeat-err").text("请输入重复密码!");
		$(".newPassRepeat").addClass("warn-border");
		return false;
	}else{
		if(repeatpass!=$.trim($(".newPass").val())){
			$(".newPassRepeat-err").text("两次密码输入不一致!");
		$(".newPassRepeat").addClass("warn-border");
		return false;
		}
	}
	$(".newPassRepeat-err").text("");
	$(".newPassRepeat").removeClass("warn-border");
	return true;
}
//身份验证检验
function  identifyCheck(){
	var flag = true;
	if(!checkControl_normal_realname()){
		flag = false;
	}
	if($(".idNo").size()!=0){
		if(!checkControl_normal_idNo()){
			flag = false;
		}
	}
	if($(".idcard").size()!=0){
		if(!checkControl_normal_idCard()){
			flag = false;
		}
	}
	//检查图片
	if($(".zhengname").size()!=0){
		if($(".zhengname").text()==""){
			$(".zhengname-err").text("请上传正面身份证照片");
			flag =  false;
		}else{
			$(".zhengname-err").text("");
		}
	}
	if($(".fanname").size()!=0){
		if($(".fanname").text()==""){
			$(".fanname-err").text("请上传反面身份证照片");
			flag =  false;
		}else{
			$(".fanname-err").text("");
		}
	}
	if($(".yingyename").size()!=0){
		if($(".yingyename").text()==""){
			$(".yingyename-err").text("请上传营业执照照片");
			flag =  false;
		}else{
			$(".yingyename-err").text("");
		}
	}
	return flag;
}
/*单项修改*/
function control_modify(){
	$(".modi-btn").click(function(){
		var itemname = $(this).attr("itemname");
		if(itemname=="usualEmail"){
			var val = $(this).parent().find(".readywords").text();
			$(this).parents(".control-form-item-content").html("<input type='text' name='email' value='"+val+"' class='control-input usualEmail val-change'>");
		}else if(itemname=="usualArea"){
			$(this).parents(".control-form-item-content").html(
											"<div class='col col_area'>"
                                               +"<div class='sel-wrap' imgsrc='/showimg?url=images/icon-images/arrow_down_gray.png'>"
                                                   +"<select name='province' id='province' class='val-change'>"
                                                  +"</select>"
                                                  +"<label>请选择<span class='col-arrow_down'><img src='/showimg?url=images/icon-images/arrow_down_gray.png' /></span></label>"
                                               +"  </div>"
                                            +" </div>"
                                            +" <div class='col col_area'>"
                                          +"<div class='sel-wrap' imgsrc='/showimg?url=images/icon-images/arrow_down_gray.png'>"
                                            +"<select name='city' id='city' class='val-change'>"
                                           +"<option value='请选择'>请选择</option>"
                                           +" </select>"
                                            +"    <label>请选择<span class='col-arrow_down'><img src='/showimg?url=images/icon-images/arrow_down_gray.png' /></span></label>"
                                           +"    </div>"
                                           +"</div>"
                                          +" <div class='col col_area' id='area_hide'>"
                                          +"       <div class='sel-wrap' imgsrc='/showimg?url=images/icon-images/arrow_down_gray.png'>"
                                          +"           <select name='district' id='area'  class='control-input val-change'>"
                                          +"               <option value='请选择'>请选择</option>"
                                          +"           </select>"
                                          +"           <label>请选择<span class='col-arrow_down'><img src='/showimg?url=images/icon-images/arrow_down_gray.png' /></span></label>"
                                          +"       </div>"
                                           +"  </div>"
				);
			
			updateProvince();
			updateCity();
			updateArea();
			if($("#oriprovince").val()!=""){
				setInitArea($("#oriprovince").val(),$("#oricity").val(),$("#oridistrict").val());
			}
			updateSelect();
		}else if(itemname=="usualAreaDetail"){
			var val = $.trim($(this).parent().find(".readywords").text());
			$(this).parents(".control-form-item-content").html("<input type='text' name='detail' value='"+val+"' class='control-input val-change'>");
		}else if(itemname=="postcode"){
			var val = $.trim($(this).parent().find(".readywords").text());
			$(this).parents(".control-form-item-content").html("<input type='text' class='control-input postcode val-change' value='"+val+"' name='postcode'>");
		}else if(itemname=="fax"){
			var val = $.trim($(this).parent().find(".readywords").text());
			$(this).parents(".control-form-item-content").html("<input type='text' class='control-input fax val-change' value='"+val+"' name='fax'>");
			
		}
		formWithoutModi_Listen();
		control_normal_formblur();
	});
}
/*显示评价框*/
 function judgeBox_show(a,b){
        $("#myModal").modal('show');
 }
/*提交评价*/
function commit_judge(orderid){
	var servicemanner = $.trim($(".servicemanner[check=true]").attr("value"));
	var servicespeed = $.trim($(".servicespeed[check=true]").attr("value"));
	var judge = formatHTML($.trim($(".judge-txt").val()));
	var grade = $(".finalgrade").text();
	if(judge==""){
		$(".judge-txt").addClass("warn-border");
		return false;
	}
	var clickStr = $(".reply-send-btn").attr("onclick");
	$(".reply-send-btn").attr("onclick","");
	setTimeout(function(){
		$(".reply-send-btn").attr("onclick",clickStr);
	},1000);
	$.ajax({
		url:"/work/finishOrder",
		type:"post",
		data:{
			orderid:orderid,
			judge:judge,
			servicerank:servicemanner,
			timerank:servicespeed,
			rank:grade,
			formToken: $("#formToken").val()
		},
		success:function(data){
			window.location.reload();
		}
	});
}
/*添加评价框事件*/
function addJudgeEvent(){
	 $(".label-check label").click(function(){
         $(this).parent().find(".model-checkbox").attr("check","false");
         $(this).find(".model-checkbox").attr("check","true");
      });
      $(".judge-txt").keyup(function(){
          var str  = $(this).val();
          $(".hasword").text(str.length);
      });
      $(".judge-txt").focus(function(){
    	  $(this).removeClass("warn-border");
      });
      var chooseStar= function(){
          var $star  = $(this).parent().parent().find("span");
          if($(this).hasClass("star-half")){
              $star.attr("class","iconfont icon-halfstar");
              console.log($star.attr("class"));
          }else{
              $star.attr("class","iconfont icon-star");
          }
          var li_index = $star.parent().index();
          var before_star  = $(".judge-level-ul>li:lt("+li_index+")>span");
          before_star.attr("class","iconfont icon-star");
          var behind_star = $(".judge-level-ul>li:gt("+li_index+")>span");
          behind_star.attr("class","iconfont icon-emptystar");
          console.log($(".judge-level-ul icon-star").length);
          $(".finalgrade").text(($(".judge-level-ul .icon-star").size()*2+$(".judge-level-ul .icon-halfstar").size()*1+$(".judge-level-ul .icon-emptystar").size()*0)*5/10);
      };
      $(".bc_choose>div").mouseenter(chooseStar);
      $(".bc_choose>div").click(chooseStar);
      
}
/*头像截取验证*/
function headCutCheck(){
	if(imgSelect){
		var sel = imgSelect.getSelection().width;
		if(sel==0){
			$(".cuthead-err").text("请先截取图片后再上传~");
			return false;
		}else{
			$(".cuthead-err").text("");
			return true;
		}
	}else{
		$(".cuthead-err").text("请先截取图片后再上传~");
		return false;
	}
}

//划过展示图片
function showExtraImg(){
	$(".showExtra").mouseenter(function(e){
		show = 0;
		var _element  = e.target||e.srcElemet;
		var $ele = $(_element);
		var x = $ele.offset().left;
		var y = $ele.offset().top;
		var html = "<div class='showExtraBox' style='left:"+(x+125)+"px;top:"+(y-67)+"px;'><s class='left-corner-i'><i></i></s><div class='showExtraBox-inner imgCenter'><img id='extraImg'></div></div>"
		$(".showExtraBox").remove();
		$("body").append(html);
		$(".showExtraBox").mouseenter(showExtraBox.show);
		$(".showExtraBox").mouseleave(showExtraBox.hidden);
		document.getElementById("extraImg").src = _element.src;
	});
	var show = 1;
	$(".showExtra").mouseleave(function(){
	
		setTimeout(function(){
			if(show==1){
				$(".showExtraBox").remove();
			}
		},200);
	});
	var showExtraBox = {
		show:function(){
			show = 0;
		},
		hidden:function(){
			$(this).remove();
			show = 1;
		}
	}
}
function changeStyleOfPage(){
	if($(".pagination>li").size()==1){
		$(".pagination>.active>a").css({"color":"#428bca","border-color":"#ccc","background-color":"#fff"});
	}
}
var formModiFlag = false;
function formWithoutModi_Listen(){
	$(".val-change").on("input propertychange change",function(){
		formModiFlag=true;
	});
}