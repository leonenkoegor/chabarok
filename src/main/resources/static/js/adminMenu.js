import Request from "./Request.js"
let select = document.getElementById('select');
let select2 = document.getElementById('select2');
let getCategories = new Request('GET','/menu/categories/get');
getCategories.sendRequest(function (xhr) {
    console.log(xhr);
    let categoryList = xhr.data;
    for(let i = 0;i<categoryList.length;i++){
        let category = document.createElement('option');
        let subCategory = document.createElement('option');
        category.value = categoryList[i]["id"];
        category.textContent = categoryList[i]["name"];
        subCategory.value = categoryList[i]["id"];
        subCategory.textContent = categoryList[i]["name"];
        select.append(category);
        select2.append(subCategory);
        }
})