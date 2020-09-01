let icon = document.getElementById('icon');
let nav = document.getElementById('nav');
let li = document.getElementsByClassName('ft');
icon.addEventListener('click',burgerMenu);
function burgerMenu() {
    if(nav.classList.contains('activeBtn')){
        nav.classList.add('close');
        nav.classList.remove('activeBtn');
        nav.style.background = 'rgba(0,0,0,0)';
        for (let i=0;i<6;i++){
            li[i].classList.remove('responsive');
            li[i].classList.add('link');
        }
    }else if(nav.classList.contains('close')){
        nav.classList.add('activeBtn');
        nav.classList.remove('close');
        nav.style.background = '#263333';
        for (let i=0;i<6;i++){
           li[i].classList.add('responsive');
            li[i].classList.remove('link');
        }
    }

}


