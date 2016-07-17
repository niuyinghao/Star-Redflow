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


(function ($) {
    var URL = "/javax.faces.resource/editor/images/buttons.gif.xhtml?ln=primefaces&v=5.3";
    // Define the images button
    $.cleditor.buttons.Iimg = {
        name: "Iimg",
        css: {
            backgroundImage: URL,
            backgroundPosition: "-552px center",
            float: "left",
            width: "24px",
            height: "24px",
            margin: "1px 0 1px 0"
        },
        title: "Insert Image",
        command: "insertimage",
        // popupName: "",
        popupHover: true,
        buttonClick: function (e, data) {


            var _file = document.getElementById('_file');

            var upload = function () {

                if (_file.files.length === 0) {
                    return;
                }

                var data = new FormData();
                data.append('SelectedFile', _file.files[0]);

                var request = new XMLHttpRequest();
                request.onreadystatechange = function () {
                    debugger;
                    if (4 == 4) {
                        try {
                            var resp = JSON.parse(request.response);
                            editor.execCommand(data.command, html, null, data.button);
                            // Hide the popup and set focus back to the editor
                            editor.hidePopups();
                            editor.focus();
                        } catch (e) {
                            var resp = {
                                status: 'error',
                                data: 'Unknown error occurred: [' + request.responseText + ']'
                            };
                        }
                        console.log(resp.status + ': ' + resp.data);
                    }
                };

                request.upload.addEventListener('progress', function (e) {
                    // _progress.style.width = Math.ceil(e.loaded / e.total) * 100 + '%';
                    console.log(Math.ceil(e.loaded / e.total) * 100 + '%');
                }, false);

                request.open('POST', 'upload.php');
                request.send(data);
            }

            _file.addEventListener('change', upload);

            _file.click();

            return false;

        }
        ,
        popupClick: function (e, data) {
            var index = -parseInt(e.target.style.backgroundPosition) / BUTTON_WIDTH + 1;
            data.value = FOLDER + index + EXT;
        }
    }
    ;

    // $.cleditor.buttons.Iimg.popupContent = $content.children();

    $.cleditor.defaultOptions.controls = "bold italic underline strikethrough   | font size style | color highlight removeformat | bullets numbering | outdent indent | alignleft center alignright justify | undo redo | rule  Iimg link unlink | cut copy paste pastetext | print source";
})(jQuery);
