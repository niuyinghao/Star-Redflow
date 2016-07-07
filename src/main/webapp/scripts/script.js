var isShowSimbol=true;
$(function () {
   setInterval(function () {
       if(isShowSimbol==true) {
           $('#simbol').attr('display', 'none');
       }
       else {
           $('#simbol').removeAttribute("display");
       }
   },1000)
});
