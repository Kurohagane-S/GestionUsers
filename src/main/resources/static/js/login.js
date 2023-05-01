// Alert de prueba
//alert(Esto es una prueba);
//onclick="eliminarUsuario('+usuario.id+')"


// Call the TableDataBaseFill jQuery plugin
$(document).ready(function() {
    //onready
});


async function IniciarSesion() {

    let datos = {};
    datos.email = document.getElementById('exampleInputEmail').value;
    datos.pass = document.getElementById('exampleInputPassword').value;


     const rawResponsejs = await fetch('api/Login', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });

      const respuesta = await rawResponsejs.text();
      console.log(respuesta);
        //location.reload();
        if(respuesta != "NO"){
                    localStorage.token = respuesta;
                    localStorage.email = datos.email;
                    console.log("Login Iniciado");
                    window.location.href = 'BaseDB.html';
                    console.log("Login Iniciado");
                }else{
                    console.log("Failed password or email wrong here!");
                    alert("Authentication Failed");
                }

/*
        if(respuesta == "YES"){
            console.log("Login Iniciado");
            window.location.href = 'BaseDB.html';
            console.log("Login Iniciado");
        }else{
            console.log("Failed password or email wrong here!");
            alert("Authentication Failed");
        }*/

}
