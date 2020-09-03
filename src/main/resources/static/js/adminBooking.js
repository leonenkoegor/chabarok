import Request from "/js/Request.js";

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

        let getBooking = new Request('GET', '/admin/ordered/tables/get?date=' + this.value);
        getBooking.sendRequest(function (xhr) {
            row.innerHTML = '';
            if(xhr["data"] === undefined || xhr["data"].length === 0){
              message.style.display = 'block';
            }else {
                message.style.display = 'none';
                for (let i = 0; i < xhr["data"].length; i++) {
                    let booking = document.createElement('div');
                    booking.className = 'col-md-4';
                    booking.innerHTML = `
            <div class="card">
             <div class="card-body border">
            <h5 class="text-left card-title">Бронь № ${xhr["data"][i]["id"]}</h5>
            <h5 class="text-left card-title">Фамилия и имя: ${xhr["data"][i]["name"]}</h5>
            <h5 class="text-left card-title">Номер телефона: ${xhr["data"][i]["phoneNumber"]}</h5>
            <h5 class="text-left card-title">Дата: ${xhr["data"][i]["orderedDate"]}</h5>
            <h5 class="text-left card-title">Время с:${xhr["data"][i]["orderedFromTime"]}</h5>
            <h5 class="text-left card-title">Количество гостей:${xhr["data"][i]["peoples"]}</h6>
            </div>
            </div>
            `
                    row.append(booking);
                }
            }
        })

})
