package com.Gest.Gestion.controllers;

import com.Gest.Gestion.Models.User;
import com.Gest.Gestion.Utils.JwtUtil;
import com.Gest.Gestion.dao.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UsuarioDao userdao;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "api/Login", method = RequestMethod.POST)
    public String registerUsuario(@RequestBody User usuario) {

        User LoggedUser = userdao.verifyLoginData(usuario);

        if (!(LoggedUser==null)){

            String tokenJWT = jwtUtil.create(String.valueOf(LoggedUser.getId()),LoggedUser.getEmail());
            //return "YES";
            return tokenJWT;
        }else{
            return "NO";
        }
    }

}
