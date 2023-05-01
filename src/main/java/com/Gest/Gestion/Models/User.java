package com.Gest.Gestion.Models;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@ToString @EqualsAndHashCode
public class User{

    @Id
    @Getter @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter @Setter
    @Column(name = "Name")
    private String Name;
    @Getter @Setter
    @Column(name = "Position")
    private String Position;
    @Getter @Setter
    @Column(name = "Office")
    private String Office;
    @Getter @Setter
    @Column(name = "Age")
    private String Age;
    @Getter @Setter
    @Column(name = "Startdate")
    private String Startdate;
    @Getter @Setter
    @Column(name = "Salary")
    private String Salary;
    @Getter @Setter
    @Column(name = "pass")
    private String Pass;
    @Getter @Setter
    @Column(name = "email")
    private String email;
}
