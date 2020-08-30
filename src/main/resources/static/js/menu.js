import Request from "./Request.js"
let categories = document.querySelectorAll('.subMenu li');
let menu = document.getElementById('categories');
let getCategories = new Request('GET','/menu/categories/get');

getCategories.sendRequest(function (xhr) {
    console.log(xhr);
        let categoryList = xhr.data;
        for(let i = 0;i<categoryList.length;i++){
            let category = document.createElement('li');
            category.setAttribute('subId',categoryList[i]["id"]);
            category.textContent = categoryList[i]["name"];
            menu.append(category);
        }
})
 for(let i=0;i<categories.length;i++){
     categories[i].addEventListener('click',function () {

        let request = new Request('GET','/menu/dishes/get?categoryId'+categories[i].getAttribute('subId'));
           request.sendRequest(function (xhr) {
          if(xhr.status ==='SUCCESS'){
                console.log(xhr);
            }else{

             }
         })
     })
 }