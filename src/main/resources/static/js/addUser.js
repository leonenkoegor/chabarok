import Request from "/js/Request.js";
let add = document.getElementById('add');
let roleItems = document.getElementsByClassName('btn-light');
let success = document.getElementById('successAlert');
for (let i=0;i<roleItems.length;i++){

    roleItems[i].addEventListener('click',function () {
      let activeBnt = document.querySelector('.usersRole .btn-dark');
      if(activeBnt !== null){
          activeBnt.classList.add('btn-light');
          activeBnt.classList.remove('btn-dark');
          activeBnt.removeAttribute('disabled');
      }
        this.classList.remove('btn-light');
        this.classList.add('btn-dark');
        this.setAttribute('disabled','disabled');
    })
}
add.addEventListener('click',createUser);

function createUser() {
    let activeBnt = document.querySelector('.usersRole .btn-dark');
    let role = activeBnt.getAttribute('role');
    let send = {
        'firstName':document.getElementById('firstName').value,
        'secondName':document.getElementById('secondName').value,
        'lastName':document.getElementById('lastName').value,
        'phoneNumber':document.getElementById('phoneNumber').value,
        'username':document.getElementById('username').value,
        'password':document.getElementById('password').value,
        'role':role,
        'enabled':'true'
    }
    console.log(JSON.stringify(send));
    let request = new Request('POST','/admin/create/user',JSON.stringify(send)+'');
    request.sendRequest(function (xhr) {
        if(xhr.status ==='SUCCESS'){
            success.style.display = 'block';
            setTimeout(function () {
                success.style.display = 'none';
            },3000)
        }else{

        }
    })
}