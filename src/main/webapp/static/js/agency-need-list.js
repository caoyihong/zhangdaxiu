$(document).ready(function(){
   //将标题裁剪，并且，当鼠标指向need时，显示一个小弹窗把完整标题显示出来
    $(".needlist > ul").after('<div class="popfulltext"></div>');
    $(".need").find(".title").each(function(){
        var timer;
        $(this).html(cutString($(this).attr("fulltext"),14));
        $(this).mousemove(function(e){
            $(".popfulltext").hide();
            var $item=$(this);
            var xx = e.originalEvent.x || e.originalEvent.layerX || 0; 
            var yy = e.originalEvent.y || e.originalEvent.layerY || 0; 
            $(".popfulltext").css("top",yy +10 + "px").css("left",xx +10 + "px");
            $(".popfulltext").html($item.attr("fulltext"));
            clearTimeout(timer);
            timer=setTimeout(function(){
                $(".popfulltext").show();
                clearTimeout(timer);
            },2000);
        });
        $(this).on("mouseleave",function(){
            $(".popfulltext").hide();
            clearTimeout(timer);
        });
    });
    //裁剪用户名
        $(".need").find(".user").each(function(){
        var timer;
        $(this).html(cutString($(this).attr("fulltext"),6));
        $(this).mousemove(function(e){
            $(".popfulltext").hide();
            var $item=$(this);
            var xx = e.originalEvent.x || e.originalEvent.layerX || 0; 
            var yy = e.originalEvent.y || e.originalEvent.layerY || 0; 
            $(".popfulltext").css("top",yy +10 + "px").css("left",xx +10 + "px");
            $(".popfulltext").html($item.attr("fulltext"));
            clearTimeout(timer);
            timer=setTimeout(function(){
                $(".popfulltext").show();
                clearTimeout(timer);
            },2000);
        });
        $(this).on("mouseleave",function(){
            $(".popfulltext").hide();
            clearTimeout(timer);
        });
    });
    //设置链接
    $(".need .title").click(function(){
        location.assign("showorder/" + $(this).parents(".need").attr("needid"));
        return false;
    }); 
    //设置收起
    $(".search-rowcontent-close").click(function(){
        var $searchRowContent=$(this).next();
        $searchRowContent.toggleClass("closed");
        if($(this).html()=="[收起全部]"){
            $(this).html("[展开全部]");
        }
        else{
            $(this).html("[收起全部]");
        }
        //找到当前选中的项，并在收起时使其提到最前面
        
        return false;
    });
});