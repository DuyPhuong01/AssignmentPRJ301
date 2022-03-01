
var s = 208;
var index = 0;
let u = document.querySelector('#select-btn ul');
u.addEventListener('click', function() {
    u.style.height = s + "px";
    s = 260 - s;
});

let l = document.querySelectorAll('#select-btn li');
l.forEach((e) => {
    e.addEventListener('click', function() {
        index = (index == e.getAttribute('values')) ? -index : e.getAttribute('values');
        l.forEach((f) => {
            f.style = 'transform: translateY(-' + (52 * index) + 'px);';
        });
    });
});