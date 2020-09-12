import Request from "./Request.js"
let select = document.getElementById('select');
let select2 = document.getElementById('select2');
let getCategories = new Request('GET','/menu/categories/get');
let addSubCategory = document.getElementById('addSubCategory');
let search = document.getElementById('search');
addSubCategory.addEventListener('click',sendRequestForAddSubCategory);
search.addEventListener('click',getDishes);
console.log(select2.value);
select2.addEventListener('change', function() {
    let n = this.options.selectedIndex;
    let val = this.options[n].value;
    this.value = val;
})
function getDishes() {
    let request = new Request('GET','/menu/dishes/get?categoryId='+select2.getAttribute('value'));
    request.sendRequest(function (xhr) {
        if (xhr.status === 'SUCCESS') {
            console.log(xhr);
        }
    })
}
function sendRequestForAddSubCategory() {
    let send = {
        'mainCategoryName':document.getElementById('mainCategoryName'),
        'name':document.getElementById('subCategory')
    }
    let addSubCategoryRequest = new Request('GET', '/admin/menu/category/add', send);
    addSubCategoryRequest.sendRequest(function (xhr) {
        if(xhr.status ==='SUCCESS'){
            console.log('gugi');
        }
    })
}
getCategories.sendRequest(function (xhr) {
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

////////////////////////////////////////////////////
let dishCards = document.getElementById('dishCards');
let subcategory = document.getElementById('select2');
let searchSubcategoryDishes = document.getElementById('search');
let csrf = document.querySelector("meta[name='_csrf']").getAttribute("content");

searchSubcategoryDishes.addEventListener('click', searchDishes)

function searchDishes() {
    let selectedSubcategory = subcategory.options[subcategory.selectedIndex].value;
    let request = new Request('GET','/menu/dishes/get?categoryId=' + selectedSubcategory);
    request.sendRequest(function (xhr) {
        if (xhr.status === 'SUCCESS') {
            clearDishCards();
            for(let i = 0; i < xhr['data'].length; i++) {
                addDishToDishCard(xhr['data'][i]);
            }
        }
    })
}

function clearDishCards() {
    dishCards.innerHTML = "";
}

function addDishToDishCard(dish) {
    let dishCard = document.createElement('div');
    dishCard.className = 'col col-xl-2 col-lg-3 col-md-6 col-sm-12 rounded';
    dishCard.style.padding = "5px";
    dishCard.innerHTML = `
    <img src="/menu/dishes/image/get?dishId=${dish['id']}" style="margin-bottom: 6px; width: inherit; height: 200px;" class="img-thumbnail">
    <div class="d-flex flex-column">
        <input class="form-control" style="margin-bottom: 6px;" name="name" placeholder="Название" type="text" value="${dish['name']}">
        <textarea class="form-control" style="margin-bottom: 6px;" name="description" placeholder="Описание" type="text">${dish['description']}</textarea>
        <input class="form-control" style="margin-bottom: 6px;" name="weight" placeholder="Вес" type="text" value="${dish['weight']}">
        <input class="form-control" style="margin-bottom: 6px;" name="cost" placeholder="Стоимость" type="text" value="${dish['cost']}">
        <button class="btn btn-secondary delete-dish" style="margin-bottom: 6px;">Удалить</button>
        <button class="btn btn-primary update-dish">Обновить</button>
    </div>
    `

    // Update DOM tree
    dishCard.display = "none";
    dishCard.display = "block";

    let dishCardDeleteButton = dishCard.getElementsByClassName("delete-dish")[0];
    dishCardDeleteButton.addEventListener('click', function () {
        disableDish(dish['id']);
        this.parentElement.display = "none";
    });

    let dishCardUpdateButton = dishCard.getElementsByClassName("update-dish")[0];
    dishCardUpdateButton.addEventListener('click', function () {
        let name = this.parentElement.querySelector("[name=name]").value;
        let description = this.parentElement.querySelector("[name=description]").value;
        let weight = this.parentElement.querySelector("[name=weight]").value;
        let cost = this.parentElement.querySelector("[name=cost]").value;
        console.log(dish['id']);

        updateDish(dish['id'], name, description, weight, cost);
    });

    dishCards.append(dishCard);
}

function disableDish(dishId) {
    let request = new Request('POST','/admin/menu/dish/toggle?_csrf=' + csrf + '&dishId=' + dishId);
    request.sendRequest(function (xhr) {
        if (xhr.status === 'SUCCESS') {
            console.log("Dish deleted");
        }
    })
}

function updateDish(dishId, name, description, weight, cost) {
    let request = new Request('POST',
        '/admin/menu/dish/update'
            + '?_csrf=' + csrf
            + '&dishId=' + dishId
            + '&name=' + name
            + '&description=' + description
            + '&weight=' + weight
            + '&cost=' + cost
        );
    request.sendRequest(function (xhr) {
        if (xhr.status === 'SUCCESS') {
            console.log("Dish updated");
        }
    })
}
