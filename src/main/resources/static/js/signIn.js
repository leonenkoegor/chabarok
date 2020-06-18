let url = window.location.href;
let password = document.getElementById('exampleInputPassword1');
let login = document.getElementById('login');
let signInForm = document.getElementById('signInForm');
let signBtn = document.getElementById('btn');
if(url === 'http://localhost:8080/login?error'){
    validation(
        'Неверный логин или пароль'
    )
};
signBtn.addEventListener('click',function (event) {
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
    if(signBtn.nextElementSibling == undefined) {
        let signError = document.createElement('div');
        signError.className = 'signInError';
        signError.textContent = text;
        signInForm.append(signError);
    }else{
        signBtn.nextElementSibling.textContent = 'Поля ввода должны содержать не менее 6 символов';
    }
}