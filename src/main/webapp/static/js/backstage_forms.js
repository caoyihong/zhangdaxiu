(function(){
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

	function checkInput(){
		function checkPhoneNum(){
			$("#phoneNum").blur(function(){
				if($(this).val().match(/1[3458]{1}\d{9}$/)){
					$(this).css("border","2px solid #E4E4E4");
					$(this).next().html("");
				}
				else{
					$(this).css("border","2px solid red");
					$(this).next().html("不是一个合法的手机号！");
				}
			});
			$('#phoneNum').bind('keypress',function(event){
		            if(event.keyCode == "13")    
		            {
		            	if($(this).val().match(/1[3458]{1}\d{9}$/)){
						$(this).css("border","2px solid #E4E4E4");
						$(this).next().html("");
					}
					else{
						$(this).css("border","2px solid red");
						$(this).next().html("不是一个合法的手机号！");
						return false;
					}
		          }
        		});
		}
		checkPhoneNum();
	}
	updateProvince();
	updateCity();
	updateArea();
	updateSelect();
	checkInput();

})();