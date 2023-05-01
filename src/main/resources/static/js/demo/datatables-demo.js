// Alert de prueba
//alert(Esto es una prueba);
//onclick="eliminarUsuario('+usuario.id+')"


// Call the TableDataBaseFill jQuery plugin
$(document).ready(function() {
    loadUsuarios();
  $('#TableDataBaseFill').DataTable();
});


async function loadUsuarios() {
     const rawResponsejs = await fetch('api/Usuario', {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization': localStorage.token
        }
      });

      const listadoConvertido = await rawResponsejs.json();
      // FULL ARRAY DE USUARIOS
      //console.log(listadoConvertido);



        let listadoHTML = '';
        console.log("conexion no encontrada");





        for (let usuario of listadoConvertido){

            console.log('Conexion de '+usuario.id+'');

             let eliminarButton = '<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

             let usuarioHTML ='<tr><th>'+usuario.id+'</th><td>'
             +usuario.name+'</td><td>'+usuario.position+'</td><td>'
             +usuario.office+'</td><td>'+usuario.age+'</td><td>'
             +usuario.startdate+'</td><td>'+usuario.salary+'</td><th>'
             +usuario.email+'</th><th>'+eliminarButton+'</th></tr>';

                listadoHTML += usuarioHTML;

        }

         try {
                        document.querySelector('#TableDataBaseFill tbody').outerHTML = listadoHTML;
                      } catch (error) {
                        console.log("hola esto si");
                        // Expected output: ReferenceError: nonExistentFunction is not defined
                        // (Note: the exact output may be browser-dependent)
                      }
}
//
// PARA REUTILIZAR HEADERS
/*
function getHeaders(){
    return{
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    };
}*/




async function eliminarUsuario(id){
            if (!confirm('Desea eliminar este usuario con id '+id+'')){
                alert("usuario NO eliminado");
                return;
                //console.log("Usuario NO Eliminado");

            }
            const rawResponsejs = await fetch('api/Usuario/'+id, {
            method: 'DELETE',
            headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
            }
            });;
            alert("usuario ELIMINADO");
            console.log("Usuario Eliminado");
            /*
            let result = confirm('Desea eliminar este usuario con id '+id+'');
                        console.log(result);
                        if (result == true) {
                                        const rawResponsejs = await fetch('api/Usuario/'+id, {
                                              method: 'DELETE',
                                              headers: {
                                              'Accept': 'application/json',
                                              'Content-Type': 'application/json'
                                              }
                                        });;
                                         console.log("Usuario Eliminado");
                                    } else {
                                        return;
                                        console.log("Usuario NO Eliminado");
                                    }

            */
            location.reload();
        }

