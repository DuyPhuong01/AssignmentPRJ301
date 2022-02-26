
let theme_checkbox = document.getElementById('theme-checkbox');
let logo = document.getElementById('store-logo');
let theme = document.documentElement.getAttribute('data-theme');
if(theme == 'dark'){
    theme_checkbox.setAttribute('checked', 'true');
    logo.classList.add('negative-logo');
}

theme_checkbox.addEventListener('change', function() {
    if (this.checked) {
        document.documentElement.setAttribute('data-theme', 'dark');
        logo.classList.add('negative-logo');
        sessionStorage.setItem('theme', 'dark');
    } else {
        document.documentElement.setAttribute('data-theme', 'light');
        logo.classList.remove('negative-logo');
        sessionStorage.setItem('theme', 'light');
    }
});

