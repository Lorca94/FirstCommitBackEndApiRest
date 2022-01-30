package com.raullorca.FCProject.entity;

import javax.persistence.*;

@Table(name = "Etiqueta")
@Entity
public class Etiqueta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String etiqueta;

    public Etiqueta() { }
    public Etiqueta(String etiqueta){
        this.etiqueta=etiqueta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
}
