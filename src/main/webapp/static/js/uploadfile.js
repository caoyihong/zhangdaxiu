/****************************************
**
**
**
**
******************************************/
//读取上传的限制条件等等
$(document).ready(function(){
	$(".imgupload").find(".upload").on("click",function(){
		$(this).parent(".imgupload").find(".hideinput").trigger("click");
	});
	$(".hideinput").change(function(e){
             var file = e.target.files||e.dataTransfer.files;
             if(file){
                 var reader = new FileReader();
                 var $preview=$(this).parent(".imgupload").find(".imgpreview");
                 reader.onload=function(){
                 	$preview.html('<img src="' + this.result + '" /><div class="deleImg"><img src="/showimg?url=images/icon-images/delete_white.png" /></div>');
                 	//增加对图片的操作事件
                 	$preview.find(".deleImg img").on("click",function(){                 		
                 		var $imgupload=$(this).parent(".deleImg").parent(".imgpreview").parent(".imgupload");
                 		var $Input=$imgupload.find(".hideinput");
                 		$Input.val("");
                 		$imgupload.find(".notice").before($Input);
                 		$(this).parent(".deleImg").parent(".imgpreview").html("");
                 	});
                 };
                reader.readAsDataURL(file[0]);
            }
      });
});

function delNullFIle(){
}