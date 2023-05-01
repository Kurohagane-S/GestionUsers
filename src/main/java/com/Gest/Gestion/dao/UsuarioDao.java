package com.Gest.Gestion.dao;

import com.Gest.Gestion.Models.User;

import java.util.List;

public interface UsuarioDao {
    List<User> getUsuario();

    void eliminar(Long id);

    void registrar(User usuario);

    User verifyLoginData(User usuario);
}
