/**********************发布需求专用*************************/
/**********************完成日期应在投标截止之后*************************/
function getNextDay(d){
        d = new Date(d);
        d = +d + 1000*60*60*24;
        d = new Date(d);
        return d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();     
}
$('#stoptime').on('focus',function(){
    $('#stoptime').datetimepicker({
        lang:'ch',
        timepicker:false,
        format:'Y-m-d',
        formatDate:'Y-m-d',
        minDate:0
    });
});
$('#stoptime').on('blur',function(){
    $('#finishtime').datetimepicker({
        lang:'ch',
        timepicker:false,
        format:'Y-m-d',
        formatDate:'Y-m-d',
        minDate:getNextDay($(this).val())
    });
});