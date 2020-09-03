import Request from "./Request.js"
let dish ='';
let message = document.getElementById('message');
let order = document.getElementById('order');
let cardContainer = document.getElementById('cardContainer');
let totalPrice = document.getElementById('resultCost');
let getData = JSON.parse(localStorage.getItem('cart'));
let listArray = getData.list;
let btnOrder = document.getElementById('btn');
btnOrder.addEventListener('click', sendOrder);

   function sendOrder() {
       let today = new Date();
       let dd = String(today.getDate()).padStart(2, '0');
       let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
       let yyyy = today.getFullYear();
       let HH = today.getHours();
       let minutes = today.getMinutes()<10? '0'+today.getMinutes():today.getMinutes();

       today = dd + '.' + mm + '.' + yyyy + ' ' + HH + ':' + minutes ;
       let dishContainer = document.getElementsByClassName('orderElem');
       console.log(dishContainer);
       for (let i=0;i<dishContainer.length;i++){

               dish+= dishContainer[i].getAttribute('subId')+'A'+dishContainer[i].querySelector('.count').textContent+'&';

       }
       dish = dish.substring(0, dish.length - 1);
       let send = {
           'name':document.getElementById('name').value,
           'dishAndAmount':dish,
           'totalPrice':totalPrice.textContent,
           'address':document.getElementById('address').value,
           'phoneNumber':document.getElementById('phoneNumber').value,
           'isToDeliver':true,
           'isToPayByCard':true,
           'date':today
       }
       let request = new Request('GET','/cart/dishes/order', send);
       request.sendRequest(function (xhr) {
           if(xhr.status ==='SUCCESS'){
              console.log(xhr);
              localStorage.clear();
              message.style.display = 'block';
              setTimeout(3000,function () {
                message.style.display = 'none';
              })
           }else{

           }
       })
   }
function plusFunc() {
    let value = this.nextElementSibling.textContent;
    value++;
    this.nextElementSibling.textContent = value;
    this.previousElementSibling.textContent = this.previousElementSibling.textContent * 2;
}

function minusFunc() {
    let value = this.previousElementSibling.textContent;
    if (value != 1) {
        value--;
        this.previousElementSibling.textContent = value;
        this.previousElementSibling.previousElementSibling.previousElementSibling.textContent = this.previousElementSibling.previousElementSibling.previousElementSibling.textContent / 2;
    }

}

let list = '';
for (let i = 0; i < listArray.length; i++) {
    list += 'dishes=' + listArray[i] + '&';
}
list = list.substring(0, list.length - 1);

let request = new Request('GET', '/cart/dishes/get?' + list);

request.sendRequest(function (xhr) {
    if (xhr.status === 'SUCCESS') {

        for (let i = 0; i < xhr["data"].length; i++) {
            totalPrice.valueO+=xhr["data"][i]["cost"];
            let url = `/menu/dishes/image/get?dishId=${xhr["data"][i]["id"]}`;
            let card = document.createElement('div');
            card.className = 'card';
            card.innerHTML = `
                <div class="card-img" style="background-image: url(${url})"></div>
                <h3>${xhr["data"][i]["name"]}</h3>
                <p class="foodDescription">${xhr["data"][i]["description"]}</p>
                <div class="foodParamContainer">
                    <div class="weight">${xhr["data"][i]["weight"]}</div>
                    <div class="price">${xhr["data"][i]["cost"]}</div>
                </div>
                `
            let li = document.createElement('li');
            li.className = 'orderElem';
            li.setAttribute('subId', xhr["data"][i]["id"]);
            li.innerHTML = `
                    <div>
                            <span class="name">${xhr["data"][i]["name"]}</span>
                        </div>
                        <div class="itemFlex">
                            <div class="cost">${xhr["data"][i]["cost"]}</div><div class="plus">+</div><div class="count">1</div><div
                                class="minus">-</div>
                        </div>
                `
            order.append(li);
            cardContainer.append(card);

            let plus = document.getElementsByClassName('plus');
            let minus = document.getElementsByClassName('minus');
            for (let i = 0; i < plus.length; i++) {
                plus[i].addEventListener('click', plusFunc);
                minus[i].addEventListener('click', minusFunc);
            }
        }
    } else {

    }
})


