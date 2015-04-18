
var imgInput_ForId = 0;
$(function(){
	//初始化
	initUpload();
	
});
function initUpload(){
	$(".filePicker").click(function(){
		imgInput_ForId++;
		$(".uploadImg-active .upload_box").append("<input id='fileImage_"+imgInput_ForId+"' class='fileinput' type='file' size='30' onchange='select_img()' accept='image/*' name='fileselect[]'>");
		$(".uploadImg-active .fileinput:last").click();
	});
	addImgEventListener();
	/*if($(".uploadImg-active .fileinput").size()!=0&&$(".uploadImg-active .fileinput:last").val()==""){
		return;
	}
	if($("#fileImage_"+imgInput_ForId)){
		imgInput_ForId++;
	}
	$(".uploadImg-active .upload_box").append("<input id='fileImage_"+imgInput_ForId+"' class='fileinput' type='file' size='30' onchange='select_img()' accept='image/*' name='fileselect[]'>");*/
	
}
function select_img(){

	var selected_Id = "fileImage_"+imgInput_ForId;
	var file_arr = document.getElementById(selected_Id).files; 
	if(file_arr.length!=0){
		preview(file_arr[0],selected_Id);
		catchFileInfo();
	}else{
		$("#"+selected_Id).remove();
	}
	
}
//删除空input
function delNullFIle(){
	$(".uploadImg-active .fileinput").each(function(){
		if($(this).val()==""){
			$(this).remove();
		}
	});
}
function preview(file,fileId) {
            var reader = new FileReader();
            reader.onload = function(e) {
				
				var html = "";
		
				html += '<div  fileId="'+fileId+'" class="upload_append_list">';
				html += '	<div class="file_bar">';
				html += '		<div style="padding:5px;">';
				html += '			<p class="file_name">' + file.name + '</p>';
				html += '<span class="file_del" fileId ="'+fileId+'" title="删除"></span>';  
				html += '		</div>';
				html += '	</div>';
				html += '	<a style="height:100px;width:120px;" href="#" class="imgBox">';
				html += '		<div class="uploadImg" style="width:105px">';				
				html += '			<img  class="upload_image" src="' + e.target.result + '" style="width:105px;height:110px" />';                                                                 
				html += '		</div>';
				html += '	</a>';
				html += '</div>';
               
			
                $('.uploadImg-active #preview').append(html);
				addImgEventListener();
            };		
            reader.readAsDataURL(file);
			
}
//绑定事件
function addImgEventListener(){
		$(".reset-share").click(function(){
			$(".uploadImg-active .cancel_Allpick").click();
		});
		$(".upload_append_list").mouseenter(function(){
			$(this).find(".file_bar").addClass("file_hover");
			
		});
		$(".upload_append_list").mouseleave(function(){
			$(this).find(".file_bar").removeClass("file_hover");
		});
		$(".file_del").click(function(){
			var fileId = $(this).attr("fileId");
			$("#"+fileId).remove();
			$(".uploadImg-active div[fileId="+fileId+"]").remove();
			catchFileInfo();
		});
		$(".cancel_Allpick").click(function(){
			$(".uploadImg-active .file_del").click();
			catchFileInfo();
		});
 }  

//统计大小
function catchFileInfo(){
		var fileCount= 0 , fileSize = 0;
		$(".uploadImg-active .upload_box>.fileinput").each(function(){
			var files = document.getElementById($(this).attr("id")).files; 
			if(files.length!= 0){
				fileCount+=files.length;
				$.each(files, function(k,v){
					fileSize += v.size;
				});
			}					
		});				
		// 转化为kb和MB格式
		if (fileSize > 1024 * 1024) {                    
			fileSize = (Math.round(fileSize * 100 / (1024 * 1024)) / 100).toString() + 'MB';                
		} else {                    
			fileSize = (Math.round(fileSize * 100 / 1024) / 100).toString() + 'KB';                
		}  
		
		// 设置内容
		$(".uploadImg-active #status_info").html("选中"+fileCount+"张文件，共"+fileSize+"。");

			
} 