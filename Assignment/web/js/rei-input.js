var rei_input_label =  document.querySelectorAll('.rei-input-label');
rei_input_label.forEach((l) => {
    if(l.querySelector('input').getAttribute('value')!=='' && l.querySelector('input').getAttribute('value')!==null){
        l.querySelector('.rei-float-name').classList = 'rei-float rei-float-name';
    }
    l.querySelector('input').addEventListener('input', function(){
        if(this.value=='') l.querySelector('.rei-float-name').classList = 'rei-float-name';
        else {l.querySelector('.rei-float-name').classList = 'rei-float rei-float-name';}
    }) 
});
