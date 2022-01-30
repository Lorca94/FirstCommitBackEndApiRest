package com.raullorca.FCProject.entity;

import javax.persistence.*;

@Entity
//Hibernate creará o buscará una tabla con este nombre
@Table(name="user")
public class AppUser {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="username")
    private String username;
    @Column(name="email")
    private String email;
    @Column(name = "password")
    private String password;

    public AppUser(){}

    public AppUser(String username,String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    //Getter and setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
