import Request from "./Request"
let categories = document.querySelectorAll('.subMenu li');
let request = new Request('GET','/menu/categories/get');
request.sendRequest(function (xhr) {
    console.log(xhr);
})
for(let i=0;i<categories.length;i++){
    categories[i].addEventListener('click',function () {

        let request = new Request('GET','/ordered/table/add', send);
        request.sendRequest(function (xhr) {
            if(xhr.status ==='SUCCESS'){
                console.log('всё норм');
            }else{

            }
        })
    })
}