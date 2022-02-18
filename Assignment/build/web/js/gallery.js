$('.select-btn ul li.option').click(function() {
    $(this).siblings().children().remove();
    var a = $(this).siblings().toggle();
    console.log($(a).is(":visible"));
});

let checkbox = document.querySelector('input[name=theme]');
checkbox.addEventListener('change', function() {
    if (this.checked) {
        document.documentElement.setAttribute('data-theme', 'dark');
        sessionStorage.theme = "dark";
    } else {
        document.documentElement.setAttribute('data-theme', 'light');
        sessionStorage.theme = "light";
    }
});