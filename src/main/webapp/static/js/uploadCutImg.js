
$(function(){
	$(".addLocalImg").click(function(){
		$("#singlePhoto").click();
	});
});

var imgSelect ;
var cutyet = 0;
function loadImg(){
	
	var file_arr = document.getElementById("singlePhoto").files; 
	if(file_arr.length!=0){
		if(!putview(file_arr[0],"singlePhoto")){
            return false;
        }
		setTimeout(function(){
			imgSelect = $('.cut-preview>img').imgAreaSelect({
				selectionColor: 'blue',
				x1: 140, y1: 140, x2:180, y2: 180,
				selectionOpacity: 0.2 ,
				aspectRatio: '1:1',
				onSelectEnd: previewHead,
			});
			
		},1000);
	}else{
		
	}

}
function putview(file,fileId) {
            var reader = new FileReader();
           if(file.size>1024*1024){
            alert("图片过大,请重新选择!");
            return false;
           }
           
           var flag = true;
            reader.onload = function(e) {
            	var img =  new Image();
            	img.src = e.target.result;
            	if(img.width<160||img.height<160){
            		alert("上传图片宽和高不能小于160像素,请重新选择");
            		$("#singlePhoto").val("");
            		flag=false;
            	}else{
            		$(".cut-preview>img,.cut-preview1>img,.cut-preview2>img,.cut-preview3>img").attr("src",e.target.result);           		
            		flag=true;
            	}
				
            };	
            reader.readAsDataURL(file);
            return flag;
			
}
function previewHead(img, selection) { 
	cutyet = 1;
	$(".cuthead-err").text("");
	var imgWidth = img.naturalWidth;
	var imgHeight = img.naturalHeight;
	console.log(imgWidth+"==="+imgHeight);

	if(imgWidth>250||imgHeight>250){
		if(imgWidth>imgHeight){
			imgHeight = 250*imgHeight/imgWidth;
			imgWidth =250;
		}else{
			imgWidth = 250*imgWidth/imgHeight;
			imgHeight = 250;
		}

	}
	console.log(imgWidth+"==0="+imgHeight);
    if (!selection.width || !selection.height) 
        return; 
    
    var scaleX = 160 / selection.width; 
    var scaleY = 160 / selection.height; 

    $('.cut-preview1>img').css({ 
        width: Math.round(scaleX * imgWidth), 
        height: Math.round(scaleY * imgHeight), 
        marginLeft: -Math.round(scaleX * selection.x1), 
        marginTop: -Math.round(scaleY * selection.y1) 
    }); 
    
    scaleX = 60 / selection.width; 
    scaleY = 60 / selection.height; 
    
    $('.cut-preview2>img').css({ 
        width: Math.round(scaleX * imgWidth), 
        height: Math.round(scaleY * imgHeight), 
        marginLeft: -Math.round(scaleX * selection.x1), 
        marginTop: -Math.round(scaleY * selection.y1) 
    }); 
    
    scaleX = 40 / selection.width; 
    scaleY = 40 / selection.height; 
    
    $('.cut-preview3>img').css({ 
        width: Math.round(scaleX * imgWidth), 
        height: Math.round(scaleY * imgHeight), 
        marginLeft: -Math.round(scaleX * selection.x1), 
        marginTop: -Math.round(scaleY * selection.y1) 
    }); 
    
    $('#imgx1').val(selection.x1); 
    $('#imgy1').val(selection.y1); 
    $('#imgx2').val(selection.x2); 
    $('#imgy2').val(selection.y2); 
    $('#imgSelectW').val(selection.width); 
    $('#imgSelectH').val(selection.height); 
    $('#imgRealW').val(imgWidth); 
    $('#imgRealH').val(imgHeight);       
} 