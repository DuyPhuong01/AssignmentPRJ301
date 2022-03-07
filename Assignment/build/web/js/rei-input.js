var rei_input_label =  document.querySelectorAll('.rei-input-label');
function setFloat() {
    rei_input_label.forEach((l) => {
        var i = l.querySelector('input');
        if(i.getAttribute('value')!='' && i.getAttribute('value')!=null || i.value!=''){
            l.querySelector('.rei-float-name').classList = 'rei-float rei-float-name';
        }
        l.querySelector('input').addEventListener('change', function(){
            if(this.value=='') l.querySelector('.rei-float-name').classList = 'rei-float-name';
            else {l.querySelector('.rei-float-name').classList = 'rei-float rei-float-name';}
        });
    });
}
setTimeout(setFloat, 1);