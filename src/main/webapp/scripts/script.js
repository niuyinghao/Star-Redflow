var isSimbolShown = true;
$(function () {
    var symbol = $('#simbol').find("img");

    // for firefox blink
    symbol.css('display','none');
    setTimeout(function () {
        symbol.css('display','');
    }, 500);

    //
    setInterval(function () {
        debugger;
        if (isSimbolShown == true) {
            symbol.fadeOut(3000);
        }
        else {
            symbol.fadeIn(500);
        }
        isSimbolShown = !isSimbolShown;
    }, 10)
});
