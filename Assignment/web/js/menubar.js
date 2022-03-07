
var menu = document.getElementById('menubar');
document.querySelector('input[name="menu-switch"]').addEventListener('change', function() {
    if(this.checked) {
        menu.style = 'height: 80vh';
    } else {
        menu.style = '';
    }
});

var setting = document.querySelector('.nav-menu .setting-dropdown-menu');
document.querySelector('input[name="setting-switch"]').addEventListener('change', function() {
    if(this.checked) {
        setting.style = 'display: block;';
    } else {
        setting.style = '';
    }
});