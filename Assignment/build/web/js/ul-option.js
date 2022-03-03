var select_btn_list = document.getElementsByClassName('select-btn');
for (var i = 1; i < select_btn_list.length + 1; i++) {
    var select_btn = select_btn_list[i];
    var select_box = document.querySelector('.select-btn:nth-child(' + i + ') .select-box');
    var select_container_list = document.querySelector('.select-btn:nth-child(' + i + ') .select-list');
    var select_list = document.querySelectorAll('.select-btn:nth-child(' + i + ') .select-list li');
    var selected = document.querySelector('.select-btn:nth-child(' + i + ') .selected p')
    var select_name = document.querySelector('.select-btn:nth-child(' + i + ') .select-name')
    if (selected.innerHTML != '') {
        select_name.classList = 'float-select-name';
    }
    var select_container_list_height = 0;
    select_box.addEventListener('click', function() {
        select_container_list_height = (select_container_list_height == 0) ? select_list.length * select_list[0].offsetHeight : 0;
        select_container_list.style = 'height: ' + select_container_list_height + 'px';
    });
    select_list.forEach((l) => {
        l.addEventListener('click', function() {
            select_name.classList = 'float-select-name';
            select_container_list.style = 'height: 0';
            select_container_list_height = 0;
            selected.innerHTML = l.innerHTML;
        })
    })
}