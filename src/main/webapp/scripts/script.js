var isSimbolShown = true;
$(function () {
    var symbol = $('#simbol').find("img");
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
