import Request from "./Request.js"

let eventsContainer = document.getElementById('events');
let getEvents = new Request('GET','/admin/events/get');



getEvents.sendRequest(function (xhr) {
    if(xhr.status === 'SUCCESS'){
        for(let i=0;i<xhr["data"].length;i++){
            let url = `/menu/dishes/image/get?dishId=${xhr["data"][i]["id"]}`;
            let event = document.createElement('div');
            event.className = 'event-content';
            event.innerHTML = `
            <div class="photo" style="background-image: url(${url})"></div>
                <p class="photo-desc">${xhr["data"][i]["description"]}</p>
            `
        }
    }
})


