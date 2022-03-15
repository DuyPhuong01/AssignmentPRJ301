function submitFilter() {
    var brands = [];
    document.querySelectorAll('#side-bar input[name="brand"]:checked').forEach((b) => {
        brands.push(b.getAttribute('value'));
    });
    var min = [];
    min.push(document.querySelector('input[id="input-with-keypress-0"]').value);
    var max = [];
    max.push(document.querySelector('input[id="input-with-keypress-1"]').value);
    
    var url = window.location.search;
    url = changeURL('brand', brands, url);
    url = changeURL('min', min, url);
    url = changeURL('max', max, url);
    window.location.search = url;
}

function changeURL(atb, vals, url) {
    let searchParams = new URLSearchParams(url);
    if(searchParams.has('page')) searchParams.delete('page');
    if(searchParams.has(atb)) searchParams.delete(atb);
    for (var i = 0; i < vals.length; i++) {
        searchParams.append(atb, vals[i]);
    };
    return searchParams.toString();
}