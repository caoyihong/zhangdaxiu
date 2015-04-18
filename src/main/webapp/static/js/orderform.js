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
				return ;
			}
			if(sub_arr[key*100+1]==undefined){
				//直辖市取消显示第三个框
				$("#area_hide").hide();
			}
			else{
				$("#area_hide").show();
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

	/**服务类型数据**/
	var	services = [],professinal=[];
	services[0]= "律师";
	professinal[0] = [];
	professinal[0][0]="专利";
	professinal[0][1]="商标";
	professinal[0][2]="版权";
	professinal[0][3]="软件";
	professinal[0][4]="商业秘密";
	professinal[0][5]="反不当竞争";
	services[1]= "评估师";
	professinal[1] = [];
	professinal[1][0]="知识产权评估";
	services[2]="司法鉴定人";
	professinal[2] = [];
	professinal[2][0]="司法鉴定";
	services[3]="商标代理人";
	professinal[3] = [];
	professinal[3][0]="代理商标";
	services[4]="专利代理";
	professinal[4] = [];
	professinal[4][0]="机械";
	professinal[4][1]="光电";
	professinal[4][2]="通信";
	professinal[4][3]="计算机及软件";
	professinal[4][4]="化学";
	professinal[4][5]="生物医药";
	professinal[4][6]="材料";
	function updateItemType(){
		
		var str = "<option value='请选择' key=''>请选择</option>";
		var str1 = "<option value='请选择' key='' selected>请选择</option>";
		$.each(services,function(index,data){
			str+="<option value='"+data+"' key='"+index+"'>"+data+"</option>"
		});
		$("select[name=kind01]").html(str);
		$("select[name=kind02]").html(str1);
		$("select[name=kind01]").change(function(){
			var key = $(this).find("option:checked").attr("key");
			
			if(key!=""){
				$.each(professinal[key],function(index,data){
					str1+= "<option value='"+data+"' key='"+index+"'>"+data+"</option>";
				});
			}else{
				 str1 = "<option value='请选择' key=''>请选择</option>";
			}
			$("select[name=kind02]").html(str1);
			updateSelect();
		});
	}
	/*****/
	function updateIndustryType(){//行业领域
		
		var str = "<option value='请选择' key=''>请选择</option>";
		var str1 = "<option value='请选择' key='' selected>请选择</option>";
		$.each(industry,function(index,data){
			str+="<option value='"+data+"' key='"+index+"'>"+data+"</option>"
		});
		$("select[name=kind03]").html(str);
		
		$("select[name=kind04]").html(str1);
		$("select[name=kind03]").change(function(){
			var key = $(this).find("option:checked").attr("key");
			
			if(key!=""){
				$.each(field[key],function(index,data){
					str1+= "<option value='"+formatIndustryCode(key,index)+"' key='"+index+"'>"+data+"</option>";
				});
			}else{
				 str1 = "<option value='请选择' key=''>请选择</option>";
			}
			$("select[name=kind04]").html(str1);
			updateSelect();
		});
	}
	function  formatIndustryCode(key,index){
		key = key<10?"0"+key:key+"";
		index= index<10?"0"+index:index+"";
		return key+""+index;
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
	updateItemType();
	 updateIndustryType();
	updateSelect();
	checkInput();

})();