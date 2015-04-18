// 使select的某个选项被选中
function toselect(selectid,optionval)
{
	var objSelect = document.getElementById(selectid);
	var l = objSelect.options.length;
	for (var i = 0; i < l; i++) 
	{
		var op = objSelect.options[i];
		if(op.value == optionval)
		{
			objSelect.options[i].selected=true;
			break;
		}
	}
}

//使select的某个选项被选中
function toselectobj(objSelect,optionval)
{
	var l = objSelect.options.length;
	for (var i = 0; i < l; i++) 
	{
		var op = objSelect.options[i];
		if(op.value == optionval)
		{
			objSelect.options[i].selected=true;
			break;
		}
	}
}

//使radio的某个选项被选中
function toradio(groupname,optionval)
{
	var objgroup=document.getElementsByName(groupname);
	for(var i = 0; i< objgroup.length; i++){
		if(objgroup[i].value==optionval){
			objgroup[i].checked=true;
		}
	}
}


// 说明：Javascript 控制 CheckBox 的全选与取消全选--S // 

//全选
function checkAll(name){    
  var el = document.getElementsByTagName('input');  
  var len = el.length;  
  for(var i=0; i<len; i++){
  	  if((el[i].type=="checkbox") && (el[i].name==name)){ 
  	  	el[i].checked = true;
     }     
   }
} 
//返选
function reverseAll(name){    
  var el = document.getElementsByTagName('input');  
  var len = el.length;  
  for(var i=0; i<len; i++){
  	  if((el[i].type=="checkbox") && (el[i].name==name)){ 
  	  	el[i].checked = !(el[i].checked);
     }     
   }
}
//全部不选
function clearAll(name){  
  var el = document.getElementsByTagName('input');   
  var len = el.length;   
  for(var i=0; i<len; i++){
  	if((el[i].type=="checkbox") && (el[i].name==name)){
  		el[i].checked = false;         
  	}
  } 
} 

//判断复选框是否选中
function checkData(name){
   var el = document.getElementsByName(name);   
   var len = el.length;   
   var ret = false;
   for(var i=0; i<len; i++){
   		if(el[i].checked == true){
			ret = true;
			break;
		 }
   }   
   return ret;
}

//根据控件名,拼接批量处理所需要的"|"参数
function spliceVerticalBar(name){
	var el = document.getElementsByName(name); 
	var len = el.length;  
	var ret = "";
   	for(var i=0; i<len; i++){
   	  if((el[i].type=="checkbox") && el[i].checked ){
   	  	ret += el[i].value + "|";
      }     
    }
    return ret;
}

//获取radio组当前选中的值
function curradioValue(name){
   var el = document.getElementsByName(name);   
   var len = el.length;   
   var ret = "";
   for(var i=0; i<len; i++){
   		if(el[i].checked == true && el[i].type == "radio"){
			ret = el[i].value
			break;
		 }
   }   
   return ret;
} 


//去左空格;
function ltrim(s){
  return s.replace( /^\s*/, ""); 

}
//去右空格;
function rtrim(s){
  return s.replace( /\s*$/, "");
}
//去左右空格;
function trim(s){
  return rtrim(ltrim(s));
}

//判断字符串是否为空
function isNan(str)
{
	str = trim(str);
	if(str.length == 0){
		return false;
	}
	return true;
}
 
 //返回字符串长度
 function getStrLength(str)
 {
 	return str.length;
 }
 
 //是否有效的Email;
function IsMail(_str){
    var tmp_str = trim(_str);
    var pattern = /^[ _a-z0-9- ]+(\.[a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,3})$/;
    return pattern.test(tmp_str);
}




//是否有效的整数;
function IsNumber(_str){
    var tmp_str = trim(_str);
    var pattern = /^-?\d+$/;
    return pattern.test(tmp_str);
}

//是否有效的小数
function IsNumber_float(_str){
    var tmp_str = trim(_str);
    var pattern = /^(-?\d+)(\.\d+)?$/;
    return pattern.test(tmp_str);
}

//是否有效的链接;
function IsURL(url){
    var sTemp;
    var b=true;
    sTemp=url.substring(0,7);
    sTemp=sTemp.toUpperCase();
    if ((sTemp!="HTTP://")||(url.length<10)){
        b=false;
    }
    return b;
}
//是否有效的手机号码;
function IsMobile(_str){
    var tmp_str = trim(_str);
    if(tmp_str.length>11){return false;}
    var pattern = /1\d{10}/;
    return pattern.test(tmp_str);
}

//是否有效邮编
function IsCode(_str){
    var tmp_str = trim(_str);
    var pattern = /^[1-9]\d{5}$/;
    return pattern.test(tmp_str);
}
//是否有效固话
function IsPhone(_str){
    var tmp_str = trim(_str);
    var pattern = /^(0(\d){3}-)?(\d){7}(-(\d){1,6})?|(0(\d){2}-)?(\d){8}(-(\d){1,6})?$/;
    return pattern.test(tmp_str);
}

function showMess(_str){
	document.getElementById("pdiscrib").innerHTML=_str+'';
	document.getElementById("pubwin").click();
}

//函数名：CheckDateTime  
//功能介绍：检查是否为日期时间 
function IsDateTime(str){	  
  var reg = /^(\d+)-(\d{1,2})-(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/; 
  var r = str.match(reg); 
  if(r==null)return false; 
  r[2]=r[2]-1; 
  var d= new Date(r[1], r[2],r[3], r[4],r[5], r[6]); 
  if(d.getFullYear()!=r[1])return false; 
  if(d.getMonth()!=r[2])return false; 
  if(d.getDate()!=r[3])return false; 
  if(d.getHours()!=r[4])return false; 
  if(d.getMinutes()!=r[5])return false; 
  if(d.getSeconds()!=r[6])return false; 
  return true;
}

function checkdate(datafrom,datato){
	datafrom = datafrom.replace(/-/g,"");
	datafrom = datafrom.replace(/:/g,"");
	datafrom = datafrom.replace(/ /g,"");
	datato = datato.replace(/-/g,"");
	datato = datato.replace(/:/g,"");
	datato = datato.replace(/ /g,"");
	if (datafrom <= datato) {
		return true;
	}else {
		return false;
	}
	
}
