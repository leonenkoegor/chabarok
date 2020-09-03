import Request from "./Request.js"
let menu1 = document.getElementById('hotFood');
let menu2 = document.getElementById('coldFood');
let menu3 = document.getElementById('drinks');
let cardContainer = document.getElementById('cardContainer');
let getCategories = new Request('GET','/menu/categories/get');

function getLocalStorage() {
    let cart = localStorage.getItem('cart');
    if(cart === null){
        let add = {
            list:[],
            isDeliver:false
        }
        return add;
    }else{
        return JSON.parse(localStorage.getItem('cart'));
    }
}


getCategories.sendRequest(function (xhr) {
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
                        let addToCartBtn = document.createElement('div');
                        card.classList.add('card');
                        let url = `/menu/dishes/image/get?dishId=${foodList[i]["id"]}`;
                        card.setAttribute('subId',foodList[i]["id"]);
                        card.innerHTML = `
                        <div class="card-img" style="background-image: url(${url});background-size: contain;"></div>
                <h3>${foodList[i]["name"]}
                </h3> 
                <p class="foodDescription">${foodList[i]["description"]}</p>
                <div class="foodParamContainer">
                    <div class="weight">${foodList[i]["weight"]}</div>
                    <div class="price">${foodList[i]["cost"]}</div>
                </div>
            </div>
                  `    ;
                        addToCartBtn.classList.add('addToCartBtn');
                            addToCartBtn.textContent = 'Добавить корзину';
                            addToCartBtn.addEventListener('click',function () {
                               let addToCart = getLocalStorage();
                               if(!addToCart["list"].includes(this.parentElement.getAttribute('subId'),0)){
                                   addToCart["list"].push(this.parentElement.getAttribute('subId'));
                               }
                               localStorage.setItem('cart',JSON.stringify(addToCart));
                            });
                            card.append(addToCartBtn);
                            cardContainer.append(card);
                        }
                    }else{

                    }
                })
            })

        }

})


