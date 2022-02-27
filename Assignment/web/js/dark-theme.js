
let theme_checkbox = document.getElementById('theme-checkbox');
let theme = document.documentElement.getAttribute('data-theme');
if(theme == 'dark'){
    theme_checkbox.setAttribute('checked', 'true');
}

theme_checkbox.addEventListener('change', function() {
    if (this.checked) {
        document.documentElement.setAttribute('data-theme', 'dark');
        sessionStorage.setItem('theme', 'dark');
    } else {
        document.documentElement.setAttribute('data-theme', 'light');
        sessionStorage.setItem('theme', 'light');
    }
});

