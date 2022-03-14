<%-- 
    Document   : addcategory
    Author     : Duy Phuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Brand</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css">
        <script src="js/bootstrap/jquery.min.js"></script>
        <script src="js/set-theme.js"></script>
    </head>
    <body>
        <div class="center row mrg-lr-0" style="justify-content: center">
            <div class="col-ms-12 col-md-6 col-lg-4">
                <a href="home">Home</a>
                <label>
                    <input id="theme-checkbox" type="checkbox" name="theme">
                    <span class="btn"></span>
                    <i class="fa fa-moon-o" aria-hidden="true"></i>
                </label>
                <form class="container" action="brand" method="post" enctype="multipart/form-data">
                    <input type="text" name="action" value="create" hidden>
                    <div class="row">
                        <label class="col-ms-12 rei-input-label">
                            <input type="text" name="name">
                            <div class="rei-input-name">
                                <span></span>
                                <span class="rei-float-name"><p>Name</p></span>
                                <span></span>
                            </div>
                        </label>
                        <label class="rei-input-file-image col-ms-4">
                            <span class="input-name">Brand Picture</span>
                            <input type="file" name="brandLogo">
                            <div class="preview">
                                <img id="uploadPreview" style="display: none;">
                                <div class="box-hover"><p>Upload File</p></div>
                            </div>
                        </label>
                    </div>
                    <br/>
                    <input type="submit" value="Create Brand">
                </form>
            </div>
        </div>
    </body>
    <script src="js/rei-input.js"></script>
    <script src="js/dark-theme.js"></script>
    <script>
        var imgPreview = document.querySelector('#uploadPreview');
        document.querySelector('input[name="brandLogo"]').addEventListener('change', function () {
            const files = this.files[0];
            if (files) {
                const fileReader = new FileReader();
                fileReader.readAsDataURL(files);
                fileReader.addEventListener("load", function () {
                    imgPreview.style = '';
                    imgPreview.classList = 'preview-img';
                    imgPreview.setAttribute('src', this.result);
                });
            }
        });
    </script>
</html>
