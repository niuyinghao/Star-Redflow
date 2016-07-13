var isSimbolShown = true;
$(function () {
    var symbol = $('#simbol').find("img");

    // for firefox blink
    symbol.hide();
    setTimeout(function () {
        symbol.show();
    }, 500);


    setInterval(function () {
        if (isSimbolShown == true) {
            symbol.fadeOut(3000);
        }
        else {
            symbol.fadeIn(500);
        }
        isSimbolShown = !isSimbolShown;
    }, 10)
});
