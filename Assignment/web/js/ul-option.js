
var index = 0;
let l = document.querySelectorAll('#select-btn li');
let u = document.querySelector('#select-btn ul');
let h = l[0].offsetHeight;
var s = h * l.length;
u.addEventListener('click', function() {
    u.style.height = s + "px";
    s = h * (l.length + 1) - s;
})
l.forEach((e) => {
    e.addEventListener('click', function() {
        index = (index == e.getAttribute('values')) ? -index : e.getAttribute('values');
        l.forEach((f) => {
            f.style = 'transform: translateY(-' + (e.offsetHeight * index) + 'px);'
        });
    })
});