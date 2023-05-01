// Alert de prueba
//alert(Esto es una prueba);
//onclick="eliminarUsuario('+usuario.id+')"


// Call the TableDataBaseFill jQuery plugin
$(document).ready(function() {
    //onready
});


async function registerUsuarios() {

    let datos = {};
    datos.name = document.getElementById('exampleFirstName').value;
    datos.position = document.getElementById('examplePosition').value;
    datos.office = document.getElementById('exampleOffice').value;
    datos.age = document.getElementById('exampleAge').value;
    datos.startdate = document.getElementById('exampleStartdate').value;
    datos.salary = document.getElementById('exampleSalary').value;
    datos.pass = document.getElementById('exampleInputPassword').value;
    datos.email = document.getElementById('exampleInputEmail').value;

    let repetirpass = datos.pass = document.getElementById('exampleRepeatPassword').value;

    alert(parseInt(datos.age));

    //if (Type == 2 && (PageCount == 0 || PageCount == '')) {

    if ((parseInt(datos.age)>150) && (parseInt(datos.age)<18)  ){
            alert("La Edad minima es 18 y maxima es 150");
            return;
    }


    if (repetirpass != datos.pass){
        alert("La password no es igual porfavor verifica la informacion");
        return;
    }


     const rawResponsejs = await fetch('api/Usuario', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });
/*
      const listadoConvertido = await rawResponsejs.json();
      console.log(listadoConvertido);*/
        location.reload();
        console.log("Registro COMPLETADO");
        alert("Registro COMPLETADO");
        window.location.href = 'login.html';

}
