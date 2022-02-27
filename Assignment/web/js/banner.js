var images = document.querySelectorAll('.rei_banner_body>div');
var btn = document.querySelectorAll('.rei_banner_bottombtns span');
var i = 0;

banner();

function banner() {
    for (var index = 0; index < images.length; index++) {
        images[index].style = 'transform: translateX(-' + (100 * i) + '%); transition: 1s;'
        btn[index].classList = '';
    }
    btn[i].classList = 'banner-highlight';
    setTimeout(banner, 3000);
    i++;
    if (i == images.length) i = 0;
}

function getBanner(id) {
    i = id - 1;
    for (var index = 0; index < images.length; index++) {
        btn[index].classList = '';
        images[index].style = 'transform: translateX(-' + (100 * i) + '%); transition: 1s;'
    }
    btn[i].classList = 'banner-highlight';
}