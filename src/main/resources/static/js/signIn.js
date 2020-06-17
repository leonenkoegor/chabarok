let url = window.location.href;
let signInForm = document.getElementById('signInForm');
if(url === 'http://localhost:8080/login?error'){
    let signError = document.createElement('div');
    signError.className = 'signInError';
    signError.textContent = 'Неверный логин или пароль';
    signInForm.append(signError);
}