/**
 * Created by Administrator on 2015/8/21.
 */


var member_div;
$(function() {
    if($("#accountType").val()=="member")
    {
        $("#member_div").show();
    }    else{
        member_div = $("#member_div").html();
        $("#member_div").hide(400).empty();
    }
})

$("#accountType").change(function() {

    if($(this).find("option:selected").val()=="member")
    {
        $("#member_div").html(member_div).show(400);
    }
    else{
        member_div = $("#member_div").html();
        $("#member_div").hide(400).empty();
    }

})