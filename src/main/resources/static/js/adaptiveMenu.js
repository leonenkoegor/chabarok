let icon = document.getElementById('icon');
let nav = document.getElementById('nav');
let li = document.getElementsByClassName('ft');
let menu = document.getElementById('nav-container');
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


window.addEventListener('scroll', function() {
    if(this.scrollY<10){
        menu.style.background = 'none';
        menu.style.top = '20px';
    }
    if (this.scrollY>50){
        menu.style.backgroundColor = 'rgba(0,0,0,0.8)';
        menu.style.top = '0';
    }
});