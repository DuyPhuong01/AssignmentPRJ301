
var menu = document.getElementById('side-bar');
document.querySelector('input[name="menu-switch"]').addEventListener('change', function() {
    if(this.checked) {
        menu.style = 'left: 0';
    } else {
        menu.style = '';
    }
});