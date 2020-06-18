import Request from "/js/Request.js";
let roleItems = document.getElementsByClassName('btn-light');
console.log(roleItems);
for (let i=0;i<roleItems.length;i++){
    roleItems[i].addEventListener('click',function () {
      let activeBnt = document.querySelector('.usersRole .btn-dark');
      console.log(activeBnt);
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