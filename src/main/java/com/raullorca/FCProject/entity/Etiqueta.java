package com.raullorca.FCProject.entity;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name = "Etiqueta")
@Entity
public class Etiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String etiqueta;
    @ManyToMany(mappedBy = "etiquetasId")
    List<Candidato> candidatos;

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
