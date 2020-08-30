import Request from "./Request.js"
let menu1 = document.getElementById('hotFood');
let menu2 = document.getElementById('coldFood');
let menu3 = document.getElementById('drinks');
let cardContainer = document.getElementById('cardContainer');
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
                let request = new Request('GET','/menu/dishes/get?categoryId='+category.getAttribute('subId'));
                request.sendRequest(function (xhr) {
                    if(xhr.status ==='SUCCESS'){
                        cardContainer.innerHTML = '';
                        let foodList = xhr.data;
                        for (let i=0;i<foodList.length;i++){
                        let card = document.createElement('div');
                        card.classList.add('card');

                        card.setAttribute('subId',foodList[i]["id"]);
                        card.innerHTML = `
                        <div class="card-img"></div>
                <h3>${foodList[i]["name"]}
                </h3>
                <p class="foodDescription">${foodList[i]["description"]}</p>
                <div class="foodParamContainer">
                    <div class="weight">${foodList[i]["weight"]}</div>
                    <div class="price">${foodList[i]["cost"]}</div>
                </div>
                <div class="addToCartBtn">Добавить в корзину</div>
            </div>
                  `
                            cardContainer.append(card);
                        }
                    }else{

                    }
                })
            })

        }

})


