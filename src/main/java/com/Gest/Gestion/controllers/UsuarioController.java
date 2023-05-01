package com.Gest.Gestion.controllers;

import com.Gest.Gestion.Models.User;
import com.Gest.Gestion.Utils.JwtUtil;
import com.Gest.Gestion.dao.UsuarioDao;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioDao userdao;
    @Autowired
    private JwtUtil jwtUtil;

    private boolean validarToken(String token){
        String idUser = jwtUtil.getKey(token);
        return idUser != null;
    }

    @RequestMapping(value = "api/Usuario", method = RequestMethod.GET)
    public List<User> getUsuario(@RequestHeader(value = "Authorization") String token) {

        if(!validarToken(token)){return null;}
        return userdao.getUsuario();
    }

    @RequestMapping(value = "api/Usuario", method = RequestMethod.POST)
    public void registerUsuario(@RequestBody User usuario) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024,1,usuario.getPass());
        usuario.setPass(hash);

        userdao.registrar(usuario);
    }

    @RequestMapping(value = "api/Usuario/{id}", method = RequestMethod.DELETE)
    public void Eliminar(@RequestHeader(value = "Authorization") String token, @PathVariable Long id) {
        if(!validarToken(token)){return;}
        userdao.eliminar(id);
    }


    /*
    @RequestMapping(value = "api/Usuario/{id}")
    public User getUsuario(@PathVariable Long id) {
        User usuario = new User();
        usuario.setId(id);
        usuario.setName("Maloide Caloide");
        usuario.setPosition("Animalato");
        usuario.setOffice("Bosque");
        usuario.setStartdate("0/0/0000");
        usuario.setSalary("3,000,000");
        usuario.setPass("Password");
        usuario.setEmail("maloide@gmalcom");
        usuario.setAge("1");
        return usuario;
    }
    */

}
