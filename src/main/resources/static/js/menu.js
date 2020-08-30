import Request from "./Request.js"
let menu1 = document.getElementById('hootFood');
let menu2 = document.getElementById('coldFood');
let menu3 = document.getElementById('drinks');
let getCategories = new Request('GET','/menu/categories/get');

getCategories.sendRequest(function (xhr) {
    console.log(xhr);
        let categoryList = xhr.data;
        for(let i = 0;i<categoryList.length;i++){
            let category = document.createElement('li');
            category.setAttribute('subId',categoryList[i]["id"]);
            category.textContent = categoryList[i]["name"];
            if(categoryList[i]["mainCategoryName"] === 'Горячие блюда'){
                menu1.append(category);
            }else if(categoryList[i]["mainCategoryName"] === 'Холодные закуски'){
                menu2.append(category);
            }else{
                menu3.append(category);
            }
            category.addEventListener('click',function () {
                let request = new Request('GET','/menu/dishes/get?categoryId'+category.getAttribute('subId'));
                request.sendRequest(function (xhr) {
                    if(xhr.status ==='SUCCESS'){
                        console.log(xhr);
                    }else{

                    }
                })
            })

        }

})


