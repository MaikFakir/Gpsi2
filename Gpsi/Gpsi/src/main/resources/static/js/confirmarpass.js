function validarPasswords() {
    var pass = document.getElementById("password").value;
    var repass = document.getElementById("confirmPssword").value;

    if (pass != repass) {
        alert('Las contraseñas no son iguales.');
    } else {

        alert('Okay');
        document.all["frm"].submit();
    }
}