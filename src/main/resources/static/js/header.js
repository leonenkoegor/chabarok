let navList = document.querySelectorAll('header nav li');

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