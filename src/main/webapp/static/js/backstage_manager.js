(function(){
	$(document).ready(function(){
		$(".second-manul").hide();
		$(".second-manul:first").show();
		$(".first-manul > li > a").on("click",function(){
			$(".second-manul").hide();
			$(this).next().show();
			return false;
		});
		$(".addInput").on("click",function(){
			//点击找父元素找inputlist并给inputlist增加一个input
			var $inputlist=$(this).parent("li").find(".inputlist");
			if(this.inputcount==undefined){
				this.inputcount=1;
			}
			this.inputcount++;
			$inputlist.find("li:first input").trigger("click");//先取消已有的特殊样式
			var $newli=$inputlist.find("li:first").clone();
			$newli.find("input").attr("name",$newli.find("input").attr("name") + this.inputcount);
			$inputlist.append($newli);
			//增加处理事件
			$newli.find(".deleInput").on("click",function(){
				$(this).parent("li").remove();
				return false;
			});
			return false;
		});
		$(".deleInput").on("click",function(){
			$(this).parent("li").remove();
			return false;
		});
	});
})();