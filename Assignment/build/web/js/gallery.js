$('.select-btn ul li.option').click(function() {
    $(this).siblings().children().remove();
    var a = $(this).siblings().toggle();
    console.log($(a).is(":visible"));
});

let theme = sessionStorage.theme;
if(theme==='dark') {
    document.documentElement.setAttribute('data-theme', 'dark');
    document.getElementById('theme-checkbox').setAttribute('checked', 'true');
} else {
    document.documentElement.setAttribute('data-theme', 'light');
}

let checkbox = document.querySelector('input[name=theme]');
checkbox.addEventListener('change', function() {
    if (this.checked) {
        document.documentElement.setAttribute('data-theme', 'dark');
        sessionStorage.setItem('theme', 'dark');
    } else {
        document.documentElement.setAttribute('data-theme', 'light');
        sessionStorage.setItem('theme', 'light');
    }
});

function deleteProduct(id){
    if(confirm("Are you sure to delete Product with id = "+id+"?")){
        window.location="deleteproduct?id="+id;
    }
}