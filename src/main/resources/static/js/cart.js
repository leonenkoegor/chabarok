import Request from "./Request.js"
let dish ='';
let message = document.getElementById('message');
let order = document.getElementById('order');
let cardContainer = document.getElementById('cardContainer');
let totalPrice = document.querySelector('#resultCost h2');
let name = document.getElementById('name');
let address = document.getElementById('address');
let phoneNumber = document.getElementById('phoneNumber');
phoneNumber.addEventListener('click',placeHolder);
address.addEventListener('click',placeHolder);
name.addEventListener('click',placeHolder);
phoneNumber.addEventListener('blur',placeHolder);
address.addEventListener('blur',placeHolder);
name.addEventListener('blur',placeHolder);
console.log(JSON.parse(localStorage.getItem('cart')));

    let getData = JSON.parse(localStorage.getItem('cart'));
    let listArray = getData.list;

let isDeliver = document.getElementById('deliverValue');
let isPayByCard = document.getElementById('isPayByCard');
let btnOrder = document.getElementById('btn');

function placeHolder(){
    if(event.type ==='click'){
        if(this.value === this.name){
            this.value = '';
        }
    }else if(event.type ==='blur'){
        if(this.value === ''){
            this.value = this.name;
        }
    }

}
btnOrder.addEventListener('click', sendOrder);

   function sendOrder() {

       let dishContainer = document.getElementsByClassName('orderElem');
       console.log(dishContainer);
       for (let i = 0; i < dishContainer.length - 1; i++) {
           dish += dishContainer[i].getAttribute('subId') + 'A' + dishContainer[i].querySelector('.count').textContent + '&dishAndAmount=';
       }

       dishContainer.length === 0 ? '':dish += dishContainer[dishContainer.length - 1].getAttribute('subId') + 'A' + dishContainer[dishContainer.length - 1].querySelector('.count').textContent;
       let send = {
           'name': name.value,
           'dishAndAmount': dish,
           'address': address.value,
           'phoneNumber': phoneNumber.value,
           'isToDeliver': isDeliver.style.color === 'black' ? true : false,
           'isToPayByCard': isPayByCard.style.color === 'black' ? true : false,
       }
       if (address.value === address.name || name.value === name.name || phoneNumber.value === phoneNumber.name) {
           let errMessage = document.getElementById('errorMessage1');
           errMessage.style.display = 'block';
           setTimeout(function () {
                errMessage.style.display = 'none';
           },6000);
       }else if(JSON.parse(localStorage.getItem('cart'))===null){
           let errMessage = document.getElementById('errorMessage2');
           errMessage.style.display = 'block';
           setTimeout(function () {
               errMessage.style.display = 'none';
           },6000);
       }else{
           let request = new Request('GET', '/cart/dishes/order', send);
           request.sendRequest(function (xhr) {
               if (xhr.status === 'SUCCESS') {
                   console.log(xhr);
                   localStorage.clear();
                   message.style.display = 'block';
                   setTimeout(() => {
                       message.style.display = 'none';
                   }, 6000)
               }else {

               }
           })
       }
   }
function plusFunc() {
    let startPrice = parseFloat(this.parentElement.parentElement.parentElement.getAttribute('price'));
    totalPrice.textContent = (parseFloat(totalPrice.textContent) + startPrice).toFixed(2);
    let value = this.nextElementSibling.textContent;
    value++;
    this.nextElementSibling.textContent = value;
    this.parentElement.previousElementSibling.textContent = (parseFloat(this.parentElement.previousElementSibling.textContent) + startPrice).toFixed(2);

}

function minusFunc() {
    let startPrice = parseFloat(this.parentElement.parentElement.parentElement.getAttribute('price'));
    let value = this.previousElementSibling.textContent;
    if (value != 1) {
        totalPrice.textContent = (parseFloat(totalPrice.textContent) - startPrice).toFixed(2);
        value--;
        this.previousElementSibling.textContent = value;
        this.previousElementSibling.previousElementSibling.parentElement.previousElementSibling.textContent = (this.previousElementSibling.previousElementSibling.parentElement.previousElementSibling.textContent - startPrice).toFixed(2) ;

    }

}

let list = '';
 for (let i = 0; i < listArray.length; i++) {
     list += 'dishes=' + listArray[i] + '&';}
list = list.substring(0, list.length - 1);

 let request = new Request('GET', '/cart/dishes/get?' + list);

 request.sendRequest(function (xhr) {
     let count = 0;
     if (xhr.status === 'SUCCESS') {
        cardContainer.innerHTML = '';
         for (let i = 0; i < xhr["data"].length; i++) {
             count+= xhr["data"][i]["cost"];
             totalPrice.textContent = count.toFixed(2);
             let url = `/menu/dishes/image/get?dishId=${xhr["data"][i]["id"]}`;
             let card = document.createElement('div');
             card.className = 'card';

             card.innerHTML = `
                 <div class="card-img" style="background-image: url(${url})"></div>
                 <h3>${xhr["data"][i]["name"]}</h3>
                 <p class="foodDescription">${xhr["data"][i]["description"]}</p>
                 <div class="foodParamContainer">
                     <div class="weight">${xhr["data"][i]["weight"]}г</div>
                     <div class="price">${xhr["data"][i]["cost"]}р.</div>
                 </div>
                 `
             let li = document.createElement('li');
             li.className = 'orderElem';
             li.setAttribute('subId', xhr["data"][i]["id"]);
             li.setAttribute('price', xhr["data"][i]["cost"]);
             li.style.position = 'relative';
             let deleted = document.createElement('div');
             deleted.className = 'deleteImg';
             deleted.innerHTML = `
             <i class="fas fa-trash-alt"></i>
             `
             deleted.addEventListener('click',function () {

                 let arr = JSON.parse(localStorage.getItem('cart'));
                 let listArray = arr["list"];
                 let pos = listArray.indexOf(parseInt(this.parentElement.getAttribute('subId')),0);
                 listArray.splice(pos,1);
                 let data = {
                     list:listArray,
                     isDeliver:arr.isDeliver
                 }
                 localStorage.setItem('cart',JSON.stringify(data));
                 totalPrice.textContent = (parseFloat(parseFloat(totalPrice.textContent)-this.parentElement.querySelector('.cost').textContent)).toFixed(2);
                 this.parentElement.style.display = 'none';
             })
             li.innerHTML = `
                 
                     <div>
                             <span class="name">${xhr["data"][i]["name"]}</span>
                         </div>
                         <div class="itemFlex">
                             <div class="cost">${xhr["data"][i]["cost"]}</div>
                             <div class="elemParam">
                             <div class="plus">+</div><div class="count">1</div><div
                                 class="minus">-</div>
                               </div>
                         </div>
                 `
             li.prepend(deleted);
             li.addEventListener('mouseover',function () {
                 this.firstChild.style.display = 'block';
             })
             li.addEventListener('mouseout',function () {
                 this.firstChild.style.display = 'none';
             })
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


