/***************************************************************
**filename:chekregist.js
**function:【专用机构注册】检查表单合法性
**descript:1、表单含有checkform类名
**             2、每个input有ifEmpty或ifIllegal属性用于校验不通过时的提示内容
**             3、在submit的时候进行统一校验，高亮显示不通过的项
**history:2015-03-17 built
***************************************************************/
(function(){
	var flag=true;
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
					console.log(i+"=="+opt[i].selected);
				if (opt[i].selected == true) {
					o = opt[i].innerHTML;
				
				}
			})

			$(this).find('label').html(o + '<span class="col-arrow_down"><img src="' + $(this).attr("imgsrc") + '" /></span>');
		}).trigger('change');	
	}

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
	$("[maxWordCount]").bind("input propertychange",function(){
		if($(this).val().length>$(this).attr("maxWordCount")){
			//字数超了
			$(this).css("border","2px solid red").parent().find(".wordcount").css("color","red");
			flag=false;
		}
		else{
			//字数没超
			$(this).css("border","2px solid #E4E4E4").parent().find(".wordcount").css("color","#555");
			flag=true;
		}
		//更新字数统计
		$(this).parent().find(".wordcount").html($(this).val().length + "/" + $(this).attr("maxWordCount") + "字");
	});
	if($("[maxWordCount]").length!=0){
		$("[maxWordCount]").each(function(){
			$("[maxWordCount]").parent().find(".wordcount").css("color","#555").html($("[maxWordCount]").val().length + "/" + $("[maxWordCount]").attr("maxWordCount") + "字");
		})
		
	}
	//提交表单的函数
	$(".checkform input[type=submit]").css("background","#ccc");

	$(".checkform").unbind().submit(function(){
		return false;
	});
	$(".checkform input#accept-rule2").on("change",function(){
		if($(this).parent().find("input[type=checkbox]:checked").length==0){
			$(".checkform input[type=submit]").css("background","#ccc");
			$(".checkform").unbind().submit(function(){
				return false;
			});
		}
		else{
			$(".checkform input[type=submit]").css("background","#FC6E51");
			$(".checkform").unbind().submit(function(){
				flag=true;
				var $input=$(this).find("[ifEmpty]").each(ifEmpty);
				var $input=$(this).find("[ifIllegal]").each(ifIllegal);
				var $input=$(this).find("[ifSelected]").each(ifSelected);
				var $input=$(this).find("[ifGroupSelected]").each(ifGroupSelected);
				var $input=$(this).find("[ifItemSelected]").each(ifItemSelected);
				var $input=$(this).find("[ifAtleastOne]").each(ifAtleastOne);
				return flag;
			});
		}
	});
	updateProvince();
	updateCity();
	updateArea();
	updateSelect();
})();