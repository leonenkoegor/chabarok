let url = window.location.href;
let password = document.getElementById('exampleInputPassword1');
let login = document.getElementById('login');
let signInForm = document.getElementById('signInForm');
let signInBtn = document.getElementById('signInBtn');
if(url.includes('?error')){
    validation(
        'Неверный логин или пароль'
    )
}
signInBtn.addEventListener('click',function (event) {
    event.preventDefault();
    if(password.value.length<6 || login.value.length<6){
        validation(
            'Поля ввода должны содержать не менее 6 символов'
        )
    }else{
        signInForm.submit();
    }
});

function validation(text) {
    if(signInBtn.nextElementSibling == undefined) {
        let signError = document.createElement('div');
        signError.className = 'signInError';
        signError.textContent = text;
        signInForm.append(signError);
    }else{
        signInBtn.nextElementSibling.textContent = 'Поля ввода должны содержать не менее 6 символов';
    }
}