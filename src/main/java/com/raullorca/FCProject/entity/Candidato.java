package com.raullorca.FCProject.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name="candidatos")
public class Candidato {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombreCompleto;
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private String telefono;

    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "pais")
    private String pais;

    @Column(name = "presencialidad")
    private Boolean presencialidad;
    @Column(name = "traslado")
    private Boolean traslado;
    @Column(name = "imagen")
    private String imagen;
    @Column(name = "curriculum")
    private String curriculum;

    //Relación con user
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private AppUser appUserRel;

    public Candidato() {}

    public Candidato(String nombreCompleto, String email, String telefono, String ciudad, String pais,
                     Boolean presencialidad, Boolean traslado, String imagen, String curriculum
                     ){
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.pais = pais;
        this.presencialidad = presencialidad;
        this.traslado = traslado;
        this.imagen = imagen;
        this.curriculum = curriculum;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Boolean getPresencialidad() {
        return presencialidad;
    }

    public void setPresencialidad(Boolean presencialidad) {
        this.presencialidad = presencialidad;
    }

    public Boolean getTraslado() {
        return traslado;
    }

    public void setTraslado(Boolean traslado) {
        this.traslado = traslado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public AppUser getAppUserRel() {
        return appUserRel;
    }

    public void setAppUserRel(AppUser appUserRel) {
        this.appUserRel = appUserRel;
    }
}