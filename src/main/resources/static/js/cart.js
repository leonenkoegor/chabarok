import Request from "./Request.js"

    let getData = JSON.parse(localStorage.getItem('cart'));
    let listArray = getData.list;
    let list ='';
    for (let i=0;i<listArray.length;i++){
            list += 'dishes=' + listArray[i] + '&';
    }
    list = list.substring(0, list.length - 1);

    let request = new Request('GET','/cart/dishes/get?' + list);
    request.sendRequest(function (xhr) {
        if(xhr.status ==='SUCCESS'){
            console.log(xhr);
        }else{

        }
    })
