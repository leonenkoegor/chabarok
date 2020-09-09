class Request {
    constructor(method, url, data, isJSON = true) {
        this.method = method;
        this.url = url;
        this.data = data;
        this.isJSON = isJSON;
    }

    sendRequest(callback) {
        const xhr = new XMLHttpRequest()

        if (this.isJSON === true) {
            xhr.responseType = 'json';
        }

        xhr.onload = () => {
            callback(xhr.response);
        }

        if (typeof this.data === "string") {
            xhr.open(this.method, this.url)
            xhr.send(this.data);
            return;
        }

        let body = "?";
        for (let key in this.data) {
            body += key + "=" + this.data[key] + "&";
        }
        body = body.substring(0, body.length - 1);

        if (this.method === "GET") {
            xhr.open(this.method, this.url + body);
            xhr.send();
        } else {
            xhr.open(this.method, this.url);
            xhr.send(body);
        }
    }
}

let dateTo = document.getElementById('date');

calendar = {};

calendar.monthName=[
    'Январь', 'Февраль', 'Март', 'Апрель',
    'Май', 'Июнь', 'Июль', 'Август',
    'Сентябрь', 'Октябрь', 'Ноябрь', 'Декабрь'
];

calendar.dayName=[
    'ПН', 'ВТ', 'СР', 'ЧТ', 'ПТ', 'СБ', 'ВС'
];

calendar.selectedDate = {
    'Day' : null,
    'Month' : null,
    'Year' : null
};

calendar.element_id=null;

calendar.selectDate = function(day,month,year) {
    calendar.selectedDate={
        'Day' : day,
        'Month' : month,
        'Year' : year
    };
    calendar.drawCalendar(month,year);
}
calendar.drawCalendar = function(month,year) {
    let tmp='';
    tmp+='<table class="calendar" cellspacing="0" cellpadding="0">';

    tmp+='<tr>';
    tmp+='<td class="navigation" '+
        'onclick="calendar.drawCalendar('+(month>1?(month-1):12)+
        ','+(month>1?year:(year-1))+');">◄<\/td>';
    tmp+='<td colspan="5" class="navigation" '+
        'onclick="calendar.drawCalendar('+
        calendar.selectedDate.Month+','+
        calendar.selectedDate.Year+');">'+
        calendar.monthName[(month-1)]+' - '+year+'<\/td>';
    tmp+='<td class="navigation" '+
        'onclick="calendar.drawCalendar('+(month<12?(month+1):1)+
        ','+(month<12?year:(year+1))+');">►<\/td>';
    tmp+='<\/tr>';

    tmp+='<tr>';
    tmp+='<th>'+calendar.dayName[0]+'<\/th>';
    tmp+='<th>'+calendar.dayName[1]+'<\/th>';
    tmp+='<th>'+calendar.dayName[2]+'<\/th>';
    tmp+='<th>'+calendar.dayName[3]+'<\/th>';
    tmp+='<th>'+calendar.dayName[4]+'<\/th>';
    tmp+='<th class="holiday">'+calendar.dayName[5]+'<\/th>';
    tmp+='<th class="holiday">'+calendar.dayName[6]+'<\/th>';
    tmp+='<\/tr>';

    // Количество дней в месяце
    var total_days = 32 - new Date(year, (month-1), 32).getDate();
    // Начальный день месяца
    var start_day = new Date(year, (month-1), 1).getDay();
    if (start_day==0) { start_day=7; }
    start_day--;
    // Количество ячеек в таблице
    var final_index=Math.ceil((total_days+start_day)/7)*7;

    var day=1;
    var index=0;
    do {
        // Начало строки таблицы
        if (index%7==0) {
            tmp+='<tr>';
        }
        // Пустые ячейки до начала месяца или после окончания
        if ((index<start_day) || (index>=(total_days+start_day))) {
            tmp+='<td  class="grayed" > <\/td>';
        }
        else {
            var class_name='';
            // Выбранный день
            if (calendar.selectedDate.Day==day &&
                calendar.selectedDate.Month==month &&
                calendar.selectedDate.Year==year) {
                class_name='selected';

            }
            // Праздничный день
            else if (index%7==6 || index%7==5) {
                class_name='holiday';
            }

            tmp+='<td class=" '+class_name+ ' days' + '" '+
                'onclick="calendar.selectDate('+
                day+','+month+','+year+');">'+day+'<\/td>';
            day++;
        }
        // Конец строки таблицы
        if (index%7==6) {
            tmp+='<\/tr>';
        }
        index++;
    }
    while (index<final_index);

    tmp+='<\/table>';

    // Вставить таблицу календарика на страницу
    let el=document.getElementById(calendar.element_id);
    if (el) {
        el.innerHTML=tmp;
    }
}

// ID элемента для размещения календарика
calendar.element_id = 'calendar_table';

// По умолчанию используется текущая дата
calendar.selectedDate={
    'Day' : new Date().getDate(),
    'Month' : parseInt(new Date().getMonth())+1,
    'Year' : new Date().getFullYear()
};

// Нарисовать календарик
calendar.drawCalendar(
    calendar.selectedDate.Month,
    calendar.selectedDate.Year
);
let message = document.getElementById('message');
let fromTime = document.getElementById('fromTime');
let toTime = document.getElementById('toTime');
function booking(){
    let name = document.getElementById('firstNameAndSecondName');
    let phoneNumber =document.getElementById('phoneNumber') ;
    if(fromTime.value.charAt(0)==='0'){
        fromTime.value = fromTime.value.substring(1);
    }
    if(toTime.value.charAt(0)==='0'){
        toTime.value = toTime.value.substring(1);
    }
    if(calendar.selectedDate.Day<10){
        calendar.selectedDate.Day = '0'+calendar.selectedDate.Day;
    }
    if(calendar.selectedDate.Month<10){
        calendar.selectedDate.Month = '0'+calendar.selectedDate.Month;
    }
   let send = {
        'orderedDate':calendar.selectedDate.Day + '.'+calendar.selectedDate.Month+'.'+calendar.selectedDate.Year,
        'orderedFromTime':fromTime.value,
        'orderedToTime':toTime.value,
        'peoples':document.getElementById('guestsCount').value,
         'name':name.value,
         'phoneNumber':phoneNumber.value,
     }
    if(name.value === ''||phoneNumber.value ===''){
        let message = document.getElementById('message2');
        message.style.display = 'block';
        setTimeout(function () {
            message.style.display = 'none';
        },6000);
    }else {
        let request = new Request('GET', '/ordered/table/add', send);
        request.sendRequest(function (xhr) {
            message.style.display = 'block';
            message.textContent = 'Заявка на бронь успешно принята!';
            setTimeout(function () {
                message.style.display = 'none';
            }, 3000)

        })
    }
 }

 let searchTable = document.getElementById('searchTable');
 searchTable.addEventListener('click',booking);
 fromTime.addEventListener('input',timeCorrected);
 toTime.addEventListener('input',timeCorrected);

function timeCorrected() {
    if(this.value.length === 2 ){
        if(this.value[1]!=':'){
            this.value+=':';
        }
        if(this.value[0]===':' || this.value[1]===':'){
            this.value = '';
        }
    }
}


//
//
//