import Request from "/js/Request.js";
let navList = document.querySelectorAll('header nav li');
let addUser = document.getElementById('addUser');
let main = document.querySelector('main');
document.getElementById('load');
for(let i=0;i<navList.length;i++){
    navList[i].addEventListener('click',function () {
        for (let j=0;j<navList.length;j++){
            if(navList[j].classList.contains('active')){
                navList[j].classList.remove('active');
            }
        }
        this.classList.add('active');
    });
}

addUser.addEventListener('click',getAddUserPage);

function getAddUserPage() {
    let request = new Request('GET','/admin/get/fragment/addUser', {}, false);
    request.sendRequest(function (xhr) {
        const g = document.createRange().createContextualFragment(xhr);
        main.append(g);
    })
}


