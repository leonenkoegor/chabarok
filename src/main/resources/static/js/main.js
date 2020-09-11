import Request from "./Request.js"

let eventsContainer = document.getElementById('events');
let getEvents = new Request('GET','/events/get');



getEvents.sendRequest(function (xhr) {
    if(xhr !=null) {
        if (xhr.status === 'SUCCESS') {
            eventsContainer.innerHTML = '';
            for (let i = 0; i < xhr["data"].length; i++) {
                let url = `/events/image/get?eventId=${xhr["data"][i]["id"]}`;
                let event = document.createElement('div');
                event.className = 'event-content';
                event.innerHTML = `
            <div class="photo" style="background-image: url(${url})"></div>
                <p class="photo-desc">${xhr["data"][i]["description"]}</p>
            `
                eventsContainer.append(event);
            }
        }
    }
})


