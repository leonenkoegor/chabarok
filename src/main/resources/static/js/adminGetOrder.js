import Request from "./Request.js";
let message = document.getElementById('message');
let date = document.getElementById('date');
let row = document.getElementById('row');
setInterval(function () {
    if (date.value){
    let getOrder = new Request('GET', '/admin/orders/get?date=' + date.value);
    row.innerHTML = '';
    getOrder.sendRequest(function (xhr) {
        if (xhr["data"] === undefined || xhr["data"].length === 0) {
            console.log(xhr["data"]);
            message.style.display = 'block';
        } else {
            message.style.display = 'none';
            for (let i = 0; i < xhr["data"].length; i++) {
                let order = document.createElement('div');
                order.className = 'col-md-4';
                order.innerHTML = `
            <div class="card">
             <div class="card-body border rounded-0">
            <h5 class="text-left card-title">Заказ № ${xhr["data"][i]["id"]}</h5>
            <h5 class="text-left card-title">Фамилия и имя: ${xhr["data"][i]["name"]}</h5>
            <h5 class="text-left card-title">Номер телефона: ${xhr["data"][i]["phoneNumber"]}</h5>
            <h5 class="text-left card-title">Дата: ${xhr["data"][i]["date"]}</h5
            <h5 class="text-left card-title">Цена:${xhr["data"][i]["totalPrice"]}</h5>
            <h5 class="text-left card-title">Адрес:${xhr["data"][i]["address"]}</h5>
            <h5 class="text-left card-title">Способ оплаты:${xhr["data"][i]["isToPayByCard"] ? ' картой' : ' наличными'}</h5>
            </div>
            </div>
           
            `
                row.append(order);
            }
        }

    })}
},30000)
function dateCorrected() {
    let message = document.getElementById('message');
    let date = document.getElementById('date');
    let row = document.getElementById('row');
    if (this.value.length === 2) {
        if (this.value[1] != '/') {
            this.value += '/';
        }

        if (this.value[0] === '/' || this.value[1] === '/') {
            this.value = '';
        }
    }
    if(this.value.length ===5){
        if (this.value[4] != '/') {
            this.value += '/';
        }
    }
}
date.addEventListener('input', dateCorrected);
date.addEventListener('blur', function () {
    let getOrder = new Request('GET', '/admin/orders/get?date=' + this.value);
    row.innerHTML = '';
    getOrder.sendRequest(function (xhr) {
        if(xhr["data"] === undefined || xhr["data"].length === 0 ){
            console.log(xhr["data"]);
            message.style.display = 'block';
        }else {
            message.style.display = 'none';
            for (let i = 0; i < xhr["data"].length; i++) {
                let order = document.createElement('div');
                order.className = 'col-md-4';
                order.innerHTML = `
            <div class="card">
             <div class="card-body border rounded-0">
            <h5 class="text-left card-title">Заказ № ${xhr["data"][i]["id"]}</h5>
            <h5 class="text-left card-title">Фамилия и имя: ${xhr["data"][i]["name"]}</h5>
            <h5 class="text-left card-title">Номер телефона: ${xhr["data"][i]["phoneNumber"]}</h5>
            <h5 class="text-left card-title">Дата: ${xhr["data"][i]["date"]}</h5
            <h5 class="text-left card-title">Цена:${xhr["data"][i]["totalPrice"]}</h5>
            <h5 class="text-left card-title">Адрес:${xhr["data"][i]["address"]}</h5>
            <h5 class="text-left card-title">Способ оплаты:${xhr["data"][i]["isToPayByCard"]?' картой':' наличными'}</h5>
            </div>
            </div>
           
            `
                row.append(order);
            }
        }
    })

})
