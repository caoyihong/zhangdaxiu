$(function(){
	$(".upload-iden-btn").click(function(){
		$(this).parent().find(".ideninput").click();
		$("."+$.trim($(this).parent().find(".iden-img-name").attr("class").replace("iden-img-name",""))+"-err").text("");
	});
});
function loadIdentifyImg(e){
	var dom = $(e)[0];
	var file_arr =dom .files; 
	if(file_arr.length!=0){
		previeIdenImg(file_arr[0],e)
	}
}
function previeIdenImg(file,dom) {
            var reader = new FileReader();
           if(file.size>1024*1024*2){
            alert("图片过大,请重新选择!");
            return false;
           }
            reader.onload = function(e) {
				$(dom).parent().parent().find("img").attr("src",e.target.result);
				$(dom).parent().find(".iden-img-name").text(file.name);
            };	
            reader.readAsDataURL(file);
            return true;
			
}