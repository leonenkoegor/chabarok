import Request from "/js/Request.js";
let navList = document.querySelectorAll('header nav li');
let addUser = document.getElementById('addUser');
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
    let request = new Request('GEY','/admin/get/fragment/addUser',[],'notJson');
    request.sendRequest(function (xhr) {
        if(xhr.status === 'OK'){

        }
    })
}


