import Request from "./Request.js";

Array.prototype.addEvents = function(event){
    for (let i=0;i<this.length;i++){
        this[i].addEventListener('click',event);
    }
}
let date = document.getElementById('date');
let guestCount =document.getElementById('guestsCount');
let searchTable = document.getElementById('searchTable');
let clearInput =function(){
    this.value = '';
}
let returnVal = function(el,val){
    el.value = val;
}

date.addEventListener('click',clearInput);
guestCount.addEventListener('click',clearInput);

//date.addEventListener('blur',function (event) {
//    returnVal(event.target,'Дата');
//})
//guestCount.addEventListener('blur',function (event) {
//    returnVal(event.target,'Количество гостей');
//})

searchTable.addEventListener('click',sendBookingRequest);


function sendBookingRequest() {
    let trueFormat = String(document.getElementById('fromTime').value);
    let trueFormat2 =String(document.getElementById('toTime').value);
    if(trueFormat){
        trueFormat = trueFormat.substring(1);
    }
    if(trueFormat2[0]===0){
        trueFormat2 = trueFormat.substring(1);
    }
    let send = {
        'orderedDate':document.getElementById('date').value,
        'orderedFromTime':trueFormat,
        'orderedToTime':trueFormat2,
        'peoples':document.getElementById('guestsCount').value,
        'name':document.getElementById('firstNameAndSecondName').value,
        'phoneNumber':document.getElementById('phoneNumber').value,
    }
    let request = new Request('GET','/ordered/table/add', send);
    request.sendRequest(function (xhr) {
        if(xhr.status ==='SUCCESS'){
            console.log('всё норм');
        }else{

        }
    })
}