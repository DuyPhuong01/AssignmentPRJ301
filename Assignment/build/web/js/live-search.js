var items = document.getElementsByClassName('product');
var data_name = document.getElementsByClassName('product-name');
var data = [];
for(var i=0; i<data_name.length; i++){
    data.push(data_name[i].innerHTML);
}

const results = document.getElementById("product-list-1");
const search = document.getElementById("search");
let search_term = "";
const showList = () => {
    for(var i=0; i<items.length; i++){
        items[i].style.display = 'none';
        if(data[i].toLowerCase().includes(search_term)) items[i].style.display = 'block';
    }
};

search.addEventListener("input", (event) => {
    search_term = event.target.value.toLowerCase();
    showList();
});