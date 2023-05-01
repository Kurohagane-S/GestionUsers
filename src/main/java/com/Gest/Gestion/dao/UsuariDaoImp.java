package com.Gest.Gestion.dao;

import com.Gest.Gestion.Models.User;
import java.sql.*;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Repository
@Transactional
public class UsuariDaoImp implements UsuarioDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<User> getUsuario() {
        String query = "FROM User";
        Conexion();
        if (entityManager!=null){
            System.out.println(" ***************************************** WHAATTTTTTTTTT *****************");
            return entityManager.createQuery(query).getResultList();
        }else{

            List<User> DummyList =new ArrayList<User>();
            //Adding elements in the List
            User usuario = new User();
            usuario.setName("newname");
            usuario.setAge("newname");
            usuario.setId(891L);
            usuario.setPass("newname");
            usuario.setEmail("newname");
            usuario.setSalary("newname");
            usuario.setStartdate("newname");
            usuario.setOffice("newname");
            DummyList.add(usuario);
            System.out.println(" ***************************************** YES *****************");
            System.out.println(DummyList.toArray().toString());
            return DummyList;
        }
/*
         List<User> resultado = entityManager.createQuery(query).getResultList();
        return resultado;*/
    }

    @Override
    public void eliminar(Long id) {
        User usuario = entityManager.find(User.class,id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(User usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public User verifyLoginData(User usuario) {
        /*String query = "FROM User WHERE email = :email AND Pass = :password";
        System.out.println("*******************************");
        List<User> Lista = entityManager.createQuery(query)
                .setParameter("email",usuario.getEmail())
                .setParameter("password",usuario.getPass())
                .getResultList();

        System.out.println(query);
        System.out.println(Lista.toArray().toString());

        return !Lista.isEmpty();*/
        String query = "FROM User WHERE email = :email";
        List<User> Lista = entityManager.createQuery(query)
                .setParameter("email",usuario.getEmail())
                .getResultList();
        if (Lista.isEmpty()) {
            return null;
        }
        String PasswordEncrypted = Lista.get(0).getPass();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        // boolean PassTrue = argon2.verify(PasswordEncrypted, usuario.getPass());
        //return argon2.verify(PasswordEncrypted, usuario.getPass());
        if (argon2.verify(PasswordEncrypted, usuario.getPass())){
            return Lista.get(0);
        }
        return null;
    }

    // CONEXION MANUAL A BASE DE DATOS
    public void Conexion(){
        Connection con = null;
        Statement statement = null;
        ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/maindatabase?useSSl=false","root","");
            statement = (Statement) con.createStatement();
            String sql;
            sql = "SELECT * FROM `users`";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ArrayList<String> inner = new ArrayList<String>();
                inner.add(resultSet.getString("name"));
                outer.add(inner);
            }
            System.out.println("The name are as follows:");
            for (int i = 0; i < outer.size(); i++) {
                System.out.println(outer.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


