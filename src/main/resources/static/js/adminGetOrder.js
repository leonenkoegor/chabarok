import Request from "./Request.js";

function dateCorrected() {

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


let message = document.getElementById('message');
let date = document.getElementById('date');
let row = document.getElementById('row');
date.addEventListener('input', dateCorrected);
date.addEventListener('blur', function () {
    let getOrder = new Request('GET', '/admin/orders/get?date=' + this.value);
    row.innerHTML = '';
    getOrder.sendRequest(function (xhr) {
        if(xhr["data"] === undefined || xhr["data"].length === 0 ){
            message.style.display = 'block';
        }else {
            message.style.display = 'none';
            for (let i = 0; i < xhr["data"].length; i++) {
                let order = document.createElement('div');
                order.className = 'col-md-4';
                order.innerHTML = `
            <div class="card">
             <div class="card-body border rounded-0">
            <h5 class="text-right card-title">Заказ № ${xhr["data"][i]["id"]}</h5>
            <h5 class="text-right card-title">Фамилия и имя: ${xhr["data"][i]["name"]}</h5>
            <h5 class="text-right card-title">Номер телефона: ${xhr["data"][i]["phoneNumber"]}</h5>
            <h5 class="text-right card-title">Дата: ${xhr["data"][i]["orderedDate"]}</h5>
            <h5 class="text-right card-title mb-2">Время с:${xhr["data"][i]["orderedFromTime"]}</h5>
            <h5 class="text-right card-title mb-2">Количество гостей:${xhr["data"][i]["peoples"]}</h5>
            </div>
            </div>
           
            `
                row.append(booking);
            }
        }
    })

})
