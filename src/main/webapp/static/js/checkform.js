/***************************************************************
**filename:chekform.js
**function:【中介_事务所法务预约表单.html专用】检查表单合法性
**descript:1、表单含有checkform类名
**             2、每个input有ifEmpty或ifIllegal属性用于校验不通过时的提示内容
**             3、在submit的时候进行统一校验，高亮显示不通过的项
**history:2015-03-17 built
***************************************************************/
(function(){
	var flag=true;
	function ifEmpty(){
		if($(this).val()==""){
			//高亮显示当前为空的输入选区
			$(this).css("border","2px solid red");
			$(this).parent().find(".notice").html($(this).attr("ifEmpty"));
			flag=false;

			//当点击之后复原
			$(this).click(function(){
				$(this).css("border","2px solid #E5E5E5");
				$(this).parent().find(".notice").html("");
			});
		}
	}
	function ifSelected(){
		if($(this).val()=="请选择"){
			//高亮显示当前为空的输入选区
			$(this).next().css("border","2px solid red");
			$(this).parent().find(".notice").html($(this).attr("ifSelected"));
			flag=false;

			//当点击之后复原
			$(this).click(function(){
				$(this).next().css("border","2px solid #E5E5E5");
				$(this).parent().find(".notice").html("");
			});
		}	
	}
	function ifItemSelected(){//发布项目类别必选
		if($(this).val()=="请选择"){
			$(".itemNotice").html($(this).attr("ifItemSelected"));
			flag=false;
			$(this).click(function(){
				$(".itemNotice").html("");
			});
		}
	}
	function ifGroupSelected(){
		//地区专用的，确定第三个框是否选择来判断
		if($(this).find("select:last").val()=="请选择"){
			$(this).find("label").css("border","2px solid red");
			$(this).find("label:first").css("border","none");
			$(this).find(".notice").html($(this).attr("ifGroupSelected"));
			flag=false;

			//点击复原
			$(this).find("select").click(function(){
				$("[ifGroupSelected]").find("label").css("border","2px solid #E5E5E5");
				$("[ifGroupSelected]").find("label:first").css("border","none");
				$("[ifGroupSelected]").find(".notice").html("");
			});
		}
	}
	function ifIllegal(){
		if($(this).val()==""){
			//高亮显示当前为空的输入选区
			$(this).css("border","2px solid red");
			$(this).parent().find(".notice").html($(this).attr("ifIllegal"));
			flag=false;

			//当点击之后复原
			$(this).click(function(){
				$(this).css("border","2px solid #E5E5E5");
				$(this).parent().find(".notice").html("");
			});
		}
	}
	function ifAtleastOne(){
		//判断一组多选框至少选择了其中之一
		if($(this).find("input[type=checkbox]:checked").length==0){
			$(this).find(".notice").html($(this).attr("ifAtleastOne"));
			$(this).on("click",function(){
				$(this).find(".notice").html("");
				$(this).unbind("click");
			});
			flag=false;
		}
		else{
			$(this).find(".notice").html("");
		}
	}
	$(".checkform").submit(function(){
		flag=true;
		var $input=$(this).find("[ifEmpty]").each(ifEmpty);
		var $input=$(this).find("[ifIllegal]").each(ifIllegal);
		var $input=$(this).find("[ifSelected]").each(ifSelected);
		var $input=$(this).find("[ifGroupSelected]").each(ifGroupSelected);
		var $input=$(this).find("[ifItemSelected]").each(ifItemSelected);
		var $input=$(this).find("[ifAtleastOne]").each(ifAtleastOne);
		if(flag==true){
			$(".checkform").find("input[type=submit]").attr("disabled","disabled");
		}
		return flag;
	});
	$("[maxWordCount]").bind("input propertychange",function(){
		if($(this).val().length>$(this).attr("maxWordCount")){
			//字数超了
			$(this).css("border","2px solid red").next().css("color","red");
			flag=false;
		}
		else{
			//字数没超
			$(this).css("border","2px solid #E4E4E4").next().css("color","#555");
			flag=true;
		}
		//更新字数统计
		$(this).next().html($(this).val().length + "/200 字");
	});
	if($("[maxWordCount]").length!=0){
		$("[maxWordCount]").next().css("color","#555").html($("[maxWordCount]").val().length + "/200 字");
	}
})();