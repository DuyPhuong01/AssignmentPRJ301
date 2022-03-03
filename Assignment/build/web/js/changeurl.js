function submitFilter() {
    var brands = [];
    document.querySelectorAll('input[name="brand"]:checked').forEach((b) => {
        brands.push(b.getAttribute('value'));
    });
    var min = [];
    min.push(document.querySelector('input[name="min"]').value);
    var max = [];
    max.push(document.querySelector('input[name="max"]').value);
    var url = window.location.href;
    url = changeURL('brand', brands, url);
    url = changeURL('min', min, url);
    url = changeURL('max', max, url);
    window.location = url;
}

function changeURL(atb, vals, url) {
    var result = '';

    for (var i = 0; i < vals.length; i++) {
        result += atb + '=' + vals[i];
        if (i != vals.length - 1) result += '&';
    };
    var path = url.split('?');
    if (url.includes('?')) {
        var para = path[1].split('&');
        for (var i = 0; i < para.length; i++) {
            if (para[i].split('=')[0] != atb) {
                if (!(vals.length == 0 && i == 0)) result += '&';
                result += para[i];
            }
        }
    }
    if (result != '') result = path[0] + '?' + result;
    else result = url
    return result;
}