
var menu = document.getElementById('menubar');
document.querySelector('input[name="menu-switch"]').addEventListener('change', function() {
    if(this.checked) {
        menu.style = 'height: 80vh';
    } else {
        menu.style = '';
    }
});